package telas;

import Abstrato.Config_idiomas;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Calendar;
import dao.ConexaoDAO;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author Ramadan ismaeL
 */

public final class TelaPrincipal extends JFrame implements ActionListener, Config_idiomas {

    private static final long serialVersionUID = 1L;
    private JMenuBar menuBar;

    protected JMenu menuArmazem, menuLoja, menuRelatorio, menuOpcoes, menu_Loja2, menu_Loja3, menu_LojaJardim, menuIdioma, menuAjuda;
    protected JMenuItem itemA_Beira, itemA_Maputo, itemL2_Venda, itemL2_Produto, itemL3_Venda, itemL3_Produto,itemLjardim_Venda, itemLjardim_Produto, itemO_Registro, itemO_Sair, itemA_Sobre, itemA_Docs;
    protected JCheckBoxMenuItem itemO_Port, itemO_Eng;
    protected JLabel lblUsuario, txtUsuario, lblMarca, lblHoraDesk, lblDataDesk, lblDiaSemanaDesk, lblSalamDesk, lblInfoDesk;
    private SimpleDateFormat formatoDate, formatoTime;
    private JDesktopPane desktop;
    protected JButton btnHome, btnVenda, btnProduto, btnCliente, btnUsuario, btnSair;
    private Connection conexao = null;
    private TelaUsuario telaUsuario = null;
    private TelaCliente telaCliente = null;
    private TelaProduto_Loja1 telaProduto_Loja1 = null;
    private TelaVenda_Loja1 telaVenda_Loja1 = null;
    private TelaArmazem_Beira telaAbeira = null;
    private TelaArmazem_Maputo telaAmaputo = null;
    private TelaProduto_Loja2 telaL2produto = null;
    private TelaProduto_Loja3 telaL3produto = null;
    private TelaProduto_LojaJardim telaLjardimproduto = null;
    private TelaVenda_Loja2 telaL2venda = null;
    private TelaVenda_Loja3 telaL3venda = null;
    private TelaVenda_LojaJardim telaLjardimvenda = null;
    private TelaEstatistica telaRtotal = null;
    private RegistrarProdutos registrarP = null;
    private Sobre sobre = null;
    private Docmts doc = null;
    private String formattedDate;
    
    public TelaPrincipal() throws SQLException {
        conexao = ConexaoDAO.conector();
        
        Janela();        
        configView();
        
        if(Idiomas.getPort() == true) {
            configPort();
        } else {
            configEng();
        }
        
        itemO_Port.setSelected(Idiomas.getPort());
        itemO_Eng.setSelected(Idiomas.getEng());  
        
        setVisible(true);
    }
    
    private void Janela() {
        setTitle("BLUE MOON TRADING COMPANY");
        setSize(1536, 860);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    private void configView() {
        setLayout(null);
        
        menuBar = new JMenuBar();
        
        menuArmazem = new JMenu();
        menuLoja = new JMenu();
        menu_Loja2 = new JMenu();
        menu_Loja3 = new JMenu();
        menu_LojaJardim = new JMenu();
        menuRelatorio = new JMenu();
        menuOpcoes = new JMenu();
        menuIdioma = new JMenu();
        menuAjuda = new JMenu();
        
        itemA_Beira = new JMenuItem();
        itemA_Maputo = new JMenuItem();
        itemL2_Venda = new JMenuItem();
        itemL2_Produto = new JMenuItem();
        itemL3_Venda = new JMenuItem();
        itemL3_Produto = new JMenuItem();
        itemLjardim_Venda = new JMenuItem();
        itemLjardim_Produto = new JMenuItem();
        itemO_Registro = new JMenuItem();
        itemO_Sair = new JMenuItem();
        itemA_Sobre = new JMenuItem();
        itemA_Docs = new JMenuItem();
        
        itemO_Port = new JCheckBoxMenuItem();
        itemO_Eng = new JCheckBoxMenuItem();
        
        lblUsuario = new JLabel();
        txtUsuario = new JLabel();
        lblMarca = new JLabel();
        lblHoraDesk = new JLabel();
        lblDataDesk = new JLabel();
        lblDiaSemanaDesk = new JLabel();
        lblSalamDesk = new JLabel();
        lblInfoDesk = new JLabel();
        
        btnHome = new JButton();
        btnVenda = new JButton();
        btnProduto = new JButton();
        btnCliente = new JButton();
        btnUsuario = new JButton();
        btnSair = new JButton();
        
        desktop = new JDesktopPane();                       
        
        menuArmazem.setFont(new Font("Times New Roman", 0, 15));
        menuArmazem.setForeground(Color.BLACK);
                
        itemA_Beira.setFont(new Font("Times New Roman", 0, 14));
        itemA_Beira.setForeground(Color.BLACK);
                
        itemA_Maputo.setFont(new Font("Times New Roman", 0, 14));
        itemA_Maputo.setForeground(Color.BLACK);       
        
        menuArmazem.add(itemA_Beira);
        menuArmazem.add(itemA_Maputo);
        
        menuLoja.setFont(new Font("Times New Roman", 0, 15));
        menuLoja.setForeground(Color.BLACK);
        
        menu_Loja2.setFont(new Font("Times New Roman", 0, 14));
        menu_Loja2.setForeground(Color.BLACK);
        
        menu_Loja3.setFont(new Font("Times New Roman", 0, 14));
        menu_Loja3.setForeground(Color.BLACK);
        
        menu_LojaJardim.setFont(new Font("Times New Roman", 0, 14));
        menu_LojaJardim.setForeground(Color.BLACK);
        
        itemL2_Venda.setFont(new Font("Times New Roman", 0, 14));
        itemL2_Venda.setForeground(Color.BLACK);
        
        itemL2_Produto.setFont(new Font("Times New Roman", 0, 14));
        itemL2_Produto.setForeground(Color.BLACK);
        
        itemL3_Venda.setFont(new Font("Times New Roman", 0, 14));
        itemL3_Venda.setForeground(Color.BLACK);
        
        itemL3_Produto.setFont(new Font("Times New Roman", 0, 14));
        itemL3_Produto.setForeground(Color.BLACK);
        
        itemLjardim_Venda.setFont(new Font("Times New Roman", 0, 14));
        itemLjardim_Venda.setForeground(Color.BLACK);
        
        itemLjardim_Produto.setFont(new Font("Times New Roman", 0, 14));
        itemLjardim_Produto.setForeground(Color.BLACK);
        
        menu_Loja2.add(itemL2_Produto);
        menu_Loja2.add(itemL2_Venda);
                
        menu_Loja3.add(itemL3_Produto);
        menu_Loja3.add(itemL3_Venda);
                
        menu_LojaJardim.add(itemLjardim_Produto);
        menu_LojaJardim.add(itemLjardim_Venda);
                
        menuLoja.add(menu_Loja2);
        menuLoja.add(menu_Loja3);
        menuLoja.add(menu_LojaJardim);         
        
        menuIdioma.add(itemO_Port);
        menuIdioma.add(itemO_Eng);
        menuOpcoes.add(itemO_Registro);
        menuOpcoes.add(menuIdioma);      
        menuOpcoes.add(itemO_Sair);
        
        menuAjuda.add(itemA_Docs);
        menuAjuda.add(itemA_Sobre);
                
        menuBar.add(menuArmazem);
        menuBar.add(menuLoja);
        menuBar.add(menuRelatorio);
        menuBar.add(menuOpcoes);
        menuBar.add(menuAjuda);
        
        desktop.setBounds(216, 1, 1320, 833);        
        desktop.setBackground(new Color(100, 149, 237));
               
        lblSalamDesk.setIcon(new ImageIcon(this.getClass().getResource("/icones/salam.png")));
        lblSalamDesk.setBounds(260, 100, 730, 120);
        
        lblDataDesk.setText("T I M E");
        
        lblDataDesk.setText("D A T E");
        
        lblDiaSemanaDesk.setText("DIA DA SEMANA");
        lblDiaSemanaDesk.setFont(new Font("Agency FB", 3, 60));
        lblDiaSemanaDesk.setForeground(new Color(0, 0, 128));
                
        configData();
        
        Thread updateTimeThread = new Thread(() -> {
            while (true) {
                configData();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        
        lblInfoDesk.setText("Blue Moon Trading Company  -  Rua dos irmãos Roby, Bairro de Xipamanine  -  Maputo, Moçambique");
        lblInfoDesk.setFont(new Font("Monotype Corsiva", 0, 17));
        lblInfoDesk.setForeground(Color.BLACK);
        lblInfoDesk.setBounds(0, 816, 620, 20);
        
        desktop.add(lblSalamDesk);
        desktop.add(lblHoraDesk);
        desktop.add(lblDataDesk);
        desktop.add(lblDiaSemanaDesk);
        desktop.add(lblInfoDesk);
        
        
        lblUsuario.setBounds(4, 0, 100, 25);
        
        txtUsuario.setBounds(4, 20, 207, 25);
        
        lblMarca.setIcon(new ImageIcon(this.getClass().getResource("/icones/marca.png")));
        lblMarca.setBounds(4, 50, 207, 195);
        
        btnHome.setIcon(new ImageIcon(this.getClass().getResource("/icones/home32.png")));
        btnHome.setBounds(4, 250, 207, 35);
        
        btnVenda.setIcon(new ImageIcon(this.getClass().getResource("/icones/vendas.png")));
        btnVenda.setBounds(4, 290, 207, 35);
        
        btnProduto.setIcon(new ImageIcon(this.getClass().getResource("/icones/produt.png")));
        btnProduto.setBounds(4, 330, 207, 35);
        
        btnCliente.setIcon(new ImageIcon(this.getClass().getResource("/icones/client.png")));
        btnCliente.setBounds(4, 370, 207, 35);
        
        btnUsuario.setIcon(new ImageIcon(this.getClass().getResource("/icones/user32.png")));
        btnUsuario.setBounds(4, 410, 207, 35);
        
        btnSair.setIcon(new ImageIcon(this.getClass().getResource("/icones/sair.png")));
        btnSair.setBounds(4, 799, 207, 35);
        
        configTools();
        
        updateTimeThread.start();
        setJMenuBar(menuBar);
        getContentPane().add(desktop);
        getContentPane().add(lblUsuario);
        getContentPane().add(txtUsuario);
        getContentPane().add(lblMarca);
        getContentPane().add(btnHome);
        getContentPane().add(btnVenda);
        getContentPane().add(btnProduto);
        getContentPane().add(btnCliente);
        getContentPane().add(btnUsuario);
        getContentPane().add(btnSair);
    }
    
    private void configData() {
        Date now = new Date();
        
        formatoTime = new SimpleDateFormat("HH:mm:ss");
        
        if(Idiomas.getPort() == true) {
            formatoDate = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy");
            lblHoraDesk.setBounds(510, 250, 500, 190);
            lblDataDesk.setBounds(450, 400, 700, 100);
            lblDiaSemanaDesk.setBounds(450, 460, 700, 100);
            lblDataDesk.setText(formatoDate.format(now));
        } else {
            Date date = new Date();
            formatoDate = new SimpleDateFormat("MMMM dd',' yyyy");
            formattedDate = formatoDate.format(date);
            formattedDate = addOrdinalSuffix(formattedDate);
            lblHoraDesk.setBounds(450, 250, 500, 190);
            lblDataDesk.setBounds(450, 400, 700, 100);
            lblDiaSemanaDesk.setBounds(450, 460, 700, 100);
            lblDataDesk.setText(formattedDate);
        }
                    
        lblHoraDesk.setFont(new Font("Agency FB", 1, 150));
        lblHoraDesk.setForeground(new Color(0, 0, 128));
                
        lblDataDesk.setFont(new Font("Agency FB", 3, 60));
        lblDataDesk.setForeground(new Color(0, 0, 128));
                
        lblHoraDesk.setText(formatoTime.format(now));
    }
    
    public String addOrdinalSuffix(String date) {
        String[] parts = date.split(" ");
        String day = parts[1].replace(",", "");
        int dayInt = Integer.parseInt(day);

        String suffix;
        if (dayInt >= 11 && dayInt <= 13) {
            suffix = "th,";
        } else {
            suffix = switch (dayInt % 10) {
                case 1 -> "st,";
                case 2 -> "nd,";
                case 3 -> "rd,";
                default -> "th,";
            };
        }

        parts[1] = day + suffix;
        return String.join(" ", parts);
    }
    
    public void configTools() {
        itemO_Port.addActionListener(this);
        itemO_Eng.addActionListener(this);
        btnSair.addActionListener(this);
        itemO_Sair.addActionListener(this);
        btnUsuario.addActionListener(this);
        btnCliente.addActionListener(this);
        btnProduto.addActionListener(this);
        btnVenda.addActionListener(this);
        itemA_Beira.addActionListener(this);
        itemA_Maputo.addActionListener(this);
        itemL2_Produto.addActionListener(this);
        itemL2_Venda.addActionListener(this);
        itemL3_Produto.addActionListener(this);
        itemL3_Venda.addActionListener(this);
        itemLjardim_Produto.addActionListener(this);
        itemLjardim_Venda.addActionListener(this);        
        itemO_Registro.addActionListener(this);
        itemA_Sobre.addActionListener(this);
        itemA_Docs.addActionListener(this);
        
        menuRelatorio.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SwingUtilities.invokeLater(() -> {
                    if (telaRtotal == null || !telaRtotal.isVisible()) {
                        telaRtotal = new TelaEstatistica();
                        telaRtotal.setVisible(true);
                        telaRtotal.toFront();
                    } else {
                        telaRtotal.toFront();
                    }
                });
            }
        });  
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        if(evt.getSource() == itemO_Port) {
            itemO_Eng.setSelected(false);
            itemO_Port.setSelected(true);
            Idiomas.setPort(true);
            Idiomas.setEng(false);
            JOptionPane.showMessageDialog(null, "Idioma Português ativado !");
        } else if(evt.getSource() == itemO_Eng) {
            itemO_Port.setSelected(false);
            itemO_Eng.setSelected(true);
            Idiomas.setEng(true);
            Idiomas.setPort(false);
            JOptionPane.showMessageDialog(null, "English language active !");
        } else if(evt.getSource() == btnSair) {
            if(Idiomas.getPort() == true) {
                int sair = JOptionPane.showConfirmDialog(null, "Têm certeza que deseja sair?", "Atenção", JOptionPane.YES_NO_OPTION);
                if(sair == JOptionPane.YES_OPTION) {
                    try {
                        conexao.close();
                        System.exit(0);
                    } catch (SQLException ex) {
                        Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                int sair = JOptionPane.showConfirmDialog(null, "Are you sure you want to leave?", "Attention", JOptionPane.YES_NO_OPTION);
                if(sair == JOptionPane.YES_OPTION) {
                    try {
                        conexao.close();
                        System.exit(0);
                    } catch (SQLException ex) {
                        Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        } else if(evt.getSource() == itemO_Sair) {
            try {
                this.dispose();
                TelaPrincipal telaP = new TelaPrincipal();
                String sql = "SELECT * FROM tbusuario where NOME = ?";
                try {
                    Connection conec = ConexaoDAO.conector();
                    PreparedStatement pst = conec.prepareStatement(sql);
                    pst.setString(1, txtUsuario.getText());
                    ResultSet rs = pst.executeQuery();
                    
                    if(rs.next()) {
                        String perfil = Usuario.getUsuarioPerfil();
                        SwingUtilities.invokeLater(() -> {
                            if("admin".equals(perfil)) {
                                telaP.txtUsuario.setForeground(Color.red);
                                telaP.btnUsuario.setEnabled(true);
                                telaP.menuArmazem.setEnabled(true);
                                telaP.menuLoja.setEnabled(true);
                                telaP.menuRelatorio.setEnabled(true);
                            } else {
                                telaP.txtUsuario.setForeground(Color.black);
                                telaP.btnUsuario.setEnabled(false);
                                telaP.menuArmazem.setEnabled(false);
                                telaP.menuLoja.setEnabled(false);
                                telaP.menuRelatorio.setEnabled(false);
                            }
                        });
                    }
                } catch(HeadlessException | SQLException error) {
                    if(Idiomas.getPort() == true) {
                        JOptionPane.showMessageDialog(null, "Erro no admin ou user.! \nVeja mais detalhes abaixo : \n\n" + error.getMessage(), "Atenção", 0);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error in admin or user..! \nSee more details below: \n\n" + error.getMessage(), "Attention", 0);
                    }
                    
                }
            } catch(SQLException ex) {
                Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                
            }
        } else if(evt.getSource() == btnUsuario) {    
            SwingUtilities.invokeLater(() -> {
                if (telaUsuario == null || telaUsuario.isClosed()) {
                    try {
                        telaUsuario = new TelaUsuario();
                    } catch (SQLException ex) {
                        Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    desktop.add(telaUsuario);
                    telaUsuario.setVisible(true);
                    telaUsuario.toFront();
                } else {
                    telaUsuario.toFront();
                }
            });
        } else if(evt.getSource() == btnCliente) {
            SwingUtilities.invokeLater(() -> {
                if (telaCliente == null || telaCliente.isClosed()) {
                    try {
                        telaCliente = new TelaCliente();
                    } catch (SQLException ex) {
                        Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    desktop.add(telaCliente);
                    telaCliente.setVisible(true);
                    telaCliente.toFront();
                } else {
                    telaCliente.toFront();
                }
            });
        } else if(evt.getSource() == btnProduto) {
            SwingUtilities.invokeLater(() -> {
                if (telaProduto_Loja1 == null || telaProduto_Loja1.isClosed()) {
                    try {
                        telaProduto_Loja1 = new TelaProduto_Loja1();
                    } catch (SQLException ex) {
                        Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    desktop.add(telaProduto_Loja1);
                    telaProduto_Loja1.setVisible(true);
                    telaProduto_Loja1.toFront();
                } else {
                    telaProduto_Loja1.toFront();
                }
            });
        } else if(evt.getSource() == btnVenda) {
            SwingUtilities.invokeLater(() -> {
                if (telaVenda_Loja1 == null || telaVenda_Loja1.isClosed()) {
                    try {
                        telaVenda_Loja1 = new TelaVenda_Loja1();
                    } catch (SQLException ex) {
                        Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    desktop.add(telaVenda_Loja1);
                    telaVenda_Loja1.setVisible(true);
                    telaVenda_Loja1.toFront();
                } else {
                    telaVenda_Loja1.toFront();
                }
            });
        } else if(evt.getSource() == itemA_Beira) {
            SwingUtilities.invokeLater(() -> {
                if (telaAbeira == null || telaAbeira.isClosed()) {
                    try {
                        telaAbeira = new TelaArmazem_Beira();
                    } catch (SQLException ex) {
                        Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    desktop.add(telaAbeira);
                    telaAbeira.setVisible(true);
                    telaAbeira.toFront();
                } else {
                    telaAbeira.toFront();
                }
            });
        } else if(evt.getSource() == itemA_Maputo) {
            SwingUtilities.invokeLater(() -> {
                if (telaAmaputo == null || telaAmaputo.isClosed()) {
                    try {
                        telaAmaputo = new TelaArmazem_Maputo();
                    } catch (SQLException ex) {
                        Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    desktop.add(telaAmaputo);
                    telaAmaputo.setVisible(true);
                    telaAmaputo.toFront();
                } else {
                    telaAmaputo.toFront();
                }
            });
        } else if(evt.getSource() == itemL2_Produto) {
            SwingUtilities.invokeLater(() -> {
                if (telaL2produto == null ||telaL2produto.isClosed()) {
                    try {
                        telaL2produto = new TelaProduto_Loja2();
                    } catch (SQLException ex) {
                        Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    desktop.add(telaL2produto);
                    telaL2produto.setVisible(true);
                    telaL2produto.toFront();
                } else {
                    telaL2produto.toFront();
                }
            });
        } else if(evt.getSource() == itemL3_Produto) {
            SwingUtilities.invokeLater(() -> {
                if (telaL3produto == null ||telaL3produto.isClosed()) {
                    try {
                        telaL3produto = new TelaProduto_Loja3();
                    } catch (SQLException ex) {
                        Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    desktop.add(telaL3produto);
                    telaL3produto.setVisible(true);
                    telaL3produto.toFront();
                } else {
                    telaL3produto.toFront();
                }
            });
        } else if(evt.getSource() == itemLjardim_Produto) {
            SwingUtilities.invokeLater(() -> {
                if (telaLjardimproduto == null ||telaLjardimproduto.isClosed()) {
                    try {
                        telaLjardimproduto = new TelaProduto_LojaJardim();
                    } catch (SQLException ex) {
                        Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    desktop.add(telaLjardimproduto);
                    telaLjardimproduto.setVisible(true);
                    telaLjardimproduto.toFront();
                } else {
                    telaLjardimproduto.toFront();
                }
            });
        } else if(evt.getSource() == itemL2_Venda) {
            SwingUtilities.invokeLater(() -> {
                if (telaL2venda == null ||telaL2venda.isClosed()) {
                    try {
                        telaL2venda = new TelaVenda_Loja2();
                    } catch (SQLException ex) {
                        Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    desktop.add(telaL2venda);
                    telaL2venda.setVisible(true);
                    telaL2venda.toFront();
                } else {
                    telaL2venda.toFront();
                }
            });
        } else if(evt.getSource() == itemL3_Venda) {
            SwingUtilities.invokeLater(() -> {
                if (telaL3venda == null ||telaL3venda.isClosed()) {
                    try {
                        telaL3venda = new TelaVenda_Loja3();
                    } catch (SQLException ex) {
                        Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    desktop.add(telaL3venda);
                    telaL3venda.setVisible(true);
                    telaL3venda.toFront();
                } else {
                    telaL3venda.toFront();
                }
            });
        } else if(evt.getSource() == itemLjardim_Venda) {
            SwingUtilities.invokeLater(() -> {
                if (telaLjardimvenda == null ||telaLjardimvenda.isClosed()) {
                    try {
                        telaLjardimvenda = new TelaVenda_LojaJardim();
                    } catch (SQLException ex) {
                        Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    desktop.add(telaLjardimvenda);
                    telaLjardimvenda.setVisible(true);
                    telaLjardimvenda.toFront();
                } else {
                    telaLjardimvenda.toFront();
                }
            });
        } else if(evt.getSource() == itemO_Registro) {
            if (registrarP == null || !registrarP.isVisible()) {
                try {
                    registrarP = new RegistrarProdutos();
                    registrarP.setVisible(true);
                    registrarP.toFront();
                } catch (SQLException ex) {
                    Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
               registrarP.toFront();
            }
        } else if(evt.getSource() == itemA_Sobre) {
            if (sobre == null || !sobre.isVisible()) {
                sobre = new Sobre();
                sobre.setVisible(true);
                sobre.toFront();
            } else {
               sobre.toFront();
            }
        } else if(evt.getSource() == itemA_Docs) {
            if (doc == null || !doc.isVisible()) {
                doc = new Docmts();
                doc.setVisible(true);
                doc.toFront();
            } else {
                doc.toFront();
            }
        } else if(evt.getSource() == btnHome) {
        }
    }
    
    
    @Override
    public void configPort() {
        menuArmazem.setText("Armazéns");
        itemA_Beira.setText("Beira");
        itemA_Maputo.setText("Maputo");
        menuLoja.setText("Lojas");
        menu_Loja2.setText("Loja 2");
        menu_Loja3.setText("Loja 3");
        menu_LojaJardim.setText("Loja Jardim");
        itemL2_Venda.setText("Vendas");
        itemL2_Produto.setText("Produtos");
        itemL3_Venda.setText("Vendas");
        itemL3_Produto.setText("Produtos");
        itemLjardim_Venda.setText("Vendas");
        itemLjardim_Produto.setText("Produtos");
        menuRelatorio.setText("Relatórios");
        menuOpcoes.setText("Opções");
        itemO_Registro.setText("Registro de produtos");
        menuIdioma.setText("Idioma");
        itemO_Port.setText("Português");        
        itemO_Eng.setText("Inglês");
        itemA_Sobre.setText("Sobre");
        itemA_Docs.setText("Informações e Suportes");
        itemO_Sair.setText("Reiniciar");
        lblUsuario.setText("Usuário :");
        btnHome.setText("Home");
        btnVenda.setText("Vendas");
        btnProduto.setText("Produtos");
        btnCliente.setText("Clientes");
        btnUsuario.setText("Usuários");
        btnSair.setText("Sair");
        menuAjuda.setText("Ajuda");
        
        
        menuRelatorio.setFont(new Font("Times New Roman", 0, 15));
        menuRelatorio.setForeground(Color.BLACK);
        
        menuOpcoes.setFont(new Font("Times New Roman", 0, 15));
        menuOpcoes.setForeground(Color.BLACK);
                
        itemO_Registro.setFont(new Font("Times New Roman", 0, 14));
        itemO_Registro.setForeground(Color.BLACK);
        
        menuIdioma.setFont(new Font("Times New Roman", 0, 14));
        menuIdioma.setForeground(Color.BLACK);
        
        itemO_Port.setFont(new Font("Times New Roman", 0, 14));
        itemO_Port.setForeground(Color.BLACK);       
        
        itemO_Eng.setFont(new Font("Times New Roman", 0, 14));
        itemO_Eng.setForeground(Color.BLACK);
        
        itemA_Sobre.setFont(new Font("Times New Roman", 0, 14));
        itemA_Sobre.setForeground(Color.BLACK);
        
        itemA_Docs.setFont(new Font("Times New Roman", 0, 14));
        itemA_Docs.setForeground(Color.BLACK);
        
        itemO_Sair.setFont(new Font("Times New Roman", 0, 14));
        itemO_Sair.setForeground(Color.BLACK);
        
        menuAjuda.setFont(new Font("Times New Roman", 0, 15));
        menuAjuda.setForeground(Color.BLACK);
        
        lblUsuario.setFont(new Font("Times New Roman", 1, 17));
        lblUsuario.setForeground(Color.BLACK);
        txtUsuario.setFont(new Font("Times New Roman", 2, 16));
        txtUsuario.setForeground(Color.BLACK);
        btnHome.setFont(new Font("Times New Roman", 0, 16));
        btnVenda.setFont(new Font("Times New Roman", 0, 16));
        btnProduto.setFont(new Font("Times New Roman", 0, 16));
        btnCliente.setFont(new Font("Times New Roman", 0, 16));
        btnUsuario.setFont(new Font("Times New Roman", 0, 16));
        btnSair.setFont(new Font("Times New Roman", 0, 16));
        
        txtUsuario.setText(Usuario.getUsuarioNome());
        if(("admin".equals(Usuario.getUsuarioPerfil()))) {
            txtUsuario.setForeground(Color.red);
        } else {
            txtUsuario.setForeground(Color.black);
        }
        
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());

        int diaDaSemana = cal.get(Calendar.DAY_OF_WEEK);
        switch (diaDaSemana) {
            case Calendar.SUNDAY -> lblDiaSemanaDesk.setText("domingo");
            case Calendar.MONDAY -> lblDiaSemanaDesk.setText("segunda-feira");
            case Calendar.TUESDAY -> lblDiaSemanaDesk.setText("terça-feira");
            case Calendar.WEDNESDAY -> lblDiaSemanaDesk.setText("quarta-feira");
            case Calendar.THURSDAY -> lblDiaSemanaDesk.setText("quinta-feira");
            case Calendar.FRIDAY -> lblDiaSemanaDesk.setText("sexta-feira");
            case Calendar.SATURDAY -> lblDiaSemanaDesk.setText("sábado");
        }
    }
    
    @Override
    public void configEng() {
        menuArmazem.setText("Warehouses");
        itemA_Beira.setText("Beira");
        itemA_Maputo.setText("Maputo");
        menuLoja.setText("Shops");
        menu_Loja2.setText("Shop 2");
        menu_Loja3.setText("Shop 3");
        menu_LojaJardim.setText("Jardim Shop");
        itemL2_Venda.setText("Sellings");
        itemL2_Produto.setText("Products");
        itemL3_Venda.setText("Sellings");
        itemL3_Produto.setText("Products");
        itemLjardim_Venda.setText("Sellings");
        itemLjardim_Produto.setText("Products");
        menuRelatorio.setText("Reports");
        menuOpcoes.setText("Options");
        itemO_Registro.setText("Product Registration");
        menuIdioma.setText("Languages");
        itemO_Port.setText("Portuguese");        
        itemO_Eng.setText("English");
        itemA_Sobre.setText("About");
        itemA_Docs.setText("Docs and Support");
        itemO_Sair.setText("Restart");
        lblUsuario.setText("User :");
        btnHome.setText("Home");
        btnVenda.setText("Sellings");
        btnProduto.setText("Products");
        btnCliente.setText("Clients");
        btnUsuario.setText("Users");
        btnSair.setText("Exit");
        menuAjuda.setText("Help");
        
        menuAjuda.setFont(new Font("Times New Roman", 0, 15));
        menuAjuda.setForeground(Color.BLACK);
        
        menuRelatorio.setFont(new Font("Times New Roman", 0, 15));
        menuRelatorio.setForeground(Color.BLACK);
        
        menuOpcoes.setFont(new Font("Times New Roman", 0, 15));
        menuOpcoes.setForeground(Color.BLACK);
                
        itemO_Registro.setFont(new Font("Times New Roman", 0, 14));
        itemO_Registro.setForeground(Color.BLACK);
        
        menuIdioma.setFont(new Font("Times New Roman", 0, 14));
        menuIdioma.setForeground(Color.BLACK);
        
        itemO_Port.setFont(new Font("Times New Roman", 0, 14));
        itemO_Port.setForeground(Color.BLACK);       
        
        itemO_Eng.setFont(new Font("Times New Roman", 0, 14));
        itemO_Eng.setForeground(Color.BLACK);
        
        itemA_Sobre.setFont(new Font("Times New Roman", 0, 14));
        itemA_Sobre.setForeground(Color.BLACK);
        
        itemA_Docs.setFont(new Font("Times New Roman", 0, 14));
        itemA_Docs.setForeground(Color.BLACK);
        
        itemO_Sair.setFont(new Font("Times New Roman", 0, 14));
        itemO_Sair.setForeground(Color.BLACK);
        
        lblUsuario.setFont(new Font("Times New Roman", 1, 17));
        lblUsuario.setForeground(Color.BLACK);
        txtUsuario.setText(Usuario.getUsuarioNome());
        if(("admin".equals(Usuario.getUsuarioPerfil()))) {
            txtUsuario.setForeground(Color.red);
        } else {
            txtUsuario.setForeground(Color.black);
        }
        txtUsuario.setFont(new Font("Times New Roman", 2, 16));
        btnHome.setFont(new Font("Times New Roman", 0, 16));
        btnVenda.setFont(new Font("Times New Roman", 0, 16));
        btnProduto.setFont(new Font("Times New Roman", 0, 16));
        btnCliente.setFont(new Font("Times New Roman", 0, 16));
        btnUsuario.setFont(new Font("Times New Roman", 0, 16));
        btnSair.setFont(new Font("Times New Roman", 0, 16));
        
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(System.currentTimeMillis());

        int diaDaSemana = cal.get(Calendar.DAY_OF_WEEK);
        switch (diaDaSemana) {
            case Calendar.SUNDAY -> lblDiaSemanaDesk.setText("sunday");
            case Calendar.MONDAY -> lblDiaSemanaDesk.setText("monday");
            case Calendar.TUESDAY -> lblDiaSemanaDesk.setText("tuesday");
            case Calendar.WEDNESDAY -> lblDiaSemanaDesk.setText("wednesday");
            case Calendar.THURSDAY -> lblDiaSemanaDesk.setText("thursday");
            case Calendar.FRIDAY -> lblDiaSemanaDesk.setText("friday");
            case Calendar.SATURDAY -> lblDiaSemanaDesk.setText("saturday");
        }
    }
    
}