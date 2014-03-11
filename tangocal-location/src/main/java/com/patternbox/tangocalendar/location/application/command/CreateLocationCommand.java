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

	private final String name;

	private final String country;

	private final String state;

	private final String town;

	private final String zipCode;

	private final String street;

	/**
	 * @param name
	 * @param country
	 * @param state
	 * @param town
	 * @param zipCode
	 * @param street
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
