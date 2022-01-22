package com.edu.controller.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.edu.controller.Action;
import com.edu.service.MemberService;

public class FindIdAction implements Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		String jsonParam = req.getParameter("json");
		JSONObject json = (JSONObject) JSONValue.parse(jsonParam); //json-simple-1.1.1.jar
		JSONObject useridList = null;
		HashMap<String, Object> obj = null;
		
		MemberService service = MemberService.getInstance();
		ArrayList<String> list = service.getUseridByMail(String.valueOf(json.get("mail")));		
		obj = new HashMap<String, Object>();
		if (list != null) {
			int i = 0;
			for (String userid : list) {
				userid = userid.substring(0, 2)
						+ new String(new char[userid.length()-4]).replace("\0", "*")
						+ userid.substring(userid.length()-2);
				obj.put(Integer.toString(i), userid);
				i++;
			}
		}
		useridList = new JSONObject(obj);

		PrintWriter out = null;
		try {
			out = resp.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print(useridList);
		out.close();
	}

}
