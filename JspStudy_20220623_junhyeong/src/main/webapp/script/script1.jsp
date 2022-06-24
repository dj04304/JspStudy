<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
 
 <!-- 선언문 declaration -->
<%!
	String[] name = new String[5];
	String[] email = new String[5];
	
	String madeName(String name) {
		return name + "님";
	}
	
	String madeEmail(String email) {
		return email + "@google.com";
	}
%>

<!-- 스크립틀릿  -->
<%
	name[0] = "박준형";
	email[0] = "dj043045";
	name[1] = "박준형1";
	email[1] = "dj043046";
	name[2] = "박준형2";
	email[2] = "dj043047";
	name[3] = "박준형3";
	email[3] = "dj043048";
	name[4] = "박준형4";
	email[4] = "dj043049";
%>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>이름</th>
				<th>이메일</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="name" items="${name}" varStatus="status">
				<tr>
					<!-- 표현문  -->
					<td>${status.index + 1 }</td>
					<td>${madeName(name[status.index]) }</td>
					<td>${madeEmail(email[status.index]) }</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>