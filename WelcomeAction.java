package admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WelcomeAction extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		final String REGISTER_PAGE = "/JSP/input.jsp";
		final String REMOVEFIX_PAGE = "/JSP/";				//	ファイル名が現在不明なため後ほど追加
		
		String path = "";
		
		if(request.getParameter("register") != null) {
			path = REGISTER_PAGE;
			
			
		}
		//	後ほど追加
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
}
