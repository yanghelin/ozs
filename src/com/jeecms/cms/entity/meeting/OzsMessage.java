package com.jeecms.cms.entity.meeting;

import java.io.Serializable;
import java.util.Date;

import com.jeecms.core.entity.CmsUser;

public class OzsMessage implements Serializable {
    private Integer id;

    private String title;

    private String content;

    private String sendUsers;

    private Date sendTime;

    //private Integer sendBy;

    private Byte isDelete;
    
    private CmsUser sendBy;

    private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getSendUsers() {
		return sendUsers;
	}

	public void setSendUsers(String sendUsers) {
		this.sendUsers = sendUsers;
	}

	public Date getSendTime() {
		return sendTime;
	}

	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}

	public Byte getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Byte isDelete) {
		this.isDelete = isDelete;
	}

	public CmsUser getSendBy() {
		return sendBy;
	}

	public void setSendBy(CmsUser sendBy) {
		this.sendBy = sendBy;
	}
}