package com.example.blog_webapp.repositories.auth;

import com.example.blog_webapp.entity.OtpEntity;

public interface AuthRepository {

    int getLastAttemptCount(String userId);

    boolean checkTokenExist(String userId) throws Exception;

    boolean checkOtpExist(String userId) throws Exception;

    OtpEntity getDbOtp(String userId) throws Exception;

    String generateToken(String userId) throws Exception;

    Boolean generateOtp(String userId) throws Exception;

    Boolean verifyOtp(String userId, String otp, int retryCount) throws Exception;

    Boolean validateToken(String token, String userId) throws Exception;

}
