package com.example.blog_webapp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PostCategoryEntity {
    private int id;
    private String title;
    private boolean isActive;
    private String createdDate;
    private String updatedDate;
}
