package com.example.blog_webapp.payloads;

import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PostModel {
    public int id;
    public int userId;
    public String title;
    public String content;
    public String category;
    public String tags;
    public String date;
    public boolean isVerified;
    public boolean isActive;

}
