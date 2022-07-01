package web.dto;

import domain.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignupReqDto {
	private String name;
	private String email;
	private String username;
	private String password;
	
	public User toEntity() { //dto의 모든 변수의 값을 user클래스로 바꿔서 return해주는 용도이다.
		return User.builder()
				.name(name)
				.email(email)
				.username(username)
				.password(password)
				.roles("ROLE_USER")
				.build();
	}
}
