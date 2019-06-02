package org.testVagrant.pageobjects.HotelBooking;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testVagrant.core.GenericFunctions;

public class HotelBookingPage {

	WebDriver driver;
	GenericFunctions generic;
	WebDriverWait wait;
	
	public HotelBookingPage(WebDriver driver, GenericFunctions generic) {
		this.driver = driver;
		this.generic = generic;
		PageFactory.initElements(driver, this);
		wait = new WebDriverWait(driver, 20);
	}
	
	@FindBy(css="div[class=\"searchForm\"]>h1")
	WebElement searchForHotel_WE;
	
    @FindBy(id = "Tags")
    WebElement where_Input;
    
    @FindBy(id="CheckOutDate")
    WebElement checkOutDate;
    
    @FindBy(id="travellersOnhome")
    WebElement travellers_DD;
    
    @FindBy(id="SearchHotelsButton")
    WebElement searchHotel_Btn;
    
	By whereElementTag = By.id("ui-id-1");
	By dropdownOptionList = By.cssSelector("li[class=\"list\"]>a");
	
	public String getText_searchForHotel_WE() {
		return generic.GetText(searchForHotel_WE);
	}
	
	void fillText_where_Input(String location) {
		generic.Fill_Text(where_Input, location);
	}
	
	public void select_To_Location(String location) {
		fillText_where_Input(location);
		WebElement destinationElement = wait.until(ExpectedConditions.visibilityOf(driver.findElement(whereElementTag)));
		
		List<WebElement> originOptions = wait.until(ExpectedConditions.
				visibilityOfAllElements((destinationElement.findElements(dropdownOptionList))));
		
		for(WebElement element : originOptions) {
			if(element.getText().trim().contains(location.trim())) {
				generic.MouseHoverAndClick(element);;
			}
		}
	}
	
	public void select_checkInOutDate_WE(String ddmmyyyy) {
		String [] dateArr = ddmmyyyy.split("-");;
		//split function will split string into dd, mm, yyyy
		By dateList = By.xpath("//td[@data-month="+dateArr[1]+" and @data-year="+dateArr[2]+"]/a");
		
		List<WebElement> dateListWE = wait.until(ExpectedConditions.
				visibilityOfAllElements(generic.getElementList(dateList)));
		for(WebElement element : dateListWE) {
			try {
				if(element.getText().trim().equals(dateArr[0].trim())) {
					element.click();
					break;
				}
			}
			catch(Exception e) {
				select_checkInOutDate_WE(ddmmyyyy);
			}
			
		}
	}
	
	public void select_travellers_DD(String travellers) {
		generic.SelectDropDownByText(travellers_DD, travellers);
	}
	
	public void click_searchHotel_Btn() {
		generic.Click(searchHotel_Btn);
	}
	
	public void click_checkOutDate() {
		generic.Click(checkOutDate);
	}
}
