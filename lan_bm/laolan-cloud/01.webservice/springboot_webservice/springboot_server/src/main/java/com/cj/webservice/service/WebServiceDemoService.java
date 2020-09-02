
package com.cj.webservice.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface WebServiceDemoService {
    @WebMethod
    public String hello(@WebParam(name = "name") String name);
}

