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
package com.patternbox.tangocalendar.event.infrastructure.persistence;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.MappedSuperclass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.TypedQuery;

import com.patternbox.tangocalendar.core.annotations.Finder;
import com.patternbox.tangocalendar.event.cdi.EventManagement;
import com.patternbox.tangocalendar.event.domain.model.danceevent.EventCategory;

/**
 * @author <a href='http://www.patternbox.com'>D. Ehms, Patternbox</a>
 */
@Finder
@MappedSuperclass
@ApplicationScoped
@NamedQueries({ @NamedQuery(name = "JpaEventCategoryFinder.findAll", query = "SELECT ec FROM EventCategory ec ") })
public class JpaEventCategoryFinder {

	@Inject
	@EventManagement
	private EntityManager em;

	private Map<String, String> eventCategories;

	/**
	 * Read event categories from database and cache them.
	 */
	@PostConstruct
	private void onPostConstruct() {
		SortedMap<String, String> categories = new TreeMap<String, String>();
		TypedQuery<EventCategory> qry = em.createNamedQuery("JpaEventCategoryFinder.findAll",
				EventCategory.class);
		for (EventCategory ec : qry.getResultList()) {
			categories.put(ec.getLabel(), ec.getCode());
		}
		eventCategories = Collections.unmodifiableSortedMap(categories);
	}

	public Map<String, String> getEventCategories() {
		return eventCategories;
	}

	@Deprecated
	public Map<String, String> getEventCategories3() {
		Map<String, String> result = new LinkedHashMap<String, String>();
		TypedQuery<EventCategory> qry = em.createNamedQuery("EventCategory.findAll",
				EventCategory.class);
		for (EventCategory ec : qry.getResultList()) {
			result.put(ec.getLabel(), ec.getCode());
		}
		return result;
	}

	@Deprecated
	public Map<String, String> getEventCategories2() {
		Map<String, String> result = new LinkedHashMap<String, String>();
		TypedQuery<EventCategory> qry = em.createQuery("select ec from EventCategory ec",
				EventCategory.class);
		for (EventCategory ec : qry.getResultList()) {
			result.put(ec.getLabel(), ec.getCode());
		}
		return result;
	}

	@Deprecated
	public Map<String, String> getEventCategories1() {
		Map<String, String> result = new LinkedHashMap<String, String>();
		result.put("Tango Argentino", "TANGO");
		result.put("Salsa", "SALSA");
		result.put("Standard und Latein", "BALLROOM");
		result.put("Discofox", "DISCOFOX");
		return result;
	}
}
