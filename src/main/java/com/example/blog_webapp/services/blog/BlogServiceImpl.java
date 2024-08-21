package com.example.blog_webapp.services.blog;

import com.example.blog_webapp.payloads.PostModel;
import com.example.blog_webapp.payloads.request.CommentRequest;
import com.example.blog_webapp.repositories.post.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {
    private String TAG = this.getClass().getSimpleName() + " ";
    private static final Logger log = LoggerFactory.getLogger(BlogServiceImpl.class);
    @Autowired
    PostRepository blogRepository;

    @Override
    public boolean addPost(PostModel postModel) throws Exception {
        return blogRepository.addPost(postModel);
    }

    @Override
    public PostModel loadPost(PostModel postModel) {
        log.info(TAG + "loadAllPost()");
        PostModel data = null;
        try {
            data = blogRepository.loadPost(postModel);
        } catch (Exception e) {
            log.info(TAG + "----Exception :" + e.getMessage());
        }
        return data;
    }

    @Override
    public List<PostModel> loadAllPost() {
        log.info(TAG + "loadAllPost()");
        List<PostModel> data = List.of();
        try {
            data = blogRepository.loadAllPost();
            return data;
        } catch (Exception e) {
            log.info(TAG + "----Exception :" + e.getMessage() + "");
        }
        return data;
    }

    @Override
    public List<PostModel> loadUserPosts(String userId) {
        List<PostModel> data = List.of();
        try {
            data = blogRepository.loadUserPosts(userId);
            return data;
        } catch (Exception e) {
            log.info(TAG + "----Exception :" + e.getMessage() + "");
        }
        return data;
    }

    @Override
    public List<PostModel> loadPopularPost(String userId) {
        //TODO logic not implemented
        log.info(TAG + "loadUserPosts()" + userId);
        List<PostModel> data = List.of();
        try {
            data = blogRepository.loadPopularPost(userId);
            return data;
        } catch (Exception e) {
            log.info(TAG + "----Exception :" + e.getMessage() + "");
        }

        return data;
    }

    @Override
    public boolean deletePost(PostModel postModel) {
        return blogRepository.deletePost(postModel);
    }

    @Override
    public boolean hidePost(PostModel postModel) {
        return blogRepository.hidePost(postModel);
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
    public boolean deleteCommentPost(CommentRequest postModel) {
        return false;
    }
}
