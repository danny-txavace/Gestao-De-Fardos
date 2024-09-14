package main;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import telas.TelaLogin;

/**
 * @author Ramadan ismaeL
 */

public class Main {
    public static void main(String[] args) throws SQLException {
        SwingUtilities.invokeLater(() -> {
            try {
                new TelaLogin();
            } catch (SQLException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}