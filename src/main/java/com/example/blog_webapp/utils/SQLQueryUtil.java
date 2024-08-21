package com.example.blog_webapp.utils;

public class SQLQueryUtil {

    //-----------------------------------USER RELATED QUERIES------------------------\\

    public static final String USER_CHECK_EMAIL_AND_PASSWORD = "Select * FROM " + TableUtil.USER.TABLE_NAME + " WHERE email =? AND password =?";
    public static final String USER_CHECK_EMAIL = "Select Count(*) FROM " + TableUtil.USER.TABLE_NAME + " WHERE email =?";
    public static final String USER_CHECK_PHONE = "Select Count(*) FROM " + TableUtil.USER.TABLE_NAME + " WHERE phone =?";
    public static final String USER_CHECK_USER = "Select Count(*) FROM " + TableUtil.USER.TABLE_NAME + " WHERE id =?";
    public static final String USER_ADD = "INSERT INTO " + TableUtil.USER.TABLE_NAME + " (fName,lName,email,password,phone,isActive) VALUES (?,?,?,?,?,?)";
    public static final String USER_UPDATE = "UPDATE " + TableUtil.USER.TABLE_NAME + " SET fName = :fName,lName = :lName,email = :email, phone = :phone, roleId = :roleId, role = :role,isActive =:isActive) VALUES(?,?,?,?,?,?) WHERE id = :id";
    public static final String USER_DELETE = "INSERT INTO " + TableUtil.USER.TABLE_NAME + " (" + TableUtil.USER.COL_F_NAME + ",\n" + " " + TableUtil.USER.COL_L_NAME + ",\n" + " " + TableUtil.USER.COL_EMAIL + ",\n" + " " + TableUtil.USER.COL_PHONE + ",\n" + " " + TableUtil.USER.COL_COUNTRY_CODE + ",\n" + " " + TableUtil.USER.COL_ROLE_ID + ",\n" + " " + TableUtil.USER.COL_PASSWORD + ")\n" + " VALUES (?, ?, ?,?, ?, ?, ?)";
    public static final String USER_LOAD_ONLY = "Select * FROM " + TableUtil.USER.TABLE_NAME + " WHERE id =:id";
    public static final String USER_LOAD_ALL = "Select * FROM " + TableUtil.USER.TABLE_NAME;
    public static final String USER_ACTIVE = "UPDATE " + TableUtil.USER.TABLE_NAME + " SET isActive = ? WHERE id = ?";
    public static final String USER_UPDATE_PASSWORD = "UPDATE " + TableUtil.USER.TABLE_NAME + " SET password = ? WHERE id = ?";
    public static final String USER_VERIFIED = "UPDATE " + TableUtil.USER.TABLE_NAME + " SET isActive = ? WHERE id = ?";
    public static final String USER_VERIFY = "UPDATE " + TableUtil.USER.TABLE_NAME + " SET isVerified = ? WHERE id = ?";

    //-----------------------------------AUTH RELATED QUERIES------------------------\\
//      id,uid,opt,lastAttempt,`updatedDate,isVerified,                                             INSERT INTO userOtp (opt,uid,lastAttempt) VALUES(123456,5,0);
    public static final String AUTH_OTP_CREATE = "INSERT INTO " + TableUtil.USER_OTP.TABLE_NAME + " (otpCode,uid,lastAttempt) VALUES(?,?,?)";
    public static final String AUTH_OTP_UPDATE = "UPDATE " + TableUtil.USER_OTP.TABLE_NAME + " SET otpCode = ?,lastAttempt = ? WHERE uid = ?";
    public static final String AUTH_OTP_EXIST = "Select COUNT(*) FROM " + TableUtil.USER_OTP.TABLE_NAME + " WHERE uid = ?";
    public static final String AUTH_OTP_GET_OTP = "Select * FROM " + TableUtil.USER_OTP.TABLE_NAME + " WHERE uid = ?";
    public static final String AUTH_OTP_ATTEMPT = "Select SUM(lastAttempt) FROM " + TableUtil.USER_OTP.TABLE_NAME + " WHERE uid = ?";
    public static final String AUTH_ATTEMPT = "UPDATE " + TableUtil.USER_OTP.TABLE_NAME + " SET lastAttempt = ? " + " WHERE id = ?";
    public static final String AUTH_OTP_VERIFY = "UPDATE " + TableUtil.USER_OTP.TABLE_NAME + " SET isVerified = ?, lastAttempt = ?" + " WHERE id =?";


    //-----------------------------------POST RELATED QUERIES------------------------\\
    public static final String ADD_POST = "INSERT INTO " + TableUtil.POST.TABLE_NAME + " (title,content,userId,tags,category) VALUES(?,?,?,?,?)";
    public static final String POST_UPDATE = "UPDATE " + TableUtil.POST.TABLE_NAME + " SET title = ?,content = ?,userId = ?,tags = ?, category = ? WHERE id = ?";
    public static final String POST_DELETE = "UPDATE " + TableUtil.POST.TABLE_NAME + " SET isDeleted = :isDeleted) VALUES(?) WHERE id = :id";
    public static final String POST_ACTIVE = "UPDATE " + TableUtil.POST.TABLE_NAME + " SET isActive = :isActive) VALUES(?) WHERE id = :id";
    public static final String POST_COMMENT_ON_OFF = "UPDATE " + TableUtil.POST.TABLE_NAME + " SET commentEnable = :isActive VALUES(?) WHERE id = :id";
    /*UPDATE blog.posts SET title='', content='', userId=0, category='', tags='', isVerified=1, isDeleted=0, isActive=1, createdDate=CURRENT_TIMESTAMP, updatedDate=CURRENT_TIMESTAMP WHERE id=0;*/
}
