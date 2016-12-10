package com.jeecms.cms.manager.meeting.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.meeting.OutMeetingDao;
import com.jeecms.cms.entity.meeting.OutMeeting;
import com.jeecms.cms.manager.meeting.OutMeetingMng;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

@Service
@Transactional
public class OutMeetingMngImpl implements OutMeetingMng {
	@Transactional(readOnly = true)
	public Pagination getPage(String name, int pageNo, int pageSize) {
		Pagination page = outMeetingDao.getPage(name, pageNo, pageSize);
		return page;
	}
	
	@Transactional(readOnly = true)
	public List<OutMeeting> getList(String name) {
		List<OutMeeting> list = outMeetingDao.getList(name);
		return list;
	}

	@Transactional(readOnly = true)
	public OutMeeting findById(Integer id) {
		OutMeeting entity = outMeetingDao.findById(id);
		return entity;
	}

	public OutMeeting addOutMeeting(OutMeeting bean){
		return bean;
	}

	
	public void updateOutMeeting(OutMeeting bean){
		Updater<OutMeeting> updater = new Updater<OutMeeting>(bean);
		outMeetingDao.updateByUpdater(updater);
	}

	public OutMeeting saveOutMeeting(OutMeeting bean) {
		return outMeetingDao.save(bean);
	}

	public OutMeeting deleteById(Integer id) {
		return outMeetingDao.deleteById(id);
	}

	public OutMeeting[] deleteByIds(Integer[] ids) {
		OutMeeting[] beans = new OutMeeting[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	@Override
	public OutMeeting getMaxMeetingId(Integer isCn) {
		return outMeetingDao.getMaxMeetingId(isCn);
	}
	
	@Autowired
	private OutMeetingDao outMeetingDao;

}