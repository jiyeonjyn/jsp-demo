package com.edu.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.edu.controller.Action;
import com.edu.controller.HttpUtil;
import com.edu.service.BoardService;
import com.edu.vo.BoardVO;

public class BoardReadAction implements Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		int boardNum = Integer.parseInt(req.getParameter("brdNo"));
		BoardService boardService = BoardService.getInstance();
		String userid = boardService.getWriter(boardNum);
		
		//CommentService commentService = CommentService.getInstance();
		//ArrayList<CommentVO> list = commentService.getCommentList(boardNum); // 댓글 가져오기
		
		HttpSession session = req.getSession();
		if(session.getAttribute("currentId") == null || !session.getAttribute("currentId").equals(userid)) {
			boardService.updateViews(boardNum); // 자기 글이 아니면 조회수 1 증가
		}
		BoardVO board = boardService.getBoard(boardNum); // 본문 가져오기
		req.setAttribute("brdVO", board);
		
		String path = null;
		boolean isModify = req.getParameter("modify") == null ? false : true;
		if (isModify) {
			path = "/board/boardModify.jsp";
			
		} else {
			//req.setAttribute("commentList", list);
			path = "/board/boardRead.jsp";
		}
		HttpUtil.forward(req, resp, path);
	}

}
