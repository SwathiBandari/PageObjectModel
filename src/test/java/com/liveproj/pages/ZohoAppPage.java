package com.liveproj.pages;

import com.liveproj.base.PageClass;
import com.liveproj.pages.crm.CRMHomePage;

public class ZohoAppPage extends PageClass {
	

	public void gotocliq() {
		click("gotocliq_CSS");
	}
	
	public CRMHomePage gotoCRM() {
		click("gotoCRM_CSS");
		return new CRMHomePage();
		
	}
	public void gotoMeeting() {
		click("gotoMeeting_CSS");
	}
}
