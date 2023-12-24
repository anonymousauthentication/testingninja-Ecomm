package com.tutorialninja.qa.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;

public class Utilities {
	public static final int IMPLICITE_WAIT_TIME = 10;
	public static final int PAGE_WAIT_TIME = 5;

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
		System.out.println(workbook.getSheetName(0));
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(1).getLastCellNum();

		Object[][] data = new Object[rows][cols];
		for (int i = 0; i < rows; i++) {
			XSSFRow row = sheet.getRow(i + 1);
			for (int j = 0; j < cols; j++) {
				XSSFCell cell = row.getCell(j);
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
}
