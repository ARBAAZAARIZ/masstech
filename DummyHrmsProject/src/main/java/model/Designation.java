package model;

import java.sql.Timestamp;

public class Designation {
    private int desigId;
    private String desigTitle;
    private int deptId;
    private String level;
    private Timestamp createdAt;

    // Default constructor
    public Designation() {}

    // Parameterized constructor
    public Designation(int desigId, String desigTitle, int deptId, String level, Timestamp createdAt) {
        this.desigId = desigId;
        this.desigTitle = desigTitle;
        this.deptId = deptId;
        this.level = level;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public int getDesigId() { return desigId; }
    public void setDesigId(int desigId) { this.desigId = desigId; }

    public String getDesigTitle() { return desigTitle; }
    public void setDesigTitle(String desigTitle) { this.desigTitle = desigTitle; }

    public int getDeptId() { return deptId; }
    public void setDeptId(int deptId) { this.deptId = deptId; }

    public String getLevel() { return level; }
    public void setLevel(String level) { this.level = level; }

    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    @Override
    public String toString() {
        return "Designation{" +
               "desigId=" + desigId +
               ", desigTitle='" + desigTitle + '\'' +
               ", deptId=" + deptId +
               ", level='" + level + '\'' +
               ", createdAt=" + createdAt +
               '}';
    }
}

