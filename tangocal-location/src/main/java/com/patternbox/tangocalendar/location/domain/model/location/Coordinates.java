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
package com.patternbox.tangocalendar.location.domain.model.location;

import javax.persistence.Embeddable;

import com.patternbox.tangocalendar.core.types.ValueObject;

/**
 * Location coordinates DDD value object.
 * 
 * @author <a href='http://www.patternbox.com'>D. Ehms, Patternbox</a>
 */
@Embeddable
@SuppressWarnings("serial")
public class Coordinates implements ValueObject<Coordinates> {

	/**
	 * Earth radius in Kilometers
	 */
	private static final double EARTH_RADIUS = 6371.00;

	private Double latitude;

	private Double longitude;

	/**
	 * Default constructor to satisfy JPA.
	 */
	public Coordinates() {
		super();
	}

	/**
	 * Parameterized constructor.
	 */
	public Coordinates(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	/**
	 * Returns the distance between two geographical locations in Kilometers using the Haversine
	 * Distance Algorithm.
	 * 
	 * @param geoPos
	 *          the other GeoPosition
	 * @return the distance
	 */
	public double calcDistanceInKm(Coordinates geoPos) {
		double lat1 = this.latitude;
		double lat2 = geoPos.latitude;
		double lon1 = this.longitude;
		double lon2 = geoPos.longitude;
		double latDistance = toRad(lat2 - lat1);
		double lonDistance = toRad(lon2 - lon1);
		double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2) + Math.cos(toRad(lat1))
				* Math.cos(toRad(lat2)) * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		return EARTH_RADIUS * c;
	}

	private static Double toRad(Double value) {
		return value * Math.PI / 180;
	}

	/**
	 * @see com.patternbox.tangocalendar.core.types.ValueObject#sameValueAs(java.lang.Object)
	 */
	@Override
	public boolean sameValueAs(Coordinates other) {
		// TODO Auto-generated method stub
		return false;
	}
}
