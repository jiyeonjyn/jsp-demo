package com.edu.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.controller.board.BoardDeleteAction;
import com.edu.controller.board.BoardListAction;
import com.edu.controller.board.BoardModifyAction;
import com.edu.controller.board.BoardReadAction;
import com.edu.controller.board.BoardWriteAction;
import com.edu.controller.comment.CommentDeleteAction;
import com.edu.controller.comment.CommentListAction;
import com.edu.controller.comment.CommentWriteAction;
import com.edu.controller.member.FindIdAction;
import com.edu.controller.member.LogOutAction;
import com.edu.controller.member.SignInAction;
import com.edu.controller.member.SignUpAction;
import com.edu.controller.member.SignUpCheckIdAction;
import com.edu.controller.product.ProductAddAction;
import com.edu.controller.product.ProductDeleteAction;
import com.edu.controller.product.ProductListAction;
import com.edu.controller.product.ProductListAppendAction;
import com.edu.controller.product.ProductThumbnailAction;

public class Controller extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	String charset;
	HashMap<String, Action> list = null;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		charset = config.getInitParameter("charset");
		list = new HashMap<String, Action>();
		
		list.put("/signUp.do", new SignUpAction());
		list.put("/signUpCheckId.do", new SignUpCheckIdAction());
		list.put("/signIn.do", new SignInAction());
		list.put("/findId.do", new FindIdAction());
		list.put("/logOut.do", new LogOutAction());
		list.put("/boardList.do", new BoardListAction());
		list.put("/boardWrite.do", new BoardWriteAction());
		list.put("/boardRead.do", new BoardReadAction());
		list.put("/boardModify.do", new BoardModifyAction());
		list.put("/boardDelete.do", new BoardDeleteAction());
		list.put("/commentList.do", new CommentListAction());
		list.put("/commentWrite.do", new CommentWriteAction());
		list.put("/commentDelete.do", new CommentDeleteAction());
		list.put("/productList.do", new ProductListAction());
		list.put("/productListAppend.do", new ProductListAppendAction());
		list.put("/productThumbnail.do", new ProductThumbnailAction());
		list.put("/productAdd.do", new ProductAddAction());
		list.put("/productDelete.do", new ProductDeleteAction());
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding(charset);
		resp.setContentType("text/html; charset=" + charset);

		String requestURI = req.getRequestURI(); //요청된 URI(주소) : /프로젝트명/~.do
		String contextPath = req.getContextPath(); // /프로젝트명
		String path = requestURI.substring(contextPath.length());

		list.get(path).execute(req, resp);
	}
}