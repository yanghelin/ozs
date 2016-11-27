package com.jeecms.cms.manager.meeting.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.meeting.InMeetingBriefingDao;
import com.jeecms.cms.entity.meeting.InMeetingBriefing;
import com.jeecms.cms.manager.meeting.InMeetingBriefingMng;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

@Service
@Transactional
public class InMeetingBriefingMngImpl implements InMeetingBriefingMng {
	@Transactional(readOnly = true)
	public Pagination getPage(String name, int pageNo, int pageSize) {
		Pagination page = inMeetingBriefingDao.getPage(name, pageNo, pageSize);
		return page;
	}
	
	@Transactional(readOnly = true)
	public List<InMeetingBriefing> getList(String name) {
		List<InMeetingBriefing> list = inMeetingBriefingDao.getList(name);
		return list;
	}

	@Transactional(readOnly = true)
	public InMeetingBriefing findById(Integer id) {
		InMeetingBriefing entity = inMeetingBriefingDao.findById(id);
		return entity;
	}

	public InMeetingBriefing addInMeetingBriefing(InMeetingBriefing bean){
		return bean;
	}

	
	public void updateInMeetingBriefing(InMeetingBriefing bean){
		Updater<InMeetingBriefing> updater = new Updater<InMeetingBriefing>(bean);
		inMeetingBriefingDao.updateByUpdater(updater);
	}

	public InMeetingBriefing saveInMeetingBriefing(InMeetingBriefing bean) {
		return inMeetingBriefingDao.save(bean);
	}

	public InMeetingBriefing deleteById(Integer id) {
		return inMeetingBriefingDao.deleteById(id);
	}

	public InMeetingBriefing[] deleteByIds(Integer[] ids) {
		InMeetingBriefing[] beans = new InMeetingBriefing[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	@Autowired
	private InMeetingBriefingDao inMeetingBriefingDao;

}