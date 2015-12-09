package com.abc.helloworld.exception.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {
	@ExceptionHandler(Exception.class)
	public String handle404Exception(Exception e) {
		return "error/404";// view name for 404 error
	}
}