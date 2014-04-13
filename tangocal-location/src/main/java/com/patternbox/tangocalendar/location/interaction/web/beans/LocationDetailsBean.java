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
package com.patternbox.tangocalendar.location.interaction.web.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.patternbox.tangocalendar.location.application.data.LocationData;
import com.patternbox.tangocalendar.location.infrastructure.persistence.JpaLocationFinder;

/**
 * @author <a href='http://www.patternbox.com'>D. Ehms, Patternbox</a>
 */
@Named("locationDetails")
// @RequestScoped
// @ConversationScoped
@SessionScoped
public class LocationDetailsBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private JpaLocationFinder locationFinder;

	private LocationData locationDto;

	private String action;

	// @ManagedProperty(value = "#{param.id}")
	private Long locationId;

	/**
	 * Find and cache location DTO.
	 */
	@PostConstruct
	private void onPostConstruct() {
		// FacesContext fc = FacesContext.getCurrentInstance();
		// String id = fc.getExternalContext().getRequestParameterMap().get("id");
		// if (id != null) {
		// locationDto = locationFinder.findLocation(Long.parseLong(id));
		// }
	}

	/**
	 * Return location data.
	 */
	LocationData getData() {
		if (locationDto == null && locationId != null) {
			locationDto = locationFinder.findLocation(locationId);
		}
		return locationDto;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getAction() {
		return action;
	}

	/**
	 * Return the locationId
	 */
	public Long getLocationId() {
		return locationId;
	}

	/**
	 * @param locationId
	 *          the locationId to set
	 */
	public void setLocationId(Long locationId) {
		this.locationId = locationId;
	}
}
