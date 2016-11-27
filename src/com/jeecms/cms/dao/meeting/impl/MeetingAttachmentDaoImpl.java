package com.jeecms.cms.dao.meeting.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.jeecms.cms.dao.meeting.MeetingAttachmentDao;
import com.jeecms.cms.entity.meeting.MeetingAttachment;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;

@Repository
public class MeetingAttachmentDaoImpl extends HibernateBaseDao<MeetingAttachment, Integer>
		implements MeetingAttachmentDao {
	public Pagination getPage(String name, int pageNo, int pageSize) {
		Finder f = Finder.create("select bean from MeetingAttachment bean");
		f.append(" where 1=1 and bean.isDelete = 0 ");
		if (!StringUtils.isBlank(name)) {
			f.append(" and bean.name like :name");
			f.setParam("name", "%" + name + "%");
		}
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}
	
	@SuppressWarnings("unchecked")
	public List<MeetingAttachment> getList(String name) {
		Finder f = Finder.create("select bean from MeetingAttachment bean");
		f.append(" where 1=1 and bean.isDelete = 0 ");
		if (!StringUtils.isBlank(name)) {
			f.append(" and bean.name like :name");
			f.setParam("name", "%" + name + "%");
		}
		f.append(" order by bean.id desc");
		return find(f);
	}

	public MeetingAttachment findById(Integer id) {
		MeetingAttachment entity = get(id);
		return entity;
	}

	public MeetingAttachment save(MeetingAttachment bean) {
		getSession().save(bean);
		return bean;
	}

	public MeetingAttachment deleteById(Integer id) {
		MeetingAttachment entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<MeetingAttachment> getEntityClass() {
		return MeetingAttachment.class;
	}
}