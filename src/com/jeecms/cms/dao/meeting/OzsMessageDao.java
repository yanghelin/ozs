package com.jeecms.cms.dao.meeting;

import java.util.List;

import com.jeecms.cms.entity.meeting.OzsMessage;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

/**
 * 站内消息DAO接口
 */
public interface OzsMessageDao{
	public Pagination getPage(String name, int pageNo, int pageSize);
	
	public List<OzsMessage> getList(String name);

	public OzsMessage findById(Integer id);

	public OzsMessage save(OzsMessage bean);

	public OzsMessage updateByUpdater(Updater<OzsMessage> updater);

	public OzsMessage deleteById(Integer id);
}