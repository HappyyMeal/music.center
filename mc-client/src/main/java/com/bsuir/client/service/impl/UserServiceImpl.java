package com.bsuir.client.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bsuir.center.domain.User;
import com.bsuir.client.exception.ServiceException;
import com.bsuir.client.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	private static final Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Autowired
	private Environment environment;

	@Autowired
	private RestTemplate restTemplate;

	public String create(User user) throws ServiceException {
		String username = null;

		if (user != null) {
			username = restTemplate.postForObject(environment.getProperty("USER_REST_SERVICE_URI"), user, String.class);
		} else {
			logger.error(environment.getProperty("USER_IS_EMPTY"));
			throw new ServiceException(environment.getProperty("USER_IS_EMPTY"));
		}

		return username;
	}

	public User read(String username) throws ServiceException {
		User user = null;

		if (username != null) {
			if (restTemplate != null && environment != null)
				user = restTemplate.getForObject(environment.getProperty("USER_REST_SERVICE_URI") + "/" + username,
						User.class);

		} else {
			logger.error(environment.getProperty("USERNAME_IS_EMPTY"));
			throw new ServiceException(environment.getProperty("USERNAME_IS_EMPTY"));
		}

		return user;
	}

	public List<User> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public void update(User entity) {
		// TODO Auto-generated method stub

	}

	public void delete(String entityId) {
		// TODO Auto-generated method stub

	}

	public boolean isMatchable(String uiPassword, String dbPassword) {
		if (uiPassword != null)
			return uiPassword.equals(dbPassword);

		return false;
	}

}
