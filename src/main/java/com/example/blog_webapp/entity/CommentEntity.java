package com.example.blog_webapp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommentEntity {
    private int id;
    private int postId;
    private String comment;
    private String reaction;
    private boolean isDeleted;
    private String createdDate;
    private String updatedDate;

}
