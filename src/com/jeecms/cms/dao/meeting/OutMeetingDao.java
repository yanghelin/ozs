package com.jeecms.cms.dao.meeting;

import java.util.List;

import com.jeecms.cms.entity.meeting.OutMeeting;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

/**
 * 国际会议DAO接口
 */
public interface OutMeetingDao{
	public Pagination getPage(String name, int pageNo, int pageSize);
	
	public List<OutMeeting> getList(String name);

	public OutMeeting findById(Integer id);

	public OutMeeting save(OutMeeting bean);

	public OutMeeting updateByUpdater(Updater<OutMeeting> updater);

	public OutMeeting deleteById(Integer id);
}