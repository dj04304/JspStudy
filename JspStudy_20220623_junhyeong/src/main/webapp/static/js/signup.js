const usernameInput = document.querySelector(".username-input"); // 중복된 아이디일 경우
const usernameCheckMsg = document.querySelector(".username-check-msg"); //회원가입을 할 수 있는 아이디라고 알려주는 역할
const inputItems = document.querySelectorAll("table input"); //테이블 내의 모든 input들을 가져온다.
const submitButton = document.querySelector(".submit-button"); // 서브밋 버튼


let signupFlag = [false, false, false, false, false]; //name email username password checkUsername 모두 true여야 submit요청이 가능하게끔 한다.

submitButton.onclick = () => {
	for(let i = 0; i < inputItems.length; i++){
		if(isEmpty(inputItems[i].value)){ //input의 내용이 비어있다면 반복을 돌면서 alert을 띄워준다.
			alert((i == 0 ? "이름을" 
				: i == 1 ? "이메일을" 
				: i == 2 ? "사용자 이름을"
				: "비밀번호를")
 				+ " 입력해 주세요.");
 				
 			signupFlag[i] = false; //비어있기 때문에 false를 return해줘야한다.
 			
 			return;
		} 
		signupFlag[i] = true; //비어있지않으면 true를 반복해서 넣어줌으로 입력이 완료됐다고 해줘야한다.
		
	}
	if(signupFlag[4] == false){ // checkUsername 이 false면 중복체크를 유도해야한다.
		alert("사용자이름 중복확인이 필요합니다.");
		return;
	}
	
	if(!signupFlag.includes(false)){ //signupFlag에 flase를 포함하고 있지 않다면,
		submit();
	}
	
}

usernameInput.onblur = () => { //onblur 커서가 벗어나는 이벤트
	let username = usernameInput.value;
	
	$.ajax({
		type: "get",
		url: `/check/username?username=${username}`, //포스트요청에는 data가 필요
		dataType: "text", //응답받을 때의 데이터 형태 나중에는 "JSON"으로 받을 예정
		success: (response) => { //응답을 받았을 때의 메세지
			if(response == "true"){ //dataType이 text형태이기 때문에 "true" 문자열로 해줌
				signupFlag[4] = false; //true는 아이디가 있다는 뜻이기 때문에 회원가입이 불가능(false)하다.
				usernameCheckMsg.innerHTML = `<td colspan="2">${username}은(는) 이미 존재하는 사용자이름 입니다.</td>`;
			}else{
				signupFlag[4] = true;
				usernameCheckMsg.innerHTML = `<td colspan="2">${username}은(는) 가입할 수 있는 사용자이름 입니다.</td>`;
			}
			//alert("요청 후 응답 성공: " + response);
		},
		error: errorMessage
	}); //ajax요청 변수에 담아서 쓸수도있지만, ajax 내에 {}열어서 바로 써줄 수 있다.
 		
}

//submit ajax post요청
function submit(){ //post요청을 ajax로 한다.
	$.ajax({
		type: "post",
		url: "/signup",
		data: { //body 로 보내기 때문에 쿼리가 아닌 data로 보내준다.
			name: inputItems[0].value,
			email: inputItems[1].value,
			username: inputItems[2].value,
			password: inputItems[3].value
		},
		dataType: "text",
		success: (response) => {
			if(response == "true"){ //out.print(true) 응답이 날라왔을 때 alert를 띄운 후 signin 페이지로 넘어감
				alert("축하합니다!\n회원가입에 성공하셨습니다.");
				location.replace("/signin"); //회원가입 이후에는 뒤로가기 버튼을 활성화시키면 안된다.
			}else{
				alert("회원가입에 실패하였습니다. \n다시 시도해주세요.");
				usernameCheckMsg.innerHTML = ``;
				document.querySelector("form").reset(); // form에서 입력한 모든 값을 reset해줌
			}
		},
		error: errorMessage
	});
}

//ajax errorMessage 
function errorMessage(request, status, error) {
	alert("요청 실패")
	console.log(request.status); //status는 request내의 것을 쓰며, 위에 status매개변수를 쓰는 이유는 자리를 채우기 위해서이다.
	console.log(request.reponseText);
	console.log(error);
}	
	
//input isEmpty
function isEmpty(str) { 
	return str == "" || str == null || typeof str == undefined; //하나라도 참이면 비어있다는 뜻이다.
}
	
