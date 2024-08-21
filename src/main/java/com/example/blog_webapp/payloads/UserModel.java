package com.example.blog_webapp.payloads;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserModel {
    public int id;
    public String fName;
    public String lName;
    public String email;
    public String phone;
    public String password;
    public String roleId;
    public String token;
    public boolean isVerified;
    public boolean isActive;
    public boolean isDeleted;
    public String createdDate;
    public String updatedDate;

}
