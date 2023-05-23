package admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import db.SearchInfoDBAccess;

public class ConfirmAction extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String BACK_PAGE = "../JSP/courceFix.jsp";
		String NEXT_PAGE = "../JSP/confirmed.jsp";
		
		String path = "";
		String the_date = "";				//	開催日
		String start_time = "";			//	開始時刻
		String end_time = "";				//	終了時刻
		
		HttpSession session = request.getSession();
		SearchInfoDBAccess sia = new SearchInfoDBAccess();
		
		String cource_no = (String)session.getAttribute("cource_no");
		String cource_name = (String)session.getAttribute("cource_name");
		String ayear = (String)session.getAttribute("ayear");
		String amonth = (String)session.getAttribute("amonth");
		String aday = (String)session.getAttribute("aday");
		String sthour = (String)session.getAttribute("sthour");
		String stminute = (String)session.getAttribute("stminute");
		String endhour = (String)session.getAttribute("endhour");
		String endminute = (String)session.getAttribute("endminute");
		String capacity = (String)session.getAttribute("capacity");
		
		request.setCharacterEncoding("UTF-8");
		
		the_date = ayear;
		the_date = the_date.concat("-");
		the_date = the_date.concat(amonth);
		the_date = the_date.concat("-");
		the_date = the_date.concat(aday);
		
		start_time = sthour;
		start_time = start_time.concat(":");
		start_time = start_time.concat(stminute);
		
		end_time = endhour;
		end_time = end_time.concat(":");
		end_time = end_time.concat(endminute);
		
		if(request.getParameter("back") != null) {
			path = BACK_PAGE;
		}
		else {
			path = NEXT_PAGE;
			try {
				sia.updateInfoDBAccess(cource_no, cource_name, the_date, start_time, end_time, capacity);
				session.removeAttribute("cource_no");
				session.removeAttribute("cource_name");
				session.removeAttribute("year");
				session.removeAttribute("month");
				session.removeAttribute("day");
				session.removeAttribute("shour");
				session.removeAttribute("sminute");
				session.removeAttribute("ehour");
				session.removeAttribute("eminute");
				session.removeAttribute("capacity");
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
}
