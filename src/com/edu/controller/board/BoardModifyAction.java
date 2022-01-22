package com.edu.controller.board;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.controller.Action;
import com.edu.service.BoardService;

public class BoardModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		int num = Integer.parseInt(req.getParameter("brdNo"));
		String newTitle = req.getParameter("title");
		String newContent = req.getParameter("content");
		
		BoardService service = BoardService.getInstance();
		boolean result = service.updateBoard(num, newTitle, newContent);
		req.setAttribute("result", result);
		
		try {
			resp.sendRedirect("/DEMO/boardRead.do?brdNo="+num);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
