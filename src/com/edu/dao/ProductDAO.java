package com.edu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.edu.vo.ProductVO;

public class ProductDAO {
	private static ProductDAO dao = new ProductDAO();
	private ProductDAO() {}
	public static ProductDAO getInstance() {
		return dao;
	}
	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	
	private static DataSource ds; 
	static { 
		try {
			Context context = new InitialContext(); 
			ds = (DataSource)context.lookup("java:comp/env/jdbc/mysql");
			System.out.println("start DBCP!");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
//	public Connection Connect() {
//		con = null;
//		String dbURL = "jdbc:mysql://localhost:3306/demo?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8";
//		String dbID  = "root";
//		String dbPW  = "1234";
//		
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");
//			con = DriverManager.getConnection(dbURL, dbID, dbPW);
//		} catch (ClassNotFoundException | SQLException e) {
//			e.printStackTrace();
//		}
//
//		System.out.println("DB connect success!");
//		
//		return con;
//	}
	
	public boolean productInsert(ProductVO product) {
		boolean result = false;
		sql = "INSERT INTO PRODUCT(name, brief, imgfile, detailpage) VALUES(?, ?, ?, ?)";
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, product.getName());
			ps.setString(2, product.getBrief());
			ps.setString(3, product.getImgfile());
			ps.setString(4, product.getDetailpage());
			result = ps.executeUpdate() == 1;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, ps);
		}
		return result;
	}
	
	public ArrayList<ProductVO> productListSelect(int start, int count) {
		ArrayList<ProductVO> list = new ArrayList<>();
		ProductVO product = null;
		sql = "SELECT p.* FROM (SELECT num FROM product ORDER BY num DESC LIMIT ?, ?) q "
				+ "JOIN product p ON p.num = q.num ORDER BY p.num DESC";
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, count);
			rs = ps.executeQuery();
			while(rs.next()) {
				product = new ProductVO();
				product.setNum(rs.getInt("num"));
				product.setName(rs.getString("name"));
				product.setBrief(rs.getString("brief"));
				product.setImgfile(rs.getString("imgfile"));
				product.setDetailpage(rs.getString("detailpage"));
				list.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {			
			close(con, ps, rs);
		}
		return list.isEmpty() ? null : list;
	}
	
	public int productCountSelect() {
		int totalCount = 0;
		sql = "SELECT COUNT(num) FROM PRODUCT";
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()) {
				totalCount = rs.getInt("COUNT(num)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {			
			close(con, ps, rs);
		}
		return totalCount;
	}
	
	public String productImgfileSelect(int num) {
		String imgfile = null;
		sql = "SELECT imgfile FROM PRODUCT WHERE num = ?";
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, num);
			rs = ps.executeQuery();
			if(rs.next()) {
				imgfile = rs.getString("imgfile");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {			
			close(con, ps, rs);
		}
		return imgfile;
	}
	
	public boolean productDelete(int num) {
		boolean result = false;
		sql = "DELETE FROM product WHERE num = ?";
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, num);
			result = ps.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, ps);			
		}
		return result;
	}
	
	private static void close(Connection con, PreparedStatement ps, ResultSet rs) {
		try {
			if(rs != null) {
				rs.close();
			}
			if(ps != null) {
				ps.close();
			}
			if(con != null) { 
				con.close(); 
			} 
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	private static void close(Connection con, PreparedStatement ps) {
		close(con, ps, null);
	}
}
