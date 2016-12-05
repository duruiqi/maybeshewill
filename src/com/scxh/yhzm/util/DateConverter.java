package com.scxh.yhzm.util;

import java.util.Locale;

import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.locale.converters.DateLocaleConverter;

public class DateConverter implements Converter{

	@Override
	public Object convert(Class type, Object value) {
		if(value == null){//这里是非空判断
			return null;			
		}
		
		if(!(value instanceof String)){//如果不是字符串类型则不转换，原样返回
			return value;
		}
		String val = (String)value;
		DateLocaleConverter dlc = new DateLocaleConverter(Locale.CHINA, "yyyy-MM-dd");
//		SimpleDateFormat sdf = new SimpleDateFormat();
		//使用自定义的日期格式
		try {			
//			return sdf.parse(val);//将数据转换成date格式并返回
			return dlc.convert(val);//将数据转换成date格式并返回
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
