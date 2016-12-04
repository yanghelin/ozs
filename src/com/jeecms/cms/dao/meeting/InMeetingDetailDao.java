package com.jeecms.cms.dao.meeting;

import java.util.List;

import com.jeecms.cms.entity.meeting.InMeetingDetail;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

/**
 * 所内会议详细DAO接口
 */
public interface InMeetingDetailDao{
	public Pagination getPage(String name, Integer userId, int pageNo, int pageSize);
	
	public List<InMeetingDetail> getList(String name);

	public InMeetingDetail findById(Integer id);

	public InMeetingDetail save(InMeetingDetail bean);

	public InMeetingDetail updateByUpdater(Updater<InMeetingDetail> updater);

	public InMeetingDetail deleteById(Integer id);
}