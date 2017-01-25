package com.chebada.hardware.helloworld.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/helloworld")
public class HelloWorldController {

	private static final Log log = LogFactory.getLog(HelloWorldController.class);
	
	//private IHelloWorldService helloWorldService;
	
	/*public IHelloWorldService getHelloWorldService() {
		return helloWorldService;
	}*/
	
	//@Resource
	/*public void setHelloWorldService(IHelloService helloWorldService) {
		this.helloWorldService = helloWorldService;
	}*/
	
	
}
