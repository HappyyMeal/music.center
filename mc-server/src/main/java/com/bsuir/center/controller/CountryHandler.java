package com.bsuir.center.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bsuir.center.dao.ICoutryDao;
import com.bsuir.center.domain.Country;
import com.bsuir.center.exception.DaoException;

@RestController
@RequestMapping("v1/music/country")
public class CountryHandler {

	@Autowired
	private ICoutryDao coutryDao;

	@RequestMapping(method = RequestMethod.GET)
	public List<Country> readAll() {
		List<Country> countries = null;

		try {
			countries = coutryDao.readAll();
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return countries;
	}
}
