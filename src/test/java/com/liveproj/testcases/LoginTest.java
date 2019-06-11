package com.liveproj.testcases;

import java.util.Hashtable;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.liveproj.utilities.Utilities;
import com.liveproj.base.PageClass;
import com.liveproj.pages.HomePage;
import com.liveproj.pages.LoginPage;
import com.liveproj.pages.ZohoAppPage;

public class LoginTest extends PageClass {

	@Test(dataProviderClass = Utilities.class, dataProvider = "dp")
	public void loginTest(Hashtable<String,String> data) {
		HomePage home = new HomePage();
		LoginPage lp = home.gotologin();
		ZohoAppPage zp = lp.doLogin(data.get("username"),data.get("password"));

	}
}
