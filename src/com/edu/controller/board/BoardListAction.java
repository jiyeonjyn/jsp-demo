package com.edu.controller.board;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.controller.Action;
import com.edu.controller.HttpUtil;
import com.edu.service.BoardService;
import com.edu.vo.BoardVO;

public class BoardListAction implements Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		int page = Integer.parseInt(req.getParameter("page"));
		BoardService service = BoardService.getInstance();
		ArrayList<BoardVO> list = service.getBoardList(page);
		int totalPages = service.getTotalPages();
		req.setAttribute("boardList", list);
		req.setAttribute("totalPages", totalPages); 
		req.setAttribute("currentPage", page);
		
		HttpUtil.forward(req, resp, "/board/boardList.jsp");
	}

}
