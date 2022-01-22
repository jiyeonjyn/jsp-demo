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
import com.edu.vo.CommentVO;

public class CommentWriteAction implements Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
//		HttpSession session = req.getSession();
//		String id = (String)session.getAttribute("currentId");
//		String name = (String)session.getAttribute("currentName");
//		String content = req.getParameter("content");
//		int parentNum = Integer.parseInt(req.getParameter("parentnum"));
//		
//		CommentVO comment = new CommentVO();
//		comment.setUserid(id);
//		comment.setName(name);
//		comment.setContent(content);
//		comment.setParentnum(parentNum);
//		
//		CommentService service = CommentService.getInstance();
//		boolean result = service.setComment(comment);
//		req.setAttribute("result", result);
//		req.setAttribute("state", "작성");
//		req.setAttribute("parentnum", parentNum);
//		HttpUtil.forward(req, resp, "/board/commentResult.jsp");
		
		String jsonParam = req.getParameter("json");
		JSONObject json = (JSONObject) JSONValue.parse(jsonParam);
		String id = String.valueOf(json.get("userid"));
		String name = String.valueOf(json.get("name"));
		String content = String.valueOf(json.get("content"));
		int parentNum = Integer.parseInt(String.valueOf(json.get("parentnum")));
		
		CommentVO comment = new CommentVO();
		comment.setUserid(id);
		comment.setName(name);
		comment.setContent(content);
		comment.setParentnum(parentNum);
		
		CommentService service = CommentService.getInstance();
		boolean result = service.setComment(comment);
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
