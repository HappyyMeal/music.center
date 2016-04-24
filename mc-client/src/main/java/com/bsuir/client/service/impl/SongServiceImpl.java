package com.bsuir.client.service.impl;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bsuir.center.domain.Song;
import com.bsuir.client.exception.ServiceException;
import com.bsuir.client.service.ISongService;

@Service
public class SongServiceImpl implements ISongService {
	private static final Logger logger = Logger.getLogger(SongServiceImpl.class);
	
	@Autowired
	private Environment environment;

	@Autowired
	private RestTemplate restTemplate;

	public Long create(Song entity) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public Song read(Long entityId) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Song> readAll() throws ServiceException {

		List<Song> songs = Arrays.asList(restTemplate.getForEntity(environment.getProperty("SONG_REST_SERVICE_URI"), Song[].class).getBody());

		if (songs != null) {
			return songs;
		} else {
			logger.error("Returned from Rest Service list cannot be null.");
			throw new ServiceException("Returned from Rest Service list cannot be null.");
		}
	}

	public void update(Song entity) {
		// TODO Auto-generated method stub

	}

	public void delete(Long entityId) {
		// TODO Auto-generated method stub

	}

}
