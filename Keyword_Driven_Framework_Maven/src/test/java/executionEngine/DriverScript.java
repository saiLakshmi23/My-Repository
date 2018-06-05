package executionEngine;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class DriverScript 
{
	private static WebDriver driver = null;
	
	public static void main(String[] args) throws InterruptedException
	{
		System.setProperty("webdriver.gecko.driver", "C:\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get("http://stg.rak.ae");
		driver.findElement(By.linkText("Login")).click();
		driver.findElement(By.xpath("html/body/div[2]/div[1]/div/ul[1]/li[2]/div/ul/li[1]/a")).click();
		driver.findElement(By.xpath(".//*[@id='rakloginform']/div[1]/div/div/div[1]/input[1]")).sendKeys("portaladmin");
		driver.findElement(By.xpath(".//*[@id='rakloginform']/div[1]/div/div/div[1]/input[2]")).sendKeys("idsuser1");
		driver.findElement(By.id("loginbtn")).click();
		driver.findElement(By.xpath("html/body/div[2]/div[1]/div/ul[1]/li[2]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("html/body/div[2]/div[1]/div/ul[1]/li[2]/div/ul/li[4]/a[1]")).click();
		driver.quit();
		
	}
}
