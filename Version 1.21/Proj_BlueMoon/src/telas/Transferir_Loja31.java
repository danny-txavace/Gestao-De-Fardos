package telas;

import Abstrato.Config_idiomas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.sql.*;
import dao.ConexaoDAO;
import javax.swing.border.MatteBorder;
import javax.swing.event.ChangeEvent;
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

public final class Transferir_Loja31 extends JFrame implements ActionListener, Config_idiomas {
    private JLabel lblQuant, txtQuant, lbluser, txtuser, lblfundo, lbltitulo, lblloja_1, lblDesignacao, lblMarca, lblCategoria, lblUnidade, lblQuantidade, lblQuantidade_Disp, txtQuantidade_Disp, tituloVenda, txtArtigo_MZN, txtArtigo_USD, tituloTotal, txtTotal_MZN, txtTotal_USD, mzn, usd, mznT, usdT;
    private JTextField txtDesignacao, txtMarca, txtUnidade;
    private JComboBox txtCategoria;
    private JSpinner txtQuantidade;
    private JButton btnAdicionar, btnCancelar, btnTransferir, btnRemover, btnSair, btnAtualizar_Tb;
    private JList listaDesignacao, listaMarca;
    private JScrollPane scroll_listaDesignacao, scroll_listaMarca, scroll;
    private Connection conexao = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private DefaultListModel model;
    private DefaultTableModel modelT;
    private JSeparator separator, separatorV;
    private JTable tabela;
    private SpinnerNumberModel modelS;
    private int quantidade = 0, adicionado, sair, deleteItem;
    private double artigo_mzn, total_mzn, artigo_usd, total_usd;
    
    public Transferir_Loja31() throws SQLException {
        conexao = ConexaoDAO.conector();
        
        Janela();
        configView();
        
        if(Idiomas.getPort() == true) {
            configPort();
        } else {
            configEng();
        }
        
        tabelaDAO();
    }
    
    private void Janela() {        
        if(Idiomas.getPort() == true) {
            setTitle("TRANSFERIR PARA LOJA 1");
        } else {
            setTitle("TRANSFER TO SHOP_1");
        }
        setSize(1000, 540);        
        setLocationRelativeTo(null);
        setUndecorated(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(false);
    }
    
    private void configView() throws SQLException {
        setLayout(null);
        
        lbluser = new JLabel();
        txtuser = new JLabel();
        lblfundo = new JLabel();
        lbltitulo = new JLabel();
        lblloja_1 = new JLabel();
        lblDesignacao = new JLabel();
        lblMarca = new JLabel();
        lblCategoria = new JLabel();
        lblUnidade = new JLabel();
        lblQuantidade = new JLabel();
        lblQuantidade_Disp = new JLabel();
        txtQuantidade_Disp = new JLabel();     
        tituloVenda = new JLabel();
        txtArtigo_MZN = new JLabel();
        txtArtigo_USD = new JLabel();
        tituloTotal = new JLabel();
        tituloTotal = new JLabel();
        txtTotal_MZN = new JLabel();
        txtTotal_USD = new JLabel();
        mzn = new JLabel();
        usd = new JLabel();
        mznT = new JLabel();
        usdT = new JLabel();
        lblQuant = new JLabel();
        txtQuant = new JLabel();
                
        separator = new JSeparator();
        separatorV = new JSeparator();
        
        txtDesignacao = new JTextField();
        txtMarca = new JTextField();
        txtUnidade = new JTextField();
        
        modelS = new SpinnerNumberModel(0, 0, quantidade, 1);
        txtQuantidade = new JSpinner(modelS);
        
        txtCategoria = new JComboBox();
        txtCategoria();
        
        listaDesignacao = new JList();
        scroll_listaDesignacao = new JScrollPane();
        listaMarca = new JList();
        scroll_listaMarca = new JScrollPane();
        scroll = new JScrollPane();
        
        modelT = new DefaultTableModel();
        tabela = new JTable(modelT);
        
        btnAdicionar = new JButton();
        btnCancelar = new JButton();
        btnTransferir = new JButton();
        btnRemover = new JButton();
        btnSair = new JButton();
        btnAtualizar_Tb = new JButton();
        
        lblfundo.setIcon(new ImageIcon(this.getClass().getResource("/icones/light-grey.jpg")));
        lblfundo.setBounds(0, 0, 1000, 540);
        
        btnAtualizar_Tb.setIcon(new ImageIcon(this.getClass().getResource("/icones/atualizar.png")));
        btnAtualizar_Tb.setBounds(1, 43, 50, 27);
        btnAtualizar_Tb.setOpaque(false); //Torna o botão visível ou transparente
        btnAtualizar_Tb.setBackground(new Color(135, 206, 250));
        btnAtualizar_Tb.setBorder(new MatteBorder(0, 0, 0, 0, Color.WHITE));
        
        lbltitulo.setFont(new Font("Times New Roman", 3, 21));
        lbltitulo.setForeground(new Color(0, 191, 255));
        lbltitulo.setBounds(10, 0, 300, 30);
        
        lblloja_1.setFont(new Font("Stencil", 0, 18));
        lblloja_1.setForeground(Color.BLACK);
        lblloja_1.setBounds(920, 2, 150, 21);
                
        lbluser.setFont(new Font("Times New Roman", 1, 16));
        lbluser.setForeground(Color.BLACK);
        lbluser.setBounds(557, 43, 100, 28);
        
        txtuser.setText(Usuario.getUsuarioNome());
        txtuser.setFont(new Font("Times New Roman", 0, 17));
        txtuser.setForeground(Color.blue);
        txtuser.setBounds(650, 43, 207, 28);
        
        txtMarca.setText(null);
        txtMarca.setFont(new Font("Times New Roman", 0, 15));
        txtMarca.setForeground(Color.BLACK);
        txtMarca.setBounds(100, 71, 135, 28);
        
        lblMarca.setFont(new Font("Times New Roman", 1, 16));
        lblMarca.setForeground(Color.BLACK);
        lblMarca.setBounds(10, 71, 110, 28);
        
        txtMarca.setText(null);
        txtMarca.setFont(new Font("Times New Roman", 0, 15));
        txtMarca.setForeground(Color.BLACK);
        txtMarca.setBounds(100, 71, 135, 28);
        
        listaMarca.setFont(new Font("Times New Roman", 0, 15));
        listaMarca.setForeground(Color.BLACK);
        listaMarca.setBounds(100, 99, 135, 55);
        listaMarca.setBorder(null);
        listaMarca.setOpaque(false);
        
        scroll_listaMarca.setViewportView(listaMarca);
        scroll_listaMarca.setBounds(100, 99, 135, 55);
        scroll_listaMarca.setVisible(false);   
        scroll_listaMarca.setBorder(null);
        
        lblCategoria.setFont(new Font("Times New Roman", 1, 16));
        lblCategoria.setForeground(Color.BLACK);
        lblCategoria.setBounds(310, 71, 110, 28);
        
        txtCategoria.setSelectedItem(null);
        txtCategoria.setFont(new Font("Times New Roman", 0, 15));
        txtCategoria.setForeground(Color.BLACK);
        txtCategoria.setBounds(402, 71, 135, 28);
        
        lblDesignacao.setFont(new Font("Times New Roman", 1, 16));
        lblDesignacao.setForeground(Color.BLACK);
        lblDesignacao.setBounds(10, 109, 110, 28);
        
        txtDesignacao.setText(null);
        txtDesignacao.setFont(new Font("Times New Roman", 0, 15));
        txtDesignacao.setForeground(Color.BLACK);
        txtDesignacao.setBounds(100, 109, 437, 28);
        txtDesignacao.setBorder(new MatteBorder(2, 2, 0, 0, Color.GRAY));
                
        listaDesignacao.setFont(new Font("Times New Roman", 0, 15));
        listaDesignacao.setForeground(Color.BLACK);
        listaDesignacao.setBounds(100, 137, 437, 65);
        listaDesignacao.setBorder(null);
        listaDesignacao.setOpaque(false);
        
        scroll_listaDesignacao.setViewportView(listaDesignacao);
        scroll_listaDesignacao.setBounds(100, 137, 437, 65);
        scroll_listaDesignacao.setVisible(false);   
        scroll_listaDesignacao.setBorder(null);
                        
        lblUnidade.setFont(new Font("Times New Roman", 1, 16));
        lblUnidade.setForeground(Color.BLACK);
        lblUnidade.setBounds(10, 147, 110, 28);
        
        txtUnidade.setText(null);
        txtUnidade.setFont(new Font("Times New Roman", 0, 15));
        txtUnidade.setForeground(Color.BLACK);
        txtUnidade.setBounds(100, 147, 135, 28);
        txtUnidade.setEditable(false);
        
        lblQuantidade_Disp.setFont(new Font("Times New Roman", 1, 16));
        lblQuantidade_Disp.setForeground(Color.BLACK);
        lblQuantidade_Disp.setBounds(100, 182, 165, 28);
        
        txtQuantidade_Disp.setText("0");
        txtQuantidade_Disp.setFont(new Font("Agency FB", 0, 17));
        txtQuantidade_Disp.setForeground(Color.BLACK);
        txtQuantidade_Disp.setBounds(265, 182, 65, 28);
        
        lblQuantidade.setFont(new Font("Times New Roman", 1, 16));
        lblQuantidade.setForeground(Color.BLACK);
        lblQuantidade.setBounds(10, 215, 110, 28);
                
        txtQuantidade.setFont(new Font("Times New Roman", 0, 15));
        txtQuantidade.setForeground(Color.BLACK);
        txtQuantidade.setBounds(100, 215, 135, 28);
               
        separator.setBounds(10, 273, 528, 60);
        separator.setBorder(new MatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
        
        tituloVenda.setFont(new Font("Times New Roman", 1, 16));
        tituloVenda.setForeground(Color.black);
        tituloVenda.setBounds(15, 274, 117, 28);
        
        mzn.setText("MZN");
        mzn.setFont(new Font("Agency FB", 0, 17));
        mzn.setForeground(Color.black);
        mzn.setBounds(135, 274, 25, 28);
        
        usd.setText("$");
        usd.setFont(new Font("Agency FB", 0, 17));
        usd.setForeground(Color.black);
        usd.setBounds(135, 302, 25, 28);
        
        txtArtigo_MZN.setText("0.0");
        txtArtigo_MZN.setFont(new Font("Agency FB", 0, 17));
        txtArtigo_MZN.setForeground(Color.black);
        txtArtigo_MZN.setBounds(165, 274, 100, 28);
        
        txtArtigo_USD.setText("0.0");
        txtArtigo_USD.setFont(new Font("Agency FB", 0, 17));
        txtArtigo_USD.setForeground(Color.black);
        txtArtigo_USD.setBounds(165, 302, 100, 28);
        
        tituloTotal.setFont(new Font("Times New Roman", 1, 16));
        tituloTotal.setForeground(Color.black);
        tituloTotal.setBounds(310, 274, 100, 28);
        
        mznT.setText("MZN");
        mznT.setFont(new Font("Agency FB", 0, 17));
        mznT.setForeground(Color.black);
        mznT.setBounds(413, 274, 25, 28);
        
        usdT.setText("$");
        usdT.setFont(new Font("Agency FB", 0, 17));
        usdT.setForeground(Color.black);
        usdT.setBounds(413, 302, 25, 28);
        
        txtTotal_MZN.setText("0.0");
        txtTotal_MZN.setFont(new Font("Agency FB", 0, 17));
        txtTotal_MZN.setForeground(Color.black);
        txtTotal_MZN.setBounds(443, 274, 100, 28);
        
        txtTotal_USD.setText("0.0");
        txtTotal_USD.setFont(new Font("Agency FB", 0, 17));
        txtTotal_USD.setForeground(Color.black);
        txtTotal_USD.setBounds(443, 302, 100, 28);
        
        btnAdicionar.setFont(new Font("Agency FB", 1, 20));
        btnAdicionar.setForeground(Color.BLACK);
        btnAdicionar.setBounds(10, 500, 150, 30);
        btnAdicionar.setOpaque(false);
        
        btnCancelar.setFont(new Font("Agency FB", 1, 20));
        btnCancelar.setForeground(Color.BLACK);
        btnCancelar.setBounds(165, 500, 150, 30);
        btnCancelar.setOpaque(false);
        
        separatorV.setBounds(547, 0, 1, 540);
        separatorV.setBorder(new MatteBorder(0, 0, 0, 1, Color.LIGHT_GRAY));
        
        if(Idiomas.getPort() == true) {
            String[] nome = {"ID", "DESIGNAÇÃO", "QUANT.", "PREÇO_VENDA"};
            String dados[][] = {};        
            tabela = new JTable(dados, nome) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        } else {
            String[] nome = {"ID", "ITEM DESCRIPTION", "QUANT.", "SELLING PRICE"};
            String dados[][] = {};        
            tabela = new JTable(dados, nome) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        }       
               
        scroll.setViewportView(tabela);
        scroll.setBounds(557, 71, 435, 320);
                
        btnTransferir.setFont(new Font("Agency FB", 1, 20));
        btnTransferir.setForeground(Color.BLACK);
        btnTransferir.setBounds(557, 500, 150, 30);
        btnTransferir.setOpaque(false);
        
        btnRemover.setFont(new Font("Agency FB", 1, 20));
        btnRemover.setForeground(Color.BLACK);
        btnRemover.setBounds(712, 500, 150, 30);
        btnRemover.setOpaque(false);
        
        btnSair.setFont(new Font("Agency FB", 1, 20));
        btnSair.setForeground(Color.red);
        btnSair.setBounds(903, 500, 90, 30);
        btnSair.setOpaque(false);
        
        lblQuant.setFont(new Font("Times New Roman", 1, 17));
        lblQuant.setForeground(Color.black);
        lblQuant.setBounds(557, 411, 100, 28);
        
        txtQuant.setText("0");
        txtQuant.setFont(new Font("Agency FB", 0, 18));
        txtQuant.setForeground(Color.black);
        txtQuant.setBounds(662, 411, 100, 28);
        
        configTools();
                
        lblfundo.add(lbltitulo);
        lblfundo.add(lblloja_1);
        lblfundo.add(btnAtualizar_Tb);
        lblfundo.add(lblMarca);
        lblfundo.add(txtMarca);
        lblfundo.add(scroll_listaMarca);
        lblfundo.add(lblCategoria);
        lblfundo.add(txtCategoria);
        lblfundo.add(lblDesignacao);
        lblfundo.add(txtDesignacao);
        lblfundo.add(scroll_listaDesignacao);
        lblfundo.add(lblUnidade);
        lblfundo.add(txtUnidade);
        lblfundo.add(lblQuantidade_Disp);
        lblfundo.add(txtQuantidade_Disp);
        lblfundo.add(lblQuantidade);
        lblfundo.add(txtQuantidade);
        lblfundo.add(separator);        
        lblfundo.add(tituloVenda);
        lblfundo.add(txtArtigo_MZN);
        lblfundo.add(txtArtigo_USD);
        lblfundo.add(txtTotal_MZN);
        lblfundo.add(txtTotal_USD);
        lblfundo.add(tituloTotal);
        lblfundo.add(mzn);
        lblfundo.add(usd);
        lblfundo.add(mznT);
        lblfundo.add(usdT);
        lblfundo.add(btnAdicionar);
        lblfundo.add(btnCancelar);
        lblfundo.add(separatorV);
        lblfundo.add(scroll);
        lblfundo.add(lbluser);
        lblfundo.add(txtuser);
        lblfundo.add(lblQuant);
        lblfundo.add(txtQuant);
        lblfundo.add(btnTransferir);
        lblfundo.add(btnRemover);
        lblfundo.add(btnSair);
        
        getContentPane().add(lblfundo);
    }
    
    private void configTools() throws SQLException {
        btnAdicionar.addActionListener(this);
        btnCancelar.addActionListener(this);
        btnTransferir.addActionListener(this);
        btnSair.addActionListener(this);
        btnRemover.addActionListener(this);
        btnAtualizar_Tb.addActionListener(this);
        
        txtDesignacao.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                configListarDesignacao();                
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
        
        calctotal();
        temp_loja_1();
    }
    
    private void configListarDesignacao() {
        DefaultListModel<String> modelo = new DefaultListModel<>();
        listaDesignacao.setModel(modelo);
        String sql = "select * from tbloja_3 where MARCA = ? and CATEGORIA = ? and DESIGNAÇÃO like '%" + txtDesignacao.getText() + "%'";
        
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtMarca.getText());
            pst.setString(2, (String) txtCategoria.getSelectedItem());
            rs = pst.executeQuery();
            
            boolean encontrou = false;

            while (rs.next()) {
                modelo.addElement(rs.getString(2));
                encontrou = true;   
                                
                if (encontrou || txtDesignacao.getText().isEmpty()) {
                    scroll_listaDesignacao.setVisible(true);
                
                    listaDesignacao.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            model = (DefaultListModel) listaDesignacao.getModel();
                            int selected = listaDesignacao.getSelectedIndex();
                            txtDesignacao.setText((String) model.getElementAt(selected));
                            scroll_listaDesignacao.setVisible(false);
                        
                            String sql2 = "select * from tbloja_3 where DESIGNAÇÃO = ?";
                            try {
                                PreparedStatement pstm = conexao.prepareStatement(sql2);
                                pstm.setString(1, (String) model.getElementAt(selected));
                                ResultSet rst = pstm.executeQuery();
                            
                                if(rst.next()) {
                                    quantidade = rst.getInt("QUANTIDADE");
                                    String unidade = rst.getString("UNIDADE");
                                    artigo_mzn = rst.getDouble("PRECO_ARTIGO_MZN");
                                    artigo_usd = rst.getDouble("PRECO_ARTIGO_USD");
                
                                    modelS.setMaximum(quantidade);
                                
                                    txtUnidade.setText(unidade);
                                    txtArtigo_MZN.setText(""+artigo_mzn);
                                    txtArtigo_USD.setText(""+artigo_usd);                        
                                    txtQuantidade_Disp.setText(""+quantidade);
                                }
                            } catch(SQLException err) {
                                JOptionPane.showMessageDialog(null, err, "Attention", 0);
                            }
                        }
                    });
                } else {
                    scroll_listaDesignacao.setVisible(false);
                    txtQuantidade_Disp.setText("0");
                    txtArtigo_MZN.setText("0.0");
                    txtArtigo_USD.setText("0.0");
                    txtTotal_MZN.setText("0.0");
                    txtTotal_USD.setText("0.0");                
                }
                
                if(txtDesignacao.getText().isEmpty()) {
                    scroll_listaDesignacao.setVisible(false);
                    txtQuantidade_Disp.setText("0");
                    txtArtigo_MZN.setText("0.0");
                    txtArtigo_USD.setText("0.0");
                    txtTotal_MZN.setText("0.0");
                    txtTotal_USD.setText("0.0"); 
                }
            }
        } catch(SQLException erro) {
            if(Idiomas.getPort() == true) {
                try {
                    this.pst.close();
                    this.rs.close();
                    this.conexao.close();        
                    JOptionPane.showMessageDialog(null, "Erro no configListarDesignacao() - Transferir_Loja31 \n" + erro, "Atenção", 0);
                } catch (SQLException ex) {
                    Logger.getLogger(Transferir_Loja31.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    this.pst.close();
                    this.rs.close();
                    this.conexao.close();
                    JOptionPane.showMessageDialog(null, "Error in configListarDesignacao() - Transferir_Loja31 \n" + erro, "Attention", 0);
                } catch (SQLException ex) {
                    Logger.getLogger(Transferir_Loja31.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    private void calctotal() {
        txtQuantidade.addChangeListener((ChangeEvent evt) -> {
            int quant = (int) txtQuantidade.getValue();
            
            total_mzn = quant * artigo_mzn;
            total_usd = quant * artigo_usd;
            
            txtTotal_MZN.setText(""+total_mzn);
            txtTotal_USD.setText(""+total_usd);
        });
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
                    JOptionPane.showMessageDialog(null, "Erro no configListarMarca() - Transferir_Loja31 \n" + erro, "Atenção", 0);
                } catch (SQLException ex) {
                    Logger.getLogger(Transferir_Loja31.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    this.pst.close();
                    this.rs.close();
                    this.conexao.close();
                    JOptionPane.showMessageDialog(null, "Error in configListarMarca() - Transferir_Loja31 \n" + erro, "Attention", 0);
                } catch (SQLException ex) {
                    Logger.getLogger(Transferir_Loja31.class.getName()).log(Level.SEVERE, null, ex);
                }
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
            
            } catch (SQLException erro) {
                if(Idiomas.getPort() == true) {
                    try {
                        this.pst.close();
                        this.rs.close();
                        this.conexao.close();        
                        JOptionPane.showMessageDialog(null, "Erro no txtCategoria() - Transferir_Loja31 \n" + erro, "Atenção", 0);
                    } catch (SQLException ex) {
                        Logger.getLogger(Transferir_Loja31.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        this.pst.close();
                        this.rs.close();
                        this.conexao.close();
                        JOptionPane.showMessageDialog(null, "Error in txtCategoria()- Transferir_Loja31 \n" + erro, "Attention", 0);
                    } catch (SQLException ex) {
                        Logger.getLogger(Transferir_Loja31.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
            }
    }
    
    private void temp_loja_1() throws SQLException {
        Statement ctemp = conexao.createStatement();
        ctemp.executeUpdate("create temporary table temp_tbloja_1 like tbloja_1");         
    }
    
    private void tabelaDAO() {
        String sql = "SELECT ID, DESIGNAÇÃO, MARCA, CATEGORIA, QUANTIDADE FROM temp_tbloja_1";
        

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            tabela.setModel(DbUtils.resultSetToTableModel(rs));
            
            tabela.getColumnModel().getColumn(0).setPreferredWidth(15);
            tabela.getColumnModel().getColumn(1).setPreferredWidth(150);
            tabela.getColumnModel().getColumn(2).setPreferredWidth(40);
            tabela.getColumnModel().getColumn(3).setPreferredWidth(40);
            tabela.getColumnModel().getColumn(3).setPreferredWidth(30);
            
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
                col.setHeaderValue("QUANT.");
            } else {
                col.setHeaderValue("ID");
                col = columnModel.getColumn(1);
                col.setHeaderValue("ITEM DESCRIPTION");
                col = columnModel.getColumn(2);
                col.setHeaderValue("LABEL");
                col = columnModel.getColumn(3);
                col.setHeaderValue("CATEGORY");
                col = columnModel.getColumn(4);
                col.setHeaderValue("QUANT.");
            }
            
            tabela.getTableHeader().repaint();
        } catch(SQLException er) {
            if(Idiomas.getPort() == true) {
                try {
                    this.pst.close();
                    this.rs.close();
                    this.conexao.close();
                    JOptionPane.showMessageDialog(null,er + " ERRO NA TABELA_DAO() - Transferir_Loja31", "TBL   DAO", 0);
                } catch (SQLException ex) {
                    Logger.getLogger(Transferir_Loja31.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    this.pst.close();
                    this.rs.close();
                    this.conexao.close();
                    JOptionPane.showMessageDialog(null,er + " ERROR IN DAO_TABLE() - Transferir_Loja31", "TBL   DAO", 0);
                } catch (SQLException ex) {
                    Logger.getLogger(Transferir_Loja31.class.getName()).log(Level.SEVERE, null, ex);
                }
            }            
        }
    }
    
    private void salvar() throws SQLException {
        String sql = "insert into temp_tbloja_1(designação, marca, categoria, unidade, quantidade, preco_artigo_mzn, preco_artigo_usd) values (?, ?, ?, ?, ?, ?, ?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtDesignacao.getText());
            pst.setString(2, txtMarca.getText());
            pst.setString(3, (String) txtCategoria.getSelectedItem());
            pst.setString(4, txtUnidade.getText());
            pst.setInt(5, (int) txtQuantidade.getValue());
            pst.setString(6, txtArtigo_MZN.getText());
            pst.setString(7, txtArtigo_USD.getText());

            if((txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() == null) & (txtDesignacao.getText().isEmpty()) & (txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0)) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblMarca.setForeground(Color.red);
                lblCategoria.setForeground(Color.red);
                lblDesignacao.setForeground(Color.red);
                lblUnidade.setForeground(Color.red);
                lblQuantidade.setForeground(Color.red);
            } else if((txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() == null) & (txtDesignacao.getText().isEmpty()) & (txtUnidade.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblMarca.setForeground(Color.red);
                lblCategoria.setForeground(Color.red);
                lblDesignacao.setForeground(Color.red);
                lblUnidade.setForeground(Color.red);
            } else if((txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() == null) & (txtDesignacao.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0)) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblMarca.setForeground(Color.red);
                lblCategoria.setForeground(Color.red);
                lblDesignacao.setForeground(Color.red);
                lblQuantidade.setForeground(Color.red);
            } else if((txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() == null) & (txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0)) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblMarca.setForeground(Color.red);
                lblCategoria.setForeground(Color.red);
                lblUnidade.setForeground(Color.red);
                lblQuantidade.setForeground(Color.red);
            } else if((txtMarca.getText().isEmpty()) & (txtDesignacao.getText().isEmpty()) & (txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0)) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblMarca.setForeground(Color.red);
                lblDesignacao.setForeground(Color.red);
                lblUnidade.setForeground(Color.red);
                lblQuantidade.setForeground(Color.red);
            } else if((txtCategoria.getSelectedItem() == null) & (txtDesignacao.getText().isEmpty()) & (txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0)) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblCategoria.setForeground(Color.red);
                lblDesignacao.setForeground(Color.red);
                lblUnidade.setForeground(Color.red);
                lblQuantidade.setForeground(Color.red);
            } else if((txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() == null) & (txtDesignacao.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblMarca.setForeground(Color.red);
                lblCategoria.setForeground(Color.red);
                lblDesignacao.setForeground(Color.red);
            } else if((txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() == null) & (txtUnidade.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblMarca.setForeground(Color.red);
                lblCategoria.setForeground(Color.red);
                lblUnidade.setForeground(Color.red);
            } else if((txtMarca.getText().isEmpty()) & (txtDesignacao.getText().isEmpty()) & (txtUnidade.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblMarca.setForeground(Color.red);
                lblDesignacao.setForeground(Color.red);
                lblUnidade.setForeground(Color.red);
            } else if((txtMarca.getText().isEmpty()) & (txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0)) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblMarca.setForeground(Color.red);
                lblUnidade.setForeground(Color.red);
                lblQuantidade.setForeground(Color.red);
            } else if((txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() == null) & ((int) txtQuantidade.getValue() == 0)) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblMarca.setForeground(Color.red);
                lblCategoria.setForeground(Color.red);
                lblQuantidade.setForeground(Color.red);
            } else if((txtCategoria.getSelectedItem() == null) & (txtDesignacao.getText().isEmpty()) & (txtUnidade.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblCategoria.setForeground(Color.red);
                lblDesignacao.setForeground(Color.red);
                lblUnidade.setForeground(Color.red);
            } else if((txtCategoria.getSelectedItem() == null) & (txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0)) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblCategoria.setForeground(Color.red);
                lblUnidade.setForeground(Color.red);
                lblQuantidade.setForeground(Color.red);
            } else if((txtCategoria.getSelectedItem() == null) & (txtDesignacao.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0)) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblCategoria.setForeground(Color.red);
                lblDesignacao.setForeground(Color.red);
                lblQuantidade.setForeground(Color.red);
            } else if((txtDesignacao.getText().isEmpty()) & (txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0)) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblDesignacao.setForeground(Color.red);
                lblUnidade.setForeground(Color.red);
                lblQuantidade.setForeground(Color.red);
            }  else if((txtMarca.getText().isEmpty()) & (txtDesignacao.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0)) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblMarca.setForeground(Color.red);
                lblDesignacao.setForeground(Color.red);
                lblQuantidade.setForeground(Color.red);
            } else if((txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() == null)) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblMarca.setForeground(Color.red);
                lblCategoria.setForeground(Color.red);
            } else if((txtMarca.getText().isEmpty()) & (txtDesignacao.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblMarca.setForeground(Color.red);
                lblDesignacao.setForeground(Color.red);
            } else if((txtMarca.getText().isEmpty()) & (txtUnidade.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblMarca.setForeground(Color.red);
                lblQuantidade.setForeground(Color.red);
            } else if((txtCategoria.getSelectedItem() == null) & (txtDesignacao.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblCategoria.setForeground(Color.red);
                lblDesignacao.setForeground(Color.red);
            } else if((txtCategoria.getSelectedItem() == null) & (txtUnidade.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblCategoria.setForeground(Color.red);
                lblUnidade.setForeground(Color.red);
            } else if((txtCategoria.getSelectedItem() == null) & ((int) txtQuantidade.getValue() == 0)) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblCategoria.setForeground(Color.red);
                lblQuantidade.setForeground(Color.red);
            } else if((txtDesignacao.getText().isEmpty()) & (txtUnidade.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblDesignacao.setForeground(Color.red);
                lblUnidade.setForeground(Color.red);
            } else if((txtDesignacao.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0)) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblDesignacao.setForeground(Color.red);
                lblQuantidade.setForeground(Color.red);
            } else if((txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0)) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblUnidade.setForeground(Color.red);
                lblQuantidade.setForeground(Color.red);
            } else if((txtMarca.getText().isEmpty()) & (txtUnidade.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblMarca.setForeground(Color.red);
                lblUnidade.setForeground(Color.red);
            } else if((txtMarca.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblMarca.setForeground(Color.red);
            } else if(txtCategoria.getSelectedItem() == null) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblCategoria.setForeground(Color.red);
            } else if(txtDesignacao.getText().isEmpty()) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblDesignacao.setForeground(Color.red);
            } else if(txtUnidade.getText().isEmpty()) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblUnidade.setForeground(Color.red);
            } else if((int) txtQuantidade.getValue() == 0) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblQuantidade.setForeground(Color.red);
            } else {
                adicionado = pst.executeUpdate();
            }
            
            if((!txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() != null) & (!txtDesignacao.getText().isEmpty()) & (!txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() != 0)) {
                lblMarca.setForeground(Color.black);
                lblCategoria.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            }  else if((!txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() != null) & (!txtDesignacao.getText().isEmpty()) & (!txtUnidade.getText().isEmpty())) {
                lblMarca.setForeground(Color.black);
                lblCategoria.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
            } else if((!txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() != null) & (!txtDesignacao.getText().isEmpty()) & ((int) txtQuantidade.getValue() != 0)) {
                lblMarca.setForeground(Color.black);
                lblCategoria.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            } else if((!txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() != null) & (!txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() != 0)) {
                lblMarca.setForeground(Color.black);
                lblCategoria.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            } else if((!txtMarca.getText().isEmpty()) & (!txtDesignacao.getText().isEmpty()) & (!txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() != 0)) {
                lblMarca.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            } else if((txtCategoria.getSelectedItem() != null) & (!txtDesignacao.getText().isEmpty()) & (!txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() != 0)) {
                lblCategoria.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            } else if((!txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() != null) & (!txtDesignacao.getText().isEmpty())) {
                lblMarca.setForeground(Color.black);
                lblCategoria.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
            } else if((!txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() != null) & (!txtUnidade.getText().isEmpty())) {
                lblMarca.setForeground(Color.black);
                lblCategoria.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
            } else if((!txtMarca.getText().isEmpty()) & (!txtDesignacao.getText().isEmpty()) & (!txtUnidade.getText().isEmpty())) {
                lblMarca.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
            } else if((!txtMarca.getText().isEmpty()) & (!txtDesignacao.getText().isEmpty()) & ((int) txtQuantidade.getValue() != 0)) {
                lblMarca.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            } else if((!txtMarca.getText().isEmpty()) & (!txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() != 0)) {
                lblMarca.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            } else if((!txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() != null) & ((int) txtQuantidade.getValue() != 0)) {
                lblMarca.setForeground(Color.black);
                lblCategoria.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            } else if((txtCategoria.getSelectedItem() != null) & (!txtDesignacao.getText().isEmpty()) & (!txtUnidade.getText().isEmpty())) {
                lblCategoria.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
            } else if((txtCategoria.getSelectedItem() != null) & (!txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() != 0)) {
                lblCategoria.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            } else if((txtCategoria.getSelectedItem() != null) & (!txtDesignacao.getText().isEmpty()) & ((int) txtQuantidade.getValue() != 0)) {
                lblCategoria.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            } else if((!txtDesignacao.getText().isEmpty()) & (!txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() != 0)) {
                lblDesignacao.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            } else if((!txtMarca.getText().isEmpty()) & (!txtDesignacao.getText().isEmpty()) & ((int) txtQuantidade.getValue() != 0)) {
                lblMarca.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            }  else if((!txtMarca.getText().isEmpty()) & (!txtDesignacao.getText().isEmpty())) {
                lblMarca.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
            } else if((!txtMarca.getText().isEmpty()) & (!txtUnidade.getText().isEmpty())) {
                lblMarca.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            } else if((txtCategoria.getSelectedItem() != null) & (!txtDesignacao.getText().isEmpty())) {
                lblCategoria.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
            } else if((txtCategoria.getSelectedItem() != null) & (!txtUnidade.getText().isEmpty())) {
                lblCategoria.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
            } else if((txtCategoria.getSelectedItem() != null) & ((int) txtQuantidade.getValue() != 0)) {
                lblCategoria.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            } else if((!txtDesignacao.getText().isEmpty()) & (!txtUnidade.getText().isEmpty())) {
                lblDesignacao.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
            } else if((!txtDesignacao.getText().isEmpty()) & ((int) txtQuantidade.getValue() != 0)) {
                lblDesignacao.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            } else if((!txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() != 0)) {
                lblUnidade.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            } else if((!txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() != null)) {
                lblMarca.setForeground(Color.black);
                lblCategoria.setForeground(Color.black);
            } else if((!txtMarca.getText().isEmpty()) & (!txtUnidade.getText().isEmpty())) {
                lblMarca.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
            } else if((!txtMarca.getText().isEmpty())) {
                lblMarca.setForeground(Color.black);
            } else if((!txtDesignacao.getText().isEmpty())) {
                lblDesignacao.setForeground(Color.black);
            } else if((!txtUnidade.getText().isEmpty())) {
                lblUnidade.setForeground(Color.black);
            } else if(((int) txtQuantidade.getValue() != 0)) {
                lblQuantidade.setForeground(Color.black);
            } else if(txtCategoria.getSelectedItem() != null) {
                lblCategoria.setForeground(Color.black);
            } 
                                                            
            if (adicionado > 0) {
                txtDesignacao.setText(null);
                txtMarca.setText(null);
                txtCategoria.setSelectedItem(null);
                txtUnidade.setText(null);
                txtQuantidade_Disp.setText("0");
                txtQuantidade.setValue(0);
                txtArtigo_MZN.setText("0.0");
                txtArtigo_USD.setText("0.0");
            }        
            tabelaDAO();
        } catch (HeadlessException | SQLException erro) {
            if(Idiomas.getPort() == true) {
                this.pst.close();
                this.conexao.close();
                JOptionPane.showMessageDialog(null, erro+" MENSAGEM DE ERRO AO TENTAR SALVAR UMA VENDA - Transferir_Loja31!", "AVISO", 0);
            } else {
                this.pst.close();
                this.conexao.close();
                JOptionPane.showMessageDialog(null, erro+" ERROR MESSAGE WHEN TRYING TO SAVE - Transferir_Loja31!", "WARNING", 0);
            }
        }
        
        String sql2 = "select sum( QUANTIDADE ) as somatemploja_1 from temp_tbloja_1";
        int somaVlr = 0;
        try{
            rs = conexao.createStatement().executeQuery(sql2);
            
            while (rs.next()) {
                somaVlr = rs.getInt("somatemploja_1");
            }          
            
            txtQuant.setText(""+somaVlr);
            
        } catch(SQLException err) {
            if(Idiomas.getPort() == true) {
                this.rs.close();
                this.conexao.close();
                this.pst.close();
                JOptionPane.showMessageDialog(null, "ERRO NO save() somatemploja_2 - Transferir_Loja31 ..! "+err, "Atenção", 0);
            } else {
                this.rs.close();
                this.conexao.close();
                this.pst.close();
                JOptionPane.showMessageDialog(null, "ERROR IN save() somatemploja_2 - Transferir_Loja31 ..! "+err, "Attention", 0);
            }            
        }        
    }
     
        
    private void configTransferir1() throws SQLException {
        String sqlTemp = "select DESIGNAÇÃO, QUANTIDADE from temp_tbloja_1";
        
        pst = conexao.prepareStatement(sqlTemp);
        rs = pst.executeQuery();
        
        String sqlUpdate_Loja1 = "update tbloja_3 set QUANTIDADE = QUANTIDADE - ? WHERE DESIGNAÇÃO = ?";
        
        try {
            pst = conexao.prepareStatement(sqlUpdate_Loja1);
            
            while(rs.next()) {
                String designacao = rs.getString("DESIGNAÇÃO");
                int quantTemp = rs.getInt("QUANTIDADE");
                
                pst.setInt(1, quantTemp);
                pst.setString(2, designacao);
                pst.executeUpdate();
            }
        } catch (HeadlessException | SQLException erro) {
            if(Idiomas.getPort() == true) {
                this.pst.close();
                this.rs.close();
                this.conexao.close();
                JOptionPane.showMessageDialog(null, erro+" MENSAGEM DE ERRO no configUTransferir1() - Transferir_Loja31 - Update Loja 1!", "AVISO", 0);
            } else {
                this.pst.close();
                this.rs.close();
                this.conexao.close();
                JOptionPane.showMessageDialog(null, erro+" ERROR MESSAGE in configTransferir1() - Transferir_Loja31 - Update Shop 1!", "WARNING", 0);
            }
        }
    }
    
    private void configTransferir2() throws SQLException {
        String sqlTemp = "select DESIGNAÇÃO, QUANTIDADE from temp_tbloja_1";
        
        pst = conexao.prepareStatement(sqlTemp);
        rs = pst.executeQuery();
        
        String sqlUpdate_Loja2 = "update tbloja_1 set QUANTIDADE = QUANTIDADE + ? WHERE DESIGNAÇÃO = ?";
        
        try {
            pst = conexao.prepareStatement(sqlUpdate_Loja2);
            
            while(rs.next()) {
                String designacao = rs.getString("DESIGNAÇÃO");
                int quantTemp = rs.getInt("QUANTIDADE");
                
                pst.setInt(1, quantTemp);
                pst.setString(2, designacao);
                pst.executeUpdate();
            }
        } catch (HeadlessException | SQLException erro) {
            if(Idiomas.getPort() == true) {
                this.pst.close();
                this.rs.close();
                this.conexao.close();
                JOptionPane.showMessageDialog(null, erro+" MENSAGEM DE ERRO no configUTransferir2() - Transferir_Loja31 - Update Loja 1!", "AVISO", 0);
            } else {
                this.pst.close();
                this.rs.close();
                this.conexao.close();
                JOptionPane.showMessageDialog(null, erro+" ERROR MESSAGE in configTransferir2() - Transferir_Loja31 - Update Shop 1!", "WARNING", 0);
            }
        }
    }
    
    private void limparTabela() {
        String sql = "drop temporary table temp_tbloja_1";
        
        try {
            pst = conexao.prepareStatement(sql);
            pst.executeUpdate();
        } catch(SQLException erro) {
            if(Idiomas.getPort() == true) {
                try {
                    this.pst.close();
                    this.conexao.close();
                    JOptionPane.showMessageDialog(null, erro+" MENSAGEM DE ERRO no limparTabela() - Transferir_Loja31!", "AVISO", 0);
                } catch (SQLException ex) {
                    Logger.getLogger(Transferir_Loja31.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    this.pst.close();
                    this.conexao.close();
                    JOptionPane.showMessageDialog(null, erro+" ERROR MESSAGE in limparTabela() - Transferir_Loja31!", "WARNING", 0);
                } catch (SQLException ex) {
                    Logger.getLogger(Transferir_Loja31.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    public void remover() {
        String sql = "delete from temp_tbloja_1 where ID = ?";
        modelT = (DefaultTableModel) tabela.getModel();
        int sr = tabela.getSelectedRow();
        try {
            pst = conexao.prepareStatement(sql);
            int id = Integer.parseInt(modelT.getValueAt(sr, 0).toString());
            
            if(Idiomas.getPort() == true) {
                deleteItem = JOptionPane.showConfirmDialog(null, "Têm certeza que deseja remover?", "Atenção", JOptionPane.YES_NO_OPTION);
            } else {
                deleteItem = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove?", "Attention", JOptionPane.YES_NO_OPTION);
            }
            if(deleteItem == JOptionPane.YES_OPTION) {                
                pst.setInt(1, id);
                pst.executeUpdate();
                
                tabelaDAO();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Removido..!", "Atenção", 1);
                } else {
                    JOptionPane.showMessageDialog(null, "Removed..!", "Attention", 1);
                }               
            }
        } catch(HeadlessException | NumberFormatException | SQLException Er) {
            if(Idiomas.getPort() == true) {
                try {
                    this.pst.close();
                    this.conexao.close();
                    JOptionPane.showMessageDialog(null," ERRO no remover() - Transferir_Loja31...! "+Er, "Atenção",0);
                } catch (SQLException ex) {
                    Logger.getLogger(Transferir_Loja31.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    this.pst.close();
                    this.conexao.close();
                    JOptionPane.showMessageDialog(null,"ERROR WHILE TRYING TO DELETE in remove() - Transferir_Loja31...! "+Er, "Attention",0);
                } catch (SQLException ex) {
                    Logger.getLogger(Transferir_Loja31.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
        }
    }
    
    private void limparTela() {
        txtDesignacao.setText(null);
        txtMarca.setText(null);
        txtCategoria.setSelectedItem(null);
        txtUnidade.setText(null);
        txtQuantidade_Disp.setText("0");
        txtQuantidade.setValue(0);
        txtArtigo_MZN.setText("0.0");
        txtArtigo_USD.setText("0.0");
        scroll_listaDesignacao.setVisible(false);
        scroll_listaMarca.setVisible(false);
    }
    
        private void validar() {
            if((txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() == null) & (txtDesignacao.getText().isEmpty()) & (txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0)) {
                lblMarca.setForeground(Color.black);
                lblCategoria.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            } else if((txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() == null) & (txtDesignacao.getText().isEmpty()) & (txtUnidade.getText().isEmpty())) {
                lblMarca.setForeground(Color.black);
                lblCategoria.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
            } else if((txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() == null) & (txtDesignacao.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0)) {
                lblMarca.setForeground(Color.black);
                lblCategoria.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            } else if((txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() == null) & (txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0)) {
                lblMarca.setForeground(Color.black);
                lblCategoria.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            } else if((txtMarca.getText().isEmpty()) & (txtDesignacao.getText().isEmpty()) & (txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0)) {
                lblMarca.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            } else if((txtCategoria.getSelectedItem() == null) & (txtDesignacao.getText().isEmpty()) & (txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0)) {
                lblCategoria.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            } else if((txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() == null) & (txtDesignacao.getText().isEmpty())) {
                lblMarca.setForeground(Color.black);
                lblCategoria.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
            } else if((txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() == null) & (txtUnidade.getText().isEmpty())) {
                lblMarca.setForeground(Color.black);
                lblCategoria.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
            } else if((txtMarca.getText().isEmpty()) & (txtDesignacao.getText().isEmpty()) & (txtUnidade.getText().isEmpty())) {
                lblMarca.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
            } else if((txtMarca.getText().isEmpty()) & (txtDesignacao.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0)) {
                txtMarca.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            } else if((txtMarca.getText().isEmpty()) & (txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0)) {
                lblMarca.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            } else if((txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() == null) & ((int) txtQuantidade.getValue() == 0)) {
                lblMarca.setForeground(Color.black);
                lblCategoria.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            } else if((txtCategoria.getSelectedItem() != null) & (txtDesignacao.getText().isEmpty()) & (txtUnidade.getText().isEmpty())) {
                lblCategoria.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
            } else if((txtCategoria.getSelectedItem() != null) & (txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0)) {
                lblCategoria.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            } else if((txtCategoria.getSelectedItem() != null) & (txtDesignacao.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0)) {
                lblCategoria.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            } else if((txtDesignacao.getText().isEmpty()) & (txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0)) {
                lblDesignacao.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            } else if((txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() == null)) {
                lblMarca.setForeground(Color.black);
                lblCategoria.setForeground(Color.black);
            } else if((txtMarca.getText().isEmpty()) & (txtDesignacao.getText().isEmpty())) {
                lblMarca.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
            } else if((txtMarca.getText().isEmpty()) & (txtUnidade.getText().isEmpty())) {
                lblMarca.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            } else if((txtCategoria.getSelectedItem() == null) & (txtDesignacao.getText().isEmpty())) {
                lblCategoria.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
            } else if((txtCategoria.getSelectedItem() == null) & (txtUnidade.getText().isEmpty())) {
                lblCategoria.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
            } else if((txtCategoria.getSelectedItem() == null) & ((int) txtQuantidade.getValue() == 0)) {
                lblCategoria.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            } else if((txtDesignacao.getText().isEmpty()) & (txtUnidade.getText().isEmpty())) {
                lblDesignacao.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
            } else if((txtDesignacao.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0)) {
                lblDesignacao.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            } else if((txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0)) {
                lblUnidade.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            } else if((txtMarca.getText().isEmpty()) & (txtUnidade.getText().isEmpty())) {
                lblMarca.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
            } else if((txtMarca.getText().isEmpty())) {
                lblMarca.setForeground(Color.black);
            } else if((txtCategoria.getSelectedItem() == null)) {
                lblCategoria.setForeground(Color.black);
            } else if((txtDesignacao.getText().isEmpty())) {
                lblDesignacao.setForeground(Color.black);
            } else if(txtUnidade.getText().isEmpty()) {
                lblUnidade.setForeground(Color.black);
            } else if((int) txtQuantidade.getValue() == 0) {
                lblQuantidade.setForeground(Color.black);
            }
        }
    
    @Override
    public void actionPerformed(ActionEvent evt) {
        if(evt.getSource() == btnAdicionar) {
            try {            
                salvar();
            } catch (SQLException ex) {
                Logger.getLogger(Transferir_Loja31.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if(evt.getSource() == btnCancelar) {
            limparTela();  
            validar();
        } else if(evt.getSource() == btnTransferir) {
            try {
                configTransferir1();
                configTransferir2();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Transferido com sucesso para LOJA 1 !", "Atenção", 1);
                } else {
                    JOptionPane.showMessageDialog(null, "Successfully Transferred to SHOP 1!", "Successfully", 1);
                }                
            } catch (SQLException ex) {
                Logger.getLogger(Transferir_Loja31.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if(evt.getSource() == btnSair) {
            if(Idiomas.getPort() == true) {
                sair = JOptionPane.showConfirmDialog(null, "Têm certeza que deseja sair?", "Atenção", JOptionPane.YES_NO_OPTION);
                if(sair == JOptionPane.YES_OPTION) {
                    try {
                        conexao.close();
                        this.dispose();
                    } catch (SQLException ex) {
                        Logger.getLogger(Transferir_Loja31.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                sair = JOptionPane.showConfirmDialog(null, "Are you sure you want to leave?", "Attention", JOptionPane.YES_NO_OPTION);
                if(sair == JOptionPane.YES_OPTION) {
                    try {
                        conexao.close();
                        this.dispose();
                    } catch (SQLException ex) {
                        Logger.getLogger(Transferir_Loja31.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } else if(evt.getSource() == btnRemover) {
            if(tabela.getSelectedRow() == -1) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Por favor, selecione alguma venda na tabela !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a product from the table !", "Attention", 2);
                }                
            } else {
                remover();
            }
        } else if(evt.getSource() == btnAtualizar_Tb) {
            limparTabela();
            try {
                temp_loja_1();
            } catch (SQLException ex) {
                Logger.getLogger(Transferir_Loja31.class.getName()).log(Level.SEVERE, null, ex);
            }
            limparTela();
            validar();
            tabelaDAO(); 
            if(Idiomas.getPort() == true) {
                JOptionPane.showMessageDialog(null, "Atualizado !", "Atenção", 1);
            } else {
                JOptionPane.showMessageDialog(null, "Updated !", "Attention", 1);
            }
        }
    }

    @Override
    public void configPort() {
        lblDesignacao.setText("Designação");
        lblMarca.setText("Marca");
        lblCategoria.setText("Categoria");
        lblUnidade.setText("Unidade");       
        lblQuantidade.setText("Quantidade");
        btnAdicionar.setText("Adicionar");
        btnCancelar.setText("Cancelar");
        lbltitulo.setText("TRANSFERÊNCIA");
        lblloja_1.setText("# LOJA_3");
        lblQuantidade_Disp.setText("Quantidade disponível : ");       
        tituloVenda.setText("Preço do artigo : ");
        tituloTotal.setText("Preço total : ");
        lblQuant.setText("Quant. total :");
        btnTransferir.setText("Transferir");
        btnRemover.setText("Remover");
        btnSair.setText("Sair");
        lbluser.setText("Destino :");
        txtuser.setText("Loja_1");
    }

    @Override
    public void configEng() {
        lblDesignacao.setText("Designation");
        lblMarca.setText("Code");
        lblCategoria.setText("Category");
        lblUnidade.setText("Unit");    
        lblQuantidade.setText("Quantity");
        btnAdicionar.setText("Add");
        btnCancelar.setText("Cancel");
        lbltitulo.setText("TRANSFERENCE");
        lblloja_1.setText("# SHOP_3");
        lblQuantidade_Disp.setText("Available quantity : ");       
        tituloVenda.setText("Article price : ");
        tituloTotal.setText("Total price : ");
        lblQuant.setText("Total quant : ");
        btnTransferir.setText("Transfer");
        btnRemover.setText("Remove");
        btnSair.setText("Exit");
        lbluser.setText("Destination :");
        txtuser.setText("Shop_1");
    }
}