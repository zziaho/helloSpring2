package com.bs.spring2.board.model.vo;

import java.sql.Date;
import java.util.List;

import com.bs.spring2.member.model.vo.Member;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Board {
	
	private int boardNo;
	private String boardTitle;
	private Member boardWriter; // 객체지향적 관점으로 보면 이게 맞음
	private String boardContent;
	private Date boardDate;
	private int boardReadCount;
	private List<Attachment> files;
	
}
