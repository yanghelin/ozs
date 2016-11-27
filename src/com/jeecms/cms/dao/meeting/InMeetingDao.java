package com.jeecms.cms.dao.meeting;

import java.util.List;

import com.jeecms.cms.entity.meeting.InMeeting;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

/**
 * 所内会议DAO接口
 */
public interface InMeetingDao{
	public Pagination getPage(String name, int pageNo, int pageSize);
	
	public List<InMeeting> getList(String name);

	public InMeeting findById(Integer id);

	public InMeeting save(InMeeting bean);

	public InMeeting updateByUpdater(Updater<InMeeting> updater);

	public InMeeting deleteById(Integer id);
}