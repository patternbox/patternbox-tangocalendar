package com.patternbox.tangocalendar.location.application.command;

import javax.inject.Named;

import com.patternbox.tangocalendar.location.application.data.AddressData;

/**
 * ...
 * 
 * @author <a href='http://www.patternbox.com'>D. Ehms, Patternbox</a>
 */
@Named
public class UpdateLocationAddressCommand extends AddressData {

	private Long locationId;

	/**
	 * Parameterized constructor used for field initialization.
	 */
	public UpdateLocationAddressCommand(String country, String state, String town, String postalCode,
			String street, Long locationId) {
		super(country, state, town, postalCode, street);
		this.setLocationId(locationId);
	}

	/**
	 * @return the locationId
	 */
	public Long getLocationId() {
		return locationId;
	}

	/**
	 * @param locationId
	 *          the locationId to set
	 */
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
}
