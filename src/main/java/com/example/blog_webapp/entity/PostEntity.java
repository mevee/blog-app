package com.example.blog_webapp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PostEntity {
    private int id;
    private String title;
    private String content;
    private int userId;
    private String category;
    private String tags;
    private boolean isVerified;
    private boolean isActive;
    private boolean isDeleted;
    private String createdDate;
    private String updatedDate;

}
