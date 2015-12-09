package com.abc.helloworld.session;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("Session")
public class Session {

	@Autowired
	private HttpSession httpSession;

	private static Map<String, String> sessionIDs = new HashMap<String, String>();

	public Boolean setSession(String username, HttpServletRequest request) {
		httpSession = request.getSession();
		if(username == "null") return true;
		return checkSession(username);
	}

	private Boolean checkSession(String username) {
		if (sessionIDs.containsKey(username)) {
			String ID = sessionIDs.get(username);
			if (ID != httpSession.getId()) {
				sessionIDs.remove(username);
				sessionIDs.put(username, httpSession.getId());
			} else {
				return false;
			}
		} else {
			sessionIDs.put(username, httpSession.getId());
		}
		return true;
	}
}
