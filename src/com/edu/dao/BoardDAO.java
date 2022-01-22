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

import com.edu.vo.BoardVO;

public class BoardDAO {
	private static BoardDAO dao = new BoardDAO();
	private BoardDAO() {}
	public static BoardDAO getInstance() {
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
	
	public boolean boardInsert(BoardVO board) {
		boolean result = false;
		sql = "INSERT INTO BOARD(title, content, userid, name, views) VALUES(?, ?, ?, ?, 0)";
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, board.getTitle());
			ps.setString(2, board.getContent());
			ps.setString(3, board.getUserid());
			ps.setString(4, board.getName());
			result = ps.executeUpdate() == 1;
		} catch(SQLException e) {
			e.printStackTrace();
		}
		close(con, ps);
		return result;
	}
	
	public ArrayList<BoardVO> boardListSelect(int start, int count) {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>();
		BoardVO board = null;
		sql = "SELECT a.*, IFNULL(COUNT(b.num),0) commentnum FROM "
				+ "(SELECT p.* FROM (SELECT num FROM board ORDER BY num DESC LIMIT ?, ?) q "
				+ "JOIN board p ON p.num = q.num) a LEFT JOIN comment b ON a.num=b.parentnum "
				+ "GROUP BY a.num ORDER BY a.num DESC";
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, start);
			ps.setInt(2, count);
			rs = ps.executeQuery();
			while(rs.next()) {
				board = new BoardVO();
				board.setNum(rs.getInt("num"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setName(rs.getString("name"));
				board.setRegdate(rs.getString("regdate"));
				board.setViews(rs.getInt("views"));
				board.setCommentnum(rs.getInt("commentnum"));
				list.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);			
		}
		return list.isEmpty() ? null : list;
	}
	
	public BoardVO boardSelect(int num) {
		BoardVO board = null;
//		sql = "SELECT a.*, IFNULL(COUNT(b.num),0) commentnum FROM "
//				+ "(SELECT * FROM BOARD WHERE num = ?) a "
//				+ "LEFT JOIN comment b ON a.num=b.parentnum GROUP BY a.num";
		sql = "SELECT * FROM BOARD WHERE num = ?";
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, num);
			rs = ps.executeQuery();
			if(rs.next()) {
				board = new BoardVO();
				board.setNum(rs.getInt("num"));
				board.setTitle(rs.getString("title"));
				board.setContent(rs.getString("content"));
				board.setUserid(rs.getString("userid"));
				board.setName(rs.getString("name"));
				board.setRegdate(rs.getString("regdate"));
				board.setViews(rs.getInt("views"));
				//board.setCommentnum(rs.getInt("commentnum"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);			
		}
		return board;
	}
	
	public int boardCountSelect() {
		int totalCount = 0;
		sql = "SELECT COUNT(num) FROM BOARD";
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
	
	public String useridSelect(int num) {
		String userid = null;
		sql = "SELECT userid FROM BOARD WHERE num = ?";
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, num);
			rs = ps.executeQuery();
			if(rs.next()) {
				userid = rs.getString("userid");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {			
			close(con, ps, rs);
		}
		return userid;
	}
	
	public boolean viewsUpdate(int num) {
		boolean result = false;
		sql = "UPDATE board SET views = views + 1 WHERE num = ?";
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
	
	public boolean boardUpdate(int num, String title, String content) {
		boolean result = false;
		sql = "UPDATE board SET title = ?, content = ? WHERE num = ?";
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, title);
			ps.setString(2, content);
			ps.setInt(3, num);
			result = ps.executeUpdate() == 1;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, ps);			
		}
		return result;
	}
	
	public boolean boardDelete(int num) {
		boolean result = false;
		sql = "DELETE FROM board WHERE num = ?";
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
