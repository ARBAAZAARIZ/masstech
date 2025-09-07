package model;

public class Employee {
	private int eid;
	private String ename;
	private String email;
	private double esalary;
	private String profile_photo;
	public Employee(int eid, String ename, String email, double esalary, String profile_photo) {
		//super();
		this.eid = eid;
		this.ename = ename;
		this.email = email;
		this.esalary = esalary;
		this.profile_photo = profile_photo;
	}
	public Employee() {
		//super();
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getEsalary() {
		return esalary;
	}
	public void setEsalary(double esalary) {
		this.esalary = esalary;
	}
	public String getProfile_photo() {
		return profile_photo;
	}
	public void setProfile_photo(String profile_photo) {
		this.profile_photo = profile_photo;
	}
	
	
	
	
	
	
}
