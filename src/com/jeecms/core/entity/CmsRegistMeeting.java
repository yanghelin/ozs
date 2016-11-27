package com.jeecms.core.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.jeecms.common.hibernate3.PriorityInterface;
import com.jeecms.core.entity.base.BaseCmsGroup;
import com.jeecms.core.entity.base.BaseCmsRegistUser;

public class CmsRegistMeeting implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String registId;
	private String meetingId;
	private String path;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRegistId() {
		return registId;
	}

	public void setRegistId(String registId) {
		this.registId = registId;
	}

	public String getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(String meetingId) {
		this.meetingId = meetingId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * 从集合中提取ID数组
	 * 
	 * @param groups
	 * @return
	 */
	public static Integer[] fetchIds(Collection<CmsRegistMeeting> groups) {
		Integer[] ids = new Integer[groups.size()];
		int i = 0;
		for (CmsRegistMeeting g : groups) {
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
	public static List<CmsRegistMeeting> sortByList(Set<CmsRegistMeeting> source,
			List<CmsRegistMeeting> target) {
		List<CmsRegistMeeting> list = new ArrayList<CmsRegistMeeting>(source.size());
		for (CmsRegistMeeting g : target) {
			if (source.contains(g)) {
				list.add(g);
			}
		}
		return list;
	}

	
	
}
