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
package com.patternbox.tangocalendar.location.test.infrastructure.persistence;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.patternbox.tangocalendar.location.domain.model.location.Location;
import com.patternbox.tangocalendar.location.infrastructure.persistence.JpaLocationRepository;

import de.akquinet.jbosscc.needle.annotation.ObjectUnderTest;
import de.akquinet.jbosscc.needle.db.transaction.VoidRunnable;
import de.akquinet.jbosscc.needle.junit.DatabaseRule;
import de.akquinet.jbosscc.needle.junit.NeedleRule;

/**
 * Unit test for {@link JpaLocationRepository}
 * 
 * @author <a href='http://www.patternbox.com'>D. Ehms, Patternbox</a>
 */
public class LocationRepositoryITest {

	@Rule
	public DatabaseRule databaseRule = new DatabaseRule();

	@Rule
	public NeedleRule needleRule = new NeedleRule(databaseRule);

	/** The @ObjectUnderTest annotation instantiates a real object (no mock!!!) */
	@ObjectUnderTest
	JpaLocationRepository repository;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test method for {@link JpaLocationRepository#storeLocation(Location)}.
	 */
	@Test
	public void testStoreLocation() throws Exception {
		final String locationName = "My Location";
		//
		databaseRule.getTransactionHelper().executeInTransaction(new VoidRunnable() {

			@Override
			public void doRun(EntityManager entityManager) throws Exception {
				Location location = new Location(locationName);
				assertNull("Location identifier is not null", location.getIdentifer());
				repository.storeLocation(location);
				assertNotNull("Location identifier is null", location.getIdentifer());
			}
		});
		// find new location
		List<String> names = repository.locationNames();
		assertNotNull(names);
		assertTrue(names.contains(locationName));
	}

	/**
	 * Test method for
	 * {@link com.patternbox.tangocalendar.location.logic.LocationService#getLocations()}.
	 */
	@Test
	public void testGetLocationNames() {
		List<String> names = repository.locationNames();
		assertNotNull(names);
		// Mockito.verify(repository, Mockito.atLeastOnce()).getLocationNames();
	}
}
