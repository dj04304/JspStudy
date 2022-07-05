package service;

import domain.dao.UserDao;
import domain.dao.UserDaoImpl;
import domain.entity.User;
import web.dto.SignupReqDto;

public class UserServiceImpl implements UserService{

	private final UserDao userDao;
	
	public UserServiceImpl() {
		userDao = new UserDaoImpl();
	}
	
	@Override
	public boolean checkUsername(String username) throws Exception {
		return userDao.findUserByUsername(username) != null; //null 또는 user의 정보가 findUserByUsername의 검사를 통해 날아온다
	}
	
	@Override
	public boolean createUser(SignupReqDto signupReqDto) throws Exception {
		return userDao.save(signupReqDto.toEntity()) > 0; //생성한 builder를 가져오고 save의 return자료형이 int이기 때문에 return하여 0보다 작으면 false가 되게끔 한다.
	}

	@Override
	public void getUser() throws Exception {
		
	}

	@Override
	public boolean updateUser() throws Exception {
		return false;
	}

	@Override
	public boolean deleteUser() throws Exception {
		return false;
	}

	@Override
	public User loadUser(String username, String password) throws Exception {
		User user = userDao.findUserByUsername(username);
		if(user == null) {
			return null;
		}else {
			return user.getPassword().equals(password) ? user : null; //password가 내가보낸 password랑 일치할 경우 user return 
		}
	}
	
}
