package result;

public class RegistCourseEntity {
	private String course_no;
	private String course_name;
	private String the_date;
	private String start_time;
	private String end_time;
	private int capacity;
	private String status;

	/**
	 * @return courceNO
	 */
	public String getCourse_no() {
		return course_no;
	}
	/**
	 * @param courceNO セットする courceNO
	 */
	public void setCourse_no(String course_no) {
		this.course_no = course_no;
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public String getThe_Date() {
		return the_date;
	}
	public void setThe_Date(String the_date) {
		this.the_date = the_date;
	}
	public String getStart_time() {
		return start_time;
	}
	public void setStart_Time(String start_time) {
		this.start_time = start_time;
	}
	public String getEnd_Time() {
		return end_time;
	}
	public void setEnd_Time(String end_time) {
		this.end_time = end_time;
	}
	
	/**
	 * @return capacity
	 */
	public int getCapacity() {
		return capacity;
	}
	/**
	 * @param capacity セットする capacity
	 */
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	/**
	 * @return status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status セットする status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}
