package com.bsuir.client.service.impl;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bsuir.center.domain.Group;
import com.bsuir.client.exception.ServiceException;
import com.bsuir.client.service.IGroupService;

@Service
public class GroupServiceImpl implements IGroupService {
	private static final Logger logger = Logger.getLogger(GroupServiceImpl.class);
	
	@Autowired
	private Environment environment;

	@Autowired
	private RestTemplate restTemplate;

	public Long create(Group entity) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public Group read(Long entityId) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Group> readAll() throws ServiceException {
		List<Group> persons = Arrays.asList(restTemplate.getForEntity(environment.getProperty("GROUP_REST_SERVICE_URI"), Group[].class).getBody());

		if (persons != null) {
			return persons;
		} else {
			logger.error("Returned from Rest Service list of groups cannot be null.");
			throw new ServiceException("Returned from Rest Service list of groups cannot be null.");
		}
	}

	public void update(Group entity) {
		// TODO Auto-generated method stub

	}

	public void delete(Long entityId) {
		// TODO Auto-generated method stub

	}

}
