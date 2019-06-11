package com.liveproj.pages;

import com.liveproj.base.PageClass;

public class LoginPage extends PageClass {

	public ZohoAppPage doLogin(String username, String password) {
		type("Email_CSS", username);
		type("Password_CSS", password);
		click("SigninButton_CSS");
		return new ZohoAppPage();
	}
}
