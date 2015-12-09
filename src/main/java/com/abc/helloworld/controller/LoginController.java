package com.abc.helloworld.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.abc.helloworld.model.User;
import com.abc.helloworld.service.UserService;
import com.abc.helloworld.session.Session;

@Controller
@SessionAttributes({ "loginStatus", "username" })
public class LoginController {

	@Autowired
	private UserService service;

	private final String path = "Login";

	@RequestMapping(value = "/checkRegistered")
	public @ResponseBody Boolean checkRegistered() {
		boolean result = false;
		return result;
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginPage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		// 將user instance 傳到頁面
		mav.addObject("user", new User());
		Session session = new Session();
		boolean result = session.setSession(String.valueOf(request.getSession().getAttribute("username")), request);
		if (result == false)
			mav.setViewName("/index");
		else
			mav.setViewName(path + File.separator + "login");
		return mav;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView authorizeForLogin(@ModelAttribute User user, HttpServletRequest request, Model model) {
		ModelAndView mav = new ModelAndView();
		Map<String, String> map = service.authorizeForLogin(user);
		if (Boolean.getBoolean(map.get("isEmpty")) == false) {
			request.getSession().setAttribute("loginStatus", true);
		}
		request.getSession().setAttribute("username", map.get("username"));
		Session session = new Session();
		session.setSession(String.valueOf(map.get("username")), request);
		mav.setViewName(path + File.separator + map.get("view"));
		return mav;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public void logout(WebRequest request, SessionStatus status, HttpServletResponse response)
			throws IOException, ServletException {
	    status.setComplete();
	    request.removeAttribute("username", WebRequest.SCOPE_SESSION);
	    request.removeAttribute("loginStatus", WebRequest.SCOPE_SESSION);
		response.sendRedirect(request.getContextPath() + "/login");
	}
}
