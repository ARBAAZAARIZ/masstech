package model;

import java.sql.Date;

public class Employee {
    private int empId;
    private String name;
    private String email;
    private String phone;
    private Date dob;
    private String gender;
    private int departmentId;
    private int designationId;
    private Date doj;
    private String status;
    private String role;
    private String password;

    // Default constructor
    public Employee() {}

    // Parameterized constructor
    public Employee(int empId, String name, String email, String phone, Date dob, String gender,
                    int departmentId, int designationId, Date doj, String status, String role, String password) {
        this.empId = empId;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.dob = dob;
        this.gender = gender;
        this.departmentId = departmentId;
        this.designationId = designationId;
        this.doj = doj;
        this.status = status;
        this.role = role;
        this.password = password;
    }

    // Getters and Setters
    public int getEmpId() { return empId; }
    public void setEmpId(int empId) { this.empId = empId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public Date getDob() { return dob; }
    public void setDob(Date dob) { this.dob = dob; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public int getDepartmentId() { return departmentId; }
    public void setDepartmentId(int departmentId) { this.departmentId = departmentId; }

    public int getDesignationId() { return designationId; }
    public void setDesignationId(int designationId) { this.designationId = designationId; }

    public Date getDoj() { return doj; }
    public void setDoj(Date doj) { this.doj = doj; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    @Override
    public String toString() {
        return "Employee{" +
               "empId=" + empId +
               ", name='" + name + '\'' +
               ", email='" + email + '\'' +
               ", phone='" + phone + '\'' +
               ", dob=" + dob +
               ", gender='" + gender + '\'' +
               ", departmentId=" + departmentId +
               ", designationId=" + designationId +
               ", doj=" + doj +
               ", status='" + status + '\'' +
               ", role='" + role + '\'' +
               ", password='" + password + '\'' +
               '}';
    }
}

