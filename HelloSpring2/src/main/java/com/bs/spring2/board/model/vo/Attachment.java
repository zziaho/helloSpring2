package com.bs.spring2.board.model.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Attachment {
	
	private int attachmentNo;
//	private int boardNo; DB의 관계때문에 있는거지 여기서는 필요없어.
	private String originalFilename;
	private String renamedFilename;
	private Date uploadDate;
	private int downloadCount;
//	private String status;
	
}
