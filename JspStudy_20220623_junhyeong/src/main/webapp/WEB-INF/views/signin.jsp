<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
<script type="text/javascript" src="https://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>
	<div id = "container">
		<h1>로그인</h1>
		<!-- action -> 요청 메시지를 입력하는 곳 -->
		<!-- 로그인은 post요청이다. 이는 url(주소창)에 파라미터값이 다 뜨기 때문에 post요청을 해줘야한다. 즉, 보안이 필요한 정보는 post요청이라고 생각하면 된다.-->
		<form action="/signin" method="post">
			<table>
				<tr>
					<th>사용자 이름</th>
					<td><input type = "text" class ="username-input" name = "username"></td>
				</tr>
				<tr>
					<th>비밀번호</th>
					<td><input type= "password" name= "password"></td>
				</tr>
			</table>
			<button type= "button" class ="submit-button">로그인</button>
			<button type= "button" class="signup-button">회원가입</button>
		</form>
	</div>
	<script type="text/javascript" src="/static/js/signin.js"></script>
</body>
</html>