package utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import config.Constants;
import executionEngine.DriverScriptReflection;

public class ExcelUtils 
{
    private static XSSFSheet ExcelWSheet;
    private static XSSFWorkbook ExcelWBook;
    private static XSSFCell Cell;
    private static XSSFRow Row;

	//This method is to set the File path and to open the Excel file
	//Pass Excel Path and SheetName as Arguments to this method
	public static void setExcelFile(String Path) throws Exception 
	{
			try
			{
			FileInputStream ExcelFile = new FileInputStream(Path);
	        ExcelWBook = new XSSFWorkbook(ExcelFile);
			}
			catch(Exception e)
			{
				Log.error("Class Utils | Method setExcel | Exeception desc:"+e.getMessage());
				DriverScriptReflection.bResult = false;
			}
	}

	//This method is to read the test data from the Excel cell
	//In this we are passing Arguments as Row Num, Col Num & Sheet Name
	public static String getCellData(int RowNum, int ColNum, String SheetName) throws Exception
	{
	    	ExcelWSheet = ExcelWBook.getSheet(SheetName);
	    	 try
	    	 {
		    		Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
		             String CellData = Cell.getStringCellValue();
		             return CellData;
	             }
	    	 	catch (Exception e)
	    	 	 {
	               Log.error("Class Utils | Method getCellData | Exception desc : "+e.getMessage());
	               DriverScriptReflection.bResult = false;
	               return"";
	             }
	}

	//This method is to get the row count used of the excel sheet
	public static int getRowCount(String SheetName)
	{
			int iNumber=0;
			try
			{
				ExcelWSheet = ExcelWBook.getSheet(SheetName);
				iNumber=ExcelWSheet.getLastRowNum()+1;
			}
			catch(Exception e)
			{
				Log.error("Class Utils | Method getRowCount | Exception desc : "+e.getMessage());
				DriverScriptReflection.bResult = false;
			}
			return iNumber;
	}

	//This method is to get the Row number of the test case
	//This methods takes three arguments(Test Case name , Column Number & Sheet name)
	public static int getRowContains(String sTestCaseName, int colNum,String SheetName) throws Exception
	{
			int iRowNum = 0;	
			try
			{
				//ExcelWSheet = ExcelWBook.getSheet(SheetName);
				int rowCount = ExcelUtils.getRowCount(SheetName);
				for (iRowNum=0 ; iRowNum<rowCount; iRowNum++)
				{
					if  (ExcelUtils.getCellData(iRowNum,colNum,SheetName).equalsIgnoreCase(sTestCaseName))
					{
						break;
					}
				}
			}
			catch(Exception e)
			{
				Log.error("Class Utils | Method getRowContains | Exception desc : "+e.getMessage());
				DriverScriptReflection.bResult = false;
			}
			
			return iRowNum;
	}
		
	//This method is to get the count of the test steps of test case
	//This method takes three arguments (Sheet name, Test Case Id & Test case row number)
	public static int getTestStepsCount(String SheetName, String sTestCaseID, int iTestCaseStart) throws Exception
	{
		try 
		{
			for(int i=iTestCaseStart;i<=ExcelUtils.getRowCount(SheetName);i++)
			{
				if(!sTestCaseID.equals(ExcelUtils.getCellData(i, Constants.Col_TestCaseID, SheetName)))
				{
					int number = i;
					return number;      				
				}
			}
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			int number=ExcelWSheet.getLastRowNum()+1;
			return number;
		}
		catch (Exception e)
		{
			Log.error("Class Utils | Method getRowContains | Exception desc : "+e.getMessage());
			DriverScriptReflection.bResult = false;
			return 0;
	    }
	}
	
	//This method is used to write value in the excel sheet
	//This method accepts four arguments (Result, Row Number, Column Number & Sheet Name)
	public static void setCellData(String Result, int RowNum, int ColNum, String SheetName) throws IOException
	{
		try
		{
			ExcelWSheet = ExcelWBook.getSheet(SheetName);
			Row = ExcelWSheet.getRow(RowNum);
			Cell = Row.getCell(ColNum);
			if(Cell==null)
			{
				Cell = Row.createCell(ColNum);
				Cell.setCellValue(Result);
			}
			else
			{
				Cell.setCellValue(Result);
			}
			
			//Constant Variables Test Data path and Test Data file name
			FileOutputStream fileOut = new FileOutputStream(Constants.Path_TestData);
			ExcelWBook.write(fileOut);
			//fileOut.flush();
			fileOut.close();
			ExcelWBook = new XSSFWorkbook(new FileInputStream(Constants.Path_TestData));
			
		}
		catch(Exception e)
		{
			DriverScriptReflection.bResult = false;
		}
		
		
		
	}

}