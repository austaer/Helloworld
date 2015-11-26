package com.abc.helloworld.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.abc.helloworld.model.Employee;
import com.abc.helloworld.serviceimpl.HomeServiceImpl;

@Controller
public class HomeController {

	@Autowired
	private HomeServiceImpl service;

	@RequestMapping(value = "/")
	public ModelAndView mainPage() {
		return new ModelAndView("index");
	}

	@RequestMapping(value = "/index")
	public ModelAndView indexPage() {
		return new ModelAndView("index");
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
	public ModelAndView getEmployees(){
		ModelAndView modelAndView = new ModelAndView("index");
		String message = "Show Employee Page.";
		modelAndView.addObject("operationType", "list");
		modelAndView.addObject("employees", service.getEmployees());
		modelAndView.addObject("message", message);
		return modelAndView;
	}
	
	@RequestMapping(value = "/list/{id}", method = RequestMethod.GET)
	public ModelAndView getEmployee(@PathVariable("id") Integer id){
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("employee", service.getEmployee(id));
		modelAndView.addObject("operationType", "listOne");
		return modelAndView;
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editEmployee(@PathVariable("id") Integer id){
		ModelAndView modelAndView = new ModelAndView("index");
		modelAndView.addObject("operationType", "edit");
		modelAndView.addObject("employee", service.getEmployee(id));
		return modelAndView;
	}
	
	@RequestMapping(value = "/edit/{id}", method = RequestMethod.POST)
	public ModelAndView editEmployee(@ModelAttribute Employee employee){
		ModelAndView modelAndView = new ModelAndView("index");
		service.updateEmployee(employee);
		return modelAndView;
	}
	
	/*
	 * @RequestMapping(value = "/index", method = RequestMethod.GET) public
	 * String getIndex() { logger.debug("index() is executed!"); try { return
	 * "index"; } catch (Exception e) { logger.debug(e.getMessage()); return "";
	 * }
	 * 
	 * }
	 * 
	 * @RequestMapping(path = "/about/{day}", method = RequestMethod.GET) public
	 * ModelAndView getDay(@PathVariable("day") String day, Model model) {
	 * System.out.println("the day is a today : " + day);
	 * 
	 * ModelAndView mav = new ModelAndView(); mav.setViewName("index");
	 * model.addAttribute("day:", day); mav.addObject(model); return mav; }
	 * 
	 * @RequestMapping(value = "/total", method = RequestMethod.GET) public
	 * ModelAndView getTotal(Model model){ ModelAndView mav = new
	 * ModelAndView(); mav.setViewName("index"); model.addAttribute("total",
	 * service.getTotal()); model.addAttribute("list", service.getList());
	 * return mav; }
	 */
}
