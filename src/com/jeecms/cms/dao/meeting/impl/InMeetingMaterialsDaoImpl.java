package com.jeecms.cms.dao.meeting.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.jeecms.cms.dao.meeting.InMeetingMaterialsDao;
import com.jeecms.cms.entity.meeting.InMeetingMaterials;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;

@Repository
public class InMeetingMaterialsDaoImpl extends HibernateBaseDao<InMeetingMaterials, Integer>
		implements InMeetingMaterialsDao {
	public Pagination getPage(String name, int pageNo, int pageSize) {
		Finder f = Finder.create("select bean from InMeetingMaterials bean");
		f.append(" where 1=1 and bean.isDelete = 0 ");
		if (!StringUtils.isBlank(name)) {
			f.append(" and bean.name like :name");
			f.setParam("name", "%" + name + "%");
		}
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}
	
	@SuppressWarnings("unchecked")
	public List<InMeetingMaterials> getList(String name) {
		Finder f = Finder.create("select bean from InMeetingMaterials bean");
		f.append(" where 1=1 and bean.isDelete = 0 ");
		if (!StringUtils.isBlank(name)) {
			f.append(" and bean.name like :name");
			f.setParam("name", "%" + name + "%");
		}
		f.append(" order by bean.id desc");
		return find(f);
	}

	public InMeetingMaterials findById(Integer id) {
		InMeetingMaterials entity = get(id);
		return entity;
	}

	public InMeetingMaterials save(InMeetingMaterials bean) {
		getSession().save(bean);
		return bean;
	}

	public InMeetingMaterials deleteById(Integer id) {
		InMeetingMaterials entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<InMeetingMaterials> getEntityClass() {
		return InMeetingMaterials.class;
	}
}