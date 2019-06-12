package com.liveproj.listeners;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import com.liveproj.base.PageClass;
import com.liveproj.utilities.MonitoringMail;
import com.liveproj.utilities.TestConfig;
import com.liveproj.utilities.Utilities;
import com.relevantcodes.extentreports.LogStatus;

public class CustomListeners extends PageClass implements ITestListener, ISuiteListener{
           
	public String messagebody;
	public void onTestStart(ITestResult result) 
	{
		// TODO Auto-generated method stub  
		Test=rep.startTest(result.getName().toUpperCase());
	}
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		
		//EXTENT REPORt
		
		PageClass.Test.log(LogStatus.PASS, result.getName().toUpperCase()+"PASS");
		
		//REPORTNG REPORT
		System.setProperty("org.uncommons.reportng.escape-output", "false");		
		try {
			Utilities.Capturescreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(Utilities.ScreenShotName);
		Reporter.log(Utilities.ScreenShotName);
	    //Creates a link for the screenshot and opens screenshot in a NEW tab in the report
	    Reporter.log("<a href="+Utilities.ScreenShotName+">Screenshot</a>");
	    //CREATES A THUMBNAIL OF THE SCREENSHOT
	    Reporter.log("<br>");
	   Reporter.log("<a target=\"_blank\" href="+Utilities.ScreenShotName+"><img src="+Utilities.ScreenShotName+" height=200 width=200></img></a>");
	   rep.endTest(Test);
		rep.flush();
	}

	

	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
System.setProperty("org.uncommons.reportng.escape-output", "false");
		
		try {
			Utilities.Capturescreenshot();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PageClass.Test.log(LogStatus.FAIL, result.getName().toUpperCase()+"FAILED WITH EXCEPTIOn: "+ result.getThrowable());
		Utilities.Test.log(LogStatus.FAIL, Test.addScreenCapture(Utilities.ScreenShotName));
			
		System.out.println(Utilities.ScreenShotName);
		Reporter.log(Utilities.ScreenShotName);
	    //Creates a link for the screenshot and opens screenshot in a NEW tab in the report
	    Reporter.log("<a href="+Utilities.ScreenShotName+">Screenshot</a>");
	    //CREATES A THUMBNAIL OF THE SCREENSHOT
	    Reporter.log("<br>");
	   Reporter.log("<a target=\"_blank\" href="+Utilities.ScreenShotName+"><img src="+Utilities.ScreenShotName+" height=200 width=200></img></a>");
		rep.endTest(Test);
		rep.flush();
	}

	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		
		Test.log(LogStatus.SKIP, result.getName().toUpperCase()+ " Skipped the test as the runmode test is NO ");
		rep.endTest(Test);
		rep.flush();
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}
	
	
	@Override
	public void onFinish(ISuite suite) {

		MonitoringMail mail = new MonitoringMail();
	
		try {
			messagebody = "http://"+InetAddress.getLocalHost().getHostAddress()+ ":8080/job/PageObjectModelLiveProject/Extent_20Report/";
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			mail.sendMail(TestConfig.server, TestConfig.from, TestConfig.to, TestConfig.subject, messagebody);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	}

}
	@Override
	public void onStart(ISuite suite) {
		// TODO Auto-generated method stub
		
	}}
