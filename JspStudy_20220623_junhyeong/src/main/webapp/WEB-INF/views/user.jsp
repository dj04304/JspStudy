<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!-- String name = (String) request.getAttribute("name") setAttribute가 object형식이기 때문에, String 으로 다운캐스팅해서 가져와야한다. -->

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>사용자 정보</h1>
	<label>이름: </label>
	<input type = "text" value ="${name}">
</body>
</html>