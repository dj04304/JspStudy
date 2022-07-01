package domain.dao;

import domain.entity.User;

//CRUD
public interface UserDao {
	public int save(User user) throws Exception; //user객체를 받아 정보를 db에전달
	public User findUserByUsername(String username) throws Exception; //username으로 user를 찾는 역할
	public int modify(int user_code) throws Exception; //key값으로 수정
	public int remove(int user_code) throws Exception; //key값으로 삭제
	
}
