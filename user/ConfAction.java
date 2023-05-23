package user;

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

import admin.InputDBAccess;
import beans.ApplicationBean;

public class ConfAction extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		String BACK_PAGE = "../user/input.jsp";
		String NEXT_PAGE = "../user/end.jsp";
		
		String path = "";
		String date = "";				//	InsertApplicationDBAccessのメソッドを呼び出すときに引数として設定する変数date
		String start = "";				//	InsertApplicationDBAccessのメソッドを呼び出すときに引数として設定する変数star
		
		HttpSession session = request.getSession();
		
		ApplicationBean bn2 = (ApplicationBean)session.getAttribute("bn2");
		
		InsertApplicationDBAccesss iadb = new InsertApplicationDBAccesss();
		InputDBAccess idb = new InputDBAccess();
		
		List<String> dlist = new ArrayList<String>();			//	開催日開始時刻終了時刻
		List<String> clist = new ArrayList<String>();			//	講座名
		
		boolean apply1 = bn2.isApply1();
		boolean apply2 = bn2.isApply2();
		boolean apply3 = bn2.isApply3();
		boolean apply4 = bn2.isApply4();
		boolean apply5 = bn2.isApply5();
		
		request.setCharacterEncoding("UTF-8");
		
		try {
			dlist = idb.acquisition(1, null, null, null);
			clist = idb.acquisition(2, null, null, null);
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(request.getParameter("back") != null) {
			path = BACK_PAGE;
		}
		else {
			path = NEXT_PAGE;
			
			int suffix = 0; 		//	DBメソッド呼び出し時に使用
			
			if(apply1 == true) {
				dlist.get(0);
				clist.get(0);
				suffix = 0;
				
				date = dlist.get(0).substring(0, 5);	//	表の開催日時から年を取り出す
				date = date.replace('年', '-');			//	DBに登録するため年月日の間に - に変換リプレイス
				date = date.concat(dlist.get(0).substring(5, 10));
				date = date.replace('月', '-');
				date = date.replace('日', ' ');
				start = dlist.get(0).substring(14, 19);
				
				try {
					iadb.insertDBAccess(bn2);
					iadb.insertApplyDBAccess(bn2, clist.get(suffix), date, start);
					idb.updateCapacity(clist.get(suffix), date, start);
				}
				catch(Exception e) {
					e.printStackTrace();
					path = BACK_PAGE;
				}
			}
			
			if(apply2 == true) {
				dlist.get(1);
				clist.get(1);
				suffix = 1;
				
				date = dlist.get(1).substring(0, 5);	//	表の開催日時から年を取り出す
				date = date.replace('年', '-');			//	DBに登録するため年月日の間に - に変換リプレイス
				date = date.concat(dlist.get(1).substring(5, 10));
				date = date.replace('月', '-');
				date = date.replace('日', ' ');
				
				start = dlist.get(1).substring(14, 19);
				
				try {
					iadb.insertDBAccess(bn2);
					iadb.insertApplyDBAccess(bn2, clist.get(suffix), date, start);
					idb.updateCapacity(clist.get(suffix), date, start);
				}
				catch(Exception e) {
					e.printStackTrace();
					path = BACK_PAGE;
				}
			}
			
			if(apply3 == true) {
				dlist.get(2);
				clist.get(2);
				suffix = 2;
				
				date = dlist.get(2).substring(0, 5);	//	表の開催日時から年を取り出す
				date = date.replace('年', '-');			//	DBに登録するため年月日の間に - に変換リプレイス
				date = date.concat(dlist.get(2).substring(5, 10));
				date = date.replace('月', '-');
				date = date.replace('日', ' ');
				
				start = dlist.get(2).substring(14, 19);
				
				try {
					iadb.insertDBAccess(bn2);
					iadb.insertApplyDBAccess(bn2, clist.get(suffix), date, start);
					idb.updateCapacity(clist.get(suffix), date, start);
				}
				catch(Exception e) {
					e.printStackTrace();
					path = BACK_PAGE;
				}
			}
			
			if(apply4 == true) {
				dlist.get(3);
				clist.get(3);
				suffix = 3;
				
				date = dlist.get(3).substring(0, 5);	//	表の開催日時から年を取り出す
				date = date.replace('年', '-');			//	DBに登録するため年月日の間に - に変換リプレイス
				date = date.concat(dlist.get(3).substring(5, 10));
				date = date.replace('月', '-');
				date = date.replace('日', ' ');
				
				start = dlist.get(3).substring(14, 19);
				
				try {
					iadb.insertDBAccess(bn2);
					iadb.insertApplyDBAccess(bn2, clist.get(suffix), date, start);
					idb.updateCapacity(clist.get(suffix), date, start);
				}
				catch(Exception e) {
					e.printStackTrace();
					path = BACK_PAGE;
				}
			}
			
			if(apply5 == true) {
				dlist.get(4);
				clist.get(4);
				suffix = 4;
				
				date = dlist.get(4).substring(0, 5);	//	表の開催日時から年を取り出す
				date = date.replace('年', '-');			//	DBに登録するため年月日の間に - に変換リプレイス
				date = date.concat(dlist.get(4).substring(5, 10));
				date = date.replace('月', '-');
				date = date.replace('日', ' ');
				
				start = dlist.get(4).substring(14, 19);
				
				try {
					iadb.insertDBAccess(bn2);
					iadb.insertApplyDBAccess(bn2, clist.get(suffix), date, start);
					idb.updateCapacity(clist.get(suffix), date, start);
				}
				catch(Exception e) {
					e.printStackTrace();
					path = BACK_PAGE;
				}
			}
			
			session.removeAttribute("bn2");
			bn2 = null;
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(path);
		rd.forward(request, response);
	}
}
