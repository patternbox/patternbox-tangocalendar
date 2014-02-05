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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import com.patternbox.tangocalendar.event.domain.Recurrence;
import com.patternbox.tangocalendar.event.domain.Recurrence.DayOfWeek;

/**
 * @author <a href='http://www.patternbox.com'>D. Ehms, Patternbox</a>
 */
public class DateIntervalTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetFirstOccurrence() {
		DateTime today = new DateTime(Recurrence.getToday());
		String todayWeekday = today.dayOfWeek().getAsText(Locale.US).toUpperCase();
		// test every day of week and focus on today as a special test case
		for (DayOfWeek weekday : DayOfWeek.values()) {
			DateTime day = DateInterval.getFirstOccurrence(weekday, today);
			String dayOfWeek = day.dayOfWeek().getAsText(Locale.US).toUpperCase();
			assertEquals("Day of week name wrong", weekday.name(), dayOfWeek);
			if (todayWeekday.equals(dayOfWeek)) {
				assertEquals("Wrong day offset", today, day);
			} else {
				assertTrue(today.isBefore(day));
			}
		}
	}

	@Test
	public void testGetWeeklyDates() {
		DateTime today = new DateTime(Recurrence.getToday());
		final int weekCount = 4;
		final int dayOffset = (DateInterval.DAYS_PER_WEEK * weekCount) - 1;
		DateTime lastDate = new DateTime(today).plusDays(dayOffset);
		// test every day of week
		for (DayOfWeek weekday : DayOfWeek.values()) {
			List<Date> dates = DateInterval.getWeeklyDates(weekday, today.toDate(), lastDate.toDate());
			assertNotNull("No date list", dates);
			assertEquals("Date list size is wrong", weekCount, dates.size());
			assertEquals("First date is wrong", DateInterval.getFirstOccurrence(weekday, today).toDate(),
					dates.get(0));
		}
	}

	@Test
	public void testJodaDate() {
		DateTime date = new DateTime();
	}
}
