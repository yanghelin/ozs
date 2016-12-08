package com.jeecms.cms.manager.meeting;

import java.util.List;

import com.jeecms.cms.entity.meeting.InMeetingItems;
import com.jeecms.common.page.Pagination;

public interface InMeetingItemsMng {
	public Pagination getPage(String meetingName, int pageNo, int pageSize);
	
	public List<InMeetingItems> getList(String name);

	public InMeetingItems findById(Integer id);
	
	public List<InMeetingItems> findByMeetingId(Integer meetingId);

	public InMeetingItems saveInMeetingItems(InMeetingItems InMeetingItems);

	public void updateInMeetingItems(InMeetingItems bean);

	public InMeetingItems deleteById(Integer id);

	public InMeetingItems[] deleteByIds(Integer[] ids);

}