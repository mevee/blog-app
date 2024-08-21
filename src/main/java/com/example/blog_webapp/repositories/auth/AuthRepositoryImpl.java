package com.example.blog_webapp.repositories.auth;

import com.example.blog_webapp.dataMapper.opt.OtpMapper;
import com.example.blog_webapp.entity.OtpEntity;
import com.example.blog_webapp.repositories.user.UserRepository;
import com.example.blog_webapp.utils.SQLQueryUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class AuthRepositoryImpl implements AuthRepository {
    private String TAG = this.getClass().getSimpleName() + " ";
    private static final Logger log = LoggerFactory.getLogger(AuthRepositoryImpl.class);
    @Autowired
    JdbcTemplate db;
    private static final long OTP_VALID_DURATION = 2 * 60 * 1000; // 5 minutes
    @Autowired
    UserRepository userRepository;

    @Override
    public boolean checkOtpExist(String userId) throws Exception {
        log.info(TAG + "checkOtpExist() userId:" + userId);
        int result = 0;
        String query = SQLQueryUtil.AUTH_OTP_EXIST;
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
    public OtpEntity getDbOtp(String userId) throws Exception {
        OtpEntity count = null;
        String query = SQLQueryUtil.AUTH_OTP_GET_OTP;
        log.info(TAG + "Query-" + query);
        try {
            count = db.queryForObject(query, new Object[]{userId}, new OtpMapper());
            log.info(TAG + "----result :" + count + "");
        } catch (DataAccessException e) {
            log.info(TAG + "----Exception :" + e.getMessage() + "");
        }
        return count;
    }

    @Override
    public int getLastAttemptCount(String userId) {
        int count = 0;
        String query = SQLQueryUtil.AUTH_OTP_ATTEMPT;
        log.info(TAG + "Query-" + query);
        try {
            count = db.queryForObject(query, new Object[]{userId}, Integer.class);
            log.info(TAG + "----result :" + count + "");
        } catch (DataAccessException e) {
            log.info(TAG + "----Exception :" + e.getMessage() + "");
        }

        return count;
    }

    @Override
    public boolean checkTokenExist(String userId) throws Exception {
        log.info(TAG + "checkTokenExist() userId:" + userId);
        int result = 0;
//        String query = SQLQueryUtil.AUTH_OTP_EXIST;
//        log.info(TAG + "Query-" + query);
//        try {
//            result = db.queryForObject(query, new Object[]{userId}, Integer.class);
//            log.info(TAG + "----result :" + result + "");
//
//        } catch (DataAccessException e) {
//            log.info(TAG + "----Exception :" + e.getMessage() + "");
//        }

        if (result > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String generateToken(String userId) throws Exception {
        return "";
    }

    @Override
    public Boolean generateOtp(String userId) throws Exception {
        log.info(TAG + "generateOtp()");

        int result = 0;
        if (!userRepository.isUserExist(userId)) {
            log.info(TAG + "user not exist");

            throw new Exception("No user exist with given id");
        } else if (!checkOtpExist(userId)) {
            log.info(TAG + "Otp entry not present in db");

            String query = SQLQueryUtil.AUTH_OTP_CREATE;
            log.info(TAG + "query: " + query);

            String otp = generateOTP();
            result = db.update(query, otp, userId, 0);
            log.info(TAG + "result:" + result);
        } else {
            log.info(TAG + "Otp exist in db");

            String query = SQLQueryUtil.AUTH_OTP_UPDATE;
            log.info(TAG + "query: " + query);

            String otp = generateOTP();
            int attemptCount = 0;
            try {
                log.info(TAG + "otp:" + otp + " , attemptCount:" + attemptCount + ", userId:" + userId);

                result = db.update(query, new Object[]{otp, attemptCount, userId});
                log.info(TAG + "result:" + result);
            } catch (DataAccessException e) {
                result = 0;
                log.info(TAG + "Exception" + e.getMessage());

            }
        }
        return result > 0;
    }

    @Override
    public Boolean verifyOtp(String userId, String otp, int retryCount) throws Exception {
        int result = 0;
        OtpEntity dbOtp = getDbOtp(userId);
        log.info(TAG + "dbOtp:" + dbOtp);

        if (dbOtp != null) {
            long currentTime = Calendar.getInstance().getTimeInMillis();
            Date date = new Date(dbOtp.updatedDate.getTime());
            Date currentDate = new Date(System.currentTimeMillis());
            SimpleDateFormat sdfUTC = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            sdfUTC.setTimeZone(TimeZone.getTimeZone("UTC"));

            long otpTime = dbOtp.updatedDate.getTime();
            long difference = currentTime - otpTime;

            log.info(TAG + "dbDate:" + sdfUTC.format(date));
            log.info(TAG + "currentDate:" + sdfUTC.format(currentDate));

            log.info(TAG + "difference:" + difference);
            log.info(TAG + "against check :" + OTP_VALID_DURATION);


            if (dbOtp.isVerified) {
                throw new Exception("OTP already verified.");
            } else if (dbOtp.lastAttempt > 3) {
                throw new Exception("You have exhausted your limit.You can retry in an hour.");
            } else if (difference > OTP_VALID_DURATION) {
                throw new Exception("Otp expired. Please send new OTP to verify.");
            } else if (!dbOtp.otpCode.equals(otp)) {
                String query = SQLQueryUtil.AUTH_ATTEMPT;
                log.info(TAG + "query:" + query);
                int attemptCount = dbOtp.lastAttempt + 1;
                result = db.update(query, attemptCount,dbOtp.id);
                throw new Exception("Otp do not matched.");
            } else {
                String query = SQLQueryUtil.AUTH_OTP_VERIFY;
                result = db.update(query, true,0,dbOtp.id);
                userRepository.verifyUser(userId);
            }
        } else {
            throw new Exception("OTP not exist for this user");
        }
        return result > 0;
    }

    @Override
    public Boolean validateToken(String token, String userId) throws Exception {
        return null;
    }

    private String generateOTP() {
        return getOtp(6);
    }

    private String getOtp(int length) {
        String numbers = "0123456789";
        Random rndm_method = new Random();
        char[] otp = new char[length];
        for (int i = 0; i < length; i++) {
            otp[i] = numbers.charAt(rndm_method.nextInt(numbers.length()));
        }
        return new String(otp);
    }
}
