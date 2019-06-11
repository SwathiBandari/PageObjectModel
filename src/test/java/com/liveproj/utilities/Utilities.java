package com.liveproj.utilities;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Hashtable;
import org.apache.maven.surefire.shade.common.org.apache.maven.shared.utils.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.annotations.DataProvider;

import com.liveproj.base.PageClass;


public class Utilities extends PageClass {
	public static String ScreenshotPath;
	public static String ScreenShotName;
	
	public static void Capturescreenshot() throws IOException {
		
	File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	Date d = new Date();
	ScreenShotName = d.toString().replace(":","_").replace(" ", "_")+".jpg";
	System.out.println(ScreenShotName);
		   
	FileUtils.copyFile(srcFile, new File("target\\surefire-reports\\html\\"+ScreenShotName));
	try {
		Thread.sleep(3000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	System.out.println(ScreenShotName);
	}
	
	
	
	@DataProvider(name="dp")
	public static Object[] getdata(Method m) throws IOException {
		String sheetName=m.getName();
		int rows = excel.getRowCount(sheetName);
		int cols = excel.getColumnCount(sheetName);
		Object[][] custdata = new Object[rows - 1][1];
		Hashtable<String, String> table = null;

		for (int rownum = 2; rownum <= excel.getRowCount(sheetName); rownum++) {
			table = new Hashtable<String, String>();
			for (int colnum = 0; colnum < cols; colnum++) {
				table.put(excel.getCellData(sheetName, colnum, 1), excel.getCellData(sheetName, colnum, rownum));
				custdata[rownum - 2][0] = table;
	}

}
		return custdata;
}}