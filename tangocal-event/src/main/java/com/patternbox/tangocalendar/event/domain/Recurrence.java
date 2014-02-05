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
package com.patternbox.tangocalendar.event.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.patternbox.tangocalendar.types.ValueObject;

/**
 * @author <a href='http://www.patternbox.com'>D. Ehms, Patternbox</a>
 */
@Embeddable
@SuppressWarnings("serial")
public class Recurrence implements ValueObject<Recurrence> {

	public enum DayOfWeek {
		SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY;
	}

	public enum WeekSelection {
		FIRST, SECOND, THIRD, FOURTH, LAST;
	}

	public enum Period {
		ONCE, WEEKLY, MONTHLY, NEVER;
	}

	private Date startDate;

	private Date endDate;

	@Enumerated(EnumType.STRING)
	private DayOfWeek dayOfWeek;

	@Enumerated(EnumType.STRING)
	private WeekSelection weekSelection;

	@Enumerated(EnumType.STRING)
	private Period period;

	/**
	 * Default constructor to satisfy JPA.
	 */
	public Recurrence() {
		super();
	}

	/**
	 * Parameterized constructor.
	 */
	public Recurrence(Date startDate, Date endDate, DayOfWeek weekday, WeekSelection weekSelection,
			Period period) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.dayOfWeek = weekday;
		this.weekSelection = weekSelection;
		this.period = period;
	}

	/**
	 * @see com.patternbox.tangocalendar.types.ValueObject#sameValueAs(java.lang.Object)
	 */
	@Override
	public boolean sameValueAs(Recurrence other) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Returns date instance for today without time part
	 */
	public static Date getToday() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return new Date(cal.getTime().getTime());
	}

	/**
	 * Returns a list of event dates for the next three months.
	 */
	public List<Date> getNextEventDates() {
		List<Date> result = new ArrayList<Date>();
		return result;
	}
}
