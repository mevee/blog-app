package com.example.blog_webapp.services.user;

import com.example.blog_webapp.payloads.GenericResponse;
import com.example.blog_webapp.payloads.UserModel;
import com.example.blog_webapp.repositories.user.UserRepository;
import com.example.blog_webapp.services.UserService;
import com.example.blog_webapp.services.blog.BlogServiceImpl;
import com.example.blog_webapp.utils.ResponseGenericUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private String TAG = this.getClass().getSimpleName();
    private static final Logger log = LoggerFactory.getLogger(BlogServiceImpl.class);

    @Autowired
    UserRepository repository;

    @Override
    public GenericResponse<UserModel> login(String email, String password) {
        if (email == null || email.isEmpty()) {
            return ResponseGenericUtil.getFailed("Invalid email");
        } else if (password == null || password.isEmpty()) {
            return ResponseGenericUtil.getFailed("Invalid password");
        }
        try {
            GenericResponse response = ResponseGenericUtil.getSuccess("Success");
            try {
                response.data = repository.loginWithEmailAndPassword(email, password);

            } catch (EmptyResultDataAccessException e) {
                log.info(TAG + "EmptyResultDataAccessException" + e);
                response =   ResponseGenericUtil.getFailed("Invalid credentials");
             } catch (Exception e) {
                log.info(TAG + "Exception" + e);
                response =   ResponseGenericUtil.getFailed(e.getMessage());
             }
            return response;
        } catch (Exception e) {
            return ResponseGenericUtil.getFailed(e.getMessage());
        }
    }

    @Override
    public GenericResponse<UserModel> addUser(UserModel userModel) {
        if (userModel.fName == null || userModel.fName.isEmpty()) {
            return ResponseGenericUtil.getFailed("Invalid first name entered");
        } else if (userModel.email == null || userModel.email.isEmpty()) {
            return ResponseGenericUtil.getFailed("Invalid email entered");
        } else if (userModel.password == null || userModel.password.isEmpty()) {
            return ResponseGenericUtil.getFailed("Invalid password entered");
        } else if (userModel.phone == null || userModel.phone.isEmpty()) {
            return ResponseGenericUtil.getFailed("Invalid phone entered");
        } else if (userModel.roleId == null || userModel.roleId.isEmpty()) {
            return ResponseGenericUtil.getFailed("Role id required");
        }
        try {
            GenericResponse response = ResponseGenericUtil.getSuccess("Success");
            response.data = repository.addUser(userModel);
            return response;
        } catch (Exception e) {
            return ResponseGenericUtil.getFailed(e.getMessage());
        }
    }

    @Override
    public GenericResponse<UserModel> deleteUser(UserModel userModel) {
        if (userModel.id == 0) {
            return ResponseGenericUtil.getFailed("Invalid user id");
        }
        try {
            GenericResponse response = ResponseGenericUtil.getSuccess("Success");
            response.data = repository.deleteUser(userModel);
            return response;
        } catch (Exception e) {
            return ResponseGenericUtil.getFailed(e.getMessage());
        }
    }

    @Override
    public GenericResponse<UserModel> activateUser(UserModel userModel) {
        if (userModel.id == 0) {
            return ResponseGenericUtil.getFailed("Invalid user id");
        }
        try {
            GenericResponse response = ResponseGenericUtil.getSuccess("Success");
            response.data = repository.activateUser(userModel);
            return response;
        } catch (Exception e) {
            return ResponseGenericUtil.getFailed(e.getMessage());
        }
    }

    @Override
    public GenericResponse<UserModel> deActivateUser(UserModel userModel) {
        if (userModel.id == 0) {
            return ResponseGenericUtil.getFailed("Invalid user id");
        }
        try {
            GenericResponse response = ResponseGenericUtil.getSuccess("Success");
            response.data = repository.deActivateUser(userModel);
            return response;
        } catch (Exception e) {
            return ResponseGenericUtil.getFailed(e.getMessage());
        }
    }

    @Override
    public GenericResponse<List<UserModel>> getAllUsers() {

        try {
            GenericResponse response = ResponseGenericUtil.getSuccess("Success");
            response.data = repository.getAllUsers();
            return response;
        } catch (Exception e) {
            return ResponseGenericUtil.getFailed(e.getMessage());
        }
    }
}
