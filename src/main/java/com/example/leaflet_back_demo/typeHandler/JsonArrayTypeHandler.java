package com.example.leaflet_back_demo.typeHandler;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import java.sql.*;
import java.util.Arrays;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 映射类型处理
 * String类型的对象数组
 * 映射为 JsonArray
 * */
public class JsonArrayTypeHandler implements TypeHandler<Object[]> {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void setParameter(PreparedStatement ps, int i, Object[] parameter, JdbcType jdbcType) throws SQLException {
        if (parameter == null) {
            ps.setNull(i, Types.ARRAY);
        } else {
            ps.setString(i, Arrays.toString(parameter));
        }
    }

    @Override
    public Object[] getResult(ResultSet rs, String columnName) throws SQLException {
        String jsonString = rs.getString(columnName);
        return jsonString != null ? toObjectArray(jsonString) : null;
    }

    @Override
    public Object[] getResult(ResultSet rs, int columnIndex) throws SQLException {
        String jsonString = rs.getString(columnIndex);
        return jsonString != null ? toObjectArray(jsonString) : null;
    }

    @Override
    public Object[] getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String jsonString = cs.getString(columnIndex);
        return jsonString != null ? toObjectArray(jsonString) : null;
    }

    private Object[] toObjectArray(String jsonString) {
        try {
            return objectMapper.readValue(jsonString, Object[].class);
        } catch (Exception e) {
            throw new RuntimeException("Error converting JSON string to object array", e);
        }
    }
}