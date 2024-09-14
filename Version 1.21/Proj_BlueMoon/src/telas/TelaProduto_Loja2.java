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

public final class TelaProduto_Loja2 extends JInternalFrame implements ActionListener, Config_idiomas, Config_remover {
    private JLabel lbltitulo, lblpesquisa, lblloja_1, lbltotal;
    private JTextField txtpesquisa, txtquant, txtPreco_artigo_mzn, txtPreco_total_mzn, txtPreco_artigo_usd, txtPreco_total_usd;
    private JButton btnAdicionar, btnEditar, btnRemover, btnTransferir, btnAtualizar;
    private JTable tabela;
    private JScrollPane scroll;
    private Connection conexao = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private DefaultTableModel model;
    private int deleteItem, soma = 0;
    private double somaVlr = 0.00;
    private TelaAdd_Produto_Loja2 telaAproduto = null;
    private TelaEdd_Produto_Loja2 telaEproduto = null;
    private Transferir_Loja2 transferir = null;
    
    public TelaProduto_Loja2() throws SQLException {
        conexao = ConexaoDAO.conector();
        
        Janela();
        configView();
        
        if(Idiomas.getPort() == true) {
            configPort();
        } else {
            configEng();
        }
        
        tabelaDAO();
        
        setVisible(true);
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
        txtPreco_artigo_usd = new JTextField();
        txtPreco_total_usd = new JTextField();
        
        btnAdicionar = new JButton();
        btnEditar = new JButton();
        btnRemover = new JButton();
        btnTransferir = new JButton();
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
        
        btnTransferir.setIcon(new ImageIcon(this.getClass().getResource("/icones/transferir.png")));
        btnTransferir.setBounds(985, 90, 322, 30);
        
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
            String[] nome = {"ID", "DESIGNAÇÃO", "MARCA", "CATEGORIA", "UNIDADE", "QUANT.", "VLR_VENDA(MZN)", "VLR_TOTAL(MZN)", "VLR_VENDA(USD)", "VLR_TOTAL(USD)", "DATA & HORA"};
            String dados[][] = {};        
            tabela = new JTable(dados, nome) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        } else {
            String[] nome = {"ID", "ITEM DESCRIPTION", "CODE", "CATEGORY", "UNIT", "QUANT.", "SELLING PRICE(MZN)", "TOTAL PRICE(MZN)", "SELLING PRICE(USD)", "TOTAL PRICE(USD)", "DATE & TIME"};
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
        lbltotal.setBounds(445, 762, 100, 35);
        
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
        
        txtPreco_artigo_usd.setText("artigo USD");
        txtPreco_artigo_usd.setFont(new Font("Agency FB", 1, 17));
        txtPreco_artigo_usd.setForeground(Color.BLACK);
        txtPreco_artigo_usd.setBackground(new Color(220, 220, 220));
        txtPreco_artigo_usd.setBounds(886, 762, 130, 35);
        txtPreco_artigo_usd.setEditable(false);
        
        txtPreco_total_usd.setText("total USD");
        txtPreco_total_usd.setFont(new Font("Agency FB", 1, 17));
        txtPreco_total_usd.setForeground(Color.BLACK);
        txtPreco_total_usd.setBackground(new Color(220, 220, 220));
        txtPreco_total_usd.setBounds(1019, 762, 130, 35);
        txtPreco_total_usd.setEditable(false);
        
        configTools();        
        
        getContentPane().add(lbltitulo);
        getContentPane().add(lblloja_1);
        getContentPane().add(btnAdicionar);
        getContentPane().add(btnEditar);
        getContentPane().add(btnRemover);
        getContentPane().add(btnTransferir);
        getContentPane().add(btnAtualizar);
        getContentPane().add(lblpesquisa);
        getContentPane().add(txtpesquisa);
        getContentPane().add(scroll);
        getContentPane().add(lbltotal);
        getContentPane().add(txtquant);
        getContentPane().add(txtPreco_artigo_mzn);
        getContentPane().add(txtPreco_total_mzn);
        getContentPane().add(txtPreco_artigo_usd);
        getContentPane().add(txtPreco_total_usd);
    }
    
    private void configTools() {
        btnAdicionar.addActionListener(this);
        btnEditar.addActionListener(this);
        btnRemover.addActionListener(this);
        btnAtualizar.addActionListener(this);
        btnTransferir.addActionListener(this);
        
        txtpesquisa.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                pesquisar();
                pesquisar_calc_Quant();
                pesquisar_calc_Preco_Artigo_MZN();
                pesquisar_calc_Preco_Total_MZN();
                pesquisar_calc_Preco_Artigo_USD();
                pesquisar_calc_Preco_Total_USD();                
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
                if(telaAproduto == null || !telaAproduto.isVisible()) {
                    try {
                        telaAproduto = new TelaAdd_Produto_Loja2();
                    } catch (SQLException ex) {
                        Logger.getLogger(TelaProduto_Loja2.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    telaAproduto.setVisible(true);
                    telaAproduto.toFront();
                } else {
                    telaAproduto.toFront();
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
        } else if(evt.getSource() == btnTransferir) {
            SwingUtilities.invokeLater(() -> {
                if(transferir == null || !transferir.isVisible()) {
                    transferir = new Transferir_Loja2();
                    transferir.setVisible(true);
                    transferir.toFront();
                } else {
                    transferir.toFront();
                }
            });
        }
    }
    
    private void tabelaDAO() {
        tabelaDAOProced();
        String sql = "select * from tbloja_2";
        
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
                col.setHeaderValue("PREÇO_ARTIGO(USD)");
                col = columnModel.getColumn(9);
                col.setHeaderValue("PREÇO_TOTAL(USD)");
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
                col.setHeaderValue("ARTC. PRICE(USD)");
                col = columnModel.getColumn(9);
                col.setHeaderValue("TOTAL PRICE(USD)");
                col = columnModel.getColumn(10);
                col.setHeaderValue("DATE & TIME");
            }
            
            tabela.getTableHeader().repaint();
        } catch(SQLException er) {
            try {
                this.pst.close();
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null,er + " ERRO NA TABELA_DAO() - TelaProduto_Loja2", "TBL   DAO", 0);
                } else {
                    JOptionPane.showMessageDialog(null,er + " ERROR IN THE DAO_TABLE() - TelaProduto_Loja2", "TBL   DAO", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaProduto_Loja2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void tabelaDAOProced() {
        String sql = "call Proced_Organizartbloja_2()";
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
        } catch(SQLException er) {
            if(Idiomas.getPort() == true) {
                try {
                    this.pst.close();
                    this.rs.close();
                    this.conexao.close();
                    JOptionPane.showMessageDialog(null,er + " ERRO NO PROCED_TABELA_DAO() - TelaProduto_Loja2", "PROCED  TBL  DAO", 0);
                } catch (SQLException ex) {
                    Logger.getLogger(TelaProduto_Loja2.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(null,er + " ERROR IN THE DAO_TABLE_PROCED() - TelaProduto_Loja2", "DAO  TBL  PROCED", 0);
            } 
        }
    }
    
    private void pesquisar() {
        String sql = "select * from tbloja_2 where DESIGNAÇÃO like ? || MARCA like ? || CATEGORIA like ? || DATA_HORA like ?";
        
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
                col.setHeaderValue("PREÇO_ARTIGO(USD)");
                col = columnModel.getColumn(9);
                col.setHeaderValue("PREÇO_TOTAL(USD)");
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
                col.setHeaderValue("ARTC. PRICE(USD)");
                col = columnModel.getColumn(9);
                col.setHeaderValue("TOTAL PRICE(USD)");
                col = columnModel.getColumn(10);
                col.setHeaderValue("DATE & TIME");
            }
            
            tabela.getTableHeader().repaint();
        } catch(SQLException err) {
            try {
                this.conexao.close();
                this.rs.close();
                this.pst.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO AO TENTAR PESQUISAR - TelaProduto_Loja2..! \n"+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR WHILE TRYING TO SEARCH - TelaProduto_Loja2..! \n"+err, "Attention", 0);            
                }
            } catch (SQLException ex) {
                Logger.getLogger(TelaProduto_Loja2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void editar() {
        if(tabela.getSelectedRow() == -1) {
            if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Por favor, selecione algum produto na tabela !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a product from the table !", "Attention", 2);
            }
        } else {
                SwingUtilities.invokeLater(() -> {
                if(telaEproduto == null || !telaEproduto.isVisible()) {
                    try {
                        telaEproduto = new TelaEdd_Produto_Loja2();
                    } catch (SQLException ex) {
                        Logger.getLogger(TelaProduto_Loja2.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    telaEproduto.setVisible(true);
                    telaEproduto.toFront();
                    
                    int selectedRowIndex = tabela.getSelectedRow();
                    model = (DefaultTableModel) tabela.getModel();
                    telaEproduto.txtID.setText(model.getValueAt(selectedRowIndex, 0).toString());
                    telaEproduto.txtDesignacao.setText(model.getValueAt(selectedRowIndex, 1).toString());
                    telaEproduto.txtMarca.setText(model.getValueAt(selectedRowIndex, 2).toString());
                    telaEproduto.txtCategoria.setSelectedItem(model.getValueAt(selectedRowIndex, 3));
                    telaEproduto.txtUnidade.setText(model.getValueAt(selectedRowIndex, 4).toString());
                    telaEproduto.txtQuantidade.setValue(model.getValueAt(selectedRowIndex, 5));
                    telaEproduto.txtPreco.setText(model.getValueAt(selectedRowIndex, 6).toString());
                    telaEproduto.txtPrecousd.setText(model.getValueAt(selectedRowIndex, 8).toString());
                } else {
                    telaEproduto.toFront();
                }
            });
        }
    }
    
    private void configCalc() {
        calc_Quant();
        calc_Preco_Artigo_MZN();
        calc_Preco_Total_MZN();
        calc_Preco_Artigo_USD();
        calc_Preco_Total_USD();
    }
    
    private void calc_Quant() {
        String sql = "select sum( QUANTIDADE ) as quant from tbloja_2";
        
        try{
            rs = conexao.createStatement().executeQuery(sql);
            
            while (rs.next()) {
                soma = rs.getInt("quant");
            }          
            
            txtquant.setText("  "+soma);
            
        } catch(SQLException err) {
            try {
                this.pst.close();
                this.conexao.close();
                this.rs.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO NO calc_Quant() - TelaProduto_Loja2..! \n"+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR IN calc_Quant() - TelaProduto_Loja2..! \n"+err, "Attention", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaProduto_Loja2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void pesquisar_calc_Quant() {
        String sql = "select sum( QUANTIDADE ) as Pquant from tbloja_2 where DESIGNAÇÃO like ? || MARCA like ? || CATEGORIA like ? || DATA_HORA like ?";
        
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
            if(Idiomas.getPort() == true) {
                try {
                    this.pst.close();
                    this.conexao.close();
                    this.rs.close();
                    JOptionPane.showMessageDialog(null, "ERRO AO TENTAR pesquisar_calc_Quant() - TelaProduto_Loja2..! \n"+err, "Atenção", 0);
                } catch (SQLException ex) {
                    Logger.getLogger(TelaProduto_Loja2.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "ERROR WHILE TRYING TO pesquisar_calc_Quant() - TelaProduto_Loja2..! \n"+err, "Attention", 0);
            }            
        }
    }
    
    private void calc_Preco_Artigo_MZN() {
        String sql = "select sum( PRECO_ARTIGO_MZN ) as somaartigomzn from tbloja_2";
        
        try{
            rs = conexao.createStatement().executeQuery(sql);
            
            while (rs.next()) {
                somaVlr = rs.getDouble("somaartigomzn");
            }          
            
            txtPreco_artigo_mzn.setText(" MZN  "+somaVlr);
            
        } catch(SQLException err) {
            try {
                this.pst.close();
                this.conexao.close();
                this.rs.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO NO calc_Preco_Artigo_MZN() - TelaProduto_Loja2..! "+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR IN calc_Preco_Artigo_MZN() - TelaProduto_Loja2..! "+err, "Attention", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaProduto_Loja2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void pesquisar_calc_Preco_Artigo_MZN() {
        String sql = "select sum( PRECO_ARTIGO_MZN ) as Psomaartigomzn from tbloja_2 where DESIGNAÇÃO like ? || MARCA like ? || CATEGORIA like ? || DATA_HORA like ?";
        
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
            
            txtPreco_artigo_mzn.setText(" MZN  "+somaVlr);
        } catch(SQLException err) {
            if(Idiomas.getPort() == true) {
                try {
                    this.pst.close();
                    this.conexao.close();
                    JOptionPane.showMessageDialog(null, "ERRO AO TENTAR pesquisar_calc_Preco_Artigo_MZN() - TelaProduto_Loja2..! \n"+err, "Atenção", 0);
                } catch (SQLException ex) {
                    Logger.getLogger(TelaProduto_Loja2.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(null, "ERROR WHILE TRYING TO pesquisar_calc_Preco_Artigo_MZN() - TelaProduto_Loja2..! \n"+err, "Attention", 0);
            }            
        }
    }
    
    private void calc_Preco_Total_MZN() {
        String sql = "select sum( PRECO_TOTAL_MZN ) as somatotalmzn from tbloja_2";
        
        try{
            rs = conexao.createStatement().executeQuery(sql);
            
            while (rs.next()) {
                somaVlr = rs.getDouble("somatotalmzn");
            }          
            
            txtPreco_total_mzn.setText(" MZN  "+somaVlr);
            
        } catch(SQLException err) {
            try {
                this.pst.close();
                this.conexao.close();
                this.rs.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO NO calc_Preco_Total_MZN() - TelaProduto_Loja2..! "+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR IN calc_Preco_Total_MZN() - TelaProduto_Loja2..! "+err, "Attention", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaProduto_Loja2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void pesquisar_calc_Preco_Total_MZN() {
        String sql = "select sum( PRECO_TOTAL_MZN ) as Psomatotalmzn from tbloja_2 where DESIGNAÇÃO like ? || MARCA like ? || CATEGORIA like ? || DATA_HORA like ?";
        
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
            
            txtPreco_total_mzn.setText(" MZN  "+somaVlr);
        } catch(SQLException err) {
            try {
                this.pst.close();
                this.conexao.close();
                this.rs.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO AO TENTAR pesquisar_calc_Preco_Total_MZN() - TelaProduto_Loja2..! \n"+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR WHILE TRYING TO pesquisar_calc_Preco_Total_MZN() - TelaProduto_Loja2..! \n"+err, "Attention", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaProduto_Loja2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void calc_Preco_Artigo_USD() {
        String sql = "select sum( PRECO_ARTIGO_USD ) as somaartigousd from tbloja_2";
        
        try{
            rs = conexao.createStatement().executeQuery(sql);
            
            while (rs.next()) {
                somaVlr = rs.getDouble("somaartigousd");
            }          
            
            txtPreco_artigo_usd.setText(" $  "+somaVlr);
            
        } catch(SQLException err) {
            try {
                this.pst.close();
                this.conexao.close();
                this.rs.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO NO calc_Preco_Artigo_USD() - TelaProduto_Loja2..! "+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR IN calc_Preco_Artigo_USD() - TelaProduto_Loja2..! "+err, "Attention", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaProduto_Loja2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void pesquisar_calc_Preco_Artigo_USD() {
        String sql = "select sum( PRECO_ARTIGO_USD ) as Psomaartigousd from tbloja_2 where DESIGNAÇÃO like ? || MARCA like ? || CATEGORIA like ? || DATA_HORA like ?";
        
        try{
            pst = conexao.prepareStatement(sql);

            pst.setString(1, "%" + txtpesquisa.getText() + "%");
            pst.setString(2, "%" + txtpesquisa.getText() + "%");
            pst.setString(3, "%" + txtpesquisa.getText() + "%");
            pst.setString(4, "%" + txtpesquisa.getText() + "%");

            rs = pst.executeQuery();

            while (rs.next()) {
                somaVlr = rs.getDouble("Psomaartigousd");
            }          
            
            txtPreco_artigo_usd.setText(" $  "+somaVlr);
        } catch(SQLException err) {
            try {
                this.pst.close();
                this.conexao.close();
                this.rs.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO AO TENTAR pesquisar_calc_Preco_Artigo_USD() - TelaProduto_Loja2..! \n"+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR WHILE TRYING TO pesquisar_calc_Preco_Artigo_USD() - TelaProduto_Loja2..! \n"+err, "Attention", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaProduto_Loja2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void calc_Preco_Total_USD() {
        String sql_quant = "select sum( PRECO_TOTAL_USD ) as somatotalusd from tbloja_2";
        
        try{
            rs = conexao.createStatement().executeQuery(sql_quant);
            
            while (rs.next()) {
                somaVlr = rs.getDouble("somatotalusd");
            }          
            
            txtPreco_total_usd.setText(" $  "+somaVlr);
            
        } catch(SQLException err) {
            try {
                this.pst.close();
                this.conexao.close();
                this.rs.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO NO calc_Preco_Total_USD() - TelaProduto_Loja2..! "+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR IN  calc_Preco_Total_USD() - TelaProduto_Loja2..! "+err, "Attention", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaProduto_Loja2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
            
    private void pesquisar_calc_Preco_Total_USD() {
        String sql = "select sum( PRECO_TOTAL_USD ) as Psomatotalusd from tbloja_2 where DESIGNAÇÃO like ? || MARCA like ? || CATEGORIA like ? || DATA_HORA like ?";
        
        try{
            pst = conexao.prepareStatement(sql);

            pst.setString(1, "%" + txtpesquisa.getText() + "%");
            pst.setString(2, "%" + txtpesquisa.getText() + "%");
            pst.setString(3, "%" + txtpesquisa.getText() + "%");
            pst.setString(4, "%" + txtpesquisa.getText() + "%");

            rs = pst.executeQuery();

            while (rs.next()) {
                somaVlr = rs.getDouble("Psomatotalusd");
            }          
            
            txtPreco_total_usd.setText(" $  "+somaVlr);
        } catch(SQLException err) {
            try {
                this.pst.close();
                this.conexao.close();
                this.rs.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO AO TENTAR pesquisar_calc_Preco_Total_USD() - TelaProduto_Loja2 ..! \n"+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR WHILE TRYING TO pesquisar_calc_Preco_Total_USD() - TelaProduto_Loja2..! \n"+err, "Attention", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaProduto_Loja2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void configPort() {
        lbltitulo.setText("*  P R O D U T O S  *");
        lbltitulo.setFont(new Font("Times New Roman", 3, 21));
        btnAdicionar.setText("Adicionar");
        btnAdicionar.setFont(new Font("Agency FB", 1, 20));
        btnEditar.setText("Editar");
        btnEditar.setFont(new Font("Agency FB", 1, 20));
        btnRemover.setText("Remover");
        btnRemover.setFont(new Font("Agency FB", 1, 20));
        btnTransferir.setText("Transferir");
        btnTransferir.setFont(new Font("Agency FB", 1, 20));
        lblloja_1.setText("# LOJA_2");
    }

    @Override
    public void configEng() {
        lbltitulo.setText("*  P R O D U C T S  *");
        lbltitulo.setFont(new Font("Times New Roman", 3, 21));
        btnAdicionar.setText("Add");
        btnAdicionar.setFont(new Font("Agency FB", 1, 20));
        btnEditar.setText("Edit");
        btnEditar.setFont(new Font("Agency FB", 1, 20));
        btnRemover.setText("Remove");
        btnRemover.setFont(new Font("Agency FB", 1, 20));
        btnTransferir.setText("Transfer");
        btnTransferir.setFont(new Font("Agency FB", 1, 20));
        lblloja_1.setText("# SHOP_2");
    }
    
    @Override
    public void remover1() {
        String sql = "delete from tbloja_2 where ID = ?";
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
                JOptionPane.showMessageDialog(null," ERRO AO TENTAR DELETAR - TelaProduto_Loja2...! "+Er, "Atenção",0);
            } else {
                JOptionPane.showMessageDialog(null,"ERROR WHILE TRYING TO DELETE - TelaProduto_Loja2...! "+Er, "Attention",0);
            }
            
        }
    }

    @Override
    public void remover2() {
        int[] sr = tabela.getSelectedRows();
        String sql = "delete from tbloja_2 where ID in (?, ?)";
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
                JOptionPane.showMessageDialog(null," ERRO AO TENTAR DELETAR - TelaProduto_Loja2...! "+Er, "Atenção",0);
            } else {
                JOptionPane.showMessageDialog(null,"ERROR WHILE TRYING TO DELETE - TelaProduto_Loja2...! "+Er, "Attention",0);
            }
            
        }
    }

    @Override
    public void remover3() {
        int[] sr = tabela.getSelectedRows();
        String sql = "delete from tbloja_2 where ID in (?, ?, ?)";
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
                JOptionPane.showMessageDialog(null," ERRO AO TENTAR DELETAR - TelaProduto_Loja2...! "+Er, "Atenção",0);
            } else {
                JOptionPane.showMessageDialog(null,"ERROR WHILE TRYING TO DELETE - TelaProduto_Loja2...! "+Er, "Attention",0);
            }
            
        }
    }

    @Override
    public void remover4() {
        int[] sr = tabela.getSelectedRows();
        String sql = "delete from tbloja_2 where ID in (?, ?, ?, ?)";
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
                JOptionPane.showMessageDialog(null," ERRO AO TENTAR DELETAR - TelaProduto_Loja2...! "+Er, "Atenção",0);
            } else {
                JOptionPane.showMessageDialog(null,"ERROR WHILE TRYING TO DELETE - TelaProduto_Loja2...! "+Er, "Attention",0);
            }
            
        }
    }

    @Override
    public void remover5() {
        int[] sr = tabela.getSelectedRows();
        String sql = "delete from tbloja_2 where ID in (?, ?, ?, ?, ?)";
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
                JOptionPane.showMessageDialog(null," ERRO AO TENTAR DELETAR - TelaProduto_Loja2...! "+Er, "Atenção",0);
            } else {
                JOptionPane.showMessageDialog(null,"ERROR WHILE TRYING TO DELETE - TelaProduto_Loja2...! "+Er, "Attention",0);
            }
            
        }
    }

    @Override
    public void remover6() {
        int[] sr = tabela.getSelectedRows();
        String sql = "delete from tbloja_2 where ID in (?, ?, ?, ?, ?, ?)";
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
                JOptionPane.showMessageDialog(null," ERRO AO TENTAR DELETAR - TelaProduto_Loja2..! "+Er, "Atenção",0);
            } else {
                JOptionPane.showMessageDialog(null,"ERROR WHILE TRYING TO DELETE - TelaProduto_Loja2...! "+Er, "Attention",0);
            }
            
        }
    }

    @Override
    public void remover7() {
        int[] sr = tabela.getSelectedRows();
        String sql = "delete from tbloja_2 where ID in (?, ?, ?, ?, ?, ?, ?)";
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
                JOptionPane.showMessageDialog(null," ERRO AO TENTAR DELETAR - TelaProduto_Loja2...! "+Er, "Atenção",0);
            } else {
                JOptionPane.showMessageDialog(null,"ERROR WHILE TRYING TO DELETE - TelaProduto_Loja2...! "+Er, "Attention",0);
            }
            
        }
    }

    @Override
    public void remover8() {
        int[] sr = tabela.getSelectedRows();
        String sql = "delete from tbloja_2 where ID in (?, ?, ?, ?, ?, ?, ?, ?)";
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
                JOptionPane.showMessageDialog(null," ERRO AO TENTAR DELETAR - TelaProduto_Loja2...! "+Er, "Atenção",0);
            } else {
                JOptionPane.showMessageDialog(null,"ERROR WHILE TRYING TO DELETE - TelaProduto_Loja2...! "+Er, "Attention",0);
            }
            
        }
    }

    @Override
    public void remover9() {
        int[] sr = tabela.getSelectedRows();
        String sql = "delete from tbloja_2 where ID in (?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
                JOptionPane.showMessageDialog(null," ERRO AO TENTAR DELETAR - TelaProduto_Loja2...! "+Er, "Atenção",0);
            } else {
                JOptionPane.showMessageDialog(null,"ERROR WHILE TRYING TO DELETE - TelaProduto_Loja2...! "+Er, "Attention",0);
            }
            
        }
    }

    @Override
    public void remover10() {
        int[] sr = tabela.getSelectedRows();
        String sql = "delete from tbloja_2 where ID in (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
                JOptionPane.showMessageDialog(null," ERRO AO TENTAR DELETAR - TelaProduto_Loja2...! "+Er, "Atenção",0);
            } else {
                JOptionPane.showMessageDialog(null,"ERROR WHILE TRYING TO DELETE - TelaProduto_Loja2...! "+Er, "Attention",0);
            }
            
        }
    }
}
 