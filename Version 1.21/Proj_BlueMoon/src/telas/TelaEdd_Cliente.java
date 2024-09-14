package telas;

import Abstrato.Config_idiomas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import dao.ConexaoDAO;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.border.MatteBorder;
import javax.swing.text.MaskFormatter;

/**
 * @author Ramadan ismaeL
 */

public final class TelaEdd_Cliente extends JFrame implements ActionListener, Config_idiomas {
    protected JLabel lbltitulo, lblfundo, lblNome, lblPhone_1, lblPhone_2, lblLocalizacao, lblLoja, lblID, txtID, lbldescricao;
    protected JTextField txtNome, txtPhone_2, txtLocalizacao;
    protected JFormattedTextField txtPhone_1;
    protected JComboBox txtLoja;
    protected JTextArea txtdescricao;
    private JButton btnSalvar, btnCancelar, btnSair;
    private int adicionado;
    private Connection conexao = null;
    private PreparedStatement pst = null;
    
    public TelaEdd_Cliente() throws SQLException {
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
        setSize(523, 450);        
        setLocationRelativeTo(null);
        setResizable(false);
        setUndecorated(true);        
        setVisible(false);
    }
    
    private void configView() {
        setLayout(null);
        
        lblfundo = new JLabel();
        lbltitulo = new JLabel();
        lblID = new JLabel();
        txtID = new JLabel();
        lblNome = new JLabel();
        lblPhone_1 = new JLabel();
        lblPhone_2 = new JLabel();
        lblLocalizacao = new JLabel();
        lblLoja = new JLabel();
        lbldescricao = new JLabel();
        
        txtNome = new JTextField();
        try {
            MaskFormatter phoneMask = new MaskFormatter("(+258) ## ### ####");
            txtPhone_1 = new JFormattedTextField(phoneMask);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        txtPhone_2 = new JTextField();
        txtLocalizacao = new JTextField();     
            
        if(Idiomas.getPort() == true) {
            String[] loja = {"--", "Loja 1", "Loja 2", "Loja 3", "Loja Jardim"};
            txtLoja = new JComboBox(loja);
        } else {
            String[] loja = {"--", "Shop 1", "Shop 2", "Shop 3", "Jardim Shop"};
            txtLoja = new JComboBox(loja);
        }
        
        txtdescricao = new JTextArea();
        
        btnSalvar = new JButton();
        btnCancelar = new JButton();
        btnSair = new JButton();
        
        lblfundo.setText(null);
        lblfundo.setIcon(new ImageIcon(this.getClass().getResource("/icones/fundoCli.jpg")));
        lblfundo.setBounds(0, 0, 600, 451);
                
        lbltitulo.setFont(new Font("Times New Roman", 3, 21));
        lbltitulo.setForeground(new Color(0, 191, 255));
        lbltitulo.setBounds(10, 0, 300, 30);
        
        lblID.setFont(new Font("Times New Roman", 1, 16));
        lblID.setForeground(Color.white);
        lblID.setBounds(10, 70, 110, 28);
        
        txtID.setText(null);
        txtID.setFont(new Font("Times New Roman", 1, 16));
        txtID.setForeground(Color.red);
        txtID.setBounds(120, 70, 100, 28);
        
        lblNome.setFont(new Font("Times New Roman", 1, 16));
        lblNome.setForeground(Color.white);
        lblNome.setBounds(10, 100, 110, 28);
        
        txtNome.setText(null);
        txtNome.setFont(new Font("Times New Roman", 0, 15));
        txtNome.setForeground(Color.BLACK);
        txtNome.setBounds(120, 100, 392, 28);
        
        lblPhone_1.setFont(new Font("Times New Roman", 1, 16));
        lblPhone_1.setForeground(Color.white);
        lblPhone_1.setBounds(10, 130, 100, 28);
        
        txtPhone_1.setFont(new Font("Times New Roman", 0, 15));
        txtPhone_1.setForeground(Color.BLACK);
        txtPhone_1.setBounds(120, 130, 140, 28);
        
        lblPhone_2.setFont(new Font("Times New Roman", 1, 16));
        lblPhone_2.setForeground(Color.white);
        lblPhone_2.setBounds(10, 160, 100, 28);
        
        txtPhone_2.setText(null);
        txtPhone_2.setFont(new Font("Times New Roman", 0, 15));
        txtPhone_2.setForeground(Color.BLACK);
        txtPhone_2.setBounds(120, 160, 140, 28);
                        
        txtLocalizacao.setText(null);
        txtLocalizacao.setFont(new Font("Times New Roman", 0, 15));
        txtLocalizacao.setForeground(Color.BLACK);
        txtLocalizacao.setBounds(120, 190, 140, 28);
        
        lblLocalizacao.setForeground(Color.white);
        lblLocalizacao.setFont(new Font("Times New Roman", 1, 16));
        lblLocalizacao.setBounds(10, 190, 100, 28);
        
        lblLoja.setFont(new Font("Times New Roman", 1, 16));
        lblLoja.setForeground(Color.white);
        lblLoja.setBounds(290, 190, 80, 28);
        
        txtLoja.setFont(new Font("Times New Roman", 0, 15));
        txtLoja.setForeground(Color.BLACK);
        txtLoja.setBounds(370, 190, 140, 28);
        
        lbldescricao.setBounds(10, 220, 110, 28);
                
        txtdescricao.setBounds(120, 220, 392, 162);
        txtdescricao.setBorder(new MatteBorder(1, 1, 1, 1, Color.gray));
        txtdescricao.setLineWrap(true);
         
        btnSalvar.setFont(new Font("Agency FB", 1, 20));
        btnSalvar.setForeground(new Color(0, 100, 0));
        btnSalvar.setBackground(new Color(176, 224, 230));
        btnSalvar.setBounds(10, 410, 150, 30);
        
        btnCancelar.setFont(new Font("Agency FB", 1, 20));
        btnCancelar.setForeground(new Color(0, 100, 0));
        btnCancelar.setBackground(new Color(176, 224, 230));
        btnCancelar.setBounds(170, 410, 150, 30);
        
        btnSair.setFont(new Font("Agency FB", 1, 20));
        btnSair.setForeground(Color.RED);
        btnSair.setBackground(new Color(176, 224, 230));
        btnSair.setBounds(393, 410, 120, 30);
        
        lblfundo.add(lbltitulo);
        lblfundo.add(lblID);
        lblfundo.add(txtID);
        lblfundo.add(lblNome);
        lblfundo.add(txtNome);
        lblfundo.add(lblPhone_1);
        lblfundo.add(txtPhone_1);
        lblfundo.add(lblPhone_2);
        lblfundo.add(txtPhone_2);
        lblfundo.add(lblLocalizacao);
        lblfundo.add(txtLocalizacao);
        lblfundo.add(lblLoja);
        lblfundo.add(txtLoja);
        lblfundo.add(lbldescricao);
        lblfundo.add(txtdescricao);
        lblfundo.add(btnSalvar);
        lblfundo.add(btnCancelar);
        lblfundo.add(btnSair);
        
        configTools();
        
        getContentPane().add(lblfundo);
    }
    
    private void configTools() {
        btnSalvar.addActionListener(this);
        btnCancelar.addActionListener(this);
        btnSair.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if(evt.getSource() == btnSalvar) {
            update();            
        } else if(evt.getSource() == btnCancelar) {
            txtNome.setText(null);
            txtPhone_2.setText(null);
            txtLocalizacao.setText(null);
            txtLoja.setSelectedItem("--");
        } else if(evt.getSource() == btnSair) {
            if(Idiomas.getPort() == true) {
                int sair = JOptionPane.showConfirmDialog(null, "Têm certeza que deseja sair?", "Atenção", JOptionPane.YES_NO_OPTION);
                if(sair == JOptionPane.YES_OPTION) {
                    try {
                        conexao.close();
                        this.dispose();
                    } catch (SQLException ex) {
                        Logger.getLogger(TelaEdd_Cliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                int sair = JOptionPane.showConfirmDialog(null, "Are you sure you want to leave?", "Attention", JOptionPane.YES_NO_OPTION);
                if(sair == JOptionPane.YES_OPTION) {
                    try {
                        conexao.close();
                        this.dispose();
                    } catch (SQLException ex) {
                        Logger.getLogger(TelaEdd_Cliente.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }
    
    private void update() {
        String sql = "UPDATE tbcliente set nome = ?, phone_1 = ?, phone_2 = ?, email = ?, loja = ?, descrição = ? where id = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNome.getText());
            pst.setString(2, txtPhone_1.getText());
            pst.setString(3, txtPhone_2.getText());
            pst.setString(4, txtLocalizacao.getText());
            pst.setString(5, txtLoja.getSelectedItem().toString());
            pst.setString(6, txtdescricao.getText());
            pst.setString(7, txtID.getText());

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
                } else{
                    adicionado = pst.executeUpdate();
                    
                    if(Idiomas.getPort() == true) {
                        JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso !", "Atenção", 1);
                        conexao.close();
                        this.dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Client updated successfully !", "Attention", 1);
                        conexao.close();
                        this.dispose();
                    }
                }
                    if((!txtNome.getText().isEmpty()) & (!"--".equals(txtLoja.getSelectedItem().toString().trim()))) {
                        lblNome.setForeground(Color.white);
                        lblLoja.setForeground(Color.white);
                    } else if(!txtNome.getText().isEmpty()) {
                        lblNome.setForeground(Color.white);
                    } else if(!"--".equals(txtLoja.getSelectedItem().toString().trim())) {
                        lblLoja.setForeground(Color.white);
                    }
                    
                if (adicionado > 0) {
                    txtNome.setText(null);
                    txtPhone_1.setText(null);
                    txtPhone_2.setText(null);
                    txtLocalizacao.setText(null);
                    txtLoja.setSelectedItem("--");
                    txtID.setText(null);
                    txtdescricao.setText(null);
                }                        
        } catch (HeadlessException | SQLException erro) {
            try {
                this.pst.close();
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, erro+" MENSAGEM DE ERRO AO TENTAR ATUALIZAR - TelaEdd_Cliente !", "AVISO", 0);
                } else {
                    JOptionPane.showMessageDialog(null, erro+" ERROR MESSAGE WHEN TRYING TO UPDATE - TelaEdd_Cliente!", "WARNING", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaEdd_Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void configPort() {
        lbltitulo.setText("ATUALIZAR CLIENTE");        
        lblID.setText("ID");        
        lblNome.setText("Nome Completo");       
        lblPhone_1.setText("Telef. 1");        
        lblPhone_2.setText("Telef. 2");        
        lblLocalizacao.setText("Localização");
        lblLoja.setText("Lojas");
        lbldescricao.setText("Descrição");
        btnSalvar.setText("Salvar");
        btnCancelar.setText("Cancelar");
        btnSair.setText("Sair");  
        
        lbldescricao.setFont(new Font("Times New Roman", 1, 16));
        lbldescricao.setForeground(Color.white);
        txtdescricao.setFont(new Font("Times New Roman", 0, 16));
        txtdescricao.setForeground(Color.BLACK);
    }

    @Override
    public void configEng() {
        lbltitulo.setText("UPDATE CLIENT");
        lblID.setText("ID");
        lblNome.setText("Full Name");
        lblPhone_1.setText("Phone 1");
        lblPhone_2.setText("Phone 2");
        lblLocalizacao.setText("Location");
        lblLoja.setText("Shops");
        lbldescricao.setText("Description");
        btnSalvar.setText("Save");
        btnCancelar.setText("Cancel");
        btnSair.setText("Exit");
        
        lbldescricao.setFont(new Font("Times New Roman", 1, 16));
        lbldescricao.setForeground(Color.white);
        txtdescricao.setFont(new Font("Times New Roman", 0, 16));
        txtdescricao.setForeground(Color.BLACK);
    }
}