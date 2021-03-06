package com.abc.helloworld.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.abc.helloworld.model.Employee;

import java.util.*;

@Repository
public class HomeDao {

//	private JdbcTemplate jdbcTemplate;
	
/*	@Autowired
	public HomeDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Map<String, Object>> getEmployee() {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		result = jdbcTemplate.queryForList("select count(*) as total from employee;");
		logger.debug("select count(*) as total from employee;");
		return result;
	}*/
	
	@Autowired
	public SessionFactory sessionFactory;
	
	private Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@Transactional
	public void addEmployee(Employee employee){
		getCurrentSession().save(employee);
	}
	
	@Transactional
	public Employee getEmployee(int id){
		Employee employee = (Employee) getCurrentSession().get(Employee.class, id);
		return employee;
	}
	
	@Transactional
	public void updateEmployee(Employee employee){
		Employee employeeToUpdate = getEmployee(employee.getId());
		employeeToUpdate.setName(employee.getName());
		employeeToUpdate.setAddress(employee.getAddress());
		employeeToUpdate.setPhone(employee.getPhone());
		getCurrentSession().update(employeeToUpdate);
	}
	
	@Transactional
	public Boolean deleteEmployee(int id){
		Employee employeeToDelete = getEmployee(id);
		if(employeeToDelete != null){
			try{
				getCurrentSession().delete(employeeToDelete);
				return true;
			} catch(Exception e){
				e.printStackTrace();
				return false;
			}
			
		}
		return false;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional
	public List<Employee> getEmployees(){
		return getCurrentSession().createCriteria(Employee.class)
				.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
	}
}
