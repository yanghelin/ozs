package com.jeecms.cms.manager.meeting;

import java.util.List;

import com.jeecms.cms.entity.meeting.OutMeeting;
import com.jeecms.common.page.Pagination;

public interface OutMeetingMng {
	public Pagination getPage(String meetingName, int pageNo, int pageSize);
	
	public List<OutMeeting> getList(String name);

	public OutMeeting findById(Integer id);

	public OutMeeting saveOutMeeting(OutMeeting OutMeeting);

	public void updateOutMeeting(OutMeeting bean);

	public OutMeeting deleteById(Integer id);

	public OutMeeting[] deleteByIds(Integer[] ids);

}