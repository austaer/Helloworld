package com.abc.helloworld.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.helloworld.dao.HomeDao;
import com.abc.helloworld.model.Employee;
import com.abc.helloworld.service.HomeService;

@Service
public class HomeServiceImpl implements HomeService {

	@Autowired
	private HomeDao dao;
/*
	@Override
	public int getTotal() {
		int total = dao.getEmployee().get(0).get("total").toString().equals(null) ?
				0: Integer.valueOf(dao.getEmployee().get(0).get("total").toString());
		return total;
	};
	
	@Override
	public String getName(String name) {
		return "name : " + name;
	}

	@Override
	public String getAge(int age) {
		return "age : " + String.valueOf(age);
	}
*/

	@Override
	public void addEmployee(Employee employee) {
		dao.addEmployee(employee);
		
	}

	@Override
	public Employee getEmployee(int id) {
		return dao.getEmployee(id);
	}

	@Override
	public void deleteEmployee(int id) {
		dao.deleteEmployee(id);
		
	}

	@Override
	public void updateEmployee(Employee employee) {
		dao.updateEmployee(employee);	
	}

	@Override
	public List<Employee> getEmployees() {
		return dao.getEmployees();
	}

}
