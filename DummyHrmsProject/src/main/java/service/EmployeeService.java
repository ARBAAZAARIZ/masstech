package service;

import java.util.List;

import dao.EmployeeDAO;
import model.EmployeeDetail;

public class EmployeeService {
	
	EmployeeDAO empDao;
	public EmployeeService() {
		empDao=new EmployeeDAO();
	}
	
	public List<EmployeeDetail> getAllEmployeeDetails(){
		return empDao.getAllEmployee();
	}

}
