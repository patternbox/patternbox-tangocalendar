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
package com.patternbox.tangocalendar.location.domain.model.location;

import static com.patternbox.tangocalendar.location.domain.model.location.Location.COL_NAME;
import static com.patternbox.tangocalendar.location.domain.model.location.Location.ORDER_SUFFIX;
import static com.patternbox.tangocalendar.location.domain.model.location.Location.QRY_LOCATION_BY_NAME;
import static com.patternbox.tangocalendar.location.domain.model.location.Location.QRY_LOCATION_NAMES;
import static com.patternbox.tangocalendar.location.domain.model.location.Location.SELECT_PREFIX;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;

import com.patternbox.tangocalendar.core.event.DomainEventPublisher;
import com.patternbox.tangocalendar.core.types.Entity;

/**
 * The tango event location entity.
 * 
 * @author <a href='http://www.patternbox.com'>D. Ehms, Patternbox<a>
 */
@javax.persistence.Entity
@NamedQueries({
		@NamedQuery(name = QRY_LOCATION_BY_NAME, query = SELECT_PREFIX + "WHERE l.name = :" + COL_NAME),
		@NamedQuery(name = QRY_LOCATION_NAMES, query = "SELECT l.name FROM Location l " + ORDER_SUFFIX) })
// @EntityListeners({ LocationEntityListener.class })
public class Location implements Entity<Location, Long> {

	public static final String QRY_LOCATION_NAMES = "QryLocationNames";

	public static final String QRY_LOCATION_BY_NAME = "QryLocationByName";

	public static final String COL_NAME = "name";

	public static final String FK_LOCATION = "Location_FK";

	static final String SELECT_PREFIX = "SELECT l FROM Location l ";

	static final String ORDER_SUFFIX = " ORDER BY l.name";

	@Id
	@GeneratedValue
	private Long identifier;

	@Column(unique = true, nullable = false)
	private String name;

	@Embedded
	private Address address;

	@Embedded
	private Coordinates geoPosition;

	/**
	 * Default constructor to satisfy JPA.
	 */
	public Location() {
		super();
	}

	/**
	 * Parameterized constructor.
	 */
	public Location(String name) {
		this.name = name;
	}

	@PostPersist
	@PostUpdate
	private void onPostPersistOrUpdate() {
		// locationAddressUpdated.fire(new LocationAddressUpdated(address));
	}

	/**
	 * @see com.patternbox.tangocalendar.core.types.Entity#sameIdentityAs(java.lang.Object)
	 */
	@Override
	public boolean sameIdentityAs(Location other) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @see com.patternbox.tangocalendar.core.types.Entity#getIdentifer()
	 */
	@Override
	public Long getIdentifer() {
		return identifier;
	}

	/**
	 * Update location address.
	 * 
	 * @param address
	 *          the location address
	 */
	public void updateAddress(Address address) {
		this.address = address;
		DomainEventPublisher.getInstance().publish(new LocationAddressUpdated(identifier, address));
	}
}
