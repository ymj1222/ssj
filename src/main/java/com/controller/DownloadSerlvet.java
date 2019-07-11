package com.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.entity.Orders;
import com.service.OrdersDownLoadService;
import com.service.OrdersService;
import com.util.JsonUtils;


@Controller //ע�� (Լ����������)
public class DownloadSerlvet{
	@Autowired
	private OrdersService orderService;
	@RequestMapping("/findordersDownLoad")
	public String findOrders() {
		return "forward:/WEB-INF/views/orders/orderDownload.jsp";
	}
	@RequestMapping("/ordersDownLoad")
	public void fuck(HttpServletRequest req, HttpServletResponse resp,Orders orders) throws ServletException, IOException {
		OrdersDownLoadService excelService = new OrdersDownLoadService();
		File tempFile = null;
		try {
			tempFile = excelService.writeExcel(orders);
		} catch (Exception e) {
			e.printStackTrace();
		}
		FileInputStream fis = new FileInputStream(tempFile);// ��������������
		String filename = URLEncoder.encode(tempFile.getName(), "utf-8"); // ��������ļ������غ����������
																			// getName()����ļ�������
		byte[] b = new byte[fis.available()];// ����һ������Ϊfis�����ֽڵ�byte����
		fis.read(b); // �����ݶ�������
		resp.setCharacterEncoding("utf-8"); // ��������ļ����ݵ���������
		resp.setHeader("Content-Disposition", "attachment; filename=" + filename + ""); // ����һ��ָ���ļ������صĴ���
		// ��ȡ��Ӧ�������������
		ServletOutputStream out = resp.getOutputStream();
		// ���
		out.write(b);
		out.flush();
		out.close();
		fis.close();
	}
	@RequestMapping(value = "/ordersDownLoadQuery")
	public String updateQuery( String code,HttpServletResponse response, Model model) throws IOException {
		Orders orders=orderService.updatequery(code);
		model.addAttribute("code", orders.getCode());
		model.addAttribute("productCode", orders.getProductCode());
		model.addAttribute("productName", orders.getProductName());
		model.addAttribute("price", orders.getPrice());
		model.addAttribute("amount", orders.getAmount());
		model.addAttribute("receivingAddress", orders.getReceivingAddress());
		model.addAttribute("phone", orders.getPhone());
		model.addAttribute("consignee", orders.getConsignee());
		model.addAttribute("deliveryTime", orders.getDeliveryTime());
		model.addAttribute("isconfirmreceipt", orders.getIsconfirmreceipt());
		model.addAttribute("receivingTime", orders.getReceivingTime());
		model.addAttribute("usersCode", orders.getUsersCode());
		model.addAttribute("createTime",orders.getCreateTime());
		model.addAttribute("createor",orders.getCreateor());
		model.addAttribute("lastUpdater",orders.getLastUpdater());
		model.addAttribute("lastUpdateTime",orders.getLastUpdateTime());
		String p=JsonUtils.beanToJson(orderService.updatequery(code));
		
		response.getWriter().write(p);
		return "redirect:ordersDownLoad";
	}
}
