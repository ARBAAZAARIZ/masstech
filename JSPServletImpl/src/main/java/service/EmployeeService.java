package service;

import java.util.List;

import dao.EmployeeDAO;
import model.Employee;

public class EmployeeService {
	EmployeeDAO dao=new EmployeeDAO();
	
	public void AddEmployee(Employee e)
	{
		dao.AddEmp(e);
	}
	
	public List<Employee> fetchEmployee()
	{
		return dao.getEmpDetails();
	}
	
	public void deleteEmpById(int id)
	{
		dao.deleteEmp(id);
	}
	
	public Employee findEmpById(int id)
	{
		return dao.FindEmpbyId(id);
	}
}
