package com.leyou.httpdemo;

import com.leyou.httpdemo.pojo.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HttpDemoApplication.class)
public class RestTemplateTest {

	@Autowired
	private RestTemplate restTemplate;

	@Test
	public void testGet() {
		User user = this.restTemplate.getForObject("http://localhost:8080/hello", User.class);
		System.out.println(user);
	}

}
