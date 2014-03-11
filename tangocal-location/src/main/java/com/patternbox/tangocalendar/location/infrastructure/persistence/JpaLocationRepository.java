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
package com.patternbox.tangocalendar.location.infrastructure.persistence;

import static com.patternbox.tangocalendar.location.domain.model.location.Location.COL_NAME;
import static com.patternbox.tangocalendar.location.domain.model.location.Location.QRY_LOCATION_BY_NAME;
import static com.patternbox.tangocalendar.location.domain.model.location.Location.QRY_LOCATION_NAMES;

import java.util.List;

import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.patternbox.tangocalendar.core.annotations.Repository;
import com.patternbox.tangocalendar.location.cdi.LocationManagement;
import com.patternbox.tangocalendar.location.domain.model.location.Address;
import com.patternbox.tangocalendar.location.domain.model.location.Location;
import com.patternbox.tangocalendar.location.domain.model.location.LocationRepository;

/**
 * @author <a href='http://www.patternbox.com'>D. Ehms, Patternbox<a>
 */
@Repository
public class JpaLocationRepository implements LocationRepository {

	@Inject
	@LocationManagement
	private EntityManager em;

	@Inject
	private Event<Address> locationAddressUpdated;

	/**
	 * @see com.patternbox.tangocalendar.location.domain.model.location.LocationRepository#storeLocation(com.patternbox.tangocalendar.location.domain.model.location.Location)
	 */
	@Override
	public void storeLocation(Location location) {
		em.persist(location);
		locationAddressUpdated.fire(new Address());
	}

	/**
	 * @see com.patternbox.tangocalendar.location.domain.model.location.LocationRepository#findLocation(java.lang.Long)
	 */
	@Override
	public Location findLocation(Long identifier) {
		return em.find(Location.class, identifier);
	}

	/**
	 * @see com.patternbox.tangocalendar.location.domain.model.location.LocationRepository#findLocation(java.lang.String)
	 */
	@Override
	public Location findLocation(String name) {
		TypedQuery<Location> query = em.createNamedQuery(QRY_LOCATION_BY_NAME, Location.class);
		query.setParameter(COL_NAME, name);
		return query.getSingleResult();
	}

	/**
	 * @see com.patternbox.tangocalendar.location.domain.model.location.LocationRepository#locationNames()
	 */
	@Override
	public List<String> locationNames() {
		return em.createNamedQuery(QRY_LOCATION_NAMES, String.class).getResultList();
	}
}
