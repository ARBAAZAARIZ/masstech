package dao;

import model.AuthUser;
import util.DB_Connection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class AuthDAO {

    

    public AuthUser authenticate(String loginId, String password) {
        AuthUser authUser = null;
        String sql = "{CALL sp_user_login(?, ?)}"; // stored procedure

        try (Connection conn = DB_Connection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setString(1, loginId);   // username OR email
            stmt.setString(2, password);  // plain/hashed password

            boolean hasResult = stmt.execute();

            if (hasResult) {
                try (ResultSet rs = stmt.getResultSet()) {
                    if (rs.next()) {
                        authUser = new AuthUser(
                            rs.getLong("user_id"),
                            rs.getString("username"),
                            rs.getString("email"),
                            rs.getString("role"),
                            rs.getString("full_name"),
                            rs.getString("phone"),
                            rs.getString("status"),
                            rs.getTimestamp("last_login_at"),
                            rs.getString("profile_photo"),
                            rs.getInt("society_id"),
                            rs.getInt("member_id")
                        );
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        

        return authUser; // returns null if authentication fails
    }
}
