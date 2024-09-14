package telas;

import Abstrato.Config_idiomas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import dao.ConexaoDAO;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.event.ChangeEvent;

/**
 * @author Ramadan ismaeL
 */

public final class TelaEdd_Produto_Loja3 extends JFrame implements ActionListener, Config_idiomas {
    protected JLabel lblID, txtID, lblDesignacao, lblMarca, lblCategoria, lblUnidade, lblQuantidade, lblPreco, lblPrecousd;
    protected JTextField txtDesignacao, txtMarca, txtUnidade, txtPreco, txtPrecousd;
    protected JComboBox txtCategoria;
    protected JSpinner txtQuantidade;
    private JRadioButton kg, pecas;
    private JButton btnSalvar, btnCancelar;
    private JList listaDesignacao, listaMarca;
    private JScrollPane scroll_listaDesignacao, scroll_listaMarca;
    private DefaultListModel model;
    private int adicionado;
    private Connection conexao = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    
    public TelaEdd_Produto_Loja3() throws SQLException {
        conexao = ConexaoDAO.conector();
        
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
            setTitle("EDITAR  PRODUTOS");
        } else {
            setTitle("PRODUCTS EDIT");
        }
        setSize(560, 340);        
        setLocationRelativeTo(null);
        setResizable(false);
        setUndecorated(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(false);
    }
    
    private void configView() {
        setLayout(null);
        
        lblID = new JLabel();
        txtID = new JLabel();
        lblDesignacao = new JLabel();
        lblMarca = new JLabel();
        lblCategoria = new JLabel();
        lblUnidade = new JLabel();
        lblQuantidade = new JLabel();
        lblPreco = new JLabel();
        lblPrecousd = new JLabel();
        
        txtDesignacao = new JTextField();
        txtMarca = new JTextField();
        txtUnidade = new JTextField();
        txtPreco = new JTextField();
        txtPrecousd = new JTextField();
        
        SpinnerModel modelS = new SpinnerNumberModel(0, 0, 500, 1);
        txtQuantidade = new JSpinner(modelS);
        txtQuantidade.addChangeListener((ChangeEvent evt) -> {
        });
        
        txtCategoria();
        
        kg = new JRadioButton();
        pecas = new JRadioButton();
        
        listaDesignacao = new JList();
        scroll_listaDesignacao = new JScrollPane();
        listaMarca = new JList();
        scroll_listaMarca = new JScrollPane();
        
        btnSalvar = new JButton();
        btnCancelar = new JButton();
                
        lblID.setFont(new Font("Times New Roman", 1, 16));
        lblID.setForeground(Color.black);
        lblID.setBounds(10, 5, 50, 28);
        
        txtID.setText(null);
        txtID.setFont(new Font("Times New Roman", 1, 16));
        txtID.setForeground(Color.red);
        txtID.setBounds(100, 5, 100, 28);
        
        lblDesignacao.setFont(new Font("Times New Roman", 1, 16));
        lblDesignacao.setForeground(Color.BLACK);
        lblDesignacao.setBounds(10, 38, 110, 28);
        
        txtDesignacao.setText(null);
        txtDesignacao.setFont(new Font("Times New Roman", 0, 15));
        txtDesignacao.setForeground(Color.BLACK);
        txtDesignacao.setBounds(100, 38, 437, 28);
        
        listaDesignacao.setFont(new Font("Times New Roman", 0, 15));
        listaDesignacao.setForeground(Color.BLACK);
        listaDesignacao.setBounds(100, 65, 437, 125);
        listaDesignacao.setBorder(null);
        
        scroll_listaDesignacao.setViewportView(listaDesignacao);
        scroll_listaDesignacao.setBounds(100, 65, 437, 125);
        scroll_listaDesignacao.setVisible(false);   
        scroll_listaDesignacao.setBorder(null);
        
        lblMarca.setFont(new Font("Times New Roman", 1, 16));
        lblMarca.setForeground(Color.BLACK);
        lblMarca.setBounds(10, 71, 110, 28);
        
        txtMarca.setText(null);
        txtMarca.setFont(new Font("Times New Roman", 0, 15));
        txtMarca.setForeground(Color.BLACK);
        txtMarca.setBounds(100, 71, 135, 28);
        
        listaMarca.setFont(new Font("Times New Roman", 0, 15));
        listaMarca.setForeground(Color.BLACK);
        listaMarca.setBounds(100, 98, 135, 70);
        listaMarca.setBorder(null);
        
        scroll_listaMarca.setViewportView(listaMarca);
        scroll_listaMarca.setBounds(100, 98, 135, 70);
        scroll_listaMarca.setVisible(false);   
        scroll_listaMarca.setBorder(null);
        
        lblCategoria.setFont(new Font("Times New Roman", 1, 16));
        lblCategoria.setForeground(Color.BLACK);
        lblCategoria.setBounds(290, 71, 110, 28);
        
        txtCategoria.setSelectedItem(null);
        txtCategoria.setFont(new Font("Times New Roman", 0, 15));
        txtCategoria.setForeground(Color.BLACK);
        txtCategoria.setBounds(376, 71, 135, 28);
        
        lblUnidade.setFont(new Font("Times New Roman", 1, 16));
        lblUnidade.setForeground(Color.BLACK);
        lblUnidade.setBounds(10, 104, 110, 28);
        
        txtUnidade.setText(null);
        txtUnidade.setFont(new Font("Times New Roman", 0, 15));
        txtUnidade.setForeground(Color.BLACK);
        txtUnidade.setBounds(100, 104, 135, 28);
        
        kg.setFont(new Font("Times New Roman", 0, 15));
        kg.setForeground(Color.BLACK);
        kg.setBounds(235, 104, 40, 28);
        
        pecas.setFont(new Font("Times New Roman", 0, 15));
        pecas.setForeground(Color.BLACK);
        pecas.setBounds(286, 104, 80, 28);
                
        lblQuantidade.setFont(new Font("Times New Roman", 1, 16));
        lblQuantidade.setForeground(Color.BLACK);
        lblQuantidade.setBounds(10, 137, 110, 28);
                
        txtQuantidade.setFont(new Font("Times New Roman", 0, 15));
        txtQuantidade.setForeground(Color.BLACK);
        txtQuantidade.setBounds(100, 137, 135, 28);
        
        lblPreco.setFont(new Font("Times New Roman", 1, 16));
        lblPreco.setForeground(Color.BLACK);
        lblPreco.setBounds(10, 170, 110, 28);
                
        txtPreco.setFont(new Font("Times New Roman", 0, 15));
        txtPreco.setForeground(Color.BLACK);
        txtPreco.setBounds(100, 170, 135, 28);
        
        lblPrecousd.setFont(new Font("Times New Roman", 1, 16));
        lblPrecousd.setForeground(Color.BLACK);
        lblPrecousd.setBounds(290, 170, 110, 28);
                
        txtPrecousd.setFont(new Font("Times New Roman", 0, 15));
        txtPrecousd.setForeground(Color.BLACK);
        txtPrecousd.setBounds(376, 170, 135, 28);
        
        btnSalvar.setBounds(10, 265, 150, 30);
        btnSalvar.setOpaque(false);
        
        btnCancelar.setBounds(170, 265, 150, 30);
        btnCancelar.setOpaque(false);
        
        configTools();
        
        getContentPane().add(lblID);
        getContentPane().add(txtID);
        getContentPane().add(lblDesignacao);
        getContentPane().add(txtDesignacao);
        getContentPane().add(scroll_listaDesignacao);
        getContentPane().add(lblMarca);
        getContentPane().add(txtMarca);
        getContentPane().add(scroll_listaMarca);
        getContentPane().add(lblCategoria);
        getContentPane().add(txtCategoria);
        getContentPane().add(lblUnidade);
        getContentPane().add(txtUnidade);
        getContentPane().add(kg);
        getContentPane().add(pecas);
        getContentPane().add(lblQuantidade);
        getContentPane().add(txtQuantidade);
        getContentPane().add(lblPreco);
        getContentPane().add(txtPreco);
        getContentPane().add(lblPrecousd);
        getContentPane().add(txtPrecousd);
        getContentPane().add(btnSalvar);
        getContentPane().add(btnCancelar);
    }
    
    private void configTools() {
        btnSalvar.addActionListener(this);
        btnCancelar.addActionListener(this);
        kg.addActionListener(this);
        pecas.addActionListener(this); 
        
        txtDesignacao.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                configListarDesignacao();
            }
        });
        
        listaDesignacao.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                model = (DefaultListModel) listaDesignacao.getModel();
                int selected = listaDesignacao.getSelectedIndex();
                txtDesignacao.setText((String) model.getElementAt(selected));
                scroll_listaDesignacao.setVisible(false);
            }
        });
        
        txtMarca.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                configListarMarca();
            }
        });
        
        listaMarca.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                model = (DefaultListModel) listaMarca.getModel();
                int selected = listaMarca.getSelectedIndex();
                txtMarca.setText((String) model.getElementAt(selected));
                scroll_listaMarca.setVisible(false);
            }
        });
    }
    
    private void configListarDesignacao() {
        DefaultListModel<String> modelo = new DefaultListModel<>();
        listaDesignacao.setModel(modelo);
        String sql = "select * from tbloja_3 where designação like '%" + txtDesignacao.getText() + "%'";
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            
            boolean encontrou = false;

            while (rs.next()) {
                modelo.addElement(rs.getString(2));
                encontrou = true;
            }

            if (encontrou || txtDesignacao.getText().isEmpty()) {
                scroll_listaDesignacao.setVisible(true);
            } else {
                scroll_listaDesignacao.setVisible(false);
            }
            
            if(txtDesignacao.getText().isEmpty()) {
                scroll_listaDesignacao.setVisible(false);
            } else {
                txtDesignacao.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent evt) {
                        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
                            scroll_listaDesignacao.setVisible(false);
                        }
                    }
                });
            }
        } catch(SQLException erro) {
            try {
                this.pst.close();
                this.rs.close();
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Erro no configListarDesignacao() - PRODUTO_EDD_LOJA_3 \n" + erro, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "Error in configListarDesignacao() - PRODUCT_EDD_SHOP_3 \n" + erro, "Attention", 0);
                }
            } catch (SQLException ex) {
                Logger.getLogger(TelaEdd_Produto_Loja3.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void configListarMarca() {
        DefaultListModel<String> modelo = new DefaultListModel<>();
        listaMarca.setModel(modelo);
        String sql = "select * from tbmarca where marca like '%" + txtMarca.getText() + "%'";
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            
            boolean encontrou = false;

            while (rs.next()) {
                modelo.addElement(rs.getString(2));
                encontrou = true;
            }

            if (encontrou || txtMarca.getText().isEmpty()) {
                scroll_listaMarca.setVisible(true);
            } else {
                scroll_listaMarca.setVisible(false);
            }
            
            if(txtMarca.getText().isEmpty()) {
                scroll_listaMarca.setVisible(false);
            }
        } catch(SQLException erro) {
            try {
                this.pst.close();
                this.rs.close();
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Erro no configListarMarca() PRODUTO_EDD_LOJA_3 \n" + erro, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "Error in configListarMarca()- PRODUCT_EDD_SHOP_3 \n" + erro, "Attention", 0);
                }
            } catch (SQLException ex) {
                Logger.getLogger(TelaEdd_Produto_Loja3.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if(evt.getSource() == btnSalvar) {
            update();            
        } else if(evt.getSource() == btnCancelar) {
            txtDesignacao.setText(null);
            txtMarca.setText(null);
            txtCategoria.setSelectedItem(null);
            txtUnidade.setText(null);
            txtQuantidade.setValue(0);
            txtPreco.setText(null);
            txtPrecousd.setText(null);
            scroll_listaDesignacao.setVisible(false);
            scroll_listaMarca.setVisible(false);
        } else if(evt.getSource() == kg) {
            pecas.setSelected(false);
            kg.setSelected(true);
            if("1".equals(txtUnidade.getText())) {
                txtUnidade.setText(txtUnidade.getText() + " kg");
            } else {
                txtUnidade.setText(txtUnidade.getText() + " kgs");
            }
        } else if(evt.getSource() == pecas) {
            kg.setSelected(false);
            pecas.setSelected(true);
            if("1".equals(txtUnidade.getText())) {
                txtUnidade.setText(txtUnidade.getText() + "peça");
            } else {
                txtUnidade.setText(txtUnidade.getText() + " peças");
            }            
        }
    }
    
    private void txtCategoria() {
        String sql = "select CATEGORIA from tbcategoria";
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();

            DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>();

            while (rs.next()) {
                String nome = rs.getString("CATEGORIA");
                comboBoxModel.addElement(nome);
            }
            
            txtCategoria = new JComboBox<>(comboBoxModel);
            
            } catch (SQLException err) {
            try {
                this.pst.close();
                this.rs.close();
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Erro no txtCategoria() - PRODUTO_EDD_LOJA_3.\n" + err.getMessage());
                } else {
                    JOptionPane.showMessageDialog(null, "Error in txtCategoria() - PRODUCT_EDD_SHOP_3.\n" + err.getMessage());
                }
            } catch (SQLException ex) {
                Logger.getLogger(TelaEdd_Produto_Loja3.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
    }
    
    private void update() {
        String sql = "update tbloja_3 set designação = ?, marca = ?, categoria = ?, unidade = ?, quantidade = ?, preco_artigo_mzn = ?, preco_artigo_usd = ? where id = ?";
        
        try {
            pst = conexao.prepareStatement(sql);
                
            pst.setString(1, txtDesignacao.getText());
            pst.setString(2, txtMarca.getText());
            pst.setString(3, (String) txtCategoria.getSelectedItem());
            pst.setString(4, txtUnidade.getText());
            pst.setString(5, txtQuantidade.getValue().toString().trim());
            pst.setString(6, txtPreco.getText());
            pst.setString(7, txtPrecousd.getText());
            pst.setString(8, txtID.getText());
                
            if((txtDesignacao.getText().isEmpty()) & (txtCategoria.getSelectedItem() == null) & (txtQuantidade.getValue() == null) & (txtPreco.getText().isEmpty()) & (txtPrecousd.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblDesignacao.setForeground(Color.red);
                lblCategoria.setForeground(Color.red);
                lblQuantidade.setForeground(Color.red);
                lblPreco.setForeground(Color.red);
                lblPrecousd.setForeground(Color.red);
            } else if((txtDesignacao.getText().isEmpty()) & (txtCategoria.getSelectedItem() == null) & (txtQuantidade.getValue() == null) & (txtPreco.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblDesignacao.setForeground(Color.red);
                lblCategoria.setForeground(Color.red);
                lblQuantidade.setForeground(Color.red);
                lblPreco.setForeground(Color.red);
            } else if((txtDesignacao.getText().isEmpty()) & (txtCategoria.getSelectedItem() == null) & (txtQuantidade.getValue() == null) & (txtPrecousd.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblDesignacao.setForeground(Color.red);
                lblCategoria.setForeground(Color.red);
                lblQuantidade.setForeground(Color.red);
                lblPrecousd.setForeground(Color.red);
            } else if((txtDesignacao.getText().isEmpty()) & (txtQuantidade.getValue() == null) & (txtPreco.getText().isEmpty()) & (txtPrecousd.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblDesignacao.setForeground(Color.red);
                lblQuantidade.setForeground(Color.red);
                lblPreco.setForeground(Color.red);
                lblPrecousd.setForeground(Color.red);
            } else if((txtDesignacao.getText().isEmpty()) & (txtCategoria.getSelectedItem() == null) & (txtPreco.getText().isEmpty()) & (txtPrecousd.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblDesignacao.setForeground(Color.red);
                lblCategoria.setForeground(Color.red);
                lblPreco.setForeground(Color.red);
                lblPrecousd.setForeground(Color.red);
            } else if((txtCategoria.getSelectedItem() == null) & (txtQuantidade.getValue() == null) & (txtPreco.getText().isEmpty()) & (txtPrecousd.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblCategoria.setForeground(Color.red);
                lblQuantidade.setForeground(Color.red);
                lblPreco.setForeground(Color.red);
                lblPrecousd.setForeground(Color.red);
            } else if((txtDesignacao.getText().isEmpty()) & (txtCategoria.getSelectedItem() == null) & (txtQuantidade.getValue() == null)) {
                if(Idiomas.getPort() == true) {
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblDesignacao.setForeground(Color.red);
                lblCategoria.setForeground(Color.red);
                lblQuantidade.setForeground(Color.red);
            } else if((txtDesignacao.getText().isEmpty()) & (txtCategoria.getSelectedItem() == null) & (txtPreco.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblDesignacao.setForeground(Color.red);
                lblCategoria.setForeground(Color.red);
                lblPreco.setForeground(Color.red);
            } else if((txtDesignacao.getText().isEmpty()) & (txtCategoria.getSelectedItem() == null) & (txtPrecousd.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblDesignacao.setForeground(Color.red);
                lblCategoria.setForeground(Color.red);
                lblPrecousd.setForeground(Color.red);
            } else if((txtDesignacao.getText().isEmpty()) & (txtQuantidade.getValue() == null) & (txtPreco.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblDesignacao.setForeground(Color.red);
                lblQuantidade.setForeground(Color.red);
                lblPreco.setForeground(Color.red);
            } else if((txtDesignacao.getText().isEmpty()) & (txtQuantidade.getValue() == null) & (txtPrecousd.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblDesignacao.setForeground(Color.red);
                lblQuantidade.setForeground(Color.red);
                lblPrecousd.setForeground(Color.red);
            } else if((txtDesignacao.getText().isEmpty()) & (txtPreco.getText().isEmpty()) & (txtPrecousd.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblDesignacao.setForeground(Color.red);
                lblPreco.setForeground(Color.red);
                lblPrecousd.setForeground(Color.red);
            } else if((txtCategoria.getSelectedItem() == null) & (txtQuantidade.getValue() == null) & (txtPreco.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblCategoria.setForeground(Color.red);
                lblQuantidade.setForeground(Color.red);
                lblPreco.setForeground(Color.red);
            } else if((txtCategoria.getSelectedItem() == null) & (txtQuantidade.getValue() == null) & (txtPrecousd.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblCategoria.setForeground(Color.red);
                lblQuantidade.setForeground(Color.red);
                lblPrecousd.setForeground(Color.red);
            } else if((txtCategoria.getSelectedItem() == null) & (txtPreco.getText().isEmpty()) & (txtPrecousd.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblCategoria.setForeground(Color.red);
                lblPreco.setForeground(Color.red);
                lblPrecousd.setForeground(Color.red);
            } else if((txtQuantidade.getValue() == null) & (txtPreco.getText().isEmpty()) & (txtPrecousd.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblQuantidade.setForeground(Color.red);
                lblPreco.setForeground(Color.red);
                lblPrecousd.setForeground(Color.red);
            } else if((txtDesignacao.getText().isEmpty()) & (txtCategoria.getSelectedItem() == null)) {
                if(Idiomas.getPort() == true) {
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblDesignacao.setForeground(Color.red);
                lblCategoria.setForeground(Color.red);
            } else if((txtDesignacao.getText().isEmpty()) & (txtQuantidade.getValue() == null)) {
                if(Idiomas.getPort() == true) {
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblDesignacao.setForeground(Color.red);
                lblQuantidade.setForeground(Color.red);
            } else if((txtDesignacao.getText().isEmpty()) & (txtPreco.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblDesignacao.setForeground(Color.red);
                lblPreco.setForeground(Color.red);
            } else if((txtDesignacao.getText().isEmpty()) & (txtPrecousd.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblDesignacao.setForeground(Color.red);
                lblPrecousd.setForeground(Color.red);
            } else if((txtCategoria.getSelectedItem() == null) & (txtQuantidade.getValue() == null)) {
                if(Idiomas.getPort() == true) {
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblCategoria.setForeground(Color.red);
                lblQuantidade.setForeground(Color.red);
            } else if((txtCategoria.getSelectedItem() == null) & (txtPreco.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblCategoria.setForeground(Color.red);
                lblPreco.setForeground(Color.red);
            } else if((txtCategoria.getSelectedItem() == null) & (txtPrecousd.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblCategoria.setForeground(Color.red);
                lblPrecousd.setForeground(Color.red);
            } else if((txtQuantidade.getValue() == null) & (txtPreco.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblQuantidade.setForeground(Color.red);
                lblPreco.setForeground(Color.red);
            } else if((txtQuantidade.getValue() == null) & (txtPrecousd.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblQuantidade.setForeground(Color.red);
                lblPrecousd.setForeground(Color.red);
            } else if((txtPreco.getText().isEmpty()) & (txtPrecousd.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblPreco.setForeground(Color.red);
                lblPrecousd.setForeground(Color.red);
            } else if(txtDesignacao.getText().isEmpty()) {
                if(Idiomas.getPort() == true) {
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblDesignacao.setForeground(Color.red);
            } else if(txtCategoria.getSelectedItem() == null) {
                if(Idiomas.getPort() == true) {
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblCategoria.setForeground(Color.red);
            } else if(txtQuantidade.getValue() == null) {
                if(Idiomas.getPort() == true) {
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblQuantidade.setForeground(Color.red);
            } else if(txtPreco.getText().isEmpty()) {
                if(Idiomas.getPort() == true) {
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblPreco.setForeground(Color.red);
            } else if(txtPrecousd.getText().isEmpty()) {
                if(Idiomas.getPort() == true) {
                JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblPrecousd.setForeground(Color.red);
            } else {
                adicionado = pst.executeUpdate();
                
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso !", "Atenção", 1);
                    conexao.close();
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Product updated successfully !", "Attention", 1);
                    conexao.close();
                    this.dispose();
                }                
            }
                
                    if((!txtDesignacao.getText().isEmpty()) & (txtCategoria.getSelectedItem() != null) & (txtQuantidade.getValue() != null) & (!txtPreco.getText().isEmpty()) & (!txtPrecousd.getText().isEmpty())) {
                        lblDesignacao.setForeground(Color.black);
                        lblCategoria.setForeground(Color.black);
                        lblQuantidade.setForeground(Color.black);
                        lblPreco.setForeground(Color.black);
                        lblPrecousd.setForeground(Color.black);
                    } else if((!txtDesignacao.getText().isEmpty()) & (txtCategoria.getSelectedItem() != null) & (txtQuantidade.getValue() != null) & (!txtPreco.getText().isEmpty())) {
                        lblDesignacao.setForeground(Color.black);
                        lblCategoria.setForeground(Color.black);
                        lblQuantidade.setForeground(Color.black);
                        lblPreco.setForeground(Color.black);
                    } else if((!txtDesignacao.getText().isEmpty()) & (txtCategoria.getSelectedItem() != null) & (txtQuantidade.getValue() != null) & (!txtPrecousd.getText().isEmpty())) {
                        lblDesignacao.setForeground(Color.black);
                        lblCategoria.setForeground(Color.black);
                        lblQuantidade.setForeground(Color.black);
                        lblPrecousd.setForeground(Color.black);
                    } else if((!txtDesignacao.getText().isEmpty()) & (txtQuantidade.getValue() != null) & (!txtPreco.getText().isEmpty()) & (!txtPrecousd.getText().isEmpty())) {
                        lblDesignacao.setForeground(Color.black);
                        lblQuantidade.setForeground(Color.black);
                        lblPreco.setForeground(Color.black);
                        lblPrecousd.setForeground(Color.black);
                    } else if((!txtDesignacao.getText().isEmpty()) & (txtCategoria.getSelectedItem() != null) & (!txtPreco.getText().isEmpty()) & (!txtPrecousd.getText().isEmpty())) {
                        lblDesignacao.setForeground(Color.black);
                        lblCategoria.setForeground(Color.black);
                        lblPreco.setForeground(Color.black);
                        lblPrecousd.setForeground(Color.black);
                    } else if((txtCategoria.getSelectedItem() != null) & (txtQuantidade.getValue() != null) & (!txtPreco.getText().isEmpty()) & (!txtPrecousd.getText().isEmpty())) {
                        lblCategoria.setForeground(Color.black);
                        lblQuantidade.setForeground(Color.black);
                        lblPreco.setForeground(Color.black);
                        lblPrecousd.setForeground(Color.black);
                    } else if((!txtDesignacao.getText().isEmpty()) & (txtCategoria.getSelectedItem() != null) & (txtQuantidade.getValue() != null)) {
                        lblDesignacao.setForeground(Color.black);
                        lblCategoria.setForeground(Color.black);
                        lblQuantidade.setForeground(Color.black);
                    } else if((!txtDesignacao.getText().isEmpty()) & (txtCategoria.getSelectedItem() != null) & (!txtPreco.getText().isEmpty())) {
                        lblDesignacao.setForeground(Color.black);
                        lblCategoria.setForeground(Color.black);
                        lblPreco.setForeground(Color.black);
                    } else if((!txtDesignacao.getText().isEmpty()) & (txtCategoria.getSelectedItem() != null) & (!txtPrecousd.getText().isEmpty())) {
                        lblDesignacao.setForeground(Color.black);
                        lblCategoria.setForeground(Color.black);
                        lblPrecousd.setForeground(Color.black);
                    } else if((!txtDesignacao.getText().isEmpty()) & (txtQuantidade.getValue() != null) & (!txtPreco.getText().isEmpty())) {
                        lblDesignacao.setForeground(Color.black);
                        lblQuantidade.setForeground(Color.black);
                        lblPreco.setForeground(Color.black);
                    } else if((!txtDesignacao.getText().isEmpty()) & (txtQuantidade.getValue() != null) & (!txtPrecousd.getText().isEmpty())) {
                        lblDesignacao.setForeground(Color.black);
                        lblQuantidade.setForeground(Color.black);
                        lblPrecousd.setForeground(Color.black);
                    } else if((!txtDesignacao.getText().isEmpty()) & (!txtPreco.getText().isEmpty()) & (!txtPrecousd.getText().isEmpty())) {
                        lblDesignacao.setForeground(Color.black);
                        lblPreco.setForeground(Color.black);
                        lblPrecousd.setForeground(Color.black);
                    } else if((txtCategoria.getSelectedItem() != null) & (txtQuantidade.getValue() != null) & (!txtPreco.getText().isEmpty())) {
                        lblCategoria.setForeground(Color.black);
                        lblQuantidade.setForeground(Color.black);
                        lblPreco.setForeground(Color.black);
                    } else if((txtCategoria.getSelectedItem() != null) & (txtQuantidade.getValue() != null) & (!txtPrecousd.getText().isEmpty())) {
                        lblCategoria.setForeground(Color.black);
                        lblQuantidade.setForeground(Color.black);
                        lblPrecousd.setForeground(Color.black);
                    } else if((txtCategoria.getSelectedItem() != null) & (!txtPreco.getText().isEmpty()) & (!txtPrecousd.getText().isEmpty())) {
                        lblCategoria.setForeground(Color.black);
                        lblPreco.setForeground(Color.black);
                        lblPrecousd.setForeground(Color.black);
                    } else if((txtQuantidade.getValue() != null) & (!txtPreco.getText().isEmpty()) & (!txtPrecousd.getText().isEmpty())) {
                        lblQuantidade.setForeground(Color.black);
                        lblPreco.setForeground(Color.black);
                        lblPrecousd.setForeground(Color.black);
                    } else if((!txtDesignacao.getText().isEmpty()) & (txtCategoria.getSelectedItem() != null)) {
                        lblDesignacao.setForeground(Color.black);
                        lblCategoria.setForeground(Color.black);
                    } else if((!txtDesignacao.getText().isEmpty()) & (txtQuantidade.getValue() != null)) {
                        lblDesignacao.setForeground(Color.black);
                        lblQuantidade.setForeground(Color.black);
                    } else if((!txtDesignacao.getText().isEmpty()) & (!txtPreco.getText().isEmpty())) {
                        lblDesignacao.setForeground(Color.black);
                        lblPreco.setForeground(Color.black);
                    } else if((!txtDesignacao.getText().isEmpty()) & (!txtPrecousd.getText().isEmpty())) {
                        lblDesignacao.setForeground(Color.black);
                        lblPrecousd.setForeground(Color.black);
                    } else if((txtCategoria.getSelectedItem() != null) & (txtQuantidade.getValue() != null)) {
                        lblCategoria.setForeground(Color.black);
                        lblQuantidade.setForeground(Color.black);
                    } else if((txtCategoria.getSelectedItem() != null) & (!txtPreco.getText().isEmpty())) {
                        lblCategoria.setForeground(Color.black);
                        lblPreco.setForeground(Color.black);
                    } else if((txtCategoria.getSelectedItem() != null) & (!txtPrecousd.getText().isEmpty())) {
                        lblCategoria.setForeground(Color.black);
                        lblPrecousd.setForeground(Color.black);
                    } else if((txtQuantidade.getValue() != null) & (!txtPreco.getText().isEmpty())) {
                        lblQuantidade.setForeground(Color.black);
                        lblPreco.setForeground(Color.black);
                    } else if((txtQuantidade.getValue() != null) & (!txtPrecousd.getText().isEmpty())) {
                        lblQuantidade.setForeground(Color.black);
                        lblPrecousd.setForeground(Color.black);
                    } else if((!txtPreco.getText().isEmpty()) & (!txtPrecousd.getText().isEmpty())) {
                        lblPreco.setForeground(Color.black);
                        lblPrecousd.setForeground(Color.black);
                    } else if(!txtDesignacao.getText().isEmpty()) {
                        lblDesignacao.setForeground(Color.black);
                    } else if(txtCategoria.getSelectedItem() != null) {
                        lblCategoria.setForeground(Color.black);
                    } else if(txtQuantidade.getValue() != null) {
                        lblQuantidade.setForeground(Color.black);
                    } else if(!txtPreco.getText().isEmpty()) {
                        lblPreco.setForeground(Color.black);
                    } else if(!txtPrecousd.getText().isEmpty()) {
                        lblPrecousd.setForeground(Color.black);
                    }
                    
                        if (adicionado > 0) {
                            txtID.setText(null);
                            txtDesignacao.setText(null);
                            txtMarca.setText(null);
                            txtCategoria.setSelectedItem(null);
                            txtUnidade.setText(null);
                            txtQuantidade.setValue(0);
                            txtPreco.setText(null);
                            txtPrecousd.setText(null);
                        }
            
        } catch (HeadlessException | SQLException erro) {
            try {
                this.pst.close();
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, erro+" MENSAGEM DE ERRO AO TENTAR ATUALIZAR - TelaEdd_Produto_Loja3 !", "AVISO", 0);
                } else {
                    JOptionPane.showMessageDialog(null, erro+" ERROR MESSAGE WHEN TRYING TO UPDATE - TelaEdd_Produto_Loja3 !", "WARNING", 0);
                }
            } catch (SQLException ex) {
                Logger.getLogger(TelaEdd_Produto_Loja3.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }           
    
    @Override
    public void configPort() {
        lblID.setText("ID");
        lblDesignacao.setText("Designação");
        lblMarca.setText("Marca");
        lblCategoria.setText("Categoria");
        lblUnidade.setText("Unidade");
        kg.setText("kg");
        pecas.setText("peças");        
        lblQuantidade.setText("Quantidade");
        lblPreco.setText("Preço(MZN)");
        lblPrecousd.setText("Preço(USD)");
        btnSalvar.setText("Salvar");
        btnSalvar.setFont(new Font("Agency FB", 1, 20));
        btnSalvar.setForeground(Color.BLACK);
        btnCancelar.setText("Cancelar");
        btnCancelar.setFont(new Font("Agency FB", 1, 20));
        btnCancelar.setForeground(Color.BLACK);
    }

    @Override
    public void configEng() {
        lblID.setText("ID");
        lblDesignacao.setText("Designation");
        lblMarca.setText("Code");
        lblCategoria.setText("Category");
        lblUnidade.setText("Unit");
        kg.setText("kg");
        pecas.setText("peças");        
        lblQuantidade.setText("Quantity");
        lblPreco.setText("Price(MZN)");
        lblPreco.setText("Price(USD)");
        btnSalvar.setText("Save");
        btnSalvar.setFont(new Font("Agency FB", 1, 20));
        btnSalvar.setForeground(Color.BLACK);
        btnCancelar.setText("Cancel");
        btnCancelar.setFont(new Font("Agency FB", 1, 20));
        btnCancelar.setForeground(Color.BLACK);
    }
}