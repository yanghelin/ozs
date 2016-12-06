package com.jeecms.cms.entity.meeting;

import java.io.Serializable;

import com.jeecms.core.entity.CmsUser;

public class MeetingMenuUser implements Serializable {

	private static final long serialVersionUID = -8506420209746775862L;

	private Integer id;
	
	private MeetingMenu meetingMenu;
	
	private CmsUser user;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MeetingMenu getMeetingMenu() {
		return meetingMenu;
	}

	public void setMeetingMenu(MeetingMenu meetingMenu) {
		this.meetingMenu = meetingMenu;
	}

	public CmsUser getUser() {
		return user;
	}

	public void setUser(CmsUser user) {
		this.user = user;
	}
}
