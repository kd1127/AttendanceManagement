package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import beans.SearchBean;
import result.RegistCourseEntity;

public class SearchInfoDBAccess {

	public List<RegistCourseEntity> searchInfo(SearchBean bn4, String cource_no, String cource_name, String event_date, String start_time, String end_time) throws SQLException{
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		Connection con = null;
		String sql = null;
		String sql2 = null;
		
		boolean flag1 = false, flag2 = false;
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		}
		catch(ClassNotFoundException e) {
			System.out.println("ドライバのロードに失敗しました。");
		}
		
		try {
			con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/attendance", "root", "");
			con.setAutoCommit(false);
			
			StringBuilder whereStr = new StringBuilder();
			
			// PreparedStatement用パラメータ
			List<BindParameter> bindParameters = new ArrayList<BindParameter>();

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
			
			//	講座番号
			if(!bn4.getCource_no().isEmpty()) {
				whereStr.append(makeWhereSentence(whereStr, "cource_no like ?"));
				bindParameters.add(new BindParameter(bn4.getCource_no(), java.sql.Types.VARCHAR));
				flag1 = true;
			}
			//	講座名
			if(!bn4.getCource_name().isEmpty()) {
				whereStr.append(makeWhereSentence(whereStr, "cource_name like ?"));
				bindParameters.add(new BindParameter(bn4.getCource_name(), java.sql.Types.VARCHAR));
				flag2 = true;
			}
			//	開催日年
			if(!bn4.getSyear().isEmpty()) {
				whereStr.append(makeWhereSentence(whereStr, "DATE_FORMAT(the_date, '%Y') = ?"));
				bindParameters.add(new BindParameter(bn4.getSyear(), java.sql.Types.INTEGER));
			}
			//	開催日月
			if(!bn4.getSmonth().isEmpty()) {
				whereStr.append(makeWhereSentence(whereStr, "DATE_FORMAT(the_date, '%m') = ?"));
				bindParameters.add(new BindParameter(bn4.getSmonth(), java.sql.Types.INTEGER));
			}
			//	開催日日
			if(!bn4.getSday().isEmpty()) {
				whereStr.append(makeWhereSentence(whereStr, "DATE_FORMAT(the_date, '%d') = ?"));
				bindParameters.add(new BindParameter(bn4.getSday(), java.sql.Types.INTEGER));
			}
			// 開始時刻
			if (!bn4.getSshour().isEmpty()) {
				whereStr.append(makeWhereSentence(whereStr, "start_time=?"));
				bindParameters.add(new BindParameter(start_time, java.sql.Types.VARCHAR));
			}
			// 終了時刻
			if (!bn4.getSehour().isEmpty()) {
				whereStr.append(makeWhereSentence(whereStr, "end_time=?"));
				bindParameters.add(new BindParameter(end_time,	java.sql.Types.VARCHAR));
			}
			//	状態が終了
			if(bn4.getEnd_condition() != null) {
				whereStr.append(makeWhereSentence(whereStr, "the_date < " + "'" + now + "'"));
			}
			//	状態が開催中
			if(bn4.getIn_session() != null) {
				whereStr.append(makeWhereSentence(whereStr, "the_date = " + "'" + now + "'"));
			}
			//	状態が開催予定
			if(bn4.getDate_held() != null) {
				whereStr.append(makeWhereSentence(whereStr, "the_date > " + "'" + now + "'"));
			}
			
			sql = "select cource_no, cource_name, the_date, start_time, end_time, capacity from cource ";
			sql2 = ""+ whereStr.toString() + " order by cource_no";
			
			sql = sql.concat(sql2.toString());
			System.out.println(sql);
			
			pstmt = con.prepareStatement(sql.toString());
			int i = 1;
			for (BindParameter param : bindParameters) {
				
				//	講座番号
				if(flag1 == true) {
					pstmt.setString(i++, "%" + bn4.getCource_no() + "%");
					flag1 = false;
				}
				//	講座名
				else if(flag2 == true) {
					pstmt.setString(i++, "%" + bn4.getCource_name() + "%");
					flag2 = false;
				}
				else {
					pstmt.setObject(i++, param.bindObject, param.sqlType);
				}
			}
			
			rs = pstmt.executeQuery();
			
			List<RegistCourseEntity> resultList = new ArrayList<RegistCourseEntity>();
		
			while (rs.next()) {
				String date = "";
				date = rs.getString(3);
				
				int year2 = Integer.parseInt(rs.getString(3).substring(0, 4));
				int month2 = Integer.parseInt(rs.getString(3).substring(5, 7));
				int day2 = Integer.parseInt(rs.getString(3).substring(8, 10));
				
				LocalDate ld2 = LocalDate.of(year2, month2, day2);
				DayOfWeek dw2 = ld2.getDayOfWeek();
				
				switch(dw2) {
				case SUNDAY:
					date = date.concat("(日)");
					break;
				case MONDAY:
					date = date.concat("(月)");
					break;
				case TUESDAY:
					date = date.concat("(火)");
					break;
				case WEDNESDAY:
					date = date.concat("(水)");
					break;
				case THURSDAY:
					date = date.concat("(木)");
					break;
				case FRIDAY:
					date = date.concat("(金)");
					break;
				case SATURDAY:
					date = date.concat("(土)");
				}
				
				date = date.concat(rs.getString(4));
				date = date.concat("-");
				date = date.concat(rs.getString(5));
				
				RegistCourseEntity course = new RegistCourseEntity();
				course.setCourse_no(rs.getString(1));
				course.setCourse_name(rs.getString(2));
				course.setThe_Date(date);
				course.setStart_Time(rs.getString(4));
				course.setEnd_Time(rs.getString(5));
				course.setCapacity(rs.getInt(6));
				resultList.add(course);
			}
			con.commit();
			return resultList;
		}
		finally {
			if(con != null) {
				con.close();
			}
			if(pstmt != null) {
				pstmt = null;
			}
			if(rs != null) {
				rs = null;
			}
		}
	}
	
	/**
	 * WHERE句の条件文を生成する 既にWHERE句があれば "AND"を付与する
	 *
	 * @param builder WHERE句
	 * @param str     追加する条件文
	 * @return 生成された条件文
	 */
	private String makeWhereSentence(StringBuilder builder, String str) {
		if (builder.length() != 0) {
			return " and " + str;
		} else {
			return "where " + str;
		}
	}
	
	/**
	 * PreparedStatement用パラメータクラス 値と値の型を管理する
	 */
	public class BindParameter {
		private int sqlType; // java.sql.Typesの各価を設定する
		private Object bindObject;

		public BindParameter(Object bindObject, int sqlType) {
			this.sqlType = sqlType;
			this.bindObject = bindObject;
		}
	}

	
	//	DB更新メソッド
	public void updateInfoDBAccess(String cource_no, String cource_name, String the_date, String start_time, String end_time, String capacity) throws SQLException{
		
		Connection con = null;
		PreparedStatement pst = null;
		String sql = "";
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		}
		catch(ClassNotFoundException e) {
			System.out.println("ドライバのロードに失敗しました。");
		}
		
		try {
			con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/attendance", "root", "");
			con.setAutoCommit(false);
			
			sql = "update cource set cource_name = ?, the_date = ?, start_time = ?, end_time = ?, capacity = ? where cource_no = ?";
			pst = con.prepareStatement(sql);
			
			pst.setString(1, cource_name);
			pst.setString(2, the_date);
			pst.setString(3, start_time);
			pst.setString(4, end_time);
			pst.setString(5, capacity);
			pst.setString(6, cource_no);
			
			pst.executeUpdate();
			
			con.commit();
		}
		finally {
			if(con != null) {
				con.close();
			}
			if(pst != null) {
				pst.close();
			}
		}
	}
	
	//	削除メソッド
	public void deleteInfoDBAccess(int operation, String cource_no) throws SQLException{
		PreparedStatement pst = null;
		Connection con = null;
		String sql1 = null;
		String sql2 = null;
		
		try {
			Class.forName("org.mariadb.jdbc.Driver");
		}
		catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			con =DriverManager.getConnection("jdbc:mariadb://localhost:3306/attendance", "root", "");
			con.setAutoCommit(false);
			
			if(operation == 1) {
				sql1 = "delete from cource where cource_no = ?";
				pst = con.prepareStatement(sql1);
				pst.setString(1, cource_no);
				pst.executeUpdate();
			}
			else {
				sql2 = "delete from cource_apply where cource_no = ?";
				pst = con.prepareStatement(sql2);
				pst.setString(1, cource_no);
				pst.executeUpdate();
			}
			con.commit();
		}
		finally{
			if(pst != null) {
				pst.close();
			}
			if(con != null) {
				con.close();
			}
		}
	}
}