package com.jeecms.cms.dao.meeting;

import java.util.List;

import com.jeecms.cms.entity.meeting.InMeetingRoom;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

/**
 * 所内会议室管理DAO接口
 */
public interface InMeetingRoomDao{
	public Pagination getPage(String name, int pageNo, int pageSize);
	
	public List<InMeetingRoom> getList(String name);

	public InMeetingRoom findById(Integer id);

	public InMeetingRoom save(InMeetingRoom bean);

	public InMeetingRoom updateByUpdater(Updater<InMeetingRoom> updater);

	public InMeetingRoom deleteById(Integer id);
}