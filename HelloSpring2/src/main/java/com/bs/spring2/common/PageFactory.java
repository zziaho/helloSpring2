package com.bs.spring2.common;

public class PageFactory {

	// 페이징 처리하기~~
	public static String getPageBar(int totalData, int cPage, int numPerpage, int pageBarSize, String url) {
		// int totalData
		// int totalPage
		// int pageBarSize
		// int pageNo
		// int pageEnd
		// String pageBar
		
		String pageBar = "";
		
		int totalPage = (int)Math.ceil((double)totalData / numPerpage);
		int pageNo = ((cPage - 1) / pageBarSize) * pageBarSize + 1;
		int pageEnd = pageNo + pageBarSize - 1;
		
		pageBar += "<ul class='pagination justify-content-center pagination-sm'>";
		if(pageNo == 1) {
			pageBar += "<li class='page-item disabled'>";
	        pageBar += "<a class='page-link' href='#' tabindex='-1'>이전</a>";
	        pageBar += "</li>";
		} else {
	        pageBar += "<li class='page-item'>";
	        pageBar += "<a class='page-link' href='javascript:fn_paging("+(pageNo-1)+")'>이전</a>";
	        pageBar += "</li>";

		}
		
		while(!(pageNo > pageEnd || pageNo > totalPage)) {
			if(cPage == pageNo) {
	           pageBar += "<li class='page-item active'>";
	           pageBar += "<a class='page-link' href='#'>"+pageNo+"</a>";
	           pageBar += "</li>";
			}else {
	           pageBar += "<li class='page-item'>";
	           pageBar += "<a class='page-link' href='javascript:fn_paging("+(pageNo)+")'>"+pageNo+"</a>";
	           pageBar += "</li>";
			}
			pageNo++;
		}
		
		if(pageNo > totalPage) {
	        pageBar += "<li class='page-item'>";
	        pageBar += "<a class='page-link' href='#'>다음</a>";
	        pageBar += "</li>";
		}else {
	        pageBar += "<li class='page-item'>";
	        pageBar += "<a class='page-link' href='javascript:fn_paging("+(pageNo)+")'>다음</a>";
	        pageBar += "</li>";
		}
		pageBar += "</ul>";
		pageBar += "<script>";
		pageBar += "function fn_paging(cPage){";
		pageBar += "location.assign('" + url + "?cPage='+cPage);";
		pageBar += "}";
		pageBar += "</script>";
		
		return pageBar;
	}
	
}
























