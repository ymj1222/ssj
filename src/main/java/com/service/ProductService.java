package com.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
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

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

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
		Agent agent = agentDao.getById(product.getAgent().getId());
		product.setBrandName(brand.getName());
		product.setTypeName(type.getName());
		product.setAgentName(agent.getName());
		product.setBrandCode(Long.valueOf(product.getBrand().getCode()));
		product.setType(Long.valueOf(product.getProducttype().getCode()));
		product.setAgentCode(Long.valueOf(agent.getCode()));
		product.setAgent(agent);
		product.setBrand(brand);
		product.setProducttype(type);
		productDao.save(product);
		agent.getProduct().add(product);
		type.getProduct().add(product);
		brand.getProduct().add(product);
	}

	/**
	 * 
	 * @param
	 * @return 商品編緝的數據
	 */
	public Page<Product> selectproductlist(String name, int pageNow, int pageSize) {
		Page<Product> page;
		Sort sort = new Sort(Sort.Direction.DESC, "code");
		PageRequest pageable =PageRequest.of(pageNow-1, pageSize, sort);
		Specification<Product> specification = new Specification<Product>() {
			@Override
			public Predicate toPredicate(Root<Product> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				if(name !=null && name !="") {
					Predicate predicate = cb.like(root.get("name").as(String.class),"%"+name+"%");
					query.where(root.get("auditStatus").as(Integer.class).in(1,2),predicate);
					return null;
				}
				query.where(root.get("auditStatus").as(Integer.class).in(1,2));
				return null;
			}
		};
		page= productDao.findAll(specification,pageable);
		return page;
	}


	/**
	 * 
	 * @param code
	 * @return 回顯
	 */
	public Product updatequery(Long code) {
		return productDao.getProductByCode(code);
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
		Agent agent = agentDao.getById(product.getAgent().getId());
		product.setBrandName(brand.getName());
		product.setTypeName(type.getName());
		product.setAgentName(agent.getName());
		product.setAgent(agent);
		product.setBrand(brand);
		product.setProducttype(type);
		productDao.updateSave(product.getName(),product.getPrice(),Long.valueOf(product.getProducttype().getCode()),Long.valueOf(product.getBrand().getCode()),product.getSize(),product.getSellValue(),product.getMarketValue(),product.getColor(),product.getInduction(),product.getCode(),product.getBrandName(),product.getTypeName(),product.getAgentName());
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
	public Page<Product> querystatus(int status, int pageNow, int pageSize) {
		Page<Product> page;
		Sort sort = new Sort(Sort.Direction.DESC, "code");
		PageRequest pageable =PageRequest.of(pageNow-1, pageSize, sort);
		Specification<Product> specification = new Specification<Product>() {
			@Override
			public Predicate toPredicate(Root<Product> root,
										 CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.equal(root.get("auditStatus").as(Integer.class),status);
				query.where(predicate);
				return null;

			}
		};
		page= productDao.findAll(specification,pageable);

		List<Product> list = page.getContent();
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
		return page;
	}



	@Transactional
	public void updateStatus(Integer status, long code) {
		productDao.updateStatus(status, code);
		if (status == 3) {
			ByProduct byProduct = new ByProduct();
			Product product = productDao.getProductByCode(code);
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
				productDao.updateShelftime(product.getShelftime(),product.getIsEffective(),product.getCode());
				byProduct.setProduct(product);
				byproductDao.save(byProduct);
				warehouse.getWao().add(WAO);
				warehouse.setProduct(product);
				WarehouseDao.save(warehouse);
				product.setWarehouse(warehouse);
				product.getWao().add(WAO);
				WAO.setProduct(product);
				WAO.setWarehouse(warehouse);
				WarehousingAndOutDao.save(WAO);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}

	public Page<Product> both(Product product, int pageNow, int pageSize) {
		Page<Product> page;
		Sort sort = new Sort(Sort.Direction.DESC, "code");
		PageRequest pageable =PageRequest.of(pageNow-1, pageSize, sort);
		Specification<Product> specification = new Specification<Product>() {
			@Override
			public Predicate toPredicate(Root<Product> root,
										 CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				Predicate predicate = cb.conjunction();
				if (product.getName() != null && product.getName() != "") {
					Predicate predicate1 = cb.like(root.get("name"),"%"+product.getName()+"%");
					predicate.getExpressions().add(predicate1);				}
				if (product.getCode() != null && product.getCode() != 0) {
					Predicate predicate2 = cb.equal(root.get("code"),product.getCode());
					predicate.getExpressions().add(predicate2);
				}
				if (product.getType() != null && product.getType() != 0) {
					Predicate predicate3 = cb.equal(root.get("type"),product.getType());
					predicate.getExpressions().add(predicate3);

				}if (product.getBrandCode() != null && product.getBrandCode() != 0) {
					Predicate predicate4 = cb.equal(root.get("brandCode"),product.getBrandCode());
					predicate.getExpressions().add(predicate4);

				}if (product.getAuditStatus() != null && product.getAuditStatus() != 0) {
					Predicate predicate5 = cb.equal(root.get("auditStatus"),product.getAuditStatus());
					predicate.getExpressions().add(predicate5);

				}
				if (product.getIsEffective() != null) {
					Predicate predicate6 = cb.equal(root.get("isEffective"),product.getIsEffective());
					predicate.getExpressions().add(predicate6);

				}

				return predicate;
			}
		};
		page= productDao.findAll(specification,pageable);

		List<Product> list = page.getContent();
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
		return page;
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


}