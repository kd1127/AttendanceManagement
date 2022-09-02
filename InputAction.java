package admin;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class InputAction extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		//	遷移先のパスを設定
		String NEXT_PAGE = "/JSP/conf.jsp";
		String INPUT_ERROR = "/JSP/input.jsp";
		String BACK_PAGE = "/JSP/menu.jsp";
		
		String path = "";
		
		int capacity = 0;		//capacitycvをint型変換後の変数		//	入力エラーチェック時に使用する変数 conversionからcvを取った
		String number = ""; 										//	同上
		
		InputDBAccess idb = new InputDBAccess();
		
		HttpSession session = request.getSession();
		
		request.setCharacterEncoding("UTF-8");
		
		if(request.getParameter("back") != null) {	//	menu.jspで「戻る」が押下していたら前画面に戻る
			path = BACK_PAGE;
		}
		else {			
			String anumber = request.getParameter("anumber");
			String aname = request.getParameter("aname");
			String ayear = request.getParameter("ayear");
			String amonth = request.getParameter("amonth");
			String aday = request.getParameter("aday");
			String sthour = request.getParameter("sthour");
			String stminute = request.getParameter("stminute");
			String endhour = request.getParameter("endhour");
			String endminute = request.getParameter("endminute");
			String capacitycv = request.getParameter("capacity");
			
			List<String> eMessage = new ArrayList<>();
			
			//	値の重複の可否を調べる処理
			try {
				number = idb.errorCheckDbAccess(anumber);
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
			
			//	一応メモ　同じ文字列なのにtrueにならない
			if(number.equals(anumber)) {
				eMessage.add("「講座番号」が重複しています");
			}
			
			//	入力エラーチェック
			if(anumber.equals("") || anumber == null) {
				eMessage.add("「講座番号」は必須項目です");
			}
			
			if(anumber.length() >= 5) {
				eMessage.add("「講座番号」は４文字以内で入力してください");
			}
			
			if(aname.equals("") || aname == null) {
				eMessage.add("「講座名」は必須項目です");
			}
			
			if(aname.length() >= 41) {
				eMessage.add("「講座番号」は４０文字以内で入力してください");
			}
			
			if(ayear.equals("")|| ayear.equals("ayear")) {
				eMessage.add("「講座開催日（年）」は必須項目です");
				ayear = "0";
			}
			
			if(amonth.equals("") || amonth.equals("amonth")) {
				eMessage.add("「講座開催日（月）」は必須項目です");
				amonth = "0";
			}
			
			if(aday.equals("") || aday.equals("aday")) {
				eMessage.add("「講座開催日（日）」は必須項目です");
				aday = "0";
			}
			
			if(sthour.equals("") || sthour.equals("sthour")) {
				eMessage.add("「開始時刻（時）」は必須項目です");
				sthour = "0";
			}
			
			if(stminute.equals("") || stminute.equals("stminute")) {
				eMessage.add("「開始時刻（分）」は必須項目です");
				stminute = "0";
			}
			
			if(endhour.equals("") || endhour.equals("endhour")) {
				eMessage.add("「終了時刻（時）」は必須項目です");
				endhour = "0";
			}
			
			if(endminute.equals("") || endminute.equals("endminute")) {
				eMessage.add("「終了時刻（分）」は必須項目です");
				endminute = "0";
			}
			
			int capalen = 0;
			
			if(capacitycv.equals("") || capacitycv == null) {
				eMessage.add("「定員」は必須項目です");
				capacity = 0;
			}
			else {		//	String型をint型に変換する　数字以外がcapacitycvに代入されていたらNumberFormatException発生
				try {
					capalen = Integer.parseInt(capacitycv);
				}
				catch(NumberFormatException e) {
					capalen = 0;
					eMessage.add("「定員」は数字で入力してください");
				}
			}
			
			if(capalen <= 0 || capalen >= 51) {
				eMessage.add("「定員」は１名以上、５０名以下で入力してください");
			}
			else {
				Integer i = Integer.valueOf(capacitycv);
				capacity = i.intValue();
			}
			
			if(!sthour.equals("0") && !endhour.equals("0") ) {
				
				//	開始時刻より前の時刻が入力されたか調べる処理
				String startTime = sthour.concat(stminute);
				String endTime = endhour.concat(endminute);
				
				if(startTime.length() == 3) {
					startTime = startTime.concat("0");
				}
				
				if(endTime.length() == 3) {
					endTime = endTime.concat("0");
				}
				
				int sTime = Integer.parseInt(startTime);
				int eTime = Integer.parseInt(endTime);
				
				if(eTime < sTime) {
					eMessage.add("「終了時刻」は「開始時刻」よりも後の時刻を入力してください");
				}
			}
	
			request.setAttribute("eMessage", eMessage);
			
			InputBean bn = new InputBean();
			bn.setAnumber(anumber);
			bn.setAname(aname);
			bn.setAyear(ayear);
			bn.setAmonth(amonth);
			bn.setAday(aday);
			bn.setSthour(sthour);
			bn.setStminute(stminute);
			bn.setEndhour(endhour);
			bn.setEndminute(endminute);
			bn.setCapacity(capacity);
			
			session.setAttribute("bn", bn);
			
			if(eMessage.isEmpty() == true) {
				path = NEXT_PAGE;
			}
			else {
				path = INPUT_ERROR;
			}
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
}
