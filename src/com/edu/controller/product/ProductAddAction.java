package com.edu.controller.product;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.controller.Action;
import com.edu.controller.HttpUtil;
import com.edu.service.ProductService;
import com.edu.vo.ProductVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class ProductAddAction implements Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		String filePath = "D:\\Users\\user\\images";
		MultipartRequest mr = null;
		try {
			mr = new MultipartRequest(req, filePath, 5*1024*1024, "UTF-8", new DefaultFileRenamePolicy());
			//제한 용량 (5 * 1024 * 1024 = 5MB)
		} catch (IOException e) {
			e.printStackTrace();
		}
		String originalFileName = mr.getOriginalFileName("imgfile");
		String fileName = null;
		if (originalFileName != null) {
			fileName = mr.getFilesystemName("imgfile");
		}

		String name = mr.getParameter("name");
		String brief = mr.getParameter("brief");
		String imgfile = fileName;
		String detailpage = mr.getParameter("detailpage");
		
		ProductVO product = new ProductVO();
		product.setName(name);
		product.setBrief(brief);
		product.setImgfile(imgfile);
		product.setDetailpage(detailpage);
		ProductService service = ProductService.getInstance();
		boolean result = service.insertProduct(product);
		req.setAttribute("state", "추가");
		req.setAttribute("result", result);
		HttpUtil.forward(req, resp, "/product/productResult.jsp");
	}
}
