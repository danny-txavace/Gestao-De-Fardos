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
public final class Transferir_Loja2 extends JFrame implements  Config_idiomas, ActionListener {
    private JLabel lblDestino;
    private JComboBox txtDestino;
    private JButton btnNext;
    private Transferir_Loja21 transferir21 = null;
    private Transferir_Loja23 transferir23 = null;
    private Transferir_Loja2Jardim transferir2Jardim = null;
    private Transferir_Loja2Abeira transferir2Beira = null;
    private Transferir_Loja2Amaputo transferir2Maputo = null;
    
    public Transferir_Loja2() {
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
            String[] lojas = {"", "Loja 1", "Loja 3", "Loja Jardim", "Armazém da Beira", "Armazém do Maputo"};
            txtDestino = new JComboBox(lojas);
        } else {
            String[] shops = {"", "Shop 1", "Shop 3", "Jardim Shop", "Beira Warehouse", "Maputo Warehouse"};
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
                        if(transferir21 == null || !transferir21.isVisible()) {
                            try {
                                transferir21 = new Transferir_Loja21();
                            } catch (SQLException ex) {
                                Logger.getLogger(Transferir_Loja2.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            transferir21.setVisible(true);
                            transferir21.toFront();
                        } else {
                            transferir21.toFront();
                        }
                    });
                } else if("Loja 3".equals(txtDestino.getSelectedItem())) {
                    SwingUtilities.invokeLater(() -> {
                        if(transferir23 == null || !transferir23.isVisible()) {
                            try {
                                transferir23 = new Transferir_Loja23();
                            } catch (SQLException ex) {
                                Logger.getLogger(Transferir_Loja2.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            transferir23.setVisible(true);
                            transferir23.toFront();
                        } else {
                            transferir23.toFront();
                        }
                    });
                } else if("Loja Jardim".equals(txtDestino.getSelectedItem())) {
                    SwingUtilities.invokeLater(() -> {
                        if(transferir2Jardim == null || !transferir2Jardim.isVisible()) {
                            try {
                                transferir2Jardim = new Transferir_Loja2Jardim();
                            } catch (SQLException ex) {
                                Logger.getLogger(Transferir_Loja2.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            transferir2Jardim.setVisible(true);
                            transferir2Jardim.toFront();
                        } else {
                            transferir2Jardim.toFront();
                        }
                    });
                } else if("Armazém da Beira".equals(txtDestino.getSelectedItem())) {
                    SwingUtilities.invokeLater(() -> {
                        if(transferir2Beira == null || !transferir2Beira.isVisible()) {
                            try {
                                transferir2Beira = new Transferir_Loja2Abeira();
                            } catch (SQLException ex) {
                                Logger.getLogger(Transferir_Loja2.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            transferir2Beira.setVisible(true);
                            transferir2Beira.toFront();
                        } else {
                            transferir2Beira.toFront();
                        }
                    });
                } else if("Armazém do Maputo".equals(txtDestino.getSelectedItem())) {
                    SwingUtilities.invokeLater(() -> {
                        if(transferir2Maputo == null || !transferir2Maputo.isVisible()) {
                            try {
                                transferir2Maputo = new Transferir_Loja2Amaputo();
                            } catch (SQLException ex) {
                                Logger.getLogger(Transferir_Loja2.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            transferir2Maputo.setVisible(true);
                            transferir2Maputo.toFront();
                        } else {
                            transferir2Maputo.toFront();
                        }
                    });
                }
            } else {
                if("Shop 1".equals(txtDestino.getSelectedItem())) {
                    SwingUtilities.invokeLater(() -> {
                        if(transferir21 == null || !transferir21.isVisible()) {
                            try {
                                transferir21 = new Transferir_Loja21();
                            } catch (SQLException ex) {
                                Logger.getLogger(Transferir_Loja2.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            transferir21.setVisible(true);
                            transferir21.toFront();
                        } else {
                            transferir21.toFront();
                        }
                    });
                } else if("Shop 3".equals(txtDestino.getSelectedItem())) {
                    SwingUtilities.invokeLater(() -> {
                        if(transferir23 == null || !transferir23.isVisible()) {
                            try {
                                transferir23 = new Transferir_Loja23();
                            } catch (SQLException ex) {
                                Logger.getLogger(Transferir_Loja2.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            transferir23.setVisible(true);
                            transferir23.toFront();
                        } else {
                            transferir23.toFront();
                        }
                    });
                } else if("Jardim Shop".equals(txtDestino.getSelectedItem())) {
                    SwingUtilities.invokeLater(() -> {
                        if(transferir2Jardim == null || !transferir2Jardim.isVisible()) {
                            try {
                                transferir2Jardim = new Transferir_Loja2Jardim();
                            } catch (SQLException ex) {
                                Logger.getLogger(Transferir_Loja2.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            transferir2Jardim.setVisible(true);
                            transferir2Jardim.toFront();
                        } else {
                            transferir2Jardim.toFront();
                        }
                    });
                } else if("Beira Warehouse".equals(txtDestino.getSelectedItem())) {
                    SwingUtilities.invokeLater(() -> {
                        if(transferir2Beira == null || !transferir2Beira.isVisible()) {
                            try {
                                transferir2Beira = new Transferir_Loja2Abeira();
                            } catch (SQLException ex) {
                                Logger.getLogger(Transferir_Loja2.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            transferir2Beira.setVisible(true);
                            transferir2Beira.toFront();
                        } else {
                            transferir2Beira.toFront();
                        }
                    });
                } else if("Maputo Warehouse".equals(txtDestino.getSelectedItem())) {
                    SwingUtilities.invokeLater(() -> {
                        if(transferir2Maputo == null || !transferir2Maputo.isVisible()) {
                            try {
                                transferir2Maputo = new Transferir_Loja2Amaputo();
                            } catch (SQLException ex) {
                                Logger.getLogger(Transferir_Loja2.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            transferir2Maputo.setVisible(true);
                            transferir2Maputo.toFront();
                        } else {
                            transferir2Maputo.toFront();
                        }
                    });
                }
            }
        }
    }
}
