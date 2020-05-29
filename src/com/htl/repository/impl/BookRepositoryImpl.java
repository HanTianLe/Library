package com.htl.repository.impl;

import com.htl.entity.Book;
import com.htl.entity.BookCase;
import com.htl.repository.BookRepository;
import com.htl.utils.JDBCTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookRepositoryImpl implements BookRepository {
    @Override
    public List<Book> findAll(int index,int limit) {
        Connection connection = JDBCTools.getConnection();
        /** 获取（book表和bookcase表通过bookcaseid和id连接而成的表）的部分信息。（需要2个参数。）*/
        String sql = "select * from book,bookcase where book.bookcaseid = bookcase.id limit ?,?";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Book> list = new ArrayList<>();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,index);  /** 下标从(n-1)*6开始，n表示第几页。*/
            preparedStatement.setInt(2,limit);  /** 每页6条记录。*/
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                list.add(new Book(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getInt(5),resultSet.getDouble(6),new BookCase(resultSet.getInt(9),resultSet.getString(10))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return list;
    }

    @Override
    public int count() {
        Connection connection = JDBCTools.getConnection();
        /** 这个sql语句的作用是：显示数据库里面的图书记录的（总条数）。*/
        String sql = "select count(*) from book,bookcase where book.bookcaseid = bookcase.id";
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                count = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,preparedStatement,resultSet);
        }
        return count;
    }
}
