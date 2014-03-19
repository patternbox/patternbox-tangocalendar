package com.patternbox.tangocalendar.location.application.command;

import java.io.Serializable;

import javax.inject.Named;

/**
 * ...
 * 
 * @author <a href='http://www.patternbox.com'>D. Ehms, Patternbox</a>
 */
@Named
@SuppressWarnings("serial")
public class CreateLocationCommand implements Serializable {

	protected String name;

	protected String country;

	protected String state;

	protected String town;

	protected String zipCode;

	protected String street;

	protected String homePage;

	/**
	 * Default constructor used by derived sub classes
	 */
	protected CreateLocationCommand() {
		super();
	}

	/**
	 * Parameterized constructor.
	 */
	public CreateLocationCommand(String name, String country, String state, String town,
			String zipCode, String street) {
		this.name = name;
		this.country = country;
		this.state = state;
		this.town = town;
		this.zipCode = zipCode;
		this.street = street;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the homePage
	 */
	public String getHomePage() {
		return homePage;
	}

	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @return the town
	 */
	public String getTown() {
		return town;
	}

	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}
}
