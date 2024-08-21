package com.example.blog_webapp.controllers;

import com.example.blog_webapp.payloads.GenericResponse;
import com.example.blog_webapp.payloads.Meta;
import com.example.blog_webapp.payloads.PostModel;
import com.example.blog_webapp.services.blog.BlogService;
import com.example.blog_webapp.utils.ResponseMetaUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("post")
@Slf4j
public class PostController {

    @Autowired
    private BlogService blogService;

    @GetMapping("/load-all")
    ResponseEntity<GenericResponse<PostModel>> getAllBlogs() {
        GenericResponse response = new GenericResponse();
        response.meta = new Meta();
        List<PostModel> mData = new ArrayList();
        try {
            mData = blogService.loadAllPost();
            response.data = mData;
            response.meta = ResponseMetaUtil.getSuccess();
        } catch (Exception e) {
            response.meta = ResponseMetaUtil.getFailed(e.getMessage());
        }
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @PostMapping("/add")
    ResponseEntity<GenericResponse> addPost(@RequestBody PostModel postRequest) {
        GenericResponse response = new GenericResponse();
        response.meta = new Meta();
        if (postRequest == null) {
            response.meta = ResponseMetaUtil.getFailed("Request body missing");
            return new ResponseEntity(response, HttpStatus.OK);
        } else if (postRequest.title == null || postRequest.title.isEmpty()) {
            response.meta = ResponseMetaUtil.getFailed("Title is missing.");
            return new ResponseEntity(response, HttpStatus.OK);
        } else if (postRequest.content == null || postRequest.content.isEmpty()) {
            response.meta = ResponseMetaUtil.getFailed("Content is missing.");
            return new ResponseEntity(response, HttpStatus.OK);
        }

        try {
            boolean result = blogService.addPost(postRequest);
            if (result) {
                response.meta = ResponseMetaUtil.getSuccess();
            } else {
                response.meta = ResponseMetaUtil.getFailed();
            }
        } catch (Exception e) {
            response.meta = ResponseMetaUtil.getFailed(e.getMessage());
        }
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/delete")
    ResponseEntity<GenericResponse> deletePost(PostModel postRequest) {
        GenericResponse response = new GenericResponse();
        response.meta = new Meta();
        try {
            boolean result = blogService.addPost(postRequest);
            if (result) {
                response.meta = ResponseMetaUtil.getSuccess();
            } else {
                response.meta = ResponseMetaUtil.getSuccess();

            }
        } catch (Exception e) {
            response.meta = ResponseMetaUtil.getFailed(e.getMessage());
        }
        return new ResponseEntity(response, HttpStatus.OK);
    }

    @GetMapping("/block")
    ResponseEntity<GenericResponse> blockPost(PostModel postRequest) {
        GenericResponse response = new GenericResponse();
        response.meta = new Meta();
        try {
            boolean result = blogService.addPost(postRequest);
            if (result) {
                response.meta = ResponseMetaUtil.getSuccess();
            } else {
                response.meta = ResponseMetaUtil.getSuccess();
            }
        } catch (Exception e) {
            response.meta = ResponseMetaUtil.getFailed(e.getMessage());
        }
        return new ResponseEntity(response, HttpStatus.OK);
    }
}
