<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글쓰기</title>
</head>
<body>
<h2>게시판 글쓰기</h2>
<!-- 로그인하지 않은 경우 login페이지로 강제이동 -->
<c:if test="${empty sessionScope.sessionId }">
	<c:redirect url="login"/>
</c:if>



<form action="bwriteOk" >
	글제 목:<input type="text" name="btitle" ><br><br>
	글 내용:<textarea rows="10" cols="45" name="bcontent"></textarea><br><br>
	글쓴이:<input type="text" value="${sessionScope.sessionId }" name="bwriter" readonly><br><br>
	<input type="submit" value="작성완료">
	

</form>
</body>
</html>