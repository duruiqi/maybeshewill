package com.scxh.yhzm.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.scxh.yhzm.dao.mapper.ContactUsMapper;
import com.scxh.yhzm.po.ContactUs;
import com.scxh.yhzm.service.ContactUsService;
import com.scxh.yhzm.util.CommonUtils;

public class ContactUsServiceImpl  extends BaseServiceImpl<ContactUs> implements ContactUsService<ContactUs>{
	private ContactUsMapper<ContactUs> contactUsMapper;
	
	public void setContactUsMapper(ContactUsMapper<ContactUs> contactUsMapper) {
		this.contactUsMapper = contactUsMapper;
	}
	
	@Override
	public List<ContactUs> getAllEntry2(Map<String, Object> map) {
		List<ContactUs> firmList= contactUsMapper.getAllEntry(CommonUtils.toBean(map, ContactUs.class));		
		return firmList;
	}
	
	
	@Override
	public ContactUs getEntryById(Serializable id){
		return contactUsMapper.getEntryById(id);
	}


	@Override
	public Integer modEntry(Map<String, Object> map) {
		
		return contactUsMapper.updateEntry(CommonUtils.toBean(map, ContactUs.class));
	}

}