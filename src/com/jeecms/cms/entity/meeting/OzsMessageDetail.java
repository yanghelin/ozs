package com.jeecms.cms.entity.meeting;

import java.io.Serializable;
import java.util.Date;

import com.jeecms.core.entity.CmsUser;

public class OzsMessageDetail implements Serializable {
    private Integer id;

    //private Integer messageId;

    private String messageTitle;

    private String messageContent;

    //private Integer sender;

    //private Integer receiver;

    private Date createTime;

    private Byte isRead;

    private Date readTime;

    private Byte isReply;

    private Byte isDelete;
    
    private OzsMessage messageId;
    
    private CmsUser sender;
    
    private CmsUser receiver;

    private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getMessageTitle() {
		return messageTitle;
	}

	public void setMessageTitle(String messageTitle) {
		this.messageTitle = messageTitle;
	}

	public String getMessageContent() {
		return messageContent;
	}

	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Byte getIsRead() {
		return isRead;
	}

	public void setIsRead(Byte isRead) {
		this.isRead = isRead;
	}

	public Date getReadTime() {
		return readTime;
	}

	public void setReadTime(Date readTime) {
		this.readTime = readTime;
	}

	public Byte getIsReply() {
		return isReply;
	}

	public void setIsReply(Byte isReply) {
		this.isReply = isReply;
	}

	public Byte getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Byte isDelete) {
		this.isDelete = isDelete;
	}

	public OzsMessage getMessageId() {
		return messageId;
	}

	public void setMessageId(OzsMessage messageId) {
		this.messageId = messageId;
	}

	public CmsUser getSender() {
		return sender;
	}

	public void setSender(CmsUser sender) {
		this.sender = sender;
	}

	public CmsUser getReceiver() {
		return receiver;
	}

	public void setReceiver(CmsUser receiver) {
		this.receiver = receiver;
	}
}