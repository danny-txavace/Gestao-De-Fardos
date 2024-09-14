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
public final class Transferir_Loja3 extends JFrame implements  Config_idiomas, ActionListener {
    private JLabel lblDestino;
    private JComboBox txtDestino;
    private JButton btnNext;
    private Transferir_Loja31 transferir31 = null;
    private Transferir_Loja32 transferir32 = null;
    private Transferir_Loja3Jardim transferir3Jardim = null;
    private Transferir_Loja3Abeira transferir3Beira = null;
    private Transferir_Loja3Amaputo transferir3Maputo = null;
    
    public Transferir_Loja3() {
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
            String[] lojas = {"", "Loja 1", "Loja 2", "Loja Jardim", "Armazém da Beira", "Armazém do Maputo"};
            txtDestino = new JComboBox(lojas);
        } else {
            String[] shops = {"", "Shop 1", "Shop 2", "Jardim Shop", "Beira Warehouse", "Maputo Warehouse"};
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
                        if(transferir31 == null || !transferir31.isVisible()) {
                            try {
                                transferir31 = new Transferir_Loja31();
                            } catch (SQLException ex) {
                                Logger.getLogger(Transferir_Loja3.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            transferir31.setVisible(true);
                            transferir31.toFront();
                        } else {
                            transferir31.toFront();
                        }
                    });
                } else if("Loja 2".equals(txtDestino.getSelectedItem())) {
                    SwingUtilities.invokeLater(() -> {
                        if(transferir32 == null || !transferir32.isVisible()) {
                            try {
                                transferir32 = new Transferir_Loja32();
                            } catch (SQLException ex) {
                                Logger.getLogger(Transferir_Loja3.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            transferir32.setVisible(true);
                            transferir32.toFront();
                        } else {
                            transferir32.toFront();
                        }
                    });
                } else if("Loja Jardim".equals(txtDestino.getSelectedItem())) {
                    SwingUtilities.invokeLater(() -> {
                        if(transferir3Jardim == null || !transferir3Jardim.isVisible()) {
                            try {
                                transferir3Jardim = new Transferir_Loja3Jardim();
                            } catch (SQLException ex) {
                                Logger.getLogger(Transferir_Loja3.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            transferir3Jardim.setVisible(true);
                            transferir3Jardim.toFront();
                        } else {
                            transferir3Jardim.toFront();
                        }
                    });
                } else if("Armazém da Beira".equals(txtDestino.getSelectedItem())) {
                    SwingUtilities.invokeLater(() -> {
                        if(transferir3Beira == null || !transferir3Beira.isVisible()) {
                            try {
                                transferir3Beira = new Transferir_Loja3Abeira();
                            } catch (SQLException ex) {
                                Logger.getLogger(Transferir_Loja3.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            transferir3Beira.setVisible(true);
                            transferir3Beira.toFront();
                        } else {
                            transferir3Beira.toFront();
                        }
                    });
                } else if("Armazém do Maputo".equals(txtDestino.getSelectedItem())) {
                    SwingUtilities.invokeLater(() -> {
                        if(transferir3Maputo == null || !transferir3Maputo.isVisible()) {
                            try {
                                transferir3Maputo = new Transferir_Loja3Amaputo();
                            } catch (SQLException ex) {
                                Logger.getLogger(Transferir_Loja3.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            transferir3Maputo.setVisible(true);
                            transferir3Maputo.toFront();
                        } else {
                            transferir3Maputo.toFront();
                        }
                    });
                }
            } else {
                if("Shop 1".equals(txtDestino.getSelectedItem())) {
                    SwingUtilities.invokeLater(() -> {
                        if(transferir31 == null || !transferir31.isVisible()) {
                            try {
                                transferir31 = new Transferir_Loja31();
                            } catch (SQLException ex) {
                                Logger.getLogger(Transferir_Loja3.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            transferir31.setVisible(true);
                            transferir31.toFront();
                        } else {
                            transferir31.toFront();
                        }
                    });
                } else if("Shop 2".equals(txtDestino.getSelectedItem())) {
                    SwingUtilities.invokeLater(() -> {
                        if(transferir32 == null || !transferir32.isVisible()) {
                            try {
                                transferir32 = new Transferir_Loja32();
                            } catch (SQLException ex) {
                                Logger.getLogger(Transferir_Loja3.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            transferir32.setVisible(true);
                            transferir32.toFront();
                        } else {
                            transferir32.toFront();
                        }
                    });
                } else if("Jardim Shop".equals(txtDestino.getSelectedItem())) {
                    SwingUtilities.invokeLater(() -> {
                        if(transferir3Jardim == null || !transferir3Jardim.isVisible()) {
                            try {
                                transferir3Jardim = new Transferir_Loja3Jardim();
                            } catch (SQLException ex) {
                                Logger.getLogger(Transferir_Loja3.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            transferir3Jardim.setVisible(true);
                            transferir3Jardim.toFront();
                        } else {
                            transferir3Jardim.toFront();
                        }
                    });
                } else if("Beira Warehouse".equals(txtDestino.getSelectedItem())) {
                    SwingUtilities.invokeLater(() -> {
                        if(transferir3Beira == null || !transferir3Beira.isVisible()) {
                            try {
                                transferir3Beira = new Transferir_Loja3Abeira();
                            } catch (SQLException ex) {
                                Logger.getLogger(Transferir_Loja3.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            transferir3Beira.setVisible(true);
                            transferir3Beira.toFront();
                        } else {
                            transferir3Beira.toFront();
                        }
                    });
                } else if("Maputo Warehouse".equals(txtDestino.getSelectedItem())) {
                    SwingUtilities.invokeLater(() -> {
                        if(transferir3Maputo == null || !transferir3Maputo.isVisible()) {
                            try {
                                transferir3Maputo = new Transferir_Loja3Amaputo();
                            } catch (SQLException ex) {
                                Logger.getLogger(Transferir_Loja3.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            transferir3Maputo.setVisible(true);
                            transferir3Maputo.toFront();
                        } else {
                            transferir3Maputo.toFront();
                        }
                    });
                }
            }
        }
    }
}
