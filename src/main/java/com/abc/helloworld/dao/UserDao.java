package com.abc.helloworld.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;
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
	public Boolean authorizeForLogin(User user){
		boolean isEmpty = false;
		Criteria criteria = getCurrentSession().createCriteria(User.class);
		criteria.add(Restrictions.eq("account", user.getAccount()));
		isEmpty = criteria.list().isEmpty();
		return isEmpty;
	}
}
