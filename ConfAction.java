package admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ConfAction extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String BACK_PAGE = "/JSP/input.jsp";
		String NEXT_PAGE = "/JSP/end.jsp";
		
		String path = "";
		
		HttpSession session = request.getSession();
		
		InputBean bn = (InputBean)session.getAttribute("bn");
		
		request.setCharacterEncoding("UTF-8");
		
		InputDBAccess idb = new InputDBAccess();
		
		if(request.getParameter("back") != null) {
			path = BACK_PAGE;
		}
		else {
			path = NEXT_PAGE;
			
			try {
				System.out.println(bn.getAnumber());
				
				if(bn != null) {
					idb.dataRegisterInfo(bn);
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
}
