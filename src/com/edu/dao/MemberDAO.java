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

import com.edu.vo.MemberVO;

public class MemberDAO {
	// 싱글톤 패턴
	private static MemberDAO dao = new MemberDAO();
	private MemberDAO() {}
	public static MemberDAO getInstance() {
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
//		DataSource ds = null;
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
	
	public boolean memberInsert(MemberVO member) {
		boolean result = false;
		sql = "INSERT INTO MEMBER(userid, userpw, name, mail) VALUES(?, ?, ?, ?)";
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, member.getUserid());
			ps.setString(2, member.getUserpw());
			ps.setString(3, member.getName());
			ps.setString(4, member.getMail());
			result = ps.executeUpdate() == 1;
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {			
			close(con, ps);
		}
		return result;
	}
	
	public MemberVO memberSelect(String id, String pw) {
		MemberVO member = null;		
		sql = "SELECT * FROM MEMBER WHERE userid = ? AND userpw = ?";
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pw);
			rs = ps.executeQuery();
			if(rs.next()) {
				member = new MemberVO();
				member.setUserid(rs.getString("userid"));
				member.setUserpw(rs.getString("userpw"));
				member.setName(rs.getString("name"));
				member.setMail(rs.getString("mail"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {			
			close(con, ps, rs);
		}
		return member;
	}
	
	public boolean memberSelectByUserid(String userid) {
		boolean result = false;
		sql = "SELECT id FROM MEMBER WHERE userid = ?";
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, userid);
			rs = ps.executeQuery();
			if(rs.next())
				result = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {			
			close(con, ps, rs);
		}
		return result;
	}
	
	public ArrayList<String> useridSelectByMail(String mail) {
		ArrayList<String> list = new ArrayList<>();
		sql = "SELECT userid FROM MEMBER WHERE mail = ?";
		
		try {
			con = ds.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, mail);
			rs = ps.executeQuery();
			if(rs.next())
				list.add(rs.getString("userid"));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);			
		}
		return list.isEmpty() ? null : list;
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
