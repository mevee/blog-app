package com.example.blog_webapp.services;

import com.example.blog_webapp.payloads.GenericResponse;
import com.example.blog_webapp.payloads.UserModel;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {
    GenericResponse<UserModel> login(String email,String password);

    GenericResponse<UserModel> addUser(UserModel userModel);

    GenericResponse<UserModel> deleteUser(UserModel userModel);

    GenericResponse<UserModel> activateUser(UserModel userModel);

    GenericResponse<UserModel> deActivateUser(UserModel userModel);

    GenericResponse<List<UserModel>> getAllUsers();

}
