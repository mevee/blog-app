package com.example.blog_webapp.dataMapper;

import com.example.blog_webapp.payloads.PostModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostObjectMapper implements RowMapper<PostModel> {

    @Override
    public PostModel mapRow(ResultSet rs, int rowNum) throws SQLException {
        PostModel results = new PostModel();
       do {
           results.id = rs.getInt("id");
           results.userId = rs.getInt("userId");
           results.title = rs.getString("title");
           results.content = rs.getString("content");
           results.category = rs.getString("category");
           results.tags = rs.getString("tags");
           results.date = rs.getString("createdDate");
           results.isVerified = rs.getBoolean("isVerified");
           results.isActive = rs.getBoolean("isActive");
        }while (rs.next());

        return results;
    }
}
