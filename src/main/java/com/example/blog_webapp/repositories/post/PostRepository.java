package com.example.blog_webapp.repositories.post;

import com.example.blog_webapp.payloads.PostModel;
import com.example.blog_webapp.payloads.request.CommentRequest;

import java.util.List;

public interface PostRepository {
    boolean addPost(PostModel postModel) throws Exception;
    PostModel loadPost(PostModel postModel) throws Exception;
    List<PostModel> loadAllPost() throws Exception;
    List<PostModel> loadUserPosts(String userId) throws Exception;
    List<PostModel> loadPopularPost(String userId) throws Exception;
    boolean deletePost(PostModel postModel);
    boolean hidePost(PostModel postModel);
    boolean likePost(PostModel postModel);
    boolean commentPost(CommentRequest postModel);
    boolean deleteCommentPost(CommentRequest postModel) throws Exception ;



}
