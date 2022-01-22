package com.edu.controller.product;

import java.util.HashMap;

import org.json.simple.JSONObject;

public class Test {
	public static void main(String[] args) {
		JSONObject productList = null;
		HashMap<String, Object> obj = null;
		HashMap<String, Object> subObj = null;
		
		obj = new HashMap<String, Object>();
		for (int i = 0; i<8; i++) {
			subObj = new HashMap<String, Object>();
			subObj.put("num", Integer.toString(10));
			subObj.put("name", "sdaf");
			subObj.put("brief", "sdaf");
			subObj.put("imgfile", "sdaf");
			subObj.put("detailpage", "sdaf");
			obj.put(Integer.toString(i), subObj);
		}
		
		productList = new JSONObject(obj);
		System.out.println(productList);
	}
}
