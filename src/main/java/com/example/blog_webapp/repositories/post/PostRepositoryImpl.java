package com.example.blog_webapp.repositories.post;

import com.example.blog_webapp.dataMapper.PostListMapper;
import com.example.blog_webapp.dataMapper.PostObjectMapper;
import com.example.blog_webapp.payloads.PostModel;
import com.example.blog_webapp.payloads.request.CommentRequest;
import com.example.blog_webapp.services.blog.BlogServiceImpl;
import com.example.blog_webapp.utils.SQLQueryUtil;
import com.example.blog_webapp.utils.TableUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class PostRepositoryImpl implements PostRepository {
    private String TAG = this.getClass().getSimpleName() + " ";
    private static final Logger log = LoggerFactory.getLogger(BlogServiceImpl.class);
    @Autowired
    JdbcTemplate db;

    @Override
    public boolean addPost(PostModel postModel) throws Exception {
        log.info(TAG + "addPost() postModel:" + postModel);
        int result = 0;
        if (postModel.id == 0) {
            String query = SQLQueryUtil.ADD_POST;
            log.info(TAG + "Query-" + query);
            result = db.update(query, postModel.title, postModel.content, postModel.userId, postModel.tags, postModel.category);
        } else {
            String query = SQLQueryUtil.POST_UPDATE;;
            log.info(TAG + "Query-" + query);
            result = db.update(query, postModel.title,postModel.content,postModel.tags,
                    postModel.category,
                    postModel.id
                    );
        }
        log.info(TAG + "result:" + result);

        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public PostModel loadPost(PostModel postModel) throws Exception {
        log.info(TAG + "loadPost()");
        String query = "SELECT * FROM " + TableUtil.POST.TABLE_NAME + " WHERE id = " + postModel.id;
        log.info(TAG + "----Query :" + query + "----");
        PostModel data = null;
        try {
            data = db.queryForObject(query, new PostObjectMapper());
            log.info(TAG + "----data :" + data + "----");
            return data;
        } catch (DataAccessException e) {
            log.info(TAG + "----Exception :" + e.getMessage() + "");
        }
        return data;
    }

    @Override
    public List<PostModel> loadAllPost() throws Exception {
        log.info(TAG + "loadAllPost()");
        String query = "SELECT * FROM " + TableUtil.POST.TABLE_NAME;
        log.info(TAG + "----Query :" + query + "----");
        List<PostModel> data = List.of();
        try {
            data = db.queryForObject(query, new PostListMapper());
            log.info(TAG + "----data :" + data + "----");
            return data;
        } catch (DataAccessException e) {
            log.info(TAG + "----Exception :" + e.getMessage() + "");

        }
        return data;
    }

    @Override
    public List<PostModel> loadUserPosts(String userId) throws Exception {
        log.info(TAG + "loadUserPosts()" + userId);
        String query = "SELECT * FROM " + TableUtil.POST.TABLE_NAME + " WHERE userId = " + userId;
        log.info(TAG + "----Query :" + query + "----");
        List<PostModel> data = List.of();
        try {
            data = db.queryForObject(query, new PostListMapper());
            log.info(TAG + "----data :" + data + "----");
            return data;
        } catch (DataAccessException e) {
            log.info(TAG + "----Exception :" + e.getMessage() + "");
        }
        return data;
    }

    @Override
    public List<PostModel> loadPopularPost(String userId) throws Exception {
        //TODO logic not implemented
        log.info(TAG + "loadUserPosts()" + userId);
        String query = "SELECT * FROM " + TableUtil.POST.TABLE_NAME + " WHERE userId = " + userId;
        log.info(TAG + "----Query :" + query + "----");
        List<PostModel> data = List.of();
        try {
            data = db.queryForObject(query, new PostListMapper());
            log.info(TAG + "----data :" + data + "----");
            return data;
        } catch (DataAccessException e) {
            log.info(TAG + "----Exception :" + e.getMessage() + "");
        }
        return data;
    }

    @Override
    public boolean deletePost(PostModel postModel) {
        int result = 0;
        String query = SQLQueryUtil.POST_DELETE;
        Map<String, Object> params = new HashMap();
        params.put("isDeleted", true);
        params.put("id", postModel.id);
        log.info(TAG + "Query-" + query);
        try {
            result = db.update(query, params);
        } catch (DataAccessException e) {
            log.info(TAG + "----Exception :" + e.getMessage() + "");
        }
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean hidePost(PostModel postModel) {
        int result = 0;
        String query = SQLQueryUtil.POST_ACTIVE;
        Map<String, Object> params = new HashMap();
        params.put("isActive", !postModel.isActive);
        params.put("id", postModel.id);
        log.info(TAG + "Query-" + query);
        try {
            result = db.update(query, params);
        } catch (DataAccessException e) {
            log.info(TAG + "----Exception :" + e.getMessage() + "");
        }
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean likePost(PostModel postModel) {
        return false;
    }

    @Override
    public boolean commentPost(CommentRequest postModel) {
        return false;
    }

    @Override
    public boolean deleteCommentPost(CommentRequest postModel) throws Exception {
        return false;
    }
}
