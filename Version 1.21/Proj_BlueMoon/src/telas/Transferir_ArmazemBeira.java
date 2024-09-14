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
public final class Transferir_ArmazemBeira extends JFrame implements  Config_idiomas, ActionListener {
    private JLabel lblDestino;
    private JComboBox txtDestino;
    private JButton btnNext;
    private Transferir_ArmazemBeira1 transferirB1 = null;
    private Transferir_ArmazemBeira2 transferirB2 = null;
    private Transferir_ArmazemBeira3 transferirB3 = null;
    private Transferir_ArmazemBeiraJardim transferirBJardim = null;
    private Transferir_ArmazemBeiraAmaputo transferirBMaputo = null;
    
    public Transferir_ArmazemBeira() {
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
            String[] lojas = {"", "Loja 1", "Loja 2", "Loja 3", "Loja Jardim", "Armazém do Maputo"};
            txtDestino = new JComboBox(lojas);
        } else {
            String[] shops = {"", "Shop 1", "Shop 2", "Shop 3", "Jardim Shop", "Maputo Warehouse"};
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
                        if(transferirB1 == null || !transferirB1.isVisible()) {
                            try {
                                transferirB1 = new Transferir_ArmazemBeira1();
                            } catch (SQLException ex) {
                                Logger.getLogger(Transferir_ArmazemBeira.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            transferirB1.setVisible(true);
                            transferirB1.toFront();
                        } else {
                            transferirB1.toFront();
                        }
                    });
                } else if("Loja 2".equals(txtDestino.getSelectedItem())) {
                    SwingUtilities.invokeLater(() -> {
                        if(transferirB2 == null || !transferirB2.isVisible()) {
                            try {
                                transferirB2 = new Transferir_ArmazemBeira2();
                            } catch (SQLException ex) {
                                Logger.getLogger(Transferir_ArmazemBeira.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            transferirB2.setVisible(true);
                            transferirB2.toFront();
                        } else {
                            transferirB2.toFront();
                        }
                    });
                } else if("Loja 3".equals(txtDestino.getSelectedItem())) {
                    SwingUtilities.invokeLater(() -> {
                        if(transferirB3 == null || !transferirB3.isVisible()) {
                            try {
                                transferirB3 = new Transferir_ArmazemBeira3();
                            } catch (SQLException ex) {
                                Logger.getLogger(Transferir_ArmazemBeira.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            transferirB3.setVisible(true);
                            transferirB3.toFront();
                        } else {
                            transferirB3.toFront();
                        }
                    });
                } else if("Loja Jardim".equals(txtDestino.getSelectedItem())) {
                    SwingUtilities.invokeLater(() -> {
                        if(transferirBJardim == null || !transferirBJardim.isVisible()) {
                            try {
                                transferirBJardim = new Transferir_ArmazemBeiraJardim();
                            } catch (SQLException ex) {
                                Logger.getLogger(Transferir_ArmazemBeira.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            transferirBJardim.setVisible(true);
                            transferirBJardim.toFront();
                        } else {
                            transferirBJardim.toFront();
                        }
                    });
                } else if("Armazém do Maputo".equals(txtDestino.getSelectedItem())) {
                    SwingUtilities.invokeLater(() -> {
                        if(transferirBMaputo == null || !transferirBMaputo.isVisible()) {
                            try {
                                transferirBMaputo = new Transferir_ArmazemBeiraAmaputo();
                            } catch (SQLException ex) {
                                Logger.getLogger(Transferir_ArmazemBeira.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            transferirBMaputo.setVisible(true);
                            transferirBMaputo.toFront();
                        } else {
                            transferirBMaputo.toFront();
                        }
                    });
                }
                } else {
                    if("Shop 1".equals(txtDestino.getSelectedItem())) {
                        SwingUtilities.invokeLater(() -> {
                            if(transferirB1 == null || !transferirB1.isVisible()) {
                                try {
                                    transferirB1 = new Transferir_ArmazemBeira1();
                            }    catch (SQLException ex) {
                                    Logger.getLogger(Transferir_ArmazemBeira.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                transferirB1.setVisible(true);
                                transferirB1.toFront();
                            } else {
                                transferirB1.toFront();
                            }
                        });
                    } else if("Shop 2".equals(txtDestino.getSelectedItem())) {
                        SwingUtilities.invokeLater(() -> {
                            if(transferirB2 == null || !transferirB2.isVisible()) {
                            try {
                                transferirB2 = new Transferir_ArmazemBeira2();
                            } catch (SQLException ex) {
                                Logger.getLogger(Transferir_ArmazemBeira.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            transferirB2.setVisible(true);
                            transferirB2.toFront();
                        } else {
                            transferirB2.toFront();
                        }
                    });
                } else if("Shop 3".equals(txtDestino.getSelectedItem())) {
                    SwingUtilities.invokeLater(() -> {
                        if(transferirB3 == null || !transferirB3.isVisible()) {
                            try {
                                transferirB3 = new Transferir_ArmazemBeira3();
                            } catch (SQLException ex) {
                                Logger.getLogger(Transferir_ArmazemBeira.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            transferirB3.setVisible(true);
                            transferirB3.toFront();
                        } else {
                            transferirB3.toFront();
                        }
                    });
                } else if("Jardim Shop".equals(txtDestino.getSelectedItem())) {
                    SwingUtilities.invokeLater(() -> {
                        if(transferirBJardim == null || !transferirBJardim.isVisible()) {
                            try {
                                transferirBJardim = new Transferir_ArmazemBeiraJardim();
                            } catch (SQLException ex) {
                                Logger.getLogger(Transferir_ArmazemBeira.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            transferirBJardim.setVisible(true);
                            transferirBJardim.toFront();
                        } else {
                            transferirBJardim.toFront();
                        }
                    });
                } else if("Maputo Warehouse".equals(txtDestino.getSelectedItem())) {
                    SwingUtilities.invokeLater(() -> {
                        if(transferirBMaputo == null || !transferirBMaputo.isVisible()) {
                            try {
                                transferirBMaputo = new Transferir_ArmazemBeiraAmaputo();
                            } catch (SQLException ex) {
                                Logger.getLogger(Transferir_ArmazemBeira.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            transferirBMaputo.setVisible(true);
                            transferirBMaputo.toFront();
                        } else {
                            transferirBMaputo.toFront();
                        }
                    });
                }
            }
        }
    }
}