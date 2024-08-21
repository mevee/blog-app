package com.example.blog_webapp.appEnum;


public enum FileEnums {
    IMAGE(1),
    PDF(1),
    AUDIO(3),
    TEXT(4),
    VIDEO(5);

    FileEnums(int type) {
         this.type = type;
    }
     private int type;
    @Override
    public String toString() {
        return ""+this.type;
    }
}
