package com.example.blog_webapp.repositories.user;

import com.example.blog_webapp.dataMapper.user.UserListMapper;
import com.example.blog_webapp.dataMapper.user.UserMapper;
import com.example.blog_webapp.payloads.UserModel;
import com.example.blog_webapp.utils.SQLQueryUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private String TAG = this.getClass().getSimpleName() + " ";
    private static final Logger log = LoggerFactory.getLogger(UserRepositoryImpl.class);
    @Autowired
    JdbcTemplate db;

    @Override
    public UserModel addUser(UserModel userModel) throws Exception {
        log.info(TAG + "addUser() addUser:" + userModel);
        int result = 0;

        if (isEmailExist(userModel.email)) {
            throw new Exception("User with email is already exist");
        } else if (isMobileExist(userModel.phone)) {
            throw new Exception("User with phone is already exist");
        } else if (isMobileExist(userModel.phone)) {
            throw new Exception("User with phone is already exist");
        } else {
            String query = SQLQueryUtil.USER_ADD;
            log.info(TAG + "Query-" + query);
            result = db.update(query, userModel.fName, userModel.lName, userModel.email, userModel.password, userModel.phone, true);
        }
        log.info(TAG + "result:" + result);

        if (result > 0) {
            return userModel;
        } else {
            return null;
        }
    }

    @Override
    public UserModel updateUser(UserModel userModel) throws Exception {
        log.info(TAG + "addUser() addUser:" + userModel);
        int result = 0;
        if (userModel.id == 0) {
            throw new Exception("Invalid user id");
        } else {
            String query = SQLQueryUtil.USER_UPDATE;
            Map<String, Object> params = new HashMap();
            params.put("fName", userModel.fName);
            params.put("lName", userModel.lName);
            params.put("email", userModel.email);
            params.put("phone", userModel.phone);
            params.put("roleId", userModel.roleId);
            params.put("isVerified", true);
            params.put("isActive", true);
            params.put("id", userModel.id);
            log.info(TAG + "Query-" + query);
            result = db.update(query, params);
        }
        log.info(TAG + "result:" + result);
        if (result > 0) {
            return userModel;
        } else {
            return null;
        }
    }

    @Override
    public void verifyUser(String id) {
        log.info(TAG + "verifyUser() id:" + id);
        int result = 0;
        String query = SQLQueryUtil.USER_VERIFY;
        log.info(TAG + "Query-" + query);
        result = db.update(query, true,id);
        log.info(TAG + "result:" + result);

    }

    @Override
    public boolean deleteUser(UserModel userModel) throws Exception {
        int result = 0;
        String query = SQLQueryUtil.USER_DELETE;
        Map<String, Object> params = new HashMap();
        params.put("isDeleted", true);
        params.put("id", userModel.id);
        log.info(TAG + "Query-" + query);
        try {
            result = db.update(query, params);
            log.info(TAG + "----result :" + result + "");

        } catch (DataAccessException e) {
            log.info(TAG + "----Exception :" + e.getMessage() + "");
        }
        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public UserModel activateUser(UserModel userModel) throws Exception {
        int result = 0;
        String query = SQLQueryUtil.USER_ACTIVE;
        Map<String, Object> params = new HashMap();
        params.put("isActive", true);
        params.put("id", userModel.id);
        log.info(TAG + "Query-" + query);
        result = db.update(query, params);
        log.info(TAG + "result-" + result);

        return userModel;
    }

    @Override
    public UserModel getUserWithId(String id) throws Exception {
        log.info(TAG + "getUserWithId() id:" + id);
        int userId = 0;
        try {
            userId = Integer.parseInt(id);
            if (userId <= 0) {
                throw new Exception("Invalid user id");
            } else {
                String query = SQLQueryUtil.USER_LOAD_ONLY;
                return db.queryForObject(query, new UserMapper());
            }
        } catch (Exception e) {
            throw new Exception("Invalid user id");
        }

    }

    @Override
    public UserModel loginWithEmailAndPassword(String email, String password) throws Exception {
        log.info(TAG + "loginWithEmailAndPassword()" + email);
        if (isEmailExist(email)) {
            String query = SQLQueryUtil.USER_CHECK_EMAIL_AND_PASSWORD;
            log.info(TAG + "Query-" + query);
            return db.queryForObject(query, new Object[]{email, password}, new UserMapper());
        } else {
            throw new Exception("Invalid email");
        }
    }

    @Override
    public UserModel deActivateUser(UserModel userModel) throws Exception {
        log.info(TAG + "deActivateUser()" + userModel);
        int result = 0;
        String query = SQLQueryUtil.USER_ACTIVE;
        Map<String, Object> params = new HashMap();
        params.put("isActive", false);
        params.put("id", userModel.id);
        log.info(TAG + "Query-" + query);
        result = db.update(query, params);
        log.info(TAG + "result-" + result);

        return userModel;
    }

    @Override
    public List<UserModel> getAllUsers() {
        log.info(TAG + "getAllUsers()");

        List<UserModel> userList = List.of();
        String query = SQLQueryUtil.USER_LOAD_ALL;
        log.info(TAG + "Query-" + query);

        userList = db.queryForObject(query, new UserListMapper());

        return userList;
    }


    @Override
    public boolean isUserExist(String userId) {
        log.info(TAG + "isUserExist()");

        int result = 0;
        String query = SQLQueryUtil.USER_CHECK_USER;
        log.info(TAG + "Query-" + query);
        try {
            result = db.queryForObject(query, new Object[]{userId}, Integer.class);
            log.info(TAG + "----result :" + result + "");

        } catch (DataAccessException e) {
            log.info(TAG + "----Exception :" + e.getMessage() + "");
        }

        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isMobileExist(String mobile) {
        log.info(TAG + "isMobileExist()");

        int result = 0;
        String query = SQLQueryUtil.USER_CHECK_PHONE;
        Map<String, Object> params = new HashMap();
        params.put("phone", mobile);
        for (var entry : params.entrySet()) {
            query.replace(":" + entry.getKey(), entry.getValue().toString());
        }
        log.info(TAG + "Query-" + query);
        try {
            result = db.queryForObject(query, new Object[]{mobile}, Integer.class);
            log.info(TAG + "----result :" + result + "");

        } catch (DataAccessException e) {
            log.info(TAG + "----Exception :" + e.getMessage() + "");
        }

        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isEmailExist(String email) {
        log.info(TAG + "isEmailExist()");

        int result = 0;
//       String query = "Select Count(*) FROM " + TableUtil.USER.TABLE_NAME + " WHERE email = "+email;
        String query = SQLQueryUtil.USER_CHECK_EMAIL;
        log.info(TAG + "Query-" + query);
        try {
            result = db.queryForObject(query, new Object[]{email}, Integer.class);
            log.info(TAG + "----result :" + result + "");

        } catch (DataAccessException e) {
            log.info(TAG + "----Exception :" + e.getMessage() + "");
        }

        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }
}
