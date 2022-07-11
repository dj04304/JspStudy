package config;

import javax.servlet.annotation.WebFilter;

import domain.dao.UserDao;
import domain.dao.UserDaoImpl;
import lombok.Getter;
import service.UserService;
import service.UserServiceImpl;


@Getter
public class ServletContextConfig {
	private static ServletContextConfig instance = null;
	
	/*
	 * Custom IoC (객체관리)
	 * 
	 * 매번 implement하는 것이 아닌 여기서 모아놓고 한번만 가져다 쓰는 용도
	 */
	
	//Repository
	private UserDao userDao;
	
	//service
	private UserService userService;
	
	
	
	private ServletContextConfig() {}
	
	public static ServletContextConfig getInstance() {
		if(instance == null) {
			instance = new ServletContextConfig();
			instance.setIoC();
		}
				
		return instance;
	}

	private void setIoC() {
	
		if(userDao == null) {
			userDao = new UserDaoImpl();
		}
		
		if(userService == null) {
			userService = new UserServiceImpl();
		}
		
	}
	
}


