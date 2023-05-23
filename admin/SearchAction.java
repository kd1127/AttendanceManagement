package admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.SearchBean;
import db.SearchInfoDBAccess;
import result.RegistCourseEntity;

public class SearchAction extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String SEARCH = "../JSP/courceList.jsp";	//	検索ボタンを押下すると講座一覧画面へ
		String ADMIN ="../JSP/menu.jsp";			//	講座管理画面へ
		String ERROR = "../JSP/search.jsp";			//	エラーの場合自画面遷移
		
		String path = "";
		String userId = (String)request.getAttribute("userId");
		
		String event_date = "";		//	syear smonth sdayに記号を加えた変数
		String start_time = "";		//	sshour ssminuteを時：分の形式に変換した後に使用
		String end_time = "";			//	sehour seminute 
		String smonth2 = "";
		String sday2 = "";				//	一桁場合、先頭に０を付ける専用の変数
		
		List<RegistCourseEntity> courceList = new ArrayList<>();		//	検索処理で得たデータを格納
		List<String> eMessage4 = new ArrayList<>();					//	エラーメッセージ
		
		HttpSession session = request.getSession();
		SearchBean bn4 = new SearchBean();
		SearchErrorCheck sec = new SearchErrorCheck();
		SearchInfoDBAccess sidb = new SearchInfoDBAccess();
				
		request.setCharacterEncoding("UTF-8");
		
		String cource_no = request.getParameter("cource_no");
		String cource_name= request.getParameter("cource_name");
		String syear = request.getParameter("syear");
		String smonth = request.getParameter("smonth");
		String sday = request.getParameter("sday");
		String sshour = request.getParameter("sshour");
		String ssminute = request.getParameter("ssminute");
		String sehour = request.getParameter("sehour");
		String seminute = request.getParameter("seminute");
		String end_condition = request.getParameter("end_condition");
		String in_session = request.getParameter("in_session");
		String date_held = request.getParameter("date_held");
		String no_condition = request.getParameter("no_condition");
		
		//	DBに渡すため記号を加えた形式に変換
		if(!syear.equals("") && !smonth.equals("") && !sday.equals("")) {
			event_date = syear.concat("-");
			event_date = event_date.concat(smonth);
			event_date = event_date.concat("-");
			event_date = event_date.concat(sday);
		}
	
		if(!sshour.equals("") &&!ssminute.equals("")) {
			start_time = sshour.concat(":");
			start_time = start_time.concat(ssminute);
		}
		
		
		if(!sehour.equals("") &&  !seminute.equals("")) {
			end_time = sehour.concat(":");
			end_time = end_time.concat(seminute);
		}
	
		//	月の値が一桁の場合、０をくっつける
		if(!smonth.equals("")) {
			int month = Integer.parseInt(smonth);
			
			if(month >= 1 && month <= 9) {
				smonth2 = "0".concat(smonth);
				bn4.setSmonth(smonth2);
			}
			else {
				bn4.setSmonth(smonth);
			}
		}
		//	日の値が一桁の場合、０をくっつける
		if(!sday.equals("")) {
			int day = Integer.parseInt(sday);	
			
			if(day >= 1 && day <= 9) {
				sday2 = "0".concat(sday);
				bn4.setSday(sday2);
			}
			else {
				bn4.setSday(sday);
			}
		}
		
		bn4.setCource_no(cource_no);
		bn4.setCource_name(cource_name);
		bn4.setSyear(syear);
		bn4.setSshour(sshour);
		bn4.setSsminute(ssminute);
		bn4.setSehour(sehour);
		bn4.setSeminute(seminute);
		bn4.setEnd_condition(end_condition);
		bn4.setIn_session(in_session);
		bn4.setDate_held(date_held);
		bn4.setNo_condition(no_condition);
		
		if(syear == "" || syear.equals("")) {
			syear = "0";
		}
		
		if(smonth == "" || smonth.equals("")) {
			smonth = "0";
		}
		
		if(sday == "" || sday.equals("")) {
			sday = "0";
		}
		
		if(sshour == "" || sshour.equals("")) {
			sshour = "0";
		}
		
		if(ssminute == "" || ssminute.equals("")) {
			ssminute = "0";
		}
		
		if(sehour == "" || sehour.equals("")) {
			sehour = "0";
		}
		
		if(seminute == "" || seminute.equals("")) {
			seminute = "0";
		}
		
		eMessage4 = sec.errorCheck(sshour, ssminute, sehour, seminute);
		
		session.setAttribute("syear", syear);
		session.setAttribute("smonth", smonth);
		session.setAttribute("sday", sday);
		session.setAttribute("sshour", sshour);
		session.setAttribute("ssminute", ssminute);
		session.setAttribute("sehour", sehour);
		session.setAttribute("seminute", seminute);
		
		try {
			if(request.getParameter("admin") != null) {
				path = ADMIN;
				request.setAttribute("userId", userId);
				session.removeAttribute("bn4");
			}
			else if(eMessage4.isEmpty() == false) {
				path = ERROR;
				session.setAttribute("bn4", bn4);
				request.setAttribute("eMessage4", eMessage4);
			}
			else {
				try {
					courceList = sidb.searchInfo(bn4, cource_no, cource_name, event_date, start_time, end_time);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				
				path = SEARCH;
				session.setAttribute("courseList", courceList);
				session.setAttribute("bn4", bn4);
			}
			
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} 
	}
}