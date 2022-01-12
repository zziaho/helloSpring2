package com.bs.spring2.member.model.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.bs.spring2.member.model.vo.Member;

@Repository
public class MemberDaoImpl implements MemberDao {

	@Override
	public Member login(SqlSessionTemplate session, Map param) {
		return session.selectOne("member.loginMember", param);
	}
	
	@Override
	public int insertMember(SqlSessionTemplate session, Member m) {
		return session.insert("member.insertMember", m);
	}

}
