package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Employee;
import util.DBConnection;

public class EmployeeDAO {
	
	public void AddEmp(Employee e)
	{
		try
		{
			Connection conn=DBConnection.getConnection();
			String q="insert into employee(ename,email,esalary,profile_photo) values('"+e.getEname()+"','"+e.getEmail()+"','"+e.getEsalary()+"','"+e.getProfile_photo()+"')";
			PreparedStatement ps=conn.prepareStatement(q);
			ps.executeUpdate();
		}
		catch(Exception ex)
		{
			System.out.println(e);
		}
	}
	
	public List<Employee> getEmpDetails()
	{
		List<Employee> emps=new ArrayList<Employee>();
		try
		{
			Connection conn=DBConnection.getConnection();
			String q="select * from employee";
			PreparedStatement ps=conn.prepareStatement(q);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next())
			{
				emps.add(new Employee(
							rs.getInt("eid"),
							rs.getString("ename"),
							rs.getString("email"),
							rs.getDouble("esalary"),
							rs.getString("profile_photo")
						
						));
				
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		return emps;
	}
	public Employee FindEmpbyId(int id) {
		Employee emps = null;
		try
		{
			Connection conn=DBConnection.getConnection();
			String q="select *  from employee where eid='"+id+"'";
			PreparedStatement ps=conn.prepareStatement(q);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				emps=new Employee(
						rs.getInt("eid"),
						rs.getString("ename"),
						rs.getString("email"),
						rs.getDouble("esalary"),
						rs.getString("profile_photo")
						
						);

			}
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
		return emps;
	}
	
	
	
	
	public void deleteEmp(int id)
	{
		try
		{
			Connection conn=DBConnection.getConnection();
			String q="delete from employee where eid='"+id+"'";
			PreparedStatement ps=conn.prepareStatement(q);
			ps.executeUpdate();
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
	}
}
