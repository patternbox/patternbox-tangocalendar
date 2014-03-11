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

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit test for {@link Coordinates}
 * 
 * @author <a href='http://www.patternbox.com'>D. Ehms, Patternbox</a>
 */
public class CoordinatesTest {

	/**
	 * Test method for {@link Coordinates#calcDistanceInKm(Coordinates)} .
	 */
	@Test
	public void testGetDistanceInKm() {
		Coordinates berlin = new Coordinates(52.5243700, 13.4105300);
		Coordinates hamburg = new Coordinates(53.5500000, 10.0000000);
		double distanceBH = berlin.calcDistanceInKm(hamburg);
		double distanceHB = hamburg.calcDistanceInKm(berlin);
		assertEquals("Same Distance: Berlin, Hamburg", distanceBH, distanceHB, 0.01);
		assertEquals("Distance: Berlin-Hamburg", 255.64, distanceBH, 1.0);
		assertEquals("Distance: Hamburg-Berlin", 255.64, distanceHB, 1.0);
	}
}
