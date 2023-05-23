package admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class WelcomeAction extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		final String REGISTER_PAGE = "../JSP/input.jsp";
		final String REMOVEFIX_PAGE = "/JSP/search.jsp";				//	ファイル名が現在不明なため実習３で追加 講座修正削除画面へ遷移
		
		String path = "";
		String userId = (String)request.getAttribute("userID");
		
		HttpSession session = request.getSession();
		
		if(request.getParameter("register") != null) {
			path = REGISTER_PAGE;
		}
		else {
			path = REMOVEFIX_PAGE;
			request.setAttribute("userId", userId);
			session.removeAttribute("syear");
			session.removeAttribute("smonth");
			session.removeAttribute("sday");;
			session.removeAttribute("sshour");
			session.removeAttribute("ssminute");
			session.removeAttribute("sehour");
			session.removeAttribute("seminute");
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
}
