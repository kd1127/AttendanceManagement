package user;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.ApplicationBean;

public class InputAction extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		String NEXT_PAGE = "/user/conf.jsp";
		String ERROR = "/user/input.jsp";
		String ERROR404 = "/common/404.jsp";
		String ERROR500 = "/common/500.jsp";
			
		String path = "";
		
		HttpSession session = request.getSession();
		ApplicationBean bn2 = new ApplicationBean();
		
		InsertApplicationDBAccesss iadb = new InsertApplicationDBAccesss();
		
		List<String> eMessage = new ArrayList<>();				//	エラーメッセージを格納
		
		long longTel = 0;										//	電話番号入力チェック用変数　消してはいけない
		int igender = 0;										//	チェックボックスで選択した性別を整数化
		
		request.setCharacterEncoding("UTF-8");
		
		try {
			//	input.jspで入力された値を受け取る
			String name = request.getParameter("name");
			String furigana = request.getParameter("furigana");
			String mail = request.getParameter("mail");
			String byear = request.getParameter("byear");
			String bmonth = request.getParameter("bmonth");
			String bday = request.getParameter("bday");
			String tel = request.getParameter("tel");
			String remarks = request.getParameter("remarks");
			String gender = "";
			
			if(byear == null || byear.equals("")) {
				byear = "";
			}
			
			if(bmonth == null || bmonth.equals("")) {
				bmonth ="";
			}
			
			if(bday == null || bday.equals("")) {
				bday ="";
			}
			
			if(request.getParameter("gender") != null) {
				if(request.getParameter("gender").equals("man")) {
					gender = "1";
					igender = 1;
				}
				if(request.getParameter("gender").equals("woman")) {
					gender = "2";
					igender = 2;
				}
				
				bn2.setGender(igender);
			}
			else {
				gender = "3";
				igender = 3;
				bn2.setGender(igender);
			}
			
			try {
				bn2.setId(iadb.inputIdIncrement(bn2));	//	idの累積数を設定
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			
			bn2.setName(name);
			bn2.setFurigana(furigana);
			bn2.setMail(mail);
			bn2.setByear(byear);
			bn2.setBmonth(bmonth);
			bn2.setBday(bday);
			bn2.setTel(tel);
			bn2.setRemarks(remarks);
			
			if(name.equals("") || name == null) {
				eMessage.add("「お名前」は必須項目です");
			}
			
			if(name.length() > 100) {
				eMessage.add("「お名前」は１００文字以内で入力してください。");
			}
			
			if(furigana.equals("") || furigana == null) {
				eMessage.add("「フリガナ」は必須項目です。");
			}
			
			if(furigana.length() > 100) {
				eMessage.add("「フリガナ」は１００文字以内で入力してください。");
			}
			
//		if(iskana(furigana) == false) {
//			eMessage.add("フリガナ以外は入力しないでください。");
//		}
			
			if(mail.equals("") || mail == null) {
				eMessage.add("「Eメールアドレス」は必須項目です。");
			}
			
			if(mail.indexOf("@") == -1 && mail != null && !mail.equals("")) {
				eMessage.add("「Eメールアドレス」の形式が不正です。");
			}
			
			try {
				String birthday = bn2.insertBirthday();			//	入力チェックのため消してはいけない
			}
			catch(NumberFormatException e) {
				String birthday = "";
				
				if(birthday.equals("") || birthday == null) {
					eMessage.add("「生年月日」は必須項目です。");
				}
			}
			
			if(tel.equals("") == false) {
				try {
					longTel = Long.parseLong(tel);
				}
				catch(NumberFormatException e) {
					eMessage.add("「電話番号」は数字で入力してください。");
				}
			}

			String apply1 = request.getParameter("apply1");
			String apply2 = request.getParameter("apply2");
			String apply3 = request.getParameter("apply3");
			String apply4 = request.getParameter("apply4");
			String apply5 = request.getParameter("apply5");
			
			if(apply1 != null) {
				bn2.setApply1(true);
			}
			if(apply2 != null) {
				bn2.setApply2(true);
			}
			if(apply3 != null) {
				bn2.setApply3(true);
			}
			if(apply4 != null) {
				bn2.setApply4(true);
			}
			if(apply5 != null) {
				bn2.setApply5(true);
			}
			
			if(apply1 == null && apply2 == null && apply3 == null && apply4 == null && apply5 == null){
				apply1 = null;
				apply2 = null;
				apply3 = null;
				apply4 = null;
				apply5 = null;
				eMessage.add("「希望講座」は必須項目です。");
			}

			request.setAttribute("eMessage", eMessage);
			
			session.setAttribute("name", name);
			session.setAttribute("furigana", furigana);
			session.setAttribute("mail", mail);
			session.setAttribute("gender", gender);
			session.setAttribute("byear", byear);
			session.setAttribute("bmonth", bmonth);
			session.setAttribute("bday", bday);
			session.setAttribute("tel", tel);
			session.setAttribute("remarks", remarks);
			session.setAttribute("bn2", bn2);
			
			if(eMessage.isEmpty() == true) {
				path = NEXT_PAGE;
			}
			else {
				path = ERROR;
			}
			
			//	エラーがあればそれぞれのエラーページに遷移
			if(response.getStatus() == 404) {
				path = ERROR404;
			}
			
			if(response.getStatus() == 500) {
				path = ERROR500;
			}
			
			RequestDispatcher rd = request.getRequestDispatcher(path);
			rd.forward(request, response);
		}
		catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
		} 
	}
}
	
//	public static boolean iskana(String value) {　カタカナかどうかを調べるメソッド ネット転用
//	    boolean result = false;
//	    if (value != null) {
//	        Pattern pattern = Pattern.compile("^[\u30a0-\u30ff]+$");
//	        result = pattern.matcher(value).matches();
//	    }
//	    return result;
//	}
//}
