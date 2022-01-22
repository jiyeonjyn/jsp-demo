package com.edu.controller.comment;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.edu.controller.Action;
import com.edu.service.CommentService;

public class CommentDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
//		int boardNum = Integer.parseInt(req.getParameter("brdNo"));
//		int commentNum = Integer.parseInt(req.getParameter("cmtNo"));
//		
//		CommentService service = CommentService.getInstance();
//		boolean result = service.removeComment(commentNum);
//		
//		req.setAttribute("result", result);
//		req.setAttribute("state", "삭제");
//		req.setAttribute("parentnum", boardNum);
//		HttpUtil.forward(req, resp, "/board/commentResult.jsp");
		
		String jsonParam = req.getParameter("json");
		JSONObject json = (JSONObject) JSONValue.parse(jsonParam);
		
		CommentService service = CommentService.getInstance();
		boolean result = service.removeComment(Integer.parseInt(String.valueOf(json.get("cmtNo"))));
		HashMap<String, Object> obj = new HashMap<>();
		obj.put("result", result);
		JSONObject resjson = new JSONObject(obj);
		
		PrintWriter out = null;
		try {
			out = resp.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print(resjson);
		out.close();
	}

	
}
