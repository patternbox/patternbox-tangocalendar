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
package com.patternbox.tangocal.calendar.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import com.patternbox.tangocalendar.event.domain.model.danceevent.SingleEvent;

/**
 * @author <a href='http://www.patternbox.com'>D. Ehms, Patternbox</a>
 */
@XmlRootElement
public class EventCalendar {

	private static final String INTERNAL_DATE_FORMAT = "yyyyMMddHHmmss";

	private static final int DEFAULT_DAY_COUNT = 7;

	private final String timestamp;

	private final String lastUpdate;

	private int dayCount;

	private final Map<Date, EventDate> eventDateMap = new LinkedHashMap<Date, EventDate>();

	private final Map<String, SingleEvent> eventMap = new LinkedHashMap<String, SingleEvent>();

	/**
	 * Default constructor to satisfy JAXB
	 */
	public EventCalendar() {
		this(DEFAULT_DAY_COUNT, new Date(0));
	}

	public EventCalendar(int dayCount, Date lastUpdate) {
		DateFormat df = new SimpleDateFormat(INTERNAL_DATE_FORMAT);
		timestamp = df.format(new Date());
		this.lastUpdate = df.format(lastUpdate);
		this.dayCount = dayCount;
	}

	public void appendEvent(SingleEvent event) {
		Date date = event.getEventDate();
		EventDate eventDate = eventDateMap.get(date);
		if (eventDate == null) {
			eventDate = new EventDate(date);
			eventDateMap.put(date, eventDate);
			eventMap.put(eventDate.getIsoDate(), event);
		}
		eventDate.appendEvent(event);
	}

	/**
	 * @return the dayCount
	 */
	@XmlAttribute
	public int getDayCount() {
		return dayCount;
	}

	/**
	 * @param dayCount
	 *          the dayCount to set
	 */
	public void setDayCount(int dayCount) {
		this.dayCount = dayCount;
	}

	/**
	 * Return the event dates
	 */
	public Collection<EventDate> getEventDates() {
		return eventDateMap.values();
	}

	/**
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}

	/**
	 * @return the lastUpdate
	 */
	public String getLastUpdate() {
		return lastUpdate;
	}

	/**
	 * @return the eventMap
	 */
	public Map<String, SingleEvent> getEvents() {
		return eventMap;
	}
}
