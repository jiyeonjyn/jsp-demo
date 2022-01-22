package com.edu.controller.product;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.controller.Action;
import com.edu.controller.HttpUtil;
import com.edu.service.ProductService;
import com.edu.vo.ProductVO;

public class ProductListAction implements Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		String pageParam = req.getParameter("page");
		int page = pageParam == null ? 1 : Integer.parseInt(pageParam);
		ProductService service = ProductService.getInstance();
		ArrayList<ProductVO> list = service.getProductList(page);
		int totalPages = service.getTotalPages();
		req.setAttribute("productList", list);
		req.setAttribute("totalPages", totalPages);
		req.setAttribute("currentPage", page);
		HttpUtil.forward(req, resp, "/product/productList.jsp");
	}

}
