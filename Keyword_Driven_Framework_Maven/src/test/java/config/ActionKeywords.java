package config;

import static executionEngine.DriverScriptReflection.OR;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import executionEngine.DriverScriptReflection;
import utility.Log;
import utility.TakeScreenshotMethod;

public class ActionKeywords 
{
	public static WebDriver driver;
	
	//All the methods in this class now accept 'Object' name as an argument
	public static void openBrowser(String object, String data) throws Exception
	{
		try
		{	
			//If value of the parameter is Mozilla, this will execute
			Log.info("Opening Browser");
			if(data.equals("Mozilla"))
			{
				
				System.setProperty("webdriver.gecko.driver", "C:\\Users\\sailakshmi.p\\Desktop\\geckodriver.exe");
				driver = new FirefoxDriver();
			}
			else if(data.equals("IE"))
			{
				//You may need to change the code here to start IE Driver
				driver=new InternetExplorerDriver();
				Log.info("IE browser started");
			}
			else if(data.equals("Chrome"))
			{
				driver=new ChromeDriver();
				Log.info("Chrome browser started");
			}
			
		}
		catch(Exception e)
		{
			//This is to print the logs - Method Name & Error description/stack
			Log.info("Not able to open Browser --- " + e.getMessage());
			DriverScriptReflection.bResult = false;
			TakeScreenshotMethod.takeScreenShot();
			
		}
		
	}
	
	
	
	public static void navigateWebsite(String object, String data) throws Exception
	{
		try
		{	
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			Log.info("Navigating to URL");
			driver.get(Constants.URL);
		}
		catch (Exception e)
		{
			// TODO: handle exception
			Log.info("Not able to navigate --- " + e.getMessage());
			DriverScriptReflection.bResult = false;
			TakeScreenshotMethod.takeScreenShot();
		}
		
	}
	
	public static void click_LoginButton(String object, String data) throws Exception
	{
		try
		{
			Log.info("Clicking on Webelement "+ object);
			driver.findElement(By.linkText(OR.getProperty(object))).click();
		}
		catch(Exception e)
		{
 			Log.error("Not able to click --- " + e.getMessage());
 			DriverScriptReflection.bResult = false;
 			TakeScreenshotMethod.takeScreenShot();
 		}
		
	}
	
	public static void click_Loginlink(String object, String data) throws Exception
	{
		try
		{
			Log.info("Clicking on Webelement "+ object);
			driver.findElement(By.xpath(OR.getProperty(object))).click();
		}
		catch(Exception e)
		{
 			Log.error("Not able to click --- " + e.getMessage());
 			DriverScriptReflection.bResult = false;
 			TakeScreenshotMethod.takeScreenShot();
 			
 		}
	}
	
	public static void input_Username(String object, String data) throws Exception
	{	
		try
		{
			Log.info("Entering the text in UserName");
			driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(data);
		}
		catch(Exception e)
		{
			 Log.error("Not able to Enter UserName --- " + e.getMessage());
			 DriverScriptReflection.bResult = false;
			 TakeScreenshotMethod.takeScreenShot();
			 
		}
		
	}
	
	public static void input_password(String object, String data) throws Exception
	{
		try
		{
			Log.info("Entering the text in Password");
			driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(data);
		}
		catch(Exception e)
		{
			 Log.error("Not able to Enter Password --- " + e.getMessage());
			 DriverScriptReflection.bResult = false;
			 TakeScreenshotMethod.takeScreenShot();
			 
		}
		
	}
	
	public static void click_SignIn(String object, String data) throws Exception
	{
		try
		{
			Log.info("Clicking on Webelement "+ object);
			driver.findElement(By.id(OR.getProperty(object))).click();
			
		}
		catch (Exception e)
		{
			// TODO: handle exception
			Log.error("Not able to click --- " + e.getMessage());
			DriverScriptReflection.bResult = false;
			TakeScreenshotMethod.takeScreenShot();
			
		}
		
	}
	
	public static void click_profilenameButton(String object, String data) throws Exception
	{
		try
		{
			Log.info("Clicking on Webelement "+ object);
			driver.findElement(By.xpath(OR.getProperty(object))).click();
		}
		catch (Exception e)
		{
			// TODO: handle exception
			Log.error("Not able to click --- " + e.getMessage());
			DriverScriptReflection.bResult = false;
			TakeScreenshotMethod.takeScreenShot();
			
		}
		
	}
	
	public static void waitFor(String object, String data) throws Exception
	{
		try
		{
			Log.info("Wait for 2 seconds");
			Thread.sleep(2000);
		}
		catch(Exception e)
		{
			 Log.error("Not able to Wait --- " + e.getMessage());
			 DriverScriptReflection.bResult = false;
			 TakeScreenshotMethod.takeScreenShot();
			 
		}
		
	}
	
	public static void click_Logoutlink(String object, String data) throws Exception
	{
		try
		{
			Log.info("Clicking on Webelement "+ object);
			driver.findElement(By.xpath(OR.getProperty(object))).click();
		}
		catch (Exception e)
		{
			// TODO: handle exception
			Log.error("Not able to click --- " + e.getMessage());
			DriverScriptReflection.bResult = false;
			TakeScreenshotMethod.takeScreenShot();
		}

	}
	
	public static void closeBrowser(String object, String data) throws Exception
	{
		try
		{
			Log.info("Closing the browser");
			driver.close();
			//Thread.sleep(6000);
		}
		catch(Exception e)
		{
			 Log.error("Not able to Close the Browser --- " + e.getMessage());
			 DriverScriptReflection.bResult = false;
			 TakeScreenshotMethod.takeScreenShot();
		}
	}
	
	
}
