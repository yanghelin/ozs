package com.jeecms.cms.manager.meeting.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.meeting.InMeetingDetailDao;
import com.jeecms.cms.entity.meeting.InMeetingDetail;
import com.jeecms.cms.manager.meeting.InMeetingDetailMng;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

@Service
@Transactional
public class InMeetingDetailMngImpl implements InMeetingDetailMng {
	@Transactional(readOnly = true)
	public Pagination getPage(String name, Integer userId, int pageNo, int pageSize) {
		Pagination page = inMeetingDetailDao.getPage(name, userId, pageNo, pageSize);
		return page;
	}
	
	@Transactional(readOnly = true)
	public List<InMeetingDetail> getList(String name) {
		List<InMeetingDetail> list = inMeetingDetailDao.getList(name);
		return list;
	}

	@Transactional(readOnly = true)
	public InMeetingDetail findById(Integer id) {
		InMeetingDetail entity = inMeetingDetailDao.findById(id);
		return entity;
	}

	public InMeetingDetail addInMeetingDetail(InMeetingDetail bean){
		return bean;
	}

	
	public void updateInMeetingDetail(InMeetingDetail bean){
		Updater<InMeetingDetail> updater = new Updater<InMeetingDetail>(bean);
		inMeetingDetailDao.updateByUpdater(updater);
	}

	public InMeetingDetail saveInMeetingDetail(InMeetingDetail bean) {
		return inMeetingDetailDao.save(bean);
	}

	public InMeetingDetail deleteById(Integer id) {
		return inMeetingDetailDao.deleteById(id);
	}

	public InMeetingDetail[] deleteByIds(Integer[] ids) {
		InMeetingDetail[] beans = new InMeetingDetail[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	@Autowired
	private InMeetingDetailDao inMeetingDetailDao;

}