package dao;

import java.sql.*;

import util.DB_Connection;

public class ForgotPasswordDAO {

    public String resolveEmailByIdentifier(String identifier) {
        String resolvedEmail = null;

        try (Connection conn = DB_Connection.getConnection()) {
            // Check if identifier is an email
            PreparedStatement psEmail = conn.prepareStatement("SELECT email FROM members WHERE email = ?");
            psEmail.setString(1, identifier);
            ResultSet rsEmail = psEmail.executeQuery();

            if (rsEmail.next()) {
                resolvedEmail = rsEmail.getString("email");
            } else {
                // Check if identifier is a username
                PreparedStatement psUser = conn.prepareStatement("SELECT member_id FROM users WHERE username = ?");
                psUser.setString(1, identifier);
                ResultSet rsUser = psUser.executeQuery();

                if (rsUser.next()) {
                    long memberId = rsUser.getLong("member_id");

                    PreparedStatement psMember = conn.prepareStatement("SELECT email FROM members WHERE member_id = ?");
                    psMember.setLong(1, memberId);
                    ResultSet rsMember = psMember.executeQuery();

                    if (rsMember.next()) {
                        resolvedEmail = rsMember.getString("email");
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resolvedEmail;
    }
    
    public boolean updatePassword(String email, String newPassword) {
        String sql = """
            UPDATE users 
            SET password_hash = ? 
            WHERE member_id = (SELECT member_id FROM members WHERE email = ?)
        """;

        try (Connection conn = DB_Connection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

             // use your own hashing logic
            ps.setString(1, newPassword);
            ps.setString(2, email);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

}
