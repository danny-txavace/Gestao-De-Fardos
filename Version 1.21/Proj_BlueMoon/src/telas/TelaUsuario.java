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
import javax.swing.border.MatteBorder;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Ramadan ismaeL
 */

public final class TelaUsuario extends JInternalFrame implements ActionListener, Config_idiomas, Config_remover {
    private JLabel lbltitulo, lblpesquisa;
    private JTextField txtpesquisa;
    private JButton btnAdicionar, btnEditar, btnRemover, btnAtualizar;
    private JTable tabela;
    private JScrollPane scroll;
    private Connection conexao = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private DefaultTableModel model;
    private int deleteItem;
    private TelaAdd_Usuario telaAUsuario = null;
    private TelaEdd_Usuario telaEUsuario = null;
    
    public TelaUsuario() throws SQLException {
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
        
        txtpesquisa = new JTextField();
        
        btnAdicionar = new JButton();
        btnEditar = new JButton();
        btnRemover = new JButton();
        btnAtualizar = new JButton();
        
        tabela = new JTable();
        
        scroll = new JScrollPane();
                
        lbltitulo.setForeground(new Color(0, 191, 255));
        lbltitulo.setBounds(5, 0, 200, 30);
                        
        btnAdicionar.setIcon(new ImageIcon(this.getClass().getResource("/icones/adicionar.png")));
        btnAdicionar.setBounds(5, 90, 430, 30);
        
        btnEditar.setIcon(new ImageIcon(this.getClass().getResource("/icones/editar.png")));
        btnEditar.setBounds(440, 90, 430, 30);
        
        btnRemover.setIcon(new ImageIcon(this.getClass().getResource("/icones/remover.png")));
        btnRemover.setBounds(875, 90, 430, 30);
        
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
            String[] nome = {"ID", "NOME", "PHONE_1", "PHONE_2", "USUÁRIO", "PERFIL", "LOJA", "DATA & HORA"};
            String dados[][] = {};        
            tabela = new JTable(dados, nome) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        } else {
            String[] nome = {"ID", "NAME", "PHONE_1", "PHONE_2", "USERNAME", "PROFIL", "SHOPS", "DATE & TIME"};
            String dados[][] = {};        
            tabela = new JTable(dados, nome) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        }       
               
        scroll.setViewportView(tabela);
        scroll.setBounds(5, 160, 1303, 638);
        
        configTools();
        
        getContentPane().add(lbltitulo);
        getContentPane().add(btnAdicionar);
        getContentPane().add(btnEditar);
        getContentPane().add(btnRemover);
        getContentPane().add(btnAtualizar);
        getContentPane().add(lblpesquisa);
        getContentPane().add(txtpesquisa);
        getContentPane().add(scroll);
    }
    
    private void configTools() {
        btnAdicionar.addActionListener(this);
        btnEditar.addActionListener(this);
        btnRemover.addActionListener(this);
        btnAtualizar.addActionListener(this);
                
        txtpesquisa.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                pesquisar();
            }
        });
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
                if(telaAUsuario == null || !telaAUsuario.isVisible()) {
                    try {
                        telaAUsuario = new TelaAdd_Usuario();
                    } catch (SQLException ex) {
                        Logger.getLogger(TelaUsuario.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    telaAUsuario.setVisible(true);
                    telaAUsuario.toFront();
                } else {
                    telaAUsuario.toFront();
                }
            });
        } else if(evt.getSource() == btnEditar) {
            editar();
        } else if(evt.getSource() == btnAtualizar) {
            SwingUtilities.invokeLater(() -> {
                tabelaDAO();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Atualizado !", "Atenção", 1);
                } else {
                    JOptionPane.showMessageDialog(null, "Updated !", "Attention", 1);
                }
            });
        }
    }
    
    private void tabelaDAO() {
        tabelaDAOProced();
        String sql = "select ID, NOME, PHONE_1, PHONE_2, USUÁRIO, PERFIL, LOJA, DATA_HORA from tbusuario";
        
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            
            tabela.setModel(DbUtils.resultSetToTableModel(rs));
            
            tabela.getColumnModel().getColumn(0).setPreferredWidth(10);
            tabela.getColumnModel().getColumn(1).setPreferredWidth(190);
            tabela.getColumnModel().getColumn(2).setPreferredWidth(120);
            tabela.getColumnModel().getColumn(3).setPreferredWidth(120);
            tabela.getColumnModel().getColumn(4).setPreferredWidth(100);
            tabela.getColumnModel().getColumn(5).setPreferredWidth(100);
            tabela.getColumnModel().getColumn(6).setPreferredWidth(50);
            tabela.getColumnModel().getColumn(7).setPreferredWidth(120);
            
            tabela.setFont(new Font("Times New Roman", 0, 15));
            
            TableColumnModel columnModel = tabela.getColumnModel();
            TableColumn col = columnModel.getColumn(0);
            
            if(Idiomas.getPort() == true) {
                col.setHeaderValue("ID");
                col = columnModel.getColumn(1);
                col.setHeaderValue("NOME");
                col = columnModel.getColumn(2);
                col.setHeaderValue("TELEFONE_1");
                col = columnModel.getColumn(3);
                col.setHeaderValue("TELEFONE_2");
                col = columnModel.getColumn(4);
                col.setHeaderValue("USUÁRIO");
                col = columnModel.getColumn(5);
                col.setHeaderValue("PERFIL");
                col = columnModel.getColumn(6);
                col.setHeaderValue("LOJA");
                col = columnModel.getColumn(7);
                col.setHeaderValue("DATA & HORA");
            } else {
                col.setHeaderValue("ID");
                col = columnModel.getColumn(1);
                col.setHeaderValue("NAME");
                col = columnModel.getColumn(2);
                col.setHeaderValue("PHONE_1");
                col = columnModel.getColumn(3);
                col.setHeaderValue("PHONE_2");
                col = columnModel.getColumn(4);
                col.setHeaderValue("USERNAME");
                col = columnModel.getColumn(5);
                col.setHeaderValue("PROFIL");
                col = columnModel.getColumn(6);
                col.setHeaderValue("SHOPS");
                col = columnModel.getColumn(7);
                col.setHeaderValue("DATE & TIME");
            }
            
            tabela.getTableHeader().repaint();
        } catch(SQLException er) {
            try {
                this.pst.close();
                this.conexao.close();
                this.rs.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null,er + " ERRO NA TABELA_DAO USUÁRIO", "TBL   DAO", 0);
                } else {
                    JOptionPane.showMessageDialog(null,er + " ERROR IN THE USER DAO_TABLE", "TBL   DAO", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void tabelaDAOProced() {
        String sql = "call Proced_Organizartbusuario()";
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
        } catch(SQLException er) {
            if(Idiomas.getPort() == true) {
                try {
                    this.pst.close();
                    this.conexao.close();
                    this.rs.close();
                    JOptionPane.showMessageDialog(null,er + " ERRO NO PROCED_TABELA_DAO USUÁRIO", "PROCED  TBL  DAO", 0);
                } catch (SQLException ex) {
                    Logger.getLogger(TelaUsuario.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                JOptionPane.showMessageDialog(null,er + " ERROR IN THE USER DAO_TABLE_PROCED", "DAO  TBL  PROCED", 0);
            } 
        }
    }
    
    private void pesquisar() {
        String sql = "select ID, NOME, PHONE_1, PHONE_2, USUÁRIO, PERFIL, LOJA, DATA_HORA from tbusuario where NOME like ? || PERFIL like ? || LOJA like ?";
        
        try{
            pst = conexao.prepareStatement(sql);

            pst.setString(1, "%" + txtpesquisa.getText() + "%");
            pst.setString(2, "%" + txtpesquisa.getText() + "%");
            pst.setString(3, "%" + txtpesquisa.getText() + "%");

            rs = pst.executeQuery();
            
            tabela.setModel(DbUtils.resultSetToTableModel(rs));
            
            tabela.getColumnModel().getColumn(0).setPreferredWidth(10);
            tabela.getColumnModel().getColumn(1).setPreferredWidth(190);
            tabela.getColumnModel().getColumn(2).setPreferredWidth(120);
            tabela.getColumnModel().getColumn(3).setPreferredWidth(120);
            tabela.getColumnModel().getColumn(4).setPreferredWidth(100);
            tabela.getColumnModel().getColumn(5).setPreferredWidth(100);
            tabela.getColumnModel().getColumn(6).setPreferredWidth(50);
            tabela.getColumnModel().getColumn(7).setPreferredWidth(120);
            
            tabela.setFont(new Font("Times New Roman", 0, 15));
            
            TableColumnModel columnModel = tabela.getColumnModel();
            TableColumn col = columnModel.getColumn(0);
            
            if(Idiomas.getPort() == true) {
                col.setHeaderValue("ID");
                col = columnModel.getColumn(1);
                col.setHeaderValue("NOME");
                col = columnModel.getColumn(2);
                col.setHeaderValue("TELEFONE_1");
                col = columnModel.getColumn(3);
                col.setHeaderValue("TELEFONE_2");
                col = columnModel.getColumn(4);
                col.setHeaderValue("USUÁRIO");
                col = columnModel.getColumn(5);
                col.setHeaderValue("PERFIL");
                col = columnModel.getColumn(6);
                col.setHeaderValue("LOJA");
                col = columnModel.getColumn(7);
                col.setHeaderValue("DATA & HORA");
            } else {
                col.setHeaderValue("ID");
                col = columnModel.getColumn(1);
                col.setHeaderValue("NAME");
                col = columnModel.getColumn(2);
                col.setHeaderValue("PHONE_1");
                col = columnModel.getColumn(3);
                col.setHeaderValue("PHONE_2");
                col = columnModel.getColumn(4);
                col.setHeaderValue("USERNAME");
                col = columnModel.getColumn(5);
                col.setHeaderValue("PROFIL");
                col = columnModel.getColumn(6);
                col.setHeaderValue("SHOPS");
                col = columnModel.getColumn(7);
                col.setHeaderValue("DATE & TIME");
            }
            
            tabela.getTableHeader().repaint();
        } catch(SQLException err) {
            try {
                this.pst.close();
                this.conexao.close();
                this.rs.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO AO TENTAR PESQUISAR USUÁRIO..! "+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR WHILE TRYING TO USER SEARCH..! "+err, "Attention", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
        
    private void editar() {
        if(tabela.getSelectedRow() == -1) {
            if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Por favor, selecione algum usuário na tabela !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a user from the table !", "Attention", 2);
            }
        } else {
            if(telaEUsuario == null || !telaEUsuario.isVisible()) {
                try {
                    telaEUsuario = new TelaEdd_Usuario();
                } catch (SQLException ex) {
                    Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
                telaEUsuario.setVisible(true);
            } else {
                telaEUsuario.toFront();
            }
            int selectedRowIndex = tabela.getSelectedRow();
            model = (DefaultTableModel) tabela.getModel();
            telaEUsuario.txtID.setText(model.getValueAt(selectedRowIndex, 0).toString());
            telaEUsuario.txtNome.setText(model.getValueAt(selectedRowIndex, 1).toString());
            telaEUsuario.txtPhone_1.setText(model.getValueAt(selectedRowIndex, 2).toString());
            telaEUsuario.txtPhone_2.setText(model.getValueAt(selectedRowIndex, 3).toString());
            telaEUsuario.txtUsuario.setText(model.getValueAt(selectedRowIndex, 4).toString());
            telaEUsuario.txtPerfil.setSelectedItem(model.getValueAt(selectedRowIndex, 5).toString());
            telaEUsuario.txtLoja.setSelectedItem(model.getValueAt(selectedRowIndex, 6).toString());
        }
    }

    @Override
    public void configPort() {
        lbltitulo.setText("*  U S U Á R I O S  *");
        lbltitulo.setFont(new Font("Times New Roman", 3, 21));
        btnAdicionar.setText("Adicionar");
        btnAdicionar.setFont(new Font("Agency FB", 1, 20));
        btnEditar.setText("Editar");
        btnEditar.setFont(new Font("Agency FB", 1, 20));
        btnRemover.setText("Remover");
        btnRemover.setFont(new Font("Agency FB", 1, 20));
    }

    @Override
    public void configEng() {
        lbltitulo.setText("*  U S E R S  *");
        lbltitulo.setFont(new Font("Times New Roman", 3, 21));
        btnAdicionar.setText("Add");
        btnAdicionar.setFont(new Font("Agency FB", 1, 20));
        btnEditar.setText("Edit");
        btnEditar.setFont(new Font("Agency FB", 1, 20));
        btnRemover.setText("Remove");
        btnRemover.setFont(new Font("Agency FB", 1, 20));
    }
    
    @Override
    public void remover1() {
        String sql = "delete from tbusuario where ID = ?";
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
        String sql = "delete from tbusuario where ID in (?, ?)";
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
        String sql = "delete from tbusuario where ID in (?, ?, ?)";
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
        String sql = "delete from tbusuario where ID in (?, ?, ?, ?)";
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
        String sql = "delete from tbusuario where ID in (?, ?, ?, ?, ?)";
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
        String sql = "delete from tbusuario where ID in (?, ?, ?, ?, ?, ?)";
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
        String sql = "delete from tbusuario where ID in (?, ?, ?, ?, ?, ?, ?)";
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
        String sql = "delete from tbusuario where ID in (?, ?, ?, ?, ?, ?, ?, ?)";
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
        String sql = "delete from tbusuario where ID in (?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
        String sql = "delete from tbusuario where ID in (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
 