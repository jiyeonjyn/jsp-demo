package com.edu.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.controller.Action;
import com.edu.controller.HttpUtil;
import com.edu.service.MemberService;

public class SignUpCheckIdAction implements Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		String userid = req.getParameter("userid");
		MemberService service = MemberService.getInstance();
		boolean result = service.memberExists(userid);
		req.setAttribute("result", result);
		req.setAttribute("userid", userid);
		HttpUtil.forward(req, resp, "/signup/signUpCheckId.jsp");
	}

}
