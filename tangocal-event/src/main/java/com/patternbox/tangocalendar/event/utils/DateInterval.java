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
package com.patternbox.tangocalendar.event.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;

import com.patternbox.tangocalendar.event.domain.model.eventtemplate.Recurrence.DayOfWeek;
import com.patternbox.tangocalendar.event.domain.model.eventtemplate.Recurrence.WeekSelection;

/**
 * @author <a href='http://www.patternbox.com'>D. Ehms, Patternbox</a>
 */
public class DateInterval {

	static final int DAYS_PER_WEEK = 7;

	/**
	 * Return the first occurrence of a weekday after a given start date.
	 * 
	 * @param weekday
	 *          the weekday reference
	 * @param baseDate
	 *          the date from which we start our inspection
	 */
	public static DateTime getFirstOccurrence(DayOfWeek weekday, DateTime baseDate) {
		int daysOffset = (DAYS_PER_WEEK - baseDate.getDayOfWeek() + weekday.ordinal()) % DAYS_PER_WEEK;
		return baseDate.plusDays(daysOffset);
	}

	/**
	 * Return a list of dates between start and end date for a given weekday.
	 * 
	 * @param dayOfWeek
	 *          The day of the week reference to calculate the list of days for
	 * @param startDate
	 *          the start date
	 * @param endDate
	 *          the end date
	 * 
	 * @return
	 */
	public static List<Date> getWeeklyDates(DayOfWeek dayOfWeek, Date startDate, Date endDate) {
		List<Date> result = new ArrayList<Date>();
		DateTime nextDate = getFirstOccurrence(dayOfWeek, new DateTime(startDate));
		DateTime upperBound = new DateTime(endDate).plusDays(1);
		for (; nextDate.isBefore(upperBound);) {
			result.add(nextDate.toDate());
			nextDate = nextDate.plusDays(DAYS_PER_WEEK);
		}
		return result;
	}

	/**
	 * Calculates the nth occurrence of a day of the week, for a given month and year.
	 * 
	 * @param dayOfWeek
	 *          The day of the week to calculate the day for (In the range of [1,7], where 1 is
	 *          Monday.
	 * @param weekSelection
	 *          The occurrence of the weekday to calculate. (ie. 1st, 2nd, 3rd)
	 * @param month
	 *          The month to calculate the day for.
	 * @param year
	 *          The year to calculate the day for.
	 * @return A {@link LocalDate} with the nth occurrence of the day of week, for the given month and
	 *         year.
	 */
	public static LocalDate getNthWeekdayOfMonth(DayOfWeek dayOfWeek, WeekSelection weekSelection,
			int month, int year) {
		int daysOffset = dayOfWeek.equals(DayOfWeek.SUNDAY) ? DAYS_PER_WEEK : dayOfWeek.ordinal();
		LocalDate start = new LocalDate(year, month, 1);
		LocalDate date = start.withDayOfWeek(daysOffset);
		return (date.isBefore(start)) ? date.plusWeeks(weekSelection.ordinal() + 1) : date
				.plusWeeks(weekSelection.ordinal());
	}
}
