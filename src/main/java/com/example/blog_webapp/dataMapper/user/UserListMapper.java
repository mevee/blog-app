package com.example.blog_webapp.dataMapper.user;

import com.example.blog_webapp.payloads.UserModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserListMapper implements RowMapper<List<UserModel>> {

    @Override
    public List<UserModel> mapRow(ResultSet rs, int rowNum) throws SQLException {
        List<UserModel> results = new ArrayList();
       do {
           UserModel model = new UserModel();
           model.id = rs.getInt("id");
           model.fName = rs.getString("fName");
           model.lName = rs.getString("lName");
           model.email = rs.getString("email");
           model.phone = rs.getString("phone");
           model.token = rs.getString("token");
           model.roleId = rs.getString("roleId");
           model.isActive = rs.getBoolean("isActive");
           model.isVerified = rs.getBoolean("isVerified");
           model.isActive = rs.getBoolean("isActive");
           model.createdDate = rs.getString("createdDate");
           results.add(model);
       }while (rs.next());

        return results;
    }
}
