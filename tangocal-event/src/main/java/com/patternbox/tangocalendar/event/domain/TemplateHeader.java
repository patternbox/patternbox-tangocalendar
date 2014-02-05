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
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.patternbox.tangocalendar.types.Entity;

/**
 * @author <a href='http://www.patternbox.com'>D. Ehms, Patternbox</a>
 */
@javax.persistence.Entity
public class TemplateHeader implements Entity<TemplateHeader, Long> {

	@Id
	@GeneratedValue
	private Long identifier;

	@Embedded
	private Recurrence recurrence;

	// @Column(unique = true, nullable = false)
	// private String name;
	@OneToMany
	private final List<TemplateEvent> templates = new ArrayList<TemplateEvent>();

	/**
	 * @see com.patternbox.tangocalendar.types.Entity#sameIdentityAs(java.lang.Object)
	 */
	@Override
	public boolean sameIdentityAs(TemplateHeader other) {
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
	 * Compose name from template events.
	 * 
	 * @return the template header name
	 */
	public String getName() {
		final String delimiter = " / ";
		StringBuilder result = new StringBuilder();
		for (TemplateEvent template : templates) {
			result.append(template.getEventName());
			result.append(delimiter);
		}
		// post processing
		if (result.length() > delimiter.length()) {
			result.setLength(result.length() - delimiter.length());
		} else {
			result.append("???");
		}
		return result.toString();
	}

	/**
	 * Applies the template definition and returns a list of created events.
	 * 
	 * @return a list of events
	 */
	public List<SingleEvent> applyTemplate() {
		return null;
	}
}
