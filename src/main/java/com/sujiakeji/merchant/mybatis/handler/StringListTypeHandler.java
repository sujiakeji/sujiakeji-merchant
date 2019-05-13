package com.sujiakeji.merchant.mybatis.handler;

import com.sujiakeji.merchant.constant.CommonConstants;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@MappedTypes(List.class)
public class StringListTypeHandler implements TypeHandler {

    public void setParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType) throws SQLException {
        List<String> sl = (List<String>) parameter;
        if (sl != null) {
            ps.setString(i, StringUtils.join(sl, CommonConstants.COMMA));
        } else {
            ps.setString(i, null);
        }
    }

    public Object getResult(ResultSet rs, String columnName) throws SQLException {
        String s = rs.getString(columnName);
        if (s != null) {
            return Arrays.asList(StringUtils.split(s, CommonConstants.COMMA));
        } else {
            return null;
        }
    }

    public Object getResult(ResultSet rs, int columnIndex) throws SQLException {
        String s = rs.getString(columnIndex);
        if (s != null) {
            return StringUtils.split(s, CommonConstants.COMMA);
        } else {
            return null;
        }
    }

    public Object getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String s = cs.getString(columnIndex);
        if (s != null) {
            return StringUtils.split(s, CommonConstants.COMMA);
        } else {
            return null;
        }
    }

}
