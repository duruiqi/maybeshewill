package com.scxh.yhzm.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.scxh.yhzm.dao.mapper.DepartmentMapper;
import com.scxh.yhzm.dao.mapper.LogisticsMapper;
import com.scxh.yhzm.po.Department;
import com.scxh.yhzm.po.Purchase;

public class ExcelUtil {
	/**
	 * 导出用户的所有列表到excel
	 * @param userList 用户列表
	 * @param outputStream 输出流
	 */
	public static void exportUserExcel(List<Purchase> purchaseList, OutputStream outputStream) {
		try {
			//1、创建工作簿
			HSSFWorkbook workbook = new HSSFWorkbook();
			//1.1、创建合并单元格对象
			CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, 0, 8);
			//1.2、头标题样式
			HSSFCellStyle style1 = createCellStyle(workbook, (short)16);
			
			//1.3、列标题样式
			HSSFCellStyle style2 = createCellStyle(workbook, (short)12);
			
			HSSFCellStyle style3 = baseCellStyle(workbook, (short)10);
			
			//2、创建工作表
			HSSFSheet sheet = workbook.createSheet("采购订单明细");
			//2.1、加载合并单元格对象
			sheet.addMergedRegion(cellRangeAddress);
			//设置默认列宽
			sheet.setDefaultColumnWidth(16);
			
			
			//3、创建行
			//3.1、创建头标题行；并且设置头标题
			HSSFRow row1 = sheet.createRow(0);
			HSSFCell cell1 = row1.createCell(0);
			//加载单元格样式
			cell1.setCellStyle(style1);
			cell1.setCellValue("采购订单明细");
			
			//3.2、创建列标题行；并且设置列标题
			HSSFRow row2 = sheet.createRow(1);
			String[] titles = {"采购单号","材料名称", "材料材质", "采购数量", "采购时间","采购金额","是否付款","付款类别","申请部门"};
			for(int i = 0; i < titles.length; i++){
				HSSFCell cell2 = row2.createCell(i);
				//加载单元格样式
				cell2.setCellStyle(style2);
				cell2.setCellValue(titles[i]);
			}
			
			SimpleDateFormat dateFormate = new SimpleDateFormat("yyyy/MM/dd");;
			NumberFormat numFormate = new DecimalFormat("###,###,###,###,###,###.00");
			Purchase purchase = null;
			//4、操作单元格；将列表写入excel
			
			if(purchaseList != null){
				int pLength = purchaseList.size();
				for(int j = 0; j < pLength; j++){
					HSSFRow row = sheet.createRow(j+2);
					purchase = purchaseList.get(j);
					for(int k=0;k < titles.length ;k++){
						sheet.setDefaultColumnStyle(k, style3);
						sheet.setColumnWidth(k,k<1?12000:4000);
						switch(k){
							case 0:row.createCell(k).setCellValue(purchase.getPurId());break;
							case 1:row.createCell(k).setCellValue(purchase.getMaterialName());break;
							case 2:row.createCell(k).setCellValue(purchase.getMaterialType());break;
							case 3:row.createCell(k).setCellValue(purchase.getPurCount());break;
							case 4:row.createCell(k).setCellValue(dateFormate.format(purchase.getPurDate()));break;
							case 5:row.createCell(k).setCellValue("￥" + numFormate.format(purchase.getPurPrice()) + "元");break;
							case 6:row.createCell(k).setCellValue(purchase.getIsPay() ? "已付款":"未付款");break;
							case 7:row.createCell(k).setCellValue(purchase.getPayMethod());break;
							case 8:row.createCell(k).setCellValue(purchase.getDepartment().getDname());break;
						}
					}
				}
			}
			//5、输出
			workbook.write(outputStream);
			workbook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 向数据库中保存用户提交的excle数据
	 * @param userExcel 上传excel复合类型的文件
	 * @param departmentMapper 用于操作department的持久层接口
	 * @param logisticsMapper 用于操作purchase的持久层接口
	 */
	public static  void importExcel(CommonsMultipartFile userExcel,
			DepartmentMapper<Department> departmentMapper,LogisticsMapper<Purchase> logisticsMapper) {
		try {
			
			InputStream in = userExcel.getInputStream();
			String purchaseFileName = userExcel.getOriginalFilename();
			boolean is03Excel = purchaseFileName.matches("^.+\\.(?i)(xls)$");
			//1、读取工作簿
			Workbook workbook = is03Excel ? new HSSFWorkbook(in):new XSSFWorkbook(in);
			//2、读取工作表
			Sheet sheet = workbook.getSheetAt(0);
			int cellSize = 9;
			//3、读取行
			int length = sheet.getPhysicalNumberOfRows();
			if(sheet.getPhysicalNumberOfRows() > 2){
				
				for(int k = 2; k < length; k++){
					//4、读取单元格
					Row row = sheet.getRow(k);
					Purchase purchase = new Purchase();
					for(int i = 0;i < cellSize;i++){
						switch(i){
							//获取每一行的单元格的值并将它注入到一个实体类上
							case 0: purchase.setPurId(row.getCell(i).getStringCellValue());;break;
							case 1: purchase.setMaterialName(row.getCell(i).getStringCellValue());break;
							case 2: purchase.setMaterialType(row.getCell(i).getStringCellValue());break;
							case 3: purchase.setPurCount((int)(row.getCell(i).getNumericCellValue()));break;
							case 6: purchase.setIsPay("已付款".equals(row.getCell(i).getStringCellValue()));break;
							case 7: purchase.setPayMethod(row.getCell(i).getStringCellValue());break;
							case 8: purchase.setDepartment(departmentMapper.getEntryByName(row.getCell(i).getStringCellValue()));break;
						}
						
						if(i == 4){
							java.util.Date date = null;
							int m = row.getCell(i).getCellType();
							if(m == Cell.CELL_TYPE_STRING){
								row.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
								date = new SimpleDateFormat("yyyy/MM/dd").parse(row.getCell(i).getStringCellValue());
							}else if(m == Cell.CELL_TYPE_NUMERIC){
								row.getCell(i).setCellType(Cell.CELL_TYPE_NUMERIC);
								date = row.getCell(i).getDateCellValue();
							}
						
							if(null != date){
								purchase.setPurDate(new java.sql.Date(date.getTime()));
							}else{
								throw new RuntimeException("该行的日期格式错误！");
							}
						}
						
						if(i == 5){
							Double price = null;
							int m = row.getCell(i).getCellType();
							if(m == Cell.CELL_TYPE_STRING){
								row.getCell(i).setCellType(Cell.CELL_TYPE_STRING);
								price = trimPrefixAndSuffix(row.getCell(i).getStringCellValue());
							}else if(m == Cell.CELL_TYPE_NUMERIC){
								row.getCell(i).setCellType(Cell.CELL_TYPE_NUMERIC);
								price = row.getCell(i).getNumericCellValue();
							}
							if(null != price){
								purchase.setPurPrice(price);
							}else{
								throw new RuntimeException("该行不是有效的数字格式！");
							}
						}
					}
					//向数据库保存数据
					logisticsMapper.saveEntry(purchase);
				}
			}
			
			workbook.close();
			in.close();
			 
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("导入失败" + e.getMessage());
		}
	}
	
	/**
	 * 将一个一定格式的金额表示字串去掉修饰符，并转成double
	 * @param str
	 * @return
	 */
	public static Double trimPrefixAndSuffix(String str){
		char[] strs = str.toCharArray();
		StringBuilder buffer = new StringBuilder();
		for(int i = 0;i < strs.length;i++){
			
			if(strs[i] > 57 || strs[i] < 46){
				continue;
			}
			buffer.append(strs[i]);
		}
		return Double.parseDouble(buffer.toString());
	}
	
	/**
	 * 创建单元格样式
	 * @param workbook 工作簿
	 * @param fontSize 字体大小
	 * @return 单元格样式
	 */
	private static HSSFCellStyle createCellStyle(HSSFWorkbook workbook, short fontSize) {
		HSSFCellStyle style = workbook.createCellStyle();
		
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
		//创建字体
		HSSFFont font = workbook.createFont();
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗字体
		font.setFontHeightInPoints(fontSize);
		//加载字体
		style.setFont(font);
		return style;
	}
	
	private static HSSFCellStyle baseCellStyle(HSSFWorkbook workbook, short fontSize) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);//水平居中
		style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);//垂直居中
		//创建字体
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints(fontSize);
		//加载字体
		style.setFont(font);
		return style;
	}

}