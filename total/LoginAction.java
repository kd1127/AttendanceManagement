package total;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.UserBean;
import db.UserInfoAcquisition;

public class LoginAction extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String LOGIN_APPLY = "/user/input.jsp";			//	入力エラーがなければ遷移
		String LOGIN_ADMIN = "../JSP/menu.jsp";			//	同上
		String LOGIN_USER = "../total/userRegister.jsp";	//	ユーザ登録
		String ERROR = "../total/login.jsp";				//	入力エラーがあれば自画面遷移
		String ERROR500 = "../common/500.jsp";				//	DBエラーがあればエラーページ遷移
		
		String path = "";
		
		int authority = 3;
		
		List<String> eMessage2 = new ArrayList<>();
		
		UserBean bn3 = new UserBean();
		UserInfoAcquisition uia = new UserInfoAcquisition();
		HttpSession session = request.getSession();
		
		request.setCharacterEncoding("UTF-8");
		
		String userId = request.getParameter("userID");
		String pass = request.getParameter("pass");
		
		if(userId == "" || pass == "") {
			eMessage2.add("「ユーザID」と「パスワード」は必須項目です");
		}
		
		bn3.setUser_id(userId);
		bn3.setPasswd(pass);
		
		request.setAttribute("eMessage2", eMessage2);
		session.setAttribute("bn3", bn3);
		
		if(request.getParameter("register") != null) {
			path = LOGIN_USER;
			session.removeAttribute("bn3");
		}
		else {
			if(eMessage2.isEmpty() == false) {
				path = ERROR;
			}
			else {				
				try {
					authority = uia.memberInfoSearch(userId, pass);
				}
				catch (SQLException e) {
					e.printStackTrace();
					path = ERROR500;
				}
				
				if(authority == 0) {	//	管理者
					path = LOGIN_ADMIN;
				}
				else if(authority == 1){		//	一般ユーザー
					path = LOGIN_APPLY;
				}
				else {
					eMessage2.add("アカウントが存在していません。");
					path = ERROR;
				}
			}
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
}
