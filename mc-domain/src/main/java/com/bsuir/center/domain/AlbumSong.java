package com.bsuir.center.domain;

import java.io.Serializable;

import javax.persistence.AssociationOverride;
import javax.persistence.AssociationOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "album_song")
@AssociationOverrides({ @AssociationOverride(name = "pk.album", joinColumns = @JoinColumn(name = "album_id") ),
		@AssociationOverride(name = "pk.song", joinColumns = @JoinColumn(name = "song_id") ) })
public class AlbumSong implements Serializable {
	private static final long serialVersionUID = 7507703340139642044L;

	@EmbeddedId
	private AlbumSongId pk = new AlbumSongId();

	@Transient
	private Album album;

	@Transient
	private Song song;

	@Column(name = "track_number", nullable = false)
	private Integer trackNumber;

	public AlbumSongId getPk() {
		return pk;
	}

	public void setPk(AlbumSongId pk) {
		this.pk = pk;
	}

	public Album getAlbum() {
		return getPk().getAlbum();
	}

	public void setAlbum(Album album) {
		getPk().setAlbum(album);
	}

	public Song getSong() {
		return getPk().getSong();
	}

	public void setSong(Song song) {
		getPk().setSong(song);
	}

	public Integer getTrackNumber() {
		return trackNumber;
	}

	public void setTrackNumber(Integer trackNumber) {
		this.trackNumber = trackNumber;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pk == null) ? 0 : pk.hashCode());
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
		AlbumSong other = (AlbumSong) obj;
		if (pk == null) {
			if (other.pk != null)
				return false;
		} else if (!pk.equals(other.pk))
			return false;
		return true;
	}

}
