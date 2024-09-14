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
public final class Transferir_LojaJardim extends JFrame implements  Config_idiomas, ActionListener {
    private JLabel lblDestino;
    private JComboBox txtDestino;
    private JButton btnNext;
    private Transferir_LojaJardim1 transferirJardim1 = null;
    private Transferir_LojaJardim2 transferirJardim2 = null;
    private Transferir_LojaJardim3 transferirJardim3 = null;
    private Transferir_LojaJardimAbeira transferirJardimBeira = null;
    private Transferir_LojaJardimAmaputo transferirJardimMaputo = null;
    
    public Transferir_LojaJardim() {
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
            String[] lojas = {"", "Loja 1", "Loja 2", "Loja 3", "Armazém da Beira", "Armazém do Maputo"};
            txtDestino = new JComboBox(lojas);
        } else {
            String[] shops = {"", "Shop 1", "Shop 2", "Shop 3", "Beira Warehouse", "Maputo Warehouse"};
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
                        if(transferirJardim1 == null || !transferirJardim1.isVisible()) {
                            try {
                                transferirJardim1 = new Transferir_LojaJardim1();
                            } catch (SQLException ex) {
                                Logger.getLogger(Transferir_LojaJardim.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            transferirJardim1.setVisible(true);
                            transferirJardim1.toFront();
                        } else {
                            transferirJardim1.toFront();
                        }
                    });
                } else if("Loja 2".equals(txtDestino.getSelectedItem())) {
                    SwingUtilities.invokeLater(() -> {
                        if(transferirJardim2 == null || !transferirJardim2.isVisible()) {
                            try {
                                transferirJardim2 = new Transferir_LojaJardim2();
                            } catch (SQLException ex) {
                                Logger.getLogger(Transferir_LojaJardim.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            transferirJardim2.setVisible(true);
                            transferirJardim2.toFront();
                        } else {
                            transferirJardim2.toFront();
                        }
                    });
                } else if("Loja 3".equals(txtDestino.getSelectedItem())) {
                    SwingUtilities.invokeLater(() -> {
                        if(transferirJardim3 == null || !transferirJardim3.isVisible()) {
                            try {
                                transferirJardim3 = new Transferir_LojaJardim3();
                            } catch (SQLException ex) {
                                Logger.getLogger(Transferir_LojaJardim.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            transferirJardim3.setVisible(true);
                            transferirJardim3.toFront();
                        } else {
                            transferirJardim3.toFront();
                        }
                    });
                } else if("Armazém da Beira".equals(txtDestino.getSelectedItem())) {
                    SwingUtilities.invokeLater(() -> {
                        if(transferirJardimBeira == null || !transferirJardimBeira.isVisible()) {
                            try {
                                transferirJardimBeira = new Transferir_LojaJardimAbeira();
                            } catch (SQLException ex) {
                                Logger.getLogger(Transferir_LojaJardim.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            transferirJardimBeira.setVisible(true);
                            transferirJardimBeira.toFront();
                        } else {
                            transferirJardimBeira.toFront();
                        }
                    });
                } else if("Armazém do Maputo".equals(txtDestino.getSelectedItem())) {
                    SwingUtilities.invokeLater(() -> {
                        if(transferirJardimMaputo == null || !transferirJardimMaputo.isVisible()) {
                            try {
                                transferirJardimMaputo = new Transferir_LojaJardimAmaputo();
                            } catch (SQLException ex) {
                                Logger.getLogger(Transferir_LojaJardim.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            transferirJardimMaputo.setVisible(true);
                            transferirJardimMaputo.toFront();
                        } else {
                            transferirJardimMaputo.toFront();
                        }
                    });
                }
            } else {
                if("Shop 1".equals(txtDestino.getSelectedItem())) {
                    SwingUtilities.invokeLater(() -> {
                        if(transferirJardim1 == null || !transferirJardim1.isVisible()) {
                            try {
                                transferirJardim1 = new Transferir_LojaJardim1();
                            } catch (SQLException ex) {
                                Logger.getLogger(Transferir_LojaJardim.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            transferirJardim1.setVisible(true);
                            transferirJardim1.toFront();
                        } else {
                            transferirJardim1.toFront();
                        }
                    });
                } else if("Shop 2".equals(txtDestino.getSelectedItem())) {
                    SwingUtilities.invokeLater(() -> {
                        if(transferirJardim2 == null || !transferirJardim2.isVisible()) {
                            try {
                                transferirJardim2 = new Transferir_LojaJardim2();
                            } catch (SQLException ex) {
                                Logger.getLogger(Transferir_LojaJardim.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            transferirJardim2.setVisible(true);
                            transferirJardim2.toFront();
                        } else {
                            transferirJardim2.toFront();
                        }
                    });
                } else if("Shop 3".equals(txtDestino.getSelectedItem())) {
                    SwingUtilities.invokeLater(() -> {
                        if(transferirJardim3 == null || !transferirJardim3.isVisible()) {
                            try {
                                transferirJardim3 = new Transferir_LojaJardim3();
                            } catch (SQLException ex) {
                                Logger.getLogger(Transferir_LojaJardim.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            transferirJardim3.setVisible(true);
                            transferirJardim3.toFront();
                        } else {
                            transferirJardim3.toFront();
                        }
                    });
                } else if("Beira Warehouse".equals(txtDestino.getSelectedItem())) {
                    SwingUtilities.invokeLater(() -> {
                        if(transferirJardimBeira == null || !transferirJardimBeira.isVisible()) {
                            try {
                                transferirJardimBeira = new Transferir_LojaJardimAbeira();
                            } catch (SQLException ex) {
                                Logger.getLogger(Transferir_LojaJardim.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            transferirJardimBeira.setVisible(true);
                            transferirJardimBeira.toFront();
                        } else {
                            transferirJardimBeira.toFront();
                        }
                    });
                } else if("Maputo Warehouse".equals(txtDestino.getSelectedItem())) {
                    SwingUtilities.invokeLater(() -> {
                        if(transferirJardimMaputo == null || !transferirJardimMaputo.isVisible()) {
                            try {
                                transferirJardimMaputo = new Transferir_LojaJardimAmaputo();
                            } catch (SQLException ex) {
                                Logger.getLogger(Transferir_LojaJardim.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            transferirJardimMaputo.setVisible(true);
                            transferirJardimMaputo.toFront();
                        } else {
                            transferirJardimMaputo.toFront();
                        }
                    });
                }
            }
        }
    }
}