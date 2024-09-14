package telas;

import Abstrato.Config_idiomas;
import Abstrato.Config_remover;
import javax.swing.*;
import java.sql.*;
import dao.ConexaoDAO;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.border.MatteBorder;
import javax.swing.text.MaskFormatter;
import net.proteanit.sql.DbUtils;

/**
 *
 * @author Ramadan ismaeL
 */

public final class TelaCliente extends JInternalFrame implements ActionListener, Config_idiomas, Config_remover {
    private JLabel lbltitulo, lbltituloReg, lblpesquisa, lblfundo, lblNome, lblPhone_1, lblPhone_2, lblLocalizacao, lblLoja, lbldescricao;
    private JTextField txtpesquisa, txtNome, txtPhone_2, txtLocalizacao;
    private JFormattedTextField txtPhone_1;
    private JComboBox txtLoja;
    private JTextArea txtdescricao;
    private JButton btnSalvar, btnCancelar, btnRemover, btnAtualizar_Tb, btnEditar;
    private JTable tabela;
    private JScrollPane scroll;
    private Connection conexao = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private DefaultTableModel model;
    private int deleteItem, adicionado;
    private TelaEdd_Cliente telaECliente = null;
    
    public TelaCliente() throws SQLException {
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
        lbltituloReg = new JLabel();
        lblpesquisa = new JLabel();
        lblfundo = new JLabel();
        lblNome = new JLabel();
        lblPhone_1 = new JLabel();
        lblPhone_2 = new JLabel();
        lblLocalizacao = new JLabel();
        lblLoja = new JLabel();
        lbldescricao = new JLabel();
        
        txtpesquisa = new JTextField();
        txtNome = new JTextField();
        
        try {
            MaskFormatter phoneMask = new MaskFormatter("(+258) ## ### ####");
            txtPhone_1 = new JFormattedTextField(phoneMask);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        
        txtPhone_2 = new JTextField();
        txtLocalizacao = new JTextField();
        txtdescricao = new JTextArea();
        
        if(Idiomas.getPort() == true) {
            String[] loja = {"--", "Loja 1", "Loja 2", "Loja 3", "Loja Jardim"};
            txtLoja = new JComboBox(loja);
        } else {
            String[] loja = {"--", "Shop 1", "Shop 2", "Shop 3", "Jardim Shop"};
            txtLoja = new JComboBox(loja);
        }
        
        btnSalvar = new JButton();
        btnCancelar = new JButton();
        btnRemover = new JButton();
        btnAtualizar_Tb = new JButton();
        btnEditar = new JButton();
        
        tabela = new JTable();
        
        scroll = new JScrollPane();
        
        lbltitulo.setFont(new Font("Times New Roman", 3, 21));        
        lbltitulo.setForeground(new Color(0, 191, 255));
        lbltitulo.setBounds(5, 0, 200, 30);
        
        lblpesquisa.setIcon(new ImageIcon(this.getClass().getResource("/icones/lupa.png")));
        lblpesquisa.setBounds(405, 90, 28, 28);
        
        txtpesquisa.setText(null);
        txtpesquisa.setFont(new Font("Segoe UI", 0, 15));
        txtpesquisa.setForeground(Color.BLACK);
        txtpesquisa.setBounds(5, 90, 400, 28);
        
        if(Idiomas.getPort() == true) {
            String[] nome = {"ID", "NOME", "PHONE_1", "PHONE_2", "Localização", "LOJA", "DESCRIÇÃO", "DATA & HORA"};
            String dados[][] = {};        
            tabela = new JTable(dados, nome) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        } else {
            String[] nome = {"ID", "NAME", "PHONE_1", "PHONE_2", "Location", "SHOPS", "DESCRIPTION", "DATE & TIME"};
            String dados[][] = {};        
            tabela = new JTable(dados, nome) {
            @Override
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false;
            }
        };
        }       
               
        scroll.setViewportView(tabela);
        scroll.setBounds(5, 122, 1303, 250);
        
        lbltituloReg.setFont(new Font("Times New Roman", 3, 21));
        lbltituloReg.setForeground(new Color(0, 100, 0));
        lbltituloReg.setBounds(5, 500, 500, 30);
        
        lblfundo.setIcon(new ImageIcon(this.getClass().getResource("/icones/light-grey.jpg")));
        lblfundo.setBounds(0, 470, 953, 333);
        
        lblNome.setFont(new Font("Times New Roman", 1, 16));
        lblNome.setForeground(new Color(0, 128, 0));
        lblNome.setBounds(10, 600, 110, 28);
        
        txtNome.setText(null);
        txtNome.setFont(new Font("Times New Roman", 0, 15));
        txtNome.setForeground(Color.BLACK);
        txtNome.setBounds(120, 600, 392, 28);
        
        lblPhone_1.setFont(new Font("Times New Roman", 1, 16));
        lblPhone_1.setForeground(new Color(0, 128, 0));
        lblPhone_1.setBounds(10, 660, 100, 28);
        
        txtPhone_1.setFont(new Font("Times New Roman", 0, 15));
        txtPhone_1.setForeground(Color.BLACK);
        txtPhone_1.setBounds(120, 660, 140, 28);
        
        lblPhone_2.setFont(new Font("Times New Roman", 1, 16));
        lblPhone_2.setForeground(new Color(0, 128, 0));
        lblPhone_2.setBounds(10, 690, 100, 28);
        
        txtPhone_2.setText(null);
        txtPhone_2.setFont(new Font("Times New Roman", 0, 15));
        txtPhone_2.setForeground(Color.BLACK);
        txtPhone_2.setBounds(120, 690, 140, 28);
                        
        txtLocalizacao.setText(null);
        txtLocalizacao.setFont(new Font("Times New Roman", 0, 15));
        txtLocalizacao.setForeground(Color.BLACK);
        txtLocalizacao.setBounds(120, 630, 392, 28);
        
        lblLocalizacao.setFont(new Font("Times New Roman", 1, 16));
        lblLocalizacao.setForeground(new Color(0, 128, 0));
        lblLocalizacao.setBounds(10, 630, 100, 28);
        
        lblLoja.setFont(new Font("Times New Roman", 1, 16));
        lblLoja.setForeground(new Color(0, 128, 0));
        lblLoja.setBounds(290, 660, 80, 28);
        
        txtLoja.setFont(new Font("Times New Roman", 0, 15));
        txtLoja.setForeground(Color.BLACK);
        txtLoja.setBounds(370, 660, 140, 28);
                
        lbldescricao.setBounds(550, 600, 110, 28);
                
        txtdescricao.setBounds(550, 630, 392, 162);
        txtdescricao.setBorder(new MatteBorder(1, 1, 1, 1, Color.gray));
        txtdescricao.setLineWrap(true);
        
        btnSalvar.setFont(new Font("Agency FB", 1, 20));
        btnSalvar.setIcon(new ImageIcon(this.getClass().getResource("/icones/adicionar.png")));
        btnSalvar.setForeground(new Color(0, 100, 0));
        btnSalvar.setBounds(10, 763, 150, 30);
        
        btnCancelar.setFont(new Font("Agency FB", 1, 20));
        btnCancelar.setIcon(new ImageIcon(this.getClass().getResource("/icones/cancelar.png")));
        btnCancelar.setForeground(new Color(0, 100, 0));
        btnCancelar.setBounds(170, 763, 150, 30);
        
        btnAtualizar_Tb.setIcon(new ImageIcon(this.getClass().getResource("/icones/atualizar.png")));
        btnAtualizar_Tb.setBounds(1268, 90, 50, 27);
        btnAtualizar_Tb.setOpaque(false); //Torna o botão visível ou transparente
        btnAtualizar_Tb.setBackground(new Color(135, 206, 250));
        btnAtualizar_Tb.setBorder(new MatteBorder(0, 0, 0, 0, Color.WHITE));
        
        btnRemover.setFont(new Font("Agency FB", 1, 20));
        btnRemover.setIcon(new ImageIcon(this.getClass().getResource("/icones/remover.png")));
        btnRemover.setForeground(new Color(0, 100, 0));
        btnRemover.setBounds(798, 390, 250, 30);
        
        btnEditar.setFont(new Font("Agency FB", 1, 20));
        btnEditar.setIcon(new ImageIcon(this.getClass().getResource("/icones/editar.png")));
        btnEditar.setForeground(new Color(0, 100, 0));
        btnEditar.setBounds(1057, 390, 250, 30);
                
        configTools();
        
        getContentPane().add(lbltituloReg);
        getContentPane().add(lbltituloReg);
        getContentPane().add(lblNome);
        getContentPane().add(txtNome);
        getContentPane().add(lblLocalizacao);
        getContentPane().add(txtLocalizacao);
        getContentPane().add(lblPhone_1);
        getContentPane().add(txtPhone_1);
        getContentPane().add(lblPhone_2);
        getContentPane().add(txtPhone_2);
        getContentPane().add(lblLoja);
        getContentPane().add(txtLoja);
        getContentPane().add(lbldescricao);
        getContentPane().add(txtdescricao);
        getContentPane().add(btnSalvar);
        getContentPane().add(btnCancelar);
        getContentPane().add(lblfundo);
        getContentPane().add(lbltitulo);
        getContentPane().add(lblpesquisa);
        getContentPane().add(txtpesquisa);
        getContentPane().add(scroll);
        getContentPane().add(btnRemover);
        getContentPane().add(btnAtualizar_Tb);
        getContentPane().add(btnEditar);
    }
    
    private void configTools() {
        btnSalvar.addActionListener(this);
        btnCancelar.addActionListener(this);
        btnRemover.addActionListener(this);
        btnAtualizar_Tb.addActionListener(this);
        btnEditar.addActionListener(this);
                
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
        } else if(evt.getSource() == btnSalvar) {
            salvar();
        } else if(evt.getSource() == btnCancelar) {
            txtNome.setText(null);
            txtPhone_1.setText(null);
            txtPhone_2.setText(null);
            txtLocalizacao.setText(null);
            txtLoja.setSelectedItem("--");
        } else if(evt.getSource() == btnAtualizar_Tb) {
            tabelaDAO();
            if(Idiomas.getPort() == true) {
                JOptionPane.showMessageDialog(null, "Atualizado !", "Atenção", 1);
            } else {
                JOptionPane.showMessageDialog(null, "Updated !", "Attention", 1);
            }
        } else if(evt.getSource() == btnEditar) {
            editar();
        }
    }
    
    private void tabelaDAO() {
        tabelaDAOProced();
        String sql = "select * from tbcliente";
        
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
            
            tabela.setModel(DbUtils.resultSetToTableModel(rs));
            
            tabela.getColumnModel().getColumn(0).setPreferredWidth(1);
            tabela.getColumnModel().getColumn(1).setPreferredWidth(90);
            tabela.getColumnModel().getColumn(2).setPreferredWidth(100);
            tabela.getColumnModel().getColumn(3).setPreferredWidth(100);
            tabela.getColumnModel().getColumn(4).setPreferredWidth(100);
            tabela.getColumnModel().getColumn(5).setPreferredWidth(8);
            tabela.getColumnModel().getColumn(6).setPreferredWidth(500);
            tabela.getColumnModel().getColumn(7).setPreferredWidth(100);
            
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
                col.setHeaderValue("LOCALIZAÇÃO");
                col = columnModel.getColumn(5);
                col.setHeaderValue("LOJA");
                col = columnModel.getColumn(6);
                col.setHeaderValue("DESCRIÇÃO");
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
                col.setHeaderValue("LOCATION");
                col = columnModel.getColumn(5);
                col.setHeaderValue("SHOPS");
                col = columnModel.getColumn(6);
                col.setHeaderValue("DESCRIPTION");
                col = columnModel.getColumn(7);
                col.setHeaderValue("DATE & TIME");
            }
            
            tabela.getTableHeader().repaint();
        } catch(SQLException er) {
            try {
                this.pst.close();
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null,er + " ERRO NA TABELA_DAO - TelaCliente", "TBL   DAO", 0);
                } else {
                    JOptionPane.showMessageDialog(null,er + " ERROR IN THE DAO_TABLE - TelaCliente", "TBL   DAO", 0);            
                }
            } catch (SQLException ex) {
                Logger.getLogger(TelaCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void tabelaDAOProced() {
        String sql = "call Proced_Organizartbcliente()";
        try {
            pst = conexao.prepareStatement(sql);
            rs = pst.executeQuery();
        } catch(SQLException er) {
            if(Idiomas.getPort() == true) {
                JOptionPane.showMessageDialog(null,er + " ERRO NO PROCED_TABELA_DAO - TelaCliente", "PROCED  TBL  DAO", 0);
            } else {
                JOptionPane.showMessageDialog(null,er + " ERROR IN DAO_TABLE_PROCED - TelaCliente", "DAO  TBL  PROCED", 0);
            } 
        }
    }
    
    private void pesquisar() {
        String sql = "select * from tbcliente where NOME like ? || PHONE_1 like ? || PHONE_2 like ? || LOJA like ?";
        
        try{
            pst = conexao.prepareStatement(sql);

            pst.setString(1, "%" + txtpesquisa.getText() + "%");
            pst.setString(2, "%" + txtpesquisa.getText() + "%");
            pst.setString(3, "%" + txtpesquisa.getText() + "%");
            pst.setString(4, "%" + txtpesquisa.getText() + "%");

            rs = pst.executeQuery();
            
            tabela.setModel(DbUtils.resultSetToTableModel(rs));
            
            tabela.getColumnModel().getColumn(0).setPreferredWidth(1);
            tabela.getColumnModel().getColumn(1).setPreferredWidth(90);
            tabela.getColumnModel().getColumn(2).setPreferredWidth(100);
            tabela.getColumnModel().getColumn(3).setPreferredWidth(100);
            tabela.getColumnModel().getColumn(4).setPreferredWidth(100);
            tabela.getColumnModel().getColumn(5).setPreferredWidth(8);
            tabela.getColumnModel().getColumn(6).setPreferredWidth(500);
            tabela.getColumnModel().getColumn(7).setPreferredWidth(100);
            
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
                col.setHeaderValue("LOCALIZAÇÃO");
                col = columnModel.getColumn(5);
                col.setHeaderValue("LOJA");
                col = columnModel.getColumn(6);
                col.setHeaderValue("DESCRIÇÃO");
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
                col.setHeaderValue("LOCATION");
                col = columnModel.getColumn(5);
                col.setHeaderValue("SHOPS");
                col = columnModel.getColumn(6);
                col.setHeaderValue("DESCRIPTION");
                col = columnModel.getColumn(7);
                col.setHeaderValue("DATE & TIME");
            }
            
            tabela.getTableHeader().repaint();
        } catch(SQLException err) {
            if(Idiomas.getPort() == true) {
                try {
                    this.pst.close();
                    this.rs.close();
                    this.conexao.close();
                    JOptionPane.showMessageDialog(null, "ERRO AO TENTAR PESQUISAR - TelaCliente..! "+err, "Atenção", 0);
                } catch (SQLException ex) {
                    Logger.getLogger(TelaCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    this.pst.close();
                    this.rs.close();
                    this.conexao.close();
                    JOptionPane.showMessageDialog(null, "ERROR WHILE TRYING TO SEARCH - TelaCliente..! "+err, "Attention", 0);
                } catch (SQLException ex) {
                    Logger.getLogger(TelaCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
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
            if(telaECliente == null || !telaECliente.isVisible()) {
                try {
                    telaECliente = new TelaEdd_Cliente();
                } catch (SQLException ex) {
                    Logger.getLogger(TelaCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
                telaECliente.setVisible(true);
            } else {
                telaECliente.toFront();
            }
            int selectedRowIndex = tabela.getSelectedRow();
            model = (DefaultTableModel) tabela.getModel();
            telaECliente.txtID.setText(model.getValueAt(selectedRowIndex, 0).toString());
            telaECliente.txtNome.setText(model.getValueAt(selectedRowIndex, 1).toString());
            telaECliente.txtPhone_1.setText(model.getValueAt(selectedRowIndex, 2).toString());
            telaECliente.txtPhone_2.setText(model.getValueAt(selectedRowIndex, 3).toString());
            telaECliente.txtLocalizacao.setText(model.getValueAt(selectedRowIndex, 4).toString());
            telaECliente.txtLoja.setSelectedItem(model.getValueAt(selectedRowIndex, 5).toString());
            telaECliente.txtdescricao.setText(model.getValueAt(selectedRowIndex, 6).toString());
        }
    }
    
    private void salvar() {
        String sql = "insert into tbcliente(nome, phone_1, phone_2, email, loja, descrição) values (?, ?, ?, ?, ?, ?)";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNome.getText());
            pst.setString(2, txtPhone_1.getText());
            pst.setString(3, txtPhone_2.getText());
            pst.setString(4, txtLocalizacao.getText());
            pst.setString(5, txtLoja.getSelectedItem().toString());
            pst.setString(6, txtdescricao.getText());

            if((txtNome.getText().isEmpty()) & ("--".equals(txtLoja.getSelectedItem().toString().trim()))) {
                    if(Idiomas.getPort() == true) {
                        JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                    }
                    lblNome.setForeground(Color.red);
                    lblLoja.setForeground(Color.red);
            } else if(txtNome.getText().isEmpty()) {
                if(Idiomas.getPort() == true) {
                        JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                    }
                    lblNome.setForeground(Color.red);
            } else if("--".equals(txtLoja.getSelectedItem().toString().trim())) {
                if(Idiomas.getPort() == true) {
                        JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                    }
                    lblLoja.setForeground(Color.red);
                } else {
                    adicionado = pst.executeUpdate();
                    
                    if(Idiomas.getPort() == true) {
                        JOptionPane.showMessageDialog(null, "Cliente registrado com sucesso !", "Atenção", 1);
                        this.conexao.close();
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Client registered successfully !", "Attention", 1);
                    }
                }
                    if((!txtNome.getText().isEmpty()) & (!"--".equals(txtLoja.getSelectedItem().toString().trim()))) {
                        lblNome.setForeground(new Color(0, 128, 0));
                        lblLoja.setForeground(new Color(0, 128, 0));
                    } else if(!txtNome.getText().isEmpty()) {
                        lblNome.setForeground(new Color(0, 128, 0));
            }       else if(!"--".equals(txtLoja.getSelectedItem().toString().trim())) {
                        lblLoja.setForeground(new Color(0, 128, 0));
                    }
                    
                    if (adicionado > 0) {
                    txtNome.setText(null);
                    txtPhone_1.setText(null);
                    txtPhone_2.setText(null);
                    txtLocalizacao.setText(null);
                    txtLoja.setSelectedItem("--");
                    txtdescricao.setText(null);
                    
                    tabelaDAO();
                    }            
        } catch (HeadlessException | SQLException erro) {
            try {
                this.pst.close();
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, erro+" MENSAGEM DE ERRO AO TENTAR SALVAR - TelaCliente !", "AVISO", 0);
                } else {
                    JOptionPane.showMessageDialog(null, erro+" ERROR MESSAGE WHEN TRYING TO SAVE - TelaCliente!", "WARNING", 0);
                }
            } catch (SQLException ex) {
                Logger.getLogger(TelaCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void configPort() {
        lbltitulo.setText("*  C L I E N T E S  *");
        lbltituloReg.setText("* REGISTRO DE CLIENTE *");
        lblNome.setText("Nome Completo");
        lblPhone_1.setText("Telef. 1");
        lblPhone_2.setText("Telef. 2");
        lblLocalizacao.setText("Localização");
        lblLoja.setText("Lojas");  
        lbldescricao.setText("Descrição");
        btnSalvar.setText("Salvar");
        btnCancelar.setText("Cancelar");
        btnRemover.setText("Remover");
        btnEditar.setText("Editar");
        
        lbldescricao.setFont(new Font("Times New Roman", 1, 16));
        lbldescricao.setForeground(new Color(0, 128, 0));
        txtdescricao.setFont(new Font("Times New Roman", 0, 16));
        txtdescricao.setForeground(Color.BLACK);
    }

    @Override
    public void configEng() {
        lbltitulo.setText("*  C L I E N T S  *");
        lbltituloReg.setText("* CLIENT REGISTER *");
        lblNome.setText("Full Name");
        lblPhone_1.setText("Phone_1");
        lblPhone_2.setText("Phone_2");
        lblLocalizacao.setText("Location");
        lblLoja.setText("Shops");  
        lbldescricao.setText("Description");
        btnSalvar.setText("Save");
        btnCancelar.setText("Cancel");
        btnRemover.setText("Remove");
        btnEditar.setText("Edit");
        
        lbldescricao.setFont(new Font("Times New Roman", 1, 16));
        lbldescricao.setForeground(new Color(0, 128, 0));
        txtdescricao.setFont(new Font("Times New Roman", 0, 16));
        txtdescricao.setForeground(Color.BLACK);
    }
    
    @Override
    public void remover1() {
        String sql = "delete from tbcliente where ID = ?";
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
            try {
                this.pst.close();
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null," Messagem de erro no remover1() - TelaCliente...! "+Er, "Atenção",0);
                } else {
                    JOptionPane.showMessageDialog(null," Error Message in remover1() - TelaCliente...! "+Er, "Attention",0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void remover2() {
        int[] sr = tabela.getSelectedRows();
        String sql = "delete from tbcliente where ID in (?, ?)";
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
            try {
                this.pst.close();
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null," Messagem de erro no remover2() - TelaCliente...! "+Er, "Atenção",0);
                } else {
                    JOptionPane.showMessageDialog(null," Error Message in remover2() - TelaCliente...! "+Er, "Attention",0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void remover3() {
        int[] sr = tabela.getSelectedRows();
        String sql = "delete from tbcliente where ID in (?, ?, ?)";
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
            try {
                this.pst.close();
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null," Messagem de erro no remover3() - TelaCliente...! "+Er, "Atenção",0);
                } else {
                    JOptionPane.showMessageDialog(null," Error Message in remover3() - TelaCliente...! "+Er, "Attention",0);
                }         
            } catch (SQLException ex) {
                Logger.getLogger(TelaCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void remover4() {
        int[] sr = tabela.getSelectedRows();
        String sql = "delete from tbcliente where ID in (?, ?, ?, ?)";
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
            try {
                this.pst.close();
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null," Messagem de erro no remover4() - TelaCliente...! "+Er, "Atenção",0);
                } else {
                    JOptionPane.showMessageDialog(null," Error Message in remover4() - TelaCliente...! "+Er, "Attention",0);
                }           
            } catch (SQLException ex) {
                Logger.getLogger(TelaCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void remover5() {
        int[] sr = tabela.getSelectedRows();
        String sql = "delete from tbcliente where ID in (?, ?, ?, ?, ?)";
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
            try {
                this.pst.close();
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null," Messagem de erro no remover5() - TelaCliente...! "+Er, "Atenção",0);
                } else {
                    JOptionPane.showMessageDialog(null," Error Message in remover5() - TelaCliente...! "+Er, "Attention",0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void remover6() {
        int[] sr = tabela.getSelectedRows();
        String sql = "delete from tbcliente where ID in (?, ?, ?, ?, ?, ?)";
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
            try {
                this.pst.close();
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null," Messagem de erro no remover6() - TelaCliente...! "+Er, "Atenção",0);
                } else {
                    JOptionPane.showMessageDialog(null," Error Message in remover6() - TelaCliente...! "+Er, "Attention",0);
                }         
            } catch (SQLException ex) {
                Logger.getLogger(TelaCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void remover7() {
        int[] sr = tabela.getSelectedRows();
        String sql = "delete from tbcliente where ID in (?, ?, ?, ?, ?, ?, ?)";
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
            try {
                this.pst.close();
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null," Messagem de erro no remover7() - TelaCliente...! "+Er, "Atenção",0);
                } else {
                    JOptionPane.showMessageDialog(null," Error Message in remover7() - TelaCliente...! "+Er, "Attention",0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void remover8() {
        int[] sr = tabela.getSelectedRows();
        String sql = "delete from tbcliente where ID in (?, ?, ?, ?, ?, ?, ?, ?)";
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
            try {
                this.pst.close();
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null," Messagem de erro no remover8() - TelaCliente...! "+Er, "Atenção",0);
                } else {
                    JOptionPane.showMessageDialog(null," Error Message in remover8() - TelaCliente...! "+Er, "Attention",0);
                }
            } catch (SQLException ex) {
                Logger.getLogger(TelaCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void remover9() {
        int[] sr = tabela.getSelectedRows();
        String sql = "delete from tbcliente where ID in (?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
            try {
                this.pst.close();
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null," Messagem de erro no remover9() - TelaCliente...! "+Er, "Atenção",0);
                } else {
                    JOptionPane.showMessageDialog(null," Error Message in remover9() - TelaCliente...! "+Er, "Attention",0);
                }
            } catch (SQLException ex) {
                Logger.getLogger(TelaCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void remover10() {
        int[] sr = tabela.getSelectedRows();
        String sql = "delete from tbcliente where ID in (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
            try {
                this.pst.close();
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null," Messagem de erro no remover10() - TelaCliente...! "+Er, "Atenção",0);
                } else {
                    JOptionPane.showMessageDialog(null," Error Message in remover10() - TelaCliente...! "+Er, "Attention",0);
                }
            } catch (SQLException ex) {
                Logger.getLogger(TelaCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
 