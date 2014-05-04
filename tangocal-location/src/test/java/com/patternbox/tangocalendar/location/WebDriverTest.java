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
package com.patternbox.tangocalendar.location;

import java.util.Arrays;
import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * @author <a href='http://www.patternbox.com'>D. Ehms, Patternbox</a>
 */
// @RunWith(Parameterized.class)
public class WebDriverTest {

	WebDriver driver;

	private String searchTxt;

	public void test2(String searchText) {
		this.searchTxt = searchText;
	}

	// @Parameters
	public static Collection<Object[]> data() {
		Object[][] data = new Object[][] { { "1" }, { "JU" }, { "JUnit Parallel" } };
		return Arrays.asList(data);
	}

	@Before
	public void setUp() {
		driver = new FirefoxDriver();
	}

	@After
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void testFindElements() throws Exception {
		// driver.get("http://www.google.com");
		driver.get("http://localhost:8080/location/");
		Thread.sleep(500);
		WebElement link = driver.findElement(By.name("id_create_location"));
		link.click();
		// searchArea.sendKeys(searchTxt);
		// Thread.sleep(500);
		// WebElement searchButton = driver.findElement(By.name("btnG"));
		// searchButton.click();
		// Thread.sleep(500);
		// String pageSource = driver.getPageSource();
		// Assert.assertTrue(pageSource.contains(searchTxt));
		Thread.sleep(2000);
	}
}
