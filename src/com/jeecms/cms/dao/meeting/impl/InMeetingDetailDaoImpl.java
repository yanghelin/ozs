package com.jeecms.cms.dao.meeting.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.jeecms.cms.dao.meeting.InMeetingDetailDao;
import com.jeecms.cms.entity.meeting.InMeetingDetail;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;

@Repository
public class InMeetingDetailDaoImpl extends HibernateBaseDao<InMeetingDetail, Integer>
		implements InMeetingDetailDao {
	public Pagination getPage(String name, Integer userId, int pageNo, int pageSize) {
		Finder f = Finder.create("select bean from InMeetingDetail bean");
		f.append(" where 1=1 and bean.isDelete = 0 ");
		if (!StringUtils.isBlank(name)) {
			f.append(" and bean.meetingId.name like :name");
			f.setParam("name", "%" + name + "%");
		}
		if (userId != null) {
			f.append(" and bean.attendee.id =:userId");
			f.setParam("userId", userId);
		}
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}
	
	@SuppressWarnings("unchecked")
	public List<InMeetingDetail> getList(String name) {
		Finder f = Finder.create("select bean from InMeetingDetail bean");
		f.append(" where 1=1 and bean.isDelete = 0 ");
		if (!StringUtils.isBlank(name)) {
			f.append(" and bean.name like :name");
			f.setParam("name", "%" + name + "%");
		}
		f.append(" order by bean.id desc");
		return find(f);
	}

	public InMeetingDetail findById(Integer id) {
		InMeetingDetail entity = get(id);
		return entity;
	}

	public InMeetingDetail save(InMeetingDetail bean) {
		getSession().save(bean);
		return bean;
	}

	public InMeetingDetail deleteById(Integer id) {
		InMeetingDetail entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<InMeetingDetail> getEntityClass() {
		return InMeetingDetail.class;
	}
}