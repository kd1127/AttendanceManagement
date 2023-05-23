package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import admin.InputDBAccess;
import beans.ApplicationBean;

public class InsertApplicationDBAccesss {
	
	//	データを登録する application
	public void insertDBAccess(ApplicationBean bn) throws SQLException{
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		}
		catch(ClassNotFoundException e) {
			System.out.println("ドライバのロードに失敗しました。");
			e.getMessage();
		}
		
		try {
			con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/attendance", "root", "");
			con.setAutoCommit(false);
			
			sql = "insert into application(name, furigana, mail, gender, birthday, tel, remarks) values(?, ?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, bn.getName());
			pstmt.setString(2, bn.getFurigana());
			pstmt.setString(3, bn.getMail());
			pstmt.setInt(4, bn.getGender());
			pstmt.setString(5, bn.insertBirthday());
			pstmt.setString(6, bn.getTel());
			pstmt.setString(7, bn.getRemarks());
			
			pstmt.executeUpdate();
			
			con.commit();
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
	
	//	データを登録する cource_apply
	public void insertApplyDBAccess(ApplicationBean bn, String courceName, String theDate, String startTime) throws SQLException{
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		int idCount = 0;
		
		InputDBAccess idb = new InputDBAccess();
		List<String> list = new ArrayList<String>();
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		}
		catch(ClassNotFoundException e) {
			System.out.println("ドライバのロードに失敗しました。");
		}
		
		try {
			con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/attendance", "root", "");
			con.setAutoCommit(false);
			
			idCount = bn.getId();			//	累積idの値を取得
			list = idb.acquisition(5, courceName, theDate, startTime);		//	講座番号を取得
			
			sql = "insert into cource_apply(id_application, cource_no, inp_date) values(?, ?, ?) ";	
			
			pstmt = con.prepareStatement(sql);
			
			LocalDate ld = LocalDate.now();
			String now = "";
			int year = ld.getYear();
			int month = ld.getMonthValue();
			int day = ld.getDayOfMonth();
			
			now = String.format("%04d", year);
			now = now.concat("-");
			now = now.concat(String.format("%02d", month));
			now = now.concat("-");
			now = now.concat(String.format("%02d", day));
			pstmt.setString(3, now);
			pstmt.setInt(1, idCount);
			pstmt.setString(2, list.get(0));
			
			pstmt.executeUpdate();
			
			con.commit();
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
	
	//	累積idの数を取得する
	public int inputIdIncrement(ApplicationBean bn) throws SQLException{
		
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection con = null;
		String sql = null;
		
		int count = 0;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		}
		catch(ClassNotFoundException e) {
			System.out.println("ドライバのロードに失敗しました。");
		}
		
		try {
			con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/attendance", "root", "");
			con.setAutoCommit(false);

			sql = "select count(id) from application";

			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				count = rs.getInt(1);
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
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
		return count;
	}
	
	//	その講座の申込者数を得る
	public List<Integer> getNumApplicantsCource(String cource_name) throws SQLException{
		
		ResultSet rs = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = null;
		List<Integer> applicants = new ArrayList<>();
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		}
		catch(ClassNotFoundException e) {
			System.out.println("ドライバのロードに失敗しました。");
		}
		
		try {
			con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/attendance", "root", "");
			con.setAutoCommit(false);

			sql = "select count(*) from cource_apply where cource_no = ?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cource_name);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				applicants.add(rs.getInt(1));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
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
		return applicants;
	}
}
