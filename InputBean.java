package admin;

import java.io.Serializable;

public class InputBean implements Serializable{
	
	private String anumber;
	private String aname;
	private String ayear;
	private String amonth;
	private String aday;
	private String sthour;
	private String stminute;
	private String endhour;
	private String endminute;
	private int capacity;
	
	public InputBean() {
		anumber = "";
		aname = "";
		ayear = "";
		amonth = "";
		aday = "";
		sthour = "";
		stminute = "";
		endhour = "";
		endminute = "";
		capacity = 0;
	}
	
	public String getAnumber() {
		return anumber;
	}
	public void setAnumber(String anumber) {
		this.anumber = anumber;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}	
	
	public String getAyear() {
		return ayear;
	}
	public void setAyear(String ayear) {
		this.ayear = ayear;
	}
	public String getAmonth() {
		return amonth;
	}
	public void setAmonth(String amonth) {
		this.amonth = amonth;
	}
	public String getAday() {
		return aday;
	}
	public void setAday(String aday) {
		this.aday = aday;
	}
	public String getSthour() {
		return sthour;
	}
	public void setSthour(String sthour) {
		this.sthour = sthour;
	}
	public String getStminute() {
		return stminute;
	}
	public void setStminute(String stminute) {
		this.stminute = stminute;
	}
	public String getEndhour() {
		return endhour;
	}
	public void setEndhour(String endhour) {
		this.endhour = endhour;
	}
	public String getEndminute() {
		return endminute;
	}
	public void setEndminute(String endminute) {
		this.endminute = endminute;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	public String getInsertTime() {
		int y = Integer.parseInt(ayear);
		int m = Integer.parseInt(amonth);
		int d = Integer.parseInt(aday);
		return String.format("%04d%02d%02d", y, m, d);
	}
	
	public String getStarttime() {		
		int h = Integer.parseInt(sthour);
		int m = Integer.parseInt(stminute);
		
		return String.format("%02d:%02d", h, m);
	}
	
	public String getEndTime() {
		int h =Integer.parseInt(endhour);
		int m = Integer.parseInt(endminute);
		
		return String.format("%02d:%02d", h, m);
	}
}