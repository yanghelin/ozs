package com.jeecms.cms.dao.meeting;

import java.util.List;

import com.jeecms.cms.entity.meeting.InMeetingBriefing;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

/**
 *工作简报DAO接口
 */
public interface InMeetingBriefingDao{
	public Pagination getPage(String name, int pageNo, int pageSize);
	
	public List<InMeetingBriefing> getList(String name);

	public InMeetingBriefing findById(Integer id);

	public InMeetingBriefing save(InMeetingBriefing bean);

	public InMeetingBriefing updateByUpdater(Updater<InMeetingBriefing> updater);

	public InMeetingBriefing deleteById(Integer id);
}