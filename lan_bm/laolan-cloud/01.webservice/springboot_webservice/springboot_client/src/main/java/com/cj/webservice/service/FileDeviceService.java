
package com.cj.webservice.service;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "fileDeviceService", targetNamespace = "http://service.webservice.cj.com", wsdlLocation = "http://127.0.0.1:8080/webservice/webservice?wsdl")
public class FileDeviceService
    extends Service
{

    private final static URL FILEDEVICESERVICE_WSDL_LOCATION;
    private final static WebServiceException FILEDEVICESERVICE_EXCEPTION;
    private final static QName FILEDEVICESERVICE_QNAME = new QName("http://service.webservice.cj.com", "fileDeviceService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://127.0.0.1:8080/webservice/webservice?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        FILEDEVICESERVICE_WSDL_LOCATION = url;
        FILEDEVICESERVICE_EXCEPTION = e;
    }

    public FileDeviceService() {
        super(__getWsdlLocation(), FILEDEVICESERVICE_QNAME);
    }

    public FileDeviceService(WebServiceFeature... features) {
        super(__getWsdlLocation(), FILEDEVICESERVICE_QNAME, features);
    }

    public FileDeviceService(URL wsdlLocation) {
        super(wsdlLocation, FILEDEVICESERVICE_QNAME);
    }

    public FileDeviceService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, FILEDEVICESERVICE_QNAME, features);
    }

    public FileDeviceService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public FileDeviceService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns WebServiceDemoService
     */
    @WebEndpoint(name = "WebServiceDemoServiceImplPort")
    public WebServiceDemoService getWebServiceDemoServiceImplPort() {
        return super.getPort(new QName("http://service.webservice.cj.com", "WebServiceDemoServiceImplPort"), WebServiceDemoService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns WebServiceDemoService
     */
    @WebEndpoint(name = "WebServiceDemoServiceImplPort")
    public WebServiceDemoService getWebServiceDemoServiceImplPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://service.webservice.cj.com", "WebServiceDemoServiceImplPort"), WebServiceDemoService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (FILEDEVICESERVICE_EXCEPTION!= null) {
            throw FILEDEVICESERVICE_EXCEPTION;
        }
        return FILEDEVICESERVICE_WSDL_LOCATION;
    }

}