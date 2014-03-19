package com.patternbox.tangocalendar.location.interaction.web.beans;

import javax.inject.Inject;
import javax.inject.Named;

import com.patternbox.tangocalendar.core.command.CommandService;
import com.patternbox.tangocalendar.location.application.command.CreateLocationCommand;

/**
 * ...
 * 
 * @author <a href='http://www.patternbox.com'>D. Ehms, Patternbox</a>
 */
@Named("createLocation")
@SuppressWarnings("serial")
public class CreateLocationBean extends CreateLocationCommand {

	@Inject
	private CommandService commandService;

	private Long locationId;

	/**
	 * Default constructor to satisfy web container support.
	 */
	public CreateLocationBean() {
		super();
	}

	/**
	 * Parameterized constructor.
	 */
	public CreateLocationBean(String name, String country, String state, String town, String zipCode,
			String street) {
		super(name, country, state, town, zipCode, street);
	}

	public String submit() {
		locationId = (Long) commandService.execute(this);
		return "success";
	}

	/**
	 * @param name
	 *          the name to set
	 */
	public void setName(String name) {
		this.name = name;
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
	 * @param zipCode
	 *          the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * @param street
	 *          the street to set
	 */
	public void setStreet(String street) {
		this.street = street;
	}

	/**
	 * @param homePage
	 *          the homePage to set
	 */
	public void setHomePage(String homePage) {
		this.homePage = homePage;
	}

	/**
	 * @return the locationId
	 */
	public Long getLocationId() {
		return locationId;
	}
}
