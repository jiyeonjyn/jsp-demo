package com.edu.controller.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.edu.controller.Action;
import com.edu.controller.HttpUtil;
import com.edu.service.BoardService;
import com.edu.vo.BoardVO;

public class BoardWriteAction implements Action {
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession();
		String id = (String)session.getAttribute("currentId");
		String name = (String)session.getAttribute("currentName");
		String title = req.getParameter("title");
		String con = req.getParameter("content");
		
		BoardVO board = new BoardVO();
		board.setTitle(title);
		board.setContent(con);
		board.setUserid(id);
		board.setName(name);
		
		BoardService service = BoardService.getInstance();
		boolean result = service.postBoard(board);
		req.setAttribute("state", "작성");
		req.setAttribute("result", result);
		HttpUtil.forward(req, resp, "/board/boardResult.jsp");
	}
}
