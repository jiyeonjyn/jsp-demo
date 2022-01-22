package com.edu.controller.member;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.edu.controller.Action;
import com.edu.controller.HttpUtil;
import com.edu.service.MemberService;
import com.edu.vo.MemberVO;

public class SignInAction implements Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		String id = req.getParameter("id");
		String pw = req.getParameter("pw");
		
		boolean rememberMe = Boolean.parseBoolean(req.getParameter("remember_me"));
		if(rememberMe) {
			Cookie cookie = new Cookie("rememberId", id);
			cookie.setMaxAge(60 * 60 * 24 * 365); // 1년
			cookie.setPath("/");
			resp.addCookie(cookie);
		} else { // 기존 쿠키 삭제
			Cookie cookie = new Cookie("rememberId", null);
			cookie.setMaxAge(0);
			cookie.setPath("/");
			resp.addCookie(cookie);
		}
		MemberService service = MemberService.getInstance();
		MemberVO member = service.getMember(id, pw);
		if(member != null){
			HttpSession session = req.getSession();
			session.setAttribute("currentName", member.getName());
			session.setAttribute("currentId", id);
		}
		HttpUtil.forward(req, resp, "/signin/signInResult.jsp");
	}

}
