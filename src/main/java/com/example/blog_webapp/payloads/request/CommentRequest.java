package com.example.blog_webapp.payloads.request;

import lombok.Data;

@Data
public class CommentRequest {
    private String comment;
    private String userId;
    private String postId;

}
