package com.atguigu.task;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.internet.MimeMessage;
import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot04TaskApplicationTests {

	@Autowired
	JavaMailSenderImpl mailSender;

	@Test
	public void contextLoads() {
		SimpleMailMessage message = new SimpleMailMessage();
		//邮件设置
		message.setSubject("通知-今晚开会");
		message.setText("今晚7:30开会.....");

		message.setTo("991807467@qq.com");
		message.setFrom("2476955538@qq.com");

		mailSender.send(message);
	}

	@Test
	public void test02() throws  Exception{
		//1、创建一个复杂的消息邮件
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

		//邮件设置
		helper.setSubject("通知");
		helper.setText("<b style='color:red'>今天 7:30 开会</b>",true);

		helper.setTo("1960302182@qq.com");
		helper.setFrom("2476955538@qq.com");

		//上传文件
		helper.addAttachment("桌面.jpg",new File("D:\\我的\\image\\桌面.jpg"));
//		helper.addAttachment("2.gif",new File("G:\\我的资料\\图片\\gif\\2.gif"));
//		helper.addAttachment("3.gif",new File("G:\\我的资料\\图片\\gif\\3.gif"));

		mailSender.send(mimeMessage);

	}

}
