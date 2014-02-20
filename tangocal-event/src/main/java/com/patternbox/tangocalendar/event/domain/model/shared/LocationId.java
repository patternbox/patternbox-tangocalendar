package com.patternbox.tangocalendar.event.domain.model.shared;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class LocationId implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "location_id", unique = true, updatable = false)
	private String id;

	public LocationId() {
	}

	public LocationId(String id) {
		this.id = id;
	}

	public String getIdString() {
		return id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		LocationId other = (LocationId) o;
		return sameValueAs(other);
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	boolean sameValueAs(LocationId other) {
		return other != null && this.id.equals(other.id);
	}

	@Override
	public String toString() {
		return id;
	}
}
