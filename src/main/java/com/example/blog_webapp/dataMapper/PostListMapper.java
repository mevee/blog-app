package com.example.blog_webapp.dataMapper;

import com.example.blog_webapp.payloads.PostModel;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostListMapper  implements RowMapper<List<PostModel>> {

    @Override
    public List<PostModel> mapRow(ResultSet rs, int rowNum) throws SQLException {
        List<PostModel> results = new ArrayList();
       do {
           PostModel model = new PostModel();
           model.id = rs.getInt("id");
           model.userId = rs.getInt("userId");
           model.title = rs.getString("title");
           model.content = rs.getString("content");
           model.category = rs.getString("category");
           model.tags = rs.getString("tags");
           model.date = rs.getString("createdDate");
           model.isVerified = rs.getBoolean("isVerified");
           model.isActive = rs.getBoolean("isActive");
           results.add(model);
       }while (rs.next());

        return results;
    }
}
