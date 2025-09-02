<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 글목록</title>
</head>
<body>
	<table border="1" cellspacint="0" cellpadding="0">
		<tr>
			<td>번호</td>
			<td>제목</td>
			<td>아이디</td>
			<td>글쓴이</td>
			<td>조회수</td>
			<td>등록일</td>
			<td>삭제</td>
		</tr>
		<c:forEach items="${boardList}" var="board" varStatus="status" >
		<tr>
			<td>${boardCount - status.index}</td>
			<td>
			<a href="contentview?bnum=${board.bnum}">${board.btitle}</a></td>
			<td>${board.bwriter}</td>
			<td>${board.memberDto.membername}</td>
			<td>${board.bhit}</td>
			<td>				
				<fmt:formatDate value="${board.bdate }" pattern="yyyy-MM-dd HH:mm" />		
			</td>
			<td>
				<input type="button" value="삭제" onclick="javascript:window.location.href='boarddelete?bnum=${board.bnum}'">
										
			</td>
		</tr>
		
		</c:forEach>
		
	</table>
		<input type="button" value="글쓰기" onclick="javascript:window.location.href='bwrite'">
</body>
</html>