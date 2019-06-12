package com.liveproj.pages;

import com.liveproj.base.PageClass;

public class HomePage extends PageClass {

	public SignupPage gotoSignup() {
		click("Signup_CSS");
		return new SignupPage();
	}

	public LoginPage gotologin() {
		click("Signin_CSS");
		return new LoginPage();
	}

	public void gotosupport() {
		click("Support_CSS");
	}

	public void gotoContactsales() {
		click("contactSales_CSS");
	}

	public void gotocustomers() {
		click("Customers_CSS");
	}
}
