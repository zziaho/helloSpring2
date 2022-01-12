package com.bs.spring2.common;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

// mybatis에서 지정되지 않은 타입을 선언할 때 사용
// Mybatis에서
public class StringArrayTypeHandler implements TypeHandler<String[]> {

	@Override
	public void setParameter(PreparedStatement ps, int i, String[] parameter, JdbcType jdbcType) throws SQLException {
		// TODO Auto-generated method stub
		if(parameter != null) {
			ps.setString(i, String.join(",", parameter));
		}else {
			ps.setString(i, "");
		}
		
	}

	@Override
	public String[] getResult(ResultSet rs, String columnName) throws SQLException {
		// TODO Auto-generated method stub
		String temp = rs.getString(columnName);
		return temp.split(",");
	}

	@Override
	public String[] getResult(ResultSet rs, int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		return rs.getString(columnIndex).split(",");
	}

	@Override
	public String[] getResult(CallableStatement cs, int columnIndex) throws SQLException {
		// TODO Auto-generated method stub
		return cs.getString(columnIndex).split(",");
	}

	
	
}
