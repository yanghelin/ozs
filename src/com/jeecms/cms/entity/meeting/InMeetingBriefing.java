package com.jeecms.cms.entity.meeting;

import java.io.Serializable;
import java.util.Date;

import com.jeecms.core.entity.CmsUser;

public class InMeetingBriefing implements Serializable {
    private Integer id;

    //private Integer meetingId;

    private String name;

    //private Integer attachment;

    private Date createTime;

    //private Integer createBy;

    private Date updateTime;

    //private Integer updateBy;

    private Byte isDelete;
    
    private InMeeting meetingId;
    
    private MeetingAttachment attachment;
    
    private CmsUser createBy;
    
    private CmsUser updateBy;

    private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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

	public MeetingAttachment getAttachment() {
		return attachment;
	}

	public void setAttachment(MeetingAttachment attachment) {
		this.attachment = attachment;
	}

	public CmsUser getCreateBy() {
		return createBy;
	}

	public void setCreateBy(CmsUser createBy) {
		this.createBy = createBy;
	}

	public CmsUser getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(CmsUser updateBy) {
		this.updateBy = updateBy;
	}
}