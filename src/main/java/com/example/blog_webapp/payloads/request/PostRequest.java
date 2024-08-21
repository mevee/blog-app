package com.example.blog_webapp.payloads.request;

import lombok.Data;

@Data
public class PostRequest {
    private String title;
    private String content;
    private int userId;
    private String category;
    private String tags;
}
