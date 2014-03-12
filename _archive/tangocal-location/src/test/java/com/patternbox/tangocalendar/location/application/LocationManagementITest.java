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
package com.patternbox.tangocalendar.location.application;

import static org.junit.Assert.assertNotNull;

import javax.ejb.EJB;
import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.ArchivePath;
import org.jboss.shrinkwrap.api.Filter;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.patternbox.tangocalendar.core.command.CommandService;
import com.patternbox.tangocalendar.location.application.command.CreateLocationCommand;
import com.patternbox.tangocalendar.location.domain.model.location.Address;
import com.patternbox.tangocalendar.location.domain.model.location.Location;
import com.patternbox.tangocalendar.location.domain.model.location.LocationAddressUpdated;
import com.patternbox.tangocalendar.location.domain.model.location.LocationRepository;

/**
 * @author <a href='http://www.patternbox.com'>D. Ehms, Patternbox</a>
 */
@RunWith(Arquillian.class)
public class LocationManagementITest {

	@EJB
	CommandService cmdService;

	@Inject
	BeanManager beanManager;

	@Inject
	private LocationRepository repository;

	@Inject
	private CdiEventListener cdiEventListener;

	@Deployment
	public static WebArchive createDeployment() {
		// filter test classes to avoid ClassNotFoundExceptions
		Filter<ArchivePath> filter = new Filter<ArchivePath>() {

			@Override
			public boolean include(ArchivePath archivePath) {
				String fullPath = archivePath.get();
				int idx = fullPath.lastIndexOf(ArchivePath.SEPARATOR);
				String className = fullPath.substring(idx);
				boolean isTestClass = className.contains("Test");
				return !isTestClass;
			}
		};
		// create web archive
		return ShrinkWrap.create(WebArchive.class, "test.war")
				.addPackages(true /* recursive */, "com.patternbox.tangocalendar.core")
				.addPackages(true /* recursive */, filter, "com.patternbox.tangocalendar.location")
				.addAsResource("test-persistence.xml", "META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@Before
	public void setUp() throws Exception {
		assertNotNull(cdiEventListener);
		assertNotNull(beanManager);
		// beanManager = Mockito.spy(beanManager);
		// MockitoAnnotations.initMocks(this);
		// BeanManagerProvider bmpMock = mock(BeanManagerProvider.class);
		// Field bmpField = BeanManagerProvider.class.getDeclaredField("bmpSingleton");
		// bmpField.setAccessible(true);
		// bmpField.set(null, bmpMock);
		// doReturn(beanManagerMock).when(bmpMock).getBeanManager();
	}

	@Test
	@InSequence(1)
	public void testCreateLocation() {
		final String name = "My Location";
		final Address address = new Address("DE", "Berlin", "Berlin", "12345", "My Street 12");
		CreateLocationCommand cmd = new CreateLocationCommand(name, address);
		Long id = (Long) cmdService.execute(cmd);
		assertNotNull("Location identifier is null", id);
		Location location = repository.findLocation(id);
		assertNotNull("Location is null", location);
	}

	@Test
	@Ignore
	public void location_address_updated() {
		assertNotNull(beanManager);
		LocationAddressUpdated event = new LocationAddressUpdated(1L, null);
		beanManager.fireEvent(event);
	}
}
