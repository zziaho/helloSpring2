package com.bs.spring2.demo.model.service;

import java.util.List;

import com.bs.spring2.demo.model.vo.Demo;

public interface DemoService {
	
	public int insertDemo(Demo demo);

	List<Demo> selectDemoList();
}
