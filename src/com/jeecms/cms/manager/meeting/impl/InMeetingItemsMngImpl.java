package com.jeecms.cms.manager.meeting.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.meeting.InMeetingItemsDao;
import com.jeecms.cms.entity.meeting.InMeetingItems;
import com.jeecms.cms.manager.meeting.InMeetingItemsMng;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;

@Service
@Transactional
public class InMeetingItemsMngImpl implements InMeetingItemsMng {
	@Transactional(readOnly = true)
	public Pagination getPage(String name, int pageNo, int pageSize) {
		Pagination page = inMeetingItemsDao.getPage(name, pageNo, pageSize);
		return page;
	}
	
	@Transactional(readOnly = true)
	public List<InMeetingItems> getList(String name) {
		List<InMeetingItems> list = inMeetingItemsDao.getList(name);
		return list;
	}

	@Transactional(readOnly = true)
	public InMeetingItems findById(Integer id) {
		InMeetingItems entity = inMeetingItemsDao.findById(id);
		return entity;
	}
	
	@Transactional(readOnly = true)
	public List<InMeetingItems> findByMeetingId(Integer meetingId) {
		List<InMeetingItems> itemList = inMeetingItemsDao.findByMeetingId(meetingId);
		return itemList;
	}

	public InMeetingItems addInMeetingItems(InMeetingItems bean){
		return bean;
	}

	
	public void updateInMeetingItems(InMeetingItems bean){
		Updater<InMeetingItems> updater = new Updater<InMeetingItems>(bean);
		inMeetingItemsDao.updateByUpdater(updater);
	}

	public InMeetingItems saveInMeetingItems(InMeetingItems bean) {
		return inMeetingItemsDao.save(bean);
	}

	public InMeetingItems deleteById(Integer id) {
		return inMeetingItemsDao.deleteById(id);
	}

	public InMeetingItems[] deleteByIds(Integer[] ids) {
		InMeetingItems[] beans = new InMeetingItems[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	@Autowired
	private InMeetingItemsDao inMeetingItemsDao;

}