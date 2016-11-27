package com.jeecms.cms.dao.meeting;

import java.util.List;

import com.jeecms.cms.entity.meeting.OzsMessageDetail;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

/**
 * 站内消息详细DAO接口
 */
public interface OzsMessageDetailDao{
	public Pagination getPage(String name, int pageNo, int pageSize);
	
	public List<OzsMessageDetail> getList(String name);

	public OzsMessageDetail findById(Integer id);

	public OzsMessageDetail save(OzsMessageDetail bean);

	public OzsMessageDetail updateByUpdater(Updater<OzsMessageDetail> updater);

	public OzsMessageDetail deleteById(Integer id);
}