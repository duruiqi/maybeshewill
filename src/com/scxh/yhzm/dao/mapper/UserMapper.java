package com.scxh.yhzm.dao.mapper;


public interface UserMapper<T> extends BaseMapper<T>{
	Integer updateUserState(T t);
	T selectOneUser(T t);
	Integer updateLoginState(T t);
}
