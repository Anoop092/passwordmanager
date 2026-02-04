package com.avv.app.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.avv.app.dto.UserDTO;

public class UserRepository {

    private String url = "jdbc:sqlite:C:\\sqllite\\passwordmanager";

    private Connection setupConnection() throws SQLException {
        return DriverManager.getConnection(url);
    }

    public int saveUser(String email, int hash, String salt) {

        int userId = -1;
        String sql = "INSERT INTO users(email, password, salt) VALUES (?, ?, ?)";

        try {
            Connection con = setupConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, email);
            ps.setInt(2, hash);
            ps.setString(3, salt);

            ps.executeUpdate();

            // 🔑 get generated user id
            userId = getUserId(con, email);

            // 🔑 CLOSE RESOURCES
            ps.close();
            con.close();

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }

        return userId;
    }

    public int getUserId(Connection con, String email) throws SQLException {

        String query = "SELECT * FROM users WHERE email LIKE '" + email + "';";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

        int id = -1;
        if (rs.next()) {
            id = rs.getInt(1);
        }

        // 🔑 CLOSE RESOURCES
        rs.close();
        st.close();

        return id;
    }

    public UserDTO findUser(String email) throws SQLException {

        UserDTO user = null;
        Connection con = setupConnection();

        String query = "SELECT * FROM users WHERE email LIKE '" + email + "';";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

        if (rs.next()) {
            user = new UserDTO(
                rs.getInt("id"),
                rs.getString("email"),
                rs.getInt("password"),
                rs.getString("salt")
            );
        }

        // 🔑 CLOSE RESOURCES
        rs.close();
        st.close();
        con.close();

        return user;
    }
}
