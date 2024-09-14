
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
import javax.swing.text.MaskFormatter;

/**
 * @author Ramadan ismaeL
 */

public final class TelaEdd_Usuario extends JFrame implements ActionListener, Config_idiomas {
    protected JLabel lbltitulo, lblfundo, lblNome, lblPhone_1, lblPhone_2, lblUsuario, lblSenha, lblPerfil, lblLoja, lblID, txtID;
    protected JTextField txtNome, txtPhone_2, txtUsuario, txtSenha;
    protected JFormattedTextField txtPhone_1;
    protected JComboBox txtPerfil, txtLoja;
    private JButton btnSalvar, btnCancelar, btnSair;
    private int adicionado;
    private Connection conexao = null;
    private PreparedStatement pst = null;
    
    public TelaEdd_Usuario() throws SQLException {
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
        setSize(523, 310);        
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
        lblUsuario = new JLabel();
        lblSenha = new JLabel();
        lblPerfil = new JLabel();
        lblLoja = new JLabel();
        
        txtNome = new JTextField();
        try {
            MaskFormatter phoneMask = new MaskFormatter("(+258) ## ### ####");
            txtPhone_1 = new JFormattedTextField(phoneMask);
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
        txtPhone_2 = new JTextField();
        txtUsuario = new JTextField();
        txtSenha = new JTextField();       
            
        if(Idiomas.getPort() == true) {
            String[] loja = {"--", "Loja 1", "Loja 2", "Loja 3", "Loja Jardim"}, perfis = {"--", "admin", "user"};
            txtPerfil = new JComboBox(perfis);
            txtLoja = new JComboBox(loja);
        } else {
            String[] loja = {"--", "Shop 1", "Shop 2", "Shop 3", "Jardim Shop"}, perfis = {"--", "admin", "user"};
            txtPerfil = new JComboBox(perfis);
            txtLoja = new JComboBox(loja);
        }
        
        btnSalvar = new JButton();
        btnCancelar = new JButton();
        btnSair = new JButton();
        
        lblfundo.setText(null);
        lblfundo.setIcon(new ImageIcon(this.getClass().getResource("/icones/fundoAdd_UserLight.jpg")));
        lblfundo.setBounds(0, 0, 600, 362);
                
        lbltitulo.setBounds(10, 0, 300, 30);
        
        lblID.setBounds(10, 40, 110, 28);
        
        txtID.setText(null);
        txtID.setFont(new Font("Times New Roman", 1, 16));
        txtID.setForeground(Color.BLACK);
        txtID.setBounds(120, 40, 100, 28);
        
        lblNome.setBounds(10, 70, 110, 28);
        
        txtNome.setText(null);
        txtNome.setFont(new Font("Times New Roman", 0, 15));
        txtNome.setForeground(Color.BLACK);
        txtNome.setBounds(120, 70, 392, 28);
        
        lblPhone_1.setBounds(10, 100, 100, 28);
        
        txtPhone_1.setFont(new Font("Times New Roman", 0, 15));
        txtPhone_1.setForeground(Color.BLACK);
        txtPhone_1.setBounds(120, 100, 140, 28);
        
        lblPhone_2.setBounds(10, 130, 100, 28);
        
        txtPhone_2.setText(null);
        txtPhone_2.setFont(new Font("Times New Roman", 0, 15));
        txtPhone_2.setForeground(Color.BLACK);
        txtPhone_2.setBounds(120, 130, 140, 28);
        
        lblUsuario.setBounds(10, 160, 100, 28);
        
        txtUsuario.setText(null);
        txtUsuario.setFont(new Font("Times New Roman", 0, 15));
        txtUsuario.setForeground(Color.BLACK);
        txtUsuario.setBounds(120, 160, 140, 28);
        
        lblSenha.setFont(new Font("Times New Roman", 1, 16));
        lblSenha.setForeground(Color.BLACK);
        lblSenha.setBounds(290, 160, 80, 28);
        
        txtSenha.setText(null);
        txtSenha.setFont(new Font("Times New Roman", 0, 15));
        txtSenha.setForeground(Color.BLACK);
        txtSenha.setBounds(370, 160, 140, 28);
        
        lblPerfil.setBounds(10, 190, 100, 28);
        
        txtPerfil.setFont(new Font("Times New Roman", 0, 15));
        txtPerfil.setForeground(Color.BLACK);
        txtPerfil.setBounds(120, 190, 140, 28);
        
        lblLoja.setBounds(290, 190, 80, 28);
        
        txtLoja.setFont(new Font("Times New Roman", 0, 15));
        txtLoja.setForeground(Color.BLACK);
        txtLoja.setBounds(370, 190, 140, 28);
                
        btnSalvar.setBackground(new Color(135, 206, 250));
        btnSalvar.setBounds(10, 270, 150, 30);
        
        btnCancelar.setBackground(new Color(135, 206, 250));
        btnCancelar.setBounds(170, 270, 150, 30);
        
        btnSair.setBackground(new Color(135, 206, 250));
        btnSair.setBounds(393, 270, 120, 30);
        
        lblfundo.add(lbltitulo);
        lblfundo.add(lblID);
        lblfundo.add(txtID);
        lblfundo.add(lblNome);
        lblfundo.add(txtNome);
        lblfundo.add(lblPhone_1);
        lblfundo.add(txtPhone_1);
        lblfundo.add(lblPhone_2);
        lblfundo.add(txtPhone_2);
        lblfundo.add(lblUsuario);
        lblfundo.add(txtUsuario);
        lblfundo.add(lblSenha);
        lblfundo.add(txtSenha);
        lblfundo.add(lblPerfil);
        lblfundo.add(txtPerfil);
        lblfundo.add(lblLoja);
        lblfundo.add(txtLoja);
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
            txtUsuario.setText(null);
            txtSenha.setText(null);
            txtPerfil.setSelectedItem("--");
            txtLoja.setSelectedItem("--");
        } else if(evt.getSource() == btnSair) {
            if(Idiomas.getPort() == true) {
                int sair = JOptionPane.showConfirmDialog(null, "Têm certeza que deseja sair?", "Atenção", JOptionPane.YES_NO_OPTION);
                if(sair == JOptionPane.YES_OPTION) {
                    try {
                        this.conexao.close();
                        dispose();
                    } catch (SQLException ex) {
                        Logger.getLogger(TelaEdd_Usuario.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                int sair = JOptionPane.showConfirmDialog(null, "Are you sure you want to leave?", "Attention", JOptionPane.YES_NO_OPTION);
                if(sair == JOptionPane.YES_OPTION) {
                    try {
                        this.conexao.close();
                        dispose();
                    } catch (SQLException ex) {
                        Logger.getLogger(TelaEdd_Usuario.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }
    
    private void update() {
        String sql = "UPDATE tbusuario set nome = ?, phone_1 = ?, phone_2 = ?, usuário = ?, senha = ?, perfil = ?, loja = ? where id = ?";
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, txtNome.getText());
            pst.setString(2, txtPhone_1.getText());
            pst.setString(3, txtPhone_2.getText());
            pst.setString(4, txtUsuario.getText());
            pst.setString(5, txtSenha.getText());
            pst.setString(6, txtPerfil.getSelectedItem().toString());
            pst.setString(7, txtLoja.getSelectedItem().toString());
            pst.setString(8, txtID.getText());

            //VALIDAÇÃO DOS DADOS OBRIGATÓRIOS
            if((txtNome.getText().isEmpty()) & (txtUsuario.getText().isEmpty()) & (txtSenha.getText().isEmpty()) & ("--".equals(txtPerfil.getSelectedItem().toString().trim())) & ("--".equals(txtLoja.getSelectedItem().toString().trim()))) {
                    if(Idiomas.getPort() == true) {
                        JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                    }
                    lblNome.setForeground(Color.red);
                    lblUsuario.setForeground(Color.red);
                    lblSenha.setForeground(Color.red);
                    lblPerfil.setForeground(Color.red);
                    lblLoja.setForeground(Color.red);
                } else if((txtNome.getText().isEmpty()) & (txtUsuario.getText().isEmpty()) & (txtSenha.getText().isEmpty()) & ("--".equals(txtPerfil.getSelectedItem().toString().trim()))) {
                    if(Idiomas.getPort() == true) {
                        JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                    }
                    lblNome.setForeground(Color.red);
                    lblUsuario.setForeground(Color.red);
                    lblSenha.setForeground(Color.red);
                    lblPerfil.setForeground(Color.red);
                } else if((txtNome.getText().isEmpty()) & (txtUsuario.getText().isEmpty()) & (txtSenha.getText().isEmpty()) & ("--".equals(txtLoja.getSelectedItem().toString().trim()))) {
                    if(Idiomas.getPort() == true) {
                        JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                    }
                    lblNome.setForeground(Color.red);
                    lblUsuario.setForeground(Color.red);
                    lblSenha.setForeground(Color.red);
                    lblLoja.setForeground(Color.red);
                } else if((txtNome.getText().isEmpty()) & (txtSenha.getText().isEmpty()) & ("--".equals(txtPerfil.getSelectedItem().toString().trim())) & ("--".equals(txtLoja.getSelectedItem().toString().trim()))) {
                    if(Idiomas.getPort() == true) {
                        JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                    }
                    lblNome.setForeground(Color.red);
                    lblSenha.setForeground(Color.red);
                    lblPerfil.setForeground(Color.red);
                    lblLoja.setForeground(Color.red);
                } else if((txtUsuario.getText().isEmpty()) & (txtSenha.getText().isEmpty()) & ("--".equals(txtPerfil.getSelectedItem().toString().trim())) & ("--".equals(txtLoja.getSelectedItem().toString().trim()))) {
                    if(Idiomas.getPort() == true) {
                        JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                    }
                    lblUsuario.setForeground(Color.red);
                    lblSenha.setForeground(Color.red);
                    lblPerfil.setForeground(Color.red);
                    lblLoja.setForeground(Color.red); 
                } else if((txtNome.getText().isEmpty()) & (txtUsuario.getText().isEmpty()) & ("--".equals(txtPerfil.getSelectedItem().toString().trim())) & ("--".equals(txtLoja.getSelectedItem().toString().trim()))) {
                    if(Idiomas.getPort() == true) {
                        JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                    }
                    lblNome.setForeground(Color.red);
                    lblUsuario.setForeground(Color.red);
                    lblPerfil.setForeground(Color.red);
                    lblLoja.setForeground(Color.red);
                } else if((txtNome.getText().isEmpty()) & (txtUsuario.getText().isEmpty()) & (txtSenha.getText().isEmpty())) {
                    if(Idiomas.getPort() == true) {
                        JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                    }
                    lblNome.setForeground(Color.red);
                    lblUsuario.setForeground(Color.red);
                    lblSenha.setForeground(Color.red); 
                } else if((txtNome.getText().isEmpty()) & (txtUsuario.getText().isEmpty()) & ("--".equals(txtPerfil.getSelectedItem().toString().trim()))) {
                    if(Idiomas.getPort() == true) {
                        JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                    }
                    lblNome.setForeground(Color.red);
                    lblUsuario.setForeground(Color.red);
                    lblPerfil.setForeground(Color.red);
                } else if((txtNome.getText().isEmpty()) & (txtUsuario.getText().isEmpty()) & ("--".equals(txtLoja.getSelectedItem().toString().trim()))) {
                    if(Idiomas.getPort() == true) {
                        JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                    }
                    lblNome.setForeground(Color.red);
                    lblUsuario.setForeground(Color.red);
                    lblLoja.setForeground(Color.red);
                } else if((txtNome.getText().isEmpty()) & (txtSenha.getText().isEmpty()) & ("--".equals(txtPerfil.getSelectedItem().toString().trim()))) {
                    if(Idiomas.getPort() == true) {
                        JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                    }
                    lblNome.setForeground(Color.red);
                    lblSenha.setForeground(Color.red);
                    lblPerfil.setForeground(Color.red);
                } else if((txtNome.getText().isEmpty()) & (txtSenha.getText().isEmpty()) & ("--".equals(txtLoja.getSelectedItem().toString().trim()))) {
                    if(Idiomas.getPort() == true) {
                        JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                    }
                    lblNome.setForeground(Color.red);
                    lblSenha.setForeground(Color.red);
                    lblLoja.setForeground(Color.red);
                } else if((txtNome.getText().isEmpty()) & ("--".equals(txtPerfil.getSelectedItem().toString().trim())) & ("--".equals(txtLoja.getSelectedItem().toString().trim()))) {
                    if(Idiomas.getPort() == true) {
                        JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                    }
                    lblNome.setForeground(Color.red);
                    lblPerfil.setForeground(Color.red);
                    lblLoja.setForeground(Color.red);
                } else if((txtUsuario.getText().isEmpty()) & (txtSenha.getText().isEmpty()) & ("--".equals(txtPerfil.getSelectedItem().toString().trim()))) {
                    if(Idiomas.getPort() == true) {
                        JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                    }
                    lblUsuario.setForeground(Color.red);
                    lblSenha.setForeground(Color.red);
                    lblPerfil.setForeground(Color.red);
                } else if((txtUsuario.getText().isEmpty()) & (txtSenha.getText().isEmpty()) & ("--".equals(txtLoja.getSelectedItem().toString().trim()))) {
                    if(Idiomas.getPort() == true) {
                        JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                    }
                    lblUsuario.setForeground(Color.red);
                    lblSenha.setForeground(Color.red);
                    lblLoja.setForeground(Color.red);
                } else if((txtUsuario.getText().isEmpty()) & ("--".equals(txtPerfil.getSelectedItem().toString().trim())) & ("--".equals(txtLoja.getSelectedItem().toString().trim()))) {
                    if(Idiomas.getPort() == true) {
                        JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                    }
                    lblUsuario.setForeground(Color.red);
                    lblPerfil.setForeground(Color.red);
                    lblLoja.setForeground(Color.red);
                } else if((txtSenha.getText().isEmpty()) & ("--".equals(txtPerfil.getSelectedItem().toString().trim())) & ("--".equals(txtLoja.getSelectedItem().toString().trim()))) {
                    if(Idiomas.getPort() == true) {
                        JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                    }
                    lblSenha.setForeground(Color.red);
                    lblPerfil.setForeground(Color.red);
                    lblLoja.setForeground(Color.red);
                } else if((txtNome.getText().isEmpty()) & (txtUsuario.getText().isEmpty())) {
                    if(Idiomas.getPort() == true) {
                        JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                    }
                    lblNome.setForeground(Color.red);
                    lblUsuario.setForeground(Color.red);
                } else if((txtNome.getText().isEmpty()) & (txtSenha.getText().isEmpty())) {
                    if(Idiomas.getPort() == true) {
                        JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                    }
                    lblNome.setForeground(Color.red);
                    lblSenha.setForeground(Color.red);
                } else if((txtNome.getText().isEmpty()) & ("--".equals(txtPerfil.getSelectedItem().toString().trim()))) {
                    if(Idiomas.getPort() == true) {
                        JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                    }
                    lblNome.setForeground(Color.red);
                    lblPerfil.setForeground(Color.red);
                } else if((txtNome.getText().isEmpty()) & ("--".equals(txtLoja.getSelectedItem().toString().trim()))) {
                    if(Idiomas.getPort() == true) {
                        JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                    }
                    lblNome.setForeground(Color.red);
                    lblLoja.setForeground(Color.red);
                } else if((txtUsuario.getText().isEmpty()) & ("--".equals(txtPerfil.getSelectedItem().toString().trim()))) {
                    if(Idiomas.getPort() == true) {
                        JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                    }
                    lblUsuario.setForeground(Color.red);
                    lblPerfil.setForeground(Color.red);
                } else if((txtUsuario.getText().isEmpty()) & ("--".equals(txtLoja.getSelectedItem().toString().trim()))) {
                    if(Idiomas.getPort() == true) {
                        JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                    }
                    lblUsuario.setForeground(Color.red);
                    lblLoja.setForeground(Color.red);
                } else if((txtUsuario.getText().isEmpty()) & (txtSenha.getText().isEmpty())) {
                    if(Idiomas.getPort() == true) {
                        JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                    }
                    lblUsuario.setForeground(Color.red);
                    lblSenha.setForeground(Color.red);
                } else if((txtSenha.getText().isEmpty()) & ("--".equals(txtLoja.getSelectedItem().toString().trim()))) {
                    if(Idiomas.getPort() == true) {
                        JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                    }
                    lblSenha.setForeground(Color.red);
                    lblLoja.setForeground(Color.red);
                } else if((txtSenha.getText().isEmpty()) & ("--".equals(txtPerfil.getSelectedItem().toString().trim()))) {
                    if(Idiomas.getPort() == true) {
                        JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                    }
                    lblSenha.setForeground(Color.red);
                    lblPerfil.setForeground(Color.red);
                } else if(("--".equals(txtPerfil.getSelectedItem().toString().trim())) & ("--".equals(txtLoja.getSelectedItem().toString().trim()))) {
                    if(Idiomas.getPort() == true) {
                        JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                    }
                    lblPerfil.setForeground(Color.red);
                    lblLoja.setForeground(Color.red);
                } else if(txtNome.getText().isEmpty()) {
                    if(Idiomas.getPort() == true) {
                        JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                    }
                    lblNome.setForeground(Color.red);
                } else if(txtUsuario.getText().isEmpty()) {
                    if(Idiomas.getPort() == true) {
                        JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                    }
                    lblUsuario.setForeground(Color.red);
                } else if(txtSenha.getText().isEmpty()) {
                    if(Idiomas.getPort() == true) {
                        JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                    }
                    lblSenha.setForeground(Color.red);
                } else if("--".equals(txtPerfil.getSelectedItem().toString().trim())) {
                    if(Idiomas.getPort() == true) {
                        JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                    } else {
                        JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                    }
                    lblPerfil.setForeground(Color.red);
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
                        JOptionPane.showMessageDialog(null, "Usuário atualizado com sucesso !", "Atenção", 1);
                    } else {
                        JOptionPane.showMessageDialog(null, "User updated successfully !", "Attention", 1);
                    }
                    this.dispose();
                }
                
                if((!txtNome.getText().isEmpty()) & (!txtUsuario.getText().isEmpty()) & (!txtSenha.getText().isEmpty()) & (!"--".equals(txtPerfil.getSelectedItem().toString().trim())) & (!"--".equals(txtLoja.getSelectedItem().toString().trim()))) {
                    lblNome.setForeground(Color.black);
                    lblUsuario.setForeground(Color.black);
                    lblSenha.setForeground(Color.black);
                    lblPerfil.setForeground(Color.black);
                    lblLoja.setForeground(Color.black);
                } else if((!txtNome.getText().isEmpty()) & (!txtUsuario.getText().isEmpty()) & (!txtSenha.getText().isEmpty()) & (!"--".equals(txtPerfil.getSelectedItem().toString().trim()))) {
                    lblNome.setForeground(Color.black);
                    lblUsuario.setForeground(Color.black);
                    lblSenha.setForeground(Color.black);
                    lblPerfil.setForeground(Color.black);
                } else if((!txtNome.getText().isEmpty()) & (!txtUsuario.getText().isEmpty()) & (!txtSenha.getText().isEmpty()) & (!"--".equals(txtLoja.getSelectedItem().toString().trim()))) {
                    lblNome.setForeground(Color.black);
                    lblUsuario.setForeground(Color.black);
                    lblSenha.setForeground(Color.black);
                    lblLoja.setForeground(Color.black);
                } else if((!txtNome.getText().isEmpty()) & (!txtSenha.getText().isEmpty()) & (!"--".equals(txtPerfil.getSelectedItem().toString().trim())) & (!"--".equals(txtLoja.getSelectedItem().toString().trim()))) {
                    lblNome.setForeground(Color.black);
                    lblSenha.setForeground(Color.black);
                    lblPerfil.setForeground(Color.black);
                    lblLoja.setForeground(Color.black);
                } else if((!txtUsuario.getText().isEmpty()) & (!txtSenha.getText().isEmpty()) & (!"--".equals(txtPerfil.getSelectedItem().toString().trim())) & (!"--".equals(txtLoja.getSelectedItem().toString().trim()))) {
                    lblUsuario.setForeground(Color.black);
                    lblSenha.setForeground(Color.black);
                    lblPerfil.setForeground(Color.black);
                    lblLoja.setForeground(Color.black); 
                } else if((!txtNome.getText().isEmpty()) & (!txtUsuario.getText().isEmpty()) & (!"--".equals(txtPerfil.getSelectedItem().toString().trim())) & (!"--".equals(txtLoja.getSelectedItem().toString().trim()))) {
                    lblNome.setForeground(Color.black);
                    lblUsuario.setForeground(Color.black);
                    lblPerfil.setForeground(Color.black);
                    lblLoja.setForeground(Color.black);
                } else if((!txtNome.getText().isEmpty()) & (!txtUsuario.getText().isEmpty()) & (!txtSenha.getText().isEmpty())) {
                    lblNome.setForeground(Color.black);
                    lblUsuario.setForeground(Color.black);
                    lblSenha.setForeground(Color.black); 
                } else if((!txtNome.getText().isEmpty()) & (!txtUsuario.getText().isEmpty()) & (!"--".equals(txtPerfil.getSelectedItem().toString().trim()))) {
                    lblNome.setForeground(Color.black);
                    lblUsuario.setForeground(Color.black);
                    lblPerfil.setForeground(Color.black);
                } else if((!txtNome.getText().isEmpty()) & (!txtUsuario.getText().isEmpty()) & (!"--".equals(txtLoja.getSelectedItem().toString().trim()))) {
                    lblNome.setForeground(Color.black);
                    lblUsuario.setForeground(Color.black);
                    lblLoja.setForeground(Color.black);
                } else if((!txtNome.getText().isEmpty()) & (!txtSenha.getText().isEmpty()) & (!"--".equals(txtPerfil.getSelectedItem().toString().trim()))) {
                    lblNome.setForeground(Color.black);
                    lblSenha.setForeground(Color.black);
                    lblPerfil.setForeground(Color.black);
                } else if((!txtNome.getText().isEmpty()) & (!txtSenha.getText().isEmpty()) & (!"--".equals(txtLoja.getSelectedItem().toString().trim()))) {
                    lblNome.setForeground(Color.black);
                    lblSenha.setForeground(Color.black);
                    lblLoja.setForeground(Color.black);
                } else if((!txtNome.getText().isEmpty()) & (!"--".equals(txtPerfil.getSelectedItem().toString().trim())) & (!"--".equals(txtLoja.getSelectedItem().toString().trim()))) {
                    lblNome.setForeground(Color.black);
                    lblPerfil.setForeground(Color.black);
                    lblLoja.setForeground(Color.black);
                } else if((!txtUsuario.getText().isEmpty()) & (!txtSenha.getText().isEmpty()) & (!"--".equals(txtPerfil.getSelectedItem().toString().trim()))) {
                    lblUsuario.setForeground(Color.black);
                    lblSenha.setForeground(Color.black);
                    lblPerfil.setForeground(Color.black);
                } else if((!txtUsuario.getText().isEmpty()) & (!txtSenha.getText().isEmpty()) & (!"--".equals(txtLoja.getSelectedItem().toString().trim()))) {
                    lblUsuario.setForeground(Color.black);
                    lblSenha.setForeground(Color.black);
                    lblLoja.setForeground(Color.black);
                } else if((!txtUsuario.getText().isEmpty()) & (!"--".equals(txtPerfil.getSelectedItem().toString().trim())) & (!"--".equals(txtLoja.getSelectedItem().toString().trim()))) {
                    lblUsuario.setForeground(Color.black);
                    lblPerfil.setForeground(Color.black);
                    lblLoja.setForeground(Color.black);
                } else if((!txtSenha.getText().isEmpty()) & (!"--".equals(txtPerfil.getSelectedItem().toString().trim())) & (!"--".equals(txtLoja.getSelectedItem().toString().trim()))) {
                    lblSenha.setForeground(Color.black);
                    lblPerfil.setForeground(Color.black);
                    lblLoja.setForeground(Color.black);
                } else if((!txtNome.getText().isEmpty()) & (!txtUsuario.getText().isEmpty())) {
                    lblNome.setForeground(Color.black);
                    lblUsuario.setForeground(Color.black);
                } else if((!txtNome.getText().isEmpty()) & (!txtSenha.getText().isEmpty())) {
                    lblNome.setForeground(Color.black);
                    lblSenha.setForeground(Color.black);
                } else if((!txtNome.getText().isEmpty()) & (!"--".equals(txtPerfil.getSelectedItem().toString().trim()))) {
                    lblNome.setForeground(Color.black);
                    lblPerfil.setForeground(Color.black);
                } else if((!txtNome.getText().isEmpty()) & (!"--".equals(txtLoja.getSelectedItem().toString().trim()))) {
                    lblNome.setForeground(Color.black);
                    lblLoja.setForeground(Color.black);
                } else if((!txtUsuario.getText().isEmpty()) & (!"--".equals(txtPerfil.getSelectedItem().toString().trim()))) {
                    lblUsuario.setForeground(Color.black);
                    lblPerfil.setForeground(Color.black);
                } else if((!txtUsuario.getText().isEmpty()) & (!"--".equals(txtLoja.getSelectedItem().toString().trim()))) {
                    lblUsuario.setForeground(Color.black);
                    lblLoja.setForeground(Color.black);
                } else if((!txtUsuario.getText().isEmpty()) & (!txtSenha.getText().isEmpty())) {
                    lblUsuario.setForeground(Color.black);
                    lblSenha.setForeground(Color.black);
                } else if((!txtSenha.getText().isEmpty()) & (!"--".equals(txtLoja.getSelectedItem().toString().trim()))) {
                    lblSenha.setForeground(Color.black);
                    lblLoja.setForeground(Color.black);
                } else if((!txtSenha.getText().isEmpty()) & (!"--".equals(txtPerfil.getSelectedItem().toString().trim()))) {
                    lblSenha.setForeground(Color.black);
                    lblPerfil.setForeground(Color.black);
                } else if((!"--".equals(txtPerfil.getSelectedItem().toString().trim())) & (!"--".equals(txtLoja.getSelectedItem().toString().trim()))) {
                    lblPerfil.setForeground(Color.black);
                    lblLoja.setForeground(Color.black);
                } else if(!txtNome.getText().isEmpty()) {
                    lblNome.setForeground(Color.black);
                } else if(!txtUsuario.getText().isEmpty()) {
                    lblUsuario.setForeground(Color.black);
                } else if(!txtSenha.getText().isEmpty()) {
                    lblSenha.setForeground(Color.black);
                } else if(!"--".equals(txtPerfil.getSelectedItem().toString().trim())) {
                    lblPerfil.setForeground(Color.black);
                } else if(!"--".equals(txtLoja.getSelectedItem().toString().trim())) {
                    lblLoja.setForeground(Color.black);
                }
                
                if (adicionado > 0) {
                    txtNome.setText(null);
                    txtPhone_1.setText(null);
                    txtPhone_2.setText(null);
                    txtUsuario.setText(null);
                    txtSenha.setText(null);
                    txtPerfil.setSelectedItem("--");
                    txtLoja.setSelectedItem("--");
                    txtID.setText(null);                    
                }
        } catch (HeadlessException | SQLException erro) {
            try {
                this.pst.close();
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, erro+" MENSAGEM DE ERRO AO TENTAR ATUALIZAR - TelaEdd_Usuario !", "AVISO", 0);
                } else {
                    JOptionPane.showMessageDialog(null, erro+" ERROR MESSAGE WHEN TRYING TO UPDATE - TelaEdd_Usuario !", "WARNING", 0);
                }
            } catch (SQLException ex) {
                Logger.getLogger(TelaEdd_Usuario.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void configPort() {
        lbltitulo.setText("ATUALIZAR USUÁRIO");
        lbltitulo.setFont(new Font("Times New Roman", 3, 21));
        lbltitulo.setForeground(new Color(0, 191, 255));
        lblID.setText("ID");
        lblID.setFont(new Font("Times New Roman", 1, 16));
        lblID.setForeground(Color.BLACK);
        lblNome.setText("Nome Completo");
        lblNome.setFont(new Font("Times New Roman", 1, 16));
        lblNome.setForeground(Color.BLACK);
        lblPhone_1.setText("Telefone 1");
        lblPhone_1.setFont(new Font("Times New Roman", 1, 16));
        lblPhone_1.setForeground(Color.BLACK);
        lblPhone_2.setText("Telefone 2");
        lblPhone_2.setFont(new Font("Times New Roman", 1, 16));
        lblPhone_2.setForeground(Color.BLACK);
        lblUsuario.setText("Usuário");
        lblUsuario.setFont(new Font("Times New Roman", 1, 16));
        lblUsuario.setForeground(Color.BLACK);
        lblSenha.setText("Senha");
        lblPerfil.setText("Perfil");
        lblPerfil.setFont(new Font("Times New Roman", 1, 16));
        lblPerfil.setForeground(Color.BLACK);
        lblLoja.setText("Lojas");
        lblLoja.setFont(new Font("Times New Roman", 1, 16));
        lblLoja.setForeground(Color.BLACK);
        btnSalvar.setText("Salvar");
        btnSalvar.setFont(new Font("Agency FB", 1, 20));
        btnSalvar.setForeground(Color.BLACK);
        btnCancelar.setText("Cancelar");
        btnCancelar.setFont(new Font("Agency FB", 1, 20));
        btnCancelar.setForeground(Color.BLACK);
        btnSair.setText("Sair");
        btnSair.setFont(new Font("Agency FB", 1, 20));
        btnSair.setForeground(Color.RED);
    }

    @Override
    public void configEng() {
        lbltitulo.setText("UPDATE USER");
        lbltitulo.setFont(new Font("Times New Roman", 3, 21));
        lbltitulo.setForeground(new Color(0, 191, 255)); 
        lblID.setText("ID");
        lblID.setFont(new Font("Times New Roman", 1, 16));
        lblID.setForeground(Color.BLACK);
        lblNome.setText("Full Name");
        lblNome.setFont(new Font("Times New Roman", 1, 16));
        lblNome.setForeground(Color.BLACK);
        lblPhone_1.setText("Phone 1");
        lblPhone_1.setFont(new Font("Times New Roman", 1, 16));
        lblPhone_1.setForeground(Color.BLACK);
        lblPhone_2.setText("Phone 2");
        lblPhone_2.setFont(new Font("Times New Roman", 1, 16));
        lblPhone_2.setForeground(Color.BLACK);
        lblUsuario.setText("Username");
        lblUsuario.setFont(new Font("Times New Roman", 1, 16));
        lblUsuario.setForeground(Color.BLACK);
        lblSenha.setText("Password");
        lblSenha.setFont(new Font("Times New Roman", 1, 16));
        lblSenha.setForeground(Color.BLACK);
        lblPerfil.setText("Profile");
        lblPerfil.setFont(new Font("Times New Roman", 1, 16));
        lblPerfil.setForeground(Color.BLACK);
        lblLoja.setText("Shops");
        lblLoja.setFont(new Font("Times New Roman", 1, 16));
        lblLoja.setForeground(Color.BLACK);
        btnSalvar.setText("Save");
        btnSalvar.setFont(new Font("Agency FB", 1, 20));
        btnSalvar.setForeground(Color.BLACK);
        btnCancelar.setText("Cancel");
        btnCancelar.setFont(new Font("Agency FB", 1, 20));
        btnCancelar.setForeground(Color.BLACK);
        btnSair.setText("Exit");
        btnSair.setFont(new Font("Agency FB", 1, 20));
        btnSair.setForeground(Color.RED);
    }
}