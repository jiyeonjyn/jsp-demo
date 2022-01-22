package com.edu.controller.product;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.edu.controller.Action;
import com.edu.service.ProductService;
import com.edu.vo.ProductVO;

public class ProductListAppendAction implements Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		String jsonParam = req.getParameter("json");
		JSONObject json = (JSONObject) JSONValue.parse(jsonParam); //json-simple-1.1.1.jar
		JSONObject productList = null;
		HashMap<String, Object> obj = null;
		HashMap<String, Object> subObj = null;
		
		ProductService service = ProductService.getInstance();
		ArrayList<ProductVO> list = service.getProductList(Integer.parseInt(String.valueOf(json.get("page"))));
		obj = new HashMap<String, Object>();
		if (list != null) {
			int i = 0;
			for (ProductVO product : list) {
				subObj = new HashMap<String, Object>();
				subObj.put("num", Integer.toString(product.getNum()));
				subObj.put("name", product.getName());
				subObj.put("brief", product.getBrief());
				subObj.put("imgfile", "/DEMO/productThumbnail.do?fileName="+product.getImgfile());
				subObj.put("detailpage", product.getDetailpage());
				obj.put(Integer.toString(i), subObj);
				i++;
			}
		}
		productList = new JSONObject(obj);

		PrintWriter out = null;
		try {
			out = resp.getWriter();
		} catch (IOException e) {
			e.printStackTrace();
		}
		out.print(productList);
		out.close();
	}

}
