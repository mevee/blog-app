package com.example.blog_webapp.repositories.user;

import com.example.blog_webapp.payloads.UserModel;

import java.util.List;

public interface UserRepository {


    UserModel addUser(UserModel userModel) throws Exception;

    UserModel updateUser(UserModel userModel) throws Exception;

    boolean deleteUser(UserModel userModel) throws Exception;

    UserModel activateUser(UserModel userModel) throws Exception;

    UserModel getUserWithId(String id) throws Exception;

    void verifyUser(String id);

    UserModel loginWithEmailAndPassword(String email,String password) throws Exception;

    UserModel deActivateUser(UserModel userModel) throws Exception;

    List<UserModel> getAllUsers();

    boolean isUserExist(String userId);

    boolean isMobileExist(String mobile);

    boolean isEmailExist(String email);

}
