package com.bsuir.client.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import com.bsuir.center.domain.Song;
import com.bsuir.client.exception.ServiceException;
import com.bsuir.client.service.ISongService;

@Controller
@RequestMapping("/song")
public class SongHandler {
	private static final Logger logger = Logger.getLogger(SongHandler.class);

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ISongService songService;

	@RequestMapping(value = "/all", method = GET)
	public String readListOfSongs(Model model) {
		List<Song> songs = null;

		try {
			songs = songService.readAll();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		model.addAttribute("songs", songs);
		return "all_songs";
	}
}
