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
package com.patternbox.tangocalendar.location.application.command;

import static org.junit.Assert.assertEquals;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.patternbox.tangocalendar.core.command.CommandService;
import com.patternbox.tangocalendar.location.application.handler.UpdateLocationAddressCmdHandler;

/**
 * @author <a href='http://www.patternbox.com'>D. Ehms, Patternbox</a>
 */
@RunWith(Arquillian.class)
public class UpdateLocationAddressITest {

	@Deployment
	public static JavaArchive createDeployment() {
		return ShrinkWrap.create(JavaArchive.class, "test.jar")
				.addPackages(true /* recursive */, "com.patternbox.tangocalendar.core")
				.addClasses(UpdateLocationAddressCmdHandler.class, UpdateLocationAddressCommand.class)
				.addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
	}

	@EJB
	CommandService cmdService;

	@Test
	public void update_location_address() {
		UpdateLocationAddressCommand cmd = new UpdateLocationAddressCommand();
		cmdService.execute(cmd);
		assertEquals("Hello, Earthling!", "Hello, Earthling!");
	}
}
