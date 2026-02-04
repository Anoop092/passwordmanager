package com.avv.app.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Base64;
import java.util.Optional;

import com.avv.app.dto.EncryptedVaultDTO;

public class VaultRepository {

    private static String url = "jdbc:sqlite:C:\\sqllite\\passwordmanager";

    private static Connection setupConnection() throws Exception {
        return DriverManager.getConnection(url);
    }

    // CREATE vault (used during signup)
    public static void save(EncryptedVaultDTO dto) {

        try {
            Connection con = setupConnection();

            String query = "INSERT INTO vaults(userId, vault, iv) VALUES(?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, dto.getUserId());
            ps.setString(2, dto.getEncryptedVault());
            ps.setString(3, dto.getIvInString());

            ps.executeUpdate();

            // 🔑 VERY IMPORTANT: close in correct order
            ps.close();
            con.close();

        } catch (Exception e) {
            System.err.println(e.getClass().getName() + " : " + e.getMessage());
        }
    }

    // READ vault (used during login)
    public static Optional<EncryptedVaultDTO> getVaults(int userId) throws Exception {

        Connection con = setupConnection();

        String query = "SELECT * FROM vaults WHERE userId = " + userId + ";";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);

        if (rs.next()) {
            String encryptedVault = rs.getString("vault");
            byte[] iv = Base64.getDecoder().decode(rs.getString("iv"));

            EncryptedVaultDTO dto =
                    new EncryptedVaultDTO(iv, encryptedVault, userId);

            // 🔑 CLOSE EVERYTHING BEFORE RETURN
            rs.close();
            st.close();
            con.close();

            return Optional.of(dto);
        }

        // 🔑 CLOSE EVERYTHING IF NO RECORD
        rs.close();
        st.close();
        con.close();

        return Optional.empty();
    }

    // UPDATE vault (used when adding/updating entries)
    public static void updateVault(String json, int userId) throws Exception {

        Connection connection = setupConnection();

        String query = "UPDATE vaults SET vault = ? WHERE userId = ?";

        PreparedStatement ps = connection.prepareStatement(query);
        ps.setString(1, json);
        ps.setInt(2, userId);

        ps.executeUpdate();

        // 🔑 CLOSE RESOURCES
        ps.close();
        connection.close();
    }
}