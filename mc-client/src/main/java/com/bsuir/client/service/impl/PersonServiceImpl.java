package com.bsuir.client.service.impl;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bsuir.center.domain.Person;
import com.bsuir.client.exception.ServiceException;
import com.bsuir.client.service.IPersonService;

@Service
public class PersonServiceImpl implements IPersonService {
	private static final Logger logger = Logger.getLogger(PersonServiceImpl.class);

	@Autowired
	private Environment environment;

	@Autowired
	private RestTemplate restTemplate;

	public Long create(Person entity) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public Person read(Long entityId) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Person> readAll() throws ServiceException {
		List<Person> persons = Arrays.asList(restTemplate.getForEntity(environment.getProperty("PERSON_REST_SERVICE_URI"), Person[].class).getBody());

		if (persons != null) {
			return persons;
		} else {
			logger.error("Returned from Rest Service list of persons cannot be null.");
			throw new ServiceException("Returned from Rest Service list of persons cannot be null.");
		}
	}

	public void update(Person entity) {
		// TODO Auto-generated method stub

	}

	public void delete(Long entityId) {
		// TODO Auto-generated method stub

	}

}
