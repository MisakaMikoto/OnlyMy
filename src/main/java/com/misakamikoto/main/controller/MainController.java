package com.misakamikoto.main.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
	private static Logger logger = LoggerFactory.getLogger(MainController.class);
	/**
	 * Load model and view.
	 *
	 * @return the model and view
	 */
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView load() {
		ModelAndView modelAndView = new ModelAndView();
		try {
			modelAndView.setViewName("/main/layout");

		} catch (Exception e) {
			logger.error(e.getMessage());

		} finally {
			return modelAndView;
		}

	}
}