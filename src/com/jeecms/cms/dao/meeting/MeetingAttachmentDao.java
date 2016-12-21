package com.jeecms.cms.dao.meeting;

import java.util.List;

import com.jeecms.cms.entity.meeting.MeetingAttachment;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

/**
 * 附件DAO接口
 */
public interface MeetingAttachmentDao{
	public Pagination getPage(String name, int pageNo, int pageSize);
	
	public List<MeetingAttachment> getList(String name);

	public MeetingAttachment findById(Integer id);

	public MeetingAttachment save(MeetingAttachment bean);

	public MeetingAttachment updateByUpdater(Updater<MeetingAttachment> updater);

	public MeetingAttachment deleteById(Integer id);
	
	public List<MeetingAttachment> findByIds(String ids);
}