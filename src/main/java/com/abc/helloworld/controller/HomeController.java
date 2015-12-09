package com.abc.helloworld.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.abc.helloworld.model.Employee;
import com.abc.helloworld.serviceimpl.HomeServiceImpl;

@Controller
@RequestMapping("/webmgr")
public class HomeController {

	@Autowired
	private HomeServiceImpl service;

	@RequestMapping(value = "/")
	public ModelAndView mainPage(HttpServletRequest request) {
		return new ModelAndView("index");
	}

	@RequestMapping(value = "/index")
	public ModelAndView indexPage(HttpServletRequest request) {
		return new ModelAndView(new RedirectView(request.getContextPath() + "/webmgr/"));
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView addEmployeePage() {
		ModelAndView modelAndView = new ModelAndView("index");
		String message = "Create Employee Page.";
		modelAndView.addObject("operationType", "add");
		modelAndView.addObject("employee", new Employee());
		modelAndView.addObject("message", message);
		return modelAndView;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView addEmployee(@ModelAttribute Employee employee) {
		ModelAndView modelAndView = new ModelAndView("index");
		service.addEmployee(employee);
		String message = "create employee was successed.";
		modelAndView.addObject("message", message);
		return modelAndView;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
	public ModelAndView killEmployee(@PathVariable("id") Integer id) {
		ModelAndView modelAndView = new ModelAndView("index");
		service.deleteEmployee(id);
		String message = "kill employee was successed.";
		modelAndView.addObject("message", message);
		return modelAndView;
	}

	@RequestMapping(value = "/list")
	public ModelAndView getEmployees() {
		ModelAndView modelAndView = new ModelAndView("index");
		String message = "Show Employee Page.";
		modelAndView.addObject("operationType", "list");
		modelAndView.addObject("employees", service.getEmployees());
		modelAndView.addObject("message", message);
		return modelAndView;
	}

	@RequestMapping(value = "/list/{id}", method = RequestMethod.GET)
	public ModelAndView getEmployee(@PathVariable("id") Integer id) {
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("employee", service.getEmployee(id));
		modelAndView.addObject("operationType", "listOne");
		return modelAndView;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editEmployee(@PathVariable("id") Integer id) {
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("operationType", "edit");
		modelAndView.addObject("employee", service.getEmployee(id));
		return modelAndView;
	}

	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public ModelAndView editEmployee(@ModelAttribute Employee employee) {
		ModelAndView modelAndView = new ModelAndView("index");
		service.updateEmployee(employee);
		return modelAndView;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public @ResponseBody String deleteEmployee(@PathVariable("id") int id) {
		String result = "false";
		result = String.valueOf(service.deleteEmployee(id));
		return result;
	}
}
