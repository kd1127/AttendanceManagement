package admin;

import java.util.ArrayList;
import java.util.List;

public class SearchErrorCheck {

	public List<String> errorCheck(String sshour, String ssminute, String sehour, String seminute){
		List<String> eMessage4 = new ArrayList<>();
		String start_time = "", end_time = "";
		int i_start_time = 0, i_end_time = 0;
		
		if(sshour.equals("0") && !ssminute.equals("0") || !sshour.equals("0") && ssminute.equals("0")) {
			eMessage4.add("「開始時刻」は「時」「分」どちらも入力してください");
		}
		
		if(sehour.equals("0") && !seminute.equals("0") || !sehour.equals("0") && seminute.equals("0")) {
			eMessage4.add("「終了時刻」は「時」「分」どちらも入力してください");
		}
		
		//	時刻の逆転のエラー検証
		if(!sshour.equals("") && !ssminute.equals("")) {
			start_time = sshour.concat(ssminute);
			i_start_time = Integer.parseInt(start_time);
		}
		
		if(!sehour.equals("") && !seminute.equals("")) {
			end_time = sehour.concat(seminute);
			i_end_time = Integer.parseInt(end_time);
		}
		
		if(!sshour.equals("0") && !ssminute.equals("0") && !sehour.equals("0") && !seminute.equals("0")) {
			if(i_end_time < i_start_time) {
				eMessage4.add("「終了時刻」は「開始時刻」よりも後の時刻を入力してください");
			}
		}
		
		return eMessage4;
	}
}
