package com.jeecms.cms.manager.meeting;

import java.util.List;

import com.jeecms.cms.entity.meeting.OzsMessageDetail;
import com.jeecms.common.page.Pagination;

public interface OzsMessageDetailMng {
	public Pagination getPage(String meetingName, Integer userId, int pageNo, int pageSize);
	
	public List<OzsMessageDetail> getList(String name);

	public OzsMessageDetail findById(Integer id);

	public OzsMessageDetail saveOzsMessageDetail(OzsMessageDetail OzsMessageDetail);

	public void updateOzsMessageDetail(OzsMessageDetail bean);

	public OzsMessageDetail deleteById(Integer id);

	public OzsMessageDetail[] deleteByIds(Integer[] ids);

}