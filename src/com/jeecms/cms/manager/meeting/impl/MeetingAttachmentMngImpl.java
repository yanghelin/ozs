package com.jeecms.cms.manager.meeting.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.meeting.MeetingAttachmentDao;
import com.jeecms.cms.entity.meeting.MeetingAttachment;
import com.jeecms.cms.manager.meeting.MeetingAttachmentMng;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

@Service
@Transactional
public class MeetingAttachmentMngImpl implements MeetingAttachmentMng {
	@Transactional(readOnly = true)
	public Pagination getPage(String name, int pageNo, int pageSize) {
		Pagination page = meetingAttachmentDao.getPage(name, pageNo, pageSize);
		return page;
	}
	
	@Transactional(readOnly = true)
	public List<MeetingAttachment> getList(String name) {
		List<MeetingAttachment> list = meetingAttachmentDao.getList(name);
		return list;
	}

	@Transactional(readOnly = true)
	public MeetingAttachment findById(Integer id) {
		MeetingAttachment entity = meetingAttachmentDao.findById(id);
		return entity;
	}

	public MeetingAttachment addMeetingAttachment(MeetingAttachment bean){
		return bean;
	}

	
	public void updateMeetingAttachment(MeetingAttachment bean){
		Updater<MeetingAttachment> updater = new Updater<MeetingAttachment>(bean);
		meetingAttachmentDao.updateByUpdater(updater);
	}

	public MeetingAttachment saveMeetingAttachment(MeetingAttachment bean) {
		return meetingAttachmentDao.save(bean);
	}

	public MeetingAttachment deleteById(Integer id) {
		return meetingAttachmentDao.deleteById(id);
	}

	public MeetingAttachment[] deleteByIds(Integer[] ids) {
		MeetingAttachment[] beans = new MeetingAttachment[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	@Autowired
	private MeetingAttachmentDao meetingAttachmentDao;

}