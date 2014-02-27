package com.patternbox.tangocalendar.event.application.command;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Named;

/**
 * ...
 * 
 * @author <a href='http://www.patternbox.com'>D. Ehms, Patternbox</a>
 */
@Named
@SuppressWarnings("serial")
public class CreateEventTemplateCommand implements Serializable {

	private Date startDate;

	private Date endDate;

	private long locationId;

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate
	 *          the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate
	 *          the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the locationId
	 */
	public long getLocationId() {
		return locationId;
	}

	/**
	 * @param locationId
	 *          the locationId to set
	 */
	public void setLocationId(long locationId) {
		this.locationId = locationId;
	}
}
