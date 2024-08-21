package com.example.blog_webapp.payloads.request;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class VerifyOtpRequest {
    public String userId;
    public String otp;
    public int retryCount;

}
