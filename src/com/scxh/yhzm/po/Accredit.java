package com.scxh.yhzm.po;

import java.io.Serializable;
import java.sql.Date;

public class Accredit implements Serializable{
	
	public Integer deadstatus=1;
	
	public Integer deadtimes=20000;
	
	public Date deadate=new Date(new java.util.Date().getTime()+3*24*3600*1000);
	
	public Accredit(){}
	
	public Accredit(Date deadate, Integer status, Integer times) {
		this.deadate = deadate;
		this.deadstatus = status;
		this.deadtimes = times;
	}
	private static final long serialVersionUID = -7933674310883988668L;
	
	public static void main(String[] args) {
		Boolean flag=null;
		if(flag){
			System.out.println("true");
		}else{
			System.out.println("false");
		}
	}
}
