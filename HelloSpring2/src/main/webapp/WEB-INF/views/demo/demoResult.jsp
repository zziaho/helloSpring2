<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>

<jsp:include page="/WEB-INF/views/common/header.jsp">
	<jsp:param name="title" value="demoResult"/>
</jsp:include>

<style>
	table#tbl-dev{
		margin:0 auto;
		width:50%;
	}
</style>

<section id="content">
	<table class="table" id="tbl-dev">
		<tr>
			<th scope="col">이름</th>
			<td><c:out value="${demo.devName }"/></td>
		<tr>
		<tr>
			<th>나이</th>
			<td><c:out value="${demo.devAge }"/></td>
		</tr>
		<tr>
			<th>이메일</th>
			<td><c:out value="${demo.devEmail }"/></td>
		</tr>
		<tr>
			<th>성별</th>
			<td><c:out value="${demo.devGender }"/></td>
		</tr>
		<tr>
			<th>사용가능 언어</th>
			<td>
				<c:forEach var="l" items="${demo.devLang }">
					${l }
				</c:forEach>
				
			</td>
		</tr>
	</table>
</section>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>