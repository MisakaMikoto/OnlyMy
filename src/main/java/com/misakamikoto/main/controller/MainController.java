package com.misakamikoto.main.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * The type Main controller.
 */
@RestController
@RequestMapping("/")
public class MainController {
	/**
	 * Load string.
	 *
	 * @return the string
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView load() {
		return new ModelAndView("main/index");
	}
}