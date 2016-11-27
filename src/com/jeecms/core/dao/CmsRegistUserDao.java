package com.jeecms.core.dao;

import java.util.List;

import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.CmsMemberMeeting;
import com.jeecms.core.entity.CmsRegistMeeting;
import com.jeecms.core.entity.CmsRegistUser;
import com.jeecms.core.entity.CmsSubscibeEmail;

public interface CmsRegistUserDao {
	
	Pagination getPage(Boolean admin, int pageNo, int pageSize);
	Pagination getPageEmail(Boolean admin, int pageNo, int pageSize);
	
	Pagination findAllMeeting(Boolean admin, int pageNo, int pageSize);
	
	List<CmsRegistUser> getRegistList();
	
	void addRegistUser(CmsRegistUser cmsRegistUser);
	
	int selectByUserName(String userName);
	
	int selectByEmail(String email);
	
	void addEmail(CmsSubscibeEmail cmsSubscibeEmail);
	
	List<CmsSubscibeEmail> getEmailList();
	
	String findByUserName(String userName);
	
	CmsRegistUser findUser(String userName);
	
	Pagination getMeeting (Boolean admin, int pageNo, int pageSize, List<CmsRegistMeeting>list);
	
	String saveMeeting(String str1, String str2, String str3, String flag);
	
	void saveRegistMeeting(String registId, String meetingId);
	
	List<CmsRegistMeeting> finMeetingId(Integer id);
	
	CmsMemberMeeting findMemberMeeting(String id);
	
	CmsRegistMeeting findRegistMeeting(Integer registId, Integer meetingId);
	
	void updateRegistMeeting(String path, String id);
	
	List<CmsRegistMeeting> findMeetingUser(String id);
	
	CmsRegistUser findByUser(String id);
	
}
