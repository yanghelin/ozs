package com.jeecms.cms.entity.meeting;

import java.io.Serializable;
import java.util.Date;

import com.jeecms.core.entity.CmsUser;

public class WorkMeal implements Serializable {
    private Integer id;

    private Integer deptId;

    private String deptName;

    private Integer number;

    private String remark;

    private Date createTime;

    //private Integer createBy;

    private Date updateTime;

    //private Integer updateBy;

    private Byte isDelete;
    
    private CmsUser createBy;
    
    private CmsUser updateBy;

    private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDeptId() {
		return deptId;
	}

	public void setDeptId(Integer deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
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