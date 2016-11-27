package com.jeecms.cms.manager.meeting;

import java.util.List;

import com.jeecms.cms.entity.meeting.InMeetingRoom;
import com.jeecms.common.page.Pagination;

public interface InMeetingRoomMng {
	public Pagination getPage(String meetingName, int pageNo, int pageSize);
	
	public List<InMeetingRoom> getList(String name);

	public InMeetingRoom findById(Integer id);

	public InMeetingRoom saveInMeetingRoom(InMeetingRoom InMeetingRoom);

	public void updateInMeetingRoom(InMeetingRoom bean);

	public InMeetingRoom deleteById(Integer id);

	public InMeetingRoom[] deleteByIds(Integer[] ids);

}