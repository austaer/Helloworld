package com.abc.helloworld.service;

import java.util.List;

import com.abc.helloworld.model.Employee;

public interface HomeService {
/*	public String getName(String name);

	public int getTotal();

	public String getAge(int age);
	
	public List<Employee> getList();*/
	
	public void addEmployee(Employee employee);
	
	public Employee getEmployee(int id);
	
	public Boolean deleteEmployee(int id);
	
	public void updateEmployee(Employee employee);
	
	public List<Employee> getEmployees();
}
