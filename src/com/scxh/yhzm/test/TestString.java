package com.scxh.yhzm.test;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

import org.junit.Test;

public class TestString {

	@Test
	public void test(){
		String str = trimString1("kkkk bbbmmmm ccc df");
		String str2 = trimString2("kkkk bbbmmmm ccc df");
		str = trimString1(str);
		str2 = trimString2(str2);
		System.out.println(str);
		System.out.println(str2);
	}
	public String trimString1(String str){
		Set<Character> sets = new LinkedHashSet<Character>();
		for(int i = 0;i < str.length();i++){
			char c = str.charAt(i);
			sets.add(c);
		}
		
	String strs = Arrays.toString(sets.toArray());
	
	strs = strs.replaceAll("\\[", "");
	strs = strs.replaceAll("\\]", "");
	strs = strs.replaceAll(", ", "");
	return strs;
	}
	
	public String trimString2(String str){
		char[] strs = new char[str.length()];
		int j = 0;
		for(int i = 0;i < str.length();i++){
			char c = str.charAt(i);
			char c2 = 0;
			
			if(i < str.length()-1){
				c2 = str.charAt(i + 1);
			}
			
			if(i == str.length() - 1){
				strs[j++] = c;
				break;
			}
			
			if(c != c2){
				strs[j++] = c;
			}
		}
		return new String(strs,0,j);
	}
}
