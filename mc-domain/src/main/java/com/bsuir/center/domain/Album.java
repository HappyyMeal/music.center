package com.bsuir.center.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "album")
public class Album implements Serializable {
	private static final long serialVersionUID = -5426752557428630080L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "album_id", unique = true, nullable = false)
	private Integer albumId;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name = "artist_id", nullable = false)
	private Artist artist;

	@Column(name = "album_title")
	private String albumTitle;

	@Column(name = "album_year")
	private Date albumYear;

	@Column(name = "album_tracks")
	private Integer albumnTracks;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "pk.album", cascade = CascadeType.ALL)
	private Set<AlbumSong> albumSongs = new HashSet<AlbumSong>(0);

	public Integer getAlbumId() {
		return albumId;
	}

	public void setAlbumId(Integer albumId) {
		this.albumId = albumId;
	}

	public Artist getArtist() {
		return artist;
	}

	public void setArtist(Artist artist) {
		this.artist = artist;
	}

	public String getAlbumTitle() {
		return albumTitle;
	}

	public void setAlbumTitle(String albumTitle) {
		this.albumTitle = albumTitle;
	}

	public Date getAlbumYear() {
		return albumYear;
	}

	public void setAlbumYear(Date albumYear) {
		this.albumYear = albumYear;
	}

	public Integer getAlbumnTracks() {
		return albumnTracks;
	}

	public void setAlbumnTracks(Integer albumnTracks) {
		this.albumnTracks = albumnTracks;
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
		result = prime * result + ((albumId == null) ? 0 : albumId.hashCode());
		result = prime * result + ((albumTitle == null) ? 0 : albumTitle.hashCode());
		result = prime * result + ((albumYear == null) ? 0 : albumYear.hashCode());
		result = prime * result + ((albumnTracks == null) ? 0 : albumnTracks.hashCode());
		result = prime * result + ((artist == null) ? 0 : artist.hashCode());
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
		Album other = (Album) obj;
		if (albumId == null) {
			if (other.albumId != null)
				return false;
		} else if (!albumId.equals(other.albumId))
			return false;
		if (albumTitle == null) {
			if (other.albumTitle != null)
				return false;
		} else if (!albumTitle.equals(other.albumTitle))
			return false;
		if (albumYear == null) {
			if (other.albumYear != null)
				return false;
		} else if (!albumYear.equals(other.albumYear))
			return false;
		if (albumnTracks == null) {
			if (other.albumnTracks != null)
				return false;
		} else if (!albumnTracks.equals(other.albumnTracks))
			return false;
		if (artist == null) {
			if (other.artist != null)
				return false;
		} else if (!artist.equals(other.artist))
			return false;
		return true;
	}

}
