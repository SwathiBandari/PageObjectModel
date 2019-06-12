package com.liveproj.testcases;

import java.util.Hashtable;

import org.testng.annotations.Test;

import com.liveproj.base.PageClass;
import com.liveproj.pages.ZohoAppPage;
import com.liveproj.pages.crm.accounts.AccountsPage;
import com.liveproj.pages.crm.accounts.CreateAccountPage;
import com.liveproj.utilities.Utilities;

public class CreateAccountTest {

	@Test(dataProviderClass = Utilities.class, dataProvider = "dp")
		public void createAccountTest(Hashtable<String,String> data) {
			ZohoAppPage ZP=new ZohoAppPage();
			ZP.gotoCRM();
			AccountsPage account = PageClass.menu.gotoAccounts();
			CreateAccountPage cap = account.gotocreateaccount();
			cap.createaccount(data.get("accountname"));	
			
			
	}
}
