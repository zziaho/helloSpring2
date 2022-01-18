package com.bs.spring2.memo.model.service;

import java.util.List;

import com.bs.spring2.memo.model.vo.Memo;

public interface MemoService {

	List<Memo> selectMemoList();
	
	int insertMemo(Memo m);
	
}
