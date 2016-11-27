package com.jeecms.cms.manager.meeting;

import java.util.List;

import com.jeecms.cms.entity.meeting.OutMeetingEroll;
import com.jeecms.common.page.Pagination;

public interface OutMeetingErollMng {
	public Pagination getPage(String meetingName, int pageNo, int pageSize);
	
	public List<OutMeetingEroll> getList(String name);

	public OutMeetingEroll findById(Integer id);

	public OutMeetingEroll saveOutMeetingEroll(OutMeetingEroll OutMeetingEroll);

	public void updateOutMeetingEroll(OutMeetingEroll bean);

	public OutMeetingEroll deleteById(Integer id);

	public OutMeetingEroll[] deleteByIds(Integer[] ids);

}