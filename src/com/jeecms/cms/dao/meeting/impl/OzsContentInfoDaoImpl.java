package com.jeecms.cms.dao.meeting.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.jeecms.cms.dao.meeting.OzsContentInfoDao;
import com.jeecms.cms.entity.meeting.OzsContentInfo;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;

@Repository
public class OzsContentInfoDaoImpl extends HibernateBaseDao<OzsContentInfo, Integer>
		implements OzsContentInfoDao {
	public Pagination getPage(String name, int pageNo, int pageSize) {
		Finder f = Finder.create("select bean from OzsContentInfo bean");
		f.append(" where 1=1 and bean.isDelete = 0 ");
		if (!StringUtils.isBlank(name)) {
			f.append(" and bean.name like :name");
			f.setParam("name", "%" + name + "%");
		}
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}
	
	@SuppressWarnings("unchecked")
	public List<OzsContentInfo> getList(String name) {
		Finder f = Finder.create("select bean from OzsContentInfo bean");
		f.append(" where 1=1 and bean.isDelete = 0 ");
		if (!StringUtils.isBlank(name)) {
			f.append(" and bean.name like :name");
			f.setParam("name", "%" + name + "%");
		}
		f.append(" order by bean.id desc");
		return find(f);
	}

	public OzsContentInfo findById(Integer id) {
		OzsContentInfo entity = get(id);
		return entity;
	}

	public OzsContentInfo save(OzsContentInfo bean) {
		getSession().save(bean);
		return bean;
	}

	public OzsContentInfo deleteById(Integer id) {
		OzsContentInfo entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}
	
	@SuppressWarnings("unchecked")
	public OzsContentInfo findByMaxId() {
		Finder f = Finder.create("select o from OzsContentInfo o where o.id = ");
		f.append("(select max(bean.id) from OzsContentInfo bean where bean.isDelete=0)");
		List<OzsContentInfo> infoList = find(f);
		OzsContentInfo info = null;
		if(infoList != null && infoList.size()>0) {
			info = infoList.get(0);
		}
		return info;
	}

	@Override
	protected Class<OzsContentInfo> getEntityClass() {
		return OzsContentInfo.class;
	}
}