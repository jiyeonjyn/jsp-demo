package com.edu.service;

import java.util.ArrayList;

import com.edu.dao.ProductDAO;
import com.edu.vo.ProductVO;

public class ProductService {
	private static ProductService service = new ProductService();
	private ProductService() {}
	public static ProductService getInstance() {
		return service;
	}
	
	public ProductDAO dao = ProductDAO.getInstance();
	int postsPerPage = 8; // 한 페이지 당 게시물 수
	
	public boolean insertProduct(ProductVO product) {
		return dao.productInsert(product);
	}
	
	public ArrayList<ProductVO> getProductList(int page) {
		int start = (page-1)*postsPerPage;
		ArrayList<ProductVO> list = dao.productListSelect(start, postsPerPage);
		return list;
	}
	
	public int getTotalPages() {
		int totalCount = dao.productCountSelect();
		int totalPages = (totalCount-1)/postsPerPage + 1;
		return totalPages;
	}
	
	public String getFileName(int num) {
		return dao.productImgfileSelect(num);
	}
	
	public boolean deleteProduct(int num) {
		return dao.productDelete(num);
	}
}
