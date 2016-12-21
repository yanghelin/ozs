package com.jeecms.cms.dao.meeting.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.jeecms.cms.dao.meeting.OutMeetingErollDao;
import com.jeecms.cms.entity.meeting.OutMeetingEroll;
import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;

@Repository
public class OutMeetingErollDaoImpl extends HibernateBaseDao<OutMeetingEroll, Integer>
		implements OutMeetingErollDao {
	
	//查看会议报名（发言嘉宾和参会人员）和媒体速记管理（媒体和速记）
	public Pagination getPage(String name, String userType, int pageNo, int pageSize) {
		Finder f = Finder.create("select bean from OutMeetingEroll bean");
		f.append(" where 1=1 and bean.isDelete = 0 ");
		if(StringUtils.isNotEmpty(userType)) {
			f.append(" and bean.userType in(0,1) ");
		}else {
			f.append(" and bean.userType not in(0,1) ");
		}
		if (!StringUtils.isBlank(name)) {
			f.append(" and bean.outMeetingId.name like :name");
			f.setParam("name", "%" + name + "%");
		}
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}
	
	@SuppressWarnings("unchecked")
	public List<OutMeetingEroll> findListByUserType(String name, String userType) {
		Finder f = Finder.create("select bean from OutMeetingEroll bean");
		f.append(" where 1=1 and bean.isDelete = 0 ");
		if(StringUtils.isNotEmpty(userType)) {
			f.append(" and bean.userType in(0,1) ");
		}else {
			f.append(" and bean.userType not in(0,1) ");
		}
		if (!StringUtils.isBlank(name)) {
			f.append(" and bean.outMeetingId.name like :name");
			f.setParam("name", "%" + name + "%");
		}
		f.append(" order by bean.id desc");
		return find(f);
	}
	
	//住宿管理、机票管理、车辆管理
	public Pagination getPage(String name, Integer type, int pageNo, int pageSize) {
		Finder f = Finder.create("select bean from OutMeetingEroll bean");
		f.append(" where 1=1 and bean.isDelete = 0 and bean.userType in(0,1) ");
		if(type==1) {//住宿管理
			f.append(" and bean.isStay=1 and bean.isDeleteStay=0");
		}else if(type==2){//机票管理
			f.append(" and (bean.isForeign=1 or bean.isDomestic=1) and bean.isDeleteTicket=0");
		}else {//车辆管理
			f.append(" and bean.isDrive=1");
		}
		if (!StringUtils.isBlank(name)) {
			f.append(" and bean.outMeetingId.name like :name");
			f.setParam("name", "%" + name + "%");
		}
		f.append(" order by bean.id desc");
		return find(f, pageNo, pageSize);
	}
	
	//住宿管理、机票管理、车辆管理
	@SuppressWarnings("unchecked")
	public List<OutMeetingEroll> findListByType(String name, Integer type) {
		Finder f = Finder.create("select bean from OutMeetingEroll bean");
		f.append(" where 1=1 and bean.isDelete = 0 and bean.userType in(0,1) ");
		if(type==1) {//住宿管理
			f.append(" and bean.isStay=1 and bean.isDeleteStay=0");
		}else if(type==2){//机票管理
			f.append(" and (bean.isForeign=1 or bean.isDomestic=1) and bean.isDeleteTicket=0");
		}else {//车辆管理
			f.append(" and bean.isDrive=1");
		}
		if (!StringUtils.isBlank(name)) {
			f.append(" and bean.outMeetingId.name like :name");
			f.setParam("name", "%" + name + "%");
		}
		f.append(" order by bean.id desc");
		return find(f);
	}
	
	@SuppressWarnings("unchecked")
	public List<OutMeetingEroll> getList(String name) {
		Finder f = Finder.create("select bean from OutMeetingEroll bean");
		f.append(" where 1=1 and bean.isDelete = 0 ");
		if (!StringUtils.isBlank(name)) {
			f.append(" and bean.outMeetingId.name like :name");
			f.setParam("name", "%" + name + "%");
		}
		f.append(" order by bean.id desc");
		return find(f);
	}

	public OutMeetingEroll findById(Integer id) {
		OutMeetingEroll entity = get(id);
		return entity;
	}

	public OutMeetingEroll save(OutMeetingEroll bean) {
		getSession().save(bean);
		return bean;
	}

	public OutMeetingEroll deleteById(Integer id) {
		OutMeetingEroll entity = super.get(id);
		if (entity != null) {
			getSession().delete(entity);
		}
		return entity;
	}

	@Override
	protected Class<OutMeetingEroll> getEntityClass() {
		return OutMeetingEroll.class;
	}
}