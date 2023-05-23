package beans;

import java.io.Serializable;

public class ApplicationBean implements Serializable{
	
	// serialVersionUIDを指定
    private static final long serialVersionUID = 2L;
	
	private int id;
	private String name;
	private String furigana;
	private String mail;
	private String tel;
	private String remarks;
	private String byear;
	private String bmonth;
	private String bday;
	private int gender;
	
	boolean apply1 = false;
	boolean apply2 = false;
	boolean apply3 = false;
	boolean apply4 = false;
	boolean apply5 = false;
	
	public ApplicationBean() {
		name = "";
		furigana = "";
		mail = "";
		tel = "";
		remarks = "";
		byear = "";
		bmonth = "";
		bday = "";
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFurigana() {
		return furigana;
	}
	public void setFurigana(String furigana) {
		this.furigana = furigana;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	public String getByear() {
		return byear;
	}
	public void setByear(String byear) {
		this.byear = byear;
	}
	public String getBmonth() {
		return bmonth;
	}
	public void setBmonth(String bmonth) {
		this.bmonth = bmonth;
	}
	public String getBday() {
		return bday;
	}
	public void setBday(String bday) {
		this.bday = bday;
	}
	
	public String insertBirthday() {
		int y = Integer.parseInt(byear);
		int m = Integer.parseInt(bmonth);
		int d = Integer.parseInt(bday);
		return String.format("%04d%02d%02d", y, m, d);
	}
	public boolean isApply1() {
		return apply1;
	}
	public void setApply1(boolean apply1) {
		this.apply1 = apply1;
	}
	public boolean isApply2() {
		return apply2;
	}
	public void setApply2(boolean apply2) {
		this.apply2 = apply2;
	}
	public boolean isApply3() {
		return apply3;
	}
	public void setApply3(boolean apply3) {
		this.apply3 = apply3;
	}
	public boolean isApply4() {
		return apply4;
	}
	public void setApply4(boolean apply4) {
		this.apply4 = apply4;
	}
	public boolean isApply5() {
		return apply5;
	}
	public void setApply5(boolean apply5) {
		this.apply5 = apply5;
	}
	
}