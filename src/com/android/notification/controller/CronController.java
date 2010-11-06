package com.android.notification.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.android.notification.common.ParseValidateEnum;
import com.android.notification.service.ParserService;

/**
 * 
 * @author marciocamurati
 *
 */
@Controller
public class CronController {
	
	@Autowired
	private ParserService parserService;

	@RequestMapping("/cron")
	public ModelAndView validate()	{
		ModelAndView model = new ModelAndView("/cron/index.jsp");
		
		ParseValidateEnum validateEnum = parserService.validate();
		
		if (validateEnum != null){
			model.addObject("validateEnum", validateEnum);
		}
		
		return model;
	}
}
