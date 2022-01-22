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

import com.edu.vo.CommentVO;

public class CommentDAO {
	private static CommentDAO dao = new CommentDAO();
	private CommentDAO() {}
	public static CommentDAO getInstance() {
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
	
	public boolean commentInsert(CommentVO comment) {
		boolean result = false;
		sql = "INSERT INTO COMMENT(content, userid, name, parentnum) VALUES(?, ?, ?, ?)";
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, comment.getContent());
			ps.setString(2, comment.getUserid());
			ps.setString(3, comment.getName());
			ps.setInt(4, comment.getParentnum());
			result = ps.executeUpdate() == 1;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, ps);			
		}
		return result;
	}
	
	public ArrayList<CommentVO> commentListSelect(int parentNum) {
		ArrayList<CommentVO> list = new ArrayList<CommentVO>();
		CommentVO comment = null;
		sql = "SELECT * FROM comment WHERE parentnum = ? ORDER BY num ASC";

		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, parentNum);
			rs = ps.executeQuery();
			while (rs.next()) {
				comment = new CommentVO();
				comment.setNum(rs.getInt("num"));
				comment.setContent(rs.getString("content"));
				comment.setUserid(rs.getString("userid"));
				comment.setName(rs.getString("name"));
				comment.setRegdate(rs.getString("regdate"));
				comment.setParentnum(rs.getInt("parentnum"));
				list.add(comment);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return list.isEmpty() ? null : list;
	}

	
	public boolean commentDelete(int num) {
		boolean result = false;
		sql = "DELETE FROM comment WHERE num = ?";
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
	
	public boolean commentDeleteAll(int parentNum) {
		boolean result = false;
		sql = "DELETE FROM comment WHERE parentnum = ?";
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, parentNum);
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
