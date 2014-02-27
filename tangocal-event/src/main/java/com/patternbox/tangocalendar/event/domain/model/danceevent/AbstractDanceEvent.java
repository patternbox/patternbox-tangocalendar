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
package com.patternbox.tangocalendar.event.domain.model.danceevent;

import static com.patternbox.tangocalendar.location.domain.model.location.Location.FK_LOCATION;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.patternbox.tangocalendar.location.domain.model.location.Location;
import com.patternbox.tangocalendar.types.Entity;

/**
 * An abstract dance event to define all shared stuff.
 * 
 * @author <a href='http://www.patternbox.com'>D. Ehms, Patternbox<a>
 */
@XmlRootElement
@javax.persistence.Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractDanceEvent implements Entity<AbstractDanceEvent, Long> {

	@Id
	@GeneratedValue
	private Long identifier;

	@Column(unique = true, nullable = false)
	private String eventName;

	@Temporal(TemporalType.DATE)
	private Date eventDate;

	private String startTime;

	private String endTime;

	/**
	 * Uses {@link EventCategory} as lookup.
	 */
	@NotNull
	private String categoryCode;

	@Enumerated(EnumType.STRING)
	private EventType eventType;

	@ManyToOne
	@JoinColumn(name = FK_LOCATION)
	private Location location;

	public AbstractDanceEvent() {
		super();
	}

	/**
	 * @param eventName
	 * @param eventDate
	 * @param startTime
	 * @param endTime
	 * @param categoryCode
	 * @param eventType
	 */
	public AbstractDanceEvent(String eventName, Date eventDate, String startTime, String endTime,
			String categoryCode, EventType eventType) {
		this.eventName = eventName;
		this.eventDate = eventDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.categoryCode = categoryCode;
		this.eventType = eventType;
	}

	/**
	 * @see com.patternbox.tangocalendar.types.Entity#sameIdentityAs(java.lang.Object)
	 */
	@Override
	public boolean sameIdentityAs(AbstractDanceEvent other) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @see com.patternbox.tangocalendar.types.Entity#getIdentifer()
	 */
	@Override
	public Long getIdentifer() {
		return identifier;
	}

	/**
	 * @return the eventName
	 */
	public String getEventName() {
		return eventName;
	}

	/**
	 * @return the location
	 */
	public Location getLocation() {
		return location;
	}

	/**
	 * @return the identifier
	 */
	@XmlTransient
	public Long getIdentifier() {
		return identifier;
	}

	/**
	 * @return the eventDate
	 */
	@XmlTransient
	public Date getEventDate() {
		return eventDate;
	}

	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @return the categoryCode
	 */
	public String getCategoryCode() {
		return categoryCode;
	}

	/**
	 * @return the eventType
	 */
	public EventType getEventType() {
		return eventType;
	}
}
