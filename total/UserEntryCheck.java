package total;

import java.util.ArrayList;
import java.util.List;

import db.UserInfoAcquisition;

public class UserEntryCheck {

	public List<String> entryCheck(String userId, String passwd, String passwd2){
		List<String> eMessage3 = new ArrayList<>();
		UserInfoAcquisition uia = new UserInfoAcquisition();
		boolean flag = false;
		
		if(userId.equals("")) {
			eMessage3.add("「ユーザID」は必須項目です");
		}
		
		try {
			flag = uia.userIDInvestigate(userId);
			
			if(flag == true) {
				eMessage3.add("「ユーザID」が重複しています");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			eMessage3.add("../common/500.jsp");
			
			return eMessage3;
		}
		
		if(passwd.equals("")) {
			eMessage3.add("「パスワード」は必須項目です");
		}
		
		if(!passwd.equals(passwd2)) {
			eMessage3.add("「パスワード」と「パスワード（確認用）」が一致していません");
		}
		
		return eMessage3;
	}
}
