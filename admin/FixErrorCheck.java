package admin;

import java.util.ArrayList;
import java.util.List;

public class FixErrorCheck {

	public List<String> errorVerify(String cource_name, String year, String month, String day, String shour, String sminute, String endhour, String endminute, String capacity){
		List<String> eMessage4 = new ArrayList<>();
		int istart_time = 0;
		int iend_time = 0;
		String start_time = "";
		String end_time = "";
		
		boolean stflag = false;
		boolean edflag = false;
		
		if(cource_name.equals("")) {
			eMessage4.add("「講座名」は必須項目です");
		}
		
		if(year.equals("") || month.equals("") || day.equals("")) {
			eMessage4.add("「講座開催日」は必須項目です");
		}
		
		if(shour.equals("") || sminute.equals("")) {
			eMessage4.add("「開始時刻」は必須項目です");
		}
		else {
			start_time = shour.concat(sminute);
			istart_time = Integer.parseInt(start_time);
			stflag = true;
		}
		
		if(endhour.equals("") || endminute.equals("")) {
			eMessage4.add("「終了時刻」は必須項目です");
		}
		else {
			end_time = endhour.concat(endminute);		
			iend_time = Integer.parseInt(end_time);
			edflag = true;
		}
		
		if(stflag == true && edflag == true) {
			if(istart_time > iend_time) {
				eMessage4.add("「終了時刻」は「開始時刻」よりも後の時刻を入力してください");
			}
		}
		
		if(capacity.equals("")) {
			eMessage4.add("「定員」は必須項目です");
		}
		else {
			try {
				int icapacity = Integer.parseInt(capacity);
				
				if(icapacity <= 0 || icapacity >= 51) {
					eMessage4.add("「定員」は1以上、50以下で入力してください");
				}
			}
			catch(NumberFormatException e) {
				eMessage4.add("「定員」は数字で入力してください");
			}
		}
		
		return eMessage4;
	}
}
