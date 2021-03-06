package com.jeecms.cms.manager.meeting;

import java.util.List;

import com.jeecms.cms.entity.meeting.MeetingAttachment;
import com.jeecms.common.page.Pagination;

public interface MeetingAttachmentMng {
	public Pagination getPage(String meetingName, int pageNo, int pageSize);
	
	public List<MeetingAttachment> getList(String name);

	public MeetingAttachment findById(Integer id);

	public MeetingAttachment saveMeetingAttachment(MeetingAttachment bean);

	public void updateMeetingAttachment(MeetingAttachment bean);

	public MeetingAttachment deleteById(Integer id);

	public MeetingAttachment[] deleteByIds(Integer[] ids);
	
	public List<MeetingAttachment> findByIds(String ids);

}