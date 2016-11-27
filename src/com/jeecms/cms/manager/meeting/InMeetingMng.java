package com.jeecms.cms.manager.meeting;

import java.util.List;

import com.jeecms.cms.entity.meeting.InMeeting;
import com.jeecms.common.page.Pagination;

public interface InMeetingMng {
	public Pagination getPage(String meetingName, int pageNo, int pageSize);
	
	public List<InMeeting> getList(String name);

	public InMeeting findById(Integer id);

	public InMeeting saveInMeeting(InMeeting inMeeting);

	public void updateInMeeting(InMeeting bean);

	public InMeeting deleteById(Integer id);

	public InMeeting[] deleteByIds(Integer[] ids);

}