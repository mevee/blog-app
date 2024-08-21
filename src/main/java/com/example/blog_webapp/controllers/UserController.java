package com.example.blog_webapp.controllers;

import com.example.blog_webapp.payloads.GenericResponse;
import com.example.blog_webapp.payloads.PostModel;
import com.example.blog_webapp.payloads.UserModel;
import com.example.blog_webapp.services.UserService;
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
@RequestMapping("users")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    ResponseEntity<GenericResponse<PostModel>> login(@RequestBody UserModel model) {
        GenericResponse result = userService.login(model.email, model.password);
        return new ResponseEntity(result, HttpStatus.OK);
    }
    @GetMapping("/get-all-users")
    ResponseEntity<GenericResponse<PostModel>> getAllBlogs() {
        GenericResponse result = userService.getAllUsers();
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @PostMapping("/add-user")
    ResponseEntity<GenericResponse> addUser(@RequestBody UserModel request) {
        GenericResponse result = userService.addUser(request);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @PostMapping("/remove-user")
    ResponseEntity<GenericResponse> deleteUser(@RequestBody UserModel request) {
        GenericResponse result = userService.deleteUser(request);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @PostMapping("/activate-user")
    ResponseEntity<GenericResponse> activateUser(@RequestBody UserModel request) {
        GenericResponse result = userService.activateUser(request);

        return new ResponseEntity(result, HttpStatus.OK);
    }

    @PostMapping("/de-activate-user")
    ResponseEntity<GenericResponse> deActivateUser(@RequestBody UserModel request) {
        GenericResponse result = userService.deActivateUser(request);

        return new ResponseEntity(result, HttpStatus.OK);
    }
}
