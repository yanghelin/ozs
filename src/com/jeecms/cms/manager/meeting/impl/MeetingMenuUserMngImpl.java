package com.jeecms.cms.manager.meeting.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.meeting.MeetingMenuUserDao;
import com.jeecms.cms.entity.meeting.MeetingMenu;
import com.jeecms.cms.entity.meeting.MeetingMenuUser;
import com.jeecms.cms.manager.meeting.MeetingMenuUserMng;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.core.entity.CmsUser;

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
	
	public MeetingMenuUser saveMeetingMenuUsers(Integer[] menuIds, CmsUser cmsUser) {
		if(menuIds == null || menuIds.length<1) {
			return null;
		}
		MeetingMenuUser returnMenuUser = null;
		for(Integer menuId:menuIds) {
			MeetingMenu menu = new MeetingMenu();
			menu.setId(menuId);
			MeetingMenuUser menuUser = new MeetingMenuUser();
			menuUser.setMeetingMenu(menu);
			menuUser.setUser(cmsUser);
			returnMenuUser = meetingMenuUserDao.save(menuUser);
		}
		return returnMenuUser;
	}
	
	public void updateMeetingMenuUsers(Integer[] menuIds, CmsUser cmsUser){
		if(menuIds != null && menuIds.length>0 && cmsUser.getId() != null) {
			List<MeetingMenuUser> muList = meetingMenuUserDao.findByProperty("user.id", cmsUser.getId());
			if(muList != null && muList.size()>0) {
				for(MeetingMenuUser menuUser:muList) {
					meetingMenuUserDao.deleteById(menuUser.getId());
				}
			}
			for(Integer menuId:menuIds) {
				MeetingMenu menu = new MeetingMenu();
				menu.setId(menuId);
				MeetingMenuUser menuUser = new MeetingMenuUser();
				menuUser.setMeetingMenu(menu);
				menuUser.setUser(cmsUser);
				meetingMenuUserDao.save(menuUser);
			}
		}
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