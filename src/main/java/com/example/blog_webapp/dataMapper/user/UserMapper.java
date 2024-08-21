package com.example.blog_webapp.dataMapper.user;

import com.example.blog_webapp.payloads.UserModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<UserModel> {

    @Override
    public UserModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        UserModel results = new UserModel();
       do {
           results.id = rs.getInt("id");
           results.fName = rs.getString("fName");
           results.lName = rs.getString("lName");
           results.email = rs.getString("email");
           results.phone = rs.getString("phone");
           results.token = rs.getString("token");
           results.roleId = rs.getString("roleId");
           results.isActive = rs.getBoolean("isActive");
           results.isVerified = rs.getBoolean("isVerified");
           results.isActive = rs.getBoolean("isActive");
           results.createdDate = rs.getString("createdDate");
       }while (rs.next());

        return results;
    }
}
