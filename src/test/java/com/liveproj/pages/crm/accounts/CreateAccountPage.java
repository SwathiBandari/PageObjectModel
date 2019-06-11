package com.liveproj.pages.crm.accounts;

import com.liveproj.base.PageClass;

public class CreateAccountPage extends PageClass {
	
	public void createaccount(String accountName) {
		
		type("accountname_ID",accountName);
		
	}

}
