package com.example.blog_webapp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class RoleEntity {
    private int id;
    private int weight;
    private String grade;
    private boolean isActive;
    private String createdDate;
    private String updatedDate;
}
