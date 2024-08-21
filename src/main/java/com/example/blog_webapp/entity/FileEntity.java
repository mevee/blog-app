package com.example.blog_webapp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FileEntity {
    private int id;
    private int userId;
    private int type;
    private String fileName;
    private String path;
    private boolean isDeleted;
    private String createdDate;
}
