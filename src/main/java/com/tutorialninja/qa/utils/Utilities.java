package com.tutorialninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.poi.ss.usermodel.CellType;

public class Utilities {
	WebDriver driver;
	public static final int IMPLICITE_WAIT_TIME = 10;
	public static final int PAGE_WAIT_TIME = 5;
	
	public Utilities(WebDriver driver) {
		this.driver=driver;
	}

	public static String generateEmailWithTimeStamp() {
		Date date = new Date();
		String dateTimeStamp = date.toString().replace(" ", "_").replace(":", "_");
		return "manoharkantjoshi" + dateTimeStamp + "@gmail.com";
	}

	public static Object[][] getTestDataFromExcel(String sheetname) {
		XSSFWorkbook workbook = null;
		File fileExcel = new File(System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\tutorialninja\\qa\\testdata\\tutorialNinjaTestData.xlsx");
		try {
			FileInputStream fisExcel = new FileInputStream(fileExcel);
			workbook = new XSSFWorkbook(fisExcel);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		XSSFSheet sheet = workbook.getSheet(sheetname);
		if (sheet == null) {
		    throw new RuntimeException("Sheet with name '" + sheetname + "' not found in the workbook");
		}
		else {
			System.out.println("Sheet found");
		}
		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();
		
		System.out.println(rows+" "+cols);

		Object[][] data = new Object[rows][cols];
		for (int i = 0; i < rows; i++) {
			XSSFRow row = sheet.getRow(i + 1);
			
			for (int j = 0; j < cols; j++) {
				XSSFCell cell = row.getCell(j);
				System.out.println(cell.getStringCellValue()); 
				CellType celltype = cell.getCellType();

				switch (celltype) {

				case STRING:
					data[i][j] = cell.getStringCellValue();
					break;

				case NUMERIC:
					data[i][j] = Integer.toString((int) cell.getNumericCellValue());
					break;

				case BOOLEAN:
					data[i][j] = cell.getBooleanCellValue();
					break;
				}
			}
		}
		return data;

	}
	
	public static WebDriverWait explicitlyWait(WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		return wait;
		
	}
}
