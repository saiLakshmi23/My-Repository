package config;

import executionEngine.DriverScriptReflection;

public class Constants 
{
	//This is the list of System Variables
	//Declared as 'public' so that it can be used in other classes of this project
	//Declared as 'static' so that we do need to instantiate a class object
	//'String' and 'int' are the data type for storing a type of value
	public static final String URL = "http://stg.rak.ae";
	public static final String Path_TestData = "D://MavenWorkspace//Keyword_Driven_Framework_Maven//src//test//java//dataEngine//DataEngine.xlsx";
	public static final String File_TestData = "DataEngine.xlsx";
	public static final String Path_OR = "D://MavenWorkspace//Keyword_Driven_Framework_Maven//src//test//java//config//OR.txt";
	public static final String screenshotsFilePath = "D://Screenshots//"+DriverScriptReflection.sActionKeyword+".png";
	
	
	//List of Data Sheet Column Numbers
	public static final int Col_TestCaseID = 0;
	public static final int Col_TestScenarioID = 1;
	public static final int Col_TestCaseDesc = 1;
	public static final int Col_TestStepDesc = 2;
	public static final int Col_PageObject = 3;
	public static final int Col_ActionKeyword = 4;
	public static final int Col_RunMode = 2;
	public static final int Col_Result = 3;
	public static final int Col_DataSet = 5;
	public static final int Col_TestStepResult = 6;
	
	//List of Data Engine Excel Sheets
	public static final String Sheet_TestSteps = "TestSteps";
	public static final String Sheet_TestCases = "Test Cases";
	
	//List of Test Data
/*	public static final String UserName = "portaladmin";
	public static final String Password = "idsuser1";*/
	
	//Pass and Fail Results
	public static final String KEYWORD_FAIL = "FAIL";
	public static final String KEYWORD_PASS = "PASS";
	
}
