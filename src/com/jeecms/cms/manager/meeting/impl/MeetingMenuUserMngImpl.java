package com.jeecms.cms.manager.meeting.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.meeting.MeetingMenuUserDao;
import com.jeecms.cms.entity.meeting.MeetingMenuUser;
import com.jeecms.cms.manager.meeting.MeetingMenuUserMng;
import com.jeecms.common.hibernate3.Updater;

@Service
@Transactional
public class MeetingMenuUserMngImpl implements MeetingMenuUserMng {
	@Transactional(readOnly = true)
	public List<MeetingMenuUser> getList(Integer userId) {
		List<MeetingMenuUser> list = meetingMenuUserDao.getList(userId);
		return list;
	}

	@Transactional(readOnly = true)
	public MeetingMenuUser findById(Integer id) {
		MeetingMenuUser entity = meetingMenuUserDao.findById(id);
		return entity;
	}

	public MeetingMenuUser addMeetingMenuUser(MeetingMenuUser bean){
		return bean;
	}
	
	public void updateMeetingMenuUser(MeetingMenuUser user){
		Updater<MeetingMenuUser>updater=new Updater<MeetingMenuUser>(user);
		meetingMenuUserDao.updateByUpdater(updater);
	}

	public MeetingMenuUser saveMeetingMenuUser(MeetingMenuUser bean) {
		MeetingMenuUser menu = meetingMenuUserDao.save(bean);
		return menu;
	}

	public MeetingMenuUser deleteById(Integer id) {
		MeetingMenuUser bean = meetingMenuUserDao.deleteById(id);
		return bean;
	}

	public MeetingMenuUser[] deleteByIds(Integer[] ids) {
		MeetingMenuUser[] beans = new MeetingMenuUser[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	@Autowired
	private MeetingMenuUserDao meetingMenuUserDao;
	
}