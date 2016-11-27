package com.jeecms.cms.manager.meeting.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.meeting.OzsMessageDetailDao;
import com.jeecms.cms.entity.meeting.OzsMessageDetail;
import com.jeecms.cms.manager.meeting.OzsMessageDetailMng;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

@Service
@Transactional
public class OzsMessageDetailMngImpl implements OzsMessageDetailMng {
	@Transactional(readOnly = true)
	public Pagination getPage(String name, int pageNo, int pageSize) {
		Pagination page = ozsMessageDetailDao.getPage(name, pageNo, pageSize);
		return page;
	}
	
	@Transactional(readOnly = true)
	public List<OzsMessageDetail> getList(String name) {
		List<OzsMessageDetail> list = ozsMessageDetailDao.getList(name);
		return list;
	}

	@Transactional(readOnly = true)
	public OzsMessageDetail findById(Integer id) {
		OzsMessageDetail entity = ozsMessageDetailDao.findById(id);
		return entity;
	}

	public OzsMessageDetail addOzsMessageDetail(OzsMessageDetail bean){
		return bean;
	}

	
	public void updateOzsMessageDetail(OzsMessageDetail bean){
		Updater<OzsMessageDetail> updater = new Updater<OzsMessageDetail>(bean);
		ozsMessageDetailDao.updateByUpdater(updater);
	}

	public OzsMessageDetail saveOzsMessageDetail(OzsMessageDetail bean) {
		return ozsMessageDetailDao.save(bean);
	}

	public OzsMessageDetail deleteById(Integer id) {
		return ozsMessageDetailDao.deleteById(id);
	}

	public OzsMessageDetail[] deleteByIds(Integer[] ids) {
		OzsMessageDetail[] beans = new OzsMessageDetail[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	@Autowired
	private OzsMessageDetailDao ozsMessageDetailDao;

}