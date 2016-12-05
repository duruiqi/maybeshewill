package com.scxh.yhzm.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

public class ExcelTest{
	@Test
	public void testExcel07Write() throws Exception{
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("测试");
		XSSFRow row = sheet.createRow(3);
		XSSFCell cell = row.createCell(3);
		cell.setCellValue("Hello World!");
		OutputStream out = new FileOutputStream("d:\\测试.xlsx");
		workbook.write(out);
		out.close();
		workbook.close();
	}
	@Test
	public void testExcel07Read() throws Exception{
		XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream("d:\\测试.xlsx"));
		XSSFSheet sheet = workbook.getSheetAt(0);
		XSSFRow row = sheet.getRow(3);
		XSSFCell cell = row.getCell(3);
		System.out.println(cell.getStringCellValue());
		workbook.close();
	}
	@Test
	public void testExcelWrite()throws Exception{
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("测试");
		CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, 9);
		sheet.addMergedRegion(cellRangeAddress);
	}
	
}