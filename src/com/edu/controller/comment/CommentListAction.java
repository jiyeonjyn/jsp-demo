package com.edu.controller.comment;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.edu.controller.Action;
import com.edu.service.CommentService;
import com.edu.vo.CommentVO;

public class CommentListAction implements Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		String jsonParam = req.getParameter("json");
		JSONObject json = (JSONObject) JSONValue.parse(jsonParam);
		JSONObject commentList = null;
		HashMap<String, Object> obj = null;
		HashMap<String, Object> subObj = null;
		
		CommentService service = CommentService.getInstance();
		ArrayList<CommentVO> list = service.getCommentList(Integer.parseInt(String.valueOf(json.get("brdNo"))));
		obj = new HashMap<String, Object>();
		if (list != null) {
			int i = 0;
			for (CommentVO comment : list) {
				subObj = new HashMap<String, Object>();
				subObj.put("num", comment.getNum());
				subObj.put("content", comment.getContent());
				subObj.put("userid", comment.getUserid());
				subObj.put("name", comment.getName());
				subObj.put("regdate", comment.getRegdate());
				subObj.put("parentnum", comment.getParentnum());
				obj.put(Integer.toString(i), subObj);
				i++;
			}
		}
		commentList = new JSONObject(obj);

		PrintWriter out = null;
		try {
			out = resp.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print(commentList);
		out.close();
	}

}
