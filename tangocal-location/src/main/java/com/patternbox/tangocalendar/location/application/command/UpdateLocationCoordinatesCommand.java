package com.patternbox.tangocalendar.location.application.command;

import java.io.Serializable;

import javax.inject.Named;

import com.patternbox.tangocalendar.location.domain.model.location.Coordinates;

/**
 * ...
 * 
 * @author <a href='http://www.patternbox.com'>D. Ehms, Patternbox</a>
 */
@Named
@SuppressWarnings("serial")
public class UpdateLocationCoordinatesCommand implements Serializable {

	private final Long locationId;

	private final Coordinates coordinates;

	/**
	 * Parameterized constructor.
	 */
	public UpdateLocationCoordinatesCommand(Long locationId, Coordinates coordinates) {
		this.locationId = locationId;
		this.coordinates = coordinates;
	}

	/**
	 * @return the locationId
	 */
	public Long getLocationId() {
		return locationId;
	}

	/**
	 * @return the coordinates
	 */
	public Coordinates getCoordinates() {
		return coordinates;
	}
}
