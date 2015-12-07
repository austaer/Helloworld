package com.abc.helloworld.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.abc.helloworld.model.Employee;
import com.abc.helloworld.model.User;
import com.abc.helloworld.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserService service;
	
	@RequestMapping(value="/checkRegistered")
	public @ResponseBody Boolean checkRegistered(){
		boolean result = false;
		return result;
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public ModelAndView loginPage(){
		ModelAndView mav = new ModelAndView();
		//將user instance 傳到頁面
		mav.addObject("user", new User());
		mav.setViewName("login");
		return mav;
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView authorizeForLogin(@ModelAttribute User user){
		ModelAndView mav = new ModelAndView();
		service.authorizeForLogin(user);
		return mav;
	}
}
