package com.bs.spring2.demo.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Demo {
	
	private int devNo;
	private String devName;
	private int devAge;
	private String devEmail;
	private String devGender;
//	private String[] devLang;
	private String devLang;
	private Date birthDay;

}
