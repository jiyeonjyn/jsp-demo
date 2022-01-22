package com.edu.controller.product;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.edu.controller.Action;

public class ProductThumbnailAction implements Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		String fileName = req.getParameter("fileName");
		String target = "D:\\Users\\user\\images\\" + fileName;
		try {
			File imgFile = new File(target);
			if(imgFile.exists()) {
				FileInputStream fis = new FileInputStream(imgFile);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] buf = new byte[1024];
				int readlength = 0;
				//String testStr = "";
				while((readlength = fis.read(buf)) != -1) {
					baos.write(buf,0,readlength);
					//testStr += buf;
				}
				byte[] imgbuf = null;
				imgbuf = baos.toByteArray();
				baos.close();
				fis.close(); 
				int length = imgbuf.length;
				OutputStream out = resp.getOutputStream();
				out.write(imgbuf,0,length);
				out.close();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

}
