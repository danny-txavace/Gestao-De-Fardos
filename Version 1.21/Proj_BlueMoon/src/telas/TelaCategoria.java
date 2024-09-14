package telas;

import Abstrato.Config_idiomas;
import dao.ConexaoDAO;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.border.MatteBorder;
import javax.swing.table.*;
import net.proteanit.sql.DbUtils;

/**
 * @author Ramadan ismaeL
 */

public final class TelaCategoria extends JFrame implements ActionListener, Config_idiomas{
    private JLabel lblID, txtID, lblCategoria;
    private JTextField txtCategoria;
    private JButton btnSalvar, btnEditar, btnRemover;
    private JTable tabela;
    private JScrollPane scroll, scroll_listaCategoria;
    private JList listaCategoria;
    private int adicionado, deleteItem;
    private DefaultListModel model_lista;
    private DefaultTableModel model;
    private Connection conexao = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    
    public TelaCategoria() throws SQLException {
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
            setTitle("CATEGORIA");
        } else {
            setTitle("CATEGORY");
        }
        setSize(386, 270);        
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
        lblCategoria = new JLabel();
        
        txtCategoria = new JTextField();
        
        btnSalvar = new JButton();
        btnEditar = new JButton();
        btnRemover = new JButton();
        
        scroll = new JScrollPane();
        scroll_listaCategoria = new JScrollPane();
        
        listaCategoria = new JList();
                
        lblID.setFont(new Font("Times New Roman", 1, 16));
        lblID.setForeground(Color.black);
        lblID.setBounds(10, 5, 50, 28);
        
        txtID.setText(null);
        txtID.setFont(new Font("Times New Roman", 1, 16));
        txtID.setForeground(Color.red);
        txtID.setBounds(80, 5, 100, 28);
        
        lblCategoria.setFont(new Font("Times New Roman", 1, 16));
        lblCategoria.setForeground(Color.BLACK);
        lblCategoria.setBounds(10, 38, 110, 28);
        
        txtCategoria.setText(null);
        txtCategoria.setFont(new Font("Times New Roman", 0, 15));
        txtCategoria.setForeground(Color.BLACK);
        txtCategoria.setBounds(80, 38, 280, 28);
        txtCategoria.setBorder(new MatteBorder(2, 2, 0, 0, Color.GRAY));
        
        listaCategoria.setFont(new Font("Times New Roman", 0, 15));
        listaCategoria.setForeground(Color.BLACK);
        listaCategoria.setBounds(80, 65, 280, 80);
        listaCategoria.setBorder(null);
        listaCategoria.setOpaque(false);
        
        scroll_listaCategoria.setViewportView(listaCategoria);
        scroll_listaCategoria.setBounds(80, 65, 280, 80);
        scroll_listaCategoria.setVisible(false);   
        scroll_listaCategoria.setBorder(null);
        
        if(Idiomas.getPort() == true) {
            String[] nome = {"ID", "MARCA"};
            String dados[][] = {};        
            tabela = new JTable(dados, nome) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        } else {
            String[] nome = {"ID","CODE"};
            String dados[][] = {};        
            tabela = new JTable(dados, nome) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        }
        
        scroll.setViewportView(tabela);
        scroll.setBounds(5, 130, 363, 99);
        
        btnSalvar.setFont(new Font("Agency FB", 1, 18));
        btnSalvar.setForeground(Color.BLACK);
        btnSalvar.setBounds(5, 90, 117, 25);
        btnSalvar.setOpaque(false);
        
        btnEditar.setFont(new Font("Agency FB", 1, 18));
        btnEditar.setForeground(Color.BLACK);
        btnEditar.setBounds(127, 90, 117, 25);
        btnEditar.setOpaque(false);
        
        btnRemover.setFont(new Font("Agency FB", 1, 18));
        btnRemover.setForeground(Color.BLACK);
        btnRemover.setBounds(248, 90, 117, 25);
        btnRemover.setOpaque(false);
        
        configTools();
        
        getContentPane().add(lblID);
        getContentPane().add(txtID);
        getContentPane().add(lblCategoria);
        getContentPane().add(txtCategoria);
        getContentPane().add(scroll_listaCategoria);
        getContentPane().add(scroll);
        getContentPane().add(btnSalvar);
        getContentPane().add(btnEditar);
        getContentPane().add(btnRemover);
    }
    
    private void configTools() {
        btnSalvar.addActionListener(this);
        btnEditar.addActionListener(this);
        btnRemover.addActionListener(this);
        
        txtCategoria.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                configListarCategoria();
            }
        });
        
        listaCategoria.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                model_lista = (DefaultListModel) listaCategoria.getModel();
                int selected = listaCategoria.getSelectedIndex();
                txtCategoria.setText((String) model_lista.getElementAt(selected));
                scroll_listaCategoria.setVisible(false);
            }
        });
        
        tabela.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(tabela.getSelectedRow() != -1) {
                    int selectedRowIndex = tabela.getSelectedRow();
                    model = (DefaultTableModel) tabela.getModel();
                    txtID.setText(model.getValueAt(selectedRowIndex, 0).toString());
                    txtCategoria.setText(model.getValueAt(selectedRowIndex, 1).toString());
                }
            }
        });
    }
    
    private void configListarCategoria() {
        DefaultListModel<String> modelo = new DefaultListModel<>();
        listaCategoria.setModel(modelo);
        String sql = "select * from tbcategoria where categoria like '%" + txtCategoria.getText() + "%'";
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            
            boolean encontrou = false;

            while (rs.next()) {
                modelo.addElement(rs.getString(2));
                encontrou = true;
            }

            if (encontrou || txtCategoria.getText().isEmpty()) {
                scroll_listaCategoria.setVisible(true);
            } else {
                scroll_listaCategoria.setVisible(false);
            }
            
            if(txtCategoria.getText().isEmpty()) {
                scroll_listaCategoria.setVisible(false);
            } else {
                txtCategoria.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent evt) {
                        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
                            scroll_listaCategoria.setVisible(false);
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
                    JOptionPane.showMessageDialog(null, "Erro no configListarCategoria() - TelaCategoria \n" + erro, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "Error in configListarCategoria() - TelaCategoria \n" + erro, "Attention", 0);
                }
            } catch (SQLException ex) {
                Logger.getLogger(TelaCategoria.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void tabelaDAO() {
        tabelaDAOProced();
        String sql = "select id, categoria from tbcategoria";
        
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            
            tabela.setModel(DbUtils.resultSetToTableModel(rs));
            
            tabela.getColumnModel().getColumn(0).setPreferredWidth(15);
            tabela.getColumnModel().getColumn(1).setPreferredWidth(280);
            
            tabela.setFont(new Font("Times New Roman", 0, 15));
            
            TableColumnModel columnModel = tabela.getColumnModel();
            TableColumn col = columnModel.getColumn(0);
            
            if(Idiomas.getPort() == true) {
                col.setHeaderValue("ID");
                col = columnModel.getColumn(1);
                col.setHeaderValue("CATEGORIA");
            } else {
                col.setHeaderValue("ID");
                col = columnModel.getColumn(1);
                col.setHeaderValue("CATEGORY");
            }
            
            tabela.getTableHeader().repaint();
        } catch(SQLException er) {
            try {
                this.pst.close();
                this.rs.close();
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null,er + " ERRO NA TABELA_DAO - TelaCategoria", "TBL DAO ERROR", 0);
                } else {
                    JOptionPane.showMessageDialog(null,er + " ERROR IN THE DAO_TABLE - TelaCategoria", "TBL DAO ERROR", 0);            
                }
            } catch (SQLException ex) {
                Logger.getLogger(TelaCategoria.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void tabelaDAOProced() {
        String sql = "call Proced_Organizartbcategoria()";
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
        } catch(SQLException er) {
            try {
                this.pst.close();
                this.rs.close();
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null,er + " ERRO NO PROCED_TABELA_DAO - TelaCategoria", "PROCED  TBL  DAO ERROR", 0);
                } else {
                    JOptionPane.showMessageDialog(null,er + " ERROR IN THE DAO_TABLE_PROCED - TelaCategoria", "DAO  TBL  PROCED ERROR", 0); 
                }
            } catch (SQLException ex) {
                Logger.getLogger(TelaCategoria.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void salvar() {
        String sql = "insert into tbcategoria(categoria) values (?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtCategoria.getText());

            if((txtCategoria.getText().isEmpty())) {
                    if(Idiomas.getPort() == true) {
                        JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                    }
                    lblCategoria.setForeground(Color.red);
            } else {
                    adicionado = pst.executeUpdate();
                    
                    if(Idiomas.getPort() == true) {
                        JOptionPane.showMessageDialog(null, "Categoria registrado com sucesso !", "Atenção", 1);
                    } else {
                        JOptionPane.showMessageDialog(null, "Category successfully registered !", "Attention", 1);
                    }
                }
                    if((!txtCategoria.getText().isEmpty())) {
                        lblCategoria.setForeground(Color.black);
                    }
                    
                    if (adicionado > 0) {
                    txtCategoria.setText(null);
                    
                    tabelaDAO();
                    }            
        } catch (HeadlessException | SQLException erro) {
            try {
                this.pst.close();
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, erro+" MENSAGEM DE ERRO AO TENTAR SALVAR - TelaCategoria !", "AVISO", 0);
                } else {
                    JOptionPane.showMessageDialog(null, erro+" ERROR MESSAGE WHEN TRYING TO SAVE - TelaCategoria !", "WARNING", 0);
                }
            } catch (SQLException ex) {
                Logger.getLogger(TelaCategoria.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void update() {
        String sql = "update tbcategoria set categoria = ? where id = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtCategoria.getText());
            pst.setString(2, txtID.getText());

            if((txtCategoria.getText().isEmpty())) {
                    if(Idiomas.getPort() == true) {
                        JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                    }
                    lblCategoria.setForeground(Color.red);
            } else {
                    adicionado = pst.executeUpdate();
                    
                    if(Idiomas.getPort() == true) {
                        JOptionPane.showMessageDialog(null, "Categoria atualizado com sucesso !", "Atenção", 1);
                    } else {
                        JOptionPane.showMessageDialog(null, "Cateogry successfully updated !", "Attention", 1);
                    }
                }
                    if((!txtCategoria.getText().isEmpty())) {
                        lblCategoria.setForeground(Color.black);
                    }
                    
                    if (adicionado > 0) {
                    txtCategoria.setText(null);
                    txtID.setText(null);
                    
                    tabelaDAO();
                    }            
        } catch (HeadlessException | SQLException erro) {
            try {
                this.pst.close();
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, erro+" MENSAGEM DE ERRO AO TENTAR ATUALIZAR - TelaCategoria !", "AVISO", 0);
                } else {
                    JOptionPane.showMessageDialog(null, erro+" ERROR MESSAGE WHEN TRYING TO UPDATE - TelaCategoria !", "WARNING", 0);
                }
            } catch (SQLException ex) {
                Logger.getLogger(TelaCategoria.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void remover() {
        String sql = "delete from tbcategoria where ID = ?";
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
                
                txtID.setText(null);
                txtCategoria.setText(null);
                tabelaDAO();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Removido..!", "Atenção", 1);
                } else {
                    JOptionPane.showMessageDialog(null, "Removed..!", "Attention", 1);
                }               
            }
        } catch(HeadlessException | NumberFormatException | SQLException Er) {
            try {
                this.pst.close();
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null," ERRO AO TENTAR DELETAR - TelaCategoria...! "+Er, "Atenção",0);
                } else {
                    JOptionPane.showMessageDialog(null,"ERROR WHILE TRYING TO DELETE - TelaCategoria...! "+Er, "Attention",0);
                }
            } catch (SQLException ex) {
                Logger.getLogger(TelaCategoria.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnSalvar) {
            salvar();
        } else if(e.getSource() == btnEditar) {
            if(tabela.getSelectedRow() == -1) {
            if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Por favor, selecione alguma categoria na tabela !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a category from the table !", "Attention", 2);
            }
            } else {
                update();
            }            
        } else if(e.getSource() == btnRemover) {
            if(tabela.getSelectedRow() == -1) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Por favor, selecione alguma categoria na tabela !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a category from the table !", "Attention", 2);
                }                
            } else {                
                remover();                
            }
        }
    }

    @Override
    public void configPort() {
        lblID.setText("ID");
        lblCategoria.setText("Categoria");
        btnSalvar.setText("Salvar");
        btnEditar.setText("Atualizar");
        btnRemover.setText("Remover");
    }

    @Override
    public void configEng() {
        lblID.setText("ID");
        lblCategoria.setText("Category");
        btnSalvar.setText("Save");
        btnEditar.setText("Update");
        btnRemover.setText("Remove");
    }
}
