package model;

public class Employee {

	int eid;
	String name;
	double salary;
	int mid;
	public Employee() {
		
	}
	public int getEid() {
		return eid;
	}
	public void setEid(int eid) {
		this.eid = eid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public Employee(int eid, String name, double salary, int mid) {
		super();
		this.eid = eid;
		this.name = name;
		this.salary = salary;
		this.mid = mid;
	}
	
	

}
