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
package com.patternbox.tangocalendar.event.domain.model.danceevent;

import static com.patternbox.tangocalendar.event.domain.model.danceevent.SingleEvent.FK_PARENT_EVENT;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 * Aggregates a bunch of {@link SingleEvent}s as multiple event.
 * 
 * @author <a href='http://www.patternbox.com'>D. Ehms, Patternbox<a>
 */
@Entity
public class MultiEvent extends AbstractDanceEvent {

	@OneToMany
	@JoinColumn(name = FK_PARENT_EVENT)
	private final List<SingleEvent> events = new ArrayList<SingleEvent>();

	/**
	 * Append new single event
	 * 
	 * @param event
	 *          a single event instance
	 */
	public void appendSingleEvent(SingleEvent event) {
		events.add(event);
	}

	/**
	 * Minimum date of all assigned single events.
	 * 
	 * @return the start date of the multiple event
	 */
	public Date getStartDate() {
		return null;
	}

	/**
	 * Maximum date of all assigned single events.
	 * 
	 * @return the end date of the multiple event
	 */
	public Date getEndDate() {
		return null;
	}
}
