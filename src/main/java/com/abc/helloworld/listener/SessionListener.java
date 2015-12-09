package com.abc.helloworld.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent event) {
		System.out.println("==== Session is created ====");
		final HttpSession session = event.getSession();
		session.setMaxInactiveInterval(2 * 3600);
		session.setAttribute(session.getId(), session);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		System.out.println("==== Session is destroyed ====");
		final HttpSession session = event.getSession();
		session.removeAttribute(session.getId());
	}
}