package com.jeecms.cms.manager.meeting;

import java.util.List;

import com.jeecms.cms.entity.meeting.InMeetingBriefing;
import com.jeecms.common.page.Pagination;

public interface InMeetingBriefingMng {
	public Pagination getPage(String meetingName, int pageNo, int pageSize);
	
	public List<InMeetingBriefing> getList(String name);

	public InMeetingBriefing findById(Integer id);

	public InMeetingBriefing saveInMeetingBriefing(InMeetingBriefing InMeetingBriefing);

	public void updateInMeetingBriefing(InMeetingBriefing bean);

	public InMeetingBriefing deleteById(Integer id);

	public InMeetingBriefing[] deleteByIds(Integer[] ids);

}