package com.abc.helloworld.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.helloworld.dao.UserDao;
import com.abc.helloworld.model.User;
import com.abc.helloworld.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao dao;	
	
	@Override
	public Boolean authorizeForLogin(User user) {
		boolean result = dao.authorizeForLogin(user);
		return result;
	}

}
