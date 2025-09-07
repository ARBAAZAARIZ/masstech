package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.EmployeeDetail;
import util.DB_Connection;

public class EmployeeDAO {

    public List<EmployeeDetail> getAllEmployee() {
        List<EmployeeDetail> employeeList = new ArrayList<>();

        try (Connection con = DB_Connection.getConnection();
             CallableStatement cs = con.prepareCall("{CALL view_employee()}");
             ResultSet rs = cs.executeQuery()) {

            while (rs.next()) {
                EmployeeDetail emp = new EmployeeDetail();

                emp.setEmpId(rs.getInt("emp_id"));
                emp.setName(rs.getString("name"));
                emp.setUsername(rs.getString("username"));
                emp.setEmail(rs.getString("email"));
                emp.setPhone(rs.getString("phone"));
                emp.setDob(rs.getDate("dob").toLocalDate());
                emp.setGender(rs.getString("gender"));
                emp.setDepartmentName(rs.getString("dept_name"));
                emp.setDesignationName(rs.getString("desig_title"));
                emp.setDoj(rs.getDate("doj").toLocalDate());
                emp.setStatus(rs.getString("status"));
                emp.setRole(rs.getString("role"));
                employeeList.add(emp);
            }

        } catch (Exception e) {
            System.out.println("Error fetching employee data: " + e.getMessage());
        }

        return employeeList;
    }
}

