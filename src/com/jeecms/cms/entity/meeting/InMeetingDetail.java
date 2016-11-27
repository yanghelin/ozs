package com.jeecms.cms.entity.meeting;

import java.io.Serializable;
import java.util.Date;

import com.jeecms.core.entity.CmsUser;

public class InMeetingDetail implements Serializable {
    private Integer id;

    //private Integer meetingId;

    //private Integer attendee;

    //private Integer sender;

    private Byte isRead;

    private Date createTime;

    private Date readTime;

    private Byte isDelete;
    
    private InMeeting meetingId;
    
    private CmsUser attendee;
    
    private CmsUser sender;
    
    private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Byte getIsRead() {
		return isRead;
	}

	public void setIsRead(Byte isRead) {
		this.isRead = isRead;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getReadTime() {
		return readTime;
	}

	public void setReadTime(Date readTime) {
		this.readTime = readTime;
	}

	public Byte getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Byte isDelete) {
		this.isDelete = isDelete;
	}

	public InMeeting getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(InMeeting meetingId) {
		this.meetingId = meetingId;
	}

	public CmsUser getAttendee() {
		return attendee;
	}

	public void setAttendee(CmsUser attendee) {
		this.attendee = attendee;
	}

	public CmsUser getSender() {
		return sender;
	}

	public void setSender(CmsUser sender) {
		this.sender = sender;
	}
}