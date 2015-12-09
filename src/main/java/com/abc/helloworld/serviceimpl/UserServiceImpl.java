package com.abc.helloworld.serviceimpl;

import java.util.HashMap;
import java.util.List;
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
		List<User> list = dao.authorizeForLogin(user);
		Map<String, String> map = new HashMap<String, String>();
		if (list.isEmpty()) {
			map.put("view", "error");
		} else {
			map.put("view", "sucess");
			map.put("username", list.get(0).getUsername());
		}
		map.put("isEmpty", String.valueOf(list.isEmpty()));
		return map;
	}
}
