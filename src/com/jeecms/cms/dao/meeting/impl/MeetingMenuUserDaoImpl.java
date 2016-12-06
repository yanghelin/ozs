package com.jeecms.cms.dao.meeting.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.jeecms.cms.dao.meeting.MeetingMenuUserDao;
import com.jeecms.cms.entity.meeting.MeetingMenuUser;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;

@Repository
public class MeetingMenuUserDaoImpl extends HibernateBaseDao<MeetingMenuUser, Integer>
		implements MeetingMenuUserDao {
	@SuppressWarnings("unchecked")
	public List<MeetingMenuUser> getList(Integer userId) {
		Finder f = Finder.create("select bean from MeetingMenuUser bean");
		f.append(" where 1=1 and bean.isDelete = 0 ");
		if (userId != null) {
			f.append(" and bean.user.id =:userId");
			f.setParam("userId", userId);
		}
		f.append(" order by bean.id desc");
		return find(f);
	}

	public MeetingMenuUser findById(Integer id) {
		MeetingMenuUser entity = get(id);
		return entity;
	}

	public MeetingMenuUser save(MeetingMenuUser bean) {
		getSession().save(bean);
		return bean;
	}

	public MeetingMenuUser deleteById(Integer id) {
		MeetingMenuUser entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<MeetingMenuUser> getEntityClass() {
		return MeetingMenuUser.class;
	}
}