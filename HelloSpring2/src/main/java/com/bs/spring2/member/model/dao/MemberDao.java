package com.bs.spring2.member.model.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.bs.spring2.member.model.vo.Member;

public interface MemberDao {

	Member login(SqlSessionTemplate session, Map param);
	
	int insertMember(SqlSessionTemplate session, Member m);
}
