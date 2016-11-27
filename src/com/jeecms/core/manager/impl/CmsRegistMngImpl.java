package com.jeecms.core.manager.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.common.page.Pagination;
import com.jeecms.core.dao.CmsRegistUserDao;
import com.jeecms.core.entity.CmsMemberMeeting;
import com.jeecms.core.entity.CmsRegistMeeting;
import com.jeecms.core.entity.CmsRegistUser;
import com.jeecms.core.entity.CmsSubscibeEmail;
import com.jeecms.core.manager.CmsRegistMng;

@Service
@Transactional
public class CmsRegistMngImpl implements CmsRegistMng{
	@Autowired
	private CmsRegistUserDao dao;
	
	@Transactional(readOnly = true)
	public Pagination getPage(Boolean admin, int pageNo, int pageSize) {
		Pagination page = dao.getPage(admin, pageNo, pageSize);
		return page;
	}
	
	public Pagination findAllMeeting(Boolean admin, int pageNo, int pageSize) {
		Pagination page = dao.findAllMeeting(admin, pageNo, pageSize);
		return page;
	}
	public Pagination getPageEmail(Boolean admin, int pageNo, int pageSize) {
		Pagination page = dao.getPageEmail(admin, pageNo, pageSize);
		return page;
	}
	
	@Transactional(readOnly = true)
	public List<CmsRegistUser> getRegistList() {
		return dao.getRegistList();
	}


	public void add(CmsRegistUser cmsRegistUser) {
		dao.addRegistUser(cmsRegistUser);
	}


	public int selectByUserName(String userName) {
		return dao.selectByUserName(userName);
	}


	public int selectByEmail(String email) {
		return dao.selectByEmail(email);
	}


	public void addEmail(CmsSubscibeEmail cmsSubScibeEmail) {
		dao.addEmail(cmsSubScibeEmail);
	}
	public List<CmsSubscibeEmail> getEmailList() {
		return dao.getEmailList();
	}
	public String findByUserName(String userName) {
		return dao.findByUserName(userName);
	}
	public CmsRegistUser findUser(String userName) {
		return dao.findUser(userName);
	}
	public Pagination getMeeting(Boolean admin, int pageNo, int pageSize,  List<CmsRegistMeeting>list) {
		Pagination page = dao.getMeeting(admin, pageNo, pageSize, list);
		return page;
	}
	public String saveMeeting(String str, String str2, String str3, String flag) {
		return dao.saveMeeting(str, str2, str3, flag);
	}
	public void saveRegistMeeting(String registId, String meetingId) {
		dao.saveRegistMeeting(registId, meetingId);
		
	}
	public List<CmsRegistMeeting> finMeetingId(Integer id) {
		return dao.finMeetingId(id);
	}
	public CmsMemberMeeting findMemberMeeting(String id) {
		return dao.findMemberMeeting(id);
	}
	public CmsRegistMeeting findRegistMeeting(Integer registId, Integer meetingId) {
		return dao.findRegistMeeting(registId, meetingId);
	}
	public void updateRegistMeeting(String path, String id) {
		dao.updateRegistMeeting(path, id);
	}

	public List<CmsRegistMeeting> findMeetingUser(String id) {
		return dao.findMeetingUser(id);
	}

	public CmsRegistUser findByUser(String id) {
		return dao.findByUser(id);
	}
	


	
	
}
