<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>

<jsp:include page="/WEB-INF/views/common/header.jsp" >
   <jsp:param value=" " name="title"/>
</jsp:include>

	<style>
		div#enroll-container{width:400px; margin:0 auto; text-align:center;}
		div#enroll-container input, div#enroll-container select {margin-bottom:10px;}
	</style>

	<div id="enroll-container">
		<form name="memberEnrollFrm" action="${path }/member/updateMember.do" method="post">
			<input type="text" class="form-control" placeholder="" name="userId" id="userId_" required>
			<input type="password" class="form-control" placeholder="" name="password" id="password_" required>
			<input type="password" class="form-control" placeholder="" id="password2" required>
			<input type="text" class="form-control" placeholder="" name="userName" id="userName" required>
			<input type="number" class="form-control" placeholder="" name="age" id="age">
			<input type="email" class="form-control" placeholder="" name="email" id="email" required>
			<input type="tel" class="form-control" placeholder="" name="phone" id="phone" maxlength="11" required>
			<input type="text" class="form-control" placeholder="" name="address" id="address">
			<select class="form-control" name="gender" required>
				<option value="" disabled selected>성별</option>
				<option value="M">남</option>
				<option value="F">여</option>
			</select>
			<div class="form-check-inline form-check">
				취미 : &nbsp; 
				<input type="checkbox" class="form-check-input" name="hobby" id="hobby0" value="운동"><label for="hobby0" class="form-check-label">운동</label>&nbsp;
				<input type="checkbox" class="form-check-input" name="hobby" id="hobby1" value="등산"><label for="hobby1" class="form-check-label">등산</label>&nbsp;
				<input type="checkbox" class="form-check-input" name="hobby" id="hobby2" value="독서"><label for="hobby2" class="form-check-label">독서</label>&nbsp;
				<input type="checkbox" class="form-check-input" name="hobby" id="hobby3" value="게임"><label for="hobby3" class="form-check-label">게임</label>&nbsp;
				<input type="checkbox" class="form-check-input" name="hobby" id="hobby4" value="여행"><label for="hobby4" class="form-check-label">여행</label>&nbsp;
			</div>
			<br />
			<input type="submit" class="btn btn-outline-success" value="수정" >&nbsp;
			<input type="reset" class="btn btn-outline-success" value="취소">
		</form>
	</div>

   
<jsp:include page="/WEB-INF/views/common/footer.jsp" />   