package com.jeecms.core.manager;

import java.util.List;

import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.CmsMemberMeeting;
import com.jeecms.core.entity.CmsRegistMeeting;
import com.jeecms.core.entity.CmsRegistUser;
import com.jeecms.core.entity.CmsSubscibeEmail;

public interface CmsRegistMng {
	public List<CmsRegistUser> getRegistList();
	
	public Pagination getPage(Boolean admin, int pageNo, int pageSize);
	public Pagination findAllMeeting(Boolean admin, int pageNo, int pageSize);
	public Pagination getPageEmail(Boolean admin, int pageNo, int pageSize);
	public List<CmsSubscibeEmail> getEmailList();
	
	public void add(CmsRegistUser cmsRegistUser);
	
	public int selectByUserName(String userName);
	
	public int selectByEmail(String email);
	
	public void addEmail(CmsSubscibeEmail cmsSubScibeEmail);
	
	public String findByUserName(String userName);
	
	public CmsRegistUser findUser(String userName);
	
	public Pagination getMeeting(Boolean admin, int pageNo, int pageSize, List<CmsRegistMeeting>list);
	
	public String saveMeeting(String str, String str2, String str3, String flag);
	
	public void saveRegistMeeting(String registId, String meetingId);
	
	public List<CmsRegistMeeting> finMeetingId(Integer integer);
	
	public CmsMemberMeeting findMemberMeeting(String id);
	
	public CmsRegistMeeting findRegistMeeting(Integer registId, Integer meetingId);
	
	public void updateRegistMeeting(String path, String id);
	
	public List<CmsRegistMeeting> findMeetingUser(String id);
	
	public CmsRegistUser findByUser(String id);
	
}
