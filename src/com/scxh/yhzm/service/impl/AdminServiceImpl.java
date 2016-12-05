package com.scxh.yhzm.service.impl;

import java.util.Map;

import com.scxh.yhzm.dao.mapper.AdminMapper;
import com.scxh.yhzm.po.Admin;
import com.scxh.yhzm.service.AdminService;
import com.scxh.yhzm.util.Encrypt;

public class AdminServiceImpl implements AdminService{
	private AdminMapper<Admin> adminMapper;

	public void setAdminMapper(AdminMapper<Admin> adminMapper) {
		this.adminMapper = adminMapper;
	}

	@Override
	public Admin login(Admin admin) {
		return adminMapper.adminLogin(admin);
	}

	
	@Override
	public Integer modAdmin(Map<String,Object> paramMap) {
		String newPasswd = (String) paramMap.get("newPasswd");
		Admin admin = (Admin)paramMap.get("admin");
		encryptPassword(admin);
		Admin selectAdmin = adminMapper.adminLogin(admin);
		
		if(selectAdmin != null){
			admin.setAdminPasswd(newPasswd);
			encryptPassword(admin);
			return adminMapper.alterPasswd(admin);
		}else{
			throw new RuntimeException("旧密码错误！");
		}
	}
	
	/**
	 * 将明文的密码加密
	 */
	
	public void encryptPassword(Admin admin){
		admin.setAdminPasswd(Encrypt.md5Encrypt(admin.getAdminPasswd()));
	}
	
	public String encryptStr(String str){
		return Encrypt.md5Encrypt(str);
	}
}
