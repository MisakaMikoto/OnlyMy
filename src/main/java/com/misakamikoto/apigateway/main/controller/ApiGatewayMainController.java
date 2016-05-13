package com.misakamikoto.apigateway.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/apiGateway")
public class ApiGatewayMainController {
	@RequestMapping(method = RequestMethod.GET)
	public String load() {
		return "apigateway/index";
	}
}