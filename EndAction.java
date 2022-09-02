package admin;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EndAction extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String BACK_TO_TOP = "/JSP/menu.jsp";
		String REGISTER_PAGE = "/JSP/input.jsp";
		
		String path = "";
		
		request.setCharacterEncoding("UTF-8");
		
		if(request.getParameter("BacktoTop") != null) {
			path = BACK_TO_TOP;
		}
		else {
			path = REGISTER_PAGE;
			
			//	以下登録画面に遷移した場合の処理
			String str = "register_page";
			
			request.setAttribute("str", str);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
}
