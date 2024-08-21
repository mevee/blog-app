package com.example.blog_webapp.utils;

import com.example.blog_webapp.payloads.Meta;

public class ResponseMetaUtil {
    public static Meta getSuccess(String message) {
        if (message==null||message.isEmpty()){
            message = "Success";
        }
        return new Meta(200, message, "NA");
    }
    public static Meta getSuccess() {
        return new Meta(200, "Success", "NA");
    }

    public static Meta getFailed(String message) {
        if (message==null||message.isEmpty()){
            message = "Failed";
        }
        return new Meta(400, message, "NA");
    }

    public static Meta getFailed() {
        return new Meta(400, "Failed", "NA");
    }
    public static Meta FAILED = new Meta(400,"Failed","NA");
    public static Meta ERROR = new Meta(300,"AUTH ERROR","NA");

    @Override
    public String toString() {
        return super.toString();
    }
}
