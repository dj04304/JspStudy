package servicetest;

import webtest.dto.SignupReqTestDto;

public interface UserServiceTest {
	public boolean createUser(SignupReqTestDto signupReqTestDto) throws Exception;
	public void getUser() throws Exception;
	public boolean updateUser() throws Exception;
	public boolean deleteUser() throws Exception;
}
