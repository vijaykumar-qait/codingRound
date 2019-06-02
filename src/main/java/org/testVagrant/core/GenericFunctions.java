package org.testVagrant.core;

import java.io.File;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testVagrant.utils.Log;

/*
 * This is generic class where the initialization of the driver depending upon the system we are using along with
 * containing all the common method that we will be using in our page object classes.
 */

public class GenericFunctions extends BaseDriver{
	
	String browerType;
	
	public boolean isWindows(String OS) {
		return (OS.contains("win"));
	}
	
	public boolean isMac(String OS) {
		return (OS.contains("mac"));
	}
	
	public boolean isUnix(String OS) {
		return (OS.contains("nix") || OS.contains("nux") || OS.contains("aix"));
	}
	
	/*
	 * StartDriver take browertype as parameter depending on which script will execute on specified browser 
	 */
	public WebDriver StartDriver(String browserType) {
		this.browerType = browserType;
		String systemType = System.getProperty("os.name").toLowerCase();
		
		System.out.println("System type: " + systemType);
		
		if(isWindows(systemType)) {
			if(browserType.toLowerCase().equals("chrome")) {
				System.setProperty("webdriver.chrome.driver", "." + File.separator + "Resources" + 
						File.separator + "drivers" + File.separator+ "windows" + File.separator + "chromedriver" + File.separator + "chromedriver.exe");
				this.driver = new ChromeDriver();
				
			}
			else if(browserType.toLowerCase().equals("firefox")) {
				System.setProperty("webdriver.gecko.driver", "." + File.separator + "Resources" + 
						File.separator + "drivers" + File.separator + "windows" + File.separator +"ffdriver" + File.separator + "geckodriver.exe");
				
				this.driver = new FirefoxDriver();
			}
		}
		else if (isMac(systemType)) {
			if(browserType.toLowerCase().equals("chrome")) {
				System.setProperty("webdriver.chrome.driver", "." + File.separator + "Resources" + 
						File.separator + "drivers" + File.separator+ "mac" + File.separator + "chromedriver" + File.separator + "chromedriver.exe");
				this.driver = new ChromeDriver();
				
			}
			else if(browserType.toLowerCase().equals("firefox")) {
				System.setProperty("webdriver.gecko.driver", "." + File.separator + "Resources" + 
						File.separator + "drivers" + File.separator + "mac" + File.separator +"ffdriver" + File.separator + "geckodriver.exe");
				
				this.driver = new FirefoxDriver();
			}
		}
		else if(isUnix(systemType)) {
			if(browserType.toLowerCase().equals("chrome")) {
				System.setProperty("webdriver.chrome.driver", "." + File.separator + "Resources" + 
						File.separator + "drivers" + File.separator+ "linux" + File.separator + "chromedriver" + File.separator + "chromedriver.exe");
				this.driver = new ChromeDriver();
				
			}
			else if(browserType.toLowerCase().equals("firefox")) {
				System.setProperty("webdriver.gecko.driver", "." + File.separator + "Resources" + 
						File.separator + "drivers" + File.separator + "linux" + File.separator +"ffdriver" + File.separator + "geckodriver.exe");
				
				this.driver = new FirefoxDriver();
			}
		}
		else {
			System.out.println("Your OS is not supported please add if condition and "
					+ "selenium browser driver");
		}

		return this.driver;
	}
	
	/*
	 * Maximize browser window and navigate to URL we are getting from requirement.config file
	 */
	public void get(String url) {
		driver.manage().window().maximize();
		driver.get(url);
		Log.info("Get: " + url) ;
	}
	
	/*
	 * Closing down of browser
	 */
	public void closeBrowser() {
		driver.quit();
		Log.info("Browser closed");
	}
	
	/*
	 * isVisible method is overloaded with By or WebElement locator to return boolean result if that element is visible on DOM
	 */
	public boolean isVisible(By locator) {
		return driver.findElement(locator).isDisplayed();
	}
	
	public boolean isVisible(WebElement locator) {
		return locator.isDisplayed();
	}
	
	/*
	 * Click method is overloaded with By or WebElement locator to perform click operation on element
	 */
	public void Click(By locator) {
		driver.findElement(locator).click();
		Log.info("Element is clicked");
	}
	
	public void Click(WebElement locator) {
		locator.click();
		Log.info("Element is clicked" );
	}
	
	/*
	 * MouserHoverAndClick method is overloaded with By or WebElement locator to perform mouse hover to that element and
	 * then performing click operation
	 */
	public void MouseHoverAndClick(By locator) {
		Actions actions = new Actions(driver);
		actions.moveToElement(driver.findElement(locator));
		actions.click().build().perform();
	}
	
	public void MouseHoverAndClick(WebElement locator) {
		Actions actions = new Actions(driver);
		actions.moveToElement(locator);
		actions.click().build().perform();
	}
	
	/*
	 * Fill_Text method is overloaded with By or WebElement locator to fill text
	 */
	public void Fill_Text(By locator, String value) {
		WebElement element = driver.findElement(locator);
		this.Click(locator);
		element.sendKeys(value);
		Log.info("Filled text " + value);
	}

	public void Fill_Text(WebElement locator, String value) {
		locator.click();
		locator.sendKeys(value);
		Log.info("Filled text " + value);
	}
	
	/*
	 * Mouse_Hover method is overloaded with By or WebElement locator to perform mouse hover action to specified locator
	 */
	
	public void Mouse_Hover(By locator) {
		Actions actions = new Actions(driver);
		WebElement element = driver.findElement(locator);
		actions.moveToElement(element);
		actions.build().perform();
		Log.info("Mouse hover to element");
	}
	
	public void Mouse_Hover(WebElement locator) {
		Actions action = new Actions(driver);
		action.moveToElement(locator);
		action.build().perform();
		Log.info("Mouse hover to element");
	}
	
	/*
	 * GetText method is overloaded with By or WebElement locator to fetch text of specified web element
	 */
	public String GetText(By locator) {
		return driver.findElement(locator).getText().trim();
	}
	
	public String GetText(WebElement locator) {
		return locator.getText();
	}
	
	/*
	 * SelectDropDownByText to select drop-down with visible text passed as paramter
	 */
	public void SelectDropDownByText(WebElement locator, String byText) {
		Select select = new Select(locator);
		select.selectByVisibleText(byText);
		Log.info("Selected drop down by text: " + byText);
	}
	
	/*
	 * SwitchToFrame to switch to frame with given By or WebElement as parameter
	 */
	public void SwitchToFrame(By locator) {
		WebElement frame = driver.findElement(locator);
		try {
			driver.switchTo().frame(frame);
			Log.info("Switched to frame");
		}
		catch(NoSuchFrameException e) {
			e.printStackTrace();
			Log.error("Frame not found");
		}
	}
	
	public void SwitchToFrame(WebElement locator) {
		try {
			driver.switchTo().frame(locator);
			Log.info("Switched to frame");
		}
		catch(NoSuchFrameException e) {
			e.printStackTrace();
			Log.error("Frame not found");
		}
	}
	
	/*
	 * getElementList return list of all the webElement
	 */
	public List<WebElement> getElementList(By locator) {
		return driver.findElements(locator);
	}
}
