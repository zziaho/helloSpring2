package com.bs.spring2.memo.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.bs.spring2.memo.model.vo.Memo;

@Repository
public class MemoDaoImpl implements MemoDao {

	@Override
	public List<Memo> selectMemoList(SqlSessionTemplate session) {
		// TODO Auto-generated method stub
		return session.selectList("memo.selectMemoList");
	}

	@Override
	public int insertMemo(SqlSessionTemplate session, Memo m) {
		return session.insert("memo.insertMemo", m);
	}

}
