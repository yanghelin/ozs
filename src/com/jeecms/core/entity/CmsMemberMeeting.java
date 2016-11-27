package com.jeecms.core.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.jeecms.common.hibernate3.PriorityInterface;
import com.jeecms.core.entity.base.BaseCmsGroup;
import com.jeecms.core.entity.base.BaseCmsRegistUser;

public class CmsMemberMeeting extends BaseCmsRegistUser {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String meetingTitle;
	private Date insertTimeHis;
	private String createPerson;
	private String content;
	private String sts;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMeetingTitle() {
		return meetingTitle;
	}

	public void setMeetingTitle(String meetingTitle) {
		this.meetingTitle = meetingTitle;
	}

	public Date getInsertTimeHis() {
		return insertTimeHis;
	}

	public void setInsertTimeHis(Date insertTimeHis) {
		this.insertTimeHis = insertTimeHis;
	}

	public String getCreatePerson() {
		return createPerson;
	}

	public void setCreatePerson(String createPerson) {
		this.createPerson = createPerson;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSts() {
		return sts;
	}

	public void setSts(String sts) {
		this.sts = sts;
	}


	/**
	 * 从集合中提取ID数组
	 * 
	 * @param groups
	 * @return
	 */
	public static Integer[] fetchIds(Collection<CmsMemberMeeting> groups) {
		Integer[] ids = new Integer[groups.size()];
		int i = 0;
		for (CmsMemberMeeting g : groups) {
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
	public static List<CmsMemberMeeting> sortByList(Set<CmsMemberMeeting> source,
			List<CmsMemberMeeting> target) {
		List<CmsMemberMeeting> list = new ArrayList<CmsMemberMeeting>(source.size());
		for (CmsMemberMeeting g : target) {
			if (source.contains(g)) {
				list.add(g);
			}
		}
		return list;
	}

	
	
}
