package com.jeecms.cms.dao.meeting;

import java.util.List;

import com.jeecms.cms.entity.meeting.InMeetingItems;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

/**
 * 所内会议用品管理DAO接口
 */
public interface InMeetingItemsDao{
	public Pagination getPage(String name, int pageNo, int pageSize);
	
	public List<InMeetingItems> getList(String name);

	public InMeetingItems findById(Integer id);
	
	public List<InMeetingItems> findByMeetingId(Integer meetingId);

	public InMeetingItems save(InMeetingItems bean);

	public InMeetingItems updateByUpdater(Updater<InMeetingItems> updater);

	public InMeetingItems deleteById(Integer id);
}