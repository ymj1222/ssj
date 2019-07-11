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
		// ԭ��excel���Ƚ���Ŀ���ݲ����д���ļ���Ȼ�󵯳����ؿ�����������������������
		String tempFilePath = "D:/up/orders.xls";// Ԥ��·��
		File tempFile = new File(tempFilePath);//����ָ��·��ʵ����һ��file����
		tempFile.createNewFile();//����file���󴴽��˳���·���������ļ�

		XSSFWorkbook wb = new XSSFWorkbook();// ���һ��wb�ĵ�����
		XSSFSheet sheet = wb.createSheet();// ���һ��sheet������
		XSSFRow row1 = sheet.createRow(0);// ���һ���±�0���ж���
		writeExcelValue("������", row1, 0, wb, true);//���±�Ϊ0���к��±�Ϊ0�������һ��String��ֵ
		
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 12));// �ϲ���Ԫ��CellRangeAddress����������α�ʾ��ʼ�У������У���ʼ�� ������

		XSSFRow rowHead = sheet.createRow(1);// ������Ԫ�����õ�Ԫ������
		
		writeExcelValue("��Ʒ���", rowHead, 0, wb, true);
		writeExcelValue("�������", rowHead, 1, wb, true);
		writeExcelValue("��Ʒ����", rowHead, 2, wb, true);
		writeExcelValue("�۸�", rowHead, 3, wb, true);
		writeExcelValue("��Ʒ����", rowHead, 4, wb, true);
		writeExcelValue("�ջ���ַ", rowHead, 5, wb, true);
		writeExcelValue("�ջ��˵绰", rowHead, 6, wb, true);
		writeExcelValue("�ջ���", rowHead, 7, wb, true);
		writeExcelValue("�ջ�ʱ��", rowHead, 8, wb, true);
		writeExcelValue("����ʱ��", rowHead, 9, wb, true);
		writeExcelValue("������", rowHead, 10, wb, true);
		writeExcelValue("����޸�ʱ��", rowHead, 11, wb, true);
		writeExcelValue("����޸���", rowHead, 12, wb, true);
		sheet.setColumnWidth(0, 5000); // ���õ�4�е��п�
		sheet.setColumnWidth(1, 5000); // ���õ�5�е��п�
		sheet.setColumnWidth(6, 4000);
		sheet.setColumnWidth(8, 4000);
		sheet.setColumnWidth(9, 4000);
		sheet.setColumnWidth(10, 4000);
		sheet.setColumnWidth(11, 4000);
		List<Orders> list = new ArrayList<>();
		list.add(orders);
		XSSFCellStyle styles = wb.createCellStyle();// ����һ����Ԫ����ʽ����
		styles.setAlignment(XSSFCellStyle.ALIGN_RIGHT); // ����
		
		for (int i = 0; i < list.size(); i++) {// ѭ������
			XSSFRow rowHeadCase = sheet.createRow(i + 2);

			XSSFCell cell = rowHeadCase.createCell(0);
			cell.setCellValue(list.get(i).getProductCode());// д������
			cell.setCellStyle(styles);// ��ʽӦ���ڵ�Ԫ��

			XSSFCell cell1 = rowHeadCase.createCell(1);
			cell1.setCellValue(list.get(i).getCode());// д������
			cell1.setCellStyle(styles);// ��ʽӦ���ڵ�Ԫ��
			
			XSSFCell cell2 = rowHeadCase.createCell(2);
			cell2.setCellValue(list.get(i).getProductName());// д������
			cell2.setCellStyle(styles);// ��ʽӦ���ڵ�Ԫ��
			
			XSSFCell cell3 = rowHeadCase.createCell(3);
			cell3.setCellValue(list.get(i).getPrice());// д������
			cell3.setCellStyle(styles);// ��ʽӦ���ڵ�Ԫ��
			
			XSSFCell cell4 = rowHeadCase.createCell(4);
			cell4.setCellValue(list.get(i).getAmount());// д������
			cell4.setCellStyle(styles);// ��ʽӦ���ڵ�Ԫ��
			
			XSSFCell cell5 = rowHeadCase.createCell(5);
			cell5.setCellValue(list.get(i).getReceivingAddress());// д������
			cell5.setCellStyle(styles);// ��ʽӦ���ڵ�Ԫ��
			
			XSSFCell cell6 = rowHeadCase.createCell(6);
			cell6.setCellValue(list.get(i).getPhone());// д������
			cell6.setCellStyle(styles);// ��ʽӦ���ڵ�Ԫ��
			
			XSSFCell cell7 = rowHeadCase.createCell(7);
			cell7.setCellValue(list.get(i).getConsignee());// д������
			cell7.setCellStyle(styles);// ��ʽӦ���ڵ�Ԫ��
			
			
			XSSFCell cell10 = rowHeadCase.createCell(8);
			cell10.setCellValue(list.get(i).getReceivingTime());// д������
			cell10.setCellStyle(styles);// ��ʽӦ���ڵ�Ԫ��
			
			
			XSSFCell cell12 = rowHeadCase.createCell(9);
			cell12.setCellValue(list.get(i).getCreateTime());// д������
			cell12.setCellStyle(styles);// ��ʽӦ���ڵ�Ԫ��
			
			XSSFCell cell13 = rowHeadCase.createCell(10);
			cell13.setCellValue(list.get(i).getCreateor());// д������
			cell13.setCellStyle(styles);// ��ʽӦ���ڵ�Ԫ��
			
			XSSFCell cell14 = rowHeadCase.createCell(11);
			cell14.setCellValue(list.get(i).getLastUpdateTime());// д������
			cell14.setCellStyle(styles);// ��ʽӦ���ڵ�Ԫ��
			
			XSSFCell cell15 = rowHeadCase.createCell(12);
			cell15.setCellValue(list.get(i).getLastUpdater());// д������
			cell15.setCellStyle(styles);// ��ʽӦ���ڵ�Ԫ��
			
			
		}
		// д���ļ�
		FileOutputStream output = new FileOutputStream(tempFile);// ����file�������һ�����������
		wb.write(output);// ���ĵ����ݶ���д�����������
		output.close();// �ر���
		
		return tempFile;//���ظ��ļ�����
	}

	/**
	 * д����Ԫ��, ��Ԫ����ʽҲ����������
	 * 
	 * @param value
	 *            Ҫд�뵥Ԫ���ֵ
	 * @param row
	 *            �ڼ���
	 * @param cols
	 *            �ڼ���
	 * @param wb
	 * @param isHead
	 *            true���б�ͷ false�����б�ͷ
	 */
	private void writeExcelValue(String value, XSSFRow row, int cols, XSSFWorkbook wb, boolean isHead) {
		XSSFCell cell = row.createCell(cols);//����һ����Ԫ�����
		cell.setCellType(XSSFCell.CELL_TYPE_STRING);// ���ı���ʽӦ�õ���Ԫ��
		cell.setCellValue(value);// ������value��ֵд�뵥Ԫ��
		XSSFCellStyle style = wb.createCellStyle();// ����һ����Ԫ����ʽ����
		XSSFFont font = wb.createFont();// ����һ��������ʽ����
		if (isHead) {//�����ⲿ����Ĳ��������ж��Ƿ�Ϊ��ͷ
			// style.setFillForegroundColor((short) 13);// ���ñ���ɫ
			// style.setFillPattern(CellStyle.SOLID_FOREGROUND);// ���ñ���ɫ
			style.setWrapText(true); // �����ݻ���
			style.setBorderBottom(XSSFCellStyle.BORDER_THIN); // �±߿�
			style.setBorderTop(XSSFCellStyle.BORDER_THIN);// �ϱ߿�
			style.setBorderRight(XSSFCellStyle.BORDER_THIN);// �ұ߿�
			style.setBorderLeft(XSSFCellStyle.BORDER_THIN);// ��߿�
			style.setAlignment(XSSFCellStyle.ALIGN_CENTER); // ����
			font.setBold(true); // ����Ӵ�
			font.setFontHeight(13); // �����С
		}
		style.setFont(font);//��������ʽӦ�õ���ʽ
		cell.setCellStyle(style);//����ʽӦ�õ���Ԫ��
	}
	
	
	public void readExcel(InputStream inp) throws IOException{
		XSSFWorkbook wb = new XSSFWorkbook(inp);//��inp���촴��һ���ĵ�����
		XSSFSheet sheet = wb.getSheetAt(0);
		for (int i = 2; i <= sheet.getLastRowNum(); i++) {//ѭ���У�ѭ����ֹ��sheet��������һ��
			XSSFRow row = sheet.getRow(i);
			for (int j = 0; j < 13; j++) {//ѭ������ֹ��Ϊ��6��
				XSSFCell cell = row.getCell(j);
				if(null != cell){//����Ԫ�񲻵���null��ʱ����������
					if(j == 0 || j == 2 || j == 3 || j == 4){//��Щ��������������
						cell.setCellType(XSSFCell.CELL_TYPE_NUMERIC);// �����ı���ʽ
						System.out.print(cell.getNumericCellValue()+" , "); //��ӡ���ֵ��ı���ʽ��ֵ
					}else{
						cell.setCellType(XSSFCell.CELL_TYPE_STRING);// �ַ����ı���ʽ
						System.out.print(cell.getStringCellValue()+" , ");//��ӡ�ַ����ı���ʽ��Ԫ���ֵ
					}
				}
			}
			System.out.println(" ----- ");
		}
	}

}
