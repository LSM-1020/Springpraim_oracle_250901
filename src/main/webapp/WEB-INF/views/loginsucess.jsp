<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 성공확인</title>
</head>
<body>
	<h2>로그인 성공${sessionScope.sessionId }님</h2>
	
	<a href= "logout">로그 아웃</a>
	<a href= "bwrite">글쓰기</a>
	<a href= "blist">게시판 리스트 보기</a>
</body>
</html>