package com.bs.spring2.board.model.service;

import java.util.List;

import javax.management.RuntimeErrorException;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.bs.spring2.board.model.dao.BoardDao;
import com.bs.spring2.board.model.vo.Attachment;
import com.bs.spring2.board.model.vo.Board;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao dao;
	
	@Autowired
	private SqlSessionTemplate session;
	
	@Override
	public List<Board> selectBoardList(int cPage, int numPerpage) {
		return dao.selectBoardList(session, cPage, numPerpage);
	}
	
	@Override
	public int countBoardList() {
		return dao.countBoardList(session);
	}
	
	// 게시글 번호에 맞는 게시글 가져오기(상세보기)
	@Override
	public Board selectBoard(int boardNo) {
		return dao.selectBoard(session, boardNo);
	}
	
	// 게시글 등록
	@Override
//	@Transactional(isolation = Isolation.READ_COMMITTED) // runtime Exception이 생기면 자동으로 rollback 해준다!!!
	public int insertBoard(Board b) throws RuntimeException {
		log.debug("전 boardNo : {}", b.getBoardNo());
		int result = dao.insertBoard(session, b);
		log.debug("후 boardNo : {}", b.getBoardNo());
		if(result > 0 && !b.getFiles().isEmpty()) { // b.getFiles().size() > 0) {
			
			try {
				for(Attachment a : b.getFiles()) {
					a.setBoardNo(b.getBoardNo());
					result = dao.insertAttachment(session, a);
				}
			}catch(RuntimeException e) {
				throw new RuntimeException("기본에러. 등록실패");
			}
			
//			b.getFiles().get(0).setBoardNo(b.getBoardNo());
//			result = dao.insertAttachment(session, b.getFiles().get(0));
//			if -> spring 트렌젝션 매니저가 담당한다.
		}
		return result;
	}
	
}












