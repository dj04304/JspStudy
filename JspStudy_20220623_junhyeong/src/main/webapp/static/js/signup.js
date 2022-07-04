const usernameInput = document.querySelector(".username-input"); // 중복된 아이디일 경우
const usernameCheckMsg = document.querySelector(".username-check-msg"); //회원가입을 할 수 있는 아이디라고 알려주는 역할

let signupFlag = false;

usernameInput.onblur = () => { //onblur 커서가 벗어나는 이벤트
	let username = usernameInput.value;
	$.ajax({
		type: "get",
		url: `/check/username?username=${username}`, //포스트요청에는 data가 필요
		dataType: "text", //응답받을 때의 데이터 형태 나중에는 "JSON"으로 받을 예정
		success: (response) => { //응답을 받았을 때의 메세지
			if(response == "true"){ //dataType이 text형태이기 때문에 "true" 문자열로 해줌
				signupFlag = false; //true는 아이디가 있다는 뜻이기 때문에 회원가입이 불가능(false)하다.
				usernameCheckMsg.innerHTML = `<td colspan="2">${username}은(는) 이미 존재하는 사용자이름 입니다.</td>`;
			}else{
				signupFlag = true;
				usernameCheckMsg.innerHTML = `<td colspan="2">${username}은(는) 가입할 수 있는 사용자이름 입니다.</td>`;
			}
			//alert("요청 후 응답 성공: " + response);
		},
		error: (request, status, error) => { //에러가 발생했을 경우 상태, 메세지, 에러를 띄워주는 역할 
			alert("요청 실패")
			console.log(request.status); //status는 request내의 것을 쓰며, 위에 매개변수를 쓰는 이유는 자리를 채우기 위해서이다.
			console.log(request.reponseText);
			console.log(error);
		}
	}); //ajax요청 변수에 담아서 쓸수도있지만, ajax 내에 {}열어서 바로 써줄 수 있다.
 		
}
	
