package com.service;

import com.dao.WarehouseDao;
import com.dao.WarehousingAndOutDao;
import com.entity.Warehouse;
import com.entity.WarehousingAndOut;
import com.util.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class WarehousingAndOutService {
	@Autowired
	private WarehouseDao warehouseDao;
	@Autowired
	private WarehousingAndOutDao WAODao1;
	public Page<WarehousingAndOut> select(int type, String date, int pageNow, int pageSize) {
		Page<WarehousingAndOut> page;
		Sort sort = new Sort(Sort.Direction.DESC, "date");
		PageRequest pageable =PageRequest.of(pageNow-1, pageSize, sort);
		Specification<WarehousingAndOut> specification = new Specification<WarehousingAndOut>() {
			@Override
			public Predicate toPredicate(Root<WarehousingAndOut> root,
										 CriteriaQuery<?> query, CriteriaBuilder cb) {


				Predicate predicate1 =cb.equal(root.get("type"),type);
				if (date != null && date != "") {
				try {
					DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
					Date dat = format1.parse(date);
					Predicate predicate = cb.equal(root.get("date"),dat);
					query.where(predicate1,predicate);
				}catch (ParseException e) {
					e.printStackTrace();
				}
					return null;
				}
				query.where(predicate1);
				return null;

			}
		};
		page= WAODao1.findAll(specification,pageable);
		return page;

	}

	@Transactional
	public void stock(Integer amount, Long productCode, Date createTime, String creator, Long ordersCode) {
		WarehousingAndOut out = new WarehousingAndOut();
		Integer realamount = Integer.valueOf(warehouseDao.getByProductCode(productCode).getAmount());
		out.setOrdersCode(ordersCode);
		out.setCreator(creator);
		out.setCreateTime(createTime);
		out.setType(1);
		out.setProductCode(productCode);
		out.setDate(createTime);
		out.setAmount(amount);
		WAODao1.save(out);
		warehouseDao.stockOut(productCode, Long.valueOf(realamount - amount));

	}

	@Transactional
	public void cancel(Long ordersCode) {
		WarehousingAndOut wao = WAODao1.getByOrdersCode(ordersCode);
		int realamount = warehouseDao.getByProductCode(wao.getProductCode()).getAmount().intValue();
		warehouseDao.stockOut(wao.getProductCode(), Long.valueOf(realamount + wao.getAmount()));
		WAODao1.deleteByOrdersCode(ordersCode);
	}

	@Transactional
	public void addmount(int id, int amount) {
		Warehouse warehouse =warehouseDao.getById(id);
		 warehouseDao.addamount(id,amount+warehouse.getAmount());
		WarehousingAndOut WAO = new WarehousingAndOut();
		WAO.setAmount(amount);
		WAO.setType(2);
		warehouse.getProduct().getWao().add(WAO);
		WAO.setProduct(warehouse.getProduct());
		WAO.setProductCode(warehouse.getProduct().getCode());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = sdf.parse(DateUtils.getCurrentDateTime());
			WAO.setCreateTime(date);
			WAO.setDate(date);
			WAO.setWarehouse(warehouse);
			WAODao1.save(WAO);
			warehouse.getWao().add(WAO);
			warehouse.getProduct().getWao().add(WAO);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
