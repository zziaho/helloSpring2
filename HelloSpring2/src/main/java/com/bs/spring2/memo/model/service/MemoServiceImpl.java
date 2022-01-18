package com.bs.spring2.memo.model.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.spring2.memo.model.dao.MemoDao;
import com.bs.spring2.memo.model.vo.Memo;

@Service("memoservice") // id값을 준거임
public class MemoServiceImpl implements MemoService {
	
	@Autowired
	private MemoDao dao;
	
	@Autowired
	private SqlSessionTemplate session;

	@Override
	public List<Memo> selectMemoList() {
		return dao.selectMemoList(session);
	}

	@Override
	public int insertMemo(Memo m) {
		return dao.insertMemo(session, m);
	}

	
}
