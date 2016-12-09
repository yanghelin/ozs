package com.jeecms.cms.dao.meeting.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.jeecms.cms.dao.meeting.OzsMessageDetailDao;
import com.jeecms.cms.entity.meeting.OzsMessageDetail;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;

@Repository
public class OzsMessageDetailDaoImpl extends HibernateBaseDao<OzsMessageDetail, Integer>
		implements OzsMessageDetailDao {
	public Pagination getPage(String name, Integer userId, int pageNo, int pageSize) {
		Finder f = Finder.create("select bean from OzsMessageDetail bean");
		f.append(" where 1=1 and bean.isDelete = 0 ");
		if (userId != null) {
			f.append(" and bean.receiver.id =:userId");
			f.setParam("userId", userId);
		}
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}
	
	@SuppressWarnings("unchecked")
	public List<OzsMessageDetail> getList(String name) {
		Finder f = Finder.create("select bean from OzsMessageDetail bean");
		f.append(" where 1=1 and bean.isDelete = 0 ");
		if (!StringUtils.isBlank(name)) {
			f.append(" and bean.name like :name");
			f.setParam("name", "%" + name + "%");
		}
		f.append(" order by bean.id desc");
		return find(f);
	}

	public OzsMessageDetail findById(Integer id) {
		OzsMessageDetail entity = get(id);
		return entity;
	}

	public OzsMessageDetail save(OzsMessageDetail bean) {
		getSession().save(bean);
		return bean;
	}

	public OzsMessageDetail deleteById(Integer id) {
		OzsMessageDetail entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<OzsMessageDetail> getEntityClass() {
		return OzsMessageDetail.class;
	}
}