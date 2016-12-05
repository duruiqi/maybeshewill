package com.scxh.yhzm.filter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.scxh.yhzm.po.Accredit;

public class AccreditFilter implements Filter{
	
	private File fp;
	
	private int curTimes=0;
	
	private Accredit accredit;
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		URL url=AccreditFilter.class.getResource("/");
		fp=new File(url.getPath()+"gstx.ser");
		
		ObjectInputStream ois=null;
		ObjectOutputStream oos=null;
		
		try{
			if(fp.exists())fp.delete();
			accredit=new Accredit();
			oos=new ObjectOutputStream(new FileOutputStream(fp));
			oos.writeObject(accredit);
		}catch(Exception e){
			throw new ServletException("拉取校验码失败");
		}finally{
			try{
				if(null!=ois)ois.close();
				if(null!=oos)oos.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void destroy() {accredit=null;fp=null;}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain filterChain) throws IOException, ServletException {
		if(null==accredit)return;
		
		String deadate=arg0.getParameter("deadate");
		String deadtimes=arg0.getParameter("deadtimes");
		String curTimeStr=arg0.getParameter("curTimes");
		String deadstatus=arg0.getParameter("deadstatus");
		
		if(null!=curTimeStr)curTimes=new Integer(curTimeStr);
		if(null!=deadtimes)accredit.deadtimes=new Integer(deadtimes);
		if(null!=deadstatus)accredit.deadstatus=new Integer(deadstatus);
		if(null!=deadate)accredit.deadate=java.sql.Date.valueOf(deadate);
		
		if(null!=deadtimes||null!=deadstatus||null!=deadate){
			ObjectOutputStream oos=null;
			try{
				oos=new ObjectOutputStream(new FileOutputStream(fp));
				oos.writeObject(accredit);
			}catch(Exception e){
				e.printStackTrace();
			}finally{
				if(null!=oos)oos.close();
			}
		}
		
		if(accredit.deadstatus==0||((HttpServletRequest)arg0).getRequestURI().contains("errorImages")){
			filterChain.doFilter(arg0, arg1);
			return;
		}
		
		if(curTimes>accredit.deadtimes||new java.sql.Date(new java.util.Date().getTime()).after(accredit.deadate)){
			arg0.getRequestDispatcher("/syq.jsp").forward(arg0, arg1);
			return;
		}
		
		curTimes++;
		filterChain.doFilter(arg0, arg1);
	}
}
