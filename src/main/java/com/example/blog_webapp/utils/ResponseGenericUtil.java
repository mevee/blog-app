package com.example.blog_webapp.utils;

import com.example.blog_webapp.payloads.GenericResponse;

public class ResponseGenericUtil {

    public static GenericResponse getSuccess(String message) {
        if (message == null || message.isEmpty()) {
            message = "Success";
        }
        GenericResponse response = new GenericResponse(ResponseMetaUtil.getSuccess(message));
        response.message = message;
        response.status = "200";
        return response;
    }

    public static GenericResponse getSuccess() {
        String message = "Success";
        GenericResponse response = new GenericResponse(ResponseMetaUtil.getSuccess(message));
        response.message = message;
        response.status = "200";
        return response;
    }

    public static GenericResponse getFailed(String message) {
        if (message == null || message.isEmpty()) {
            message = "Failed";
        }
        GenericResponse response = new GenericResponse(ResponseMetaUtil.getFailed(message));
        response.message = message;
        response.status = "400";
        return response;
    }

    public static GenericResponse getFailed() {
        String message = "Failed";
        GenericResponse response = new GenericResponse(ResponseMetaUtil.getFailed());
        response.message = message;
        response.status = "400";
        return response;
    }

}
