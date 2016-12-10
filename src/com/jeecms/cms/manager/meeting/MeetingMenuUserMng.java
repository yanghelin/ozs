package com.jeecms.cms.manager.meeting;

import java.util.List;

import com.jeecms.cms.entity.meeting.MeetingMenuUser;
import com.jeecms.core.entity.CmsUser;

public interface MeetingMenuUserMng {
	public List<MeetingMenuUser> getList(Integer userId);

	public MeetingMenuUser findById(Integer id);

	public MeetingMenuUser saveMeetingMenuUser(MeetingMenuUser bean);
	
	public MeetingMenuUser saveMeetingMenuUsers(Integer[] menuIds, CmsUser cmsUser);
	
	public void updateMeetingMenuUsers(Integer[] menuIds, CmsUser cmsUser);

	public void updateMeetingMenuUser(MeetingMenuUser bean);

	public MeetingMenuUser deleteById(Integer id);

	public MeetingMenuUser[] deleteByIds(Integer[] ids);

}