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

/**
 * @author Ramadan ismaeL
 */

public final class TelaEdd_Venda_Loja2 extends JFrame implements ActionListener, Config_idiomas {
    protected JLabel lblID, txtID, lblfundo, lbltitulo, lblDesignacao, lblMarca, lblCategoria, lblUnidade, lblQuantidade, lblQuantidade_Disp, txtQuantidade_Disp, lblQuantidade_Disp2, txtQuantidade_Disp2,  lblVenda_MZN, tituloVenda, txtArtigo_MZN, txtArtigo_USD, tituloTotal, txtTotal_MZN, txtTotal_USD, mzn, usd, mznT, usdT;
    protected JTextField txtDesignacao, txtMarca, txtUnidade, txtVenda_MZN;
    protected JComboBox txtCategoria;
    protected JSpinner txtQuantidade;
    private JButton btnAdicionar, btnCancelar, btnSair;
    private JList listaDesignacao, listaMarca;
    private JScrollPane scroll_listaDesignacao, scroll_listaMarca;
    private JSeparator separator;
    private Connection conexao = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private DefaultListModel model;
    private SpinnerNumberModel modelS;
    private int quantidade = 0, adicionado, sair;
    private double artigo_mzn, total_mzn, artigo_usd, total_usd;
    
    public TelaEdd_Venda_Loja2() throws SQLException {
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
            setTitle("ATUALIZAR VENDA");
        } else {
            setTitle("SALLING UPDATE");
        }
        setSize(547, 540);        
        setLocationRelativeTo(null);
        setUndecorated(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(false);
    }
    
    private void configView() throws SQLException {
        setLayout(null);
        
        lblfundo = new JLabel();
        lblID = new JLabel();
        txtID = new JLabel();
        lbltitulo = new JLabel();
        lblDesignacao = new JLabel();
        lblMarca = new JLabel();
        lblCategoria = new JLabel();
        lblUnidade = new JLabel();
        lblQuantidade = new JLabel();
        lblQuantidade_Disp = new JLabel();
        txtQuantidade_Disp = new JLabel();
        lblQuantidade_Disp2 = new JLabel();
        txtQuantidade_Disp2 = new JLabel();
        lblVenda_MZN = new JLabel();        
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
        
        txtDesignacao = new JTextField();
        txtMarca = new JTextField();
        txtUnidade = new JTextField();
        txtVenda_MZN = new JTextField();
        
        separator = new JSeparator();
        
        modelS = new SpinnerNumberModel(0, 0, quantidade, 1);
        txtQuantidade = new JSpinner(modelS);
        
        txtCategoria = new JComboBox();
        txtCategoria();
        
        listaDesignacao = new JList();
        scroll_listaDesignacao = new JScrollPane();
        listaMarca = new JList();
        scroll_listaMarca = new JScrollPane();
        
        btnAdicionar = new JButton();
        btnCancelar = new JButton();
        btnSair = new JButton();
        
        lblfundo.setIcon(new ImageIcon(this.getClass().getResource("/icones/light-grey.jpg")));
        lblfundo.setBounds(0, 0, 1000, 540);
        
        lbltitulo.setFont(new Font("Times New Roman", 3, 21));
        lbltitulo.setForeground(new Color(0, 191, 255));
        lbltitulo.setBounds(10, 0, 300, 30);
        
        lblID.setText("ID");
        lblID.setFont(new Font("Times New Roman", 1, 16));
        lblID.setForeground(Color.black);
        lblID.setBounds(10, 71, 110, 28);
        
        txtID.setText(null);
        txtID.setFont(new Font("Times New Roman", 1, 16));
        txtID.setForeground(Color.red);
        txtID.setBounds(100, 71, 100, 28);
        
        txtMarca.setText(null);
        txtMarca.setFont(new Font("Times New Roman", 0, 15));
        txtMarca.setForeground(Color.BLACK);
        txtMarca.setBounds(100, 109, 135, 28);
        
        lblMarca.setFont(new Font("Times New Roman", 1, 16));
        lblMarca.setForeground(Color.BLACK);
        lblMarca.setBounds(10, 109, 110, 28);
        
        txtMarca.setText(null);
        txtMarca.setFont(new Font("Times New Roman", 0, 15));
        txtMarca.setForeground(Color.BLACK);
        txtMarca.setBounds(100, 109, 135, 28);
        
        listaMarca.setFont(new Font("Times New Roman", 0, 15));
        listaMarca.setForeground(Color.BLACK);
        listaMarca.setBounds(100, 137, 135, 55);
        listaMarca.setBorder(null);
        listaMarca.setOpaque(false);
        
        scroll_listaMarca.setViewportView(listaMarca);
        scroll_listaMarca.setBounds(100, 137, 135, 55);
        scroll_listaMarca.setVisible(false);   
        scroll_listaMarca.setBorder(null);
        
        lblCategoria.setFont(new Font("Times New Roman", 1, 16));
        lblCategoria.setForeground(Color.BLACK);
        lblCategoria.setBounds(310, 109, 110, 28);
        
        txtCategoria.setSelectedItem(null);
        txtCategoria.setFont(new Font("Times New Roman", 0, 15));
        txtCategoria.setForeground(Color.BLACK);
        txtCategoria.setBounds(402, 109, 135, 28);
        
        lblDesignacao.setFont(new Font("Times New Roman", 1, 16));
        lblDesignacao.setForeground(Color.BLACK);
        lblDesignacao.setBounds(10, 147, 110, 28);
        
        txtDesignacao.setText(null);
        txtDesignacao.setFont(new Font("Times New Roman", 0, 15));
        txtDesignacao.setForeground(Color.BLACK);
        txtDesignacao.setBounds(100, 147, 437, 28);
        txtDesignacao.setBorder(new MatteBorder(2, 2, 0, 0, Color.GRAY));
                
        listaDesignacao.setFont(new Font("Times New Roman", 0, 15));
        listaDesignacao.setForeground(Color.BLACK);
        listaDesignacao.setBounds(100, 175, 437, 65);
        listaDesignacao.setBorder(null);
        listaDesignacao.setOpaque(false);
        
        scroll_listaDesignacao.setViewportView(listaDesignacao);
        scroll_listaDesignacao.setBounds(100, 175, 437, 65);
        scroll_listaDesignacao.setVisible(false);   
        scroll_listaDesignacao.setBorder(null);
        
        lblUnidade.setFont(new Font("Times New Roman", 1, 16));
        lblUnidade.setForeground(Color.BLACK);
        lblUnidade.setBounds(10, 182, 110, 28);
        
        txtUnidade.setText(null);
        txtUnidade.setFont(new Font("Times New Roman", 0, 15));
        txtUnidade.setForeground(Color.BLACK);
        txtUnidade.setBounds(100, 182, 135, 28);
        txtUnidade.setEditable(false);
        
        lblQuantidade_Disp.setFont(new Font("Times New Roman", 1, 16));
        lblQuantidade_Disp.setForeground(Color.BLACK);
        lblQuantidade_Disp.setBounds(10, 215, 165, 28);
        
        txtQuantidade_Disp.setText("0");
        txtQuantidade_Disp.setFont(new Font("Agency FB", 0, 17));
        txtQuantidade_Disp.setForeground(Color.BLACK);
        txtQuantidade_Disp.setBounds(150, 215, 65, 28);
        
        lblQuantidade_Disp2.setFont(new Font("Times New Roman", 1, 16));
        lblQuantidade_Disp2.setForeground(Color.BLACK);
        lblQuantidade_Disp2.setBounds(310, 215, 165, 28);
        
        txtQuantidade_Disp2.setText("0");
        txtQuantidade_Disp2.setFont(new Font("Agency FB", 0, 17));
        txtQuantidade_Disp2.setForeground(Color.BLACK);
        txtQuantidade_Disp2.setBounds(452, 215, 65, 28);
        
        lblQuantidade.setFont(new Font("Times New Roman", 1, 16));
        lblQuantidade.setForeground(Color.BLACK);
        lblQuantidade.setBounds(10, 243, 110, 28);
                
        txtQuantidade.setFont(new Font("Times New Roman", 0, 15));
        txtQuantidade.setForeground(Color.BLACK);
        txtQuantidade.setBounds(100, 243, 135, 28);
               
        tituloVenda.setFont(new Font("Times New Roman", 1, 16));
        tituloVenda.setForeground(Color.black);
        tituloVenda.setBounds(15, 305, 150, 28);
        
        separator.setBounds(10, 304, 528, 60);
        separator.setBorder(new MatteBorder(1, 1, 1, 1, Color.LIGHT_GRAY));
        
        mzn.setText("MZN");
        mzn.setFont(new Font("Agency FB", 0, 17));
        mzn.setForeground(Color.black);
        mzn.setBounds(130, 305, 25, 28);
        
        usd.setText("$");
        usd.setFont(new Font("Agency FB", 0, 17));
        usd.setForeground(Color.black);
        usd.setBounds(137, 333, 25, 28);
        
        txtArtigo_MZN.setText("0.0");
        txtArtigo_MZN.setFont(new Font("Agency FB", 0, 17));
        txtArtigo_MZN.setForeground(Color.black);
        txtArtigo_MZN.setBounds(155, 305, 100, 28);
        
        txtArtigo_USD.setText("0.0");
        txtArtigo_USD.setFont(new Font("Agency FB", 0, 17));
        txtArtigo_USD.setForeground(Color.black);
        txtArtigo_USD.setBounds(155, 333, 100, 28);
        
        tituloTotal.setFont(new Font("Times New Roman", 1, 16));
        tituloTotal.setForeground(Color.black);
        tituloTotal.setBounds(310, 305, 150, 28);
        
        mznT.setText("MZN");
        mznT.setFont(new Font("Agency FB", 0, 17));
        mznT.setForeground(Color.black);
        mznT.setBounds(410, 305, 25, 28);
        
        usdT.setText("$");
        usdT.setFont(new Font("Agency FB", 0, 17));
        usdT.setForeground(Color.black);
        usdT.setBounds(417, 333, 25, 28);
        
        txtTotal_MZN.setText("0.0");
        txtTotal_MZN.setFont(new Font("Agency FB", 0, 17));
        txtTotal_MZN.setForeground(Color.black);
        txtTotal_MZN.setBounds(435, 305, 100, 28);
        
        txtTotal_USD.setText("0.0");
        txtTotal_USD.setFont(new Font("Agency FB", 0, 17));
        txtTotal_USD.setForeground(Color.black);
        txtTotal_USD.setBounds(435, 333, 100, 28);
        
        lblVenda_MZN.setFont(new Font("Times New Roman", 1, 16));
        lblVenda_MZN.setForeground(Color.BLACK);
        lblVenda_MZN.setBounds(10, 394, 110, 28);
                        
        txtVenda_MZN.setFont(new Font("Times New Roman", 0, 15));
        txtVenda_MZN.setForeground(Color.BLACK);
        txtVenda_MZN.setBounds(100, 394, 135, 28);
        
        btnAdicionar.setFont(new Font("Agency FB", 1, 20));
        btnAdicionar.setForeground(Color.BLACK);
        btnAdicionar.setBounds(10, 500, 150, 30);
        btnAdicionar.setOpaque(false);
        
        btnCancelar.setFont(new Font("Agency FB", 1, 20));
        btnCancelar.setForeground(Color.BLACK);
        btnCancelar.setBounds(165, 500, 150, 30);
        btnCancelar.setOpaque(false);
        
        btnSair.setFont(new Font("Agency FB", 1, 20));
        btnSair.setForeground(Color.red);
        btnSair.setBounds(448, 500, 90, 30);
        btnSair.setOpaque(false);
        
        configTools();
                
        lblfundo.add(lbltitulo);
        lblfundo.add(lblID);
        lblfundo.add(txtID);
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
        lblfundo.add(lblQuantidade_Disp2);
        lblfundo.add(txtQuantidade_Disp2);
        lblfundo.add(lblQuantidade);
        lblfundo.add(txtQuantidade);
        lblfundo.add(separator);
        lblfundo.add(tituloVenda);
        lblfundo.add(txtArtigo_MZN);
        lblfundo.add(txtArtigo_USD);
        lblfundo.add(txtTotal_MZN);
        lblfundo.add(txtTotal_USD);
        lblfundo.add(tituloTotal);
        lblfundo.add(lblVenda_MZN);
        lblfundo.add(txtVenda_MZN);
        lblfundo.add(mzn);
        lblfundo.add(usd);
        lblfundo.add(mznT);
        lblfundo.add(usdT);
        lblfundo.add(btnAdicionar);
        lblfundo.add(btnCancelar);
        lblfundo.add(btnSair);
        
        getContentPane().add(lblfundo);
    }
    
    private void configTools() throws SQLException {
        btnAdicionar.addActionListener(this);
        btnCancelar.addActionListener(this);
        btnSair.addActionListener(this);
        
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
        
        lblfundo.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                configListarDesignacao2();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                configListarDesignacao2();
            }
        });
        
        calctotal();
    }
    
    private void configListarDesignacao() {
        DefaultListModel<String> modelo = new DefaultListModel<>();
        listaDesignacao.setModel(modelo);
        String sql = "select * from tbloja_2 where MARCA = ? and CATEGORIA = ? and DESIGNAÇÃO like '%" + txtDesignacao.getText() + "%'";
        
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
                        
                            String sql2 = "select * from tbloja_2 where DESIGNAÇÃO = ?";
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
                txtQuantidade_Disp2.setText("0");
                txtQuantidade_Disp.setText("0");
                txtArtigo_MZN.setText("0.0");
                txtArtigo_USD.setText("0.0");
                txtTotal_MZN.setText("0.0");
                txtTotal_USD.setText("0.0");                
            }
            
            if(txtDesignacao.getText().isEmpty()) {
                scroll_listaDesignacao.setVisible(false);
                txtQuantidade_Disp2.setText("0");
                txtQuantidade_Disp.setText("0");
                txtArtigo_MZN.setText("0.0");
                txtArtigo_USD.setText("0.0");
                txtTotal_MZN.setText("0.0");
                txtTotal_USD.setText("0.0"); 
            }
            }
        } catch(SQLException erro) {
            try {
                this.pst.close();
                this.rs.close();
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Erro no configListarDesignacao() na TelaEdd_Venda_Loja2 \n" + erro, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "Error in configListarDesignacao() at TelaEdd_Venda_Loja2 \n" + erro, "Attention", 0);
                }
            } catch (SQLException ex) {
                Logger.getLogger(TelaEdd_Venda_Loja2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void configListarDesignacao2() {
        String sql = "select * from tbloja_2 where MARCA = ? and CATEGORIA = ? and DESIGNAÇÃO = ?";
        
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtMarca.getText());
            pst.setString(2, (String) txtCategoria.getSelectedItem());
            pst.setString(3, txtDesignacao.getText());
            rs = pst.executeQuery();

            while (rs.next()) {
                quantidade = rs.getInt("QUANTIDADE");
                String unidade = rs.getString("UNIDADE");
                artigo_mzn = rs.getDouble("PRECO_ARTIGO_MZN");
                artigo_usd = rs.getDouble("PRECO_ARTIGO_USD");
                
                modelS.setMaximum(quantidade);
                
                txtUnidade.setText(unidade);
                txtArtigo_MZN.setText(""+artigo_mzn);
                txtArtigo_USD.setText(""+artigo_usd);
                txtQuantidade_Disp.setText(""+txtQuantidade_Disp.getText());
                txtQuantidade_Disp2.setText(""+quantidade);
            }
        } catch(SQLException erro) {
            try {
                this.pst.close();
                this.rs.close();
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Erro no configListarDesignacao() na TelaEdd_Venda_Loja2 \n" + erro, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "Error in configListarDesignacao() at TelaEdd_Venda_Loja2 \n" + erro, "Attention", 0);
                }
            } catch (SQLException ex) {
                Logger.getLogger(TelaEdd_Venda_Loja2.class.getName()).log(Level.SEVERE, null, ex);
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
            try {
                this.pst.close();
                this.rs.close();
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Erro no configListarMarca() TelaEdd_Venda_Loja2 \n" + erro, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "Error in TelaEdd_Venda_Loja2 configListarMarca() \n" + erro, "Attention", 0);
                }
            } catch (SQLException ex) {
                Logger.getLogger(TelaEdd_Venda_Loja2.class.getName()).log(Level.SEVERE, null, ex);
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
            try {
                this.pst.close();
                this.rs.close();
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Erro no txtCategoria() TelaEdd_Venda_Loja2 \n" + erro, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "Error in TelaEdd_Venda_Loja2 txtCategoria() \n" + erro, "Attention", 0);
                }
            } catch (SQLException ex) {
                Logger.getLogger(TelaEdd_Venda_Loja2.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
    }
          
    private void update() throws SQLException {
        String sql = "update tbvenda_2 set designação = ?, marca = ?, categoria = ?, unidade = ?, quantidade = ?, preco_artigo_mzn = ?, preco_artigo_usd = ?, preco_venda = ? where id = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtDesignacao.getText());
            pst.setString(2, txtMarca.getText());
            pst.setString(3, (String) txtCategoria.getSelectedItem());
            pst.setString(4, txtUnidade.getText());
            pst.setInt(5, (int) txtQuantidade.getValue());
            pst.setString(6, txtArtigo_MZN.getText());
            pst.setString(7, txtArtigo_USD.getText());
            pst.setString(8, txtVenda_MZN.getText());
            pst.setString(9, txtID.getText());

            if((txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() == null) & (txtDesignacao.getText().isEmpty()) & (txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0) & (txtVenda_MZN.getText().isEmpty())) {
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
                lblVenda_MZN.setForeground(Color.red);
            } else if((txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() == null) & (txtDesignacao.getText().isEmpty()) & (txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0)) {
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
            } else if((txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() == null) & (txtDesignacao.getText().isEmpty()) & (txtUnidade.getText().isEmpty()) & (txtVenda_MZN.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblMarca.setForeground(Color.red);
                lblCategoria.setForeground(Color.red);
                lblDesignacao.setForeground(Color.red);
                lblUnidade.setForeground(Color.red);
                lblVenda_MZN.setForeground(Color.red);
            } else if((txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() == null) & (txtDesignacao.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0) & (txtVenda_MZN.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblMarca.setForeground(Color.red);
                lblCategoria.setForeground(Color.red);
                lblDesignacao.setForeground(Color.red);
                lblQuantidade.setForeground(Color.red);
                lblVenda_MZN.setForeground(Color.red);
            } else if((txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() == null) & (txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0) & (txtVenda_MZN.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblMarca.setForeground(Color.red);
                lblCategoria.setForeground(Color.red);
                lblUnidade.setForeground(Color.red);
                lblQuantidade.setForeground(Color.red);
                lblVenda_MZN.setForeground(Color.red);
            } else if((txtMarca.getText().isEmpty()) & (txtDesignacao.getText().isEmpty()) & (txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0) & (txtVenda_MZN.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblMarca.setForeground(Color.red);
                lblDesignacao.setForeground(Color.red);
                lblUnidade.setForeground(Color.red);
                lblQuantidade.setForeground(Color.red);
                lblVenda_MZN.setForeground(Color.red);
            } else if((txtCategoria.getSelectedItem() == null) & (txtDesignacao.getText().isEmpty()) & (txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0) & (txtVenda_MZN.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblCategoria.setForeground(Color.red);
                lblDesignacao.setForeground(Color.red);
                lblUnidade.setForeground(Color.red);
                lblQuantidade.setForeground(Color.red);
                lblVenda_MZN.setForeground(Color.red);
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
            } else if((txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() == null) & (txtDesignacao.getText().isEmpty()) & (txtVenda_MZN.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblMarca.setForeground(Color.red);
                lblCategoria.setForeground(Color.red);
                lblDesignacao.setForeground(Color.red);
                lblVenda_MZN.setForeground(Color.red);
            } else if((txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() == null) && (txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0)) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblMarca.setForeground(Color.red);
                lblCategoria.setForeground(Color.red);
                lblUnidade.setForeground(Color.red);
                lblQuantidade.setForeground(Color.red);
            } else if((txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() == null) & (txtUnidade.getText().isEmpty()) & (txtVenda_MZN.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblMarca.setForeground(Color.red);
                lblCategoria.setForeground(Color.red);
                lblUnidade.setForeground(Color.red);
                lblVenda_MZN.setForeground(Color.red);
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
            } else if((txtMarca.getText().isEmpty()) & (txtDesignacao.getText().isEmpty()) & (txtUnidade.getText().isEmpty()) & (txtVenda_MZN.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblMarca.setForeground(Color.red);
                lblDesignacao.setForeground(Color.red);
                lblUnidade.setForeground(Color.red);
                lblVenda_MZN.setForeground(Color.red);
            } else if((txtMarca.getText().isEmpty()) & (txtDesignacao.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0) & (txtVenda_MZN.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                txtMarca.setForeground(Color.red);
                txtDesignacao.setForeground(Color.red);
                txtQuantidade.setForeground(Color.red);
                txtVenda_MZN.setForeground(Color.red);
            } else if((txtMarca.getText().isEmpty()) & (txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0) & (txtVenda_MZN.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblMarca.setForeground(Color.red);
                lblUnidade.setForeground(Color.red);
                lblQuantidade.setForeground(Color.red);
                lblVenda_MZN.setForeground(Color.red);
            } else if((txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() == null) & ((int) txtQuantidade.getValue() == 0) & (txtVenda_MZN.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblMarca.setForeground(Color.red);
                lblCategoria.setForeground(Color.red);
                lblQuantidade.setForeground(Color.red);
                lblVenda_MZN.setForeground(Color.red);
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
            } else if((txtCategoria.getSelectedItem() == null) & (txtDesignacao.getText().isEmpty()) & (txtUnidade.getText().isEmpty()) & (txtVenda_MZN.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblCategoria.setForeground(Color.red);
                lblDesignacao.setForeground(Color.red);
                lblUnidade.setForeground(Color.red);
                lblVenda_MZN.setForeground(Color.red);
            } else if((txtCategoria.getSelectedItem() == null) & (txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0) & (txtVenda_MZN.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblCategoria.setForeground(Color.red);
                lblUnidade.setForeground(Color.red);
                lblQuantidade.setForeground(Color.red);
                lblVenda_MZN.setForeground(Color.red);
            } else if((txtCategoria.getSelectedItem() == null) & (txtDesignacao.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0) & (txtVenda_MZN.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblCategoria.setForeground(Color.red);
                lblDesignacao.setForeground(Color.red);
                lblQuantidade.setForeground(Color.red);
                lblVenda_MZN.setForeground(Color.red);
            } else if((txtDesignacao.getText().isEmpty()) & (txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0) & (txtVenda_MZN.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblDesignacao.setForeground(Color.red);
                lblUnidade.setForeground(Color.red);
                lblQuantidade.setForeground(Color.red);
                lblVenda_MZN.setForeground(Color.red);
            } else if((txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() == null) & (txtDesignacao.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblMarca.setForeground(Color.red);
                lblCategoria.setForeground(Color.red);
                lblDesignacao.setForeground(Color.red);
            } else if((txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() == null) & ((int) txtQuantidade.getValue() == 0)) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblMarca.setForeground(Color.red);
                lblCategoria.setForeground(Color.red);
                lblQuantidade.setForeground(Color.red);
            } else if((txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() == null) & (txtUnidade.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblMarca.setForeground(Color.red);
                lblCategoria.setForeground(Color.red);
                lblUnidade.setForeground(Color.red);
            } else if((txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() == null) & (txtVenda_MZN.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblMarca.setForeground(Color.red);
                lblCategoria.setForeground(Color.red);
                lblVenda_MZN.setForeground(Color.red);
            } else if((txtMarca.getText().isEmpty()) & (txtDesignacao.getText().isEmpty()) & (txtUnidade.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblMarca.setForeground(Color.red);
                lblDesignacao.setForeground(Color.red);
                lblUnidade.setForeground(Color.red);
            } else if((txtMarca.getText().isEmpty()) & (txtDesignacao.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0)) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblMarca.setForeground(Color.red);
                lblDesignacao.setForeground(Color.red);
                lblQuantidade.setForeground(Color.red);
            } else if((txtMarca.getText().isEmpty()) & (txtDesignacao.getText().isEmpty()) & (txtVenda_MZN.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblMarca.setForeground(Color.red);
                lblDesignacao.setForeground(Color.red);
                lblVenda_MZN.setForeground(Color.red);
            } else if((txtMarca.getText().isEmpty()) & (txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0)) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblMarca.setForeground(Color.red);
                lblUnidade.setForeground(Color.red);
                lblQuantidade.setForeground(Color.red);
            } else if((txtMarca.getText().isEmpty()) & (txtUnidade.getText().isEmpty()) & (txtVenda_MZN.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblMarca.setForeground(Color.red);
                lblQuantidade.setForeground(Color.red);
                lblVenda_MZN.setForeground(Color.red);
            } else if((txtCategoria.getSelectedItem() == null) & (txtDesignacao.getText().isEmpty()) & (txtUnidade.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblCategoria.setForeground(Color.red);
                lblDesignacao.setForeground(Color.red);
                lblUnidade.setForeground(Color.red);
            } else if((txtCategoria.getSelectedItem() == null) & (txtDesignacao.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0)) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblCategoria.setForeground(Color.red);
                lblDesignacao.setForeground(Color.red);
                lblQuantidade.setForeground(Color.red);
            } else if((txtCategoria.getSelectedItem() == null) & (txtDesignacao.getText().isEmpty()) & (txtVenda_MZN.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblCategoria.setForeground(Color.red);
                lblDesignacao.setForeground(Color.red);
                lblVenda_MZN.setForeground(Color.red);
            } else if((txtCategoria.getSelectedItem() == null) & (txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0)) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblCategoria.setForeground(Color.red);
                lblUnidade.setForeground(Color.red);
                lblQuantidade.setForeground(Color.red);
            } else if((txtCategoria.getSelectedItem() == null) & (txtUnidade.getText().isEmpty()) & (txtVenda_MZN.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblCategoria.setForeground(Color.red);
                lblUnidade.setForeground(Color.red);
                lblVenda_MZN.setForeground(Color.red);
            } else if((txtCategoria.getSelectedItem() == null) & ((int) txtQuantidade.getValue() == 0) & (txtVenda_MZN.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblCategoria.setForeground(Color.red);
                lblQuantidade.setForeground(Color.red);
                lblVenda_MZN.setForeground(Color.red);
            } else if((txtDesignacao.getText().isEmpty()) & (txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0)) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblDesignacao.setForeground(Color.red);
                lblUnidade.setForeground(Color.red);
                lblQuantidade.setForeground(Color.red);
            } else if((txtDesignacao.getText().isEmpty()) & (txtUnidade.getText().isEmpty()) & (txtVenda_MZN.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblDesignacao.setForeground(Color.red);
                lblUnidade.setForeground(Color.red);
                lblVenda_MZN.setForeground(Color.red);
            } else if((txtDesignacao.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0) & (txtVenda_MZN.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblDesignacao.setForeground(Color.red);
                lblQuantidade.setForeground(Color.red);
                lblVenda_MZN.setForeground(Color.red);
            } else if((txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0) & (txtVenda_MZN.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblUnidade.setForeground(Color.red);
                lblQuantidade.setForeground(Color.red);
                lblVenda_MZN.setForeground(Color.red);
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
                lblUnidade.setForeground(Color.red);
            } else if((txtMarca.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0)) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblMarca.setForeground(Color.red);
                lblQuantidade.setForeground(Color.red);
            } else if((txtMarca.getText().isEmpty()) & (txtVenda_MZN.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblMarca.setForeground(Color.red);
                lblVenda_MZN.setForeground(Color.red);
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
            } else if((txtCategoria.getSelectedItem() == null) & (txtVenda_MZN.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblCategoria.setForeground(Color.red);
                lblVenda_MZN.setForeground(Color.red);
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
            } else if((txtDesignacao.getText().isEmpty()) & (txtVenda_MZN.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblDesignacao.setForeground(Color.red);
                lblVenda_MZN.setForeground(Color.red);
            } else if((txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0)) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblUnidade.setForeground(Color.red);
                lblQuantidade.setForeground(Color.red);
            } else if((txtUnidade.getText().isEmpty()) & (txtVenda_MZN.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblUnidade.setForeground(Color.red);
                lblVenda_MZN.setForeground(Color.red);
            } else if(((int) txtQuantidade.getValue() == 0) & (txtVenda_MZN.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblQuantidade.setForeground(Color.red);
                lblVenda_MZN.setForeground(Color.red);
            } else if(txtMarca.getText().isEmpty()) {
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
            } else if((txtVenda_MZN.getText().isEmpty())) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblVenda_MZN.setForeground(Color.red);
            } else {
                adicionado = pst.executeUpdate();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso !", "Atenção", 1);
                } else {
                    JOptionPane.showMessageDialog(null, "Product updated successfully !", "Attention", 1);
                }
            }
            
            if((!txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() != null) & (!txtDesignacao.getText().isEmpty()) & (!txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() != 0) & (!txtVenda_MZN.getText().isEmpty())) {
                lblMarca.setForeground(Color.black);
                lblCategoria.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
                lblVenda_MZN.setForeground(Color.black);
            } else if((!txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() != null) & (!txtDesignacao.getText().isEmpty()) & (!txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() != 0)) {
                lblMarca.setForeground(Color.black);
                lblCategoria.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            } else if((!txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() != null) & (!txtDesignacao.getText().isEmpty()) & (!txtUnidade.getText().isEmpty()) & (!txtVenda_MZN.getText().isEmpty())) {
                lblMarca.setForeground(Color.black);
                lblCategoria.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
                lblVenda_MZN.setForeground(Color.black);
            } else if((!txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() != null) & (!txtDesignacao.getText().isEmpty()) & ((int) txtQuantidade.getValue() != 0) & (!txtVenda_MZN.getText().isEmpty())) {
                lblMarca.setForeground(Color.black);
                lblCategoria.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
                lblVenda_MZN.setForeground(Color.black);
            } else if((!txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() != null) & (!txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() != 0) & (!txtVenda_MZN.getText().isEmpty())) {
                lblMarca.setForeground(Color.black);
                lblCategoria.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
                lblVenda_MZN.setForeground(Color.black);
            } else if((!txtMarca.getText().isEmpty()) & (!txtDesignacao.getText().isEmpty()) & (!txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() != 0) & (!txtVenda_MZN.getText().isEmpty())) {
                lblMarca.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
                lblVenda_MZN.setForeground(Color.black);
            } else if((txtCategoria.getSelectedItem() != null) & (!txtDesignacao.getText().isEmpty()) & (!txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() != 0) & (!txtVenda_MZN.getText().isEmpty())) {
                lblCategoria.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
                lblVenda_MZN.setForeground(Color.black);
            } else if((!txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() != null) & (!txtDesignacao.getText().isEmpty()) & (!txtUnidade.getText().isEmpty())) {
                lblMarca.setForeground(Color.black);
                lblCategoria.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
            } else if((!txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() != null) & (!txtDesignacao.getText().isEmpty()) & ((int) txtQuantidade.getValue() != 0)) {
                lblMarca.setForeground(Color.black);
                lblCategoria.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            } else if((!txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() != null) & (!txtDesignacao.getText().isEmpty()) & (!txtVenda_MZN.getText().isEmpty())) {
                lblMarca.setForeground(Color.black);
                lblCategoria.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
                lblVenda_MZN.setForeground(Color.black);
            } else if((!txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() != null) && (!txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() != 0)) {
                lblMarca.setForeground(Color.black);
                lblCategoria.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            } else if((!txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() != null) & (!txtUnidade.getText().isEmpty()) & (!txtVenda_MZN.getText().isEmpty())) {
                lblMarca.setForeground(Color.black);
                lblCategoria.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
                lblVenda_MZN.setForeground(Color.black);
            } else if((!txtMarca.getText().isEmpty()) & (!txtDesignacao.getText().isEmpty()) & (!txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() != 0)) {
                lblMarca.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            } else if((!txtMarca.getText().isEmpty()) & (!txtDesignacao.getText().isEmpty()) & (!txtUnidade.getText().isEmpty()) & (!txtVenda_MZN.getText().isEmpty())) {
                lblMarca.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
                lblVenda_MZN.setForeground(Color.black);
            } else if((!txtMarca.getText().isEmpty()) & (!txtDesignacao.getText().isEmpty()) & ((int) txtQuantidade.getValue() != 0) & (!txtVenda_MZN.getText().isEmpty())) {
                txtMarca.setForeground(Color.black);
                txtDesignacao.setForeground(Color.black);
                txtQuantidade.setForeground(Color.black);
                txtVenda_MZN.setForeground(Color.black);
            } else if((!txtMarca.getText().isEmpty()) & (!txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() != 0) & (!txtVenda_MZN.getText().isEmpty())) {
                lblMarca.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
                lblVenda_MZN.setForeground(Color.black);
            } else if((!txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() != null) & ((int) txtQuantidade.getValue() != 0) & (!txtVenda_MZN.getText().isEmpty())) {
                lblMarca.setForeground(Color.black);
                lblCategoria.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
                lblVenda_MZN.setForeground(Color.black);
            } else if((txtCategoria.getSelectedItem() != null) & (!txtDesignacao.getText().isEmpty()) & (!txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() != 0)) {
                lblCategoria.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            } else if((txtCategoria.getSelectedItem() != null) & (!txtDesignacao.getText().isEmpty()) & (!txtUnidade.getText().isEmpty()) & (!txtVenda_MZN.getText().isEmpty())) {
                lblCategoria.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
                lblVenda_MZN.setForeground(Color.black);
            } else if((txtCategoria.getSelectedItem() != null) & (!txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() != 0) & (!txtVenda_MZN.getText().isEmpty())) {
                lblCategoria.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
                lblVenda_MZN.setForeground(Color.black);
            } else if((txtCategoria.getSelectedItem() != null) & (!txtDesignacao.getText().isEmpty()) & ((int) txtQuantidade.getValue() != 0) & (!txtVenda_MZN.getText().isEmpty())) {
                lblCategoria.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
                lblVenda_MZN.setForeground(Color.black);
            } else if((!txtDesignacao.getText().isEmpty()) & (!txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() != 0) & (!txtVenda_MZN.getText().isEmpty())) {
                lblDesignacao.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
                lblVenda_MZN.setForeground(Color.black);
            } else if((!txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() != null) & (!txtDesignacao.getText().isEmpty())) {
                lblMarca.setForeground(Color.black);
                lblCategoria.setForeground(Color.red);
                lblDesignacao.setForeground(Color.black);
            } else if((!txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() != null) & ((int) txtQuantidade.getValue() != 0)) {
                lblMarca.setForeground(Color.black);
                lblCategoria.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            } else if((!txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() != null) & (!txtUnidade.getText().isEmpty())) {
                lblMarca.setForeground(Color.black);
                lblCategoria.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
            } else if((!txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() != null) & (!txtVenda_MZN.getText().isEmpty())) {
                lblMarca.setForeground(Color.black);
                lblCategoria.setForeground(Color.black);
                lblVenda_MZN.setForeground(Color.black);
            } else if((!txtMarca.getText().isEmpty()) & (!txtDesignacao.getText().isEmpty()) & (!txtUnidade.getText().isEmpty())) {
                lblMarca.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
            } else if((!txtMarca.getText().isEmpty()) & (!txtDesignacao.getText().isEmpty()) & ((int) txtQuantidade.getValue() != 0)) {
                lblMarca.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            } else if((!txtMarca.getText().isEmpty()) & (!txtDesignacao.getText().isEmpty()) & (!txtVenda_MZN.getText().isEmpty())) {
                lblMarca.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
                lblVenda_MZN.setForeground(Color.black);
            } else if((!txtMarca.getText().isEmpty()) & (!txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() != 0)) {
                lblMarca.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            } else if((!txtMarca.getText().isEmpty()) & (!txtUnidade.getText().isEmpty()) & (!txtVenda_MZN.getText().isEmpty())) {
                lblMarca.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
                lblVenda_MZN.setForeground(Color.black);
            } else if((txtCategoria.getSelectedItem() != null) & (!txtDesignacao.getText().isEmpty()) & (!txtUnidade.getText().isEmpty())) {
                lblCategoria.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
            } else if((txtCategoria.getSelectedItem() != null) & (!txtDesignacao.getText().isEmpty()) & ((int) txtQuantidade.getValue() != 0)) {
                lblCategoria.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            } else if((txtCategoria.getSelectedItem() != null) & (!txtDesignacao.getText().isEmpty()) & (!txtVenda_MZN.getText().isEmpty())) {
                lblCategoria.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
                lblVenda_MZN.setForeground(Color.black);
            } else if((txtCategoria.getSelectedItem() != null) & (!txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() != 0)) {
                lblCategoria.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            } else if((txtCategoria.getSelectedItem() != null) & (!txtUnidade.getText().isEmpty()) & (!txtVenda_MZN.getText().isEmpty())) {
                lblCategoria.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
                lblVenda_MZN.setForeground(Color.black);
            } else if((txtCategoria.getSelectedItem() != null) & ((int) txtQuantidade.getValue() != 0) & (!txtVenda_MZN.getText().isEmpty())) {
                lblCategoria.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
                lblVenda_MZN.setForeground(Color.black);
            } else if((!txtDesignacao.getText().isEmpty()) & (!txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() != 0)) {
                lblDesignacao.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            } else if((!txtDesignacao.getText().isEmpty()) & (!txtUnidade.getText().isEmpty()) & (!txtVenda_MZN.getText().isEmpty())) {
                lblDesignacao.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
                lblVenda_MZN.setForeground(Color.black);
            } else if((!txtDesignacao.getText().isEmpty()) & ((int) txtQuantidade.getValue() != 0) & (!txtVenda_MZN.getText().isEmpty())) {
                lblDesignacao.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
                lblVenda_MZN.setForeground(Color.black);
            } else if((!txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() != 0) & (!txtVenda_MZN.getText().isEmpty())) {
                lblUnidade.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
                lblVenda_MZN.setForeground(Color.black);
            } else if((!txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() != null)) {
                lblMarca.setForeground(Color.black);
                lblCategoria.setForeground(Color.black);
            } else if((!txtMarca.getText().isEmpty()) & (!txtDesignacao.getText().isEmpty())) {
                lblMarca.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
            } else if((!txtMarca.getText().isEmpty()) & (!txtUnidade.getText().isEmpty())) {
                lblMarca.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
            } else if((!txtMarca.getText().isEmpty()) & ((int) txtQuantidade.getValue() != 0)) {
                lblMarca.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            } else if((!txtMarca.getText().isEmpty()) & (!txtVenda_MZN.getText().isEmpty())) {
                lblMarca.setForeground(Color.black);
                lblVenda_MZN.setForeground(Color.black);
            } else if((txtCategoria.getSelectedItem() != null) & (!txtDesignacao.getText().isEmpty())) {
                lblCategoria.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
            } else if((txtCategoria.getSelectedItem() != null) & (!txtUnidade.getText().isEmpty())) {
                lblCategoria.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
            } else if((txtCategoria.getSelectedItem() != null) & ((int) txtQuantidade.getValue() != 0)) {
                lblCategoria.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            } else if((txtCategoria.getSelectedItem() != null) & (!txtVenda_MZN.getText().isEmpty())) {
                lblCategoria.setForeground(Color.black);
                lblVenda_MZN.setForeground(Color.black);
            } else if((!txtDesignacao.getText().isEmpty()) & (!txtUnidade.getText().isEmpty())) {
                lblDesignacao.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
            } else if((!txtDesignacao.getText().isEmpty()) & ((int) txtQuantidade.getValue() != 0)) {
                lblDesignacao.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            } else if((!txtDesignacao.getText().isEmpty()) & (!txtVenda_MZN.getText().isEmpty())) {
                lblDesignacao.setForeground(Color.black);
                lblVenda_MZN.setForeground(Color.black);
            } else if((!txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() != 0)) {
                lblUnidade.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            } else if((!txtUnidade.getText().isEmpty()) & (!txtVenda_MZN.getText().isEmpty())) {
                lblUnidade.setForeground(Color.black);
                lblVenda_MZN.setForeground(Color.black);
            } else if(((int) txtQuantidade.getValue() != 0) & (!txtVenda_MZN.getText().isEmpty())) {
                lblQuantidade.setForeground(Color.black);
                lblVenda_MZN.setForeground(Color.black);
            } else if(!txtMarca.getText().isEmpty()) {
                lblMarca.setForeground(Color.black);
            } else if(txtCategoria.getSelectedItem() != null) {
                lblCategoria.setForeground(Color.black);
            } else if(!txtDesignacao.getText().isEmpty()) {
                lblDesignacao.setForeground(Color.black);
            } else if(!txtUnidade.getText().isEmpty()) {
                lblUnidade.setForeground(Color.black);
            } else if((int) txtQuantidade.getValue() != 0) {
                lblQuantidade.setForeground(Color.black);
            } else if(!txtVenda_MZN.getText().isEmpty()) {
                lblVenda_MZN.setForeground(Color.black);
            }   
                                                            
            if (adicionado > 0) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Atualizado !", "Atenção", 1);
                    conexao.close();
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Updated !", "Attention", 1);
                    conexao.close();
                    this.dispose();
                }
                limparTela();
            }        
        } catch (HeadlessException | SQLException erro) {
                this.pst.close();
                this.conexao.close();
            if(Idiomas.getPort() == true) {
                JOptionPane.showMessageDialog(null, erro+" MENSAGEM DE ERRO AO TENTAR SALVAR UMA VENDA na TelaEdd_Venda_Loja2 !", "AVISO", 0);
            } else {
                JOptionPane.showMessageDialog(null, erro+" ERROR MESSAGE WHEN TRYING TO SAVE A SALLING IN TelaEdd_Venda_Loja2!", "WARNING", 0);
            }
        }       
    }
                   
    private void limparTela() {
        txtID.setText(null);
        txtDesignacao.setText(null);
        txtMarca.setText(null);
        txtCategoria.setSelectedItem(null);
        txtUnidade.setText(null);
        txtQuantidade_Disp.setText("0");
        txtQuantidade_Disp2.setText("0");
        txtQuantidade.setValue(0);
        txtArtigo_MZN.setText("0.0");
        txtArtigo_USD.setText("0.0");
        txtVenda_MZN.setText(null);
        scroll_listaDesignacao.setVisible(false);
        scroll_listaMarca.setVisible(false);
    }
    
        private void validar() {
            if((txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() == null) & (txtDesignacao.getText().isEmpty()) & (txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0) & (txtVenda_MZN.getText().isEmpty())) {
                lblMarca.setForeground(Color.black);
                lblCategoria.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
                lblVenda_MZN.setForeground(Color.black);
            } else if((txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() == null) & (txtDesignacao.getText().isEmpty()) & (txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0)) {
                lblMarca.setForeground(Color.black);
                lblCategoria.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            } else if((txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() == null) & (txtDesignacao.getText().isEmpty()) & (txtUnidade.getText().isEmpty()) & (txtVenda_MZN.getText().isEmpty())) {
                lblMarca.setForeground(Color.black);
                lblCategoria.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
                lblVenda_MZN.setForeground(Color.black);
            } else if((txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() == null) & (txtDesignacao.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0) & (txtVenda_MZN.getText().isEmpty())) {
                lblMarca.setForeground(Color.black);
                lblCategoria.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
                lblVenda_MZN.setForeground(Color.black);
            } else if((txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() == null) & (txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0) & (txtVenda_MZN.getText().isEmpty())) {
                lblMarca.setForeground(Color.black);
                lblCategoria.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
                lblVenda_MZN.setForeground(Color.black);
            } else if((txtMarca.getText().isEmpty()) & (txtDesignacao.getText().isEmpty()) & (txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0) & (txtVenda_MZN.getText().isEmpty())) {
                lblMarca.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
                lblVenda_MZN.setForeground(Color.black);
            } else if((txtCategoria.getSelectedItem() == null) & (txtDesignacao.getText().isEmpty()) & (txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0) & (txtVenda_MZN.getText().isEmpty())) {
                lblCategoria.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
                lblVenda_MZN.setForeground(Color.black);
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
            } else if((txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() == null) & (txtDesignacao.getText().isEmpty()) & (txtVenda_MZN.getText().isEmpty())) {
                lblMarca.setForeground(Color.black);
                lblCategoria.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
                lblVenda_MZN.setForeground(Color.black);
            } else if((txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() == null) && (txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0)) {
                lblMarca.setForeground(Color.black);
                lblCategoria.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            } else if((txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() == null) & (txtUnidade.getText().isEmpty()) & (txtVenda_MZN.getText().isEmpty())) {
                lblMarca.setForeground(Color.black);
                lblCategoria.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
                lblVenda_MZN.setForeground(Color.black);
            } else if((txtMarca.getText().isEmpty()) & (txtDesignacao.getText().isEmpty()) & (txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0)) {
                lblMarca.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            } else if((txtMarca.getText().isEmpty()) & (txtDesignacao.getText().isEmpty()) & (txtUnidade.getText().isEmpty()) & (txtVenda_MZN.getText().isEmpty())) {
                lblMarca.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
                lblVenda_MZN.setForeground(Color.black);
            } else if((txtMarca.getText().isEmpty()) & (txtDesignacao.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0) & (txtVenda_MZN.getText().isEmpty())) {
                txtMarca.setForeground(Color.black);
                txtDesignacao.setForeground(Color.black);
                txtQuantidade.setForeground(Color.black);
                txtVenda_MZN.setForeground(Color.black);
            } else if((txtMarca.getText().isEmpty()) & (txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0) & (txtVenda_MZN.getText().isEmpty())) {
                lblMarca.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
                lblVenda_MZN.setForeground(Color.black);
            } else if((txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() == null) & ((int) txtQuantidade.getValue() == 0) & (txtVenda_MZN.getText().isEmpty())) {
                lblMarca.setForeground(Color.black);
                lblCategoria.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
                lblVenda_MZN.setForeground(Color.black);
            } else if((txtCategoria.getSelectedItem() == null) & (txtDesignacao.getText().isEmpty()) & (txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0)) {
                lblCategoria.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            } else if((txtCategoria.getSelectedItem() != null) & (txtDesignacao.getText().isEmpty()) & (txtUnidade.getText().isEmpty()) & (txtVenda_MZN.getText().isEmpty())) {
                lblCategoria.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
                lblVenda_MZN.setForeground(Color.black);
            } else if((txtCategoria.getSelectedItem() != null) & (txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0) & (txtVenda_MZN.getText().isEmpty())) {
                lblCategoria.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
                lblVenda_MZN.setForeground(Color.black);
            } else if((txtCategoria.getSelectedItem() != null) & (txtDesignacao.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0) & (txtVenda_MZN.getText().isEmpty())) {
                lblCategoria.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
                lblVenda_MZN.setForeground(Color.black);
            } else if((txtDesignacao.getText().isEmpty()) & (txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0) & (txtVenda_MZN.getText().isEmpty())) {
                lblDesignacao.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
                lblVenda_MZN.setForeground(Color.black);
            } else if((txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() == null) & (txtDesignacao.getText().isEmpty())) {
                lblMarca.setForeground(Color.black);
                lblCategoria.setForeground(Color.red);
                lblDesignacao.setForeground(Color.black);
            } else if((txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() == null) & ((int) txtQuantidade.getValue() == 0)) {
                lblMarca.setForeground(Color.black);
                lblCategoria.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            } else if((txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() == null) & (txtUnidade.getText().isEmpty())) {
                lblMarca.setForeground(Color.black);
                lblCategoria.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
            } else if((txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() == null) & (txtVenda_MZN.getText().isEmpty())) {
                lblMarca.setForeground(Color.black);
                lblCategoria.setForeground(Color.black);
                lblVenda_MZN.setForeground(Color.black);
            } else if((txtMarca.getText().isEmpty()) & (txtDesignacao.getText().isEmpty()) & (txtUnidade.getText().isEmpty())) {
                lblMarca.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
            } else if((txtMarca.getText().isEmpty()) & (txtDesignacao.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0)) {
                lblMarca.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            } else if((txtMarca.getText().isEmpty()) & (txtDesignacao.getText().isEmpty()) & (txtVenda_MZN.getText().isEmpty())) {
                lblMarca.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
                lblVenda_MZN.setForeground(Color.black);
            } else if((txtMarca.getText().isEmpty()) & (txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0)) {
                lblMarca.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            } else if((txtMarca.getText().isEmpty()) & (txtUnidade.getText().isEmpty()) & (txtVenda_MZN.getText().isEmpty())) {
                lblMarca.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
                lblVenda_MZN.setForeground(Color.black);
            } else if((txtCategoria.getSelectedItem() == null) & (txtDesignacao.getText().isEmpty()) & (txtUnidade.getText().isEmpty())) {
                lblCategoria.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
            } else if((txtCategoria.getSelectedItem() == null) & (txtDesignacao.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0)) {
                lblCategoria.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            } else if((txtCategoria.getSelectedItem() == null) & (txtDesignacao.getText().isEmpty()) & (txtVenda_MZN.getText().isEmpty())) {
                lblCategoria.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
                lblVenda_MZN.setForeground(Color.black);
            } else if((txtCategoria.getSelectedItem() == null) & (txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0)) {
                lblCategoria.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            } else if((txtCategoria.getSelectedItem() == null) & (txtUnidade.getText().isEmpty()) & (txtVenda_MZN.getText().isEmpty())) {
                lblCategoria.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
                lblVenda_MZN.setForeground(Color.black);
            } else if((txtCategoria.getSelectedItem() == null) & ((int) txtQuantidade.getValue() == 0) & (txtVenda_MZN.getText().isEmpty())) {
                lblCategoria.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
                lblVenda_MZN.setForeground(Color.black);
            } else if((txtDesignacao.getText().isEmpty()) & (txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0)) {
                lblDesignacao.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            } else if((txtDesignacao.getText().isEmpty()) & (txtUnidade.getText().isEmpty()) & (txtVenda_MZN.getText().isEmpty())) {
                lblDesignacao.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
                lblVenda_MZN.setForeground(Color.black);
            } else if((txtDesignacao.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0) & (txtVenda_MZN.getText().isEmpty())) {
                lblDesignacao.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
                lblVenda_MZN.setForeground(Color.black);
            } else if((txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0) & (txtVenda_MZN.getText().isEmpty())) {
                lblUnidade.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
                lblVenda_MZN.setForeground(Color.black);
            } else if((txtMarca.getText().isEmpty()) & (txtCategoria.getSelectedItem() == null)) {
                lblMarca.setForeground(Color.black);
                lblCategoria.setForeground(Color.black);
            } else if((txtMarca.getText().isEmpty()) & (txtDesignacao.getText().isEmpty())) {
                lblMarca.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
            } else if((txtMarca.getText().isEmpty()) & (txtUnidade.getText().isEmpty())) {
                lblMarca.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
            } else if((txtMarca.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0)) {
                lblMarca.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            } else if((txtMarca.getText().isEmpty()) & (txtVenda_MZN.getText().isEmpty())) {
                lblMarca.setForeground(Color.black);
                lblVenda_MZN.setForeground(Color.black);
            } else if((txtCategoria.getSelectedItem() == null) & (txtDesignacao.getText().isEmpty())) {
                lblCategoria.setForeground(Color.black);
                lblDesignacao.setForeground(Color.black);
            } else if((txtCategoria.getSelectedItem() == null) & (txtUnidade.getText().isEmpty())) {
                lblCategoria.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
            } else if((txtCategoria.getSelectedItem() == null) & ((int) txtQuantidade.getValue() == 0)) {
                lblCategoria.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            } else if((txtCategoria.getSelectedItem() == null) & (txtVenda_MZN.getText().isEmpty())) {
                lblCategoria.setForeground(Color.black);
                lblVenda_MZN.setForeground(Color.black);
            } else if((txtDesignacao.getText().isEmpty()) & (txtUnidade.getText().isEmpty())) {
                lblDesignacao.setForeground(Color.black);
                lblUnidade.setForeground(Color.black);
            } else if((txtDesignacao.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0)) {
                lblDesignacao.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            } else if((txtDesignacao.getText().isEmpty()) & (txtVenda_MZN.getText().isEmpty())) {
                lblDesignacao.setForeground(Color.black);
                lblVenda_MZN.setForeground(Color.black);
            } else if((txtUnidade.getText().isEmpty()) & ((int) txtQuantidade.getValue() == 0)) {
                lblUnidade.setForeground(Color.black);
                lblQuantidade.setForeground(Color.black);
            } else if((txtUnidade.getText().isEmpty()) & (txtVenda_MZN.getText().isEmpty())) {
                lblUnidade.setForeground(Color.black);
                lblVenda_MZN.setForeground(Color.black);
            } else if(((int) txtQuantidade.getValue() == 0) & (txtVenda_MZN.getText().isEmpty())) {
                lblQuantidade.setForeground(Color.black);
                lblVenda_MZN.setForeground(Color.black);
            } else if(txtMarca.getText().isEmpty()) {
                lblMarca.setForeground(Color.black);
            } else if(txtCategoria.getSelectedItem() == null) {
                lblCategoria.setForeground(Color.black);
            } else if(txtDesignacao.getText().isEmpty()) {
                lblDesignacao.setForeground(Color.black);
            } else if(txtUnidade.getText().isEmpty()) {
                lblUnidade.setForeground(Color.black);
            } else if((int) txtQuantidade.getValue() == 0) {
                lblQuantidade.setForeground(Color.black);
            } else if(txtVenda_MZN.getText().isEmpty()) {
                lblVenda_MZN.setForeground(Color.black);
            }
        }
    
    @Override
    public void actionPerformed(ActionEvent evt) {
        if(evt.getSource() == btnAdicionar) {
            try {            
                update();
            } catch (SQLException ex) {
                Logger.getLogger(TelaEdd_Venda_Loja2.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if(evt.getSource() == btnCancelar) {
            limparTela();  
            validar();
        } else if(evt.getSource() == btnSair) {
            if(Idiomas.getPort() == true) {
                sair = JOptionPane.showConfirmDialog(null, "Têm certeza que deseja sair?", "Atenção", JOptionPane.YES_NO_OPTION);
                if(sair == JOptionPane.YES_OPTION) {
                    try {
                        conexao.close();
                        this.dispose();
                    } catch (SQLException ex) {
                        Logger.getLogger(TelaEdd_Venda_Loja2.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                sair = JOptionPane.showConfirmDialog(null, "Are you sure you want to leave?", "Attention", JOptionPane.YES_NO_OPTION);
                if(sair == JOptionPane.YES_OPTION) {
                    try {
                        conexao.close();
                        this.dispose();
                    } catch (SQLException ex) {
                        Logger.getLogger(TelaEdd_Venda_Loja2.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
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
        lblVenda_MZN.setText("Preço de venda");
        btnAdicionar.setText("Atualizar");
        btnCancelar.setText("Cancelar");
        lbltitulo.setText("ATUALIZAR VENDAS");
        lblQuantidade_Disp.setText("Quantidade inserida : ");
        lblQuantidade_Disp2.setText("Quantidade disponível : ");
        tituloVenda.setText("Preço do artigo : ");
        tituloTotal.setText("Preço total : ");
        btnSair.setText("Sair");
    }

    @Override
    public void configEng() {
        lblDesignacao.setText("Designation");
        lblMarca.setText("Code");
        lblCategoria.setText("Category");
        lblUnidade.setText("Unit");    
        lblQuantidade.setText("Quantity");
        lblVenda_MZN.setText("Selling price");
        btnAdicionar.setText("Update");
        btnCancelar.setText("Cancel");
        lbltitulo.setText("SELLING EDIT");
        lblQuantidade_Disp.setText("Quantity inserted : ");
        lblQuantidade_Disp2.setText("Available quantity : ");
        tituloVenda.setText("Article price : ");
        tituloTotal.setText("Total price : ");
        btnSair.setText("Exit");
    }
}