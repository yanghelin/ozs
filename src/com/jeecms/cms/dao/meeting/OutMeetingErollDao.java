package com.jeecms.cms.dao.meeting;

import java.util.List;

import com.jeecms.cms.entity.meeting.OutMeetingEroll;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

/**
 * 国际会议报名DAO接口
 */
public interface OutMeetingErollDao{
	public Pagination getPage(String name, String userType, int pageNo, int pageSize);
	
	public Pagination getPage(String name, Integer type, int pageNo, int pageSize);
	
	public List<OutMeetingEroll> getList(String name);

	public OutMeetingEroll findById(Integer id);

	public OutMeetingEroll save(OutMeetingEroll bean);

	public OutMeetingEroll updateByUpdater(Updater<OutMeetingEroll> updater);

	public OutMeetingEroll deleteById(Integer id);
	
	public List<OutMeetingEroll> findListByUserType(String name, String userType);
	
	public List<OutMeetingEroll> findListByType(String name, Integer type);
}