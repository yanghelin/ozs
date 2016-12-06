package com.jeecms.cms.entity.meeting;

import java.io.Serializable;

public class MeetingMenu implements Serializable {

	private static final long serialVersionUID = 4764264478673204004L;

	private Integer id;
	
	private String name;
	
	private String url;
	
	private Byte isDelete;

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Byte getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Byte isDelete) {
		this.isDelete = isDelete;
	}
}
