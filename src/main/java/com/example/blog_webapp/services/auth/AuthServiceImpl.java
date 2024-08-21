package com.example.blog_webapp.services.auth;

import com.example.blog_webapp.payloads.GenericResponse;
import com.example.blog_webapp.payloads.UserModel;
import com.example.blog_webapp.payloads.request.VerifyOtpRequest;
import com.example.blog_webapp.repositories.auth.AuthRepository;
import com.example.blog_webapp.utils.ResponseGenericUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private String TAG = this.getClass().getSimpleName() + " ";
    private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Autowired
    AuthRepository repository;

    @Override
    public GenericResponse generateToken(String userId) {
        GenericResponse response = ResponseGenericUtil.getSuccess();
        if (userId == null || userId.isEmpty()) {
            return ResponseGenericUtil.getSuccess("User id required");
        }/* else {
            try {
//                String token = repository.generateToken(userId);
                return ResponseGenericUtil.getSuccess("Token::" + token);
            } catch (Exception e) {
                return ResponseGenericUtil.getFailed("Error::" + e.getMessage());
            }
        }*/
        return null;
    }

    @Override
    public GenericResponse generateOtp(UserModel userModel) {
        GenericResponse response = ResponseGenericUtil.getSuccess();

        if (userModel.id == 0) {
            return ResponseGenericUtil.getFailed("User id required");
        } else {
            try {
                Boolean result = repository.generateOtp(String.valueOf(userModel.id));
                return ResponseGenericUtil.getSuccess("OTP is sent to registered email or mobile.");
            } catch (Exception e) {
                return ResponseGenericUtil.getFailed("Error::" + e.getMessage());
            }
        }
    }

    @Override
    public GenericResponse verifyOtp(VerifyOtpRequest request) {
        GenericResponse response = ResponseGenericUtil.getSuccess();
        if (request.userId == null || request.userId.isEmpty()) {
            return ResponseGenericUtil.getFailed("User id required");
        } else if (request.otp == null || request.otp.isEmpty()) {
            return ResponseGenericUtil.getFailed("Otp is required");
        } else {
            try {
                Boolean result = repository.verifyOtp(request.userId, request.otp, request.retryCount);
                return ResponseGenericUtil.getSuccess("Otp verified.");
            } catch (Exception e) {

                return ResponseGenericUtil.getFailed( e.getMessage());
            }
        }

    }

    @Override
    public GenericResponse validateToken(String token, String userId) {
        return null;
    }
}
