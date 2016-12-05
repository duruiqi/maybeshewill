package com.scxh.yhzm.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Encoder;
/**
 * 加密
 * @author Administrator
 *
 */
public class Encrypt {
	/**
	 * @param msg
	 * @return
	 * 参数msg是要加密的文本，
	 * 该方法主要采用md5加密
	 */
	public static String md5Encrypt(String msg) {
		MessageDigest md = null;
		try {
			md = MessageDigest.getInstance("md5");
			byte[] msgb = md.digest(msg.getBytes());
			BASE64Encoder enc = new BASE64Encoder();
			String temp=enc.encode(msgb);
			return temp.substring(0, temp.length() - 4);
			
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException(e);
		}
	}
}
