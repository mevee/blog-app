package com.example.blog_webapp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class UserEntity {
    private int id;
    private String fname;
    private String lname;
    private String email;
    private String phone;
    private String password;
    private String token;
    private boolean isVerified;
    private boolean isActive;
    private boolean isDeleted;
    private String createdDate;
    private String updatedDate;
}
