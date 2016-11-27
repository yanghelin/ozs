package com.jeecms.cms.entity.meeting;

import java.io.Serializable;
import java.util.Date;

import com.jeecms.core.entity.CmsUser;

public class InMeetingRoom implements Serializable {
    private Integer id;

    //private Integer meetingId;

    private Integer attendeeNum;

    private Integer guestNum;

    private Byte meetingRoom;

    private String organizingDept;

    private String hoster;

    private String itemHoster;

    private String phone;

    private String position;

    private String remark;

    private Date createTime;

    //private Integer createBy;

    private Date updateTime;

    //private Integer updateBy;

    private Byte isDelete;
    
    private InMeeting meetingId;
    
    private CmsUser createBy;
    
    private CmsUser updateBy;

    private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAttendeeNum() {
		return attendeeNum;
	}

	public void setAttendeeNum(Integer attendeeNum) {
		this.attendeeNum = attendeeNum;
	}

	public Integer getGuestNum() {
		return guestNum;
	}

	public void setGuestNum(Integer guestNum) {
		this.guestNum = guestNum;
	}

	public Byte getMeetingRoom() {
		return meetingRoom;
	}

	public void setMeetingRoom(Byte meetingRoom) {
		this.meetingRoom = meetingRoom;
	}

	public String getOrganizingDept() {
		return organizingDept;
	}

	public void setOrganizingDept(String organizingDept) {
		this.organizingDept = organizingDept;
	}

	public String getHoster() {
		return hoster;
	}

	public void setHoster(String hoster) {
		this.hoster = hoster;
	}

	public String getItemHoster() {
		return itemHoster;
	}

	public void setItemHoster(String itemHoster) {
		this.itemHoster = itemHoster;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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