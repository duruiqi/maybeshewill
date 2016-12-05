package com.scxh.yhzm.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.scxh.yhzm.dao.mapper.UserMapper;
import com.scxh.yhzm.po.PagingBean;
import com.scxh.yhzm.po.User;
import com.scxh.yhzm.service.UserService;
import com.scxh.yhzm.util.CommonUtils;
import com.scxh.yhzm.util.Encrypt;

public class UserServiceImpl extends BaseServiceImpl<User> implements UserService<User>{
	private UserMapper<User> userMapper;
	
	public void setUserMapper(UserMapper<User> userMapper) {
		this.userMapper = userMapper;
	}
	
	
	@Override
	public User getEntryByCoulmn(User user) {
		return userMapper.getEntryByCoulmn(user);
	}

	
	@Override
	public Map<String, Object> getAllEntry(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return super.getAllEntry(map);
	}



	@Override
	public Integer addEntry(User user) {
		user.setUserId(CommonUtils.uuid());
		String password = Encrypt.md5Encrypt(user.getUserPasswd());
		user.setUserPasswd(password);
		user.setDelMark(false);
		user.setState(false);
		user.setLoginState(false);
		
		return userMapper.saveEntry(user);
	}
	@Override
	public synchronized Integer ativeUser(String stateCode) {
		User user = new User();
		user.setStateCode(stateCode);
		user = userMapper.getEntryByCoulmn(user);
		
		if(user != null){
			if(!user.getState()){
				user.setState(true);
				return userMapper.updateUserState(user);
			}else{
				throw new RuntimeException("提示！！您已经激活过了请勿重复操作！！");
			}
		}else{
			throw new RuntimeException("提示！！请勿进行非法操作！！");
		}
	}	
	
	@Override
	public User userLogin(User user) {
		user.setUserPasswd(Encrypt.md5Encrypt(user.getUserPasswd()));
		user = userMapper.selectOneUser(user);
		if(user != null){
			user.setLoginState(true);
			userMapper.updateLoginState(user);
		}
		return user;
	}
	
	@Override
	public PagingBean<User> getAllEntryFY(Map<String, Object> map) {
		Integer curPage = (Integer) map.get("curPage");
		Integer pageSize = (Integer) map.get("pageSize");
		
		String userName = (String) map.get("userName");
		String remark = (String) map.get("remark");
			
			if(userName != null && !userName.trim().isEmpty()){
				map.put("userName","%" + userName + "%");
			}
			
			if(remark != null && !remark.trim().isEmpty()){
				map.put("remark", "%" + remark + "%");
			}
		Integer totalRecord = userMapper.selectCountEntry(map);
		
		Integer startIndex = (curPage-1)*pageSize;
		map.put("startIndex", startIndex);//select * from tab_name limit startIndex,pageSize;
		List<User> pOrderList= userMapper.getALLEntryFY(map);
		PagingBean<User> pagingBean = new PagingBean<User>(totalRecord,curPage,pageSize,pOrderList);
		
		return pagingBean;
	}


	@Override
	public User getEntryById(Serializable id) {
		return userMapper.getEntryById(id);
	}


	@Override
	public Integer modEntry(User user) {
		return userMapper.updateEntry(user);
	}


	@Override
	public Integer deleteEntry(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return super.deleteEntry(map);
	}

}
