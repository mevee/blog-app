package com.example.blog_webapp.appEnum;


public enum ErrorEnums {
    SERVERERROR(500,"INTERNAL SERVER ERROR");
    ErrorEnums(int i, String s) {
        code = "" + i;
        message = s;

    }

    private String code;
    private String message;

    @Override
    public String toString() {
        return this.message;
    }
}
