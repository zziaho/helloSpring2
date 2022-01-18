<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>

<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value="헬로 스프링"/>
</jsp:include>

<section id="contet">
	<h3><a href="javascript:fn_chatting()">채팅하기</a></h3>
</section>
<script>
	const fn_chatting=()=>{
		open("${path}/chatting/connectChatting", "_blank", "width=300, height=500");
	}
</script>
		
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
		











