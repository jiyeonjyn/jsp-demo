package com.edu.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.edu.controller.Action;
import com.edu.controller.HttpUtil;

public class LogOutAction implements Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		session.removeAttribute("currentName");
		session.invalidate();
		HttpUtil.forward(req, resp, "/signin/logOutResult.jsp");
	}

}
