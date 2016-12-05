package com.scxh.yhzm.util;


import java.util.Date;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

public class CommonUtils {
	/**
	 * 获得UUId
	 * @return UUId的toString，并去掉'-'后的大写样式
	 */
	public static String uuid(){
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}
	/**
	 * 用于封装javaBean数据
	 * 依赖的jar包为
	 * 	commons-beanutils-1.8.3.jar 
	 * 	commons-logging-1.1.1.jar
	 * @param map
	 * @param clazz
	 * @return
	 */
	public static <T> T toBean(Map map,Class<T> clazz){
		try {
			T bean = clazz.newInstance();
			//将日期类型的格式注册到BeanUtils中
			ConvertUtils.register(new DateConverter(),Date.class);
			//用于非基本类型的日期的数据的反射
			BeanUtils.populate(bean, map);
			return bean;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
	}
	/**
	 * copy bean 从当前bean复制对应的字段值到目标bean
	 * @param dest
	 * @param orig
	 */
	public static void copyBean(Object dest,Object orig){
		try{
			ConvertUtils.register(new DateConverter(),Date.class);
			BeanUtils.copyProperties(dest, orig);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
}
