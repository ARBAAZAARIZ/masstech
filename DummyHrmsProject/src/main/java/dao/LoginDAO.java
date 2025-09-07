package dao;

import java.sql.*;
import model.Userdetails;
import util.DB_Connection; 

public class LoginDAO {

    public Userdetails login(String emailOrUsername, String password) {
        Userdetails user = null;

        try (Connection conn = DB_Connection.getConnection(); 
             CallableStatement cs = conn.prepareCall("{CALL sp_login_full_details(?, ?)}")) {

            cs.setString(1, emailOrUsername);
            cs.setString(2, password);

            ResultSet rs = cs.executeQuery();

            if (rs.next()) {
                user = new Userdetails();
                user.setEmpId(rs.getInt("emp_id"));
                user.setName(rs.getString("name"));
                user.setUsername(rs.getString("username"));
                user.setEmail(rs.getString("email"));
                user.setPhone(rs.getString("phone"));
                user.setGender(rs.getString("gender"));
                user.setDob(rs.getDate("dob"));
                user.setDoj(rs.getDate("doj"));
                user.setDepartmentId(rs.getInt("department_id"));
                user.setDesignationId(rs.getInt("designation_id"));
                user.setStatus(rs.getString("status"));
                user.setRole(rs.getString("role"));
                user.setMessage(rs.getString("message"));
            }

        } catch (Exception e) {
            e.printStackTrace(); 
        }

        return user;
    }
}
