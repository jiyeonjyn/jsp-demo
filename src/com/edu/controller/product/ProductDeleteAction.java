package com.edu.controller.product;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.controller.Action;
import com.edu.controller.HttpUtil;
import com.edu.service.ProductService;

public class ProductDeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		String[] productNums = req.getParameterValues("delete_num");
		String[] fileNames = new String[8]; //한 페이지 당 최대 product 개수
		boolean result = true;
		ProductService service = ProductService.getInstance();
		int i = 0;
		for (String num : productNums) {
			fileNames[i++] = service.getFileName(Integer.parseInt(num));
			result = result && service.deleteProduct(Integer.parseInt(num));
		}
		
		if (result) {
			String target = null;
			for (int j=0; j<i; j++) {
				target = "D:\\Users\\user\\images\\" + fileNames[j];
				File imgfile = new File(target);
				if(imgfile.exists()){
					result = result && imgfile.delete();
				}
			}
		}
		req.setAttribute("state", "삭제");
		req.setAttribute("result", result);
		HttpUtil.forward(req, resp, "/product/productResult.jsp");
	}

}
