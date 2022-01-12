package com.bs.spring2.member.model.service;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.spring2.member.model.dao.MemberDao;
import com.bs.spring2.member.model.vo.Member;

@Service
public class MemberServiceImpl implements MemberService {
	
	// MemberSerivceImpl이 의존하는 2가지 등록
	@Autowired
	private SqlSessionTemplate session;
	
	@Autowired
	private MemberDao dao;

	@Override
	public Member login(Map param) {
		return dao.login(session, param);
	}
	
	@Override
	public int insertMember(Member m) {
		return dao.insertMember(session, m);
	}

}
