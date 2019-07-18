package com.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.Orders;
import com.entity.Product;
import com.service.AccountWayBillService;
//import com.service.AccountWayBillService;
import com.service.OrdersService;
import com.service.ProductService;
import com.service.UsersService;
import com.service.WarehousingAndOutService;
//import com.service.ProductService;
//import com.service.UsersService;
//import com.service.WarehousingAndOutService;
import com.util.DateUtils;
import com.util.GetNameUtil;
import com.util.JsonUtils;
import com.util.Page;
import com.util.Pageh;

@Controller
public class OrdersConterllor {
	@Autowired
	private OrdersService orderService;
	@Autowired
	ProductService ps;
	@Autowired
	WarehousingAndOutService ws;

	@Autowired
	AccountWayBillService as;

	@Autowired
	UsersService us;

	@RequestMapping("/finOrdersAdd")
	public String findadd() {
		return "forward:/WEB-INF/views/orders/ordersAdd.jsp";
	}

	@RequestMapping("/finMyOrdersAdd")
	public String myOrdersfindadd(Orders orders, Model model, HttpServletRequest request) {
		model.addAttribute("createor", GetNameUtil.getName(request));
		model.addAttribute("lastUpdater", GetNameUtil.getName(request));
		model.addAttribute("amount", orders.getAmount());
		model.addAttribute("productCode", orders.getProductCode());
		model.addAttribute("price", orders.getPrice());
		model.addAttribute("productName", orders.getProductName());
		return "forward:/WEB-INF/views/orders/myOrdersAdd.jsp";
	}

	@RequestMapping("/myOrders")
	public String myOrders() {
		return "forward:/WEB-INF/views/orders/myOrders.jsp";
	}

	@RequestMapping("/myOrdersFindUpdate")
	public String myOrdersUpdate() {
		return "forward:/WEB-INF/views/orders/myOrdersUpdate.jsp";
	}

	/**
	 * 找到列表页面
	 *
	 * @return
	 */
	@RequestMapping("/findOrdersList")
	public String findselect() {
		return "forward:/WEB-INF/views/orders/ordersList.jsp";
	}

	/**
	 * 找到发货页面
	 *
	 * @return
	 */
	@RequestMapping("/findOrdersOut")
	public String findOut() {
		return "forward:/WEB-INF/views/orders/ordersOut.jsp";
	}

	/**
	 * 数据添加
	 *
	 * @param orders
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/ordersAdd", method = RequestMethod.POST)
	public String add(Orders orders, Model model, HttpServletRequest request) {
		HttpSession hs = request.getSession();
		Product p = ps.updatequery(Long.valueOf(orders.getProductCode()));
		String account = (String) hs.getAttribute("account");
		String usersCode=us.queryByAccount(account);
		orders.setUsersCode(usersCode);
		orders.setLastUpdater(GetNameUtil.getName(request));
		orders.setCreateor(GetNameUtil.getName(request));
		long bs = System.currentTimeMillis();
		String code = Long.toString(bs);
		orders.setCode(code);
		orders.setCreateTime(DateUtils.getCurrentDateString());
		orders.setLastUpdateTime(DateUtils.getCurrentDateString());
		orders.setIsOutOfStock("0");
		orders.setIsconfirmreceipt(0);
		orders.setOrderState(1);
		orders.setProduct(p);
		orderService.add(orders);
		return "redirect:findOrdersList";
	}

	/**
	 * 添加订单
	 *
	 * @param orders
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/myordersAdd", method = RequestMethod.POST)
	public String myOrdersadd(Orders orders, Model model, HttpServletRequest request) {
        try {
		HttpSession hs = request.getSession();
		String account = (String) hs.getAttribute("account");
		String usersCode=us.queryByAccount(account);
		orders.setUsersCode(usersCode);
		orders.setLastUpdater(GetNameUtil.getName(request));
		orders.setCreateor(GetNameUtil.getName(request));
		long bs = System.currentTimeMillis();
		String code = Long.toString(bs);
		orders.setCode(code);
		orders.setCreateTime(DateUtils.getCurrentDateString());
		orders.setLastUpdateTime(DateUtils.getCurrentDateString());
		orders.setIsOutOfStock("0");
		orders.setIsconfirmreceipt(0);
		orders.setOrderState(1);
		String n = "未收";
            n = new String(n.getBytes("gbk"),"utf-8");
        orders.setReceivingTime(n);
		orderService.add(orders);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
		return "redirect:myOrders";
	}

	/**
	 * 得到所有数据
	 *
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("/ordersSelect")
	public void select(Model model, HttpServletRequest request, HttpServletResponse response,String pageNow,String pageSize) throws IOException {
		Page page = new Page();
		HttpSession hs = request.getSession();
		String account = (String) hs.getAttribute("account");
		String usersCode=us.queryByAccount(account);
		String sname = request.getParameter("sname");
		String isOutOfStock=request.getParameter("isOutOfStock");
        if (null == pageNow || "".equals(pageNow.trim())) {
            pageNow = "1";
        }
        if (null == pageSize || "".equals(pageNow.trim())) {
            pageSize = "3";
        }
        int pageNow1=Integer.parseInt(pageNow)-1;
        Pageable pageable= PageRequest.of(pageNow1,Integer.parseInt(pageSize), Sort.by(Sort.Direction.DESC, "id"));
        org.springframework.data.domain.Page<Orders> list=orderService.queryAll(sname,usersCode,isOutOfStock,pageable);
        List<Orders> l= list.getContent();
		page.setPageCount(list.getTotalPages());
		page.setList(l);
        response.setCharacterEncoding("utf-8");
		page.setPageNow(Integer.parseInt(pageNow));

		String parseJSON = JsonUtils.beanToJson(page);
		response.getWriter().write(parseJSON);

	}

	@RequestMapping(value = "/finOrdersUpdate")
	public String findUpdate(Orders orders, HttpServletRequest request) {
		request.setAttribute("code", orders.getCode());
		return "forward:/WEB-INF/views/orders/ordersUpdateSave.jsp";
	}

	/**
	 * 得到要修改的数据
	 *
	 * @param code
	 * @param model
	 * @return
	 */
	@RequestMapping("/ordersUpdate")
	public String update(String code, Model model) {
		Orders orders = orderService.updatequery(code);
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
		return "forward:/WEB-INF/views/orders/ordersUpdateSave.jsp";
	}

	@RequestMapping("/myOrdersUpdate")
	public String myOrdersupdate(String code, Model model) {
		Orders orders = orderService.updatequery(code);
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
		return "forward:/WEB-INF/views/orders/myOrdersUpdate.jsp";
	}

	/**
	 * 修改数据
	 *
	 * @param orders
	 * @return
	 */
	@RequestMapping(value = "/ordersUpdateSave")
	public String updateSave(Orders orders) {
		orderService.update(orders);
		return "redirect:findOrdersList";
	}

	@RequestMapping(value = "/myOrdersUpdateSave")
	public String myOrdersupdateSave(Orders orders) {
		orderService.update(orders);
		return "redirect:myOrders";
	}

	@RequestMapping(value = "/ordersUpdateQuery")
	public String updateQuery(String code, HttpServletResponse response) throws IOException {
		String p = JsonUtils.beanToJson(orderService.updatequery(code));
		response.getWriter().write(p);
		return "redirect:ordersSelect";
	}

	@RequestMapping(value = "/myOrdersUpdateQuery")
	public Orders myOrdersUpdateQuery(String code, HttpServletResponse response) throws IOException {
		Orders p = orderService.updatequery(code);
		return p;
	}

	/**
	 * 发货
	 *
	 * @param code
	 * @param orders
	 * @return
	 */
	@RequestMapping("/ordersOut")
	public String ordersOut(String code, Orders orders, String proCode, HttpServletRequest request) {
		String logisticsNumber =as.addAccountWayBill(orders);
		orders.setLogisticsNumber(logisticsNumber);
		Integer amount = Integer.valueOf(orders.getAmount());
		String productCode = orders.getProductCode();
		Long s = Long.valueOf(productCode);
		Date createTime = Date.valueOf(DateUtils.getCurrentDateString());
		Long ordersCode = Long.valueOf(orders.getCode());
		String creator = GetNameUtil.getName(request);
		ws.stock(amount, s, createTime, creator,ordersCode);
		orders.setIsOutOfStock("1");
		orderService.ordersOut(code, orders.getIsOutOfStock(),logisticsNumber);
		return "redirect:/findOrdersOut";
	}

	/**
	 * 评价
	 *
	 * @param productCode
	 * @param m
	 * @return
	 */
	@RequestMapping("/ordersOutPj")
	public String ordersOutPj(String productCode, Model m) {
		m.addAttribute("productCode", productCode);
		return "forward:qevaluation.jsp";
	}

	/**
	 * 收货
	 *
	 * @param code
	 * @param orders
	 * @return
	 */
	@RequestMapping("/findOrderList")
	public String fIsconfirmreceipt(String code, Orders orders) {
		return "forward:/WEB-INF/views/orders/ordersList.jsp";
	}

	@RequestMapping("/ordersUpdateIs")
	public String updateIsconfirmreceipt(String code, Orders orders) {
		orders.setIsconfirmreceipt(1);
		orders.setReceivingTime(DateUtils.getCurrentDateTime());
		orderService.updateIs(code, orders.getIsconfirmreceipt(), orders.getReceivingTime());
		return "redirect:/findOrderList";
	}

	/**
	 * 我的订单收货
	 *
	 * @param code
	 * @param orders
	 * @return
	 */
	@RequestMapping("/myOrdersfind")
	public String myfIsconfirmreceipt(String code, Orders orders) {
		return "forward:/WEB-INF/views/orders/myOrders.jsp";
	}

	/**
	 * 我的订单收货
	 *
	 * @param code
	 * @param order
	 * @return
	 */
	@RequestMapping("/myordersUpdateIs")
	public String myUpdateIs(String code, Orders orders) {
		orders.setIsconfirmreceipt(1);
		orders.setReceivingTime(DateUtils.getCurrentDateTime());
		orderService.updateIs(code, orders.getIsconfirmreceipt(), orders.getReceivingTime());
		return "redirect:/myOrdersfind";
	}

	/**
	 * 发货后取消订单
	 *
	 * @param code
	 * @param order
	 * @return
	 */
	@RequestMapping("/ordersCanel")
	public String canelOrders(String code, String amount) {
		Long c = Long.valueOf(code);
		ws.cancel(c);
		Orders orders = new Orders();
		orders.setOrderState(0);
		orderService.ordersCanel(code, orders.getOrderState());
		return "redirect:/findOrdersList";
	}

	/**
	 * 未发货取消订单
	 *
	 * @param code
	 * @param order
	 * @return
	 */
	@RequestMapping("/Canel")
	public String canel(String code, String amount) {
		Orders orders = new Orders();
		orders.setOrderState(0);
		orderService.ordersCanel(code, orders.getOrderState());
		return "redirect:/findOrdersList";
	}

	/*@RequestMapping("/getProduct")
	public void getProduct(HttpServletResponse response, HttpServletRequest request) throws IOException {
		 String list = JsonUtils.listToJson(ps.option());
		 response.getWriter().write(list);
	}*/

	/**
	 * 订单历史
	 *
	 * @param code
	 * @param order
	 * @return
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/getOrders")
	public List<Orders> selectOrders(HttpServletRequest request, HttpServletResponse response, String usersCode)
			throws IOException {
		List<Orders> list = orderService.getOrders(usersCode);
		response.setCharacterEncoding("utf-8");
		return list;
	}

	/**
	 * 到货时间
	 *
	 * @param code
	 * @param order
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/UpdatedeliveryTime")
	public void updateDeliveryTime(String deliveryTime, String logisticsNumber) {
		orderService.updateDeliveryTime(deliveryTime, logisticsNumber);
	}

}
