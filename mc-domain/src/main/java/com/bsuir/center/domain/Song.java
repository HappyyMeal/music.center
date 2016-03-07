package com.bsuir.center.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table
@Entity(name = "song")
public class Song implements Serializable {
	private static final long serialVersionUID = 5039477807512758517L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "song_id", unique = true, nullable = false)
	private Integer songId;

	@Column(name = "song_title", nullable = false)
	private String songTitle;

	@Column(name = "song_duration")
	private int songDuration;

	@Column(name = "song_link")
	private String songLink;

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "songs")
	private Set<Playlist> playlists = new HashSet<Playlist>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.song", cascade = CascadeType.ALL)
	private Set<AlbumSong> albumSongs = new HashSet<AlbumSong>(0);

	public Integer getSongId() {
		return songId;
	}

	public void setSongId(Integer songId) {
		this.songId = songId;
	}

	public String getSongTitle() {
		return songTitle;
	}

	public void setSongTitle(String songTitle) {
		this.songTitle = songTitle;
	}

	public int getSongDuration() {
		return songDuration;
	}

	public void setSongDuration(int songDuration) {
		this.songDuration = songDuration;
	}

	public String getSongLink() {
		return songLink;
	}

	public void setSongLink(String songLink) {
		this.songLink = songLink;
	}

	public Set<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(Set<Playlist> playlists) {
		this.playlists = playlists;
	}

	public Set<AlbumSong> getAlbumSongs() {
		return albumSongs;
	}

	public void setAlbumSongs(Set<AlbumSong> albumSongs) {
		this.albumSongs = albumSongs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + songDuration;
		result = prime * result + ((songId == null) ? 0 : songId.hashCode());
		result = prime * result + ((songLink == null) ? 0 : songLink.hashCode());
		result = prime * result + ((songTitle == null) ? 0 : songTitle.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Song other = (Song) obj;
		if (songDuration != other.songDuration)
			return false;
		if (songId == null) {
			if (other.songId != null)
				return false;
		} else if (!songId.equals(other.songId))
			return false;
		if (songLink == null) {
			if (other.songLink != null)
				return false;
		} else if (!songLink.equals(other.songLink))
			return false;
		if (songTitle == null) {
			if (other.songTitle != null)
				return false;
		} else if (!songTitle.equals(other.songTitle))
			return false;
		return true;
	}

}
