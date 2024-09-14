package telas;

import Abstrato.Config_idiomas;
import dao.ConexaoDAO;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.MatteBorder;
import javax.swing.table.*;
import net.proteanit.sql.DbUtils;

/**
 * @author Ramadan ismaeL
 */
public final class TelaMarca extends JFrame implements ActionListener, Config_idiomas{
    private JLabel lblID, txtID, lblMarca;
    private JTextField txtMarca;
    private JButton btnSalvar, btnEditar, btnRemover;
    private JTable tabela;
    private JScrollPane scroll, scroll_listaMarca;
    private JList listaMarca;
    private int adicionado, deleteItem;
    private DefaultListModel model_lista;
    private DefaultTableModel model;
    private Connection conexao = null;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    
    public TelaMarca() throws SQLException {
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
            setTitle("MARCA");
        } else {
            setTitle("CODE");
        }
        setSize(386, 320);        
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
        lblMarca = new JLabel();
        
        txtMarca = new JTextField();
        
        btnSalvar = new JButton();
        btnEditar = new JButton();
        btnRemover = new JButton();
        
        scroll = new JScrollPane();
        scroll_listaMarca = new JScrollPane();
        
        listaMarca = new JList();
                
        lblID.setFont(new Font("Times New Roman", 1, 16));
        lblID.setForeground(Color.black);
        lblID.setBounds(10, 5, 50, 28);
        
        txtID.setText(null);
        txtID.setFont(new Font("Times New Roman", 1, 16));
        txtID.setForeground(Color.red);
        txtID.setBounds(80, 5, 100, 28);
        
        lblMarca.setFont(new Font("Times New Roman", 1, 16));
        lblMarca.setForeground(Color.BLACK);
        lblMarca.setBounds(10, 38, 110, 28);
        
        txtMarca.setText(null);
        txtMarca.setFont(new Font("Times New Roman", 0, 15));
        txtMarca.setForeground(Color.BLACK);
        txtMarca.setBounds(80, 38, 280, 28);
        txtMarca.setBorder(new MatteBorder(2, 2, 0, 0, Color.GRAY));
        
        listaMarca.setFont(new Font("Times New Roman", 0, 15));
        listaMarca.setForeground(Color.BLACK);
        listaMarca.setBounds(80, 65, 280, 80);
        listaMarca.setBorder(null);
        listaMarca.setOpaque(false);
        
        scroll_listaMarca.setViewportView(listaMarca);
        scroll_listaMarca.setBounds(80, 65, 280, 80);
        scroll_listaMarca.setVisible(false);   
        scroll_listaMarca.setBorder(null);
        
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
        scroll.setBounds(5, 130, 363, 149);
        
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
        getContentPane().add(lblMarca);
        getContentPane().add(txtMarca);
        getContentPane().add(scroll_listaMarca);
        getContentPane().add(scroll);
        getContentPane().add(btnSalvar);
        getContentPane().add(btnEditar);
        getContentPane().add(btnRemover);
    }
    
    private void configTools() {
        btnSalvar.addActionListener(this);
        btnEditar.addActionListener(this);
        btnRemover.addActionListener(this);
        
        txtMarca.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                configListarMarca();
            }
        });
        
        listaMarca.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                model_lista = (DefaultListModel) listaMarca.getModel();
                int selected = listaMarca.getSelectedIndex();
                txtMarca.setText((String) model_lista.getElementAt(selected));
                scroll_listaMarca.setVisible(false);
            }
        });
        
        tabela.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(tabela.getSelectedRow() != -1) {
                    int selectedRowIndex = tabela.getSelectedRow();
                    model = (DefaultTableModel) tabela.getModel();
                    txtID.setText(model.getValueAt(selectedRowIndex, 0).toString());
                    txtMarca.setText(model.getValueAt(selectedRowIndex, 1).toString());
                }
            }
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
            } else {
                txtMarca.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent evt) {
                        if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
                            scroll_listaMarca.setVisible(false);
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
                    JOptionPane.showMessageDialog(null, "Erro no configListarMarca() \n" + erro, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "Error in configListarMarca() \n" + erro, "Attention", 0);
                }
            } catch (SQLException ex) {
                Logger.getLogger(TelaMarca.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    protected void tabelaDAO() {
        tabelaDAOProced();
        String sql = "select id, marca from tbmarca";
        
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
                col.setHeaderValue("MARCA");
            } else {
                col.setHeaderValue("ID");
                col = columnModel.getColumn(1);
                col.setHeaderValue("CODE");
            }
            
            tabela.getTableHeader().repaint();
        } catch(SQLException er) {
            try {
                this.pst.close();
                this.rs.close();
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null,er + " ERRO NA TABELA_DAO", "TBL   DAO", 0);
                } else {
                    JOptionPane.showMessageDialog(null,er + " ERROR IN THE DAO_TABLE", "TBL   DAO", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaMarca.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void tabelaDAOProced() {
        String sql = "call Proced_Organizartbmarca()";
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
        } catch(SQLException er) {
            try {
                this.pst.close();
                this.rs.close();
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null,er + " ERRO NO PROCED_TABELA_DAO", "PROCED  TBL  DAO", 0);
                } else {
                    JOptionPane.showMessageDialog(null,er + " ERROR IN THE DAO_TABLE_PROCED", "DAO  TBL  PROCED", 0); 
                }
            } catch (SQLException ex) {
                Logger.getLogger(TelaMarca.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void salvar() {
        String sql = "insert into tbmarca(marca) values (?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtMarca.getText());

            if((txtMarca.getText().isEmpty())) {
                    if(Idiomas.getPort() == true) {
                        JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                    }
                    lblMarca.setForeground(Color.red);
            } else {
                    adicionado = pst.executeUpdate();
                    
                    if(Idiomas.getPort() == true) {
                        JOptionPane.showMessageDialog(null, "Marca registrado com sucesso !", "Atenção", 1);
                    } else {
                        JOptionPane.showMessageDialog(null, "Code successfully registered !", "Attention", 1);
                    }
                }
                    if((!txtMarca.getText().isEmpty())) {
                        lblMarca.setForeground(Color.black);
                    }
                    
                    if (adicionado > 0) {
                    txtMarca.setText(null);
                    
                    tabelaDAO();
                    }            
        } catch (HeadlessException | SQLException erro) {
            try {
                this.pst.close();
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, erro+" MENSAGEM DE ERRO AO TENTAR SALVAR A MARCA !", "AVISO", 0);
                } else {
                    JOptionPane.showMessageDialog(null, erro+" ERROR MESSAGE WHEN TRYING TO SAVE A CODE !", "WARNING", 0);
                }
            } catch (SQLException ex) {
                Logger.getLogger(TelaMarca.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void update() {
        String sql = "update tbmarca set marca = ? where id = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtMarca.getText());
            pst.setString(2, txtID.getText());

            if((txtMarca.getText().isEmpty())) {
                    if(Idiomas.getPort() == true) {
                        JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                    }
                    lblMarca.setForeground(Color.red);
            } else {
                    adicionado = pst.executeUpdate();
                    
                    if(Idiomas.getPort() == true) {
                        JOptionPane.showMessageDialog(null, "Marca atualizado com sucesso !", "Atenção", 1);
                    } else {
                        JOptionPane.showMessageDialog(null, "Code successfully updated !", "Attention", 1);
                    }
                }
                    if((!txtMarca.getText().isEmpty())) {
                        lblMarca.setForeground(Color.black);
                    }
                    
                    if (adicionado > 0) {
                    txtMarca.setText(null);
                    txtID.setText(null);
                    
                    tabelaDAO();
                    }            
        } catch (HeadlessException | SQLException erro) {
            try {
                this.pst.close();
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, erro+" MENSAGEM DE ERRO AO TENTAR ATUALIZAR A MARCA !", "AVISO", 0);
                } else {
                    JOptionPane.showMessageDialog(null, erro+" ERROR MESSAGE WHEN TRYING TO UPDATE A CODE !", "WARNING", 0);
                }
            } catch (SQLException ex) {
                Logger.getLogger(TelaMarca.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void remover() {
        String sql = "delete from tbmarca where ID = ?";
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
                txtMarca.setText(null);
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
                    JOptionPane.showMessageDialog(null," ERRO AO TENTAR DELETAR...! "+Er, "Atenção",0);
                } else {
                    JOptionPane.showMessageDialog(null,"ERROR WHILE TRYING TO DELETE...! "+Er, "Attention",0);
                }
            } catch (SQLException ex) {
                Logger.getLogger(TelaMarca.class.getName()).log(Level.SEVERE, null, ex);
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
                    JOptionPane.showMessageDialog(null, "Por favor, selecione alguma marca na tabela !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a code from the table !", "Attention", 2);
            }
            } else {
                update();
            }            
        } else if(e.getSource() == btnRemover) {
            if(tabela.getSelectedRow() == -1) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Por favor, selecione alguma marca na tabela !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a code from the table !", "Attention", 2);
                }                
            } else {                
                remover();                
            }
        }
    }

    @Override
    public void configPort() {
        lblID.setText("ID");
        lblMarca.setText("Marca");
        btnSalvar.setText("Salvar");
        btnEditar.setText("Atualizar");
        btnRemover.setText("Remover");
    }

    @Override
    public void configEng() {
        lblID.setText("ID");
        lblMarca.setText("Code");
        btnSalvar.setText("Save");
        btnEditar.setText("Update");
        btnRemover.setText("Remove");
    }
}
