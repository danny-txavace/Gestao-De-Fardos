package telas;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 * @author Ramadan ismaeL
 */
public class Usuario {
    private static final String URL = "jdbc:mysql://localhost:3306/dbbluemoon";
    private static final String user = "root";
    private static final String password = "mysql_danny2";
    
    public static String getUsuarioNome() {
        String sql = "SELECT cUser FROM tbcontrolleruser WHERE id = 1;";

        try (Connection conn = DriverManager.getConnection(URL, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("cUser");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro no getUsuarioNome na classe Usuario \n"+e, "Atenção", 0);
        }
        return null;
    }
    
    public static String getUsuarioPerfil() {
        String sql = "SELECT cPerfil FROM tbcontrolleruser WHERE id = 1;";

        try (Connection conn = DriverManager.getConnection(URL, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("cPerfil");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro no getUsuarioPerfil na classe Usuario \n"+e, "Atenção", 0);
        }
        return null;
    }
    
    public static void setUsuario(String nome, String perfil) {
        String sql = "UPDATE tbcontrolleruser SET cUser = ?, cPerfil = ? WHERE id = 1;";

        try (Connection conn = DriverManager.getConnection(URL, user, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nome);
            pstmt.setString(2, perfil);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro no setUsuario na classe Usuario \n"+e, "Atenção", 0);
        }
    }
}
