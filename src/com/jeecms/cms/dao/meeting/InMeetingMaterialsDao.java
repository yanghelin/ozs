package com.jeecms.cms.dao.meeting;

import java.util.List;

import com.jeecms.cms.entity.meeting.InMeetingMaterials;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

/**
 * 所内会议材料管理DAO接口
 */
public interface InMeetingMaterialsDao{
	public Pagination getPage(String name, int pageNo, int pageSize);
	
	public List<InMeetingMaterials> getList(String name);

	public InMeetingMaterials findById(Integer id);

	public InMeetingMaterials save(InMeetingMaterials bean);

	public InMeetingMaterials updateByUpdater(Updater<InMeetingMaterials> updater);

	public InMeetingMaterials deleteById(Integer id);
}