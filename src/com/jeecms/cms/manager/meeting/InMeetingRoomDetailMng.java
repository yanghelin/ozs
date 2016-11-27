package com.jeecms.cms.manager.meeting;

import java.util.List;

import com.jeecms.cms.entity.meeting.InMeetingRoomDetail;
import com.jeecms.common.page.Pagination;

public interface InMeetingRoomDetailMng {
	public Pagination getPage(String meetingName, int pageNo, int pageSize);
	
	public List<InMeetingRoomDetail> getList(String name);

	public InMeetingRoomDetail findById(Integer id);

	public InMeetingRoomDetail saveInMeetingRoomDetail(InMeetingRoomDetail InMeetingRoomDetail);

	public void updateInMeetingRoomDetail(InMeetingRoomDetail bean);

	public InMeetingRoomDetail deleteById(Integer id);

	public InMeetingRoomDetail[] deleteByIds(Integer[] ids);

}