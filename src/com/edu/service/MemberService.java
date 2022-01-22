package com.edu.service;

import java.util.ArrayList;

import com.edu.dao.MemberDAO;
import com.edu.vo.MemberVO;

public class MemberService {
	// 싱글톤 패턴
	private static MemberService service = new MemberService();
	private MemberService() {}
	public static MemberService getInstance() {
		return service;
	}
	
	public MemberDAO dao = MemberDAO.getInstance();

	public boolean regMember(MemberVO member) {
		boolean result = dao.memberInsert(member);
		return result;
	}
	
	public MemberVO getMember(String id, String pw) {
		MemberVO member = dao.memberSelect(id, pw);
		return member;
	}
	
	public boolean memberExists(String userid) {
		boolean result = dao.memberSelectByUserid(userid);
		return result;
	}
	
	public ArrayList<String> getUseridByMail(String mail) {
		return dao.useridSelectByMail(mail);
	}
	
}
