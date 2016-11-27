package com.jeecms.cms.manager.meeting;

import java.util.List;

import com.jeecms.cms.entity.meeting.OzsMessage;
import com.jeecms.common.page.Pagination;

public interface OzsMessageMng {
	public Pagination getPage(String meetingName, int pageNo, int pageSize);
	
	public List<OzsMessage> getList(String name);

	public OzsMessage findById(Integer id);

	public OzsMessage saveOzsMessage(OzsMessage OzsMessage);

	public void updateOzsMessage(OzsMessage bean);

	public OzsMessage deleteById(Integer id);

	public OzsMessage[] deleteByIds(Integer[] ids);

}