package executionEngine;

import java.io.File;
import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.util.Properties;
import org.apache.log4j.xml.DOMConfigurator;
import config.ActionKeywords;
import config.Constants;
import utility.CustomizedFinalReport;
import utility.ExcelUtils;
import utility.Log;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

@Listeners(value = CustomizedFinalReport.class)
public class DriverScriptReflection
{
	public static Properties OR;
	public static ActionKeywords actionKeywords;
	public static String sActionKeyword;
	public static String sPageObject;
	public static Method method[];
	public static int iTestStep;
	public static int iTestLastStep;
	public static String sTestCaseID;
	public static String sRunMode;
	public static String sTestCase_Name;
	public static String sTestCase_Desc;
	public static String sTestStep_Desc;
	public static boolean bResult;
	public static String sData;
	public static ExtentReports reports;
	public static ExtentTest logger;
	
	
	public DriverScriptReflection() throws NoSuchMethodException, SecurityException
	{
		actionKeywords = new ActionKeywords();
		method = actionKeywords.getClass().getMethods();
		reports = new ExtentReports("..\\Keyword_Driven_Framework_Maven\\Reports\\htmlreport.html");
		reports
			.addSystemInfo("Environment", "Automation Testing")
			.addSystemInfo("Project Name", "RAK");
		//loading the external xml file (i.e., extent-config.xml) which was placed under the base directory
        //You could find the xml file below. Create xml file in your project and copy past the code mentioned below
        reports.loadConfig(new File("C:\\GitRapo\\Keyword_Driven_Framework_Maven\\extent-config.xml"));
	}

	
	@Test
	public static void main() throws Exception 
	{
		ExcelUtils.setExcelFile(Constants.Path_TestData);
		
		//This is to start the Log4j logging in the test case
		DOMConfigurator.configure("log4j.xml");
		String Path_OR = Constants.Path_OR;
		FileInputStream fs = new FileInputStream(Path_OR);
		OR= new Properties(System.getProperties());
		OR.load(fs);
		
		DriverScriptReflection startEngine = new DriverScriptReflection();
		startEngine.execute_TestCase();
	}

	private void execute_TestCase() throws Exception 
	{
		//This will return the total number of test cases mentioned in the Test cases sheet
		int iTotalTestCases = ExcelUtils.getRowCount(Constants.Sheet_TestCases);
		//This loop will execute number of times equal to Total number of test cases
		for(int iTestcase=1;iTestcase<=iTotalTestCases;iTestcase++){
			
			//This is to get the Test case name from the Test Cases sheet
			sTestCaseID = ExcelUtils.getCellData(iTestcase, Constants.Col_TestCaseID, Constants.Sheet_TestCases); 
			sTestCase_Name=ExcelUtils.getCellData(iTestcase, Constants.Col_TestScenarioID, Constants.Sheet_TestCases);
			sTestCase_Desc = ExcelUtils.getCellData(iTestcase, Constants.Col_TestCaseDesc, Constants.Sheet_TestCases);
			
			
			
			//This is to get the value of the Run Mode column for the current test case
			sRunMode = ExcelUtils.getCellData(iTestcase, Constants.Col_RunMode,Constants.Sheet_TestCases);
			
			//This is the condition statement on RunMode value
			if (sRunMode.equals("Yes"))
			{
				
				System.out.println("Hai");
				
				//Only if the value of Run Mode is 'Yes', this part of code will execute
				iTestStep = ExcelUtils.getRowContains(sTestCaseID, Constants.Col_TestCaseID, Constants.Sheet_TestSteps);
				//System.out.println("Test Cases Row No:"+iTestStep);
				iTestLastStep = ExcelUtils.getTestStepsCount(Constants.Sheet_TestSteps, sTestCaseID, iTestStep);
				//System.out.println("Test Case Last Step:"+iTestLastStep);
				//This loop will execute number of times equal to Total number of test steps
				Log.startTestCase(sTestCaseID);
				//Setting the value of bResult variable to 'true' before starting every test case
				bResult = true;
				
				logger = reports.startTest(sTestCaseID, sTestCase_Desc);
				logger.assignAuthor("Sai Lakshmi Paritala");
				logger.assignCategory("Smoke Report -- PROD");
				for (;iTestStep<iTestLastStep;iTestStep++)
				{
					
		    		sActionKeyword = ExcelUtils.getCellData(iTestStep, Constants.Col_ActionKeyword,Constants.Sheet_TestSteps);
		    		sPageObject = ExcelUtils.getCellData(iTestStep, Constants.Col_PageObject, Constants.Sheet_TestSteps);
		    		sData = ExcelUtils.getCellData(iTestStep, Constants.Col_DataSet, Constants.Sheet_TestSteps);
		    		
		    		execute_Actions();
		    		
		    		//This is the result code, this code will execute after each test step
		    		//The execution flow will go into this only if the value of bRsult is 'false'
		    		if(bResult==false)
		    		{
		    			//If 'false' then store the test case result as Fail
		    			ExcelUtils.setCellData(Constants.KEYWORD_FAIL, iTestcase, Constants.Col_Result, Constants.Sheet_TestCases);
		    			//End the test case in the logs
		    			Log.endTestCase(sTestCaseID);
		    			//logger.log(LogStatus.FAIL,"Fail");
		    			//By this break statement, execution flow will not execute any more test step of the failed test case
		    			break;
		    		}
		    		
				}
				if(bResult==true)
				{
					//Storing the result as Pass in the excel sheet
					ExcelUtils.setCellData(Constants.KEYWORD_PASS, iTestcase, Constants.Col_Result, Constants.Sheet_TestCases);
					Log.endTestCase(sTestCaseID);
					//logger.log(LogStatus.PASS,"Pass");
					
				}
				
				
			}
		}
		reports.endTest(logger);
		reports.flush();
		
	}

	private static void execute_Actions() throws Exception 
	{
	for(int i=0;i<method.length;i++)
	{			
		if(method[i].getName().equals(sActionKeyword))
		{
			method[i].invoke(actionKeywords,sPageObject,sData);
			//This code block will execute after every test step
			sTestStep_Desc = ExcelUtils.getCellData(iTestStep, Constants.Col_TestStepDesc, Constants.Sheet_TestSteps);
			
			if(bResult==true)
			{
				//If the executed test step value is true, Pass the test step in Excel sheet
				logger.log(LogStatus.PASS,sTestStep_Desc);
				ExcelUtils.setCellData(Constants.KEYWORD_PASS, iTestStep, Constants.Col_TestStepResult, Constants.Sheet_TestSteps);
				break;
			}
			else 
			{
				//If the executed test step value is false. Fail the test step in Excel sheet
				ExcelUtils.setCellData(Constants.KEYWORD_FAIL, iTestStep, Constants.Col_TestStepResult, Constants.Sheet_TestSteps);
				logger.log(LogStatus.FAIL, sTestStep_Desc);
				//In case of false, the test execution will not reach to last step of closing browser
				//So it make sense to close the browser before moving on to next test case
				ActionKeywords.closeBrowser("","");
				break;
			}
			
		}
	}
  }
}