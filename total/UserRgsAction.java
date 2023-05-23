package total;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.UserBean;

public class UserRgsAction extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String BACK_PAGE = "../total/login.jsp";
		String NEXT_PAGE = "../total/confirmation.jsp";
		String ERROR_PAGE = "../total/userRegister.jsp";
		String ERROR500 = "../common/500.jsp";
		
		String path = "";
		
		HttpSession session = request.getSession();
		UserBean bn3 = (UserBean)session.getAttribute("bn3");
		UserEntryCheck uec = new UserEntryCheck();
		
		List<String> eMessage3 = new ArrayList<>();
		
		request.setCharacterEncoding("UTF-8");
		
		String userId = request.getParameter("userID");
		String pass = request.getParameter("pass");
		String passCheck = request.getParameter("pass2");
		
		if(userId.equals("")) {
			userId = "";
		}
		
		if(pass.equals("")) {
			pass = "";
		}
		
		if(passCheck.equals("")) {
			passCheck = "";
		}
		
		eMessage3 = uec.entryCheck(userId, pass, passCheck);
		
		String er500 = "";
		for(int i=0; i<eMessage3.size(); i++) {
			if(eMessage3.get(i).equals("../common/500.jsp")) {
				er500 = eMessage3.get(i);
			}
		}
		
		bn3.setUser_id(userId);
		bn3.setPasswd(pass);
		bn3.setPasswd2(passCheck);
		bn3.setAuthority(1);
		
		if(er500.equals("../common/500.jsp")) {
			path = ERROR500;
		}
		
		if(request.getParameter("back") != null) {		//	戻る
			session.removeAttribute("bn3");
			path = BACK_PAGE;
		}
		else {
			if(eMessage3.isEmpty() == false) {			//	エラー
				path = ERROR_PAGE;
				request.setAttribute("eMessage3", eMessage3);
			}
			else {										//	ユーザ登録画面へ
				path = NEXT_PAGE;
				session.setAttribute("bn3", bn3);
			}
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
}
