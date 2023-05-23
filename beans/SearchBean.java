package beans;

import java.io.Serializable;

public class SearchBean implements Serializable{

	private static final long serialVersionUID = 2L;
	
	private String cource_no;
	private String cource_name;
	private String syear;
	private String smonth;
	private String sday;
	private String sshour;		//	Search start hour
	private String ssminute;
	private String sehour;		//	Search end hour
	private String seminute;
	private String capacity;
	private String end_condition;	//	終了状態
	private String in_session;		//	開催中
	private String date_held;		//	開催予定
	private String no_condition;	//	条件なし
	
	public SearchBean() {
		cource_no = "";
		cource_name = "";
		syear = "";
		smonth = "";
		sday = "";
		sshour = "";
		ssminute = "";
		sehour = "";
		seminute = "";
		capacity = "";
		end_condition = "";
		in_session = "";
		date_held = "";
		no_condition = "";
	}

	public String getCource_no() {
		return cource_no;
	}

	public void setCource_no(String cource_number) {
		this.cource_no = cource_number;
	}

	public String getCource_name() {
		return cource_name;
	}

	public void setCource_name(String cource_name) {
		this.cource_name = cource_name;
	}

	public String getSyear() {
		return syear;
	}

	public void setSyear(String syear) {
		this.syear = syear;
	}

	public String getSmonth() {
		return smonth;
	}

	public void setSmonth(String smonth) {
		this.smonth = smonth;
	}

	public String getSday() {
		return sday;
	}

	public void setSday(String sday) {
		this.sday = sday;
	}

	public String getSshour() {
		return sshour;
	}

	public void setSshour(String sshour) {
		this.sshour = sshour;
	}

	public String getSsminute() {
		return ssminute;
	}

	public void setSsminute(String ssminute) {
		this.ssminute = ssminute;
	}

	public String getSehour() {
		return sehour;
	}

	public void setSehour(String sehour) {
		this.sehour = sehour;
	}

	public String getSeminute() {
		return seminute;
	}

	public void setSeminute(String seminute) {
		this.seminute = seminute;
	}

	public String getCapacity() {
		return capacity;
	}

	public void setCapacity(String capacity) {
		this.capacity = capacity;
	}

	public String getEnd_condition() {
		return end_condition;
	}

	public void setEnd_condition(String end_condition) {
		this.end_condition = end_condition;
	}

	public String getIn_session() {
		return in_session;
	}

	public void setIn_session(String in_session) {
		this.in_session = in_session;
	}

	public String getDate_held() {
		return date_held;
	}

	public void setDate_held(String date_held) {
		this.date_held = date_held;
	}

	public String getNo_condition() {
		return no_condition;
	}

	public void setNo_condition(String no_condition) {
		this.no_condition = no_condition;
	}

	
	
}
