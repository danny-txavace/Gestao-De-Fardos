package telas;

import controller.controller_Usuario_Login;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import dao.ConexaoDAO;
import java.util.logging.*;

/**
 * @author Ramadan ismaeL
 */

public class TelaLogin extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;
    private static final int MAX_LOGIN_ATTEMPTS = 7;
    private JLabel lbl_login, lblbluemoon, lblStatus, fundo, imagemUser, imagemSenha, lblUser, lblSenha;
    private JTextField txtUser;
    private JPasswordField txtSenha;
    private JButton btnEntrar, btnSair;
    private Connection conexao = null;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    private TelaPrincipal telaP = null;
    
    public TelaLogin() throws SQLException {
        Janela();
        configView();
        
        conexao = ConexaoDAO.conector();
        if(conexao != null) {
            lblStatus.setIcon(new ImageIcon(this.getClass().getResource("/icones/login/dbOK.png")));
        } else {
            lblStatus.setIcon(new ImageIcon(this.getClass().getResource("/icones/login/dbERROR.png")));
        }
        
        if(Idiomas.getPort() == true) {
            configPort();
        } else {
            configEng();
        }
        
        setVisible(true);
    }
    
    private void Janela() {
        setTitle(" *  LOGIN  * ");
        setSize(1000, 400);
        setLocationRelativeTo(null);
        setUndecorated(true);
    }
    
    private void configView() {
        lblbluemoon = new JLabel();
        lbl_login = new JLabel();
        lblStatus = new JLabel();
        fundo = new JLabel();
        imagemUser = new JLabel();
        imagemSenha = new JLabel();
        lblUser = new JLabel();
        lblSenha = new JLabel();
        txtUser = new JTextField();
        txtSenha = new JPasswordField();
        btnEntrar = new JButton();
        btnSair = new JButton();
        
        fundo.setIcon(new ImageIcon(this.getClass().getResource("/icones/login/fundo.jpg")));
              
        lbl_login.setText("LOGIN");
        lbl_login.setFont(new Font("Times New Roman", 1, 36));
        lbl_login.setForeground(new Color(0, 191, 255));
        lbl_login.setBounds(760, 48, 150, 45);
        
        lblbluemoon.setIcon(new ImageIcon(this.getClass().getResource("/icones/login/bluemoon.jpeg")));
        lblbluemoon.setBounds(0, 0, 650, 400);        
                
        lblStatus.setBounds(0, 0, 200, 33);
        lblbluemoon.add(lblStatus);
                
        imagemUser.setIcon(new ImageIcon(this.getClass().getResource("/icones/login/entrar-avatar24.png")));
        imagemUser.setBounds(660, 145, 25, 25);                
        
        lblUser.setFont(new Font("Times New Roman", 0, 20));
        lblUser.setForeground(Color.white);
        lblUser.setBounds(685, 145, 100, 28);
        
        txtUser.setFont(new Font("Serif", 0, 18));
        txtUser.setBounds(660, 175, 330, 30);
        txtUser.setText(null);
        txtUser.setForeground(Color.WHITE);
        txtUser.setOpaque(false);
                
        imagemSenha.setIcon(new ImageIcon(this.getClass().getResource("/icones/login/seguranca32.png")));
        imagemSenha.setBounds(660, 215, 25, 25);
        
        lblSenha.setFont(new Font("Times New Roman", 0, 20));
        lblSenha.setForeground(Color.white);
        lblSenha.setBounds(685, 215, 100, 28);
        
        txtSenha.setFont(new Font("Serif", 0, 18));
        txtSenha.setBounds(660, 245, 330, 30);
        txtSenha.setText(null);        
        txtSenha.setForeground(Color.WHITE);
        txtSenha.setOpaque(false);
        
        btnEntrar.setFont(new Font("Agency FB", 0, 20));
        btnEntrar.setForeground(Color.black);
        btnEntrar.setBackground(new Color(30,144,255));
        btnEntrar.setBounds(660, 290, 110, 30);
                
        btnSair.setFont(new Font("Agency FB", 1, 20));
        btnSair.setForeground(Color.red);
        btnSair.setBackground(new Color(30,144,255));       
        btnSair.setBounds(900, 360, 90, 30);
        btnSair.setOpaque(false);
        
        fundo.add(lblbluemoon);
        fundo.add(lbl_login);
        fundo.add(imagemUser);
        fundo.add(lblUser);
        fundo.add(txtUser);
        fundo.add(imagemSenha);        
        fundo.add(lblSenha);
        fundo.add(txtSenha);
        fundo.add(btnEntrar);
        fundo.add(btnSair);
        
        configTools();
        add(fundo);
    }
    
    private void configTools() {
        btnEntrar.addActionListener(this);
        btnSair.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent evt) {
        if(evt.getSource() == btnEntrar) {
            try {
                if(Idiomas.getPort() == true) {
                    if(exceededLogin(txtUser.getText())) {
                        JOptionPane.showMessageDialog(null, "CONTA BLOQUEADA...! \n\nEXCEDEU O LIMITE AO TENTAR ACESSAR !\n POR FAVOR, CONTACTE O ADMINISTRADOR...", "CONTA BLOQUEADA", 0);
                    } else {
                        logar();
                    }
                } else {
                    if(exceededLogin(txtUser.getText())) {
                    JOptionPane.showMessageDialog(null, "ACCOUNT BLOCKED...! \n\n YOU EXCEEDED THE LIMIT WHEN ATTEMPTING TO ACCESS! \n PLEASE CONTACT YOUR ADMINISTRATOR...", "ACCOUNT BLOCKED", 0);
                    } else {
                        logar();
                    } 
                }
            } catch (SQLException ex) {
                Logger.getLogger(TelaLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if(evt.getSource() == btnSair) {   
            if(Idiomas.getPort() == true) {
                int sair = JOptionPane.showConfirmDialog(null, "Têm certeza que deseja sair?", "Atenção", JOptionPane.YES_NO_OPTION);
                if(sair == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            } else {
                int sair = JOptionPane.showConfirmDialog(null, "Are you sure you want to leave?", "Attention", JOptionPane.YES_NO_OPTION);
                if(sair == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
            
            
        }
    }
    
    private void logar() throws SQLException {
        String nome_usuario_login, senha_usuario_login;
        nome_usuario_login = txtUser.getText();
        String captura = new String(txtSenha.getPassword());
        senha_usuario_login = captura;
        
        controller_Usuario_Login contruserlogin = new controller_Usuario_Login();
        contruserlogin.setNome_usuario(nome_usuario_login);
        contruserlogin.setSenha_usuario(senha_usuario_login);
        
        String sql = "SELECT * FROM tbusuario WHERE usuário = ? and senha = ?";        
                
        try {            
            pst = conexao.prepareStatement(sql);
            pst.setString(1, contruserlogin.getNome_usuario());
            pst.setString(2, contruserlogin.getSenha_usuario());
            
            rs = pst.executeQuery();
            
            if((txtUser.getText().isEmpty()) && (captura.isEmpty())) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha os campos obrigatórios !", "Atenção", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required fields !", "Attention", 2);
                }
                lblUser.setForeground(Color.RED);
                lblSenha.setForeground(Color.RED);
            } else if(txtUser.getText().isEmpty()) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha o campo obrigatório !", "Usuário", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required field !", "Username", 2);
                }
                lblUser.setForeground(Color.RED);
            } else if(captura.isEmpty()) {
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "Preencha o campo obrigatório !", "Senha", 2);
                } else {
                    JOptionPane.showMessageDialog(null, "Please fill in the required field !", "Password", 2);
                }
                lblSenha.setForeground(Color.RED);
            } 
            
            if(rs.next()) {
                SwingUtilities.invokeLater(() -> {
                    try {
                        telaP = new TelaPrincipal();
                        String perfil = rs.getString(7);
                        
                        if(perfil.equals("admin")) {
                            Usuario.setUsuario(rs.getString(2), "admin");
                            telaP.txtUsuario.setText(Usuario.getUsuarioNome());
                            telaP.txtUsuario.setForeground(Color.red);
                            telaP.btnUsuario.setEnabled(true);
                            telaP.menuArmazem.setEnabled(true);
                            telaP.menuLoja.setEnabled(true);
                            telaP.menuRelatorio.setEnabled(true);
                        } else {
                            Usuario.setUsuario(rs.getString(2), "user");
                            telaP.txtUsuario.setText(Usuario.getUsuarioNome());
                            telaP.txtUsuario.setForeground(Color.black);
                            telaP.btnUsuario.setEnabled(false);
                            telaP.menuArmazem.setEnabled(false);
                            telaP.menuLoja.setEnabled(false);
                            telaP.menuRelatorio.setEnabled(false);
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(TelaLogin.class.getName()).log(Level.SEVERE, null, ex);
                    }
            });
                
                this.dispose();
            } else {
                if(Idiomas.getPort() == true) {
                    if(exceededLogin(txtUser.getText())) {
                        JOptionPane.showMessageDialog(null, "CONTA BLOQUEADA...! \n\nEXCEDEU O LIMITE AO TENTAR ACESSAR !\n POR FAVOR, CONTACTE O ADMINISTRADOR...", "CONTA BLOQUEADA", 0);
                    } else {
                        loginAttempts(txtUser.getText());
                        JOptionPane.showMessageDialog(null, "Usuário ou Senha inválido..! \nTente novamente!", "Atenção", 0);
                    }
                } else {                        
                    if(exceededLogin(txtUser.getText())) {
                        JOptionPane.showMessageDialog(null, "ACCOUNT  BLOCKED...! \n\n YOU   EXCEEDED   THE   LIMIT   WHEN   ATTEMPTING   TO   ACCESS! \n PLEASE  CONTACT  YOUR  ADMINISTRATOR...", "ACCOUNT BLOCKED", 0);
                    } else {
                        loginAttempts(txtUser.getText());
                        JOptionPane.showMessageDialog(null, "Invalid username or password..! \nTry again!", "Attention", 0);
                    }                        
                }
            }
            
            if ((!txtUser.getText().isEmpty()) && (!captura.isEmpty())) {
                lblUser.setForeground(Color.white);
                lblSenha.setForeground(Color.white);
            } else if(!txtUser.getText().isEmpty()) {
                lblUser.setForeground(Color.white);
            } else if(!captura.isEmpty()) {
                lblSenha.setForeground(Color.white);
            } else {                                       
                if(Idiomas.getPort() == true) {
                    if(exceededLogin(txtUser.getText())) {
                        JOptionPane.showMessageDialog(null, "CONTA BLOQUEADA...! \n\nEXCEDEU O LIMITE AO TENTAR ACESSAR !\n POR FAVOR, CONTACTE O ADMINISTRADOR...", "CONTA BLOQUEADA", 0);
                    } else {
                        loginAttempts(txtUser.getText());
                        JOptionPane.showMessageDialog(null, "Usuário ou Senha inválido..! \nTente novamente!", "Atenção", 0);
                    }
                } else {
                    if(exceededLogin(txtUser.getText())) {
                        JOptionPane.showMessageDialog(null, "ACCOUNT BLOCKED...! \n\n YOU EXCEEDED THE LIMIT WHEN ATTEMPTING TO ACCESS! \n PLEASE CONTACT YOUR ADMINISTRATOR...", "ACCOUNT BLOCKED", 0);
                    } else {
                        loginAttempts(txtUser.getText());
                        JOptionPane.showMessageDialog(null, "Invalid username or password..! \nTry again!", "Attention", 0);
                    } 
                }
            }           
        } catch(HeadlessException | SQLException error) {
            this.pst.close();
            this.rs.close();
            this.conexao.close();
            if(Idiomas.getPort() == true) {
                JOptionPane.showMessageDialog(null, "Erro ao tentar logar..! \nVeja mais detalhes abaixo : \n\n" + error.getMessage(), "Atenção", 0);
            } else {
                JOptionPane.showMessageDialog(null, "Error when trying to login..! \nSee more details below: \n\n" + error.getMessage(), "Attention", 0);
            }
            
        } 
    }
    
    private boolean exceededLogin(String usuario) {
        String sql = "select count(*) from tblogin_attempts where usuário = ?";
        
        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, usuario);
            
            rs = pst.executeQuery();
            if(rs.next()) {
                int attempts = rs.getInt(1);
                return attempts >= MAX_LOGIN_ATTEMPTS;
            }
            
        } catch(SQLException error) {
            try {
                this.pst.close();
                this.rs.close();
                this.conexao.close();
                error.printStackTrace();
            } catch (SQLException ex) {
                Logger.getLogger(TelaLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    
    private void loginAttempts(String usuario) {
        String sql = "insert into tblogin_attempts (usuário) values(?)";        
        try{
            pst = conexao.prepareStatement(sql);
            pst.setString(1, usuario);
            pst.executeUpdate();
        } catch(SQLException err) {
            err.printStackTrace();
        }
        
        String sql2 = "call Proced_Organizartblogin_attempts()";
        try{
            pst = conexao.prepareStatement(sql2);
            pst.executeUpdate();
        } catch(SQLException err) {
            try {
                this.pst.close();
                this.conexao.close();
                err.printStackTrace();
            } catch (SQLException ex) {
                Logger.getLogger(TelaLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void configPort() {
        lblUser.setText("Nome :");
        lblSenha.setText("Senha :");
        btnEntrar.setText("Acessar");      
        btnSair.setText("Sair");
    }
    
    private void configEng() {
        lblUser.setText("Username :");
        lblSenha.setText("Password :");
        btnEntrar.setText("Log in");        
        btnSair.setText("Exit");        
    }
    
}