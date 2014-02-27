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
package com.patternbox.tangocal.calendar.logic;

import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import com.patternbox.tangocal.calendar.domain.EventCalendar;
import com.patternbox.tangocalendar.annotations.DomainService;
import com.patternbox.tangocalendar.event.domain.model.danceevent.DanceEventRepository;
import com.patternbox.tangocalendar.event.domain.model.danceevent.SingleEvent;

/**
 * @author <a href='http://www.patternbox.com'>D. Ehms, Patternbox</a>
 */
@DomainService
public class EventDateService {

	@Inject
	private DanceEventRepository eventRepository;

	/**
	 * @param dayCount
	 *          the number of days
	 * @return
	 */
	public EventCalendar findEvents(int dayCount, Date lastUpdate) {
		Date fromDate = new Date();
		Date toDate = calcToDate(dayCount);
		EventCalendar result = new EventCalendar(dayCount, lastUpdate);
		for (SingleEvent event : eventRepository.getEvents(fromDate, toDate)) {
			result.appendEvent(event);
		}
		return result;
	}

	private Date calcToDate(int dayCount) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		return cal.getTime();
	}
}
