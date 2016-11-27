package com.jeecms.cms.dao.meeting.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.jeecms.cms.dao.meeting.OzsMessageDao;
import com.jeecms.cms.entity.meeting.OzsMessage;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;

@Repository
public class OzsMessageDaoImpl extends HibernateBaseDao<OzsMessage, Integer>
		implements OzsMessageDao {
	public Pagination getPage(String name, int pageNo, int pageSize) {
		Finder f = Finder.create("select bean from OzsMessage bean");
		f.append(" where 1=1 and bean.isDelete = 0 ");
		if (!StringUtils.isBlank(name)) {
			f.append(" and bean.name like :name");
			f.setParam("name", "%" + name + "%");
		}
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}
	
	@SuppressWarnings("unchecked")
	public List<OzsMessage> getList(String name) {
		Finder f = Finder.create("select bean from OzsMessage bean");
		f.append(" where 1=1 and bean.isDelete = 0 ");
		if (!StringUtils.isBlank(name)) {
			f.append(" and bean.name like :name");
			f.setParam("name", "%" + name + "%");
		}
		f.append(" order by bean.id desc");
		return find(f);
	}

	public OzsMessage findById(Integer id) {
		OzsMessage entity = get(id);
		return entity;
	}

	public OzsMessage save(OzsMessage bean) {
		getSession().save(bean);
		return bean;
	}

	public OzsMessage deleteById(Integer id) {
		OzsMessage entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<OzsMessage> getEntityClass() {
		return OzsMessage.class;
	}
}