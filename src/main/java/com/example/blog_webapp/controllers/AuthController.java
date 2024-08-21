package com.example.blog_webapp.controllers;

import com.example.blog_webapp.payloads.GenericResponse;
import com.example.blog_webapp.payloads.PostModel;
import com.example.blog_webapp.payloads.UserModel;
import com.example.blog_webapp.payloads.request.VerifyOtpRequest;
import com.example.blog_webapp.repositories.auth.AuthRepository;
import com.example.blog_webapp.services.auth.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("auth")
@Slf4j
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/send-otp")
    ResponseEntity<GenericResponse<PostModel>> senOtp(@RequestBody UserModel model) {

        GenericResponse result = authService.generateOtp(model);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @PostMapping("/verify-otp")
    ResponseEntity<GenericResponse<PostModel>> verifyOtp(@RequestBody VerifyOtpRequest request) {
        GenericResponse result = authService.verifyOtp(request);
        return new ResponseEntity(result, HttpStatus.OK);
    }

}
