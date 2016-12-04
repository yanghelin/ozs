package com.jeecms.cms.manager.meeting;

import java.util.List;

import com.jeecms.cms.entity.meeting.InMeetingDetail;
import com.jeecms.common.page.Pagination;

public interface InMeetingDetailMng {
	public Pagination getPage(String meetingName, Integer userId, int pageNo, int pageSize);
	
	public List<InMeetingDetail> getList(String name);

	public InMeetingDetail findById(Integer id);

	public InMeetingDetail saveInMeetingDetail(InMeetingDetail InMeetingDetail);

	public void updateInMeetingDetail(InMeetingDetail bean);

	public InMeetingDetail deleteById(Integer id);

	public InMeetingDetail[] deleteByIds(Integer[] ids);

}