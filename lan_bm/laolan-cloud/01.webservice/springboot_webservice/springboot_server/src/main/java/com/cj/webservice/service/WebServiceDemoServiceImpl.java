package com.cj.webservice.service;
import org.springframework.stereotype.Service;
import javax.jws.WebService;
import java.util.Date;

/**
 * 描述：
 *
 * @author caojing
 * @create 2020-02-14-14:56
 */
@WebService(serviceName = "fileDeviceService",//对外发布的服务名
        targetNamespace = "http://service.webservice.cj.com",//指定你想要的名称空间，通常使用使用包名反转
        endpointInterface = "com.cj.webservice.service.WebServiceDemoService")
@Service
public class WebServiceDemoServiceImpl implements WebServiceDemoService {
    @Override
    public String hello(String name) {
        String result = "老兰测试结果： hello:" + name + ": " + new Date();
        System.out.println(result);
        return result;
    }
}
