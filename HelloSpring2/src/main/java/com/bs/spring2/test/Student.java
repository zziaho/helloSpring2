package com.bs.spring2.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
// 클래스를 빈으로 등록하는 머노테이션
// @Component, @Controller(servlet), @Service(service), @Repository(dao)
@Component(value = "stu") 
public class Student implements SmartLifecycle {

	private int studentNo;
	private String name;
	private int grade;
	@Autowired
	private Department dept;
	
	@Override
	public void start() {
		System.out.println("생성했다.");
	}
	
	@Override
	public void stop() {
		
	}

	// alt + shift + s + v
	@Override
	public boolean isRunning() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
