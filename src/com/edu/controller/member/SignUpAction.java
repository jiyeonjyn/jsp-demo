package com.edu.controller.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.controller.Action;
import com.edu.controller.HttpUtil;
import com.edu.service.MemberService;
import com.edu.vo.MemberVO;

public class SignUpAction implements Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		String id = req.getParameter("userid");
		String pw = req.getParameter("userpw");
		String name = req.getParameter("name");
		String mail = req.getParameter("mail");
		
		MemberVO member = new MemberVO();
		member.setUserid(id);
		member.setUserpw(pw);
		member.setName(name);
		member.setMail(mail);
		
		MemberService service = MemberService.getInstance();
		boolean result = service.regMember(member);
		req.setAttribute("name", name);	
		req.setAttribute("result", result);
		HttpUtil.forward(req, resp, "/signup/signUpResult.jsp");
	}

}
