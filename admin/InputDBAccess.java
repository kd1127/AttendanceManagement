package admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import beans.InputBean;

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
				System.out.println("ドライバのロードに失敗しました。");
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
			System.out.println("ドライバのロードに失敗しました。");
			e.getMessage();
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
		finally{
			if(pstmt != null) {
				pstmt.close();
			}
			if(con != null) {
				con.close();
			}
		}
		
		return number;
	}
	
	//	実習２
	public List<String> acquisition(int choice, String courceName, String theDate, String startTime) throws SQLException{
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection con = null;
		String sql = null;
		String sql2 = null;
		List<String> list = new ArrayList<>();
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		}
		catch(ClassNotFoundException e) {
			System.out.println("ドライバのロードに失敗しました。");
			e.getMessage();
		}
		
		try {
			con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/attendance", "root", "");
			
			//	開催日時・開始時刻・終了時刻をDBから抽出
			if(choice == 1) {				
				sql = "select * from cource where the_date > ? order by the_date, start_time, end_time asc";
				
				LocalDate ld3 = LocalDate.now();
				String now = "";
				int year2 = ld3.getYear();
				int month2 = ld3.getMonthValue();
				int day2 = ld3.getDayOfMonth();
				
				now = String.format("%04d", year2);
				now = now.concat("-");
				now = now.concat(String.format("%02d", month2));
				now = now.concat("-");
				now = now.concat(String.format("%02d", day2));
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, now);
				
				int count = 0;
				
				String str1 = "", str2 = "", str3 = "", str4 = ""; //		左から年月日、開始時刻、終了時刻、曜日
				String time = "";									  //	上記の変数を結合した文字列を格納
				String nTime = "";									  //	nowTimeをそれぞれint型に変換して結合
				long nTotal;
				
				//	現在時刻をlong型に変換
				LocalDateTime nowTime = LocalDateTime.now();
				int ntYear = nowTime.getYear();
				int ntMonth = nowTime.getMonthValue();
				int ntDay = nowTime.getDayOfMonth();
				int ntsHour = nowTime.getHour();
				int ntsMinute = nowTime.getMinute();
				
				nTime = String.format("%04d", ntYear);
				nTime = nTime.concat(String.format("%02d", ntMonth));
				nTime = nTime.concat(String.format("%02d", ntDay));
				nTime = nTime.concat(String.format("%02d", ntsHour));
				nTime = nTime.concat(String.format("%02d", ntsMinute));
				nTotal = Long.parseLong(nTime);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					str1 = rs.getString(3);		//	年月日を格納
					str2 = rs.getString(4);		//	開始時刻
					str3 = rs.getString(5);		//	終了時刻
					
					//	String型からLocalTime型に変換
					LocalDate ld = LocalDate.parse(str1);		//	str1の年月日を格納
					
					//	ldを分解して年月日をそれぞれに代入
					int year = ld.getYear();
					int month = ld.getMonthValue();
					int day = ld.getDayOfMonth();
					
					//	int型に変換するためString.formatで記号を取り除く
					str1 = String.format("%04d", year);
					str1 = str1.concat(String.format("%02d", month));
					str1 = str1.concat(String.format("%02d", day));
					String sday = String.format("%02d", day);
					
					//	String型からintに変換
					int iTime = Integer.parseInt(str1);
					
					//	str2から：を取り除き intに変換
					String str = str2;
					str = str2.replace(":", "");
					int sTime = Integer.parseInt(str);
					
					String stotal = "";
					stotal = String.valueOf(iTime) + String.valueOf(sTime);
					long total = Long.parseLong(stotal);
					
					DayOfWeek ld2 = ld.getDayOfWeek();
						
					if(total > nTotal) {
						if(ld2.getValue() == 1) {
							str4 = "日";
						}
						if(ld2.getValue() == 2) {
							str4 = "月";
						}
						if(ld2.getValue() == 3) {
							str4 = "火";
						}
						if(ld2.getValue() == 4) {
							str4 = "水";
						}
						if(ld2.getValue() == 5) {
							str4 = "木";
						}
						if(ld2.getValue() == 6) {
							str4 = "金";
						}
						if(ld2.getValue() == 7) {
							str4 = "土";
						}
						//	2022/10/04 レビュー時に判明したエラー 講座申し込み画面に遷移しなかったのは登録エラーによるためで「日」の表示を二桁にしたら改善した。
						//	年明け後「月」が原因でデータ登録エラーになるから必ず修正
						time = ld.getYear() + "年" + ld.getMonthValue() + "月" + sday + "日(" + str4 + ")" + str2 + "～" + str3;
						list.add(time);		//	テーブルに表示する日付をadd
						
						if(count == 5) {
							break;
						}
						count++;
					}
				}
			}
			else if(choice == 2){		//	講座名をDBから抽出
				sql = "select cource_name from cource where the_date";
				sql2 = "> ? order by the_date, start_time asc";
				
				sql = sql.concat(sql2);
				
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
				pstmt.setString(1, now);
				
				rs = pstmt.executeQuery();
				
				int count = 0;
				
				while(rs.next()) {
					list.add(rs.getString(1));
					
					if(count == 5) {
						break;
					}
					count++;
				}
			}
			else if(choice == 3){		//	講座番号をDBから抽出
				sql = "select cource_no from cource where the_date";
				sql2 = "> ? order by the_date, start_time asc";
				
				sql = sql.concat(sql2);
				
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
				pstmt.setString(1, now);
				
				rs = pstmt.executeQuery();
				
				int count = 1;
				
				while(rs.next()) {
					list.add(rs.getString(1));
					
					if(count == 5) {
						break;
					}
					count++;
				}
			}
			else if(choice == 4){		//	定員数をDBから抽出
				sql = "select capacity from cource where the_date";
				sql2 = "> ? order by the_date, start_time asc";
				
				sql = sql.concat(sql2);
				
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
				pstmt.setString(1, now);
				
				rs = pstmt.executeQuery();
				
				int count = 1;
				
				while(rs.next()) {
					list.add(rs.getString(1));
					
					if(count == 5) {
						break;
					}
					count++;
				}
			}
			else if(choice == 5) {		//	３つの条件に合致する講座番号を抽出
				sql = "select cource_no from cource where cource_name = ?";
				sql2 = " and the_date = ? and start_time = ?";
				
				sql = sql.concat(sql2);
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, courceName);
				pstmt.setString(2, theDate);
				pstmt.setString(3, startTime);
				
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					list.add(rs.getString(1));
				}
			}
		}
		finally {
			if(con != null) {
				con.close();
			}
			
			if(pstmt != null) {
				pstmt.close();
			}
		}
		return list;
	}
	
	//	cource表のcapacityの値を更新
	public void updateCapacity(String courceName, String theDate, String startTime) throws SQLException{
		
		PreparedStatement pstmt = null;
		Connection con = null;
		String sql = "";
		String sql2 = "";
		
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
			
			sql = "update cource set capacity = capacity - 1 ";
			sql2 = "where cource_name = ? and the_date = ? and start_time = ?";
			
			sql = sql.concat(sql2);
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, courceName);
			pstmt.setString(2, theDate);
			pstmt.setString(3, startTime);
			
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
}