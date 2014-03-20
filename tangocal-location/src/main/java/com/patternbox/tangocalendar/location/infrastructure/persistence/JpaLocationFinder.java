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

import static com.patternbox.tangocalendar.location.domain.model.location.Location.QRY_LOCATION_NAMES;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.MappedSuperclass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Query;

import com.patternbox.tangocalendar.core.annotations.Finder;
import com.patternbox.tangocalendar.location.application.data.LocationData;
import com.patternbox.tangocalendar.location.cdi.LocationManagement;

/**
 * @author <a href='http://www.patternbox.com'>D. Ehms, Patternbox<a>
 */
@Finder
@MappedSuperclass
@ApplicationScoped
@NamedQueries({
		@NamedQuery(name = "JpaLocationFinder.findAll", query = JpaLocationFinder.QRY_ROOT),
		@NamedQuery(name = "JpaLocationFinder.find", query = JpaLocationFinder.QRY_ROOT
				+ "WHERE l.id = :id") })
public class JpaLocationFinder {

	static final String QRY_ROOT = "SELECT NEW com.patternbox.tangocalendar.location.application.data.LocationData"
			+ "(l.identifier, l.name, l.address.country, l.address.state, l.address.town, l.address.postalCode, l.address.street) "
			+ "FROM Location l ";

	@Inject
	@LocationManagement
	private EntityManager em;

	public List<String> locationNames() {
		return em.createNamedQuery(QRY_LOCATION_NAMES, String.class).getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<LocationData> findLocations() {
		Query qry = em.createNamedQuery("JpaLocationFinder.findAll");
		return qry.getResultList();
	}

	public LocationData findLocation(Long id) {
		Query qry = em.createNamedQuery("JpaLocationFinder.find").setParameter("id", id);
		return (LocationData) qry.getSingleResult();
	}
}
