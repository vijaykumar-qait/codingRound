package org.testVagrant.core;

import org.openqa.selenium.WebDriver;
/*
 * This is base driver class being extended by test, generic and other utility class
 */
public class BaseDriver {
	public WebDriver driver;
	
	public WebDriver getDriver() {
		return driver;
	}
}
