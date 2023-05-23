package total;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ConfirmedAction extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String COURCE_APPLY = "../user/input.jsp";
		String LOGOUT = "../total/login.jsp";
		
		String path = "";
		
		request.setCharacterEncoding("UTF-8");
		
		if(request.getParameter("apply") != null) {
			path = COURCE_APPLY;
		}
		else {
			path = LOGOUT;
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
}
