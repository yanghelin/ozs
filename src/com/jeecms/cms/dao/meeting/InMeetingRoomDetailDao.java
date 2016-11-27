package com.jeecms.cms.dao.meeting;

import java.util.List;

import com.jeecms.cms.entity.meeting.InMeetingRoomDetail;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

/**
 * 所内会议室详细DAO接口
 */
public interface InMeetingRoomDetailDao{
	public Pagination getPage(String name, int pageNo, int pageSize);
	
	public List<InMeetingRoomDetail> getList(String name);

	public InMeetingRoomDetail findById(Integer id);

	public InMeetingRoomDetail save(InMeetingRoomDetail bean);

	public InMeetingRoomDetail updateByUpdater(Updater<InMeetingRoomDetail> updater);

	public InMeetingRoomDetail deleteById(Integer id);
}