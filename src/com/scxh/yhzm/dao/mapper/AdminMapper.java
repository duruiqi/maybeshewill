package com.scxh.yhzm.dao.mapper;

import com.scxh.yhzm.po.Admin;

public interface AdminMapper<T> extends BaseMapper<T>{
	
	   public Admin adminLogin(Admin admin);
	   public Integer alterPasswd(Admin admin);
}
