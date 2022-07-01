package domain.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.entity.User;
import domain.jdbc.DBConnectionMgr;

public class UserDaoImpl implements UserDao{
	private DBConnectionMgr pool;
	
	private String sql;
	
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public UserDaoImpl() {
		pool = DBConnectionMgr.getInstance(); //singletone
	}

	@Override
	public int save(User user) throws Exception { //insert
		int result = 0;
		
		sql = "INSERT INTO\r\n"
				+ "	user_mst\r\n"
				+ "VALUES(\r\n"
				+ "	0,\r\n"
				+ "	?,\r\n"
				+ "	?,\r\n"
				+ "	?,\r\n"
				+ "	?,\r\n"
				+ "	?,\r\n"
				+ "	?,\r\n"
				+ "	NOW(),\r\n"
				+ "	NOW()\r\n"
				+ ");";
		con = pool.getConnection(); //db와 연결을 해줌
		try { //sqlException 문제가 생기면 
			pstmt = con.prepareStatement(sql); //prepareStatement에 sql을 넣어준다.
			pstmt.setString(1, user.getName()); //setString 을 하면 작은따옴표 '' 를 같이 넣어주기 때문에, sql문에서 ''를 빼준다.
			pstmt.setString(2, user.getEmail());
			pstmt.setString(3, user.getUsername());
			pstmt.setString(4, user.getPassword());
			pstmt.setString(5, user.getRoles());
			pstmt.setString(6, user.getProvider());
			result = pstmt.executeUpdate(); //성공한 횟수를 return해준다.
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt); //연결을 했으면 끊어줘야 하기 때문에 freeConnection으로 끊어준다. 이후에 return이 됨	
		}
		
		return result;
	}

	@Override
	public User findUserByUsername(String username) throws Exception {
		return null;
	}

	@Override
	public int modify(int user_code) throws Exception {
		return 0;
	}

	@Override
	public int remove(int user_code) throws Exception {
		return 0;
	}

}
