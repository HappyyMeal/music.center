package com.bsuir.center.controller;

import static org.springframework.web.bind.annotation.RequestMethod.*;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bsuir.center.dao.IUserDao;
import com.bsuir.center.domain.User;
import com.bsuir.center.exception.DaoException;

@RestController
@RequestMapping("v1/music/user")
public class UserHandler {
	private static final Logger logger = Logger.getLogger(UserHandler.class);

	@Autowired
	private IUserDao userDao;

	@RequestMapping(method = POST)
	public String createUser(@RequestBody User user) {
		String createdLogin = null;

		try {
			if (user != null)
				createdLogin = userDao.create(user);
		} catch (DaoException e) {
			logger.error("Can't create user due to DaoException.");
		}

		return createdLogin;
	}

	@RequestMapping(value = "/{username}", method = GET)
	public User readUser(@PathVariable String username) {

		User user = null;

		try {
			if (username != null)
				user = userDao.read(username);
		} catch (DaoException e) {
			logger.error("Can't read user due to DaoException.");
		}

		return user;
	}

	@RequestMapping(method = GET)
	public List<User> readAllUsers() {
		List<User> users = null;

		try {
			users = userDao.readAll();
		} catch (DaoException e) {
			logger.error("Can't read all users due to DaoException.");
		}

		return users;

	}

	@RequestMapping(method = PUT)
	public void update(@RequestBody User user) {

		try {
			if (user != null)
				userDao.update(user);
		} catch (DaoException e) {
			logger.error("Can't update user due to DaoException.");
		}
	}

	@RequestMapping(value = "/{username}", method = DELETE)
	public void delete(@PathVariable String username) {
		try {
			if (username != null)
				userDao.delete(username);
		} catch (DaoException e) {
			logger.error("Can't delete user due to DaoException.");
		}
	}
}
