package com.jeecms.cms.manager.meeting.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.meeting.InMeetingRoomDetailDao;
import com.jeecms.cms.entity.meeting.InMeetingRoomDetail;
import com.jeecms.cms.manager.meeting.InMeetingRoomDetailMng;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

@Service
@Transactional
public class InMeetingRoomDetailMngImpl implements InMeetingRoomDetailMng {
	@Transactional(readOnly = true)
	public Pagination getPage(String name, int pageNo, int pageSize) {
		Pagination page = inMeetingRoomDetailDao.getPage(name, pageNo, pageSize);
		return page;
	}
	
	@Transactional(readOnly = true)
	public List<InMeetingRoomDetail> getList(String name) {
		List<InMeetingRoomDetail> list = inMeetingRoomDetailDao.getList(name);
		return list;
	}

	@Transactional(readOnly = true)
	public InMeetingRoomDetail findById(Integer id) {
		InMeetingRoomDetail entity = inMeetingRoomDetailDao.findById(id);
		return entity;
	}

	public InMeetingRoomDetail addInMeetingRoomDetail(InMeetingRoomDetail bean){
		return bean;
	}

	
	public void updateInMeetingRoomDetail(InMeetingRoomDetail bean){
		Updater<InMeetingRoomDetail> updater = new Updater<InMeetingRoomDetail>(bean);
		inMeetingRoomDetailDao.updateByUpdater(updater);
	}

	public InMeetingRoomDetail saveInMeetingRoomDetail(InMeetingRoomDetail bean) {
		return inMeetingRoomDetailDao.save(bean);
	}

	public InMeetingRoomDetail deleteById(Integer id) {
		return inMeetingRoomDetailDao.deleteById(id);
	}

	public InMeetingRoomDetail[] deleteByIds(Integer[] ids) {
		InMeetingRoomDetail[] beans = new InMeetingRoomDetail[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	@Autowired
	private InMeetingRoomDetailDao inMeetingRoomDetailDao;

}