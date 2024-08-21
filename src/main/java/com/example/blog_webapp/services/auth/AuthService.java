package com.example.blog_webapp.services.auth;

import com.example.blog_webapp.entity.OtpEntity;
import com.example.blog_webapp.payloads.GenericResponse;
import com.example.blog_webapp.payloads.UserModel;
import com.example.blog_webapp.payloads.request.VerifyOtpRequest;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AuthService {

    GenericResponse generateToken(String userId);

    GenericResponse generateOtp(UserModel user);

    GenericResponse verifyOtp(VerifyOtpRequest request);

    GenericResponse validateToken(String token,String userId);

}
