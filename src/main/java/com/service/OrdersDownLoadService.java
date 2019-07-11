package com.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.entity.Orders;

public class OrdersDownLoadService {

	public File writeExcel(Orders orders) throws Exception {
		// 原理：excel首先将项目数据查出来写入文件，然后弹出下载框将数据输出到浏览器给予下载
		String tempFilePath = "D:/up/orders.xls";// 预存路径
		File tempFile = new File(tempFilePath);//根据指定路径实例化一个file对象
		tempFile.createNewFile();//根据file对象创建此抽象路径名的新文件

		XSSFWorkbook wb = new XSSFWorkbook();// 获得一个wb文档对象
		XSSFSheet sheet = wb.createSheet();// 获得一个sheet表单对象
		XSSFRow row1 = sheet.createRow(0);// 获得一个下标0的行对象
		writeExcelValue("订单表", row1, 0, wb, true);//给下标为0的行和下标为0的列添加一个String的值
		
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 12));// 合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列 截至列

		XSSFRow rowHead = sheet.createRow(1);// 创建单元格并设置单元格内容
		
		writeExcelValue("商品编号", rowHead, 0, wb, true);
		writeExcelValue("订单编号", rowHead, 1, wb, true);
		writeExcelValue("商品名字", rowHead, 2, wb, true);
		writeExcelValue("价格", rowHead, 3, wb, true);
		writeExcelValue("商品数量", rowHead, 4, wb, true);
		writeExcelValue("收货地址", rowHead, 5, wb, true);
		writeExcelValue("收货人电话", rowHead, 6, wb, true);
		writeExcelValue("收货人", rowHead, 7, wb, true);
		writeExcelValue("收货时间", rowHead, 8, wb, true);
		writeExcelValue("创建时间", rowHead, 9, wb, true);
		writeExcelValue("创建人", rowHead, 10, wb, true);
		writeExcelValue("最后修改时间", rowHead, 11, wb, true);
		writeExcelValue("最后修改人", rowHead, 12, wb, true);
		sheet.setColumnWidth(0, 5000); // 设置第4列的列宽
		sheet.setColumnWidth(1, 5000); // 设置第5列的列宽
		sheet.setColumnWidth(6, 4000);
		sheet.setColumnWidth(8, 4000);
		sheet.setColumnWidth(9, 4000);
		sheet.setColumnWidth(10, 4000);
		sheet.setColumnWidth(11, 4000);
		List<Orders> list = new ArrayList<>();
		list.add(orders);
		XSSFCellStyle styles = wb.createCellStyle();// 创建一个单元格样式对象
		styles.setAlignment(XSSFCellStyle.ALIGN_RIGHT); // 居右
		
		for (int i = 0; i < list.size(); i++) {// 循环集合
			XSSFRow rowHeadCase = sheet.createRow(i + 2);

			XSSFCell cell = rowHeadCase.createCell(0);
			cell.setCellValue(list.get(i).getProductCode());// 写入内容
			cell.setCellStyle(styles);// 样式应用于单元格

			XSSFCell cell1 = rowHeadCase.createCell(1);
			cell1.setCellValue(list.get(i).getCode());// 写入内容
			cell1.setCellStyle(styles);// 样式应用于单元格
			
			XSSFCell cell2 = rowHeadCase.createCell(2);
			cell2.setCellValue(list.get(i).getProductName());// 写入内容
			cell2.setCellStyle(styles);// 样式应用于单元格
			
			XSSFCell cell3 = rowHeadCase.createCell(3);
			cell3.setCellValue(list.get(i).getPrice());// 写入内容
			cell3.setCellStyle(styles);// 样式应用于单元格
			
			XSSFCell cell4 = rowHeadCase.createCell(4);
			cell4.setCellValue(list.get(i).getAmount());// 写入内容
			cell4.setCellStyle(styles);// 样式应用于单元格
			
			XSSFCell cell5 = rowHeadCase.createCell(5);
			cell5.setCellValue(list.get(i).getReceivingAddress());// 写入内容
			cell5.setCellStyle(styles);// 样式应用于单元格
			
			XSSFCell cell6 = rowHeadCase.createCell(6);
			cell6.setCellValue(list.get(i).getPhone());// 写入内容
			cell6.setCellStyle(styles);// 样式应用于单元格
			
			XSSFCell cell7 = rowHeadCase.createCell(7);
			cell7.setCellValue(list.get(i).getConsignee());// 写入内容
			cell7.setCellStyle(styles);// 样式应用于单元格
			
			
			XSSFCell cell10 = rowHeadCase.createCell(8);
			cell10.setCellValue(list.get(i).getReceivingTime());// 写入内容
			cell10.setCellStyle(styles);// 样式应用于单元格
			
			
			XSSFCell cell12 = rowHeadCase.createCell(9);
			cell12.setCellValue(list.get(i).getCreateTime());// 写入内容
			cell12.setCellStyle(styles);// 样式应用于单元格
			
			XSSFCell cell13 = rowHeadCase.createCell(10);
			cell13.setCellValue(list.get(i).getCreateor());// 写入内容
			cell13.setCellStyle(styles);// 样式应用于单元格
			
			XSSFCell cell14 = rowHeadCase.createCell(11);
			cell14.setCellValue(list.get(i).getLastUpdateTime());// 写入内容
			cell14.setCellStyle(styles);// 样式应用于单元格
			
			XSSFCell cell15 = rowHeadCase.createCell(12);
			cell15.setCellValue(list.get(i).getLastUpdater());// 写入内容
			cell15.setCellStyle(styles);// 样式应用于单元格
			
			
		}
		// 写进文件
		FileOutputStream output = new FileOutputStream(tempFile);// 将该file对象构造成一个输出流对象
		wb.write(output);// 将文档数据对象写入输出流对象
		output.close();// 关闭流
		
		return tempFile;//返回该文件对象
	}

	/**
	 * 写进单元格, 单元格样式也在这里设置
	 * 
	 * @param value
	 *            要写入单元格的值
	 * @param row
	 *            第几行
	 * @param cols
	 *            第几列
	 * @param wb
	 * @param isHead
	 *            true是列表头 false不是列表头
	 */
	private void writeExcelValue(String value, XSSFRow row, int cols, XSSFWorkbook wb, boolean isHead) {
		XSSFCell cell = row.createCell(cols);//创建一个单元格对象
		cell.setCellType(XSSFCell.CELL_TYPE_STRING);// 将文本格式应用到单元格
		cell.setCellValue(value);// 将变量value的值写入单元格
		XSSFCellStyle style = wb.createCellStyle();// 创建一个单元格样式对象
		XSSFFont font = wb.createFont();// 创建一个字体样式对象
		if (isHead) {//这是外部传输的参数用来判断是否为表头
			// style.setFillForegroundColor((short) 13);// 设置背景色
			// style.setFillPattern(CellStyle.SOLID_FOREGROUND);// 设置背景色
			style.setWrapText(true); // 行内容换行
			style.setBorderBottom(XSSFCellStyle.BORDER_THIN); // 下边框
			style.setBorderTop(XSSFCellStyle.BORDER_THIN);// 上边框
			style.setBorderRight(XSSFCellStyle.BORDER_THIN);// 右边框
			style.setBorderLeft(XSSFCellStyle.BORDER_THIN);// 左边框
			style.setAlignment(XSSFCellStyle.ALIGN_CENTER); // 居中
			font.setBold(true); // 字体加粗
			font.setFontHeight(13); // 字体大小
		}
		style.setFont(font);//将字体样式应用到样式
		cell.setCellStyle(style);//将样式应用到单元格
	}
	
	
	public void readExcel(InputStream inp) throws IOException{
		XSSFWorkbook wb = new XSSFWorkbook(inp);//将inp构造创建一个文档对象
		XSSFSheet sheet = wb.getSheetAt(0);
		for (int i = 2; i <= sheet.getLastRowNum(); i++) {//循环行，循环终止该sheet对象的最后一行
			XSSFRow row = sheet.getRow(i);
			for (int j = 0; j < 13; j++) {//循环至截止列为第6行
				XSSFCell cell = row.getCell(j);
				if(null != cell){//当单元格不等于null的时候满足条件
					if(j == 0 || j == 2 || j == 3 || j == 4){//这些行输入数字类型
						cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);// 数字文本格式
						System.out.print(cell.getNumericCellValue()+" , "); //打印数字的文本格式的值
					}else{
						cell.setCellType(XSSFCell.CELL_TYPE_STRING);// 字符串文本格式
						System.out.print(cell.getStringCellValue()+" , ");//打印字符串文本格式单元格的值
					}
				}
			}
			System.out.println(" ----- ");
		}
	}

}
