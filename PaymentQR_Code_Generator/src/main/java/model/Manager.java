package model;

public class Manager {

	public Manager() {
		
	}
	
	int id;
	String mname;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	
	public Manager(int id, String mname) {
		super();
		this.id = id;
		this.mname = mname;
	}
	
	

}
