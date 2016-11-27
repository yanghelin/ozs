package com.jeecms.cms.entity.meeting;

import java.io.Serializable;

public class InMeetingRoomDetail implements Serializable {
    private Integer id;

    //private Integer meetingId;

    //private Integer roomId;

    //private Integer itemsId;

    private Integer itemNum;
    
    private InMeeting meetingId;
    
    private InMeetingRoom roomId;
    
    private InMeetingItems itemsId;
    
    private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getItemNum() {
		return itemNum;
	}

	public void setItemNum(Integer itemNum) {
		this.itemNum = itemNum;
	}

	public InMeeting getMeetingId() {
		return meetingId;
	}

	public void setMeetingId(InMeeting meetingId) {
		this.meetingId = meetingId;
	}

	public InMeetingRoom getRoomId() {
		return roomId;
	}

	public void setRoomId(InMeetingRoom roomId) {
		this.roomId = roomId;
	}

	public InMeetingItems getItemsId() {
		return itemsId;
	}

	public void setItemsId(InMeetingItems itemsId) {
		this.itemsId = itemsId;
	}
}