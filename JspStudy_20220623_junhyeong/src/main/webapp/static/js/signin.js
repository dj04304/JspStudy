const inputItems = document.querySelectorAll("table input");
const submitButton = document.querySelector(".submit-button");
const signupButton = document.querySelector(".signup-button");
const maintainFlag = document.querySelector("#maintain-flag"); //로그인을 시도했을 때, 체크박스의 값이 들어있는지 아닌지 중요

signupButton.onclick = () => {
	location.href = "/signup"; //회원가입으로 안내해줌
}

submitButton.onclick = () => {
	for(let i = 0; i < inputItems.length; i++){
		if(isEmpty(inputItems[i].value)){
			alert(
				(i == 0 ? "사용자 이름을" : "비밀번호를") + " 입력해주세요."
			);
			return;
		}
	}
	//for문이 돌아도 return이 되지않은것은 비어있지 않다는 뜻이다.
	submit(); //비어있지 않으면 호출
}

function submit(){
	$.ajax({
		type: "post",
		url: "/signin",
		data: {
			username: inputItems[0].value,
			password: inputItems[1].value,
			maintain: maintainFlag.checked 
		},
		dataType: "text",
		success: (response) => {
			if(response == "true"){
				alert("로그인 성공! 환영합니다.");
				location.replace("/index");
			}else {
				alert("사용자 정보를 확인해주세요.");
			}
		},
		error: errorMessage
	});
}


function errorMessage(request, status, error) {
	alert("요청 실패")
	console.log(request.status); //status는 request내의 것을 쓰며, 위에 status매개변수를 쓰는 이유는 자리를 채우기 위해서이다.
	console.log(request.reponseText);
	console.log(error);
}	

function isEmpty(str) { //빈 값 추출
	return str == "" || str == null || typeof str == undefined;
}