package com.jeecms.cms.manager.meeting;

import java.util.List;

import com.jeecms.cms.entity.meeting.MeetingMenuUser;

public interface MeetingMenuUserMng {
	public List<MeetingMenuUser> getList(Integer userId);

	public MeetingMenuUser findById(Integer id);

	public MeetingMenuUser saveMeetingMenuUser(MeetingMenuUser bean);

	public void updateMeetingMenuUser(MeetingMenuUser bean);

	public MeetingMenuUser deleteById(Integer id);

	public MeetingMenuUser[] deleteByIds(Integer[] ids);

}