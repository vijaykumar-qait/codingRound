package org.testVagrant.pageobjects.FlightBooking;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.crypto.ExemptionMechanismException;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testVagrant.core.GenericFunctions;

public class FlightPage {
	
	WebDriver driver;
	GenericFunctions generic;
	WebDriverWait wait;
	Wait wait1;
	
	public FlightPage(WebDriver driver, GenericFunctions generic) {
		this.driver = driver;
		this.generic = generic;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 20);
	}
	
	@FindBy(id="OneWay")
	WebElement oneWay_RdBtn;
	
	@FindBy(id="FromTag")
	WebElement from_Input;
	
	@FindBy(id="ToTag")
	WebElement to_Input;
	
	@FindBy(id="DepartDate")
	WebElement departDate;
	
	@FindBy(id="SearchBtn")
	WebElement searchBtn;
	
	By originElementTag = By.id("ui-id-1");
	By destinationElementTag = By.id("ui-id-2");
	By dropdownOptionList = By.cssSelector("li[class=\"list\"]>a");
	
	public void click_oneWay_RdBtn() {
		generic.Click(oneWay_RdBtn);
	}
	
	void fillText_from_Input(String location) {
		generic.Fill_Text(from_Input, location);
	}
	
	public void select_From_Location(String location) {
		fillText_from_Input(location);
		WebElement originElement = wait.until(ExpectedConditions.visibilityOf(driver.findElement(originElementTag)));
		
		List<WebElement> originOptions = wait.until(ExpectedConditions.
				visibilityOfAllElements((originElement.findElements(dropdownOptionList))));
		
		for(WebElement element : originOptions) {
			if(element.getText().trim().contains(location.trim())) {
				generic.MouseHoverAndClick(element);;
			}
		}
	}
	
	void fillText_to_Input(String location) {
		generic.Fill_Text(to_Input, location);
	}
	
	public void select_To_Location(String location) {
		fillText_to_Input(location);
		WebElement destinationElement = wait.until(ExpectedConditions.visibilityOf(driver.findElement(destinationElementTag)));
		
		List<WebElement> originOptions = wait.until(ExpectedConditions.
				visibilityOfAllElements((destinationElement.findElements(dropdownOptionList))));
		
		for(WebElement element : originOptions) {
			if(element.getText().trim().contains(location.trim())) {
				generic.MouseHoverAndClick(element);;
			}
		}
	}
	
	void click_departDate() {
		generic.Click(departDate);
	}
	
	public void select_Date_WE(String ddmmyyyy) {
		click_departDate();
		String [] dateArr = ddmmyyyy.split("-");;
		//split fucntion will split string into dd, mm, yyyy
		By dateList = By.xpath("//td[@data-month="+dateArr[1]+" and @data-year="+dateArr[2]+"]/a");
		
		List<WebElement> dateListWE = wait.until(ExpectedConditions.
				visibilityOfAllElements(generic.getElementList(dateList)));
		for(WebElement element : dateListWE) {
			if(element.getText().trim().equals(dateArr[0].trim())) {
				element.click();
				break;
			}
		}
	}
	
	public void click_searchBtn() {
		generic.Click(searchBtn);
	}
	
/*	String getCurrentDay() {
		//Create calendar object
		Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
		
		//Get current day as number
		int todayInt = calendar.get(Calendar.DAY_OF_MONTH);
		System.out.println("Today int: " + todayInt);
		String todayStr = Integer.toString(todayInt);
		return todayStr;
	}*/

}
