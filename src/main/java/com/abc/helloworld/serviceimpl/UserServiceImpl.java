package com.abc.helloworld.serviceimpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.helloworld.dao.UserDao;
import com.abc.helloworld.model.User;
import com.abc.helloworld.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao dao;

	@Override
	public Map<String, String> authorizeForLogin(User user) {
		boolean isEmpty = dao.authorizeForLogin(user);
		Map<String, String> list = new HashMap<String, String>();
		if (isEmpty) {
			list.put("view", "error");
		} else {
			list.put("view", "sucess");
		}
		list.put("username", list.get("username"));
		list.put("isEmpty", String.valueOf(isEmpty));
		return list;
	}
}
