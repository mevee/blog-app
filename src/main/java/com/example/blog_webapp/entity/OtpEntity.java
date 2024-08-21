package com.example.blog_webapp.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
public class OtpEntity {
    public int id;
    public int lastAttempt;
    public String uid;
    public String otpCode;
    public boolean isVerified;
    public Timestamp updatedDate;
}
