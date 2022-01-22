package com.edu.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.controller.Action;
import com.edu.controller.HttpUtil;
import com.edu.service.BoardService;
import com.edu.service.CommentService;

public class BoardDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		int num = Integer.parseInt( req.getParameter("brdNo") );
		BoardService boardService = BoardService.getInstance();
		boolean result = boardService.removePost(num);
		CommentService commentService = CommentService.getInstance();
		if (result) {
			result = commentService.removeAllComment(num);
		}
		req.setAttribute("state", "삭제");
		req.setAttribute("result", result);
		HttpUtil.forward(req, resp, "/board/boardResult.jsp");
	}

}
