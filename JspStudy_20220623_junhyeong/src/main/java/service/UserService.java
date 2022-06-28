package service;

import web.dto.SignupReqDto;

//CRUD 는 꼭 가져야 한다.
public interface UserService {
	public boolean createUser(SignupReqDto signupReqDto) throws Exception;
	public void getUser()  throws Exception;
	public boolean updateUser()  throws Exception;
	public boolean deleteUser()  throws Exception;
	
}
