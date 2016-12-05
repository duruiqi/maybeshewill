package com.scxh.yhzm.test;

import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
public class TestMain {
//	private MailUtils mailUtils; 
	private JavaMailSenderImpl javaMailSender;
	
/*	@Test
	public void test(){
		   //创建邮件  
        MailBean mailBean = new MailBean();  
        mailBean.setFrom("linpxing@163.com");  
        mailBean.setSubject("这是一封来自于linpxin的测试邮件");  
        mailBean.setToEmails(new String[]{"1562086621@qq.com"});  
        mailBean.setTemplate("hello ${userName} !<a href='www.baidu.com' >baidu</a>");  
        Map map = new HashMap();  
        map.put("userName", "Haley Wang");  
        mailBean.setData(map);  
        //发送邮件  
        try {  
        	mailUtils.send(mailBean);  
        } catch (MessagingException e) {  
            e.printStackTrace();  
        } 
	}

	public void setMailUtils(MailUtils mailUtils) {
		this.mailUtils = mailUtils;
	}*/
	@Test
	public void test(){
	}
	@Test
	public void test2() throws Exception{
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		javaMailSender = context.getBean("javaMailSender",JavaMailSenderImpl.class);

		// 建立邮件消息,发送简单邮件和html邮件的区别
		MimeMessage mailMessage = javaMailSender.createMimeMessage();
		// 注意这里的boolean,等于真的时候才能嵌套图片，在构建MimeMessageHelper时候，所给定的值是true表示启用，
		// multipart模式
		MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage,true, "utf-8");
		// 设置收件人，寄件人
		messageHelper.setTo("1562086621@qq.com");
		messageHelper.setFrom("m15528052137@163.com");
		messageHelper.setSubject("测试邮件中嵌套图片!！");
		// true 表示启动HTML格式的邮件
		messageHelper.setText("<a href='http://www.baidu.com'>测试链接</a>",true);

		// FileSystemResource img = new FileSystemResource(new
		// File("f:/123.jpg"));

		// 发送邮件
		javaMailSender.send(mailMessage);

		System.out.println("邮件发送成功..");
	}
}
