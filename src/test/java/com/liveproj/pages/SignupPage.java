package com.liveproj.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.liveproj.base.PageClass;

public class SignupPage extends PageClass{
	
	public NewUserHomePage signup(String signupemail, String signuppassword) throws InterruptedException  {
		
		type("SignupEmail_CSS", signupemail);
		type("SignupPassword_CSS",signuppassword);
		wait = new WebDriverWait(driver,5);
		wait.until(ExpectedConditions.elementToBeSelected(By.cssSelector(OR.getProperty("AgreeTerms_CSS"))));
		click("AgreeTerms_CSS");	
		click("SignupButton_CSS");
		return new NewUserHomePage();
		
	}}