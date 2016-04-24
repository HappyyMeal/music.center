package com.bsuir.client.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.bsuir.center.domain.Playlist;
import com.bsuir.center.domain.Song;
import com.bsuir.center.domain.User;

@Controller
@RequestMapping("/playlist")
public class PlaylistHandler {
	private static final Logger logger = Logger.getLogger(PlaylistHandler.class);

	private static final String PLAYLIST_REST_ENDPOINT = "http://localhost:8082/mc-server/v1/music/playlist";
	private static final String SONG_REST_ENDPOINT = "http://localhost:8082/mc-server/v1/music/song";

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String savePlaylist(@RequestParam String playlistName, Model model, HttpServletRequest request) {
		Integer retrievedID = null;

		User user = new User();
		Playlist playlist = new Playlist();

		String userName = (String) request.getSession().getAttribute("username");
		user.setUserLogin(userName);

		playlist.setUser(user);
		playlist.setPlaylistName(playlistName);
		playlist.setCreatedTimestamp(new Date());

		retrievedID = restTemplate.postForObject(PLAYLIST_REST_ENDPOINT, playlist, Integer.class);

		if (retrievedID != null) {
			return "redirect:show-all";
		} else {
			logger.error("Retrieved playlist ID is null.");
			return "error";
		}
	}

	@RequestMapping("/show-all")
	public String showAll(Model model) {
		List<Playlist> playlists = Arrays.asList(restTemplate.getForEntity(PLAYLIST_REST_ENDPOINT, Playlist[].class).getBody());

		if (playlists != null) {
			model.addAttribute("playlists", playlists);
		}
		return "all_playlist";
	}

	@RequestMapping("/show_save_tab")
	public String showSaveTab(Model model) {
		return "save_playlist";
	}

	@RequestMapping("/show-songs/{playlistId}")
	public String showRelatedSongs(@PathVariable Integer playlistId, Model model) {
		Playlist playlist = restTemplate.getForObject(PLAYLIST_REST_ENDPOINT + "/" + playlistId, Playlist.class);

		if (playlist != null) {
			model.addAttribute("playlist", playlist);
		}
		return "playlist_content";
	}

	@RequestMapping(value = "/show_playlists/{songId}", method = GET)
	public String showUserPlaylists(@PathVariable Integer songId, Model model, HttpServletRequest request) {
		String userName = (String) request.getSession().getAttribute("username");
		List<Playlist> playlits = Arrays.asList(restTemplate.getForEntity(PLAYLIST_REST_ENDPOINT + "/find/" + userName, Playlist[].class).getBody());
		Set<Playlist> userPlaylists = new HashSet<Playlist>(playlits);
		if (userPlaylists != null) {
			model.addAttribute("userPlaylists", userPlaylists);
		}
		request.getSession().setAttribute("songId", songId);
		return "bind_song";
	}

	@RequestMapping(value = "/bind_song", method = RequestMethod.POST)
	public String bindSongWithPlaylist(@RequestParam Integer playlistId, Model model, HttpServletRequest request) {
		Integer songId = null;
		Playlist playlist = null;
		Song song = null;
		
		songId = (Integer) request.getSession().getAttribute("songId");
		playlist = restTemplate.getForObject(PLAYLIST_REST_ENDPOINT + "/" + playlistId, Playlist.class);
		song = restTemplate.getForObject(SONG_REST_ENDPOINT + "/" + songId, Song.class);
		
		playlist.getSongs().add(song);
		restTemplate.put(PLAYLIST_REST_ENDPOINT, playlist);

		return "redirect:/search";
	}
}
