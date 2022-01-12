package com.bs.spring2.demo.model.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.spring2.demo.model.dao.DemoDao;
import com.bs.spring2.demo.model.vo.Demo;

@Service
public class DemoServiceImpl implements DemoService {
	
	@Autowired
	private DemoDao dao;
	
	@Autowired
	private SqlSessionTemplate session;
	

	@Override
	public int insertDemo(Demo demo) {
		int result = dao.insertDemo(session, demo);
		
		return result;
	}
	
	@Override
	public List<Demo> selectDemoList() {
		return dao.selectDemoList(session);
	}
	
}
