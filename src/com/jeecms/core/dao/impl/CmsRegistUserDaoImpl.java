package com.jeecms.core.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.jeecms.common.hibernate3.Finder;
import com.jeecms.common.hibernate3.HibernateBaseDao;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.dao.CmsRegistUserDao;
import com.jeecms.core.entity.CmsMemberMeeting;
import com.jeecms.core.entity.CmsRegistMeeting;
import com.jeecms.core.entity.CmsRegistUser;
import com.jeecms.core.entity.CmsSubscibeEmail;

@Repository
public class CmsRegistUserDaoImpl extends HibernateBaseDao<CmsRegistUser, Integer> implements CmsRegistUserDao{
	
	public Pagination getPage(Boolean admin, int pageNo, int pageSize) {
		Finder f = Finder.create("from CmsRegistUser order by registTime asc");
		return find(f, pageNo, pageSize);
	}
	
	public Pagination findAllMeeting(Boolean admin, int pageNo, int pageSize) {
		Finder f = Finder.create("from CmsMemberMeeting order by insertTimeHis asc");
		return find(f, pageNo, pageSize);
	}
	public Pagination getPageEmail(Boolean admin, int pageNo, int pageSize) {
		Finder f = Finder.create("from CmsSubscibeEmail order by insertTime asc");
		return find(f, pageNo, pageSize);
	}
	
	@SuppressWarnings("unchecked")
	public List<CmsRegistUser> getRegistList() {
		String hql = "";
		return find(hql);
	}

	@Override
	protected Class<CmsRegistUser> getEntityClass() {
		return CmsRegistUser.class;
	}

	public void addRegistUser(CmsRegistUser cmsRegistUser) {
		String hql = "insert into cms_regist_user(user_name, user_password,user_mail,user_company, user_position,  user_title, regist_time) values(?,?,?,?,?,?,?)";
		Query query= getSession().createSQLQuery(hql); 
		query.setParameter(0, cmsRegistUser.getUserName());
		query.setParameter(1, cmsRegistUser.getPassword());
		query.setParameter(2, cmsRegistUser.getUserMail());
		query.setParameter(3, cmsRegistUser.getUserCompany());
		query.setParameter(4, cmsRegistUser.getUserPosition());
		query.setParameter(5, cmsRegistUser.getUserTitle());
		query.setParameter(6, new Date());
		query.executeUpdate();
	}

	public int selectByUserName(String userName) {
		String hql = "from CmsRegistUser where userName = ? ";
		Query query = getSession().createQuery(hql);  
		query.setParameter(0, userName);
		List<CmsRegistUser> list = query.list();
		return list.size();
	}

	public int selectByEmail(String email) {
		String hql = "from CmsSubscibeEmail where email = ?";
		Query query = getSession().createQuery(hql);
		query.setParameter(0, email);
		List<CmsSubscibeEmail> list = query.list();
		return list.size();
	}

	public void addEmail(CmsSubscibeEmail cmsSubscibeEmail) {
		String hql = "insert into cms_subscibe_email (email, insert_time) values(?,?)";
		Query query = getSession().createSQLQuery(hql);
		query.setParameter(0, cmsSubscibeEmail.getEmail());
		query.setParameter(1, new Date());
		query.executeUpdate();
	}

	public List<CmsSubscibeEmail> getEmailList() {
		String hql = "select new CmsSubscibeEmail(email) from CmsSubscibeEmail";
		Query query = getSession().createQuery(hql);
		List<CmsSubscibeEmail> list =query.list();//返回的还是Problem对象 
		return list;
	}

	public String findByUserName(String userName) {
		String hql = "from CmsRegistUser where userName = ? ";
		Query query = getSession().createQuery(hql);  
		query.setParameter(0, userName);
		List<CmsRegistUser> list = query.list();
		if(list.size() != 0){
			return list.get(0).getPassword();
		}
		return null;
	}

	public CmsRegistUser findUser(String userName) {
		String hql = "from CmsRegistUser where userName = ? ";
		Query query = getSession().createQuery(hql);  
		query.setParameter(0, userName);
		List<CmsRegistUser> list = query.list();
		if(list.size() != 0){
			return list.get(0);
		}
		return null;
	}

	public Pagination getMeeting(Boolean admin, int pageNo, int pageSize,  List<CmsRegistMeeting>list) {
		String str = "";
		/*for(CmsRegistMeeting cmsm : list){
			str += "'"+cmsm.getMeetingId()+"',";
		}*/
		if(list.size() != 0){
			for(int i =0; i<list.size(); i++){
				if(i<list.size()-1){
					str += "'"+list.get(i).getMeetingId()+"',";
				}else{
					str += "'"+list.get(i).getMeetingId()+"'";
				}
			}
		}else{
			str = "''";
		}
		
		
		Finder f = Finder.create("from CmsMemberMeeting where id in("+str +") order by insertTimeHis asc");
		return find(f, pageNo, pageSize);
	}

	public String saveMeeting(String title, String createPerson, String container, String flag) {
		String hql = "insert into cms_member_meeting(meeting_title, insert_time_his,create_person,content, sts) values(?,?,?,?,?)";
		Query query= getSession().createSQLQuery(hql); 
		query.setParameter(0, title);
		query.setParameter(1, new Date());
		query.setParameter(2, createPerson);
		query.setParameter(3, container);
		query.setParameter(4, flag);
		query.executeUpdate();
		//order by id desc
		String hql1 = "from CmsMemberMeeting  ";
		Query query1 = getSession().createQuery(hql1);  
		List<CmsMemberMeeting> list1 = query1.list();
		if(list1.size() != 0){
			int  it = list1.get(0).getId();
			return  String.valueOf(it);
		}
		return null;
	}

	public void saveRegistMeeting(String registId, String meetingId) {
		String hql = "insert into cms_regist_meeting(regist_id, meeting_id) values(?,?)";
		Query query= getSession().createSQLQuery(hql); 
		query.setParameter(0, registId);
		query.setParameter(1, meetingId);
		query.executeUpdate();
	}

	public List<CmsRegistMeeting> finMeetingId(Integer id) {
		String strId = String.valueOf(id);
		String hql1 = "from CmsRegistMeeting  where registId="+ strId+ "GROUP BY meetingId";
		Query query1 = getSession().createQuery(hql1);  
		List<CmsRegistMeeting> list1 = query1.list();
		return list1;
	}

	public CmsMemberMeeting findMemberMeeting(String id) {
		String hql1 = "from CmsMemberMeeting  where id="+ id;
		Query query1 = getSession().createQuery(hql1);  
		return (CmsMemberMeeting) query1.list().get(0);
	}

	public CmsRegistMeeting findRegistMeeting(Integer registId, Integer meetingId) {
		String hql1 = "from CmsRegistMeeting  where registId="+ registId + "and meetingId=" + meetingId;
		Query query1 = getSession().createQuery(hql1);  
		return (CmsRegistMeeting) query1.list().get(0);
	}

	public void updateRegistMeeting(String path, String id) {
		if(path !="" || path != null){
			String hql = "update CmsRegistMeeting bean set bean.path='"+path +"' where bean.id="+id;
			Query query = getSession().createQuery(hql);
			query.executeUpdate();
		}
	}

	public List<CmsRegistMeeting> findMeetingUser(String id) {
		String hql1 = "from CmsRegistMeeting  where meetingId="+ id;
		Query query1 = getSession().createQuery(hql1);  
		return query1.list();
	}

	public CmsRegistUser findByUser(String id) {
		String hql1 = "from CmsRegistUser  where id="+ id;
		Query query1 = getSession().createQuery(hql1);  
		return (CmsRegistUser) query1.list().get(0);
	}


	

}
