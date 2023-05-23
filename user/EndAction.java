package user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EndAction extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String path = "../user/input.jsp";
		
		HttpSession session = request.getSession();
		
		request.setCharacterEncoding("UTF-8");
		
		session.removeAttribute("byear");
		session.removeAttribute("bmonth");
		session.removeAttribute("bday");
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
}
