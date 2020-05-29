package com.htl.repository.impl;

import com.htl.repository.RegisterRepository;
import com.htl.utils.JDBCTools;

import java.sql.*;

public class RegisterRepositoryImpl implements RegisterRepository {
    @Override
    public void RegisterInsert(String username, String password, String name, String telephone, String cardid, String gender) {
        Connection connection = JDBCTools.getConnection();
        String sql = "insert into reader(username, password, name, tel, cardid, gender) values(?,?,?,?,?,?)";
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement(sql);
            statement.setString(1,username);
            statement.setString(2,password);
            statement.setString(3,name);
            statement.setString(4,telephone);
            statement.setString(5,cardid);
            statement.setString(6,gender);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCTools.release(connection,statement,null);
        }
    }

}

