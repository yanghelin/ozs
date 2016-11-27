package com.jeecms.cms.manager.meeting.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.meeting.InMeetingDao;
import com.jeecms.cms.entity.meeting.InMeeting;
import com.jeecms.cms.manager.meeting.InMeetingMng;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

@Service
@Transactional
public class InMeetingMngImpl implements InMeetingMng {
	@Transactional(readOnly = true)
	public Pagination getPage(String name, int pageNo, int pageSize) {
		Pagination page = inMeetingDao.getPage(name, pageNo, pageSize);
		return page;
	}
	
	@Transactional(readOnly = true)
	public List<InMeeting> getList(String name) {
		List<InMeeting> list = inMeetingDao.getList(name);
		return list;
	}

	@Transactional(readOnly = true)
	public InMeeting findById(Integer id) {
		InMeeting entity = inMeetingDao.findById(id);
		return entity;
	}

	public InMeeting addInMeeting(InMeeting meeting){
		return meeting;
	}

	
	public void updateInMeeting(InMeeting user){
		Updater<InMeeting>updater=new Updater<InMeeting>(user);
		inMeetingDao.updateByUpdater(updater);
	}

	public InMeeting saveInMeeting(InMeeting inMeeting) {
		InMeeting meeting = inMeetingDao.save(inMeeting);
		if(StringUtils.isNotEmpty(inMeeting.getParticipants())) {
			String[] userIds = inMeeting.getParticipants().split(",");
			if(userIds != null && userIds.length>0) {
				
			}
		}
		return meeting;
	}

	public InMeeting deleteById(Integer id) {
		InMeeting bean = inMeetingDao.deleteById(id);
		return bean;
	}

	public InMeeting[] deleteByIds(Integer[] ids) {
		InMeeting[] beans = new InMeeting[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	@Autowired
	private InMeetingDao inMeetingDao;

}