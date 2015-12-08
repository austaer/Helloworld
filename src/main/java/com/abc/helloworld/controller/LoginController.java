package com.abc.helloworld.controller;

import java.io.File;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.abc.helloworld.model.Employee;
import com.abc.helloworld.model.User;
import com.abc.helloworld.service.UserService;

@Controller
@SessionAttributes({"loginStatus", "username"})
public class LoginController {
	
	@Autowired
	private UserService service;
	
	private final String path = "Login";
	
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
		mav.setViewName(path + File.separator + "login");
		return mav;
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView authorizeForLogin(@ModelAttribute User user, HttpServletRequest requst, Model model){
		ModelAndView mav = new ModelAndView();
		Map<String, String> map = service.authorizeForLogin(user);
		if(Boolean.getBoolean(map.get("isEmpty")) == false){
			requst.getSession().setAttribute("loginStatus",true);
		}
		requst.getSession().setAttribute("username",map.get("username"));
		mav.setViewName(path + File.separator + map.get("view"));
		return mav;
	}
}
