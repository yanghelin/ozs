package com.jeecms.cms.entity.meeting;

import java.io.Serializable;
import java.util.Date;

import com.jeecms.core.entity.CmsUser;

public class InMeetingItems implements Serializable {
	private Integer id;

	// private Integer meetingId;

	private Date meetingTime;

	private String itemName;

	private Integer itemNum;

	private String liablePeople;

	private String phone;

	private String position;

	private String remark;

	// private Integer createBy;

	private Date createTime;

	// private Integer updateBy;

	private Date updateTime;

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

	public Date getMeetingTime() {
		return meetingTime;
	}

	public void setMeetingTime(Date meetingTime) {
		this.meetingTime = meetingTime;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Integer getItemNum() {
		return itemNum;
	}

	public void setItemNum(Integer itemNum) {
		this.itemNum = itemNum;
	}

	public String getLiablePeople() {
		return liablePeople;
	}

	public void setLiablePeople(String liablePeople) {
		this.liablePeople = liablePeople;
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