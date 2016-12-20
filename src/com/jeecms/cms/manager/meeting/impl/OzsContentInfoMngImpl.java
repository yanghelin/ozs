package com.jeecms.cms.manager.meeting.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.meeting.OzsContentInfoDao;
import com.jeecms.cms.entity.meeting.OzsContentInfo;
import com.jeecms.cms.manager.meeting.OzsContentInfoMng;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

@Service
@Transactional
public class OzsContentInfoMngImpl implements OzsContentInfoMng {
	@Transactional(readOnly = true)
	public Pagination getPage(String name, int pageNo, int pageSize) {
		Pagination page = ozsContentInfoDao.getPage(name, pageNo, pageSize);
		return page;
	}
	
	@Transactional(readOnly = true)
	public List<OzsContentInfo> getList(String name) {
		List<OzsContentInfo> list = ozsContentInfoDao.getList(name);
		return list;
	}

	@Transactional(readOnly = true)
	public OzsContentInfo findById(Integer id) {
		OzsContentInfo entity = ozsContentInfoDao.findById(id);
		return entity;
	}

	public OzsContentInfo addOzsContentInfo(OzsContentInfo bean){
		return bean;
	}

	
	public void updateOzsContentInfo(OzsContentInfo bean){
		Updater<OzsContentInfo> updater = new Updater<OzsContentInfo>(bean);
		ozsContentInfoDao.updateByUpdater(updater);
	}

	public OzsContentInfo saveOzsContentInfo(OzsContentInfo bean) {
		OzsContentInfo message = ozsContentInfoDao.save(bean);
		return message;
	}

	public OzsContentInfo deleteById(Integer id) {
		return ozsContentInfoDao.deleteById(id);
	}

	public OzsContentInfo[] deleteByIds(Integer[] ids) {
		OzsContentInfo[] beans = new OzsContentInfo[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}
	
	public OzsContentInfo findByMaxId() {
		return ozsContentInfoDao.findByMaxId();
	}

	@Autowired
	private OzsContentInfoDao ozsContentInfoDao;
}