package com.lan._1utils._5socket;

import com.lan._1utils._3date._toDate;
import org.junit.Test;

import java.io.IOException;
import java.net.*;

public class URIUtilTest {

	@Test
	public void getNowDate() {
//		String testOpenApiAuth = URIUtil.serverPortTest("http://honeycombtest.peopleyun.cn/dam/service/auth?", 30); // 测试地址是否通
		String testOpenApiAuth = URIUtil.serverPortTest("https://datagov.beijingcloud.com.cn:18081/openapi/service/auth?", 30); // 测试地址是否通
		System.out.println(testOpenApiAuth);
	}
}
