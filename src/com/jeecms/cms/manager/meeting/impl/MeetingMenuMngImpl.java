package com.jeecms.cms.manager.meeting.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.meeting.MeetingMenuDao;
import com.jeecms.cms.entity.meeting.MeetingMenu;
import com.jeecms.cms.manager.meeting.MeetingMenuMng;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

@Service
@Transactional
public class MeetingMenuMngImpl implements MeetingMenuMng {
	@Transactional(readOnly = true)
	public Pagination getPage(String name, int pageNo, int pageSize) {
		Pagination page = meetingMenuDao.getPage(name, pageNo, pageSize);
		return page;
	}
	
	@Transactional(readOnly = true)
	public List<MeetingMenu> getList(String name) {
		List<MeetingMenu> list = meetingMenuDao.getList(name);
		return list;
	}

	@Transactional(readOnly = true)
	public MeetingMenu findById(Integer id) {
		MeetingMenu entity = meetingMenuDao.findById(id);
		return entity;
	}

	public MeetingMenu addMeetingMenu(MeetingMenu bean){
		return bean;
	}

	
	public void updateMeetingMenu(MeetingMenu user){
		Updater<MeetingMenu>updater=new Updater<MeetingMenu>(user);
		meetingMenuDao.updateByUpdater(updater);
	}

	public MeetingMenu saveMeetingMenu(MeetingMenu bean) {
		MeetingMenu menu = meetingMenuDao.save(bean);
		return menu;
	}

	public MeetingMenu deleteById(Integer id) {
		MeetingMenu bean = meetingMenuDao.deleteById(id);
		return bean;
	}

	public MeetingMenu[] deleteByIds(Integer[] ids) {
		MeetingMenu[] beans = new MeetingMenu[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	@Autowired
	private MeetingMenuDao meetingMenuDao;
	
}