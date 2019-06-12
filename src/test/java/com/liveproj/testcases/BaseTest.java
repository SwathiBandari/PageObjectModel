package com.liveproj.testcases;

import org.testng.annotations.AfterSuite;
import com.liveproj.base.PageClass;

public class BaseTest {
	
	@AfterSuite
	public void teardown() {
		PageClass.quit();
	
	}

}
