package admin;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.InputBean;
import db.SearchInfoDBAccess;

public class DeleteAction extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String BACK_PAGE = "../JSP/courceList.jsp";
		String NEXT_PAGE = "../JSP/deleteConfirmed.jsp";
		
		String path = "";
		
		HttpSession session = request.getSession();
		SearchInfoDBAccess sidb = new SearchInfoDBAccess();
		InputBean bn6 = (InputBean)session.getAttribute("bn6");
		
		request.setCharacterEncoding("UTF-8");
		
		if(request.getParameter("back") != null) {
			path = BACK_PAGE;
		}
		else {
			path = NEXT_PAGE;
			try {
				sidb.deleteInfoDBAccess(1, bn6.getAnumber());
				sidb.deleteInfoDBAccess(2, bn6.getAnumber());
			}
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
}
