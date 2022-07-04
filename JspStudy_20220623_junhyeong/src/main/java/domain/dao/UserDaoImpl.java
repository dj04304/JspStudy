package domain.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.jar.Attributes.Name;

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
		User user = null;
		
		sql = "SELECT\r\n"
				+ "	um.user_code,\r\n"
				+ "	um.name,\r\n"
				+ "	um.email,\r\n"
				+ "	um.username,\r\n"
				+ "	um.password,\r\n"
				+ "	um.roles,\r\n"
				+ "	um.provider,\r\n"
				+ "	ud.user_profile_img,\r\n"
				+ "	ud.user_address,\r\n"
				+ "	ud.user_phone,\r\n"
				+ "	ud.user_gender\r\n"
				+ "FROM\r\n"
				+ "	user_mst um\r\n"
				+ "	LEFT OUTER JOIN user_dtl ud ON(ud.user_code = um.user_code)\r\n"
				+ "WHERE\r\n"
				+ "	um.username = ?";
		
		con = pool.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			rs = pstmt.executeQuery(); //select는 무조건 rs에 executeQuery 를 담아줘야한다. create update delete는 executeUpdate(return int)이다.
			
			if(rs.next()) { //iterator에서 hasnext와 비슷한 느낌으로, 커서를 옮겨준다
				user = User.builder()
						.user_code(rs.getInt(1)) //쿼리문을 작성하여 pstmt.executeQuery 를 rs에 담아줬기 때문에 rs.get하여 가져온다.
						.name(rs.getString(2)) //괄호안의 숫자는 column번호이다.
						.email(rs.getString(3))
						.username(rs.getString(4))
						.password(rs.getString(5))
						.roles(rs.getString(6))
						.provider(rs.getString(7))
						.user_profile_img(rs.getString(8))
						.user_address(rs.getString(9))
						.user_phone(rs.getString(10))
						.user_gender(rs.getInt(11))
						.build();
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con, pstmt, rs);
		}
		return user;
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
