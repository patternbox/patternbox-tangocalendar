package com.patternbox.tangocalendar.location.application.command;

import javax.inject.Named;

import com.patternbox.tangocalendar.location.application.data.AddressData;
import com.patternbox.tangocalendar.location.application.data.LocationData;

/**
 * ...
 * 
 * @author <a href='http://www.patternbox.com'>D. Ehms, Patternbox</a>
 */
@Named
public class CreateLocationCommand extends LocationData {

	/**
	 * Default constructor to satisfy web container
	 */
	public CreateLocationCommand() {
		super();
	}

	/**
	 * Parameterized constructor used for field initialization.
	 */
	public CreateLocationCommand(String name, AddressData address) {
		super(name, address);
	}
}
