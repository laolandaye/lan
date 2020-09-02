package test;

import com.cj.webservice.service.WebServiceDemoService;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
/**
 * 描述：
 *
 * @author caojing
 * @create 2020-02-14-15:37
 */
public class test {
    static WebServiceDemoService webServiceDemoService;
    public static void main(String[] args) {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(WebServiceDemoService.class);
        factory.setAddress("http://localhost:8080/webservice/webservice?wsdl");
        webServiceDemoService = (WebServiceDemoService) factory.create();
        System.out.println(webServiceDemoService.hello("cj"));
    }
}
