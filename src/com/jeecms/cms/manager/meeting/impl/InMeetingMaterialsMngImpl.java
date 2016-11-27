package com.jeecms.cms.manager.meeting.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.meeting.InMeetingMaterialsDao;
import com.jeecms.cms.entity.meeting.InMeetingMaterials;
import com.jeecms.cms.manager.meeting.InMeetingMaterialsMng;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

@Service
@Transactional
public class InMeetingMaterialsMngImpl implements InMeetingMaterialsMng {
	@Transactional(readOnly = true)
	public Pagination getPage(String name, int pageNo, int pageSize) {
		Pagination page = inMeetingMaterialsDao.getPage(name, pageNo, pageSize);
		return page;
	}
	
	@Transactional(readOnly = true)
	public List<InMeetingMaterials> getList(String name) {
		List<InMeetingMaterials> list = inMeetingMaterialsDao.getList(name);
		return list;
	}

	@Transactional(readOnly = true)
	public InMeetingMaterials findById(Integer id) {
		InMeetingMaterials entity = inMeetingMaterialsDao.findById(id);
		return entity;
	}

	public InMeetingMaterials addInMeetingMaterials(InMeetingMaterials bean){
		return bean;
	}

	
	public void updateInMeetingMaterials(InMeetingMaterials bean){
		Updater<InMeetingMaterials>updater = new Updater<InMeetingMaterials>(bean);
		inMeetingMaterialsDao.updateByUpdater(updater);
	}

	public InMeetingMaterials saveInMeetingMaterials(InMeetingMaterials bean) {
		return inMeetingMaterialsDao.save(bean);
	}

	public InMeetingMaterials deleteById(Integer id) {
		return inMeetingMaterialsDao.deleteById(id);
	}

	public InMeetingMaterials[] deleteByIds(Integer[] ids) {
		InMeetingMaterials[] beans = new InMeetingMaterials[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	@Autowired
	private InMeetingMaterialsDao inMeetingMaterialsDao;

}