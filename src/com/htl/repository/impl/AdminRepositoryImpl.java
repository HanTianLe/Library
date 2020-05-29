package com.htl.repository.impl;

import com.htl.entity.Admin;
import com.htl.repository.AdminRepository;
import com.htl.utils.JDBCTools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRepositoryImpl implements AdminRepository {

    @Override
    /** 查询bookadmin表里面的管理员信息。*/
    public Admin login(String username, String password) {
        Connection connection = JDBCTools.getConnection();
        String sql = "select * from bookadmin where username = ? and password = ?";
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Admin admin = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,username);
            statement.setString(2,password);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                admin = new Admin(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,statement,resultSet);
        }
        return admin;
    }
}
