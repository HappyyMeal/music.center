package com.bsuir.center.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sex")
public class Sex implements Serializable {
	private static final long serialVersionUID = -8631602897628574461L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sex_id", unique = true, nullable = false)
	private Integer sexId;

	@Column(name = "sex_name")
	private String sexName;

	public Integer getSexId() {
		return sexId;
	}

	public void setSexId(Integer sexId) {
		this.sexId = sexId;
	}

	public String getSexName() {
		return sexName;
	}

	public void setSexName(String sexName) {
		this.sexName = sexName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sexId == null) ? 0 : sexId.hashCode());
		result = prime * result + ((sexName == null) ? 0 : sexName.hashCode());
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
		Sex other = (Sex) obj;
		if (sexId == null) {
			if (other.sexId != null)
				return false;
		} else if (!sexId.equals(other.sexId))
			return false;
		if (sexName == null) {
			if (other.sexName != null)
				return false;
		} else if (!sexName.equals(other.sexName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Sex [sexId=" + sexId + ", sexName=" + sexName + "]";
	}
}
