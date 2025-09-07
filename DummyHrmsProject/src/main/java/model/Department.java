package model;

import java.sql.Timestamp;

public class Department {
    private int deptId;
    private String deptName;
    private String location;
    private Timestamp createdAt;

    // Default constructor
    public Department() {}

    // Parameterized constructor
    public Department(int deptId, String deptName, String location, Timestamp createdAt) {
        this.deptId = deptId;
        this.deptName = deptName;
        this.location = location;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public int getDeptId() { return deptId; }
    public void setDeptId(int deptId) { this.deptId = deptId; }

    public String getDeptName() { return deptName; }
    public void setDeptName(String deptName) { this.deptName = deptName; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    @Override
    public String toString() {
        return "Department{" +
               "deptId=" + deptId +
               ", deptName='" + deptName + '\'' +
               ", location='" + location + '\'' +
               ", createdAt=" + createdAt +
               '}';
    }
}

