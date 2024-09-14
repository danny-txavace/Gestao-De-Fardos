package telas;

import Abstrato.Config_idiomas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 * @author Ramadan ismaeL
 */
public final class Transferir_ArmazemMaputo extends JFrame implements  Config_idiomas, ActionListener {
    private JLabel lblDestino;
    private JComboBox txtDestino;
    private JButton btnNext;
    private Transferir_ArmazemMaputo1 transferirA1 = null;
    private Transferir_ArmazemMaputo2 transferirA2 = null;
    private Transferir_ArmazemMaputo3 transferirA3 = null;
    private Transferir_ArmazemMaputoJardim transferirAJardim = null;
    private Transferir_ArmazemMaputoAbeira transferirABeira = null;
    
    public Transferir_ArmazemMaputo() {
        Janela();
        configView();
        
        if(Idiomas.getPort() == true) {
            configPort();
        } else {
            configEng();
        }
    }
    
    private void Janela() {
        if(Idiomas.getPort() == true) {
            setTitle("Transferência");
        } else {
            setTitle("Transference");
        }
        setSize(287, 146);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(false);
    }
    
    public void configView() {
        setLayout(null);
        
        lblDestino = new JLabel();
        
        if(Idiomas.getPort() == true) {
            String[] lojas = {"", "Loja 1", "Loja 2", "Loja 3", "Loja Jardim", "Armazém da Beira"};
            txtDestino = new JComboBox(lojas);
        } else {
            String[] shops = {"", "Shop 1", "Shop 2", "Shop 3", "Jardim Shop", "Beira Warehouse"};
            txtDestino = new JComboBox(shops);
        }
        
        btnNext = new JButton();
        
        lblDestino.setFont(new Font("Times New Roman", 0, 16));
        lblDestino.setForeground(Color.black);
        lblDestino.setBounds(5, 5, 90, 28);
        
        txtDestino.setFont(new Font("Times New Roman", 0, 15));
        txtDestino.setForeground(Color.black);
        txtDestino.setBounds(100, 5, 168, 28);
        
        btnNext.setFont(new Font("Agency FB", 0, 16));
        btnNext.setForeground(Color.black);
        btnNext.setBounds(167, 79, 100, 25);
        
        configTools();
        
        getContentPane().add(lblDestino);
        getContentPane().add(txtDestino);
        getContentPane().add(btnNext);
    }
    
    public void configTools() {
        btnNext.addActionListener(this);
    }

    @Override
    public void configPort() {
        lblDestino.setText("Destino :");
        btnNext.setText("Next");
    }

    @Override
    public void configEng() {
        lblDestino.setText("Destination :");
        btnNext.setText("Next");
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if(evt.getSource() == btnNext) {
            if(Idiomas.getPort() == true) {
                if("Loja 1".equals(txtDestino.getSelectedItem())) {
                    SwingUtilities.invokeLater(() -> {
                        if(transferirA1 == null || !transferirA1.isVisible()) {
                            try {
                                transferirA1 = new Transferir_ArmazemMaputo1();
                            } catch (SQLException ex) {
                                Logger.getLogger(Transferir_ArmazemMaputo.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            transferirA1.setVisible(true);
                            transferirA1.toFront();
                        } else {
                            transferirA1.toFront();
                        }
                    });
                } else if("Loja 2".equals(txtDestino.getSelectedItem())) {
                    SwingUtilities.invokeLater(() -> {
                        if(transferirA2 == null || !transferirA2.isVisible()) {
                            try {
                                transferirA2 = new Transferir_ArmazemMaputo2();
                            } catch (SQLException ex) {
                                Logger.getLogger(Transferir_ArmazemMaputo.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            transferirA2.setVisible(true);
                            transferirA2.toFront();
                        } else {
                            transferirA2.toFront();
                        }
                    });
                } else if("Loja 3".equals(txtDestino.getSelectedItem())) {
                    SwingUtilities.invokeLater(() -> {
                        if(transferirA3 == null || !transferirA3.isVisible()) {
                            try {
                                transferirA3 = new Transferir_ArmazemMaputo3();
                            } catch (SQLException ex) {
                                Logger.getLogger(Transferir_ArmazemMaputo.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            transferirA3.setVisible(true);
                            transferirA3.toFront();
                        } else {
                            transferirA3.toFront();
                        }
                    });
                } else if("Loja Jardim".equals(txtDestino.getSelectedItem())) {
                    SwingUtilities.invokeLater(() -> {
                        if(transferirAJardim == null || !transferirAJardim.isVisible()) {
                            try {
                                transferirAJardim = new Transferir_ArmazemMaputoJardim();
                            } catch (SQLException ex) {
                                Logger.getLogger(Transferir_ArmazemMaputo.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            transferirAJardim.setVisible(true);
                            transferirAJardim.toFront();
                        } else {
                            transferirAJardim.toFront();
                        }
                    });
                } else if("Armazém da Beira".equals(txtDestino.getSelectedItem())) {
                    SwingUtilities.invokeLater(() -> {
                        if(transferirABeira == null || !transferirABeira.isVisible()) {
                            try {
                                transferirABeira = new Transferir_ArmazemMaputoAbeira();
                            } catch (SQLException ex) {
                                Logger.getLogger(Transferir_ArmazemMaputo.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            transferirABeira.setVisible(true);
                            transferirABeira.toFront();
                        } else {
                            transferirABeira.toFront();
                        }
                    });
                }
                } else {
                    if("Shop 1".equals(txtDestino.getSelectedItem())) {
                        SwingUtilities.invokeLater(() -> {
                            if(transferirA1 == null || !transferirA1.isVisible()) {
                                try {
                                    transferirA1 = new Transferir_ArmazemMaputo1();
                            }    catch (SQLException ex) {
                                    Logger.getLogger(Transferir_ArmazemMaputo.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                transferirA1.setVisible(true);
                                transferirA1.toFront();
                            } else {
                                transferirA1.toFront();
                            }
                        });
                    } else if("Shop 2".equals(txtDestino.getSelectedItem())) {
                        SwingUtilities.invokeLater(() -> {
                            if(transferirA2 == null || !transferirA2.isVisible()) {
                            try {
                                transferirA2 = new Transferir_ArmazemMaputo2();
                            } catch (SQLException ex) {
                                Logger.getLogger(Transferir_ArmazemMaputo.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            transferirA2.setVisible(true);
                            transferirA2.toFront();
                        } else {
                            transferirA2.toFront();
                        }
                    });
                } else if("Shop 3".equals(txtDestino.getSelectedItem())) {
                    SwingUtilities.invokeLater(() -> {
                        if(transferirA3 == null || !transferirA3.isVisible()) {
                            try {
                                transferirA3 = new Transferir_ArmazemMaputo3();
                            } catch (SQLException ex) {
                                Logger.getLogger(Transferir_ArmazemMaputo.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            transferirA3.setVisible(true);
                            transferirA3.toFront();
                        } else {
                            transferirA3.toFront();
                        }
                    });
                } else if("Jardim Shop".equals(txtDestino.getSelectedItem())) {
                    SwingUtilities.invokeLater(() -> {
                        if(transferirAJardim == null || !transferirAJardim.isVisible()) {
                            try {
                                transferirAJardim = new Transferir_ArmazemMaputoJardim();
                            } catch (SQLException ex) {
                                Logger.getLogger(Transferir_ArmazemMaputo.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            transferirAJardim.setVisible(true);
                            transferirAJardim.toFront();
                        } else {
                            transferirAJardim.toFront();
                        }
                    });
                } else if("Beira Warehouse".equals(txtDestino.getSelectedItem())) {
                    SwingUtilities.invokeLater(() -> {
                        if(transferirABeira == null || !transferirABeira.isVisible()) {
                            try {
                                transferirABeira = new Transferir_ArmazemMaputoAbeira();
                            } catch (SQLException ex) {
                                Logger.getLogger(Transferir_ArmazemBeira.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            transferirABeira.setVisible(true);
                            transferirABeira.toFront();
                        } else {
                            transferirABeira.toFront();
                        }
                    });
                }
            }
        }
    }
}