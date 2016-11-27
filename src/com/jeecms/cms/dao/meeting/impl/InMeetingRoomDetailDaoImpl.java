package com.jeecms.cms.dao.meeting.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.jeecms.cms.dao.meeting.InMeetingRoomDetailDao;
import com.jeecms.cms.entity.meeting.InMeetingRoomDetail;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;

@Repository
public class InMeetingRoomDetailDaoImpl extends HibernateBaseDao<InMeetingRoomDetail, Integer>
		implements InMeetingRoomDetailDao {
	public Pagination getPage(String name, int pageNo, int pageSize) {
		Finder f = Finder.create("select bean from InMeetingRoomDetail bean");
		f.append(" where 1=1 and bean.isDelete = 0 ");
		if (!StringUtils.isBlank(name)) {
			f.append(" and bean.name like :name");
			f.setParam("name", "%" + name + "%");
		}
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}
	
	@SuppressWarnings("unchecked")
	public List<InMeetingRoomDetail> getList(String name) {
		Finder f = Finder.create("select bean from InMeetingRoomDetail bean");
		f.append(" where 1=1 and bean.isDelete = 0 ");
		if (!StringUtils.isBlank(name)) {
			f.append(" and bean.name like :name");
			f.setParam("name", "%" + name + "%");
		}
		f.append(" order by bean.id desc");
		return find(f);
	}

	public InMeetingRoomDetail findById(Integer id) {
		InMeetingRoomDetail entity = get(id);
		return entity;
	}

	public InMeetingRoomDetail save(InMeetingRoomDetail bean) {
		getSession().save(bean);
		return bean;
	}

	public InMeetingRoomDetail deleteById(Integer id) {
		InMeetingRoomDetail entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<InMeetingRoomDetail> getEntityClass() {
		return InMeetingRoomDetail.class;
	}
}