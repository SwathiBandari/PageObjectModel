package com.liveproj.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import com.liveproj.pages.ProfilePage;
import com.liveproj.pages.crm.accounts.AccountsPage;

public class TopMenu {

	WebDriver driver;
	public TopMenu(WebDriver driver) {
		this.driver=driver;
		
	}
	public void gotoHome() {

	}
public ProfilePage Profile() {
	PageClass.click("profile_CSS");
	Reporter.log("clicked on Profile");
	
	return new ProfilePage();
	
	
}
	public void gotoLeads() {
//PageClass.driver.findElement(By.cssSelector(""));
		driver.findElement(By.cssSelector(""));
	}

	public void gotoContacts() {

	}

	public AccountsPage gotoAccounts() {
PageClass.click("AccountsTab_CSS");
		return new AccountsPage();

	}

	public void gotoDeals() {

	}

	public void gotoActivities() {

	}
	
	public void gotoReports() {

	}
	public void gotoAnalytics() {

	}
	public void gotoproducts() {

	}
	public void gotoQuotes() {

	}
	public void gotoProjects() {

	}
}
