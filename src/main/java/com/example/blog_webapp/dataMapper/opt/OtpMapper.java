package com.example.blog_webapp.dataMapper.opt;

import com.example.blog_webapp.entity.OtpEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

public class OtpMapper implements RowMapper<OtpEntity> {
    @Override
    public OtpEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
        OtpEntity model = new OtpEntity();
        do {
            model.id = rs.getInt("id");
            model.lastAttempt = rs.getInt("lastAttempt");
            model.otpCode = rs.getString("otpCode");
            model.uid = String.valueOf(rs.getInt("uid"));
            model.isVerified = rs.getBoolean("isVerified");
            model.updatedDate = rs.getTimestamp("updatedDate");
        }while (rs.next());

        return model;
    }
}
