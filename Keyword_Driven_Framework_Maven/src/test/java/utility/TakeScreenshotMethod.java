package utility;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import executionEngine.DriverScriptReflection;
import config.*;
import config.Constants;

public class TakeScreenshotMethod 
{
	
	public static void takeScreenShot() 
	{
    	  try 
    	  {	
    		System.out.println("Entered Try Block");
    		//Convert web driver object to TakeScreenshot
    		TakesScreenshot scrShot =((TakesScreenshot)ActionKeywords.driver);
    		//Call getScreenshotAs method to create image file
    		File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
    		//Move image file to new destination
    		File DestFile = new File(Constants.screenshotsFilePath);
    		//Copy file at destination
    		FileUtils.copyFile(SrcFile, DestFile);
			System.out.println(DriverScriptReflection.sActionKeyword);
			System.out.println("***Placed screen shot in "+Constants.screenshotsFilePath+" ***");
    	  }
    	  catch (IOException e) 
    	  {
    		e.printStackTrace();
    	  }
    }
}
