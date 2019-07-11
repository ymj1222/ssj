package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ServiceRecordDao;
import com.entity.ServiceRecord;
import com.util.Pageh;

@Service
public class ServiceRecordService {
	@Autowired
	private ServiceRecordDao serviceRecordDao;

	/**
	 * ���
	 * 
	 * @param InformationPush
	 */
	public void add(ServiceRecord serviceRecord, String ShoppingGuideId) {
		if (ShoppingGuideId != null && !ShoppingGuideId.equals("")) {
			serviceRecordDao.add(serviceRecord, Integer.parseInt(ShoppingGuideId));
		} else {
			return;
		}
	}

	/**
	 * �õ���������
	 * 
	 * @return
	 */
	public List<ServiceRecord> select(Pageh pageh) {
		List<ServiceRecord> list = serviceRecordDao.select(pageh);
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getStaisfaction().equals("3001")) {
				list.get(i).setStaisfaction("����");
			} else if (list.get(i).getStaisfaction().equals("3002")) {
				list.get(i).setStaisfaction("��");
			} else if (list.get(i).getStaisfaction().equals("3003")) {
				list.get(i).setStaisfaction("һ��");
			} else if (list.get(i).getStaisfaction().equals("3004")) {
				list.get(i).setStaisfaction("����");
			} else if (list.get(i).getStaisfaction().equals("3005")) {
				list.get(i).setStaisfaction("�ǳ�����");
			}
		}
		return list;
	}

	/**
	 * �õ�������ҳ��
	 */
	public Integer gettotal(Pageh pageh) {
		Integer pageCount = 0;
		String rowCounts = serviceRecordDao.gettotal(pageh);
		Integer rowCount = Integer.parseInt(rowCounts);
		if ((rowCount % pageh.getPageSize()) == 0) {
			pageCount = rowCount / pageh.getPageSize();
		} else {
			pageCount = rowCount / pageh.getPageSize() + 1;
		}
		return pageCount;
	}

}
