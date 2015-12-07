package com.abc.helloworld.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.abc.helloworld.model.User;

@Repository
public class UserDao {
	
	@Autowired
	public SessionFactory sessionFactory;

	private Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}
	
	@Transactional
	public Boolean authorizeForLogin(User user){
		boolean result = false;
		User info = (User) getCurrentSession().get(User.class, user.getUserId());
		System.out.println(info.getPassword());
		return result;
	}
}
