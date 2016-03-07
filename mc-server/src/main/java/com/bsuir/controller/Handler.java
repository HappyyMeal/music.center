package com.bsuir.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bsuir.center.domain.Album;
import com.bsuir.center.domain.AlbumSong;
import com.bsuir.center.domain.Song;
import com.bsuir.center.util.HibernateUtil;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.hibernate.Session;

@Controller
public class Handler {

	@RequestMapping(value = "/hey", method = GET)
	public void main() {

		Session session = HibernateUtil.currentSession();
		session.beginTransaction();

		Song song1 = new Song();
		song1.setSongTitle("song");
		song1.setSongDuration(1);
		song1.setSongLink("song");

		Album album = session.get(Album.class, 4);

		AlbumSong albumSong = new AlbumSong();
		albumSong.setAlbum(album);
		albumSong.setSong(song1);
		albumSong.setTrackNumber(3);

		song1.getAlbumSongs().add(albumSong);

		session.save(song1);

		session.getTransaction().commit();
		HibernateUtil.closeSession();
	}
}
