package com.patternbox.tangocalendar.event.application.command;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Named;

import com.patternbox.tangocalendar.event.domain.model.eventtemplate.Recurrence;

/**
 * ...
 * 
 * @author <a href='http://www.patternbox.com'>D. Ehms, Patternbox</a>
 */
@Named
@SuppressWarnings("serial")
public class CreateEventTemplateCommand implements Serializable {

	private final Date startDate;

	private final Date endDate;

	private final long locationId;

	private final String eventCategory;

	private final Recurrence recurrence;

	/**
	 * Parameterized constructor
	 */
	public CreateEventTemplateCommand(Date startDate, Date endDate, long locationId,
			String eventCategory, Recurrence recurrence) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.locationId = locationId;
		this.eventCategory = eventCategory;
		this.recurrence = recurrence;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @return the locationId
	 */
	public long getLocationId() {
		return locationId;
	}

	/**
	 * @return the eventCategory
	 */
	public String getEventCategory() {
		return eventCategory;
	}

	/**
	 * @return the recurrence
	 */
	public Recurrence getRecurrence() {
		return recurrence;
	}
}
