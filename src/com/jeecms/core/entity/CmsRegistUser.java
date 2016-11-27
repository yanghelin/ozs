package com.jeecms.core.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.jeecms.common.hibernate3.PriorityInterface;
import com.jeecms.core.entity.base.BaseCmsGroup;
import com.jeecms.core.entity.base.BaseCmsRegistUser;

public class CmsRegistUser extends BaseCmsRegistUser {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String userName;
	private String userMail;
	private String userCompany;
	private String  userPosition;
	private String password;
	private String userTitle;
	private Date registTime;;
	private Date updateTime;
	
	public String getUserCompany() {
		return userCompany;
	}

	public void setUserCompany(String userCompany) {
		this.userCompany = userCompany;
	}

	public String getUserPosition() {
		return userPosition;
	}

	public void setUserPosition(String userPosition) {
		this.userPosition = userPosition;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserTitle() {
		return userTitle;
	}

	public void setUserTitle(String userTitle) {
		this.userTitle = userTitle;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public Date getRegistTime() {
		return registTime;
	}

	public void setRegistTime(Date registTime) {
		this.registTime = registTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getUserMail() {
		return userMail;
	}

	public void setUserMail(String userMail) {
		this.userMail = userMail;
	}

	

	/**
	 * 从集合中提取ID数组
	 * 
	 * @param groups
	 * @return
	 */
	public static Integer[] fetchIds(Collection<CmsRegistUser> groups) {
		Integer[] ids = new Integer[groups.size()];
		int i = 0;
		for (CmsRegistUser g : groups) {
			ids[i++] = g.getId();
		}
		return ids;
	}
	
	/**
	 * 根据列表排序
	 * 
	 * @param source
	 *            元素集合
	 * @param target
	 *            有顺序列表
	 * @return 一个新的、按目标排序的列表
	 */
	public static List<CmsRegistUser> sortByList(Set<CmsRegistUser> source,
			List<CmsRegistUser> target) {
		List<CmsRegistUser> list = new ArrayList<CmsRegistUser>(source.size());
		for (CmsRegistUser g : target) {
			if (source.contains(g)) {
				list.add(g);
			}
		}
		return list;
	}

	
	
}
