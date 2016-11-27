package com.jeecms.core.entity;

import java.util.Date;

import com.jeecms.core.entity.base.BaseCmsRegistUser;

public class CmsSubscibeEmail  extends BaseCmsRegistUser  {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String email;
	private Date insertTime;
	
	public CmsSubscibeEmail(){
		
	}
	public CmsSubscibeEmail(String email){
		 super();  
	     this.email = email;  
		
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}
	
	
	
	

}
