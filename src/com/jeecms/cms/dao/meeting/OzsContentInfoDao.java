package com.jeecms.cms.dao.meeting;

import java.util.List;

import com.jeecms.cms.entity.meeting.OzsContentInfo;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

/**
 * 内容信息数据DAO接口
 */
public interface OzsContentInfoDao{
	public Pagination getPage(String name, int pageNo, int pageSize);
	
	public List<OzsContentInfo> getList(String name);

	public OzsContentInfo findById(Integer id);

	public OzsContentInfo save(OzsContentInfo bean);

	public OzsContentInfo updateByUpdater(Updater<OzsContentInfo> updater);

	public OzsContentInfo deleteById(Integer id);
	
	public OzsContentInfo findByMaxId();
}