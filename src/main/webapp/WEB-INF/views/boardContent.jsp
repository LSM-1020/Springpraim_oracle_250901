<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 내용 보기</title>
</head>
<body>
	<h2>글 내용 보기 및 수정</h2>
	
	<form action="boardmodify">
		제목: <input type="text" name="btitle" value="${boardDto.btitle }"><br>
		내용: <textarea rows="10" cols="45" name="bcontent">${boardDto.bcontent }</textarea><br>
		글쓴이: <input type="text" name="membername" value="${boardDto.memberDto.membername }" readonly="readonly"><br>
		조회수: ${boardDto.bhit}<br>
		등록일: ${boardDto.bdate }<br>
		<input type="hidden" name="bnum" value="${boardDto.bnum }">
		<input type="submit" value="수정">
	</form>
</body>
</html>