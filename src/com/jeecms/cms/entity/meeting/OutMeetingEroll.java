package com.jeecms.cms.entity.meeting;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.jeecms.core.entity.CmsUser;

public class OutMeetingEroll implements Serializable {
    private Integer id;

    //private Integer outMeetingId;

    //private Integer loginId;

    private String loginName;

    private Byte userType;

    private String cnName;

    private Byte sex;

    private String national;

    private String enName;

    private String position;

    private String phone;

    private String unit;

    private String email;

    private String outName;

    private Date outBirthday;

    private Byte outSex;

    private String outNational;

    private String passport;

    private String outFrom;

    private String outArrive;

    private Date outGoTime;

    private Date outBackTime;

    private String outGoFlight;

    private String outBackFlight;

    private String inName;

    private Date inBirthday;

    private Byte inSex;

    private String inNational;

    private String card;

    private String inFrom;

    private String inArrive;

    private Date inGoTime;

    private Date inBackTime;

    private String inGoFlight;

    private String inBackFlight;

    private Byte isBreakfast;

    private Byte isLunch;

    private Byte isDinner;

    private String carNo;

    private String bankNo;

    private String bankAddress;

    private String bankName;

    private String bankCard;

    //private Integer other;
    
    private Date createTime;

    private Byte isStay;

    private Byte isDeleteStay;
    
    private Byte isForeign;
    
    private Byte isDomestic;
    
    private Byte isDeleteTicket;
    
    private Byte isFood;
    
    private Byte isDrive;
    
    private Byte isDelete;
    
    private OutMeeting outMeetingId;
    
    private CmsUser loginId;
    
    private MeetingAttachment other;
    
    private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public Byte getUserType() {
		return userType;
	}

	public void setUserType(Byte userType) {
		this.userType = userType;
	}

	public String getCnName() {
		return cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	public Byte getSex() {
		return sex;
	}

	public void setSex(Byte sex) {
		this.sex = sex;
	}

	public String getNational() {
		return national;
	}

	public void setNational(String national) {
		this.national = national;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOutName() {
		return outName;
	}

	public void setOutName(String outName) {
		this.outName = outName;
	}

	public String getOutBirthdayStr() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(outBirthday);
	}
	
	public Date getOutBirthday() {
		return outBirthday;
	}

	public void setOutBirthday(Date outBirthday) {
		this.outBirthday = outBirthday;
	}

	public Byte getOutSex() {
		return outSex;
	}

	public void setOutSex(Byte outSex) {
		this.outSex = outSex;
	}

	public String getOutNational() {
		return outNational;
	}

	public void setOutNational(String outNational) {
		this.outNational = outNational;
	}

	public String getPassport() {
		return passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	public String getOutFrom() {
		return outFrom;
	}

	public void setOutFrom(String outFrom) {
		this.outFrom = outFrom;
	}

	public String getOutArrive() {
		return outArrive;
	}

	public void setOutArrive(String outArrive) {
		this.outArrive = outArrive;
	}

	public Date getOutGoTime() {
		return outGoTime;
	}

	public void setOutGoTime(Date outGoTime) {
		this.outGoTime = outGoTime;
	}

	public Date getOutBackTime() {
		return outBackTime;
	}

	public void setOutBackTime(Date outBackTime) {
		this.outBackTime = outBackTime;
	}

	public String getOutGoFlight() {
		return outGoFlight;
	}

	public void setOutGoFlight(String outGoFlight) {
		this.outGoFlight = outGoFlight;
	}

	public String getOutBackFlight() {
		return outBackFlight;
	}

	public void setOutBackFlight(String outBackFlight) {
		this.outBackFlight = outBackFlight;
	}

	public String getInName() {
		return inName;
	}

	public void setInName(String inName) {
		this.inName = inName;
	}

	public Date getInBirthday() {
		return inBirthday;
	}

	public void setInBirthday(Date inBirthday) {
		this.inBirthday = inBirthday;
	}

	public Byte getInSex() {
		return inSex;
	}

	public void setInSex(Byte inSex) {
		this.inSex = inSex;
	}

	public String getInNational() {
		return inNational;
	}

	public void setInNational(String inNational) {
		this.inNational = inNational;
	}

	public String getCard() {
		return card;
	}

	public void setCard(String card) {
		this.card = card;
	}

	public String getInFrom() {
		return inFrom;
	}

	public void setInFrom(String inFrom) {
		this.inFrom = inFrom;
	}

	public String getInArrive() {
		return inArrive;
	}

	public void setInArrive(String inArrive) {
		this.inArrive = inArrive;
	}

	public Date getInGoTime() {
		return inGoTime;
	}

	public void setInGoTime(Date inGoTime) {
		this.inGoTime = inGoTime;
	}

	public Date getInBackTime() {
		return inBackTime;
	}

	public void setInBackTime(Date inBackTime) {
		this.inBackTime = inBackTime;
	}

	public String getInGoFlight() {
		return inGoFlight;
	}

	public void setInGoFlight(String inGoFlight) {
		this.inGoFlight = inGoFlight;
	}

	public String getInBackFlight() {
		return inBackFlight;
	}

	public void setInBackFlight(String inBackFlight) {
		this.inBackFlight = inBackFlight;
	}

	public Byte getIsBreakfast() {
		return isBreakfast;
	}

	public void setIsBreakfast(Byte isBreakfast) {
		this.isBreakfast = isBreakfast;
	}

	public Byte getIsLunch() {
		return isLunch;
	}

	public void setIsLunch(Byte isLunch) {
		this.isLunch = isLunch;
	}

	public Byte getIsDinner() {
		return isDinner;
	}

	public void setIsDinner(Byte isDinner) {
		this.isDinner = isDinner;
	}

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public String getBankNo() {
		return bankNo;
	}

	public void setBankNo(String bankNo) {
		this.bankNo = bankNo;
	}

	public String getBankAddress() {
		return bankAddress;
	}

	public void setBankAddress(String bankAddress) {
		this.bankAddress = bankAddress;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Byte getIsStay() {
		return isStay;
	}

	public void setIsStay(Byte isStay) {
		this.isStay = isStay;
	}

	public Byte getIsDeleteStay() {
		return isDeleteStay;
	}

	public void setIsDeleteStay(Byte isDeleteStay) {
		this.isDeleteStay = isDeleteStay;
	}

	public Byte getIsForeign() {
		return isForeign;
	}

	public void setIsForeign(Byte isForeign) {
		this.isForeign = isForeign;
	}

	public Byte getIsDomestic() {
		return isDomestic;
	}

	public void setIsDomestic(Byte isDomestic) {
		this.isDomestic = isDomestic;
	}

	public Byte getIsDeleteTicket() {
		return isDeleteTicket;
	}

	public void setIsDeleteTicket(Byte isDeleteTicket) {
		this.isDeleteTicket = isDeleteTicket;
	}

	public Byte getIsFood() {
		return isFood;
	}

	public void setIsFood(Byte isFood) {
		this.isFood = isFood;
	}

	public Byte getIsDrive() {
		return isDrive;
	}

	public void setIsDrive(Byte isDrive) {
		this.isDrive = isDrive;
	}

	public Byte getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Byte isDelete) {
		this.isDelete = isDelete;
	}

	public OutMeeting getOutMeetingId() {
		return outMeetingId;
	}

	public void setOutMeetingId(OutMeeting outMeetingId) {
		this.outMeetingId = outMeetingId;
	}

	public CmsUser getLoginId() {
		return loginId;
	}

	public void setLoginId(CmsUser loginId) {
		this.loginId = loginId;
	}

	public MeetingAttachment getOther() {
		return other;
	}

	public void setOther(MeetingAttachment other) {
		this.other = other;
	}
}