package admin;

import java.io.IOException;
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

public class ConfirmedAction extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String ADMIN = "../JSP/menu.jsp";
		String SEARCH = "../JSP/search.jsp";
		String LIST = "../JSP/courceList.jsp";
		String LOGOUT = "../total/login.jsp";
		
		String path = "";
		
		HttpSession session = request.getSession();
		try {
			SearchInfoDBAccess sidb = new SearchInfoDBAccess();
			
			List<RegistCourseEntity> courseList = (List<RegistCourseEntity>)session.getAttribute("courseList");
			SearchBean bn4 = (SearchBean)session.getAttribute("bn4");
			
			String sthour = bn4.getSshour();
			String stminute = bn4.getSsminute();
			String endhour = bn4.getSehour();
			String endminute = bn4.getSeminute();
			
			String the_date = "";
			String start_time = "";
			String end_time = "";
			
			request.setCharacterEncoding("UTF-8");
			
			if(sthour != null && stminute != null) {
				start_time = sthour;
				start_time = start_time.concat(":");
				start_time = start_time.concat(stminute);
			}
			
			if(endhour != null) {
				end_time = endhour;
				end_time = end_time.concat(":");
				end_time = end_time.concat(endminute);
			}
			
			if(request.getParameter("admin") != null) {
				path = ADMIN;
				session.removeAttribute("bn4");
			}
			else if(request.getParameter("search") != null) {
				path = SEARCH;
				session.removeAttribute("bn4");
			}
			else if(request.getParameter("list") != null) {
				path = LIST;
				courseList = sidb.searchInfo(bn4, bn4.getCource_no(), bn4.getCource_name(), the_date, start_time, end_time);
				
				session.setAttribute("courseList", courseList);
				session.setAttribute("bn4", bn4);
			}
			else {
				path = LOGOUT;
				session.removeAttribute("bn5");
				session.removeAttribute("bn4");
				session.removeAttribute("bn3");
			}
			
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} 
	}
}
