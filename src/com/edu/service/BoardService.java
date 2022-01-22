package com.edu.service;

import java.util.ArrayList;

import com.edu.dao.BoardDAO;
import com.edu.vo.BoardVO;

public class BoardService {
	private static BoardService bs = new BoardService();
	private BoardService() {}
	public static BoardService getInstance() {
		return bs;
	}
	
	public BoardDAO dao = BoardDAO.getInstance();
	int postsPerPage = 5; // 한 페이지 당 게시물 수

	public boolean postBoard(BoardVO board) {
		boolean result = dao.boardInsert(board);
		return result;
	}
	
	public ArrayList<BoardVO> getBoardList(int page) {
		int start = (page-1)*postsPerPage;
		ArrayList<BoardVO> list = dao.boardListSelect(start, postsPerPage);
		return list;
	}
	
	public int getTotalPages() {
		int totalCount = dao.boardCountSelect();
		int totalPages = (totalCount-1)/postsPerPage + 1;
		return totalPages;
	}
	
	public BoardVO getBoard(int num) {
		BoardVO board = dao.boardSelect(num);
		return board;
	}
	
	public String getWriter(int num) {
		String writer = dao.useridSelect(num);
		return writer;
	}
	
	public boolean updateViews(int num) {
		boolean result = dao.viewsUpdate(num);
		return result;
	}
	
	public boolean updateBoard(int num, String newTitle, String newContent) {
		boolean result = dao.boardUpdate(num, newTitle, newContent);
		return result;
	}
	
	public boolean removePost(int num) {
		boolean result = dao.boardDelete(num);
		return result;
	}
}
