package com.cskaoyan.typehandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(Integer[].class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class IntegerArrayAndVarchar implements TypeHandler<Integer[]> {

    ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    @Override
    public void setParameter(PreparedStatement ps, int index, Integer[] parameter, JdbcType jdbcType) throws SQLException {
        //将数组转成json字符串
        String s = objectMapper.writeValueAsString(parameter);
        ps.setString(index,s);
    }

    @Override
    public Integer[] getResult(ResultSet rs, String columnName) throws SQLException {
        String result = rs.getString(columnName);
        return transfer(result);
    }

    @Override
    public Integer[] getResult(ResultSet rs, int columnIndex) throws SQLException {
        String result = rs.getString(columnIndex);
        return transfer(result);
    }

    @Override
    public Integer[] getResult(CallableStatement cs, int columnIndex) throws SQLException {
        String result = cs.getString(columnIndex);
        return transfer(result);
    }

    private Integer[] transfer(String result) {
        if (result == null || "".equals(result)) {
            return new Integer[0];
        }
        Integer[] strings = new Integer[0];
        try {
            strings = objectMapper.readValue(result, Integer[].class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return strings;
    }
}
