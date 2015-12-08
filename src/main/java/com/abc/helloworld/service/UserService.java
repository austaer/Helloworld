package com.abc.helloworld.service;

import java.util.Map;

import com.abc.helloworld.model.User;

public interface UserService {
	public Map<String, String> authorizeForLogin(User user);
}
