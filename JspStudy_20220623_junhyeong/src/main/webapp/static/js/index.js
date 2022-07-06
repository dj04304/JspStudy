const signupBtn = document.querySelectorAll("nav ul li");

load();

signupBtn[0].onclick = () => {
	location.href = "/signup";
}

signupBtn[1].onclick = () => {
	location.href = "/signin";
}

signupBtn[3].onclick = () => {
	location.href = "/logout";
	alert("로그아웃 되었습니다.")
}

function load() {
	$.ajax({
		type: "get",
		url: "/api/v1/principal",
		dataType: "json",
		success: (response) => {
			console.log(JSON.stringify(response));
			if(response != null){ //유저 정보가 있다는 뜻.
				loadUserInfo(response); 
			}
		},
		error: () => {
			alert("요청 실패");
		}
	});
}

function loadUserInfo(principal) {
	const userInfo = document.querySelector(".user-info");
	userInfo.innerHTML = `
		<li>${principal.name}</li>
		<li>${principal.username}</li>
		<li>${principal.email}</li>
	`;
}