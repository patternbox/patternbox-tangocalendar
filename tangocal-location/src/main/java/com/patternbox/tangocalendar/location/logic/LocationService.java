/**************************** Copyright notice ********************************

Copyright (C)2013 by D. Ehms, http://www.patternbox.com
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
package com.patternbox.tangocalendar.location.logic;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import com.patternbox.tangocalendar.annotations.DomainService;
import com.patternbox.tangocalendar.location.domain.Location;

/**
 * DDD service to access <strong>location management</strong> functionality. It annotated as
 * state-less EJB to start a new transaction.
 * 
 * @author <a href='http://www.patternbox.com'>D. Ehms, Patternbox<a>
 */
@DomainService
@Stateless
public class LocationService {

	@Inject
	private LocationRepository repository;

	/**
	 * Store new location instance into database.
	 * 
	 * @param location
	 *          the location to store
	 */
	public Location storeLocation(Location location) {
		repository.storeLocation(location);
		return location;
	}

	List<Location> getLocations() {
		return new ArrayList<Location>();
	}

	/**
	 * Returns a location for a given identifier.
	 * 
	 * @param identifier
	 *          the location identifier
	 * @return the location instance
	 */
	Location getLocation(Long identifier) {
		return repository.getLocation(identifier);
	}

	/**
	 * Returns a location for a given location name.
	 * 
	 * @param name
	 *          the location name
	 * @return the location instance
	 */
	public Location getLocation(String name) {
		return repository.getLocation(name);
	}

	/**
	 * Returns a list of location names.
	 */
	public List<String> getLocationNames() {
		return repository.getLocationNames();
	}
}
