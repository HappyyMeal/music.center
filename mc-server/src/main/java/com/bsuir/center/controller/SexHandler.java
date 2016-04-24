package com.bsuir.center.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bsuir.center.dao.ISexDao;
import com.bsuir.center.domain.Sex;
import com.bsuir.center.exception.DaoException;

@RestController
@RequestMapping("v1/music/sex")
public class SexHandler {

	@Autowired
	private ISexDao sexDao;

	@RequestMapping(method = RequestMethod.GET)
	public List<Sex> readAll() {
		List<Sex> sexs = null;

		try {
			sexs = sexDao.readAll();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return sexs;
	}
}
