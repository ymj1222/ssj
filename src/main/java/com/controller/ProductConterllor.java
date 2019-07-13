package com.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.entity.Agent;
import com.entity.Photo;
import com.entity.Product;
import com.entity.ProductBrand;
import com.entity.ProductType;
import com.service.AgentService;
import com.service.PhotoService;
import com.service.ProductService;
import com.util.CodeUtil;
import com.util.DateUtils;
import com.util.JsonUtils;
import com.util.Page;
import com.util.ProUtil;

@Controller
public class ProductConterllor {
	@Autowired
	private ProductService productService;
	@Autowired
	private AgentService agentService;
	@Autowired
	private PhotoService photoService;

	@RequestMapping("/ToProductInsert")
	public String ToInsert() {
		return "forward:/WEB-INF/views/product/insertProduct.jsp";
	}

	@RequestMapping("/ProductInsert")
	public String Insert(Product product, @RequestParam("file") MultipartFile[] files, HttpServletRequest request)
			throws IllegalStateException, IOException {

		CodeUtil code = new CodeUtil();
		product.setCode(Long.valueOf(code.getCode()));
		List<String> list = new ArrayList<String>();
		if (files != null && files.length > 0) {
			for (int i = 0; i < files.length; i++) {
				String filen = files[i].getOriginalFilename();
				String fileName = System.currentTimeMillis() + "."+filen.substring(filen.lastIndexOf(".")+1);
				String destFileName = request.getServletContext().getRealPath("") + "photo" + File.separator + fileName;
				Photo photo = new Photo();
				MultipartFile file = files[i];
				photo.setUrl("photo/"+ fileName);
				photo.setProductCode(product.getCode().toString());
				CodeUtil CodeUtil = new CodeUtil();
				photo.setCode(CodeUtil.getCode());
				photo.setDate(DateUtils.getCurrentDateString());
				photo.setCreator("�ְ�");
				photo.setUpdator("�ְ�");
				photo.setName(product.getName());
				photo.setCreateTime(DateUtils.getCurrentDateString());
				photo.setUpdateTime(DateUtils.getCurrentDateString());
				photoService.add(photo);
				// �����ļ�
				list = saveFile(request, file, list,fileName);
			}
		}

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		product.setPrice(product.getSellValue() * product.getAmount());
		if (product.getSize() == null && product.getSize() == "") {
			product.setSize("���m��");
		}
		if (product.getColor() == null && product.getColor() == "") {
			product.setColor("���m��");
		}
		Date date;
		try {
			date = sdf.parse(DateUtils.getCurrentDateTime());
			product.setIsEffective(0);
			product.setCreateTime(date);
			product.setAuditStatus(1);
			productService.insert(product);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "redirect:ToProductList";
	}

	private List<String> saveFile(HttpServletRequest request, MultipartFile file, List<String> list,String fileName) {
		// �ж��ļ��Ƿ�Ϊ��
		if (!file.isEmpty()) {
			try {

				String destFileName = request.getServletContext().getRealPath("") + "photo/"+ fileName;
				list.add(file.getOriginalFilename());
				File saveDir = new File(destFileName  );
				if (!saveDir.getParentFile().exists())
					saveDir.getParentFile().mkdirs();

				// ת���ļ�
				file.transferTo(saveDir);
				return list;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}

	@RequestMapping("/ToProductList")
	public String ToqueryAll() {
		return "forward:/WEB-INF/views/product/productList.jsp";
	}

	/**
	 * �õ���������
	 *
	 * @param model
	 * @return
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("/productselect")
	public Page select(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Page page = new Page();
		String pageNow = request.getParameter("pageNow");
		String pageSize = request.getParameter("pageSize");
		String name = request.getParameter("name");
		if (null == pageNow || "".equals(pageNow.trim())) {
			pageNow = "1";
		}
		if (null == pageSize || "".equals(pageNow.trim())) {
			pageSize = page.getPageSize() + "";
		}
		int pageCount = 0;
		pageCount = productService.selectproductlistcount(name.replaceAll("_", "\\\\_"), Integer.valueOf(pageSize));
		List<Product> list = productService.selectproductlist(name.replaceAll("_", "\\\\_"), Integer.valueOf(pageNow),
				Integer.valueOf(pageSize));
		response.setCharacterEncoding("utf-8");
		page.setList(list);
		page.setPageCount(pageCount);
		page.setPageNow(Integer.parseInt(pageNow));
		return page;
	}

	/**
	 *
	 * @param code
	 * @param model
	 * @return ����Ҫ�޸ĵĔ���
	 */
	@RequestMapping("/queryProduct")
	public String queryProduct(String code, Model model) {
		Product product = productService.updatequery(Long.valueOf(code));
		model.addAttribute("name", product.getName());
		model.addAttribute("price", product.getPrice());
		model.addAttribute("amount", product.getAmount());
		model.addAttribute("sell", product.getSellValue());
		model.addAttribute("market", product.getMarketValue());
		model.addAttribute("size", product.getSize());
		model.addAttribute("color", product.getColor());
		model.addAttribute("code", product.getCode());
		return "forward:/WEB-INF/views/product/updateProduct.jsp";
	}

	@RequestMapping("/ProductUpdate")
	public String Update(Product product, HttpServletRequest request) {
		product.setPrice(product.getSellValue() * product.getAmount());
		if (product.getSize() == null && product.getSize() == "") {
			product.setSize("���m��");
		}
		if (product.getColor() == null && product.getColor() == "") {
			product.setColor("���m��");
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = sdf.parse(DateUtils.getCurrentDateTime());
			product.setLastUpdateTime(date);
			productService.updateSave(product);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return "forward:ToProductList";
	}

	@RequestMapping("/ToproductselectStatus")
	public String ToproductselectStatus() {
		return "forward:/WEB-INF/views/product/productAuditList.jsp";
	}

	@ResponseBody
	@RequestMapping("/productselectStatus")
	public Page selectstatus(String status, Model model, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		Page page = new Page();
		String pageNow = request.getParameter("pageNow");
		String pageSize = request.getParameter("pageSize");
		if (null == pageNow || "".equals(pageNow.trim())) {
			pageNow = "1";
		}
		if (null == pageSize || "".equals(pageNow.trim())) {
			pageSize = page.getPageSize() + "";
		}
		int pageCount = 0;
		pageCount = productService.querystatuscount(Integer.valueOf(status), Integer.valueOf(pageSize));
		List<Product> list = productService.querystatus(Integer.valueOf(status), Integer.valueOf(pageNow),
				Integer.valueOf(pageSize));
		response.setCharacterEncoding("utf-8");
		page.setList(list);
		page.setPageCount(pageCount);
		page.setPageNow(Integer.parseInt(pageNow));
		return page;
	}

	@RequestMapping("/productModifyStatus")
	public String productModifyStatus(String status, String code) {
		productService.updateStatus(Long.valueOf(status), Long.valueOf(code));
		return "redirect:ToproductselectStatus";
	}

	@RequestMapping("/productModifyRecover")
	public String productModifyRecover(String status, String code) {
		productService.updateStatus(Long.valueOf(status), Long.valueOf(code));
		return "redirect:Toproductselectrecover";
	}

	@RequestMapping("/Toproductselectrecover")
	public String Toproductselectrecover() {
		return "forward:/WEB-INF/views/product/productRecoverList.jsp";
	}

	@ResponseBody
	@RequestMapping("/both")
	public Page both(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Page page = new Page();
		Product product = new Product();
		String pageNow = request.getParameter("pageNow");
		String pageSize = request.getParameter("pageSize");
		String effective = request.getParameter("isEffective");
		String name = request.getParameter("name");
		String code = request.getParameter("code");
		String type = request.getParameter("type");
		String brandCode = request.getParameter("brandCode");
		String auditStatus = request.getParameter("auditStatus");
		if (effective != null && effective != "") {
			product.setIsEffective(Integer.valueOf(effective));
		}
		if (code != null && code != "") {
			product.setCode(Long.valueOf(code));
		}
		if (name != null && name != "") {
			product.setName(name.replaceAll("_", "\\\\_"));
		}
		if (type != null && type != "") {
			product.setType(Long.valueOf(type));
		}
		if (brandCode != null && brandCode != "") {
			product.setBrandCode(Long.valueOf(brandCode));
		}
		if (auditStatus != null && auditStatus != "") {
			product.setAuditStatus(Integer.valueOf(auditStatus));
		}
		if (null == pageNow || "".equals(pageNow.trim())) {
			pageNow = "1";
		}
		if (null == pageSize || "".equals(pageNow.trim())) {
			pageSize = page.getPageSize() + "";
		}
		int pageCount = 0;

		pageCount = productService.bothtotal(product, Integer.valueOf(pageSize));
		List<Product> list = productService.both(product, Integer.valueOf(pageNow), Integer.valueOf(pageSize));
		response.setCharacterEncoding("utf-8");
		page.setList(list);
		page.setPageCount(pageCount);
		page.setPageNow(Integer.parseInt(pageNow));
		return page;
	}

	@RequestMapping("/Toboth")
	public String Toboth() {
		return "forward:/WEB-INF/views/product/productBothList.jsp";
	}

	@ResponseBody
	@RequestMapping(value = "/queryagentCode22")
	public List<Agent> queryagentCode(HttpServletResponse response) throws IOException {
		return agentService.queryagentCode();
	}

	@RequestMapping("/Productdetail")
	public String detail(String code, Model model) {
		Product product = productService.updatequery(Long.valueOf(code));
		model.addAttribute("name", product.getName());
		model.addAttribute("price", product.getPrice());
		model.addAttribute("amount", product.getWarehouse().getAmount());
		model.addAttribute("sell", product.getSellValue());
		model.addAttribute("market", product.getMarketValue());
		model.addAttribute("size", product.getSize());
		model.addAttribute("color", product.getColor());
		model.addAttribute("code", product.getCode());
		model.addAttribute("induction", product.getInduction());
		List<Photo> photos = photoService.getphotoUrl(code);
		for (int i = 0; i < photos.size(); i++) {
			String photo = "photo";
			Integer a = i + 1;
			String b = a.toString();
			photo = photo + b;
			model.addAttribute(photo, photos.get(i).getUrl());
		}
		return "forward:qdetails.jsp";
	}

	@ResponseBody
	@RequestMapping("/selectphoto")
	public List<Photo> selectphoto(Model model, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String code = request.getParameter("code");
		List<Photo> photos = photoService.getphotoUrl(code);
		return photos;
	}

	@ResponseBody
	@RequestMapping("/selecttoqsale")
	public List<Product> selecttoqsale(Model model, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		List<Product> list = productService.selecttoqsale();
		return list;
	}

	@ResponseBody
	@RequestMapping("/selecttoproject")
	public List<Product> selecttoproject(Model model, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String code = request.getParameter("code");
		List<Product> list = productService.selecttoproject(Long.valueOf(code));
		response.setCharacterEncoding("utf-8");
		return list;
	}

	@ResponseBody
	@RequestMapping("/selecttoproduct")
	public List<Product> selecttoproduct(Model model, HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		String name = request.getParameter("name");
		name = new String(name.getBytes("iso-8859-1"), "utf-8");
		List<Product> list = productService.selecttoproduct(name.replaceAll("_", "\\\\_"));
		response.setCharacterEncoding("utf-8");
		return list;
	}
}
