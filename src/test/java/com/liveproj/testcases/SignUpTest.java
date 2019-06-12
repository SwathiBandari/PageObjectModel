package com.liveproj.testcases;

import java.util.Hashtable;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.liveproj.base.PageClass;
import com.liveproj.pages.HomePage;
import com.liveproj.pages.SignupPage;
import com.liveproj.utilities.Utilities;
import com.relevantcodes.extentreports.LogStatus;

public class SignUpTest extends PageClass{

	@Test(dataProviderClass = Utilities.class, dataProvider = "dp")
	public void signUpTest(Hashtable<String,String> data) throws Throwable {
		HomePage home = new HomePage();
		SignupPage Sp = home.gotoSignup();
		Sp.signup(data.get("signupemail"),data.get("signuppassword"));
		Assert.assertTrue(isElementpresent(By.cssSelector(OR.getProperty("TryProducts_CSS"))));
		Test.log(LogStatus.INFO, "User created succesfully and navigated to "+ driver.getTitle() + "Page");
				
	}
}
