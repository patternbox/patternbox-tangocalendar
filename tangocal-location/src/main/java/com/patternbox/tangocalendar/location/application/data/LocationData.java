/**************************** Copyright notice ********************************

Copyright (C)2014 by D. Ehms, http://www.patternbox.com
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions
are met:
1. Redistributions of source code must retain the above copyright
notice, this list of conditions and the following disclaimer.
2. Redistributions in binary form must reproduce the above copyright
notice, this list of conditions and the following disclaimer in the
documentation and/or other materials provided with the distribution.
THIS SOFTWARE IS PROVIDED BY THE AUTHOR AND CONTRIBUTORS ``AS IS'' AND
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
ARE DISCLAIMED. IN NO EVENT SHALL THE AUTHOR OR CONTRIBUTORS BE LIABLE
FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS
OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION)
HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT
LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
SUCH DAMAGE.
 ******************************************************************************/
package com.patternbox.tangocalendar.location.application.data;

/**
 * @author <a href='http://www.patternbox.com'>D. Ehms, Patternbox</a>
 */
public class LocationData {

	protected Long identifier;

	protected String name;

	protected AddressData address;

	/**
	 * Default constructor to satisfy web container
	 */
	public LocationData() {
		super();
	}

	/**
	 * Parameterized constructor used for field initialization.
	 */
	public LocationData(Long identifier, String name, String country, String state, String town,
			String postalCode, String street) {
		this.identifier = identifier;
		this.name = name;
		this.address = new AddressData(country, state, town, postalCode, street);
	}

	/**
	 * Parameterized constructor used for field initialization.
	 */
	public LocationData(Long identifier, String name, AddressData address) {
		this.identifier = identifier;
		this.name = name;
		this.address = address;
	}

	/**
	 * Parameterized constructor used for field initialization.
	 */
	public LocationData(String name, AddressData address) {
		this.identifier = null;
		this.name = name;
		this.address = address;
	}

	/**
	 * @return the id
	 */
	public Long getIdentifier() {
		return identifier;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the address
	 */
	public AddressData getAddress() {
		return address;
	}
}
