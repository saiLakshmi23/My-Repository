package utility;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class CustomizedReport implements ITestListener
{
	public void onStart(ITestContext context) 
	{
		// TODO Auto-generated method stub
		System.out.println("Start Of Execution(TEST)->"+context.getName());
		
	}
	
	public void onTestStart(ITestResult result)
	{
		// TODO Auto-generated method stub
		System.out.println("Test Started->"+result.getName());
		
	}

	public void onTestSuccess(ITestResult result) 
	{
		// TODO Auto-generated method stub
		System.out.println("Test Pass->"+result.getName());
		
	}

	public void onTestFailure(ITestResult result) 
	{
		// TODO Auto-generated method stub
		System.out.println("Test Failed->"+result.getName());
	}

	public void onTestSkipped(ITestResult result) 
	{
		// TODO Auto-generated method stub
		System.out.println("Test Skipped->"+result.getName());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result)
	{
		// TODO Auto-generated method stub
		
	}

	

	public void onFinish(ITestContext context) 
	{
		// TODO Auto-generated method stub
		System.out.println("END Of Execution(TEST)->"+context.getName());
		
	}
	
}
