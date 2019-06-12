package com.liveproj.testcases;

import com.liveproj.base.PageClass;
import com.liveproj.pages.ProfilePage;

public class LogoutTest{

	@Test
	public void logoutTest() {
		
		ProfilePage pp = PageClass.menu.Profile();
		pp.Signout();
			
		
	}
}
