package com.bsuir.center.controller;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bsuir.center.dao.IGroupDao;
import com.bsuir.center.domain.Group;
import com.bsuir.center.exception.DaoException;

@RestController
@RequestMapping("v1/music/group")
public class GroupHandler {
	private static final Logger logger = Logger.getLogger(GroupHandler.class);

	@Autowired
	private IGroupDao groupDao;

	@RequestMapping(value = "/{groupId}", method = GET)
	public Group read(@PathVariable Integer groupId) {
		Group group = null;

		try {
			group = groupDao.read(groupId);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return group;
	}

	@RequestMapping(method = GET)
	public List<Group> readAllGroups() {
		List<Group> groups = null;

		try {
			groups = groupDao.readAll();
		} catch (DaoException e) {
			logger.error("Can't read all users due to DaoException.");
		}
		return groups;
	}

	@RequestMapping(value = "/{id}", method = DELETE)
	public ResponseEntity<String> deleteGroup(@PathVariable Integer id) {
		try {
			groupDao.delete(id);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = POST)
	public ResponseEntity<String> updateGroup(@RequestBody Group group) {
		try {
			groupDao.update(group);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@RequestMapping(method = POST)
	public Integer createGroup(@RequestBody Group group) {
		Integer id = null;
		try {
			id = groupDao.create(group);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id;
	}

}
