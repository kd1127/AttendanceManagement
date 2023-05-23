package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class UserInfoAcquisition {
	
	//	ログイン画面で入力されたID・パスワードがDBに存在しているか調べるメソッド
	public int memberInfoSearch(String userId, String pass) throws SQLException{
		
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection con = null;
		String sql = null;
		int authority = 1;
				
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("ドライバのロードに失敗しました。");
		}
		
		try {
			con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/attendance", "root", "");
			con.setAutoCommit(false);
			
			sql = "select user_id, passwd, authority from cource_user where user_id = ? and passwd = ?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			pstmt.setString(2, pass);
			
			rs = pstmt.executeQuery();
			
			if(rs.next() == true) {
				rs.beforeFirst();
				while(rs.next()) {
					if(userId.equals(rs.getString(1)) && pass.equals(rs.getString(2))) {
						authority = rs.getInt(3);
					}
					else {
						authority = 3;
					}
				}
			}
			else {
				authority = 3;
			}
			con.commit();
		}
		finally {
			if(con != null) {
				con.close();
			}
			
			if(pstmt != null) {
				pstmt.close();
			}
			
			if(rs != null) {
				rs.close();
			}
		}
		return authority;
	}
	
	public boolean userIDInvestigate(String userId) throws SQLException{
		
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection con = null;
		String sql = null;
		boolean userIdCheck = false;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("ドライバのロードに失敗しました。");
		}
		
		try {
			con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/attendance", "root", "");
			con.setAutoCommit(false);
			
			sql = "select user_id from cource_user";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				if(rs.getString(1).equals(userId)) {
					userIdCheck = true;
				}
			}
			con.commit();
		}
		finally {
			if(rs != null) {
				rs.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}
			if(con != null) {
				con.close();
			}
		}
		
		return userIdCheck;
	}
	
	public void insertUserData(String user_id, String password, int authority) throws SQLException{
		
		PreparedStatement pstmt = null;
		Connection con = null;
		String sql = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		}
		catch(ClassNotFoundException e) {
			System.out.println("ドライバのロードに失敗しました。");
		}
		
		try {
			con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/attendance", "root", "");
			
			sql = "insert into cource_user(user_id, passwd, authority, inp_date) values(?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			
			LocalDate local = LocalDate.now();
			int year = local.getYear();
			int month = local.getMonthValue();
			int day = local.getDayOfMonth();
			String nowTime = new String();
			
			nowTime = String.format("%04d", year);
			nowTime = nowTime.concat("-");
			nowTime += String.format("%02d", month);
			nowTime = nowTime.concat("-");
			nowTime += String.format("%02d", day);
			
			pstmt.setString(1, user_id);
			pstmt.setString(2, password);
			pstmt.setInt(3, authority);
			pstmt.setString(4, nowTime);
			
			pstmt.executeUpdate();
		}
		finally {
			if(con != null) {
				con.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}
		}
	}
}
