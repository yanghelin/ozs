package com.jeecms.cms.dao.meeting;

import java.util.List;

import com.jeecms.cms.entity.meeting.MeetingMenu;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

/**
 * 所内会议DAO接口
 */
public interface MeetingMenuDao{
	public Pagination getPage(String name, int pageNo, int pageSize);
	
	public List<MeetingMenu> getList(String name);

	public MeetingMenu findById(Integer id);

	public MeetingMenu save(MeetingMenu bean);

	public MeetingMenu updateByUpdater(Updater<MeetingMenu> updater);

	public MeetingMenu deleteById(Integer id);
}