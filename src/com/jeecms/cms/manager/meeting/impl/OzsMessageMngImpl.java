package com.jeecms.cms.manager.meeting.impl;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeecms.cms.dao.meeting.OzsMessageDao;
import com.jeecms.cms.dao.meeting.OzsMessageDetailDao;
import com.jeecms.cms.entity.meeting.OzsMessage;
import com.jeecms.cms.entity.meeting.OzsMessageDetail;
import com.jeecms.cms.manager.meeting.OzsMessageMng;
import com.jeecms.common.hibernate3.Updater;
import com.jeecms.common.page.Pagination;
import com.jeecms.core.entity.CmsUser;

@Service
@Transactional
public class OzsMessageMngImpl implements OzsMessageMng {
	@Transactional(readOnly = true)
	public Pagination getPage(String name, int pageNo, int pageSize) {
		Pagination page = ozsMessageDao.getPage(name, pageNo, pageSize);
		return page;
	}
	
	@Transactional(readOnly = true)
	public List<OzsMessage> getList(String name) {
		List<OzsMessage> list = ozsMessageDao.getList(name);
		return list;
	}

	@Transactional(readOnly = true)
	public OzsMessage findById(Integer id) {
		OzsMessage entity = ozsMessageDao.findById(id);
		return entity;
	}

	public OzsMessage addOzsMessage(OzsMessage bean){
		return bean;
	}

	
	public void updateOzsMessage(OzsMessage bean){
		Updater<OzsMessage> updater = new Updater<OzsMessage>(bean);
		ozsMessageDao.updateByUpdater(updater);
	}

	public OzsMessage saveOzsMessage(OzsMessage bean) {
		OzsMessage message = ozsMessageDao.save(bean);
		
		if(StringUtils.isNotEmpty(message.getSendUsers())) {
			String[] userIds = message.getSendUsers().split(",");
			if(userIds != null && userIds.length>0) {
				for(String userId:userIds) {
					OzsMessageDetail detail = new OzsMessageDetail();
					detail.setCreateTime(new Date());
					OzsMessage om = new OzsMessage();
					om.setId(message.getId());
					detail.setMessageId(om);
					detail.setMessageTitle(message.getTitle());
					detail.setMessageContent(message.getContent());
					detail.setIsDelete((byte)0);
					detail.setSender(message.getSendBy());
					detail.setIsRead((byte)0);
					detail.setIsReply((byte)0);
					CmsUser user = new CmsUser();
					user.setId(Integer.valueOf(userId));
					detail.setReceiver(user);
					ozsMessageDetailDao.save(detail);
				}
			}
		}
		return message;
	}

	public OzsMessage deleteById(Integer id) {
		return ozsMessageDao.deleteById(id);
	}

	public OzsMessage[] deleteByIds(Integer[] ids) {
		OzsMessage[] beans = new OzsMessage[ids.length];
		for (int i = 0, len = ids.length; i < len; i++) {
			beans[i] = deleteById(ids[i]);
		}
		return beans;
	}

	@Autowired
	private OzsMessageDao ozsMessageDao;
	
	@Autowired
	private OzsMessageDetailDao ozsMessageDetailDao;

}