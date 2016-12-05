package com.scxh.yhzm.service;



public interface UserService<T> extends BaseService<T>{
	Integer ativeUser(String stateCode); 
	T userLogin(T t);
}
