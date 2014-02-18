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
package com.patternbox.tangocal.calendar.application;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.ValidationException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import com.patternbox.tangocal.calendar.domain.EventCalendar;
import com.patternbox.tangocal.calendar.logic.EventDateService;
import com.patternbox.tangocalendar.annotations.ApplicationService;

/**
 * @author <a href='http://www.patternbox.com'>D. Ehms, Patternbox</a>
 */
@Stateless
@ApplicationService
@Path("/v2")
public class CalendarService {

	private static final int MAX_DAY_COUNT = 60;

	private static final String INTERNAL_DATE_FORMAT = "yyyyMMddHHmmss";

	// private static final String LAST_UPDATE_DEFAULT = "19700101000000";
	@Inject
	private Logger logger;

	@Inject
	private EventDateService eventDateService;

	/**
	 * @param dayCount
	 *          the number of days
	 * @return
	 */
	@GET
	@Produces("application/json")
	// @Produces("application/xml")
	public EventCalendar findEvents(@QueryParam("lastupdate") String lastupdateParam) {
		int dayCount = 12;
		validateDayCount(dayCount);
		Date lastUpdate = convertLastUpdate(lastupdateParam);
		logger.info("Fetch event dates, day count: " + dayCount + ", last update: " + lastUpdate);
		return eventDateService.findEvents(dayCount, lastUpdate);
	}

	private Date convertLastUpdate(String lastupdate) {
		DateFormat df = new SimpleDateFormat(INTERNAL_DATE_FORMAT);
		try {
			return df.parse(lastupdate);
		} catch (Exception e) {
			return new Date(0);
		}
	}

	private void validateDayCount(int dayCount) {
		if (dayCount < 1 || dayCount > MAX_DAY_COUNT) {
			throw new ValidationException("Invalid day count: " + dayCount);
		}
	}
}
