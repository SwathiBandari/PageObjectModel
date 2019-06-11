package com.liveproj.pages.crm.accounts;

import com.liveproj.base.PageClass;

public class AccountsPage extends PageClass{
	
	public CreateAccountPage gotocreateaccount() {
		
		PageClass.click("CreateAccountsButton_CSS");
		return new CreateAccountPage();
		
		
	}

	public void gotoimportaccounts() {
		
	}
}
