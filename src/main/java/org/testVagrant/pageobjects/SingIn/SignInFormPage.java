package org.testVagrant.pageobjects.SingIn;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testVagrant.core.GenericFunctions;

/*
 * SignInFormPage class contains all the action methods regarding SignIn page
 */
public class SignInFormPage {
	
	WebDriver driver;
	GenericFunctions generic;
	
	public SignInFormPage(WebDriver driver, GenericFunctions generic) {
		this.driver = driver;
		this.generic = generic;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="email")
	WebElement email_Input;
	
	@FindBy(id="password")
	WebElement password_Input;
	
	@FindBy(id="modal_window")
	WebElement modalWindow_Frame;
	
	@FindBy(id="signInButton")
	WebElement loginSingIn_Btn;
	
	@FindBy(css="div[id=\"errors1\"]>span")
	WebElement errorMessage_WE;
	
	public void switchToFrame_modalWindow_Frame() {
		generic.SwitchToFrame(modalWindow_Frame);
	}
	
	public boolean isDisplayed_email_Input() {
		switchToFrame_modalWindow_Frame();
		return generic.isVisible(email_Input);
	}
	
	public boolean isDisplayed_password_Input() {
		return generic.isVisible(password_Input);
	}
	
	public void click_loginSingIn_Btn() {
		generic.Click(loginSingIn_Btn);
	}
	
	public String getText_errorMessage_WE() {
		return generic.GetText(errorMessage_WE);
	}

}
