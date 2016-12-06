package com.jeecms.cms.dao.meeting;

import java.util.List;

import com.jeecms.cms.entity.meeting.MeetingMenuUser;
import com.jeecms.common.hibernate3.Updater;

/**
 * 会议菜单用户关联表DAO接口
 */
public interface MeetingMenuUserDao{
	public List<MeetingMenuUser> getList(Integer userId);

	public MeetingMenuUser findById(Integer id);

	public MeetingMenuUser save(MeetingMenuUser bean);

	public MeetingMenuUser updateByUpdater(Updater<MeetingMenuUser> updater);

	public MeetingMenuUser deleteById(Integer id);
}