package com.edu.service;

import java.util.ArrayList;

import com.edu.dao.CommentDAO;
import com.edu.vo.CommentVO;

public class CommentService {
	private static CommentService cs = new CommentService();
	private CommentService() {}
	public static CommentService getInstance() {
		return cs;
	}
	
	public CommentDAO dao = CommentDAO.getInstance();
	
	public boolean setComment(CommentVO comment) {
		boolean result = dao.commentInsert(comment);
		return result;
	}
	
	public ArrayList<CommentVO> getCommentList(int parentNum) {
		ArrayList<CommentVO> list = dao.commentListSelect(parentNum);
		return list;
	}
	
	public boolean removeComment(int num) {
		boolean result = dao.commentDelete(num);
		return result;
	}
	
	public boolean removeAllComment(int parentNum) {
		boolean result = false;
		ArrayList<CommentVO> list = dao.commentListSelect(parentNum);
		if (list == null) {
			result = true;
		} else {
			result = dao.commentDeleteAll(parentNum);			
		}
		return result;
	}

}
