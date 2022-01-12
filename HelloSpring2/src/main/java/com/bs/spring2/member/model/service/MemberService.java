package com.bs.spring2.member.model.service;

import java.util.Map;

import com.bs.spring2.member.model.vo.Member;

public interface MemberService {
	
	Member login(Map param);
	
	int insertMember(Member m);
	

}
