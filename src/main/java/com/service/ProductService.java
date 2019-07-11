package com.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.AgentDao;
import com.dao.ByProductDao;
import com.dao.ProductDao;
import com.dao.ProductTypeDao;
import com.dao.WarehouseDao;
import com.dao.WarehousingAndOutDao;
import com.entity.Agent;
import com.entity.ByProduct;
import com.entity.Photo;
import com.entity.Product;
import com.entity.ProductBrand;
import com.entity.ProductType;
import com.entity.Warehouse;
import com.entity.WarehousingAndOut;
import com.util.DateUtils;

@Service
public class ProductService {
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ProductBrandService brandservice;
	@Autowired
	private ProductTypeService typeservice;
	@Autowired
	private ByProductDao byproductDao;
	@Autowired
	private WarehouseDao WarehouseDao;
	@Autowired
	private WarehousingAndOutDao WarehousingAndOutDao;
	@Autowired
	private AgentDao agentDao;
	@Autowired
	private ProductTypeDao productTypeDao;
	@Autowired
	private PhotoService photoService;

	@Transactional
	public void insert(Product product) {
		ProductBrand brand = brandservice.select(Long.valueOf(product.getBrand().getCode()));
		ProductType type = typeservice.select(Long.valueOf(product.getProducttype().getCode()));
		Agent agent = agentDao.find(product.getAgent().getId());
		product.setBrandCode(Long.valueOf(product.getBrand().getCode()));
		product.setType(Long.valueOf(product.getProducttype().getCode()));
		product.setAgentCode(Long.valueOf(agent.getCode()));
		product.setAgent(agent);
		product.setBrand(brand);
		product.setProducttype(type);
		productDao.insert(product);
		agent.getProduct().add(product);
		type.getProduct().add(product);
		brand.getProduct().add(product);
	}

	/**
	 * 
	 * @param page
	 * @return 商品編緝的數據
	 */
	public List<Product> selectproductlist(String name, int PageNow, int PageSize) {
		int now = (PageNow - 1) * PageSize;
		List<Product> list = productDao.selectproductlist(name, now, PageSize);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getAgent() != null) {
				list.get(i).setAgentName(list.get(i).getAgent().getName());
			}
			if (list.get(i).getType() != null) {
				list.get(i).setTypeName(list.get(i).getProducttype().getName());
			}
			if (list.get(i).getBrand() != null) {
				list.get(i).setBrandName(list.get(i).getBrand().getName());
			}
		}
		return list;
	}

	public Integer selectproductlistcount(String name, int size) {
		int pageCount = 0;
		int rowCount = productDao.selectproductlistcount(name).intValue();
		if ((rowCount % size) == 0) {
			pageCount = rowCount / size;
		} else {
			pageCount = rowCount / size + 1;
		}
		return pageCount;
	}

	/**
	 * 
	 * @param code
	 * @return 回顯
	 */
	public Product updatequery(Long code) {
		return productDao.updatequery(code);
	}

	/**
	 * 修改商品
	 * 
	 * @param product
	 */
	@Transactional
	public void updateSave(Product product) {
		ProductBrand brand = brandservice.select(Long.valueOf(product.getBrand().getName()));
		ProductType type = typeservice.select(Long.valueOf(product.getProducttype().getName()));
		Agent agent = agentDao.find(product.getAgent().getId());
		product.setAgent(agent);
		product.setBrand(brand);
		product.setAgentCode(Long.valueOf(agent.getCode()));
		product.setProducttype(type);
		product.setBrandCode(Long.valueOf(product.getBrand().getCode()));
		product.setType(Long.valueOf(product.getProducttype().getCode()));
		productDao.updateSave(product);
		agent.getProduct().add(product);
		type.getProduct().add(product);
		brand.getProduct().add(product);
	}

	/**
	 * 
	 * @param status
	 * @param pageNow
	 * @param pageSize
	 * @return 審批狀態
	 */
	public List<Product> querystatus(int status, int pageNow, int pageSize) {
		int now = (pageNow - 1) * pageSize;
		List<Product> list = productDao.querystatus(status, now, pageSize);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getAgent() != null) {
				list.get(i).setAgentName(list.get(i).getAgent().getName());
			}
			if (list.get(i).getType() != null) {
				list.get(i).setTypeName(list.get(i).getProducttype().getName());
			}
			if (list.get(i).getBrand() != null) {
				list.get(i).setBrandName(list.get(i).getBrand().getName());
			}
		}
		return list;
	}

	public Integer querystatuscount(int status, int size) {
		int pageCount = 0;
		int rowCount = productDao.querystatuscount(status).intValue();
		if ((rowCount % size) == 0) {
			pageCount = rowCount / size;
		} else {
			pageCount = rowCount / size + 1;
		}
		return pageCount;
	}

	@Transactional
	public void updateStatus(long status, long code) {
		productDao.updateStatus(status, code);
		if (status == 3) {
			ByProduct byProduct = new ByProduct();
			Product product = productDao.updatequery(code);
			WarehousingAndOut WAO = new WarehousingAndOut();
			Warehouse warehouse = new Warehouse();
			WAO.setType(2);
			WAO.setAmount(product.getAmount());
			WAO.setProductCode(code);
			product.setIsEffective(1);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date;
			try {
				date = sdf.parse(DateUtils.getCurrentDateTime());
				product.setShelftime(DateUtils.getFureTimeByDay(date, 7));
				warehouse.setAmount(product.getAmount());
				warehouse.setProductCode(product.getCode());
				warehouse.setCreateTime(date);
				WAO.setCreateTime(date);
				WAO.setDate(date);
				byProduct.setProductCode(code);
				byProduct.setIntegral(product.getSellValue() / 95);
				byProduct.setGoldCoin(product.getSellValue() / 95);
				byProduct.setCreateTime(date);
				productDao.updateShelftime(product);
				byProduct.setProduct(product);
				byproductDao.insert(byProduct);
				warehouse.getWao().add(WAO);
				warehouse.setProduct(product);
				WarehouseDao.insert(warehouse);
				product.setWarehouse(warehouse);
				product.getWao().add(WAO);
				WAO.setProduct(product);
				WAO.setWarehouse(warehouse);
				WarehousingAndOutDao.insert(WAO);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}

	public List<Product> both(Product product, int pageNow, int pageSize) {
		int now = (pageNow - 1) * pageSize;
		List<Product> list = productDao.both(product, now, pageSize);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getAgent() != null) {
				list.get(i).setAgentName(list.get(i).getAgent().getName());
			}
			if (list.get(i).getType() != null) {
				list.get(i).setTypeName(list.get(i).getProducttype().getName());
			}
			if (list.get(i).getBrand() != null) {
				list.get(i).setBrandName(list.get(i).getBrand().getName());
			}
		}
		return list;
	}

	public Integer bothtotal(Product product, int size) {
		int pageCount = 0;
		int rowCount = productDao.bothtotal(product).intValue();
		if ((rowCount % size) == 0) {
			pageCount = rowCount / size;
		} else {
			pageCount = rowCount / size + 1;
		}
		return pageCount;
	}

	public List<Product> selecttoqsale() {
		List<Product> list = productDao.selecttoqsale();
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setTypeName(list.get(i).getProducttype().getName());
			List<Photo> photos = photoService.getphotoUrl(list.get(i).getCode().toString());
			if (photos.size() <= 0) {
				list.get(i).setBrandName("photo/main_1500 (3).jpg");
			} else {
				list.get(i).setBrandName(photos.get(0).getUrl());
			}
		}
		return list;
	}

	public List<Product> selecttoproject(Long code) {
		List<Product> list = productDao.selecttoproject(code);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setTypeName(list.get(i).getProducttype().getName());
			List<Photo> photos = photoService.getphotoUrl(list.get(i).getCode().toString());
			if (photos.size() <= 0) {
				list.get(i).setBrandName("photo/main_1500 (3).jpg");
			} else {
				list.get(i).setBrandName(photos.get(0).getUrl());
			}
		}
		return list;
	}

	public List<Product> selecttoproduct(String name) {
		List<Product> list = productDao.selecttoproduct(name);
		for (int i = 0; i < list.size(); i++) {
			list.get(i).setTypeName(list.get(i).getProducttype().getName());
			List<Photo> photos = photoService.getphotoUrl(list.get(i).getCode().toString());
			if (photos.size() <= 0) {
				list.get(i).setBrandName("photo/main_1500 (3).jpg");
			} else {
				list.get(i).setBrandName(photos.get(0).getUrl());
			}
		}
		return list;
	}

	public List<Product> selectoption() {
		return productDao.selectoption();
	}
}