package com.patternbox.tangocalendar.location.interaction.web.beans;

import javax.inject.Inject;
import javax.inject.Named;

import com.patternbox.tangocalendar.core.command.CommandService;
import com.patternbox.tangocalendar.location.application.command.UpdateLocationAddressCommand;

/**
 * ...
 * 
 * @author <a href='http://www.patternbox.com'>D. Ehms, Patternbox</a>
 */
@Named("updateLocationAddress")
public class UpdateLocationAddressBean extends UpdateLocationAddressCommand {

	@Inject
	private CommandService commandService;

	/**
	 * Default constructor to satisfy web container support.
	 */
	public UpdateLocationAddressBean() {
		super(null, null, null, null, null, null);
	}

	/**
	 * Execute command on command service.
	 * 
	 * @return "success"
	 */
	public String submit() {
		commandService.execute(this);
		return "success";
	}

	/**
	 * @param country
	 *          the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * @param state
	 *          the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @param town
	 *          the town to set
	 */
	public void setTown(String town) {
		this.town = town;
	}

	/**
	 * @param postalCode
	 *          the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * @param street
	 *          the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}
}
