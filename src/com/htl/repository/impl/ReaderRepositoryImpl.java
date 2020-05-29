package com.htl.repository.impl;

import com.htl.entity.Reader;
import com.htl.repository.ReaderRepository;
import com.htl.utils.JDBCTools;

import java.sql.*;

public class ReaderRepositoryImpl implements ReaderRepository {

    @Override
    /** 查询Reader表里面的读者信息。*/
    public Reader login(String username, String password) {
        // 调用JDBCTools类里面的静态方法getConnection()。
        Connection connection = JDBCTools.getConnection();
        String sql = "select * from reader where username = ? and password = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Reader reader = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,username);
            statement.setString(2,password);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                reader = new Reader(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getString(6),resultSet.getString(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 调用JDBCTools类里面的静态方法getConnection()。
            JDBCTools.release(connection,statement,resultSet);
        }
        return reader;
    }
}

