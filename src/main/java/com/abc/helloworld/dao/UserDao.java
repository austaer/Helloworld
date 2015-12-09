package com.abc.helloworld.dao;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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
	public List<User> authorizeForLogin(User user){
		Criteria criteria = getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("account", user.getAccount()));
		criteria.add(Restrictions.eq("password", user.getPassword()));
		return criteria.list();
	}
}
