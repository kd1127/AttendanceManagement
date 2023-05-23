package beans;

import java.io.Serializable;
import java.time.LocalDate;

public class UserBean implements Serializable{
	
	// serialVersionUIDを指定
    private static final long serialVersionUID = 2L;

    private int id;
    private String user_id;
    private String passwd;
    private String passwd2;
    private int authority;
    private LocalDate inp_date;
    
    public UserBean() {
    	user_id = "";
    	passwd = "";
    	passwd2 = "";
    	authority = 1;
    	inp_date = LocalDate.now();
    }
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getPasswd2() {
		return passwd2;
	}
	public void setPasswd2(String passwd2) {
		this.passwd2 = passwd2;
	}
	public int getAuthority() {
		return authority;
	}
	public void setAuthority(int authority) {
		this.authority = authority;
	}
	public LocalDate getInp_date() {
		return inp_date;
	}
	public void setInp_date(LocalDate inp_date) {
		this.inp_date = inp_date;
	}
	
	@Override
	public String toString() {
		return "id: " + id + " ユーザID:" + user_id + " パスワード:" + passwd + " パスワード２:" + passwd2 + " 権限:" + authority + "日付" + inp_date;
	}
}
