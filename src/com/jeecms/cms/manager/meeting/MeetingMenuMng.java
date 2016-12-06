package com.jeecms.cms.manager.meeting;

import java.util.List;

import com.jeecms.cms.entity.meeting.MeetingMenu;
import com.jeecms.common.page.Pagination;

public interface MeetingMenuMng {
	public Pagination getPage(String name, int pageNo, int pageSize);
	
	public List<MeetingMenu> getList(String name);

	public MeetingMenu findById(Integer id);

	public MeetingMenu saveMeetingMenu(MeetingMenu bean);

	public void updateMeetingMenu(MeetingMenu bean);

	public MeetingMenu deleteById(Integer id);

	public MeetingMenu[] deleteByIds(Integer[] ids);

}