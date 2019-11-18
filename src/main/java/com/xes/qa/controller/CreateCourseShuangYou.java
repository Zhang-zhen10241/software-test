package com.xes.qa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CreateCourseShuangYou {
	@RequestMapping(value = { "/shuangyou" })
	public String createShuangyou() {
		System.out.println("==createShuangyou===================");
		return "shuangyou";
	}


}
