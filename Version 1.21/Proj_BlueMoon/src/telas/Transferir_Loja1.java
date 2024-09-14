package telas;

import Abstrato.Config_idiomas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Ramadan ismaeL
 */
public final class Transferir_Loja1 extends JFrame implements  Config_idiomas, ActionListener {
    private JLabel lblDestino;
    private JComboBox txtDestino;
    private JButton btnNext;
    private Transferir_Loja12 transferir12 = null;
    private Transferir_Loja13 transferir13 = null;
    private Transferir_Loja1Jardim transferir1Jardim = null;
    private Transferir_Loja1Abeira transferir1Beira = null;
    private Transferir_Loja1Amaputo transferir1Maputo = null;
    
    public Transferir_Loja1() {
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
            String[] lojas = {"", "Loja 2", "Loja 3", "Loja Jardim", "Armazém da Beira", "Armazém do Maputo"};
            txtDestino = new JComboBox(lojas);
        } else {
            String[] shops = {"", "Shop 2", "Shop 3", "Jardim Shop", "Beira Warehouse", "Maputo Warehouse"};
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
                if("Loja 2".equals(txtDestino.getSelectedItem())) {
                    SwingUtilities.invokeLater(() -> {
                        if(transferir12 == null || !transferir12.isVisible()) {
                            try {
                                transferir12 = new Transferir_Loja12();
                            } catch (SQLException ex) {
                                Logger.getLogger(Transferir_Loja1.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            transferir12.setVisible(true);
                            transferir12.toFront();
                        } else {
                            transferir12.toFront();
                        }
                    });
                } else if("Loja 3".equals(txtDestino.getSelectedItem())) {
                    SwingUtilities.invokeLater(() -> {
                        if(transferir13 == null || !transferir13.isVisible()) {
                            try {
                                transferir13 = new Transferir_Loja13();
                            } catch (SQLException ex) {
                                Logger.getLogger(Transferir_Loja1.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            transferir13.setVisible(true);
                            transferir13.toFront();
                        } else {
                            transferir13.toFront();
                        }
                    });
                } else if("Loja Jardim".equals(txtDestino.getSelectedItem())) {
                    SwingUtilities.invokeLater(() -> {
                        if(transferir1Jardim == null || !transferir1Jardim.isVisible()) {
                            try {
                                transferir1Jardim = new Transferir_Loja1Jardim();
                            } catch (SQLException ex) {
                                Logger.getLogger(Transferir_Loja1.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            transferir1Jardim.setVisible(true);
                            transferir1Jardim.toFront();
                        } else {
                            transferir1Jardim.toFront();
                        }
                    });
                } else if("Armazém da Beira".equals(txtDestino.getSelectedItem())) {
                    SwingUtilities.invokeLater(() -> {
                        if(transferir1Beira == null || !transferir1Beira.isVisible()) {
                            try {
                                transferir1Beira = new Transferir_Loja1Abeira();
                            } catch (SQLException ex) {
                                Logger.getLogger(Transferir_Loja1.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            transferir1Beira.setVisible(true);
                            transferir1Beira.toFront();
                        } else {
                            transferir1Beira.toFront();
                        }
                    });
                } else if("Armazém do Maputo".equals(txtDestino.getSelectedItem())) {
                    SwingUtilities.invokeLater(() -> {
                        if(transferir1Maputo == null || !transferir1Maputo.isVisible()) {
                            try {
                                transferir1Maputo = new Transferir_Loja1Amaputo();
                            } catch (SQLException ex) {
                                Logger.getLogger(Transferir_Loja1.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            transferir1Maputo.setVisible(true);
                            transferir1Maputo.toFront();
                        } else {
                            transferir1Maputo.toFront();
                        }
                    });
                }
            } else {
                if("Shop 2".equals(txtDestino.getSelectedItem())) {
                    SwingUtilities.invokeLater(() -> {
                        if(transferir12 == null || !transferir12.isVisible()) {
                            try {
                                transferir12 = new Transferir_Loja12();
                            } catch (SQLException ex) {
                                Logger.getLogger(Transferir_Loja1.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            transferir12.setVisible(true);
                            transferir12.toFront();
                        } else {
                            transferir12.toFront();
                        }
                    });
                } else if("Shop 3".equals(txtDestino.getSelectedItem())) {
                    SwingUtilities.invokeLater(() -> {
                        if(transferir13 == null || !transferir13.isVisible()) {
                            try {
                                transferir13 = new Transferir_Loja13();
                            } catch (SQLException ex) {
                                Logger.getLogger(Transferir_Loja1.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            transferir13.setVisible(true);
                            transferir13.toFront();
                        } else {
                            transferir13.toFront();
                        }
                    });
                } else if("Jardim Shop".equals(txtDestino.getSelectedItem())) {
                    SwingUtilities.invokeLater(() -> {
                        if(transferir1Jardim == null || !transferir1Jardim.isVisible()) {
                            try {
                                transferir1Jardim = new Transferir_Loja1Jardim();
                            } catch (SQLException ex) {
                                Logger.getLogger(Transferir_Loja1.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            transferir1Jardim.setVisible(true);
                            transferir1Jardim.toFront();
                        } else {
                            transferir1Jardim.toFront();
                        }
                    });
                } else if("Beira Warehouse".equals(txtDestino.getSelectedItem())) {
                    SwingUtilities.invokeLater(() -> {
                        if(transferir1Beira == null || !transferir1Beira.isVisible()) {
                            try {
                                transferir1Beira = new Transferir_Loja1Abeira();
                            } catch (SQLException ex) {
                                Logger.getLogger(Transferir_Loja1.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            transferir1Beira.setVisible(true);
                            transferir1Beira.toFront();
                        } else {
                            transferir1Beira.toFront();
                        }
                    });
                } else if("Maputo Warehouse".equals(txtDestino.getSelectedItem())) {
                    SwingUtilities.invokeLater(() -> {
                        if(transferir1Maputo == null || !transferir1Maputo.isVisible()) {
                            try {
                                transferir1Maputo = new Transferir_Loja1Amaputo();
                            } catch (SQLException ex) {
                                Logger.getLogger(Transferir_Loja1.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            transferir1Maputo.setVisible(true);
                            transferir1Maputo.toFront();
                        } else {
                            transferir1Maputo.toFront();
                        }
                    });
                }
            }
        }
    }
}
