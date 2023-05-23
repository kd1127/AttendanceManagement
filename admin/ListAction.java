package admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.InputBean;
import beans.SearchBean;
import result.RegistCourseEntity;

public class ListAction extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		RegistCourseEntity course;
		
		String FIX = "../JSP/courceFix.jsp";
		String DELETE = "../JSP/courceDelete.jsp";
		String SEARCH = "../JSP/search.jsp";
		
		String path = "";
		String fix = "";				//	講座一覧画面で押下された修正ステータス 修正
		String remove = "";			//	" 削除
				
		String cource_no = "";			//	講座番号を格納
		String cource_name = "";
		String year = "";
		String month = "";
		String day = "";
		String shour = "";
		String sminute = "";
		String ehour = "";
		String eminute = "";
		String capacity = "";
		
		try {	
			HttpSession session = request.getSession();
			InputBean bn6 = new InputBean();
			SearchBean bn4 = (SearchBean)session.getAttribute("bn4");
			
			request.setCharacterEncoding("UTF-8");
			
			List<RegistCourseEntity> courseList = (List<RegistCourseEntity>)session.getAttribute("courseList");
			
			int i = 0;
			
			if(session.getAttribute("i") == null){
				i = 0;
			}
			else {
				i = (int)session.getAttribute("i");			//	講座一覧画面で表示された最大行数
			}
			
			if(i == 0) {
				path = SEARCH;
			}
			
			for(int j=0; j<i; j++) {
				fix = request.getParameter("fix" + j);
				remove = request.getParameter("remove" + j);
				if(fix != null){
					path = FIX;
					course = courseList.get(j);
					cource_no = course.getCourse_no();
					cource_name = course.getCourse_name();
					year = course.getThe_Date().substring(0, 4);
					month = course.getThe_Date().substring(5, 7);
					day = course.getThe_Date().substring(8, 10);
					shour = course.getStart_time().substring(0, 2);
					sminute = course.getStart_time().substring(3, 5);
					ehour = course.getEnd_Time().substring(0, 2);
					eminute = course.getEnd_Time().substring(3, 5);
					capacity = String.valueOf(course.getCapacity());

					if(month.startsWith("0")) {
						month = course.getThe_Date().substring(5, 7);
					}
					
					session.setAttribute("cource_no", cource_no);
					session.setAttribute("cource_name", cource_name);
					session.setAttribute("ayear", year);
					session.setAttribute("amonth", month);
					session.setAttribute("aday", day);
					session.setAttribute("sthour", shour);
					session.setAttribute("stminute", sminute);
					session.setAttribute("endhour", ehour);
					session.setAttribute("endminute", eminute);
					session.setAttribute("capacity", capacity);
					
					break;
				}
				if(remove != null) {
					path = DELETE;
					
					course = courseList.get(j);
					cource_no = course.getCourse_no();
					cource_name = course.getCourse_name();
					year = course.getThe_Date().substring(0, 4);
					month = course.getThe_Date().substring(5, 7);
					day = course.getThe_Date().substring(8, 10);
					shour = course.getStart_time().substring(0, 2);
					sminute = course.getStart_time().substring(3, 5);
					ehour = course.getEnd_Time().substring(0, 2);
					eminute = course.getEnd_Time().substring(3, 5);
					capacity = String.valueOf(course.getCapacity());

					if(month.startsWith("0")) {
						month = course.getThe_Date().substring(5, 7);
					}
					
					int icapacity;
					icapacity = Integer.parseInt(capacity);
					
					bn6.setAnumber(cource_no);
					bn6.setAname(cource_name);
					bn6.setAyear(year);
					bn6.setAmonth(month);
					bn6.setAday(day);
					bn6.setSthour(shour);
					bn6.setStminute(sminute);
					bn6.setEndhour(ehour);
					bn6.setEndminute(eminute);
					bn6.setCapacity(icapacity);
					session.setAttribute("bn6", bn6);
					break;
				}
				if(request.getParameter("search") != null) {
					path = SEARCH;
					bn4 = null;
					session.setAttribute("bn4", bn4);
				}
			}
			
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
		} catch(Exception e) {
			path = SEARCH;
			e.printStackTrace();
		}
	}
}
