package com.scxh.yhzm.service;

import java.util.Map;

import com.scxh.yhzm.po.Admin;

public interface AdminService {
	   public Admin login(Admin admin);
	   public Integer modAdmin(Map<String,Object> paramMap);
}
