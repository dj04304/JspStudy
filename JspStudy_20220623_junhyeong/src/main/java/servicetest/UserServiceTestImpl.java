package servicetest;

import webtest.dto.SignupReqTestDto;

public class UserServiceTestImpl implements UserServiceTest{

	@Override
	public boolean createUser(SignupReqTestDto signupReqTestDto) throws Exception{
		System.out.println("회원가입 서비스 실행");
		System.out.println(signupReqTestDto);
		return false;
	}

	@Override
	public void getUser() throws Exception{
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean updateUser() throws Exception{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUser() throws Exception{
		// TODO Auto-generated method stub
		return false;
	}

}
