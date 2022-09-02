package admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InputDBAccess {

	//	データベース登録処理
	public void dataRegisterInfo(InputBean bn) throws SQLException {
		
		Connection con = null;
		PreparedStatement pstmt = null;
		String strSql = null;
		
		try {
			try {
				Class.forName("org.mariadb.jdbc.Driver");
			}
			catch(ClassNotFoundException e) {
				e.getMessage();
			}
			
			con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/attendance", "root", "");
			con.setAutoCommit(false);
			
			strSql = "INSERT INTO cource(cource_no, cource_name, the_Date, start_Time, end_Time, capacity) VALUES(?, ?, ?, ?, ?, ?)";
			pstmt = con.prepareStatement(strSql);
			
			pstmt.setString(1, bn.getAnumber());
			pstmt.setString(2, bn.getAname());
			pstmt.setString(3, bn.getInsertTime());
			pstmt.setString(4, bn.getStarttime());
			pstmt.setString(5, bn.getEndTime());
			pstmt.setInt(6, bn.getCapacity());
			
			pstmt.executeUpdate();
			
			con.commit();
		}
		catch(Exception e) {
			
			if(con != null) {
				con.rollback();
			}
			
			throw e;
		}
		finally{
			if(pstmt != null) {
				pstmt.close();
			}
			if(con != null) {
				con.close();
			}
		}
	}
	
	//	データベース検索処理
	public List<InputBean> dataSearchInfo() throws SQLException{
		
		PreparedStatement pstmt = null;
		Connection con = null;
		ResultSet rs = null;
		String strSql = null;
		List<InputBean> list = new ArrayList<InputBean>();
		InputBean bn = new InputBean();
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		}
		catch(Exception e) {
			System.out.println("ドライバのロードに失敗しました。");
			e.getMessage();
		}
		
		try {
			con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/attendance", "root", "");
			
			strSql = "SELECT * FROM COURCE";
			
			pstmt = con.prepareStatement(strSql);
			rs = pstmt.executeQuery(); 
			
			while(rs.next()) {
				bn.setAnumber(rs.getString(1));
				bn.setAname(rs.getString(2));
				
				String year = rs.getString(3).substring(0, 3);
				bn.setAyear(year);
				String month = rs.getString(3).substring(4, 5);
				bn.setAmonth(month);
				String day = rs.getString(3).substring(6, 7);
				bn.setAday(day);
				
				String sthour = rs.getString(4).substring(0, 1);
				bn.setSthour(sthour);
				String stminute = rs.getString(4).substring(2, 3);
				bn.setStminute(stminute);
				
				String endhour = rs.getString(5).substring(0, 1);
				bn.setEndhour(endhour);
				String endminute = rs.getString(5).substring(2, 3);
				bn.setEndminute(endminute);
				
				bn.setCapacity(rs.getInt(6));
				
				list.add(bn);
			}
		}
		catch(SQLException e) {
			e.getMessage();
		}
		finally{
			if(pstmt != null) {
				pstmt.close();
			}
			if(con != null) {
				con.close();
			}
		}
		
		return list;
	}
	
	//	入力エラーチェック用メソッド
	public String errorCheckDbAccess(String anumber)  throws SQLException{
		
		//	引数は検索したい値
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection con = null;
		String sql = null;
		String number = "";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/attendance", "root", "");
			
			sql = "SELECT cource_no FROM cource";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, anumber);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				if(rs.getString("cource_no").equals(anumber)) {
					number = rs.getString("cource_no");
				}
			}
			
			if(number.equals("") || number == null) {
				number = "Z999";						//	numberが空白であればZ999を割り当てる
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return number;
	}
}
