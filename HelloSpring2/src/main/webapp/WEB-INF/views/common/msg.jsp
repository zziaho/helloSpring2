<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath }"/>

<jsp:include page="/WEB-INF/views/common/header.jsp" >
   <jsp:param value=" " name="title"/>
</jsp:include>

<script type="text/javascript">
      alert('${msg}');
      location.assign("${path}"+"${loc}");
</script>
   
<jsp:include page="/WEB-INF/views/common/footer.jsp" />