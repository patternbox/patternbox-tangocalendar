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
package com.patternbox.tangocalendar.event.infrastructure.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.patternbox.tangocalendar.annotations.Repository;
import com.patternbox.tangocalendar.event.domain.model.danceevent.DanceEventRepository;
import com.patternbox.tangocalendar.event.domain.model.danceevent.EventType;
import com.patternbox.tangocalendar.event.domain.model.danceevent.SingleEvent;

/**
 * @author <a href='http://www.patternbox.com'>D. Ehms, Patternbox</a>
 */
@Repository
public class JpaDanceEventRepository implements DanceEventRepository {

	@Inject
	private EntityManager em;

	/**
	 * @see com.patternbox.tangocalendar.event.domain.model.danceevent.DanceEventRepository#getEventCategories()
	 */
	@Override
	public Map<String, String> getEventCategories() {
		Map<String, String> result = new LinkedHashMap<String, String>();
		result.put("Tango Argentino", "TANGO");
		result.put("Salsa", "SALSA");
		result.put("Standard und Latein", "BALLROOM");
		result.put("Discofox", "DISCOFOX");
		return result;
	}

	/**
	 * @see com.patternbox.tangocalendar.event.domain.model.danceevent.DanceEventRepository#getEvents(java.util.Date,
	 *      java.util.Date)
	 */
	@Override
	public List<SingleEvent> getEvents(Date fromDate, Date toDate) {
		List<SingleEvent> result = new ArrayList<SingleEvent>();
		SingleEvent event = new SingleEvent("Hallo", fromDate, "20:00", "22:00", "TANGO",
				EventType.Milonga);
		result.add(event);
		return result;
	}
}