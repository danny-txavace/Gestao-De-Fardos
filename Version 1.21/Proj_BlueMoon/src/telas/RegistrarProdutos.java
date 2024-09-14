package telas;

import Abstrato.Config_idiomas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.sql.*;
import dao.ConexaoDAO;
import javax.swing.border.MatteBorder;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import net.proteanit.sql.DbUtils;

/**
 * @author Ramadan ismaeL
 */

public final class RegistrarProdutos extends JFrame implements ActionListener, Config_idiomas {
    private JLabel lblDesignacao, lblMarca, lblCategoria, lblUnidade, lblQuantidade, lblPreco, lblPrecousd;
    private JTextField txtDesignacao, txtMarca, txtUnidade, txtPreco, txtPrecousd;
    private JComboBox txtCategoria;
    private JSpinner txtQuantidade;
    private JRadioButton kg, pecas;
    private JButton btnSalvar, btnSalvar2, btnCancelar, btnMarca, btnCategoria;
    private JList listaDesignacao, listaMarca;
    private JScrollPane scroll_listaDesignacao, scroll_listaMarca, scroll;
    private DefaultTableModel modelT;
    private JTable tabela;
    private int adicionado;
    private Connection conexao = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private DefaultListModel model;
    private TelaMarca telamarca = null;
    private TelaCategoria telacategoria;
    
    public RegistrarProdutos() throws SQLException {
        conexao = ConexaoDAO.conector();
        
        Janela();
        configView();
        tabelaDAO();
        
        if(Idiomas.getPort() == true) {
            configPort();
        } else {
            configEng();
        }
    }
    
    private void Janela() {        
        if(Idiomas.getPort() == true) {
            setTitle("REGISTRO  DE  PRODUTOS");
        } else {
            setTitle("PRODUCTS REGISTER");
        }
        setSize(562, 590);        
        setLocationRelativeTo(null);
        setResizable(false);
        setUndecorated(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(false);
    }
    
    private void configView() {
        setLayout(null);
        
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
        
        SpinnerModel modelS = new SpinnerNumberModel(0, 0, 21000, 1);
        txtQuantidade = new JSpinner(modelS);
        
        txtCategoria = new JComboBox();
        txtCategoria();
        
        kg = new JRadioButton();
        pecas = new JRadioButton();
        
        listaDesignacao = new JList();
        scroll_listaDesignacao = new JScrollPane();
        listaMarca = new JList();
        scroll_listaMarca = new JScrollPane();
        scroll = new JScrollPane();
        
        btnMarca = new JButton();
        btnCategoria = new JButton();
        btnSalvar = new JButton();
        btnSalvar2 = new JButton();
        btnCancelar = new JButton();
                
        modelT = new DefaultTableModel();
        tabela = new JTable(modelT);
        
        lblDesignacao.setFont(new Font("Times New Roman", 1, 16));
        lblDesignacao.setForeground(Color.BLACK);
        lblDesignacao.setBounds(10, 5, 110, 28);
        
        txtDesignacao.setText(null);
        txtDesignacao.setFont(new Font("Times New Roman", 0, 15));
        txtDesignacao.setForeground(Color.BLACK);
        txtDesignacao.setBounds(100, 5, 437, 28);
        txtDesignacao.setBorder(new MatteBorder(2, 2, 0, 0, Color.GRAY));
                
        listaDesignacao.setFont(new Font("Times New Roman", 0, 15));
        listaDesignacao.setForeground(Color.BLACK);
        listaDesignacao.setBounds(100, 32, 437, 125);
        listaDesignacao.setBorder(null);
        
        scroll_listaDesignacao.setViewportView(listaDesignacao);
        scroll_listaDesignacao.setBounds(100, 32, 437, 125);
        scroll_listaDesignacao.setVisible(false);   
        scroll_listaDesignacao.setBorder(null);
        
        lblMarca.setFont(new Font("Times New Roman", 1, 16));
        lblMarca.setForeground(Color.BLACK);
        lblMarca.setBounds(10, 38, 110, 28);
        
        txtMarca.setText(null);
        txtMarca.setFont(new Font("Times New Roman", 0, 15));
        txtMarca.setForeground(Color.BLACK);
        txtMarca.setBounds(100, 38, 135, 28);
        
        listaMarca.setFont(new Font("Times New Roman", 0, 15));
        listaMarca.setForeground(Color.BLACK);
        listaMarca.setBounds(100, 66, 135, 70);
        listaMarca.setBorder(null);
        
        scroll_listaMarca.setViewportView(listaMarca);
        scroll_listaMarca.setBounds(100, 66, 135, 70);
        scroll_listaMarca.setVisible(false);   
        scroll_listaMarca.setBorder(null);
        
        btnMarca.setIcon(new ImageIcon(this.getClass().getResource("/icones/adicionar-categ.png")));
        btnMarca.setBounds(235, 38, 30, 27);
        btnMarca.setOpaque(false); //Torna o botão visível ou transparente
        btnMarca.setBackground(new Color(135, 206, 250));
        btnMarca.setBorder(new MatteBorder(0, 0, 0, 0, Color.WHITE));
        
        lblCategoria.setFont(new Font("Times New Roman", 1, 16));
        lblCategoria.setForeground(Color.BLACK);
        lblCategoria.setBounds(290, 38, 110, 28);
        
        txtCategoria.setSelectedItem(null);
        txtCategoria.setFont(new Font("Times New Roman", 0, 15));
        txtCategoria.setForeground(Color.BLACK);
        txtCategoria.setBounds(376, 38, 135, 28);
        
        btnCategoria.setIcon(new ImageIcon(this.getClass().getResource("/icones/adicionar-categ.png")));
        btnCategoria.setBounds(510, 38, 30, 27);
        btnCategoria.setOpaque(false);
        btnCategoria.setBackground(new Color(135, 206, 250));
        btnCategoria.setBorder(new MatteBorder(0, 0, 0, 0, Color.WHITE));
        
        lblUnidade.setFont(new Font("Times New Roman", 1, 16));
        lblUnidade.setForeground(Color.BLACK);
        lblUnidade.setBounds(10, 71, 110, 28);
        
        txtUnidade.setText(null);
        txtUnidade.setFont(new Font("Times New Roman", 0, 15));
        txtUnidade.setForeground(Color.BLACK);
        txtUnidade.setBounds(100, 71, 135, 28);
        
        kg.setFont(new Font("Times New Roman", 0, 15));
        kg.setForeground(Color.BLACK);
        kg.setBounds(235, 71, 40, 28);
        
        pecas.setFont(new Font("Times New Roman", 0, 15));
        pecas.setForeground(Color.BLACK);
        pecas.setBounds(286, 71, 80, 28);
        
        lblQuantidade.setFont(new Font("Times New Roman", 1, 16));
        lblQuantidade.setForeground(Color.BLACK);
        lblQuantidade.setBounds(10, 109, 110, 28);
                
        txtQuantidade.setFont(new Font("Times New Roman", 0, 15));
        txtQuantidade.setForeground(Color.BLACK);
        txtQuantidade.setBounds(100, 109, 135, 28);
        txtQuantidade.setEnabled(false);
        
        lblPreco.setFont(new Font("Times New Roman", 1, 16));
        lblPreco.setForeground(Color.BLACK);
        lblPreco.setBounds(10, 147, 110, 28);
                        
        txtPreco.setFont(new Font("Times New Roman", 0, 15));
        txtPreco.setForeground(Color.BLACK);
        txtPreco.setBounds(100, 147, 135, 28); 
                
        lblPrecousd.setFont(new Font("Times New Roman", 1, 16));
        lblPrecousd.setForeground(Color.BLACK);
        lblPrecousd.setBounds(290, 147, 110, 28);
                
        txtPrecousd.setFont(new Font("Times New Roman", 0, 15));
        txtPrecousd.setForeground(Color.BLACK);
        txtPrecousd.setBounds(376, 147, 135, 28);
        
        btnSalvar.setBounds(10, 215, 150, 30);
        btnSalvar.setOpaque(false);
        
        btnSalvar2.setBounds(389, 215, 150, 30);
        btnSalvar2.setOpaque(false);
        
        btnCancelar.setBounds(167, 215, 150, 30);
        btnCancelar.setOpaque(false);
        
        if(Idiomas.getPort() == true) {
            String[] nome = {"ID", "DESIGNAÇÃO", "MARCA", "CATEGORIA", "PREÇO(MZN)", "PREÇO(USD)"};
            String dados[][] = {};        
            tabela = new JTable(dados, nome) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        } else {
            String[] nome = {"ID", "ITEM DESCRIPTION", "CODE", "CATEGORY", "PRICE(MZN)", "PRICE(USD)"};
            String dados[][] = {};        
            tabela = new JTable(dados, nome) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        }       
               
        scroll.setViewportView(tabela);
        scroll.setBounds(10, 255, 530, 290);
        
        configTools();
        
        getContentPane().add(lblDesignacao);
        getContentPane().add(txtDesignacao);
        getContentPane().add(scroll_listaDesignacao);
        getContentPane().add(lblMarca);
        getContentPane().add(txtMarca);
        getContentPane().add(scroll_listaMarca);
        getContentPane().add(btnMarca);
        getContentPane().add(lblCategoria);
        getContentPane().add(txtCategoria);
        getContentPane().add(btnCategoria);
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
        getContentPane().add(btnSalvar2);
        getContentPane().add(btnCancelar);
        getContentPane().add(scroll);
    }
    
    private void configTools() {
        btnSalvar.addActionListener(this);
        btnSalvar2.addActionListener(this);
        btnCancelar.addActionListener(this);
        kg.addActionListener(this);
        pecas.addActionListener(this);
        btnMarca.addActionListener(this);
        btnCategoria.addActionListener(this);
        
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
        
        try {
            temp_registro();
        } catch (SQLException ex) {
            Logger.getLogger(RegistrarProdutos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void configListarDesignacao() {
        DefaultListModel<String> modelo = new DefaultListModel<>();
        listaDesignacao.setModel(modelo);
        String sql = "select * from temp_registro where designação like '%" + txtDesignacao.getText() + "%'";
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
            if(Idiomas.getPort() == true) {
                try {
                    this.pst.close();
                    this.rs.close();
                    this.conexao.close();        
                    JOptionPane.showMessageDialog(null, "Erro no configListarDesignacao() - RegistrarProdutos \n" + erro, "Atenção", 0);
                } catch (SQLException ex) {
                    Logger.getLogger(RegistrarProdutos.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    this.pst.close();
                    this.rs.close();
                    this.conexao.close();
                    JOptionPane.showMessageDialog(null, "Error in configListarDesignacao() - RegistrarProdutos \n" + erro, "Attention", 0);
                } catch (SQLException ex) {
                    Logger.getLogger(RegistrarProdutos.class.getName()).log(Level.SEVERE, null, ex);
                }
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
            if(Idiomas.getPort() == true) {
                try {
                    this.pst.close();
                    this.rs.close();
                    this.conexao.close();        
                    JOptionPane.showMessageDialog(null, "Erro no configListarMarca() - RegistrarProdutos \n" + erro, "Atenção", 0);
                } catch (SQLException ex) {
                    Logger.getLogger(RegistrarProdutos.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    this.pst.close();
                    this.rs.close();
                    this.conexao.close();
                    JOptionPane.showMessageDialog(null, "Error in configListarMarca() - RegistrarProdutos \n" + erro, "Attention", 0);
                } catch (SQLException ex) {
                    Logger.getLogger(RegistrarProdutos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if(evt.getSource() == btnSalvar) {
            adicionar(); 
        } else if(evt.getSource() == btnSalvar2) {
                configCadastro();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Produto registrado com sucesso !", "Atenção", 1);
                } else {
                    JOptionPane.showMessageDialog(null, "Product registered successfully !", "Attention", 1);
                }
        }else if(evt.getSource() == btnCancelar) {
            txtDesignacao.setText(null);
            txtMarca.setText(null);
            txtCategoria.setSelectedItem(null);
            txtUnidade.setText(null);
            txtQuantidade.setValue(0);
            txtPreco.setText(null);
            txtPrecousd.setText(null);
            scroll_listaDesignacao.setVisible(false);
            scroll_listaMarca.setVisible(false);
        }  else if(evt.getSource() == kg) {
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
        } else if(evt.getSource() == btnMarca) {
            SwingUtilities.invokeLater(() -> {
                if(telamarca == null || !telamarca.isVisible()) {
                    try {
                        telamarca = new TelaMarca();
                    } catch (SQLException ex) {
                        Logger.getLogger(RegistrarProdutos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    telamarca.setVisible(true);
                    telamarca.toFront();
                } else {
                    telamarca.toFront();
                }
            });
        } else if(evt.getSource() == btnCategoria) {
            SwingUtilities.invokeLater(() -> {
                if(telacategoria == null || !telacategoria.isVisible()) {
                    try {
                        telacategoria = new TelaCategoria();
                    } catch (SQLException ex) {
                        Logger.getLogger(RegistrarProdutos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    telacategoria.setVisible(true);
                    telacategoria.toFront();
                } else {
                    telacategoria.toFront();
                }
            });
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
            
            } catch (SQLException erro) {
                if(Idiomas.getPort() == true) {
                    try {
                        this.pst.close();
                        this.rs.close();
                        this.conexao.close();        
                        JOptionPane.showMessageDialog(null, "Erro no txtCategoria() - RegistrarProdutos \n" + erro, "Atenção", 0);
                    } catch (SQLException ex) {
                        Logger.getLogger(RegistrarProdutos.class.getName()).log(Level.SEVERE, null, ex);
                    }
            } else {
                    try {
                        this.pst.close();
                        this.rs.close();
                        this.conexao.close();
                        JOptionPane.showMessageDialog(null, "Error in txtCategoria() - RegistrarProdutos \n" + erro, "Attention", 0);
                    } catch (SQLException ex) {
                        Logger.getLogger(RegistrarProdutos.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
            }
        }
            
    private void temp_registro() throws SQLException {
        Statement ctemp = conexao.createStatement();
        ctemp.executeUpdate("create temporary table temp_registro like tbloja_1");         
    }
    
    private void tabelaDAO() {
        String sql = "SELECT ID, DESIGNAÇÃO, MARCA, CATEGORIA, PRECO_ARTIGO_MZN, PRECO_ARTIGO_USD FROM temp_registro";
        

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            tabela.setModel(DbUtils.resultSetToTableModel(rs));
            
            tabela.getColumnModel().getColumn(0).setPreferredWidth(10);
            tabela.getColumnModel().getColumn(1).setPreferredWidth(120);
            tabela.getColumnModel().getColumn(2).setPreferredWidth(30);
            tabela.getColumnModel().getColumn(3).setPreferredWidth(30);
            tabela.getColumnModel().getColumn(4).setPreferredWidth(80);
            tabela.getColumnModel().getColumn(5).setPreferredWidth(80);
            
            tabela.setFont(new Font("Times New Roman", 0, 15));
            
            TableColumnModel columnModel = tabela.getColumnModel();
            TableColumn col = columnModel.getColumn(0);
            
            if(Idiomas.getPort() == true) {
                col.setHeaderValue("ID");
                col = columnModel.getColumn(1);
                col.setHeaderValue("DESIGNAÇÃO");
                col = columnModel.getColumn(2);
                col.setHeaderValue("MARCA");
                col = columnModel.getColumn(3);
                col.setHeaderValue("CATEGORIA");
                col = columnModel.getColumn(4);
                col.setHeaderValue("PREÇO(MZN)");
                col = columnModel.getColumn(5);
                col.setHeaderValue("PREÇO(USD)");
            } else {
                col.setHeaderValue("ID");
                col = columnModel.getColumn(1);
                col.setHeaderValue("ITEM DESCRIPTION");
                col = columnModel.getColumn(2);
                col.setHeaderValue("CODE");
                col = columnModel.getColumn(3);
                col.setHeaderValue("CATGRY");
                col = columnModel.getColumn(4);
                col.setHeaderValue("PRICE(MZN)");
                col = columnModel.getColumn(5);
                col.setHeaderValue("PRICE(USD)");
            }
            
            tabela.getTableHeader().repaint();
        } catch(SQLException er) {
            if(Idiomas.getPort() == true) {
                try {
                    this.pst.close();
                    this.rs.close();
                    this.conexao.close();
                    JOptionPane.showMessageDialog(null,er + " ERRO NA TABELA_DAO() - REGISTRARPRODUTOS", "TBL   DAO", 0);
                } catch (SQLException ex) {
                    Logger.getLogger(RegistrarProdutos.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    this.pst.close();
                    this.rs.close();
                    this.conexao.close();
                    JOptionPane.showMessageDialog(null,er + " ERROR IN DAO_TABLE() - REGISTRARPRODUTOS", "TBL   DAO", 0);
                } catch (SQLException ex) {
                    Logger.getLogger(RegistrarProdutos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }            
        }
    }
    
    private void adicionar() {
        String sql = "INSERT INTO temp_registro(designação, marca, categoria, unidade, quantidade, preco_artigo_mzn, preco_artigo_usd) VALUES(?, ?, ?, ?, ?, ?, ?)";
        
        try {
            pst = conexao.prepareStatement(sql);
                
            pst.setString(1, txtDesignacao.getText());
            pst.setString(2, txtMarca.getText());
            pst.setString(3, (String) txtCategoria.getSelectedItem());
            pst.setString(4, txtUnidade.getText());
            pst.setInt(5, 0);
            pst.setString(6, txtPreco.getText());
            pst.setString(7, txtPrecousd.getText());
                
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
                tabelaDAO();            
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
                            txtDesignacao.setText(null);
                            txtMarca.setText(null);
                            txtCategoria.setSelectedItem(null);
                            txtUnidade.setText(null);
                            txtQuantidade.setValue(0);
                            txtPreco.setText(null);
                            txtPrecousd.setText(null);
                        }
            
        } catch (HeadlessException | SQLException erro) {
            if(Idiomas.getPort() == true) {
                try {
                    this.pst.close();
                    this.conexao.close();
                    JOptionPane.showMessageDialog(null, erro+" MENSAGEM DE ERRO AO TENTAR ADCIONAR PRODUTO !", "AVISO", 0);
                } catch (SQLException ex) {
                    Logger.getLogger(RegistrarProdutos.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    this.pst.close();
                    this.conexao.close();
                    JOptionPane.showMessageDialog(null, erro+" ERROR MESSAGE WHEN TRYING TO ADD PRODUCT !", "WARNING", 0);
                } catch (SQLException ex) {
                    Logger.getLogger(RegistrarProdutos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    private void configCadastro() {
        String sql1 = "insert into tbloja_1(designação, marca, categoria, unidade, quantidade, preco_artigo_mzn, preco_artigo_usd) select designação, marca, categoria, unidade, quantidade, preco_artigo_mzn, preco_artigo_usd from temp_registro";
        
        try {
            pst = conexao.prepareStatement(sql1); 
            pst.executeUpdate();     
        } catch (HeadlessException | SQLException erro) {
            if(Idiomas.getPort() == true) {
                JOptionPane.showMessageDialog(null, erro+" MENSAGEM DE ERRO 1!", "AVISO", 0);
            } else {
                JOptionPane.showMessageDialog(null, erro+" ERROR MESSAGE 1()!", "WARNING", 0);
            }
        }
        
        String sql2 = "insert into tbloja_2(designação, marca, categoria, unidade, quantidade, preco_artigo_mzn, preco_artigo_usd) select designação, marca, categoria, unidade, quantidade, preco_artigo_mzn, preco_artigo_usd from temp_registro";
        
        try {
            pst = conexao.prepareStatement(sql2); 
            pst.executeUpdate();     
        } catch (HeadlessException | SQLException erro) {
            if(Idiomas.getPort() == true) {
                JOptionPane.showMessageDialog(null, erro+" MENSAGEM DE ERRO() 2!", "AVISO", 0);
            } else {
                JOptionPane.showMessageDialog(null, erro+" ERROR MESSAGE () 2!", "WARNING", 0);
            }
        }
        
        String sql3 = "insert into tbloja_3(designação, marca, categoria, unidade, quantidade, preco_artigo_mzn, preco_artigo_usd) select designação, marca, categoria, unidade, quantidade, preco_artigo_mzn, preco_artigo_usd from temp_registro";
        
        try {
            pst = conexao.prepareStatement(sql3); 
            pst.executeUpdate();     
        } catch (HeadlessException | SQLException erro) {
            if(Idiomas.getPort() == true) {
                JOptionPane.showMessageDialog(null, erro+" MENSAGEM DE ERRO 3() !", "AVISO", 0);
            } else {
                JOptionPane.showMessageDialog(null, erro+" ERROR MESSAGE 3()!", "WARNING", 0);
            }
        }
        
        String sqljardim = "insert into tbloja_jardim(designação, marca, categoria, unidade, quantidade, preco_artigo_mzn, preco_artigo_usd) select designação, marca, categoria, unidade, quantidade, preco_artigo_mzn, preco_artigo_usd from temp_registro";
        
        try {
            pst = conexao.prepareStatement(sqljardim); 
            pst.executeUpdate();     
        } catch (HeadlessException | SQLException erro) {
            if(Idiomas.getPort() == true) {
                JOptionPane.showMessageDialog(null, erro+" MENSAGEM DE ERRO jardim!", "AVISO", 0);
            } else {
                JOptionPane.showMessageDialog(null, erro+" ERROR MESSAGE jardim!", "WARNING", 0);
            }
        }
        
        String sqlbeira = "insert into tbarmazem_beira(designação, marca, categoria, unidade, quantidade, preco_artigo_mzn, preco_artigo_usd) select designação, marca, categoria, unidade, quantidade, preco_artigo_mzn, preco_artigo_usd from temp_registro";
        
        try {
            pst = conexao.prepareStatement(sqlbeira); 
            pst.executeUpdate();     
        } catch (HeadlessException | SQLException erro) {
            if(Idiomas.getPort() == true) {
                JOptionPane.showMessageDialog(null, erro+" MENSAGEM DE ERRO beira !", "AVISO", 0);
            } else {
                JOptionPane.showMessageDialog(null, erro+" ERROR MESSAGE beira!", "WARNING", 0);
            }
        }
        
        String sqlmaputo = "insert into tbarmazem_maputo(designação, marca, categoria, unidade, quantidade, preco_artigo_mzn, preco_artigo_usd) select designação, marca, categoria, unidade, quantidade, preco_artigo_mzn, preco_artigo_usd from temp_registro";
        
        try {
            pst = conexao.prepareStatement(sqlmaputo); 
            pst.executeUpdate();     
        } catch (HeadlessException | SQLException erro) {
            if(Idiomas.getPort() == true) {
                JOptionPane.showMessageDialog(null, erro+" MENSAGEM DE ERRO maputo !", "AVISO", 0);
            } else {
                JOptionPane.showMessageDialog(null, erro+" ERROR MESSAGE maputo!", "WARNING", 0);
            }
        }
    }

    @Override
    public void configPort() {
        lblDesignacao.setText("Designação");
        lblMarca.setText("Marca");
        lblCategoria.setText("Categoria");
        lblUnidade.setText("Unidade");
        kg.setText("kg");
        pecas.setText("peças");        
        lblQuantidade.setText("Quantidade");
        lblPreco.setText("Preço(MZN)");
        lblPrecousd.setText("Preço(USD)");
        btnSalvar.setText("Adicionar");
        btnSalvar.setFont(new Font("Agency FB", 1, 20));
        btnSalvar.setForeground(Color.BLACK);
        btnSalvar2.setText("Salvar");
        btnSalvar2.setFont(new Font("Agency FB", 1, 20));
        btnSalvar2.setForeground(Color.BLACK);
        btnCancelar.setText("Cancelar");
        btnCancelar.setFont(new Font("Agency FB", 1, 20));
        btnCancelar.setForeground(Color.BLACK);
    }

    @Override
    public void configEng() {
        lblDesignacao.setText("Designation");
        lblMarca.setText("Code");
        lblCategoria.setText("Category");
        lblUnidade.setText("Unit");
        kg.setText("kg");
        pecas.setText("peças");        
        lblQuantidade.setText("Quantity");
        lblPreco.setText("Price(MZN)");
        lblPrecousd.setText("Price(USD)");
        btnSalvar.setText("Add");
        btnSalvar.setFont(new Font("Agency FB", 1, 20));
        btnSalvar.setForeground(Color.BLACK);
        btnSalvar2.setText("Save");
        btnSalvar2.setFont(new Font("Agency FB", 1, 20));
        btnSalvar2.setForeground(Color.BLACK);
        btnCancelar.setText("Cancel");
        btnCancelar.setFont(new Font("Agency FB", 1, 20));
        btnCancelar.setForeground(Color.BLACK);
    }
}