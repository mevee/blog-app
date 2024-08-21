package com.example.blog_webapp.services.blog;

import com.example.blog_webapp.payloads.PostModel;
import com.example.blog_webapp.payloads.UserModel;
import com.example.blog_webapp.payloads.request.CommentRequest;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BlogService {
    boolean addPost(PostModel postModel) throws Exception;
    PostModel loadPost(PostModel postModel);
    List<PostModel> loadAllPost();
    List<PostModel> loadUserPosts(String userId);
    List<PostModel> loadPopularPost(String userId);
    boolean deletePost(PostModel postModel);
    boolean hidePost(PostModel postModel);
    boolean likePost(PostModel postModel);
    boolean commentPost(CommentRequest postModel);
    boolean deleteCommentPost(CommentRequest postModel);

}
