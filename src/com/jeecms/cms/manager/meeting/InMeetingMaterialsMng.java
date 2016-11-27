package com.jeecms.cms.manager.meeting;

import java.util.List;

import com.jeecms.cms.entity.meeting.InMeetingMaterials;
import com.jeecms.common.page.Pagination;

public interface InMeetingMaterialsMng {
	public Pagination getPage(String meetingName, int pageNo, int pageSize);
	
	public List<InMeetingMaterials> getList(String name);

	public InMeetingMaterials findById(Integer id);

	public InMeetingMaterials saveInMeetingMaterials(InMeetingMaterials InMeetingMaterials);

	public void updateInMeetingMaterials(InMeetingMaterials bean);

	public InMeetingMaterials deleteById(Integer id);

	public InMeetingMaterials[] deleteByIds(Integer[] ids);

}