package telas;

import Abstrato.Config_idiomas;
import Abstrato.Config_remover;
import javax.swing.*;
import java.sql.*;
import dao.ConexaoDAO;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.border.MatteBorder;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Ramadan ismaeL
 */

public final class TelaVenda_Loja1 extends JInternalFrame implements ActionListener, Config_idiomas, Config_remover {
    private JLabel lbltitulo, lblpesquisa, lblloja_1, lbltotal;
    private JTextField txtpesquisa, txtquant, txtPreco_artigo_mzn, txtPreco_total_mzn, txtPreco_venda_mzn, txtPreco_lucro;
    private JButton btnAdicionar, btnEditar, btnRemover, btnDetalhes, btnAtualizar;
    private JTable tabela;
    private JScrollPane scroll;
    private Connection conexao = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private DefaultTableModel model;
    private int deleteItem, soma = 0;
    private double somaVlr = 0.00;
    private TelaVenda_Loja1_Detalhes telaVendaDetalhes = null;
    private TelaAdd_Venda_Loja1 telaAddVenda = null;
    private TelaEdd_Venda_Loja1 telaEddVenda = null;
    
    public TelaVenda_Loja1() throws SQLException {
        conexao = ConexaoDAO.conector();
        
        Janela();
        configView();
        
        if(Idiomas.getPort() == true) {
            configPort();
        } else {
            configEng();
        }
        
        tabelaDAO();
        
        setVisible(false);
    }
    
    private void Janela() {
        setTitle(" ");
        setSize(1320, 833);
        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    private void configView() {
        setLayout(null);
        
        lbltitulo = new JLabel();
        lblpesquisa = new JLabel();
        lblloja_1 = new JLabel();
        lbltotal = new JLabel();
        
        txtpesquisa = new JTextField();
        txtquant = new JTextField();
        txtPreco_artigo_mzn = new JTextField();
        txtPreco_total_mzn = new JTextField();
        txtPreco_venda_mzn = new JTextField();
        txtPreco_lucro = new JTextField();
        
        btnAdicionar = new JButton();
        btnEditar = new JButton();
        btnRemover = new JButton();
        btnDetalhes = new JButton();
        btnAtualizar = new JButton();
        
        tabela = new JTable();
        
        scroll = new JScrollPane();
                
        lbltitulo.setForeground(new Color(0, 191, 255));
        lbltitulo.setBounds(5, 0, 200, 30);
                
        lblloja_1.setFont(new Font("Stencil", 0, 18));
        lblloja_1.setForeground(Color.BLACK);
        lblloja_1.setBounds(1230, 2, 150, 21);
        
        btnAdicionar.setIcon(new ImageIcon(this.getClass().getResource("/icones/adicionar-produto.png")));
        btnAdicionar.setBounds(5, 90, 322, 30);
        
        btnEditar.setIcon(new ImageIcon(this.getClass().getResource("/icones/editar.png")));
        btnEditar.setBounds(331, 90, 322, 30);
        
        btnRemover.setIcon(new ImageIcon(this.getClass().getResource("/icones/remover.png")));
        btnRemover.setBounds(658, 90, 322, 30);
        
        btnDetalhes.setIcon(new ImageIcon(this.getClass().getResource("/icones/detalhes.png")));
        btnDetalhes.setBounds(985, 90, 322, 30);
        
        lblpesquisa.setIcon(new ImageIcon(this.getClass().getResource("/icones/lupa.png")));
        lblpesquisa.setBounds(405, 130, 28, 28);
        
        txtpesquisa.setText(null);
        txtpesquisa.setFont(new Font("Segoe UI", 0, 15));
        txtpesquisa.setForeground(Color.BLACK);
        txtpesquisa.setBounds(5, 130, 400, 28);
        
        btnAtualizar.setIcon(new ImageIcon(this.getClass().getResource("/icones/atualizar.png")));
        btnAtualizar.setBounds(1268, 130, 50, 27);
        btnAtualizar.setOpaque(false); //Torna o botão visível ou transparente
        btnAtualizar.setBackground(new Color(135, 206, 250));
        btnAtualizar.setBorder(new MatteBorder(0, 0, 0, 0, Color.WHITE));

        if(Idiomas.getPort() == true) {
            String[] nome = {"DESIGNAÇÃO", "MARCA", "CATEGORIA", "UNIDADE", "QUANT.", "VLR_VENDA(MZN)", "VLR_TOTAL(MZN)", "DESCONTO", "VLR_PAGO_MZN", "VENDIDO_POR", "DATA & HORA"};
            String dados[][] = {};        
            tabela = new JTable(dados, nome) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        } else {
            String[] nome = {"ITEM DESCRIPTION", "CODE", "CATEGORY", "UNIT", "QUANT.", "SELLING_PRICE(MZN)", "TOTAL_PRICE(MZN)", "DISCOUNT", "PAID_PRICE(MZN)", "SOULD_OUT_BY", "DATE & TIME"};
            String dados[][] = {};        
            tabela = new JTable(dados, nome) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        }       
               
        scroll.setViewportView(tabela);
        scroll.setBounds(5, 160, 1303, 598);
        
        lbltotal.setText("TOTAL");
        lbltotal.setFont(new Font("Times New Roman", 1, 18));
        lbltotal.setForeground(Color.BLACK);
        lbltotal.setBounds(450, 762, 100, 35);
        
        txtquant.setText("qty");
        txtquant.setFont(new Font("Agency FB", 1, 17));
        txtquant.setForeground(Color.BLACK);
        txtquant.setBackground(new Color(220, 220, 220));
        txtquant.setBounds(562, 762, 55, 35);
        txtquant.setEditable(false);
        
        txtPreco_artigo_mzn.setText("artigo MZN");
        txtPreco_artigo_mzn.setFont(new Font("Agency FB", 1, 17));
        txtPreco_artigo_mzn.setForeground(Color.BLACK);
        txtPreco_artigo_mzn.setBackground(new Color(220, 220, 220));
        txtPreco_artigo_mzn.setBounds(620, 762, 130, 35);
        txtPreco_artigo_mzn.setEditable(false);
        
        txtPreco_total_mzn.setText("total MZN");
        txtPreco_total_mzn.setFont(new Font("Agency FB", 1, 17));
        txtPreco_total_mzn.setForeground(Color.BLACK);
        txtPreco_total_mzn.setBackground(new Color(220, 220, 220));
        txtPreco_total_mzn.setBounds(753, 762, 130, 35);
        txtPreco_total_mzn.setEditable(false);
        
        txtPreco_venda_mzn.setText("venda MZN");
        txtPreco_venda_mzn.setFont(new Font("Agency FB", 1, 17));
        txtPreco_venda_mzn.setForeground(Color.BLACK);
        txtPreco_venda_mzn.setBackground(new Color(220, 220, 220));
        txtPreco_venda_mzn.setBounds(886, 762, 130, 35);
        txtPreco_venda_mzn.setEditable(false);
        
        txtPreco_lucro.setText("lucro MZN");
        txtPreco_lucro.setFont(new Font("Agency FB", 1, 17));
        txtPreco_lucro.setForeground(Color.BLACK);
        txtPreco_lucro.setBackground(new Color(220, 220, 220));
        txtPreco_lucro.setBounds(1019, 762, 130, 35);
        txtPreco_lucro.setEditable(false);
                
        configTools();        
        
        getContentPane().add(lbltitulo);
        getContentPane().add(lblloja_1);
        getContentPane().add(btnAdicionar);
        getContentPane().add(btnEditar);
        getContentPane().add(btnRemover);
        getContentPane().add(btnDetalhes);
        getContentPane().add(btnAtualizar);
        getContentPane().add(lblpesquisa);
        getContentPane().add(txtpesquisa);
        getContentPane().add(scroll);
        getContentPane().add(lbltotal);
        getContentPane().add(txtquant);
        getContentPane().add(txtPreco_artigo_mzn);
        getContentPane().add(txtPreco_total_mzn);
        getContentPane().add(txtPreco_venda_mzn);
        getContentPane().add(txtPreco_lucro);
    }
    
    private void configTools() {
        btnAdicionar.addActionListener(this);
        btnEditar.addActionListener(this);
        btnRemover.addActionListener(this);
        btnAtualizar.addActionListener(this);
        btnDetalhes.addActionListener(this);
        
        txtpesquisa.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                pesquisar();
                pesquisar_calc_Quant();
                pesquisar_calc_Preco_Artigo_MZN();
                pesquisar_calc_Preco_Total_MZN();  
                pesquisar_calc_Preco_Venda_MZN();
                pesquisar_calc_Preco_Lucro_MZN();
            }
        });
        
        configCalc();
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if(evt.getSource() == btnRemover) {
            int[] sr = tabela.getSelectedRows();
            if(tabela.getSelectedRow() == -1) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Por favor, selecione alguma venda na tabela !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a product from the table !", "Attention", 2);
                }                
                } else if(sr.length == 1) {
                    remover1();
                } else if(sr.length == 2) {
                    remover2();
                } else if(sr.length == 3) {
                    remover3();
                } else if(sr.length == 4) {
                    remover4();
                } else if(sr.length == 5) {
                    remover5();
                } else if(sr.length == 6) {
                    remover6();
                } else if(sr.length == 7) {
                    remover7();
                } else if(sr.length == 8) {
                    remover8();
                } else if(sr.length == 9) {
                    remover9();
                } else if(sr.length == 10) {
                    remover10();
                } else {
                    if(Idiomas.getPort() == true) {
                        JOptionPane.showMessageDialog(null, "Você só pode remover 10 itens no máximo, por favor, seleciona no máximo 10 itens !", "Atenção", 1);
                    } else {
                        JOptionPane.showMessageDialog(null, "You can only remove a maximum of 10 items, please select up to 10 items !", "Attention", 1);
                    }
                }
        } else if(evt.getSource() == btnAdicionar) {
            SwingUtilities.invokeLater(() -> {
                if(telaAddVenda== null || !telaAddVenda.isVisible()) {
                    try {
                        telaAddVenda = new TelaAdd_Venda_Loja1();
                    } catch (SQLException ex) {
                        Logger.getLogger(TelaVenda_Loja1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    telaAddVenda.setVisible(true);
                    telaAddVenda.toFront();
                } else {
                    telaAddVenda.toFront();
                }
            });
        } else if(evt.getSource() == btnEditar) {
            editar();
        } else if(evt.getSource() == btnAtualizar) {
            tabelaDAO();
            configCalc();
            if(Idiomas.getPort() == true) {
                JOptionPane.showMessageDialog(null, "Atualizado !", "Atenção", 1);
            } else {
                JOptionPane.showMessageDialog(null, "Updated !", "Attention", 1);
            }
        } else if(evt.getSource() == btnDetalhes) {
            SwingUtilities.invokeLater(() -> {
                if(telaVendaDetalhes == null || !telaVendaDetalhes.isVisible()) {
                    try {
                        telaVendaDetalhes = new TelaVenda_Loja1_Detalhes();
                    } catch (SQLException ex) {
                        Logger.getLogger(TelaVenda_Loja1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    telaVendaDetalhes.setVisible(true);
                    telaVendaDetalhes.toFront();
                } else {
                    telaVendaDetalhes.toFront();
                }
            });
        }
    }
    
    private void tabelaDAO() {
        tabelaDAOProced();
        String sql = "SELECT ID, DESIGNAÇÃO, MARCA, CATEGORIA, UNIDADE, QUANTIDADE, PRECO_ARTIGO_MZN, PRECO_TOTAL_MZN, PRECO_VENDA, LUCRO, DATA_HORA FROM tbvenda_1 WHERE DATA_HORA >= CURDATE()";

        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            
            tabela.setModel(DbUtils.resultSetToTableModel(rs));
            
            tabela.getColumnModel().getColumn(0).setPreferredWidth(40);
            tabela.getColumnModel().getColumn(1).setPreferredWidth(221);
            tabela.getColumnModel().getColumn(2).setPreferredWidth(80);
            tabela.getColumnModel().getColumn(3).setPreferredWidth(55);
            tabela.getColumnModel().getColumn(4).setPreferredWidth(50);
            tabela.getColumnModel().getColumn(5).setPreferredWidth(35);
            tabela.getColumnModel().getColumn(6).setPreferredWidth(110);
            tabela.getColumnModel().getColumn(7).setPreferredWidth(110);
            tabela.getColumnModel().getColumn(8).setPreferredWidth(110);
            tabela.getColumnModel().getColumn(9).setPreferredWidth(110);
            tabela.getColumnModel().getColumn(10).setPreferredWidth(120);
            
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
                col.setHeaderValue("UNIDADE");
                col = columnModel.getColumn(5);
                col.setHeaderValue("QUANT.");
                col = columnModel.getColumn(6);
                col.setHeaderValue("PREÇO_ARTIGO(MZN)");
                col = columnModel.getColumn(7);
                col.setHeaderValue("PREÇO_TOTAL(MZN)");
                col = columnModel.getColumn(8);
                col.setHeaderValue("PREÇO_VENDA(MZN)");
                col = columnModel.getColumn(9);
                col.setHeaderValue("LUCRO(MZN)");
                col = columnModel.getColumn(10);
                col.setHeaderValue("DATA & HORA");
            } else {
                col.setHeaderValue("ID");
                col = columnModel.getColumn(1);
                col.setHeaderValue("ITEM DESCRIPTION");
                col = columnModel.getColumn(2);
                col.setHeaderValue("CODE");
                col = columnModel.getColumn(3);
                col.setHeaderValue("CATEGORY");
                col = columnModel.getColumn(4);
                col.setHeaderValue("UNIT");
                col = columnModel.getColumn(5);
                col.setHeaderValue("QUANT.");
                col = columnModel.getColumn(6);
                col.setHeaderValue("ARTC. PRICE(MZN)");
                col = columnModel.getColumn(7);
                col.setHeaderValue("TOTAL PRICE(MZN)");
                col = columnModel.getColumn(8);
                col.setHeaderValue("SELLING PRICE(MZN)");
                col = columnModel.getColumn(9);
                col.setHeaderValue("BALANCE(MZN)");
                col = columnModel.getColumn(10);
                col.setHeaderValue("DATE & TIME");
            }
            
            tabela.getTableHeader().repaint();
        } catch(SQLException er) {
            try {
                this.pst.close();
                this.conexao.close();
                this.rs.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null,er + " ERRO NA TABELA_DAO() VENDA_LOJA_1", "TBL   DAO", 0);
                } else {
                    JOptionPane.showMessageDialog(null,er + " ERROR IN THE SELLING_SHOP_1 DAO_TABLE()", "TBL   DAO", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaVenda_Loja1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void tabelaDAOProced() {
        String sql = "call Proced_Organizartbvenda_1()";
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
        } catch(SQLException er) {
            try {
                this.pst.close();
                this.conexao.close();
                this.rs.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null,er + " ERRO NO PROCED_TABELA_DAO() VENDA_LOJA_1", "PROCED  TBL  DAO", 0);
                } else {
                    JOptionPane.showMessageDialog(null,er + " ERROR IN THE SELLING_SHOP_1 DAO_TABLE_PROCED()", "DAO  TBL  PROCED", 0);
                } 
            } catch (SQLException ex) {
                Logger.getLogger(TelaVenda_Loja1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void pesquisar() {
        String sql = "select ID, DESIGNAÇÃO, MARCA, CATEGORIA, UNIDADE, QUANTIDADE, PRECO_ARTIGO_MZN, PRECO_TOTAL_MZN, PRECO_VENDA, LUCRO, DATA_HORA from tbvenda_1 where DESIGNAÇÃO like ? and DATA_HORA >= CURDATE() || MARCA like ? and DATA_HORA >= CURDATE() || CATEGORIA like ? and DATA_HORA >= CURDATE() || DATA_HORA like ? and DATA_HORA >= CURDATE()";
        
        try{            
            pst = conexao.prepareStatement(sql);

            pst.setString(1, "%" + txtpesquisa.getText() + "%");
            pst.setString(2, "%" + txtpesquisa.getText() + "%");
            pst.setString(3, "%" + txtpesquisa.getText() + "%");
            pst.setString(4, "%" + txtpesquisa.getText() + "%");

            rs = pst.executeQuery();
            tabela.setModel(DbUtils.resultSetToTableModel(rs));
            
            tabela.getColumnModel().getColumn(0).setPreferredWidth(40);
            tabela.getColumnModel().getColumn(1).setPreferredWidth(221);
            tabela.getColumnModel().getColumn(2).setPreferredWidth(80);
            tabela.getColumnModel().getColumn(3).setPreferredWidth(55);
            tabela.getColumnModel().getColumn(4).setPreferredWidth(50);
            tabela.getColumnModel().getColumn(5).setPreferredWidth(35);
            tabela.getColumnModel().getColumn(6).setPreferredWidth(110);
            tabela.getColumnModel().getColumn(7).setPreferredWidth(110);
            tabela.getColumnModel().getColumn(8).setPreferredWidth(110);
            tabela.getColumnModel().getColumn(9).setPreferredWidth(110);
            tabela.getColumnModel().getColumn(10).setPreferredWidth(120);
            
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
                col.setHeaderValue("UNIDADE");
                col = columnModel.getColumn(5);
                col.setHeaderValue("QUANT.");
                col = columnModel.getColumn(6);
                col.setHeaderValue("PREÇO_ARTIGO(MZN)");
                col = columnModel.getColumn(7);
                col.setHeaderValue("PREÇO_TOTAL(MZN)");
                col = columnModel.getColumn(8);
                col.setHeaderValue("PREÇO_VENDA(MZN)");
                col = columnModel.getColumn(9);
                col.setHeaderValue("LUCRO(MZN)");
                col = columnModel.getColumn(10);
                col.setHeaderValue("DATA & HORA");
            } else {
                col.setHeaderValue("ID");
                col = columnModel.getColumn(1);
                col.setHeaderValue("ITEM DESCRIPTION");
                col = columnModel.getColumn(2);
                col.setHeaderValue("CODE");
                col = columnModel.getColumn(3);
                col.setHeaderValue("CATEGORY");
                col = columnModel.getColumn(4);
                col.setHeaderValue("UNIT");
                col = columnModel.getColumn(5);
                col.setHeaderValue("QUANT.");
                col = columnModel.getColumn(6);
                col.setHeaderValue("ARTC. PRICE(MZN)");
                col = columnModel.getColumn(7);
                col.setHeaderValue("TOTAL PRICE(MZN)");
                col = columnModel.getColumn(8);
                col.setHeaderValue("SELLING PRICE(MZN)");
                col = columnModel.getColumn(9);
                col.setHeaderValue("BALANCE(MZN)");
                col = columnModel.getColumn(10);
                col.setHeaderValue("DATE & TIME");
            }
            
            tabela.getTableHeader().repaint();
        } catch(SQLException err) {
            try {
                this.pst.close();
                this.conexao.close();
                this.rs.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO AO TENTAR PESQUISAR VENDA_LOJA_1..! \n"+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR WHILE TRYING TO SELLING_SHOP_1 SEARCH..! \n"+err, "Attention", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaVenda_Loja1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
   
    private void configCalc() {
        calc_Quant();
        calc_Preco_Artigo_MZN();
        calc_Preco_Total_MZN();
        calc_Preco_Venda_MZN();
        calc_Preco_Lucro_MZN();
    }
    
    private void calc_Quant() {
        String sql = "select sum( QUANTIDADE ) as quant from tbvenda_1 where DATA_HORA >= CURDATE()";
        
        try{
            rs = conexao.createStatement().executeQuery(sql);
            
            while (rs.next()) {
                soma = rs.getInt("quant");
            }          
            
            txtquant.setText(" "+soma);
            
        } catch(SQLException err) {
            try {
                this.pst.close();
                this.conexao.close();
                this.rs.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO NO calc_Quant() na VENDA_LOJA_1..! \n"+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR IN calc_Quant() at SELLING_SHOP_1..! \n"+err, "Attention", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaVenda_Loja1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void pesquisar_calc_Quant() {
        String sql = "select sum( QUANTIDADE ) as Pquant from tbvenda_1 where DESIGNAÇÃO like ? and DATA_HORA >= CURDATE() || MARCA like ? and DATA_HORA >= CURDATE() || CATEGORIA like ? and DATA_HORA >= CURDATE() || DATA_HORA like ? and DATA_HORA >= CURDATE()";
        
        try{
            pst = conexao.prepareStatement(sql);

            pst.setString(1, "%" + txtpesquisa.getText() + "%");
            pst.setString(2, "%" + txtpesquisa.getText() + "%");
            pst.setString(3, "%" + txtpesquisa.getText() + "%");
            pst.setString(4, "%" + txtpesquisa.getText() + "%");

            rs = pst.executeQuery();

            while (rs.next()) {
                soma = rs.getInt("Pquant");
            }          
            
            txtquant.setText("  "+soma);
        } catch(SQLException err) {
            try {
                this.pst.close();
                this.conexao.close();
                this.rs.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO AO TENTAR pesquisar_calc_Quant() na VENDA_LOJA_1..! \n"+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR WHILE TRYING TO pesquisar_calc_Quant() in SELLING_SHOP_1..! \n"+err, "Attention", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaVenda_Loja1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void calc_Preco_Artigo_MZN() {
        String sql = "select sum( PRECO_ARTIGO_MZN ) as somaartigomzn from tbvenda_1 where DATA_HORA >= CURDATE()";
        
        try{
            rs = conexao.createStatement().executeQuery(sql);
            
            while (rs.next()) {
                somaVlr = rs.getDouble("somaartigomzn");
            }          
            
            txtPreco_artigo_mzn.setText(" MZN   "+somaVlr);
            
        } catch(SQLException err) {
            try {
                this.pst.close();
                this.conexao.close();
                this.rs.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO NO calc_Preco_Artigo_MZN() na VENDA_LOJA_1 ..! "+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR IN calc_Preco_Artigo_MZN() at SELLING_SHOP_1..! "+err, "Attention", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaVenda_Loja1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void pesquisar_calc_Preco_Artigo_MZN() {
        String sql = "select sum( PRECO_ARTIGO_MZN ) as Psomaartigomzn from tbvenda_1 where DESIGNAÇÃO like ? and DATA_HORA >= CURDATE() || MARCA like ? and DATA_HORA >= CURDATE() || CATEGORIA like ? and DATA_HORA >= CURDATE() || DATA_HORA like ? and DATA_HORA >= CURDATE()";
        
        try{
            pst = conexao.prepareStatement(sql);

            pst.setString(1, "%" + txtpesquisa.getText() + "%");
            pst.setString(2, "%" + txtpesquisa.getText() + "%");
            pst.setString(3, "%" + txtpesquisa.getText() + "%");
            pst.setString(4, "%" + txtpesquisa.getText() + "%");

            rs = pst.executeQuery();

            while (rs.next()) {
                somaVlr = rs.getDouble("Psomaartigomzn");
            }          
            
            txtPreco_artigo_mzn.setText(" MZN   "+somaVlr);
        } catch(SQLException err) {
            try {
                this.pst.close();
                this.conexao.close();
                this.rs.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO AO TENTAR pesquisar_calc_Preco_Artigo_MZN() na VENDA_LOJA_1..! \n"+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR WHILE TRYING TO pesquisar_calc_Preco_Artigo_MZN() in SELLING_SHOP_1..! \n"+err, "Attention", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaVenda_Loja1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void calc_Preco_Total_MZN() {
        String sql = "select sum( PRECO_TOTAL_MZN ) as somatotalmzn from tbvenda_1 where DATA_HORA >= CURDATE()";
        
        try{
            rs = conexao.createStatement().executeQuery(sql);
            
            while (rs.next()) {
                somaVlr = rs.getDouble("somatotalmzn");
            }          
            
            txtPreco_total_mzn.setText(" MZN   "+somaVlr);
            
        } catch(SQLException err) {
            try {
                this.pst.close();
                this.conexao.close();
                this.rs.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO NO calc_Preco_Total_MZN() na VENDA_LOJA_1 ..! "+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR IN calc_Preco_Total_MZN() at SELLING_SHOP_1..! "+err, "Attention", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaVenda_Loja1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void pesquisar_calc_Preco_Total_MZN() {
        String sql = "select sum( PRECO_TOTAL_MZN ) as Psomatotalmzn from tbvenda_1 where DESIGNAÇÃO like ? and DATA_HORA >= CURDATE() || MARCA like ? and DATA_HORA >= CURDATE() || CATEGORIA like ? and DATA_HORA >= CURDATE() || DATA_HORA like ? and DATA_HORA >= CURDATE()";
        
        try{
            pst = conexao.prepareStatement(sql);

            pst.setString(1, "%" + txtpesquisa.getText() + "%");
            pst.setString(2, "%" + txtpesquisa.getText() + "%");
            pst.setString(3, "%" + txtpesquisa.getText() + "%");
            pst.setString(4, "%" + txtpesquisa.getText() + "%");

            rs = pst.executeQuery();

            while (rs.next()) {
                somaVlr = rs.getDouble("Psomatotalmzn");
            }          
            
            txtPreco_total_mzn.setText(" MZN   "+somaVlr);
        } catch(SQLException err) {
            try {
                this.pst.close();
                this.conexao.close();
                this.rs.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO AO TENTAR pesquisar_calc_Preco_Total_MZN() na VENDA_LOJA_1..! \n"+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR WHILE TRYING TO pesquisar_calc_Preco_Total_MZN() in SELLING_SHOP_1..! \n"+err, "Attention", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaVenda_Loja1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void calc_Preco_Venda_MZN() {
        String sql = "select sum( PRECO_VENDA ) as somavendamzn from tbvenda_1 where DATA_HORA >= CURDATE()";
        
        try{
            rs = conexao.createStatement().executeQuery(sql);
            
            while (rs.next()) {
                somaVlr = rs.getDouble("somavendamzn");
            }          
            
            txtPreco_venda_mzn.setText(" MZN   "+somaVlr);
            
        } catch(SQLException err) {
            try {
                this.pst.close();
                this.conexao.close();
                this.rs.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO NO calc_Preco_Venda_MZN() na VENDA_LOJA_1 ..! "+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR IN calc_Preco_Venda_MZN() at SELLING_SHOP_1..! "+err, "Attention", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaVenda_Loja1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void pesquisar_calc_Preco_Venda_MZN() {
        String sql = "select sum( PRECO_VENDA ) as Psomavendamzn from tbvenda_1 where DESIGNAÇÃO like ? and DATA_HORA >= CURDATE() || MARCA like ? and DATA_HORA >= CURDATE() || CATEGORIA like ? and DATA_HORA >= CURDATE() || DATA_HORA like ? and DATA_HORA >= CURDATE()";
        
        try{
            pst = conexao.prepareStatement(sql);

            pst.setString(1, "%" + txtpesquisa.getText() + "%");
            pst.setString(2, "%" + txtpesquisa.getText() + "%");
            pst.setString(3, "%" + txtpesquisa.getText() + "%");
            pst.setString(4, "%" + txtpesquisa.getText() + "%");

            rs = pst.executeQuery();

            while (rs.next()) {
                somaVlr = rs.getDouble("Psomavendamzn");
            }          
            
            txtPreco_venda_mzn.setText(" MZN   "+somaVlr);
        } catch(SQLException err) {
            try {
                this.pst.close();
                this.conexao.close();
                this.rs.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO AO TENTAR pesquisar_calc_Preco_Venda_MZN() na VENDA_LOJA_1..! \n"+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR WHILE TRYING TO pesquisar_calc_Preco_Venda_MZN() in SELLING_SHOP_1..! \n"+err, "Attention", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaVenda_Loja1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void calc_Preco_Lucro_MZN() {
        String sql = "select sum( LUCRO ) as somalucro from tbvenda_1 where DATA_HORA >= CURDATE()";
        
        try{
            rs = conexao.createStatement().executeQuery(sql);
            
            while (rs.next()) {
                somaVlr = rs.getDouble("somalucro");
            }          
            
            txtPreco_lucro.setText(" MZN   "+somaVlr);
            
        } catch(SQLException err) {
            try {
                this.pst.close();
                this.conexao.close();
                this.rs.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO NO calc_Preco_Lucro_MZN() na VENDA_LOJA_1 ..! "+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR in calc_Preco_Lucro_MZN() at SELLING_SHOP_1..! "+err, "Attention", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaVenda_Loja1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void pesquisar_calc_Preco_Lucro_MZN() {
        String sql = "select sum( LUCRO ) as Psomalucro from tbvenda_1 where DESIGNAÇÃO like ? and DATA_HORA >= CURDATE() || MARCA like ? and DATA_HORA >= CURDATE() || CATEGORIA like ? and DATA_HORA >= CURDATE() || DATA_HORA like ? and DATA_HORA >= CURDATE()";
        
        try{
            pst = conexao.prepareStatement(sql);

            pst.setString(1, "%" + txtpesquisa.getText() + "%");
            pst.setString(2, "%" + txtpesquisa.getText() + "%");
            pst.setString(3, "%" + txtpesquisa.getText() + "%");
            pst.setString(4, "%" + txtpesquisa.getText() + "%");

            rs = pst.executeQuery();

            while (rs.next()) {
                somaVlr = rs.getDouble("Psomalucro");
            }          
            
            txtPreco_lucro.setText(" MZN   "+somaVlr);
        } catch(SQLException err) {
            try {
                this.pst.close();
                this.conexao.close();
                this.rs.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO AO TENTAR pesquisar_calc_Preco_Lucro_MZN() na VENDA_LOJA_1..! \n"+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR WHILE TRYING TO pesquisar_calc_Preco_Lucro_MZN() in SELLING_SHOP_1..! \n"+err, "Attention", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaVenda_Loja1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void editar() {
        if(tabela.getSelectedRow() == -1) {
            if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Por favor, selecione algum cliente na tabela !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a client from the table !", "Attention", 2);
            }
        } else {
            if(telaEddVenda == null || !telaEddVenda.isVisible()) {
                try {
                    telaEddVenda = new TelaEdd_Venda_Loja1();
                } catch (SQLException ex) {
                    Logger.getLogger(TelaVenda_Loja1.class.getName()).log(Level.SEVERE, null, ex);
                }
                telaEddVenda.setVisible(true);
            } else {
                telaEddVenda.toFront();
            }
            int selectedRowIndex = tabela.getSelectedRow();
            model = (DefaultTableModel) tabela.getModel();
            telaEddVenda.txtID.setText(model.getValueAt(selectedRowIndex, 0).toString());
            telaEddVenda.txtDesignacao.setText(model.getValueAt(selectedRowIndex, 1).toString());
            telaEddVenda.txtMarca.setText(model.getValueAt(selectedRowIndex, 2).toString());
            telaEddVenda.txtCategoria.setSelectedItem(model.getValueAt(selectedRowIndex, 3).toString());
            telaEddVenda.txtUnidade.setText(model.getValueAt(selectedRowIndex, 4).toString());
            telaEddVenda.txtQuantidade_Disp.setText(model.getValueAt(selectedRowIndex, 5).toString());
            telaEddVenda.txtVenda_MZN.setText(model.getValueAt(selectedRowIndex, 8).toString());
        }
    }

    @Override
    public void configPort() {
        lbltitulo.setText("*  V E N D A S  *");
        lbltitulo.setFont(new Font("Times New Roman", 3, 21));
        btnAdicionar.setText("Adicionar");
        btnAdicionar.setFont(new Font("Agency FB", 1, 20));
        btnEditar.setText("Editar");
        btnEditar.setFont(new Font("Agency FB", 1, 20));
        btnRemover.setText("Remover");
        btnRemover.setFont(new Font("Agency FB", 1, 20));
        btnDetalhes.setText("Detalhes");
        btnDetalhes.setFont(new Font("Agency FB", 1, 20));
        lblloja_1.setText("# LOJA_1");
    }

    @Override
    public void configEng() {
        lbltitulo.setText("*  S E L L I N G S  *");
        lbltitulo.setFont(new Font("Times New Roman", 3, 21));
        btnAdicionar.setText("Add");
        btnAdicionar.setFont(new Font("Agency FB", 1, 20));
        btnEditar.setText("Edit");
        btnEditar.setFont(new Font("Agency FB", 1, 20));
        btnRemover.setText("Remove");
        btnRemover.setFont(new Font("Agency FB", 1, 20));
        btnDetalhes.setText("Details");
        btnDetalhes.setFont(new Font("Agency FB", 1, 20));
        lblloja_1.setText("# SHOP_1");
    }

    @Override
    public void remover1() {
        String sql = "delete from tbvenda_1 where ID = ?";
        model = (DefaultTableModel) tabela.getModel();
        int sr = tabela.getSelectedRow();
        try {
            pst = conexao.prepareStatement(sql);
            int id = Integer.parseInt(model.getValueAt(sr, 0).toString());
            
            if(Idiomas.getPort() == true) {
                deleteItem = JOptionPane.showConfirmDialog(null, "Têm certeza que deseja remover?", "Atenção", JOptionPane.YES_NO_OPTION);
            } else {
                deleteItem = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove?", "Attention", JOptionPane.YES_NO_OPTION);
            }
            if(deleteItem == JOptionPane.YES_OPTION) {                
                pst.setInt(1, id);
                pst.executeUpdate();
                
                tabelaDAO();
                configCalc();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Removido..!", "Atenção", 1);
                } else {
                    JOptionPane.showMessageDialog(null, "Removed..!", "Attention", 1);
                }               
            }
        } catch(HeadlessException | NumberFormatException | SQLException Er) {
            if(Idiomas.getPort() == true) {
                JOptionPane.showMessageDialog(null," ERRO AO TENTAR DELETAR VENDA_LOJA_1...! "+Er, "Atenção",0);
            } else {
                JOptionPane.showMessageDialog(null,"ERROR WHILE TRYING TO SELLING_SHOP_1 DELETE...! "+Er, "Attention",0);
            }
            
        }
    }

    @Override
    public void remover2() {
        int[] sr = tabela.getSelectedRows();
        String sql = "delete from tbvenda_1 where ID in (?, ?)";
        model = (DefaultTableModel) tabela.getModel();
        try {
            pst = conexao.prepareStatement(sql);
            int id1 = Integer.parseInt(model.getValueAt(sr[0], 0).toString());
            int id2 = Integer.parseInt(model.getValueAt(sr[1], 0).toString());
            
            if(Idiomas.getPort() == true) {
                deleteItem = JOptionPane.showConfirmDialog(null, "Têm certeza que deseja remover?", "Atenção", JOptionPane.YES_NO_OPTION);
            } else {
                deleteItem = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove?", "Attention", JOptionPane.YES_NO_OPTION);
            }
            if(deleteItem == JOptionPane.YES_OPTION) {                
                pst.setInt(1, id1);
                pst.setInt(2, id2);
                pst.executeUpdate();
                
                tabelaDAO();
                configCalc();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Removido..!", "Atenção", 1);
                } else {
                    JOptionPane.showMessageDialog(null, "Removed..!", "Attention", 1);
                }               
            }
        } catch(HeadlessException | NumberFormatException | SQLException Er) {
            if(Idiomas.getPort() == true) {
                JOptionPane.showMessageDialog(null," ERRO AO TENTAR DELETAR VENDA_LOJA_1...! "+Er, "Atenção",0);
            } else {
                JOptionPane.showMessageDialog(null,"ERROR WHILE TRYING TO SELLING_SHOP_1 DELETE...! "+Er, "Attention",0);
            }
            
        }
    }

    @Override
    public void remover3() {
        int[] sr = tabela.getSelectedRows();
        String sql = "delete from tbvenda_1 where ID in (?, ?, ?)";
        model = (DefaultTableModel) tabela.getModel();
        try {
            pst = conexao.prepareStatement(sql);
            int id1 = Integer.parseInt(model.getValueAt(sr[0], 0).toString());
            int id2 = Integer.parseInt(model.getValueAt(sr[1], 0).toString());
            int id3 = Integer.parseInt(model.getValueAt(sr[2], 0).toString());
            
            if(Idiomas.getPort() == true) {
                deleteItem = JOptionPane.showConfirmDialog(null, "Têm certeza que deseja remover?", "Atenção", JOptionPane.YES_NO_OPTION);
            } else {
                deleteItem = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove?", "Attention", JOptionPane.YES_NO_OPTION);
            }
            if(deleteItem == JOptionPane.YES_OPTION) {                
                pst.setInt(1, id1);
                pst.setInt(2, id2);
                pst.setInt(3, id3);
                pst.executeUpdate();
                
                tabelaDAO();
                configCalc();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Removido..!", "Atenção", 1);
                } else {
                    JOptionPane.showMessageDialog(null, "Removed..!", "Attention", 1);
                }               
            }
        } catch(HeadlessException | NumberFormatException | SQLException Er) {
            if(Idiomas.getPort() == true) {
                JOptionPane.showMessageDialog(null," ERRO AO TENTAR DELETAR VENDA_LOJA_1...! "+Er, "Atenção",0);
            } else {
                JOptionPane.showMessageDialog(null,"ERROR WHILE TRYING TO SELLING_SHOP_1 DELETE...! "+Er, "Attention",0);
            }
            
        }
    }

    @Override
    public void remover4() {
        int[] sr = tabela.getSelectedRows();
        String sql = "delete from tbvenda_1 where ID in (?, ?, ?, ?)";
        model = (DefaultTableModel) tabela.getModel();
        try {
            pst = conexao.prepareStatement(sql);
            int id1 = Integer.parseInt(model.getValueAt(sr[0], 0).toString());
            int id2 = Integer.parseInt(model.getValueAt(sr[1], 0).toString());
            int id3 = Integer.parseInt(model.getValueAt(sr[2], 0).toString());
            int id4 = Integer.parseInt(model.getValueAt(sr[3], 0).toString());
            
            if(Idiomas.getPort() == true) {
                deleteItem = JOptionPane.showConfirmDialog(null, "Têm certeza que deseja remover?", "Atenção", JOptionPane.YES_NO_OPTION);
            } else {
                deleteItem = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove?", "Attention", JOptionPane.YES_NO_OPTION);
            }
            if(deleteItem == JOptionPane.YES_OPTION) {                
                pst.setInt(1, id1);
                pst.setInt(2, id2);
                pst.setInt(3, id3);
                pst.setInt(4, id4);
                pst.executeUpdate();
                
                tabelaDAO();
                configCalc();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Removido..!", "Atenção", 1);
                } else {
                    JOptionPane.showMessageDialog(null, "Removed..!", "Attention", 1);
                }               
            }
        } catch(HeadlessException | NumberFormatException | SQLException Er) {
            if(Idiomas.getPort() == true) {
                JOptionPane.showMessageDialog(null," ERRO AO TENTAR DELETAR VENDA_LOJA_1...! "+Er, "Atenção",0);
            } else {
                JOptionPane.showMessageDialog(null,"ERROR WHILE TRYING TO SELLING_SHOP_1 DELETE...! "+Er, "Attention",0);
            }
            
        }
    }

    @Override
    public void remover5() {
        int[] sr = tabela.getSelectedRows();
        String sql = "delete from tbvenda_1 where ID in (?, ?, ?, ?, ?)";
        model = (DefaultTableModel) tabela.getModel();
        try {
            pst = conexao.prepareStatement(sql);
            int id1 = Integer.parseInt(model.getValueAt(sr[0], 0).toString());
            int id2 = Integer.parseInt(model.getValueAt(sr[1], 0).toString());
            int id3 = Integer.parseInt(model.getValueAt(sr[2], 0).toString());
            int id4 = Integer.parseInt(model.getValueAt(sr[3], 0).toString());
            int id5 = Integer.parseInt(model.getValueAt(sr[4], 0).toString());
            
            if(Idiomas.getPort() == true) {
                deleteItem = JOptionPane.showConfirmDialog(null, "Têm certeza que deseja remover?", "Atenção", JOptionPane.YES_NO_OPTION);
            } else {
                deleteItem = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove?", "Attention", JOptionPane.YES_NO_OPTION);
            }
            if(deleteItem == JOptionPane.YES_OPTION) {                
                pst.setInt(1, id1);
                pst.setInt(2, id2);
                pst.setInt(3, id3);
                pst.setInt(4, id4);
                pst.setInt(5, id5);
                pst.executeUpdate();
                
                tabelaDAO();
                configCalc();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Removido..!", "Atenção", 1);
                } else {
                    JOptionPane.showMessageDialog(null, "Removed..!", "Attention", 1);
                }               
            }
        } catch(HeadlessException | NumberFormatException | SQLException Er) {
            if(Idiomas.getPort() == true) {
                JOptionPane.showMessageDialog(null," ERRO AO TENTAR DELETAR VENDA_LOJA_1...! "+Er, "Atenção",0);
            } else {
                JOptionPane.showMessageDialog(null,"ERROR WHILE TRYING TO SELLING_SHOP_1 DELETE...! "+Er, "Attention",0);
            }
            
        }
    }

    @Override
    public void remover6() {
        int[] sr = tabela.getSelectedRows();
        String sql = "delete from tbvenda_1 where ID in (?, ?, ?, ?, ?, ?)";
        model = (DefaultTableModel) tabela.getModel();
        try {
            pst = conexao.prepareStatement(sql);
            int id1 = Integer.parseInt(model.getValueAt(sr[0], 0).toString());
            int id2 = Integer.parseInt(model.getValueAt(sr[1], 0).toString());
            int id3 = Integer.parseInt(model.getValueAt(sr[2], 0).toString());
            int id4 = Integer.parseInt(model.getValueAt(sr[3], 0).toString());
            int id5 = Integer.parseInt(model.getValueAt(sr[4], 0).toString());
            int id6 = Integer.parseInt(model.getValueAt(sr[5], 0).toString());
            
            if(Idiomas.getPort() == true) {
                deleteItem = JOptionPane.showConfirmDialog(null, "Têm certeza que deseja remover?", "Atenção", JOptionPane.YES_NO_OPTION);
            } else {
                deleteItem = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove?", "Attention", JOptionPane.YES_NO_OPTION);
            }
            if(deleteItem == JOptionPane.YES_OPTION) {                
                pst.setInt(1, id1);
                pst.setInt(2, id2);
                pst.setInt(3, id3);
                pst.setInt(4, id4);
                pst.setInt(5, id5);
                pst.setInt(6, id6);
                pst.executeUpdate();
                
                tabelaDAO();
                configCalc();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Removido..!", "Atenção", 1);
                } else {
                    JOptionPane.showMessageDialog(null, "Removed..!", "Attention", 1);
                }               
            }
        } catch(HeadlessException | NumberFormatException | SQLException Er) {
            if(Idiomas.getPort() == true) {
                JOptionPane.showMessageDialog(null," ERRO AO TENTAR DELETAR VENDA_LOJA_1...! "+Er, "Atenção",0);
            } else {
                JOptionPane.showMessageDialog(null,"ERROR WHILE TRYING TO SELLING_SHOP_1 DELETE...! "+Er, "Attention",0);
            }
            
        }
    }

    @Override
    public void remover7() {
        int[] sr = tabela.getSelectedRows();
        String sql = "delete from tbvenda_1 where ID in (?, ?, ?, ?, ?, ?, ?)";
        model = (DefaultTableModel) tabela.getModel();
        try {
            pst = conexao.prepareStatement(sql);
            int id1 = Integer.parseInt(model.getValueAt(sr[0], 0).toString());
            int id2 = Integer.parseInt(model.getValueAt(sr[1], 0).toString());
            int id3 = Integer.parseInt(model.getValueAt(sr[2], 0).toString());
            int id4 = Integer.parseInt(model.getValueAt(sr[3], 0).toString());
            int id5 = Integer.parseInt(model.getValueAt(sr[4], 0).toString());
            int id6 = Integer.parseInt(model.getValueAt(sr[5], 0).toString());
            int id7 = Integer.parseInt(model.getValueAt(sr[6], 0).toString());
            
            if(Idiomas.getPort() == true) {
                deleteItem = JOptionPane.showConfirmDialog(null, "Têm certeza que deseja remover?", "Atenção", JOptionPane.YES_NO_OPTION);
            } else {
                deleteItem = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove?", "Attention", JOptionPane.YES_NO_OPTION);
            }
            if(deleteItem == JOptionPane.YES_OPTION) {                
                pst.setInt(1, id1);
                pst.setInt(2, id2);
                pst.setInt(3, id3);
                pst.setInt(4, id4);
                pst.setInt(5, id5);
                pst.setInt(6, id6);
                pst.setInt(7, id7);
                pst.executeUpdate();
                
                tabelaDAO();
                configCalc();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Removido..!", "Atenção", 1);
                } else {
                    JOptionPane.showMessageDialog(null, "Removed..!", "Attention", 1);
                }               
            }
        } catch(HeadlessException | NumberFormatException | SQLException Er) {
            if(Idiomas.getPort() == true) {
                JOptionPane.showMessageDialog(null," ERRO AO TENTAR DELETAR VENDA_LOJA_1...! "+Er, "Atenção",0);
            } else {
                JOptionPane.showMessageDialog(null,"ERROR WHILE TRYING TO SELLING_SHOP_1 DELETE...! "+Er, "Attention",0);
            }
            
        }
    }

    @Override
    public void remover8() {
        int[] sr = tabela.getSelectedRows();
        String sql = "delete from tbvenda_1 where ID in (?, ?, ?, ?, ?, ?, ?, ?)";
        model = (DefaultTableModel) tabela.getModel();
        try {
            pst = conexao.prepareStatement(sql);
            int id1 = Integer.parseInt(model.getValueAt(sr[0], 0).toString());
            int id2 = Integer.parseInt(model.getValueAt(sr[1], 0).toString());
            int id3 = Integer.parseInt(model.getValueAt(sr[2], 0).toString());
            int id4 = Integer.parseInt(model.getValueAt(sr[3], 0).toString());
            int id5 = Integer.parseInt(model.getValueAt(sr[4], 0).toString());
            int id6 = Integer.parseInt(model.getValueAt(sr[5], 0).toString());
            int id7 = Integer.parseInt(model.getValueAt(sr[6], 0).toString());
            int id8 = Integer.parseInt(model.getValueAt(sr[7], 0).toString());
            
            if(Idiomas.getPort() == true) {
                deleteItem = JOptionPane.showConfirmDialog(null, "Têm certeza que deseja remover?", "Atenção", JOptionPane.YES_NO_OPTION);
            } else {
                deleteItem = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove?", "Attention", JOptionPane.YES_NO_OPTION);
            }
            if(deleteItem == JOptionPane.YES_OPTION) {                
                pst.setInt(1, id1);
                pst.setInt(2, id2);
                pst.setInt(3, id3);
                pst.setInt(4, id4);
                pst.setInt(5, id5);
                pst.setInt(6, id6);
                pst.setInt(7, id7);
                pst.setInt(8, id8);
                pst.executeUpdate();
                
                tabelaDAO();
                configCalc();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Removido..!", "Atenção", 1);
                } else {
                    JOptionPane.showMessageDialog(null, "Removed..!", "Attention", 1);
                }               
            }
        } catch(HeadlessException | NumberFormatException | SQLException Er) {
            if(Idiomas.getPort() == true) {
                JOptionPane.showMessageDialog(null," ERRO AO TENTAR DELETAR VENDA_LOJA_1...! "+Er, "Atenção",0);
            } else {
                JOptionPane.showMessageDialog(null,"ERROR WHILE TRYING TO SELLING_SHOP_1 DELETE...! "+Er, "Attention",0);
            }
            
        }
    }

    @Override
    public void remover9() {
        int[] sr = tabela.getSelectedRows();
        String sql = "delete from tbvenda_1 where ID in (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        model = (DefaultTableModel) tabela.getModel();
        try {
            pst = conexao.prepareStatement(sql);
            int id1 = Integer.parseInt(model.getValueAt(sr[0], 0).toString());
            int id2 = Integer.parseInt(model.getValueAt(sr[1], 0).toString());
            int id3 = Integer.parseInt(model.getValueAt(sr[2], 0).toString());
            int id4 = Integer.parseInt(model.getValueAt(sr[3], 0).toString());
            int id5 = Integer.parseInt(model.getValueAt(sr[4], 0).toString());
            int id6 = Integer.parseInt(model.getValueAt(sr[5], 0).toString());
            int id7 = Integer.parseInt(model.getValueAt(sr[6], 0).toString());
            int id8 = Integer.parseInt(model.getValueAt(sr[7], 0).toString());
            int id9 = Integer.parseInt(model.getValueAt(sr[8], 0).toString());
            
            if(Idiomas.getPort() == true) {
                deleteItem = JOptionPane.showConfirmDialog(null, "Têm certeza que deseja remover?", "Atenção", JOptionPane.YES_NO_OPTION);
            } else {
                deleteItem = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove?", "Attention", JOptionPane.YES_NO_OPTION);
            }
            if(deleteItem == JOptionPane.YES_OPTION) {                
                pst.setInt(1, id1);
                pst.setInt(2, id2);
                pst.setInt(3, id3);
                pst.setInt(4, id4);
                pst.setInt(5, id5);
                pst.setInt(6, id6);
                pst.setInt(7, id7);
                pst.setInt(8, id8);
                pst.setInt(9, id9);
                pst.executeUpdate();
                
                tabelaDAO();
                configCalc();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Removido..!", "Atenção", 1);
                } else {
                    JOptionPane.showMessageDialog(null, "Removed..!", "Attention", 1);
                }               
            }
        } catch(HeadlessException | NumberFormatException | SQLException Er) {
            if(Idiomas.getPort() == true) {
                JOptionPane.showMessageDialog(null," ERRO AO TENTAR DELETAR VENDA_LOJA_1...! "+Er, "Atenção",0);
            } else {
                JOptionPane.showMessageDialog(null,"ERROR WHILE TRYING TO SELLING_SHOP_1 DELETE...! "+Er, "Attention",0);
            }
            
        }
    }

    @Override
    public void remover10() {
        int[] sr = tabela.getSelectedRows();
        String sql = "delete from tbvenda_1 where ID in (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        model = (DefaultTableModel) tabela.getModel();
        try {
            pst = conexao.prepareStatement(sql);
            int id1 = Integer.parseInt(model.getValueAt(sr[0], 0).toString());
            int id2 = Integer.parseInt(model.getValueAt(sr[1], 0).toString());
            int id3 = Integer.parseInt(model.getValueAt(sr[2], 0).toString());
            int id4 = Integer.parseInt(model.getValueAt(sr[3], 0).toString());
            int id5 = Integer.parseInt(model.getValueAt(sr[4], 0).toString());
            int id6 = Integer.parseInt(model.getValueAt(sr[5], 0).toString());
            int id7 = Integer.parseInt(model.getValueAt(sr[6], 0).toString());
            int id8 = Integer.parseInt(model.getValueAt(sr[7], 0).toString());
            int id9 = Integer.parseInt(model.getValueAt(sr[8], 0).toString());
            int id10 = Integer.parseInt(model.getValueAt(sr[9], 0).toString());
            
            if(Idiomas.getPort() == true) {
                deleteItem = JOptionPane.showConfirmDialog(null, "Têm certeza que deseja remover?", "Atenção", JOptionPane.YES_NO_OPTION);
            } else {
                deleteItem = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove?", "Attention", JOptionPane.YES_NO_OPTION);
            }
            if(deleteItem == JOptionPane.YES_OPTION) {                
                pst.setInt(1, id1);
                pst.setInt(2, id2);
                pst.setInt(3, id3);
                pst.setInt(4, id4);
                pst.setInt(5, id5);
                pst.setInt(6, id6);
                pst.setInt(7, id7);
                pst.setInt(8, id8);
                pst.setInt(9, id9);
                pst.setInt(10, id10);
                pst.executeUpdate();
                
                tabelaDAO();
                configCalc();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Removido..!", "Atenção", 1);
                } else {
                    JOptionPane.showMessageDialog(null, "Removed..!", "Attention", 1);
                }               
            }
        } catch(HeadlessException | NumberFormatException | SQLException Er) {
            if(Idiomas.getPort() == true) {
                JOptionPane.showMessageDialog(null," ERRO AO TENTAR DELETAR VENDA_LOJA_1...! "+Er, "Atenção",0);
            } else {
                JOptionPane.showMessageDialog(null,"ERROR WHILE TRYING TO SELLING_SHOP_1 DELETE...! "+Er, "Attention",0);
            }
            
        }
    }

}
 