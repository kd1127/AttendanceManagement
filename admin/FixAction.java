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

public class FixAction extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		try {
			String BACK_PAGE = "../JSP/courceList.jsp";
			String NEXT_PAGE = "../JSP/confirm.jsp";
			String ERROR_PAGE = "../JSP/courceFix.jsp";
			
			String path = "";
			
			List<String> eMessage4 = new ArrayList<>();
			HttpSession session = request.getSession();
			FixErrorCheck fec = new FixErrorCheck();
			
			request.setCharacterEncoding("UTF-8");
			
			String cource_no = (String)session.getAttribute("cource_no");
			String cource_name = request.getParameter("cource_name");
			String ayear = request.getParameter("ayear");
			String amonth = request.getParameter("amonth");
			String aday = request.getParameter("aday");
			String sthour = request.getParameter("sthour");
			String stminute = request.getParameter("stminute");
			String endhour = request.getParameter("endhour");
			String endminute = request.getParameter("endminute");
			String capacity = request.getParameter("capacity");
			
			eMessage4 = fec.errorVerify(cource_name, ayear, amonth, aday, sthour, stminute, endhour, endminute, capacity);
			
			session.setAttribute("cource_no", cource_no);
			session.setAttribute("cource_name", cource_name);
			session.setAttribute("ayear", ayear);
			session.setAttribute("amonth", amonth);
			session.setAttribute("aday", aday);
			session.setAttribute("sthour", sthour);
			session.setAttribute("stminute", stminute);
			session.setAttribute("endhour", endhour);
			session.setAttribute("endminute", endminute);
			session.setAttribute("capacity", capacity);
			
			if(request.getParameter("back") != null) {
				path = BACK_PAGE;
			}
			else if(eMessage4.isEmpty() == false) {
				path = ERROR_PAGE;
				request.setAttribute("eMessage4", eMessage4);
			}
			else {
				path = NEXT_PAGE;
			}
			
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} 
	}
}
