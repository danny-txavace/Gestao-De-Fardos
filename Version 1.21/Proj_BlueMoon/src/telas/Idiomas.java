package telas;

import java.sql.*;

/**
 * @author Ramadan ismaeL
 */

public class Idiomas {
    private static final String URL = "jdbc:mysql://localhost:3306/dbbluemoon";
    private static final String user = "root";
    private static final String password = "mysql_danny2";

    
    //--PORTUGUÊS
    public static boolean getPort() {
        String sql = "SELECT ESTADO FROM tbidioma WHERE id = 1;";

        try (Connection conn = DriverManager.getConnection(URL, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getBoolean("ESTADO");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static void setPort(boolean state) {
        String sql = "UPDATE tbidioma SET ESTADO = ? WHERE id = 1;";

        try (Connection conn = DriverManager.getConnection(URL, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setBoolean(1, state);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    //-- ENGLISH
    public static boolean getEng() {
        String sql = "SELECT ESTADO FROM tbidioma WHERE id = 2;";

        try (Connection conn = DriverManager.getConnection(URL, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getBoolean("ESTADO");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public static void setEng(boolean state) {
        String sql = "UPDATE tbidioma SET ESTADO = ? WHERE id = 2;";

        try (Connection conn = DriverManager.getConnection(URL, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setBoolean(1, state);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
