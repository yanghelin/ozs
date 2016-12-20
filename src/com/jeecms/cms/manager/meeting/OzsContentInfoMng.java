package com.jeecms.cms.manager.meeting;

import java.util.List;

import com.jeecms.cms.entity.meeting.OzsContentInfo;
import com.jeecms.common.page.Pagination;

public interface OzsContentInfoMng {
	public Pagination getPage(String meetingName, int pageNo, int pageSize);
	
	public List<OzsContentInfo> getList(String name);

	public OzsContentInfo findById(Integer id);

	public OzsContentInfo saveOzsContentInfo(OzsContentInfo bean);

	public void updateOzsContentInfo(OzsContentInfo bean);

	public OzsContentInfo deleteById(Integer id);

	public OzsContentInfo[] deleteByIds(Integer[] ids);
	
	public OzsContentInfo findByMaxId();

}