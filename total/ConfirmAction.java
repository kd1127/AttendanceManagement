package total;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.UserBean;
import db.UserInfoAcquisition;

public class ConfirmAction extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		try {
			String BACK_PAGE = "../total/userRegister.jsp";
			String NEXT_PAGE = "../total/confirmed.jsp";
			
			String path = "";
			
			HttpSession session = request.getSession();
			UserBean bn3 = (UserBean)session.getAttribute("bn3");
			UserInfoAcquisition userDB = new UserInfoAcquisition();
			
			request.setCharacterEncoding("UTF-8");
			
			String user_id = bn3.getUser_id();
			String password = bn3.getPasswd();
			int authority = bn3.getAuthority();
			
			if(request.getParameter("back") != null) {
				path = BACK_PAGE;
				session.setAttribute("bn3", bn3);
			}
			else {
				path = NEXT_PAGE;
				
				try {
					userDB.insertUserData(user_id, password, authority); 
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				session.removeAttribute("bn3");
				request.setAttribute("user_id", user_id);
			}
			
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}
}
