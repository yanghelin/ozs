package com.jeecms.cms.entity.meeting;

import java.io.Serializable;
import java.util.Date;

import com.jeecms.core.entity.CmsUser;

public class OutMeeting implements Serializable {
    private Integer id;

    private String name;

    private String hostUnits;

    private String sponsoringUnits;

    private String overseasPartners;

    private String address;

    private Integer meetingSize;

    private String presenter;

    private String workLanguage;

    private String contacts;

    private Date startTime;

    private Date endTime;

    private String contactPhone;

    private String contactEmail;

    private String content;
    
    private MeetingAttachment contentAttachment;

    private String agenda;
    
    private MeetingAttachment agendaAttachment;

    private MeetingAttachment invitation;

    private MeetingAttachment relatedData;

    private String hoster;

    private String phone;

    private String position;

    private Integer type;

    private Byte isStay;

    private Date stayStart;

    private Date stayEnd;

    private String stayHotel;

    private Byte isForeignTicket;

    private Date foreignStart;

    private Date foreignEnd;

    private Byte isDomesticTicket;

    private Date domesticStart;

    private Date domesticEnd;

    private Byte isFood;

    private Date breakfastStart;

    private Date breakfastEnd;

    private Date lunchStart;

    private Date lunchEnd;

    private Date dinnerStart;

    private Date dinnerEnd;

    private Byte isDrive;

    //private Integer publisher;

    private Date publishTime;

    private Date updateTime;

    //private Integer updateBy;

    private Byte isDelete;
    
    private CmsUser publisher;
    
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

	public String getHostUnits() {
		return hostUnits;
	}

	public void setHostUnits(String hostUnits) {
		this.hostUnits = hostUnits;
	}

	public String getSponsoringUnits() {
		return sponsoringUnits;
	}

	public void setSponsoringUnits(String sponsoringUnits) {
		this.sponsoringUnits = sponsoringUnits;
	}

	public String getOverseasPartners() {
		return overseasPartners;
	}

	public void setOverseasPartners(String overseasPartners) {
		this.overseasPartners = overseasPartners;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getMeetingSize() {
		return meetingSize;
	}

	public void setMeetingSize(Integer meetingSize) {
		this.meetingSize = meetingSize;
	}

	public String getPresenter() {
		return presenter;
	}

	public void setPresenter(String presenter) {
		this.presenter = presenter;
	}

	public String getWorkLanguage() {
		return workLanguage;
	}

	public void setWorkLanguage(String workLanguage) {
		this.workLanguage = workLanguage;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getContactPhone() {
		return contactPhone;
	}

	public void setContactPhone(String contactPhone) {
		this.contactPhone = contactPhone;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public MeetingAttachment getContentAttachment() {
		return contentAttachment;
	}

	public void setContentAttachment(MeetingAttachment contentAttachment) {
		this.contentAttachment = contentAttachment;
	}

	public String getAgenda() {
		return agenda;
	}

	public void setAgenda(String agenda) {
		this.agenda = agenda;
	}

	public MeetingAttachment getAgendaAttachment() {
		return agendaAttachment;
	}

	public void setAgendaAttachment(MeetingAttachment agendaAttachment) {
		this.agendaAttachment = agendaAttachment;
	}

	public MeetingAttachment getInvitation() {
		return invitation;
	}

	public void setInvitation(MeetingAttachment invitation) {
		this.invitation = invitation;
	}

	public MeetingAttachment getRelatedData() {
		return relatedData;
	}

	public void setRelatedData(MeetingAttachment relatedData) {
		this.relatedData = relatedData;
	}

	public String getHoster() {
		return hoster;
	}

	public void setHoster(String hoster) {
		this.hoster = hoster;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Byte getIsStay() {
		return isStay;
	}

	public void setIsStay(Byte isStay) {
		this.isStay = isStay;
	}

	public Date getStayStart() {
		return stayStart;
	}

	public void setStayStart(Date stayStart) {
		this.stayStart = stayStart;
	}

	public Date getStayEnd() {
		return stayEnd;
	}

	public void setStayEnd(Date stayEnd) {
		this.stayEnd = stayEnd;
	}

	public String getStayHotel() {
		return stayHotel;
	}

	public void setStayHotel(String stayHotel) {
		this.stayHotel = stayHotel;
	}

	public Byte getIsForeignTicket() {
		return isForeignTicket;
	}

	public void setIsForeignTicket(Byte isForeignTicket) {
		this.isForeignTicket = isForeignTicket;
	}

	public Date getForeignStart() {
		return foreignStart;
	}

	public void setForeignStart(Date foreignStart) {
		this.foreignStart = foreignStart;
	}

	public Date getForeignEnd() {
		return foreignEnd;
	}

	public void setForeignEnd(Date foreignEnd) {
		this.foreignEnd = foreignEnd;
	}

	public Byte getIsDomesticTicket() {
		return isDomesticTicket;
	}

	public void setIsDomesticTicket(Byte isDomesticTicket) {
		this.isDomesticTicket = isDomesticTicket;
	}

	public Date getDomesticStart() {
		return domesticStart;
	}

	public void setDomesticStart(Date domesticStart) {
		this.domesticStart = domesticStart;
	}

	public Date getDomesticEnd() {
		return domesticEnd;
	}

	public void setDomesticEnd(Date domesticEnd) {
		this.domesticEnd = domesticEnd;
	}

	public Byte getIsFood() {
		return isFood;
	}

	public void setIsFood(Byte isFood) {
		this.isFood = isFood;
	}

	public Date getBreakfastStart() {
		return breakfastStart;
	}

	public void setBreakfastStart(Date breakfastStart) {
		this.breakfastStart = breakfastStart;
	}

	public Date getBreakfastEnd() {
		return breakfastEnd;
	}

	public void setBreakfastEnd(Date breakfastEnd) {
		this.breakfastEnd = breakfastEnd;
	}

	public Date getLunchStart() {
		return lunchStart;
	}

	public void setLunchStart(Date lunchStart) {
		this.lunchStart = lunchStart;
	}

	public Date getLunchEnd() {
		return lunchEnd;
	}

	public void setLunchEnd(Date lunchEnd) {
		this.lunchEnd = lunchEnd;
	}

	public Date getDinnerStart() {
		return dinnerStart;
	}

	public void setDinnerStart(Date dinnerStart) {
		this.dinnerStart = dinnerStart;
	}

	public Date getDinnerEnd() {
		return dinnerEnd;
	}

	public void setDinnerEnd(Date dinnerEnd) {
		this.dinnerEnd = dinnerEnd;
	}

	public Byte getIsDrive() {
		return isDrive;
	}

	public void setIsDrive(Byte isDrive) {
		this.isDrive = isDrive;
	}

	public Date getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
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

	public CmsUser getPublisher() {
		return publisher;
	}

	public void setPublisher(CmsUser publisher) {
		this.publisher = publisher;
	}

	public CmsUser getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(CmsUser updateBy) {
		this.updateBy = updateBy;
	}
}