package model;

import java.time.LocalDate;

public class EmployeeDetail {

    private int empId;
    private String name;
    private String username;
    private String email;
    private String phone;
    private LocalDate dob;
    private String gender;
    private String departmentName;
    private String designationName;
    private LocalDate doj;
    private String status;
    private String role;

    // Constructors
    public EmployeeDetail() {}

    public EmployeeDetail(int empId, String name, String username, String email, String phone,
                          LocalDate dob, String gender, String departmentName, String designationName,
                          LocalDate doj, String status, String role) {
        this.empId = empId;
        this.name = name;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.gender = gender;
        this.departmentName = departmentName;
        this.designationName = designationName;
        this.doj = doj;
        this.status = status;
        this.role = role;
    }

    // Getters and Setters
    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDesignationName() {
        return designationName;
    }

    public void setDesignationName(String designationName) {
        this.designationName = designationName;
    }

    public LocalDate getDoj() {
        return doj;
    }

    public void setDoj(LocalDate doj) {
        this.doj = doj;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
