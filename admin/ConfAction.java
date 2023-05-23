package admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.InputBean;

public class ConfAction extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String BACK_PAGE = "/JSP/input.jsp";
		String NEXT_PAGE = "/JSP/end.jsp";
		
		String path = "";
		
		HttpSession session = request.getSession();
		
		InputBean bn = (InputBean)session.getAttribute("bn");
		
		String anumber = (String)session.getAttribute("anumber");
		String aname = (String)session.getAttribute("aname");
		String ayear =  (String)session.getAttribute("ayear");
		String amonth = (String)session.getAttribute("amonth");
		String aday = (String)session.getAttribute("aday");
		String sthour = (String)session.getAttribute("sthour");
		String stminute = (String)session.getAttribute("stminute");
		String endhour = (String)session.getAttribute("endhour");
		String endminute = (String)session.getAttribute("endminute");
		String capacity = (String)session.getAttribute("endminute");
		
		request.setCharacterEncoding("UTF-8");
		
		InputDBAccess idb = new InputDBAccess();
		
		if(request.getParameter("back") != null) {
			session.setAttribute("anumber", anumber);
			session.setAttribute("aname", aname);
			session.setAttribute("ayear", ayear);
			session.setAttribute("amonth", amonth);
			session.setAttribute("aday", aday);
			session.setAttribute("sthour", sthour);
			session.setAttribute("stminute", stminute);
			session.setAttribute("endhour", endhour);
			session.setAttribute("endminute", endminute);
			session.setAttribute("capacity", capacity);
			
			path = BACK_PAGE;
		}
		else{
			path = NEXT_PAGE;
			
			try {				
				if(bn != null) {
					idb.dataRegisterInfo(bn);	//	DBにデータ登録
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
//			セッションオブジェクトを消去してend.jspに画面遷移
			session.removeAttribute("anumber");
			session.removeAttribute("aname");
			session.removeAttribute("ayear");
			session.removeAttribute("amonth");
			session.removeAttribute("aday");
			session.removeAttribute("sthour");
			session.removeAttribute("stminute");
			session.removeAttribute("endhour");
			session.removeAttribute("endminute");
			session.removeAttribute("capacity");
			bn = null;
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
}
