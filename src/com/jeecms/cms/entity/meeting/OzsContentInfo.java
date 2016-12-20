package com.jeecms.cms.entity.meeting;

import java.io.Serializable;
import java.util.Date;

import com.jeecms.core.entity.CmsUser;

public class OzsContentInfo implements Serializable {
	
	private static final long serialVersionUID = -5295004514637457496L;

	private Integer id;
	
	private Date year;

    private String totalWord;
    
    private String totalArticle;

    private String originalWord;
    
    private String originalArticle;
    
    private String reprintWord;
    
    private String reprintArticle;
    
    private String en2zhWord;
    
    private String en2zhArticle;
    
    private String zh2enWord;
    
    private String zh2enArticle;
    
    private String zhWord;
    
    private String zhArticle;
    
    private String enWord;
    
    private String enArticle;
    
    private Date updateTime;
    
    private CmsUser updateBy;
    
    private Byte isDelete;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getYear() {
		return year;
	}

	public void setYear(Date year) {
		this.year = year;
	}

	public String getTotalWord() {
		return totalWord;
	}

	public void setTotalWord(String totalWord) {
		this.totalWord = totalWord;
	}

	public String getTotalArticle() {
		return totalArticle;
	}

	public void setTotalArticle(String totalArticle) {
		this.totalArticle = totalArticle;
	}

	public String getOriginalWord() {
		return originalWord;
	}

	public void setOriginalWord(String originalWord) {
		this.originalWord = originalWord;
	}

	public String getOriginalArticle() {
		return originalArticle;
	}

	public void setOriginalArticle(String originalArticle) {
		this.originalArticle = originalArticle;
	}

	public String getReprintWord() {
		return reprintWord;
	}

	public void setReprintWord(String reprintWord) {
		this.reprintWord = reprintWord;
	}

	public String getReprintArticle() {
		return reprintArticle;
	}

	public void setReprintArticle(String reprintArticle) {
		this.reprintArticle = reprintArticle;
	}

	public String getEn2zhWord() {
		return en2zhWord;
	}

	public void setEn2zhWord(String en2zhWord) {
		this.en2zhWord = en2zhWord;
	}

	public String getEn2zhArticle() {
		return en2zhArticle;
	}

	public void setEn2zhArticle(String en2zhArticle) {
		this.en2zhArticle = en2zhArticle;
	}

	public String getZh2enWord() {
		return zh2enWord;
	}

	public void setZh2enWord(String zh2enWord) {
		this.zh2enWord = zh2enWord;
	}

	public String getZh2enArticle() {
		return zh2enArticle;
	}

	public void setZh2enArticle(String zh2enArticle) {
		this.zh2enArticle = zh2enArticle;
	}

	public String getZhWord() {
		return zhWord;
	}

	public void setZhWord(String zhWord) {
		this.zhWord = zhWord;
	}

	public String getZhArticle() {
		return zhArticle;
	}

	public void setZhArticle(String zhArticle) {
		this.zhArticle = zhArticle;
	}

	public String getEnWord() {
		return enWord;
	}

	public void setEnWord(String enWord) {
		this.enWord = enWord;
	}

	public String getEnArticle() {
		return enArticle;
	}

	public void setEnArticle(String enArticle) {
		this.enArticle = enArticle;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public CmsUser getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(CmsUser updateBy) {
		this.updateBy = updateBy;
	}

	public Byte getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Byte isDelete) {
		this.isDelete = isDelete;
	}
}
