package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.entity.Special;
import com.service.SpecialService;
import com.util.DateUtils;
import com.util.JsonUtils;
import com.util.Page;
import com.util.Pageh;

@Controller
public class SpecialConterllor {
	@Autowired
	private SpecialService specialService;

	@RequestMapping("/finSpecialAdd")
	public String findadd() {
		return "forward:/WEB-INF/views/special/specialAdd.jsp";
	}

	/**
	 * �ҵ��б�ҳ��
	 * 
	 * @return
	 */
	@RequestMapping("/findSpecialList")
	public String findselect() {
		return "forward:/WEB-INF/views/special/specialList.jsp";
	}

	/**
	 * �������
	 * 
	 * @param ter
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/specialAdd", method = RequestMethod.POST)
	public String add(Special special, Model model) {
		long bs = System.currentTimeMillis();
		String code = Long.toString(bs);
		special.setCode(code);
		special.setCreateTime(DateUtils.getCurrentDateString());
		special.setLastUpdateTime(DateUtils.getCurrentDateString());
		specialService.add(special);
		return "forward:/WEB-INF/views/special/specialList.jsp";
	}

	/**
	 * ����idɾ������
	 * 
	 * @param id
	 */
	@RequestMapping(value = "/specialDelete/{code}", method = RequestMethod.DELETE)
	public String delete(@PathVariable("code") String code) {
		specialService.delete(code);
		return "redirect:/WEB-INF/views/special/specialList.jsp";
	}

	/**
	 * �õ���������
	 * 
	 * @param model
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping("/specialSelect")
	public Page select(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Page page = new Page();
		String sname = request.getParameter("sname");
		String pageNow = request.getParameter("pageNow");
		String pageSize = request.getParameter("pageSize");
		if (null == pageNow || "".equals(pageNow.trim())) {
			pageNow = "1";
		}
		if (null == pageSize || "".equals(pageNow.trim())) {
			pageSize = page.getPageSize() + "";
		}
		int pageCount = 0;
		Pageh pages = new Pageh();
		pages.setPageNow(Integer.parseInt(pageNow));
		pages.setPageSize(Integer.parseInt(pageSize));
		pages.setObject1(sname);
		pageCount = specialService.gettotal(Integer.parseInt(pageSize), sname);
		List<Special> list = specialService.select(pages);
		/*for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getType().equals("1")) {
				list.get(i).setType("������");
			}else if(list.get(i).getType().equals("2")) {
				list.get(i).setType("��װ��");
			}else if(list.get(i).getType().equals("3")) {
				list.get(i).setType("�Ҿ���");
			}else if(list.get(i).getType().equals("4")) {
				list.get(i).setType("�����");
			}else if(list.get(i).getType().equals("5")) {
				list.get(i).setType("ʳƷ��");
			}
		}*/
		response.setCharacterEncoding("utf-8");
		page.setList(list);
		page.setPageCount(pageCount);
		page.setPageNow(Integer.parseInt(pageNow));
		return page;
	}

	@RequestMapping("/finSpecialUpdate")
	public String findUpdate() {
		return "forward:/WEB-INF/views/special/specialUpdateSave.jsp";
	}

	/**
	 * �õ�Ҫ�޸ĵ�����
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/specialUpdate")
	public String update(String code, Model model) {
		Special special = specialService.updatequery(code);
		model.addAttribute("code", special.getCode());
		model.addAttribute("name", special.getName());
		model.addAttribute("type", special.getType());
		return "forward:/WEB-INF/views/special/specialUpdateSave.jsp";
	}

	/**
	 * �޸�����
	 * 
	 * @param id
	 * @param order
	 * @return
	 */
	@RequestMapping("/specialUpdateSave")
	public String updateSave(Special special) {
		specialService.update(special);
		return "redirect:findSpecialList";
	}

	@RequestMapping(value = "/specialUpdateQuery")
	public Special updateQuery(String code, HttpServletResponse response) throws IOException {
		Special p = specialService.updatequery(code);
		System.out.println(p);
		return p;
	}
}
