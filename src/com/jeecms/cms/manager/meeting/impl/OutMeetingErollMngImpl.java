package com.jeecms.cms.manager.meeting.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.meeting.OutMeetingErollDao;
import com.jeecms.cms.entity.meeting.OutMeetingEroll;
import com.jeecms.cms.manager.meeting.OutMeetingErollMng;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

@Service
@Transactional
public class OutMeetingErollMngImpl implements OutMeetingErollMng {
	@Transactional(readOnly = true)
	public Pagination getPage(String name, String userType, int pageNo, int pageSize) {
		Pagination page = outMeetingErollDao.getPage(name, userType, pageNo, pageSize);
		return page;
	}
	
	@Transactional(readOnly = true)
	public Pagination getPage(String name, Integer type, int pageNo, int pageSize) {
		Pagination page = outMeetingErollDao.getPage(name, type, pageNo, pageSize);
		return page;
	}
	
	public List<OutMeetingEroll> findListByType(String name, Integer type) {
		return outMeetingErollDao.findListByType(name, type);
	}
	
	public List<OutMeetingEroll> findListByUserType(String name, String userType) {
		return outMeetingErollDao.findListByUserType(name, userType);
	}
	@Transactional(readOnly = true)
	public List<OutMeetingEroll> getList(String name) {
		List<OutMeetingEroll> list = outMeetingErollDao.getList(name);
		return list;
	}

	@Transactional(readOnly = true)
	public OutMeetingEroll findById(Integer id) {
		OutMeetingEroll entity = outMeetingErollDao.findById(id);
		return entity;
	}

	public OutMeetingEroll addOutMeetingEroll(OutMeetingEroll bean){
		return bean;
	}

	
	public void updateOutMeetingEroll(OutMeetingEroll bean){
		Updater<OutMeetingEroll> updater = new Updater<OutMeetingEroll>(bean);
		outMeetingErollDao.updateByUpdater(updater);
	}

	public OutMeetingEroll saveOutMeetingEroll(OutMeetingEroll bean) {
		return outMeetingErollDao.save(bean);
	}

	public OutMeetingEroll deleteById(Integer id) {
		return outMeetingErollDao.deleteById(id);
	}

	public OutMeetingEroll[] deleteByIds(Integer[] ids) {
		OutMeetingEroll[] beans = new OutMeetingEroll[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	@Autowired
	private OutMeetingErollDao outMeetingErollDao;

}