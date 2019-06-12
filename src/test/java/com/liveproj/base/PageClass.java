package com.liveproj.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import com.liveproj.utilities.ExtentManager;
import com.liveproj.utilities.Utilities;
import com.liveproj.utilities.Xls_Reader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class PageClass {
	public static WebDriver driver;
	public static TopMenu menu;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoyLogger");

	
	public static Xls_Reader excel= new Xls_Reader("src/test/resources/com/liveproj/excel/testdata.xlsx");
	public WebDriverWait wait;
	public ExtentReports rep = ExtentManager.getInstance();
	public static ExtentTest Test;
	public static String browser;
	
	//update using Eclipse to Github adding more comments
	

	public PageClass() {
		if (driver == null) {
			try {
				fis = new FileInputStream("src/test/resources/com/liveproj/properties/Config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				config.load(fis);
				log.debug("config file loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				fis = new FileInputStream("src/test/resources/com/liveproj/properties/OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				OR.load(fis);
				log.debug("OR file loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		//Jenkins browser filter configurations
		if (System.getenv("browser") != null && !System.getenv("browser").isEmpty()) {
			browser = System.getenv("browser");
		} else {
			browser = config.getProperty("browser");
		}

		config.setProperty("browser", browser);
		if (config.getProperty("browser").equals("firefox")) {

			System.setProperty("webdriver.gecko.driver",System.getProperty("user.dir") + "\\src\\test\\resources\\com\\liveproj\\executables\\geckodriver.exe");
			FirefoxOptions options = new FirefoxOptions();
			options.addPreference("app.update.enabled", false);
			options.addPreference("geo.enabled", false);
			options.addPreference("dom.webnotifications.enabled", false);	
			driver = new FirefoxDriver(options);

		} else if (config.getProperty("browser").equals("chrome")) {
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir") + "\\src\\test\\resources\\com\\liveproj\\executables\\chromedriver.exe");
			Map<String, Object> prefs = new HashMap<String, Object>();
			prefs.put("profile.default_content_setting_values.notifications", 2);
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("prefs", prefs);
			options.addArguments("--disable-extensions");
			options.addArguments("--disable-infobars");
			driver = new ChromeDriver(options);
			log.debug("Chrome Launched !!!");    
		} 
			else if (config.getProperty("browser").equals("ie")) 
		{  
			System.setProperty("webdriver.ie.driver",System.getProperty("user.dir") + "\\src\\test\\resources\\com\\liveproj\\executables\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
		}
		
			driver.get(config.getProperty("testsiteurl"));
			log.debug("Navigated to:" + config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit.wait")),TimeUnit.SECONDS);
			wait = new WebDriverWait(driver,7);
			
			menu = new TopMenu(driver);

		}}
	
	public static void click(String Locator) {

		if (Locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(Locator))).click();
		} else if (Locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(Locator))).click();
		} else if (Locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(Locator))).click();
		}
			Test.log(LogStatus.INFO, "Clicking on : " + Locator);
		log.debug("Clicking on an Element : " + Locator);
	}
    
	public static void type(String Locator, String value) {
		if (Locator.endsWith("_CSS")) {
			driver.findElement(By.cssSelector(OR.getProperty(Locator))).sendKeys(value);
		} else if (Locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(Locator))).sendKeys(value);
		} else if (Locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(Locator))).sendKeys(value);
		}
		Test.log(LogStatus.INFO, "Typing in :" + Locator + " keyed value as : " + value);
		log.debug("Typing in an Element: " +Locator+ "Value of :" + value);
	}

	static WebElement dropdown;

	public void select(String Locator, String value) throws InterruptedException, IOException {
		if (Locator.endsWith("_CSS")) {
			dropdown = driver.findElement(By.cssSelector(OR.getProperty(Locator)));
		} else if (Locator.endsWith("_XPATH")) {
			dropdown = driver.findElement(By.xpath(OR.getProperty(Locator)));
		} else if (Locator.endsWith("_ID")) {
			dropdown = driver.findElement(By.id(OR.getProperty(Locator)));
		}
		Select select = new Select(dropdown);
		List<WebElement> values = select.getOptions();
		for (int i = 0; i < values.size(); i++) {
			System.out.println(values.get(i).getText());
		}
		select.selectByVisibleText(value);
		Test.log(LogStatus.INFO, " Selecting from dropdown: " + Locator + " Value as " + value);
	}

	public static void verifyEquals(String expected, String actual) throws IOException {

		try {
			Assert.assertEquals(actual, expected);
		}
		catch (Throwable t) 
		
		{
			Utilities.Capturescreenshot();
			// ReportNG Logging
			Reporter.log("<br>" + "Verification Failure:" + t.getMessage() + "<br>");
			Reporter.log("<a target=\"_blank\" href=" + Utilities.ScreenShotName + "><img src="
					+ Utilities.ScreenShotName + " height=200 width=200></img></a>");
			Reporter.log("<br>");
			// EXTENT REPORTS
			PageClass.Test.log(LogStatus.FAIL, "Verification failed with exception : " + t.getMessage());
			PageClass.Test.log(LogStatus.FAIL, Test.addScreenCapture(Utilities.ScreenShotName));
		}
	}
	
	public boolean isElementpresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;

		}}
	
	public static void quit() {
		driver.quit();
		
	
	
		
			
		}
	}
	
