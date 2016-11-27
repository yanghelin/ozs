package com.jeecms.cms.manager.meeting.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.meeting.InMeetingRoomDao;
import com.jeecms.cms.entity.meeting.InMeetingRoom;
import com.jeecms.cms.manager.meeting.InMeetingRoomMng;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

@Service
@Transactional
public class InMeetingRoomMngImpl implements InMeetingRoomMng {
	@Transactional(readOnly = true)
	public Pagination getPage(String name, int pageNo, int pageSize) {
		Pagination page = inMeetingRoomDao.getPage(name, pageNo, pageSize);
		return page;
	}
	
	@Transactional(readOnly = true)
	public List<InMeetingRoom> getList(String name) {
		List<InMeetingRoom> list = inMeetingRoomDao.getList(name);
		return list;
	}

	@Transactional(readOnly = true)
	public InMeetingRoom findById(Integer id) {
		InMeetingRoom entity = inMeetingRoomDao.findById(id);
		return entity;
	}

	public InMeetingRoom addInMeetingRoom(InMeetingRoom bean){
		return bean;
	}

	
	public void updateInMeetingRoom(InMeetingRoom bean){
		Updater<InMeetingRoom> updater = new Updater<InMeetingRoom>(bean);
		inMeetingRoomDao.updateByUpdater(updater);
	}

	public InMeetingRoom saveInMeetingRoom(InMeetingRoom bean) {
		return inMeetingRoomDao.save(bean);
	}

	public InMeetingRoom deleteById(Integer id) {
		return inMeetingRoomDao.deleteById(id);
	}

	public InMeetingRoom[] deleteByIds(Integer[] ids) {
		InMeetingRoom[] beans = new InMeetingRoom[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	@Autowired
	private InMeetingRoomDao inMeetingRoomDao;

}