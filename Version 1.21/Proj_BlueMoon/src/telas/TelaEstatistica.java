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

/**
 * @author Ramadan ismaeL
 */

public final class TelaEstatistica extends JFrame implements Config_idiomas {
    private JLabel tituloVendas, tituloTotal, lblPesquisa, lblExempl, lblLoja1, lblTotal_quant_venda1, txtTotal_quant_venda1, lblTotal_preco_artigo1, txtTotal_preco_artigo1, mznpa1, lblTotal_preco_venda1, txtTotal_preco_venda1, mznpv1, lblTotal_lucro1, txtTotal_lucro1, mznl1, lblLoja2, lblTotal_quant_venda2, txtTotal_quant_venda2, lblTotal_preco_artigo2, txtTotal_preco_artigo2, mznpa2, lblTotal_preco_venda2, txtTotal_preco_venda2, mznpv2, lblTotal_lucro2, mznl2, txtTotal_lucro2, lblLoja3, lblTotal_quant_venda3, txtTotal_quant_venda3, lblTotal_preco_artigo3, txtTotal_preco_artigo3, mznpa3, lblTotal_preco_venda3, txtTotal_preco_venda3, mznpv3, lblTotal_lucro3, txtTotal_lucro3, mznl3, lblLojaJardim, lblTotal_quant_vendaJardim, txtTotal_quant_vendaJardim, lblTotal_preco_artigoJardim, txtTotal_preco_artigoJardim, mznpaJardim, lblTotal_preco_vendaJardim, txtTotal_preco_vendaJardim, mznpvJardim, lblTotal_lucroJardim, txtTotal_lucroJardim, mznlJardim;
    private JLabel t, q1, pv1, pa1, pl1, q2, pv2, pa2, pl2, q3, pv3, pa3, pl3, qj, pvj, paj, plj;
    private JLabel lblTotal_quant_vendaT, qT, txtTotal_quant_vendaT, spqT, lblTotal_preco_artigoT, paT, mznpaT, txtTotal_preco_artigoT, spaT, lblTotal_preco_vendaT, pvT, mznpvT, txtTotal_preco_vendaT, spvT, lblTotal_lucroT, plT, mznlT, txtTotal_lucroT, splT;
    private JLabel tituloProdutos, lblLojaB, lblQuantB, sqB, lblLojaM, lblQuantM, sqM, lblLojaTP, lblQuantTP, sqTP;
    private JTextField txtPesquisa;
    private JLabel lblLoja1P, lblQuant1, sq1, lblLoja2P, lblQuant2, sq2, lblLoja3P, lblQuant3, sq3, lblLojaJardimP, lblQuantJardim, sqj;
    private JButton btnAtualizar_Tb;
    private JSeparator sp1, sp2, sp3, spj, SP;
    private Connection conexao = null;
    private int somavq1 = 0, somavq2 = 0, somavq3 = 0, somavqj = 0, somavqt = 0;
    private double somava1 = 0.00, somava2 = 0.00, somava3 = 0.00, somavaj = 0.00, somavat = 0.00;
    private double somavv1 = 0.00, somavv2 = 0.00, somavv3 = 0.00, somavvj = 0.00, somavvt = 0.00;
    private double somavl1 = 0.00, somavl2 = 0.00, somavl3 = 0.00, somavlj = 0.00, somavlt = 0.00;
    private int somap1 = 0, somap2 = 0, somap3 = 0, somapj = 0, somapt = 0, somaab = 0, somaam = 0;
    
    public TelaEstatistica() {
        try {
            conexao = ConexaoDAO.conector();
            
            Janela();
            configView();
            
            if(Idiomas.getPort() == true) {
                configPort();
            } else {
                configEng();
            }
            
            tabelaDAO();
        } catch (SQLException ex) {
            Logger.getLogger(TelaEstatistica.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void Janela() {
        if(Idiomas.getPort() == true) {
            setTitle("# ESTATÍSTICA");
        } else {
            setTitle("# TOTAL");
        }
        setSize(700, 500);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(false);
    }
    
    private void configView() {
        setLayout(null);
        
        tituloVendas = new JLabel();
        tituloTotal = new JLabel();
        lblPesquisa = new JLabel();
        lblExempl = new JLabel();
        lblLoja1 = new JLabel();
        lblTotal_quant_venda1 = new JLabel();
        txtTotal_quant_venda1 = new JLabel();
        lblTotal_preco_artigo1 = new JLabel();
        txtTotal_preco_artigo1 = new JLabel();
        mznpa1 = new JLabel();
        lblTotal_preco_venda1 = new JLabel();
        txtTotal_preco_venda1 = new JLabel();
        mznpv1 = new JLabel();
        lblTotal_lucro1 = new JLabel();
        txtTotal_lucro1 = new JLabel();
        mznl1 = new JLabel();
        lblLoja2 = new JLabel();
        lblTotal_quant_venda2 = new JLabel();
        txtTotal_quant_venda2 = new JLabel();
        lblTotal_preco_artigo2 = new JLabel();
        txtTotal_preco_artigo2 = new JLabel();
        mznpa2 = new JLabel();
        lblTotal_preco_venda2 = new JLabel();
        txtTotal_preco_venda2 = new JLabel();
        mznpv2 = new JLabel();
        lblTotal_lucro2 = new JLabel();
        mznl2 = new JLabel();
        txtTotal_lucro2 = new JLabel();
        lblLoja3 = new JLabel();
        lblTotal_quant_venda3 = new JLabel();
        txtTotal_quant_venda3 = new JLabel();
        lblTotal_preco_artigo3 = new JLabel();
        txtTotal_preco_artigo3 = new JLabel();
        mznpa3 = new JLabel();
        lblTotal_preco_venda3 = new JLabel();
        txtTotal_preco_venda3 = new JLabel();
        mznpv3 = new JLabel();
        lblTotal_lucro3 = new JLabel();
        txtTotal_lucro3 = new JLabel();
        mznl3 = new JLabel();
        lblLojaJardim = new JLabel();
        lblTotal_quant_vendaJardim = new JLabel();
        txtTotal_quant_vendaJardim = new JLabel();
        lblTotal_preco_artigoJardim =  new JLabel();
        txtTotal_preco_artigoJardim = new JLabel();
        mznpaJardim = new JLabel();
        lblTotal_preco_vendaJardim = new JLabel();
        txtTotal_preco_vendaJardim = new JLabel();
        mznpvJardim = new JLabel();
        lblTotal_lucroJardim = new JLabel(); 
        txtTotal_lucroJardim = new JLabel(); 
        mznlJardim = new JLabel();
        q1 = new JLabel();
        pv1 = new JLabel();
        pa1 = new JLabel();
        pl1 = new JLabel();
        q2 = new JLabel();
        pv2 = new JLabel();
        pa2 = new JLabel();
        pl2 = new JLabel();
        q3 = new JLabel();
        pv3 = new JLabel();
        pa3 = new JLabel();
        pl3 = new JLabel();
        qj = new JLabel();
        pvj = new JLabel();
        paj = new JLabel();
        plj = new JLabel();
        lblTotal_quant_vendaT = new JLabel();
        qT = new JLabel();
        txtTotal_quant_vendaT = new JLabel();
        spqT = new JLabel();
        lblTotal_preco_artigoT = new JLabel();
        paT = new JLabel();
        mznpaT = new JLabel();
        txtTotal_preco_artigoT = new JLabel();
        spaT = new JLabel();
        lblTotal_preco_vendaT = new JLabel();
        pvT = new JLabel();
        mznpvT = new JLabel();
        txtTotal_preco_vendaT = new JLabel();
        spvT = new JLabel();
        lblTotal_lucroT = new JLabel();
        plT = new JLabel();
        mznlT = new JLabel();
        txtTotal_lucroT = new JLabel();
        splT = new JLabel();
        tituloProdutos = new JLabel();
        lblLoja1P = new JLabel();
        lblQuant1 = new JLabel();
        sq1 = new JLabel();
        lblLoja2P = new JLabel();
        lblQuant2 = new JLabel();
        sq2 = new JLabel();
        lblLoja3P = new JLabel();
        lblQuant3 = new JLabel();
        sq3 = new JLabel();
        lblLojaJardimP = new JLabel();
        lblQuantJardim = new JLabel();
        sqj = new JLabel();
        lblLojaB = new JLabel();
        lblQuantB = new JLabel();
        sqB = new JLabel();
        lblLojaM = new JLabel();
        lblQuantM = new JLabel();
        sqM = new JLabel();
        lblLojaTP = new JLabel();
        lblQuantTP = new JLabel();
        t = new JLabel();
        sqTP = new JLabel();
                
        txtPesquisa = new JTextField();
        
        btnAtualizar_Tb = new JButton();
        
        sp1 = new JSeparator();
        sp2 = new JSeparator();
        sp3 = new JSeparator();
        spj = new JSeparator();
        SP = new JSeparator();
        
        lblPesquisa.setIcon(new ImageIcon(this.getClass().getResource("/icones/lupa.png")));
        lblPesquisa.setBounds(334, 5, 28, 28);
        
        txtPesquisa.setText(null);
        txtPesquisa.setFont(new Font("Segoe UI", 1, 16));
        txtPesquisa.setForeground(Color.red);
        txtPesquisa.setBounds(33, 5, 300, 28);
        
        btnAtualizar_Tb.setIcon(new ImageIcon(this.getClass().getResource("/icones/atualizar.png")));
        btnAtualizar_Tb.setBounds(2, 5, 28, 27);
        btnAtualizar_Tb.setOpaque(false); //Torna o botão visível ou transparente
        btnAtualizar_Tb.setBackground(new Color(135, 206, 250));
        btnAtualizar_Tb.setBorder(new MatteBorder(0, 0, 0, 0, Color.WHITE));
        
        lblExempl.setFont(new Font("Times New Roman", 0, 15));
        lblExempl.setForeground(Color.black);
        lblExempl.setBounds(364, 5, 500, 28);
        
        // V E N D A S
        
        tituloVendas.setFont(new Font("Algerian", 1, 25));
        tituloVendas.setForeground(Color.black);
        tituloVendas.setBounds(1403, 5, 400, 30);
        
        // L O J A _ 1
        
        lblLoja1.setFont(new Font("Algerian", 0, 25));
        lblLoja1.setForeground(Color.black);
        lblLoja1.setBounds(12, 60, 400, 30);
        
        lblTotal_quant_venda1.setFont(new Font("Agency FB", 0, 24));
        lblTotal_quant_venda1.setForeground(Color.black);
        lblTotal_quant_venda1.setBounds(12, 95, 160, 30);
        
        q1.setText(":");
        q1.setFont(new Font("Agency FB", 0, 24));
        q1.setForeground(Color.black);
        q1.setBounds(182, 95, 5, 30);
        
        txtTotal_quant_venda1.setText("0");
        txtTotal_quant_venda1.setFont(new Font("Agency FB", 0, 24));
        txtTotal_quant_venda1.setForeground(Color.black);
        txtTotal_quant_venda1.setBounds(202, 95, 150, 30);
        
        lblTotal_preco_artigo1.setFont(new Font("Agency FB", 0, 24));
        lblTotal_preco_artigo1.setForeground(Color.black);
        lblTotal_preco_artigo1.setBounds(12, 125, 160, 30);
        
        pa1.setText(":");
        pa1.setFont(new Font("Agency FB", 0, 24));
        pa1.setForeground(Color.black);
        pa1.setBounds(182, 125, 5, 30);
        
        mznpa1.setText("MZN");
        mznpa1.setFont(new Font("Agency FB", 0, 24));
        mznpa1.setForeground(Color.black);
        mznpa1.setBounds(202, 125, 28, 30);
        
        txtTotal_preco_artigo1.setText("0.0");
        txtTotal_preco_artigo1.setFont(new Font("Agency FB", 0, 24));
        txtTotal_preco_artigo1.setForeground(Color.black);
        txtTotal_preco_artigo1.setBounds(237, 125, 150, 30);
       
        lblTotal_preco_venda1.setFont(new Font("Agency FB", 0, 24));
        lblTotal_preco_venda1.setForeground(Color.black);
        lblTotal_preco_venda1.setBounds(12, 155, 160, 30);
        
        pv1.setText(":");
        pv1.setFont(new Font("Agency FB", 0, 24));
        pv1.setForeground(Color.black);
        pv1.setBounds(182, 155, 5, 30);
        
        mznpv1.setText("MZN");
        mznpv1.setFont(new Font("Agency FB", 0, 24));
        mznpv1.setForeground(Color.black);
        mznpv1.setBounds(202, 155, 28, 30);
        
        txtTotal_preco_venda1.setText("0.0");
        txtTotal_preco_venda1.setFont(new Font("Agency FB", 0, 24));
        txtTotal_preco_venda1.setForeground(Color.black);
        txtTotal_preco_venda1.setBounds(237, 155, 150, 30);
        
        lblTotal_lucro1.setFont(new Font("Agency FB", 0, 24));
        lblTotal_lucro1.setForeground(Color.black);
        lblTotal_lucro1.setBounds(12, 185, 160, 30);
        
        pl1.setText(":");
        pl1.setFont(new Font("Agency FB", 0, 24));
        pl1.setForeground(Color.black);
        pl1.setBounds(182, 185, 5, 30);
        
        mznl1.setText("MZN");
        mznl1.setFont(new Font("Agency FB", 0, 24));
        mznl1.setForeground(Color.black);
        mznl1.setBounds(202, 185, 28, 30);
        
        txtTotal_lucro1.setText("0.0");
        txtTotal_lucro1.setFont(new Font("Agency FB", 0, 24));
        txtTotal_lucro1.setForeground(Color.black);
        txtTotal_lucro1.setBounds(235, 185, 150, 30);
        
        sp1.setBounds(7, 60, 375, 160);
        sp1.setBorder(new MatteBorder(1, 1, 1, 1, Color.lightGray));
        
        // L O J A _ 2
        
        lblLoja2.setFont(new Font("Algerian", 0, 25));
        lblLoja2.setForeground(Color.black);
        lblLoja2.setBounds(394, 60, 400, 30);
        
        lblTotal_quant_venda2.setFont(new Font("Agency FB", 0, 24));
        lblTotal_quant_venda2.setForeground(Color.black);
        lblTotal_quant_venda2.setBounds(394, 95, 160, 30);
         
        q2.setText(":");
        q2.setFont(new Font("Agency FB", 0, 24));
        q2.setForeground(Color.black);
        q2.setBounds(564, 95, 5, 30);
        
        txtTotal_quant_venda2.setText("0");
        txtTotal_quant_venda2.setFont(new Font("Agency FB", 0, 24));
        txtTotal_quant_venda2.setForeground(Color.black);
        txtTotal_quant_venda2.setBounds(584, 95, 150, 30);
        
        lblTotal_preco_artigo2.setFont(new Font("Agency FB", 0, 24));
        lblTotal_preco_artigo2.setForeground(Color.black);
        lblTotal_preco_artigo2.setBounds(394, 125, 160, 30);
        
        pa2.setText(":");
        pa2.setFont(new Font("Agency FB", 0, 24));
        pa2.setForeground(Color.black);
        pa2.setBounds(564, 125, 5, 30);
       
        mznpa2.setText("MZN");
        mznpa2.setFont(new Font("Agency FB", 0, 24));
        mznpa2.setForeground(Color.black);
        mznpa2.setBounds(584, 125, 28, 30);
        
        txtTotal_preco_artigo2.setText("0.0");
        txtTotal_preco_artigo2.setFont(new Font("Agency FB", 0, 24));
        txtTotal_preco_artigo2.setForeground(Color.black);
        txtTotal_preco_artigo2.setBounds(619, 125, 150, 30);
        
        lblTotal_preco_venda2.setFont(new Font("Agency FB", 0, 24));
        lblTotal_preco_venda2.setForeground(Color.black);
        lblTotal_preco_venda2.setBounds(394, 155, 160, 30);
        
        pv2.setText(":");
        pv2.setFont(new Font("Agency FB", 0, 24));
        pv2.setForeground(Color.black);
        pv2.setBounds(564, 155, 5, 30);
        
        mznpv2.setText("MZN");
        mznpv2.setFont(new Font("Agency FB", 0, 24));
        mznpv2.setForeground(Color.black);
        mznpv2.setBounds(584, 155, 28, 30);
        
        txtTotal_preco_venda2.setText("0.0");
        txtTotal_preco_venda2.setFont(new Font("Agency FB", 0, 24));
        txtTotal_preco_venda2.setForeground(Color.black);
        txtTotal_preco_venda2.setBounds(619, 155, 150, 30);
        
        lblTotal_lucro2.setFont(new Font("Agency FB", 0, 24));
        lblTotal_lucro2.setForeground(Color.black);
        lblTotal_lucro2.setBounds(394, 185, 160, 30);
        
        pl2.setText(":");
        pl2.setFont(new Font("Agency FB", 0, 24));
        pl2.setForeground(Color.black);
        pl2.setBounds(564, 185, 5, 30);
       
        mznl2.setText("MZN");
        mznl2.setFont(new Font("Agency FB", 0, 24));
        mznl2.setForeground(Color.black);
        mznl2.setBounds(584, 185, 28, 30);
        
        txtTotal_lucro2.setText("0.0");
        txtTotal_lucro2.setFont(new Font("Agency FB", 0, 24));
        txtTotal_lucro2.setForeground(Color.black);
        txtTotal_lucro2.setBounds(619, 185, 150, 30);
        
        sp2.setBounds(389, 60, 375, 160);
        sp2.setBorder(new MatteBorder(1, 1, 1, 1, Color.lightGray));   
        
        // L O J A _ 3
        
        lblLoja3.setFont(new Font("Algerian", 0, 25));
        lblLoja3.setForeground(Color.black);
        lblLoja3.setBounds(776, 60, 400, 30);
        
        lblTotal_quant_venda3.setFont(new Font("Agency FB", 0, 24));
        lblTotal_quant_venda3.setForeground(Color.black);
        lblTotal_quant_venda3.setBounds(776, 95, 160, 30);
        
        q3.setText(":");
        q3.setFont(new Font("Agency FB", 0, 24));
        q3.setForeground(Color.black);
        q3.setBounds(946, 95, 5, 30);
        
        txtTotal_quant_venda3.setText("0");
        txtTotal_quant_venda3.setFont(new Font("Agency FB", 0, 24));
        txtTotal_quant_venda3.setForeground(Color.black);
        txtTotal_quant_venda3.setBounds(966, 95, 150, 30);
        
        lblTotal_preco_artigo3.setFont(new Font("Agency FB", 0, 24));
        lblTotal_preco_artigo3.setForeground(Color.black);
        lblTotal_preco_artigo3.setBounds(776, 125, 160, 30);
        
        pa3.setText(":");
        pa3.setFont(new Font("Agency FB", 0, 24));
        pa3.setForeground(Color.black);
        pa3.setBounds(946, 125, 5, 30);
       
        mznpa3.setText("MZN");
        mznpa3.setFont(new Font("Agency FB", 0, 24));
        mznpa3.setForeground(Color.black);
        mznpa3.setBounds(966, 125, 28, 30);
       
        txtTotal_preco_artigo3.setText("0.0");
        txtTotal_preco_artigo3.setFont(new Font("Agency FB", 0, 24));
        txtTotal_preco_artigo3.setForeground(Color.black);
        txtTotal_preco_artigo3.setBounds(1001, 125, 150, 30);
        
        lblTotal_preco_venda3.setFont(new Font("Agency FB", 0, 24));
        lblTotal_preco_venda3.setForeground(Color.black);
        lblTotal_preco_venda3.setBounds(776, 155, 160, 30);
       
        pv3.setText(":");
        pv3.setFont(new Font("Agency FB", 0, 24));
        pv3.setForeground(Color.black);
        pv3.setBounds(946, 155, 5, 30);
        
        mznpv3.setText("MZN");
        mznpv3.setFont(new Font("Agency FB", 0, 24));
        mznpv3.setForeground(Color.black);
        mznpv3.setBounds(966, 155, 28, 30);
       
        txtTotal_preco_venda3.setText("0.0");
        txtTotal_preco_venda3.setFont(new Font("Agency FB", 0, 24));
        txtTotal_preco_venda3.setForeground(Color.black);
        txtTotal_preco_venda3.setBounds(1001, 155, 150, 30);
        
        lblTotal_lucro3.setFont(new Font("Agency FB", 0, 24));
        lblTotal_lucro3.setForeground(Color.black);
        lblTotal_lucro3.setBounds(776, 185, 160, 30);
       
        pl3.setText(":");
        pl3.setFont(new Font("Agency FB", 0, 24));
        pl3.setForeground(Color.black);
        pl3.setBounds(946, 185, 5, 30);
       
        mznl3.setText("MZN");
        mznl3.setFont(new Font("Agency FB", 0, 24));
        mznl3.setForeground(Color.black);
        mznl3.setBounds(966, 185, 28, 30);
        
        txtTotal_lucro3.setText("0.0");
        txtTotal_lucro3.setFont(new Font("Agency FB", 0, 24));
        txtTotal_lucro3.setForeground(Color.black);
        txtTotal_lucro3.setBounds(1001, 185, 150, 30);
        
        sp3.setBounds(771, 60, 375, 160);
        sp3.setBorder(new MatteBorder(1, 1, 1, 1, Color.lightGray));
        
        // L O J A _ J A R D I M
        
        lblLojaJardim.setFont(new Font("Algerian", 0, 25));
        lblLojaJardim.setForeground(Color.black);
        lblLojaJardim.setBounds(1158, 60, 400, 30);
        
        lblTotal_quant_vendaJardim.setFont(new Font("Agency FB", 0, 24));
        lblTotal_quant_vendaJardim.setForeground(Color.black);
        lblTotal_quant_vendaJardim.setBounds(1158, 95, 160, 30);
        
        qj.setText(":");
        qj.setFont(new Font("Agency FB", 0, 24));
        qj.setForeground(Color.black);
        qj.setBounds(1328, 95, 5, 30);
        
        txtTotal_quant_vendaJardim.setText("0");
        txtTotal_quant_vendaJardim.setFont(new Font("Agency FB", 0, 24));
        txtTotal_quant_vendaJardim.setForeground(Color.black);
        txtTotal_quant_vendaJardim.setBounds(1348, 95, 150, 30);
        
        lblTotal_preco_artigoJardim.setFont(new Font("Agency FB", 0, 24));
        lblTotal_preco_artigoJardim.setForeground(Color.black);
        lblTotal_preco_artigoJardim.setBounds(1158, 125, 160, 30);
        
        paj.setText(":");
        paj.setFont(new Font("Agency FB", 0, 24));
        paj.setForeground(Color.black);
        paj.setBounds(1328, 125, 5, 30);
        
        mznpaJardim.setText("MZN");
        mznpaJardim.setFont(new Font("Agency FB", 0, 24));
        mznpaJardim.setForeground(Color.black);
        mznpaJardim.setBounds(1348, 125, 28, 30);
        
        txtTotal_preco_artigoJardim.setText("0.0");
        txtTotal_preco_artigoJardim.setFont(new Font("Agency FB", 0, 24));
        txtTotal_preco_artigoJardim.setForeground(Color.black);
        txtTotal_preco_artigoJardim.setBounds(1383, 125, 150, 30);
        
        lblTotal_preco_vendaJardim.setFont(new Font("Agency FB", 0, 24));
        lblTotal_preco_vendaJardim.setForeground(Color.black);
        lblTotal_preco_vendaJardim.setBounds(1158, 155, 160, 30);
        
        pvj.setText(":");
        pvj.setFont(new Font("Agency FB", 0, 24));
        pvj.setForeground(Color.black);
        pvj.setBounds(1328, 155, 5, 30);
        
        mznpvJardim.setText("MZN");
        mznpvJardim.setFont(new Font("Agency FB", 0, 24));
        mznpvJardim.setForeground(Color.black);
        mznpvJardim.setBounds(1348, 155, 28, 30);
        
        txtTotal_preco_vendaJardim.setText("0.0");
        txtTotal_preco_vendaJardim.setFont(new Font("Agency FB", 0, 24));
        txtTotal_preco_vendaJardim.setForeground(Color.black);
        txtTotal_preco_vendaJardim.setBounds(1383, 155, 150, 30);
        
        lblTotal_lucroJardim.setFont(new Font("Agency FB", 0, 24));
        lblTotal_lucroJardim.setForeground(Color.black);
        lblTotal_lucroJardim.setBounds(1158, 185, 160, 30);
        
        plj.setText(":");
        plj.setFont(new Font("Agency FB", 0, 24));
        plj.setForeground(Color.black);
        plj.setBounds(1328, 185, 5, 30);
        
        mznlJardim.setText("MZN");
        mznlJardim.setFont(new Font("Agency FB", 0, 24));
        mznlJardim.setForeground(Color.black);
        mznlJardim.setBounds(1348, 185, 28, 30);
        
        txtTotal_lucroJardim.setText("0.0");
        txtTotal_lucroJardim.setFont(new Font("Agency FB", 0, 24));
        txtTotal_lucroJardim.setForeground(Color.black);
        txtTotal_lucroJardim.setBounds(1383, 185, 150, 30);
        
        spj.setBounds(1153, 60, 375, 160);
        spj.setBorder(new MatteBorder(1, 1, 1, 1, Color.lightGray));
        
        // T O T A L
        
        tituloTotal.setFont(new Font("Times New Roman", 1, 25));
        tituloTotal.setForeground(Color.black);
        tituloTotal.setBounds(723, 225, 400, 30);
                
        lblTotal_quant_vendaT.setFont(new Font("Agency FB", 1, 24));
        lblTotal_quant_vendaT.setForeground(Color.black);
        lblTotal_quant_vendaT.setBounds(12, 260, 160, 30);
        
        qT.setText(":");
        qT.setFont(new Font("Agency FB", 0, 24));
        qT.setForeground(Color.black);
        qT.setBounds(182, 260, 5, 30);
        
        txtTotal_quant_vendaT.setText("0");
        txtTotal_quant_vendaT.setFont(new Font("Agency FB", 0, 24));
        txtTotal_quant_vendaT.setForeground(Color.black);
        txtTotal_quant_vendaT.setBounds(202, 260, 150, 30);
        
        spqT.setBounds(7, 258, 375, 34);
        spqT.setBorder(new MatteBorder(1, 1, 1, 1, Color.lightGray));
        
        lblTotal_preco_artigoT.setFont(new Font("Agency FB", 1, 24));
        lblTotal_preco_artigoT.setForeground(Color.black);
        lblTotal_preco_artigoT.setBounds(394, 260, 160, 30);
        
        paT.setText(":");
        paT.setFont(new Font("Agency FB", 0, 24));
        paT.setForeground(Color.black);
        paT.setBounds(564, 260, 5, 30);
       
        mznpaT.setText("MZN");
        mznpaT.setFont(new Font("Agency FB", 0, 24));
        mznpaT.setForeground(Color.black);
        mznpaT.setBounds(584, 260, 28, 30);
        
        txtTotal_preco_artigoT.setText("0.0");
        txtTotal_preco_artigoT.setFont(new Font("Agency FB", 0, 24));
        txtTotal_preco_artigoT.setForeground(Color.black);
        txtTotal_preco_artigoT.setBounds(619, 260, 150, 30);
        
        spaT.setBounds(389, 258, 375, 34);
        spaT.setBorder(new MatteBorder(1, 1, 1, 1, Color.lightGray));   
        
        lblTotal_preco_vendaT.setFont(new Font("Agency FB", 1, 24));
        lblTotal_preco_vendaT.setForeground(Color.black);
        lblTotal_preco_vendaT.setBounds(776, 260, 160, 30);
       
        pvT.setText(":");
        pvT.setFont(new Font("Agency FB", 0, 24));
        pvT.setForeground(Color.black);
        pvT.setBounds(946, 260, 5, 30);
        
        mznpvT.setText("MZN");
        mznpvT.setFont(new Font("Agency FB", 0, 24));
        mznpvT.setForeground(Color.black);
        mznpvT.setBounds(966, 260, 28, 30);
       
        txtTotal_preco_vendaT.setText("0.0");
        txtTotal_preco_vendaT.setFont(new Font("Agency FB", 0, 24));
        txtTotal_preco_vendaT.setForeground(Color.black);
        txtTotal_preco_vendaT.setBounds(1001, 260, 150, 30);
        
        spvT.setBounds(771, 258, 375, 34);
        spvT.setBorder(new MatteBorder(1, 1, 1, 1, Color.lightGray));
        
        lblTotal_lucroT.setFont(new Font("Agency FB", 1, 24));
        lblTotal_lucroT.setForeground(Color.black);
        lblTotal_lucroT.setBounds(1158, 260, 160, 30);
        
        plT.setText(":");
        plT.setFont(new Font("Agency FB", 0, 24));
        plT.setForeground(Color.black);
        plT.setBounds(1328, 260, 5, 30);
        
        mznlT.setText("MZN");
        mznlT.setFont(new Font("Agency FB", 0, 24));
        mznlT.setForeground(Color.black);
        mznlT.setBounds(1348, 260, 28, 30);
        
        txtTotal_lucroT.setText("0.0");
        txtTotal_lucroT.setFont(new Font("Agency FB", 0, 24));
        txtTotal_lucroT.setForeground(Color.black);
        txtTotal_lucroT.setBounds(1383, 260, 150, 30);
        
        splT.setBounds(1153, 258, 375, 34);
        splT.setBorder(new MatteBorder(1, 1, 1, 1, Color.lightGray));
        
        // SEPARATOR
        SP.setBounds(7, 304, 1521, 5);
        SP.setBorder(new MatteBorder(1, 0, 0, 0, Color.gray));
        
        // P R O D U T O S
        
        tituloProdutos.setFont(new Font("Algerian", 1, 25));
        tituloProdutos.setForeground(Color.black);
        tituloProdutos.setBounds(1403, 309, 400, 30);
        
        // L O J A _ 1
        
        lblLoja1P.setFont(new Font("Algerian", 0, 25));
        lblLoja1P.setForeground(Color.black);
        lblLoja1P.setBounds(12, 364, 400, 30);
        
        lblQuant1.setText("0");
        lblQuant1.setFont(new Font("Agency FB", 0, 24));
        lblQuant1.setForeground(Color.black);
        lblQuant1.setBounds(202, 399, 150, 30);
                
        sq1.setBounds(7, 357, 375, 74);
        sq1.setBorder(new MatteBorder(1, 1, 1, 1, Color.lightGray));
        
        // L O J A _ 2
        
        lblLoja2P.setFont(new Font("Algerian", 0, 25));
        lblLoja2P.setForeground(Color.black);
        lblLoja2P.setBounds(394, 364, 400, 30);
        
        lblQuant2.setText("0");
        lblQuant2.setFont(new Font("Agency FB", 0, 24));
        lblQuant2.setForeground(Color.black);
        lblQuant2.setBounds(584, 399, 150, 30);
        
        sq2.setBounds(389, 357, 375, 74);
        sq2.setBorder(new MatteBorder(1, 1, 1, 1, Color.lightGray));   
        
        // L O J A _ 3
        
        lblLoja3P.setFont(new Font("Algerian", 0, 25));
        lblLoja3P.setForeground(Color.black);
        lblLoja3P.setBounds(776, 364, 400, 30);
        
        lblQuant3.setText("0");
        lblQuant3.setFont(new Font("Agency FB", 0, 24));
        lblQuant3.setForeground(Color.black);
        lblQuant3.setBounds(966, 399, 150, 30);
        
        sq3.setBounds(771, 357, 375, 74);
        sq3.setBorder(new MatteBorder(1, 1, 1, 1, Color.lightGray));
        
        // L O J A _ J A R D I M
        
        lblLojaJardimP.setFont(new Font("Algerian", 0, 25));
        lblLojaJardimP.setForeground(Color.black);
        lblLojaJardimP.setBounds(1158, 364, 400, 30);
        
        lblQuantJardim.setText("0");
        lblQuantJardim.setFont(new Font("Agency FB", 0, 24));
        lblQuantJardim.setForeground(Color.black);
        lblQuantJardim.setBounds(1348, 399, 150, 30);
        
        sqj.setBounds(1153, 357, 375, 74);
        sqj.setBorder(new MatteBorder(1, 1, 1, 1, Color.lightGray));
        
        // ARMAZÉM DA BEIRA
        
        lblLojaB.setFont(new Font("Algerian", 0, 25));
        lblLojaB.setForeground(Color.black);
        lblLojaB.setBounds(394, 445, 400, 30);
        
        lblQuantB.setText("0");
        lblQuantB.setFont(new Font("Agency FB", 0, 24));
        lblQuantB.setForeground(Color.black);
        lblQuantB.setBounds(584, 480, 150, 30);
        
        sqB.setBounds(389, 438, 375, 74);
        sqB.setBorder(new MatteBorder(1, 1, 1, 1, Color.lightGray));   
        
        // ARMAZÉM DO MAPUTO
        
        lblLojaM.setFont(new Font("Algerian", 0, 25));
        lblLojaM.setForeground(Color.black);
        lblLojaM.setBounds(776, 445, 400, 30);
        
        lblQuantM.setText("0");
        lblQuantM.setFont(new Font("Agency FB", 0, 24));
        lblQuantM.setForeground(Color.black);
        lblQuantM.setBounds(966, 480, 150, 30);
        
        sqM.setBounds(771, 438, 375, 74);
        sqM.setBorder(new MatteBorder(1, 1, 1, 1, Color.lightGray));
        
        // TOTAL PRODUTOS
        
        lblLojaTP.setText("TOTAL");
        lblLojaTP.setFont(new Font("Times New Roman", 1, 25));
        lblLojaTP.setForeground(Color.black);
        lblLojaTP.setBounds(394, 524, 400, 30);
        
        t.setText(":");
        t.setFont(new Font("Agency FB", 0, 24));
        t.setForeground(Color.black);
        t.setBounds(564, 524, 5, 30);
        
        lblQuantTP.setText("0");
        lblQuantTP.setFont(new Font("Agency FB", 0, 24));
        lblQuantTP.setForeground(Color.black);
        lblQuantTP.setBounds(776, 559, 180, 30);
        
        sqTP.setBounds(389, 517, 757, 74);
        sqTP.setBorder(new MatteBorder(1, 1, 1, 1, Color.lightGray));
        
        configTools();
        
        getContentPane().add(lblPesquisa);
        getContentPane().add(txtPesquisa);
        getContentPane().add(btnAtualizar_Tb);
        getContentPane().add(lblExempl);
        getContentPane().add(tituloVendas);
        
        getContentPane().add(lblLoja1);
        getContentPane().add(lblTotal_quant_venda1);
        getContentPane().add(q1);
        getContentPane().add(txtTotal_quant_venda1);
        getContentPane().add(lblTotal_preco_artigo1);
        getContentPane().add(pa1);
        getContentPane().add(mznpa1);
        getContentPane().add(txtTotal_preco_artigo1);
        getContentPane().add(lblTotal_preco_venda1);
        getContentPane().add(pv1);
        getContentPane().add(mznpv1);
        getContentPane().add(txtTotal_preco_venda1);
        getContentPane().add(lblTotal_lucro1);
        getContentPane().add(pl1);
        getContentPane().add(mznl1);
        getContentPane().add(txtTotal_lucro1);
        getContentPane().add(sp1);
        
        getContentPane().add(lblLoja2);
        getContentPane().add(lblTotal_quant_venda2);
        getContentPane().add(q2);
        getContentPane().add(txtTotal_quant_venda2);
        getContentPane().add(lblTotal_preco_artigo2);
        getContentPane().add(pa2);
        getContentPane().add(mznpa2);
        getContentPane().add(txtTotal_preco_artigo2);
        getContentPane().add(lblTotal_preco_venda2);
        getContentPane().add(pv2);
        getContentPane().add(mznpv2);
        getContentPane().add(txtTotal_preco_venda2);
        getContentPane().add(lblTotal_lucro2);
        getContentPane().add(pl2);
        getContentPane().add(mznl2);
        getContentPane().add(txtTotal_lucro2);
        getContentPane().add(sp2);
        
        getContentPane().add(lblLoja3);
        getContentPane().add(lblTotal_quant_venda3);
        getContentPane().add(q3);
        getContentPane().add(txtTotal_quant_venda3);
        getContentPane().add(lblTotal_preco_artigo3);
        getContentPane().add(pa3);
        getContentPane().add(mznpa3);
        getContentPane().add(txtTotal_preco_artigo3);
        getContentPane().add(lblTotal_preco_venda3);
        getContentPane().add(pv3);
        getContentPane().add(mznpv3);
        getContentPane().add(txtTotal_preco_venda3);
        getContentPane().add(lblTotal_lucro3);
        getContentPane().add(pl3);
        getContentPane().add(mznl3);
        getContentPane().add(txtTotal_lucro3);
        getContentPane().add(sp3);
        
        getContentPane().add(lblLojaJardim);
        getContentPane().add(lblTotal_quant_vendaJardim);
        getContentPane().add(qj);
        getContentPane().add(txtTotal_quant_vendaJardim);
        getContentPane().add(lblTotal_preco_artigoJardim);
        getContentPane().add(paj);
        getContentPane().add(mznpaJardim);
        getContentPane().add(txtTotal_preco_artigoJardim);
        getContentPane().add(lblTotal_preco_vendaJardim);
        getContentPane().add(pvj);
        getContentPane().add(mznpvJardim);
        getContentPane().add(txtTotal_preco_vendaJardim);
        getContentPane().add(lblTotal_lucroJardim);
        getContentPane().add(plj);
        getContentPane().add(mznlJardim);
        getContentPane().add(txtTotal_lucroJardim);
        getContentPane().add(spj);
        
        getContentPane().add(tituloTotal);
        getContentPane().add(lblTotal_quant_vendaT);
        getContentPane().add(qT);
        getContentPane().add(txtTotal_quant_vendaT);
        getContentPane().add(spqT);
        getContentPane().add(lblTotal_preco_artigoT);
        getContentPane().add(paT);
        getContentPane().add(mznpaT);
        getContentPane().add(txtTotal_preco_artigoT);
        getContentPane().add(spaT);
        getContentPane().add(lblTotal_preco_vendaT);
        getContentPane().add(pvT);
        getContentPane().add(mznpvT);
        getContentPane().add(txtTotal_preco_vendaT);
        getContentPane().add(spvT);
        getContentPane().add(lblTotal_lucroT);
        getContentPane().add(plT);
        getContentPane().add(mznlT);
        getContentPane().add(txtTotal_lucroT);
        getContentPane().add(splT);
        
        getContentPane().add(SP);
        
        getContentPane().add(tituloProdutos);
        getContentPane().add(lblLoja1P);
        getContentPane().add(lblQuant1);
        getContentPane().add(sq1);
        getContentPane().add(lblLoja2P);
        getContentPane().add(lblQuant2);
        getContentPane().add(sq2);
        getContentPane().add(lblLoja3P);
        getContentPane().add(lblQuant3);
        getContentPane().add(sq3);
        getContentPane().add(lblLojaJardimP);
        getContentPane().add(lblQuantJardim);
        getContentPane().add(sqj);
        
        getContentPane().add(lblLojaB);
        getContentPane().add(lblQuantB);
        getContentPane().add(sqB);
        getContentPane().add(lblLojaM);
        getContentPane().add(lblQuantM);
        getContentPane().add(sqM);
        
        getContentPane().add(lblLojaTP);
        getContentPane().add(t);
        getContentPane().add(lblQuantTP);
        getContentPane().add(sqTP);
    }
    
    private void configTools() {
        txtPesquisa.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                pesquisaDAO();
                if(txtPesquisa.getText().isEmpty()) {
                    tabelaDAO();
                }
            }
        });
    }
    
    private void tabelaDAO() {
        tabelaDAO_Venda_Loja1();
        tabelaDAO_Venda_Loja2();
        tabelaDAO_Venda_Loja3();
        tabelaDAO_Venda_LojaJardim();        
        calc_Vendas();
        
        tabelaDAO_Produto_Loja1();
        tabelaDAO_Produto_Loja2();
        tabelaDAO_Produto_Loja3();
        tabelaDAO_Produto_LojaJardim();
        tabelaDAO_ArmazemBeira();
        tabelaDAO_ArmazemMaputo();
        calc_Produtos();
    }
    
    private void pesquisaDAO() {
        pesquisa_Venda_Loja1();
        pesquisa_Venda_Loja2();
        pesquisa_Venda_Loja3();
        pesquisa_Venda_LojaJardim();
        calc_Pesquisa();
    }
    
    private void tabelaDAO_Venda_Loja1() {
        String sql = "select sum( QUANTIDADE ) as quant_venda1 from tbvenda_1 where DATA_HORA >= CURDATE()";
        
        try{
            ResultSet rs = conexao.createStatement().executeQuery(sql);
            
            while (rs.next()) {
                somavq1 = rs.getInt("quant_venda1");
            }          
            
            txtTotal_quant_venda1.setText(""+somavq1);
            
        } catch(SQLException err) {
            try {
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO NA tabelaDAO_Venda_Loja1() - TelaEstatistica - Quantidade..! \n"+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR IN tabelaDAO_Venda_Loja1() - TelaEstatistica - Quantity..! \n"+err, "Attention", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaEstatistica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        String sql1 = "select sum( PRECO_TOTAL_MZN ) as somatotalmzn_venda1 from tbvenda_1 where DATA_HORA >= CURDATE()";
        
        try{
            ResultSet rs = conexao.createStatement().executeQuery(sql1);
            
            while (rs.next()) {
                somava1 = rs.getDouble("somatotalmzn_venda1");
            }          
            
            txtTotal_preco_artigo1.setText(""+somava1);
            
        } catch(SQLException err) {
            try {
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO NA tabelaDAO_Venda_Loja1() - TelaEstatistica - Preco_Total_mzn ..! "+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR IN tabelaDAO_Venda_Loja() - TelaEstatistica - Preco_Total_mzn..! "+err, "Attention", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaEstatistica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        String sql2 = "select sum( PRECO_VENDA ) as somavendamzn_venda1 from tbvenda_1 where DATA_HORA >= CURDATE()";
        
        try{
            ResultSet rs = conexao.createStatement().executeQuery(sql2);
            
            while (rs.next()) {
                somavv1 = rs.getDouble("somavendamzn_venda1");
            }          
            
            txtTotal_preco_venda1.setText(""+somavv1);
            
        } catch(SQLException err) {
            try {
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO NA tabelaDAO_Venda_Loja1() - TelaEstatistica - Preco_Venda ..! "+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR IN tabelaDAO_Venda_Loja1() - TelaEstatistica - Preco_Venda..! "+err, "Attention", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaEstatistica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        String sql3 = "select sum( LUCRO ) as somalucro_venda1 from tbvenda_1 where DATA_HORA >= CURDATE()";
        
        try{
            ResultSet rs = conexao.createStatement().executeQuery(sql3);
            
            while (rs.next()) {
                somavl1 = rs.getDouble("somalucro_venda1");
            }          
            
            txtTotal_lucro1.setText(""+somavl1);
            
        } catch(SQLException err) {
            try {
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO NA tabelaDAO_Venda_Loja1() - TelaEstatistica - Lucro ..! "+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR IN tabelaDAO_Venda_Loja1() - TelaEstatistica - Balance..! "+err, "Attention", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaEstatistica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void tabelaDAO_Venda_Loja2() {
        String sql = "select sum( QUANTIDADE ) as quant_venda2 from tbvenda_2 where DATA_HORA >= CURDATE()";
        
        try{
            ResultSet rs = conexao.createStatement().executeQuery(sql);
            
            while (rs.next()) {
                somavq2 = rs.getInt("quant_venda2");
            }          
            
            txtTotal_quant_venda2.setText(""+somavq2);
            
        } catch(SQLException err) {
            try {
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO NA tabelaDAO_Venda_Loja2() - TelaEstatistica - Quantidade..! \n"+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR IN tabelaDAO_Venda_Loja2() - TelaEstatistica - Quantity..! \n"+err, "Attention", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaEstatistica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        String sql1 = "select sum( PRECO_TOTAL_MZN ) as somatotalmzn_venda2 from tbvenda_2 where DATA_HORA >= CURDATE()";
        
        try{
            ResultSet rs = conexao.createStatement().executeQuery(sql1);
            
            while (rs.next()) {
                somava2 = rs.getDouble("somatotalmzn_venda2");
            }          
            
            txtTotal_preco_artigo2.setText(""+somava2);
            
        } catch(SQLException err) {
            try {
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO NA tabelaDAO_Venda_Loja2() - TelaEstatistica - Preco_Total_mzn ..! "+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR IN tabelaDAO_Venda_Loja2() - TelaEstatistica - Preco_Total_mzn..! "+err, "Attention", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaEstatistica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        String sql2 = "select sum( PRECO_VENDA ) as somavendamzn_venda2 from tbvenda_2 where DATA_HORA >= CURDATE()";
        
        try{
            ResultSet rs = conexao.createStatement().executeQuery(sql2);
            
            while (rs.next()) {
                somavv2 = rs.getDouble("somavendamzn_venda2");
            }          
            
            txtTotal_preco_venda2.setText(""+somavv2);
            
        } catch(SQLException err) {
            try {
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO NA tabelaDAO_Venda_Loja2() - TelaEstatistica - Preco_Venda ..! "+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR IN tabelaDAO_Venda_Loja2() - TelaEstatistica - Preco_Venda..! "+err, "Attention", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaEstatistica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        String sql3 = "select sum( LUCRO ) as somalucro_venda2 from tbvenda_2 where DATA_HORA >= CURDATE()";
        
        try{
            ResultSet rs = conexao.createStatement().executeQuery(sql3);
            
            while (rs.next()) {
                somavl2 = rs.getDouble("somalucro_venda2");
            }          
            
            txtTotal_lucro2.setText(""+somavl2);
            
        } catch(SQLException err) {
            try {
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO NA tabelaDAO_Venda_Loja2() - TelaEstatistica - Lucro ..! "+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR IN tabelaDAO_Venda_Loja2() - TelaEstatistica - Balance..! "+err, "Attention", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaEstatistica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void tabelaDAO_Venda_Loja3() {
        String sql = "select sum( QUANTIDADE ) as quant_venda3 from tbvenda_3 where DATA_HORA >= CURDATE()";
        
        try{
            ResultSet rs = conexao.createStatement().executeQuery(sql);
            
            while (rs.next()) {
                somavq3 = rs.getInt("quant_venda3");
            }          
            
            txtTotal_quant_venda3.setText(""+somavq3);
            
        } catch(SQLException err) {
            try {
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO NA tabelaDAO_Venda_Loja3() - TelaEstatistica - Quantidade..! \n"+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR IN tabelaDAO_Venda_Loja3() - TelaEstatistica - Quantity..! \n"+err, "Attention", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaEstatistica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        String sql1 = "select sum( PRECO_TOTAL_MZN ) as somatotalmzn_venda3 from tbvenda_3 where DATA_HORA >= CURDATE()";
        
        try{
            ResultSet rs = conexao.createStatement().executeQuery(sql1);
            
            while (rs.next()) {
                somava3 = rs.getDouble("somatotalmzn_venda3");
            }          
            
            txtTotal_preco_artigo3.setText(""+somava3);
            
        } catch(SQLException err) {
            try {
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO NA tabelaDAO_Venda_Loja3() - TelaEstatistica - Preco_Total_mzn ..! "+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR IN tabelaDAO_Venda_Loja3() - TelaEstatistica - Preco_Total_mzn..! "+err, "Attention", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaEstatistica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        String sql2 = "select sum( PRECO_VENDA ) as somavendamzn_venda3 from tbvenda_3 where DATA_HORA >= CURDATE()";
        
        try{
            ResultSet rs = conexao.createStatement().executeQuery(sql2);
            
            while (rs.next()) {
                somavv3 = rs.getDouble("somavendamzn_venda3");
            }          
            
            txtTotal_preco_venda3.setText(""+somavv3);
            
        } catch(SQLException err) {
            try {
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO NA tabelaDAO_Venda_Loja3() - TelaEstatistica - Preco_Venda ..! "+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR IN tabelaDAO_Venda_Loja3() - TelaEstatistica - Preco_Venda..! "+err, "Attention", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaEstatistica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        String sql3 = "select sum( LUCRO ) as somalucro_venda3 from tbvenda_3 where DATA_HORA >= CURDATE()";
        
        try{
            ResultSet rs = conexao.createStatement().executeQuery(sql3);
            
            while (rs.next()) {
                somavl3 = rs.getDouble("somalucro_venda3");
            }          
            
            txtTotal_lucro3.setText(""+somavl3);
            
        } catch(SQLException err) {
            try {
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO NA tabelaDAO_Venda_Loja3() - TelaEstatistica - Lucro ..! "+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR IN tabelaDAO_Venda_Loja3() - TelaEstatistica - Balance..! "+err, "Attention", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaEstatistica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void tabelaDAO_Venda_LojaJardim() {
        String sql = "select sum( QUANTIDADE ) as quant_vendajardim from tbvenda_jardim where DATA_HORA >= CURDATE()";
        
        try{
            ResultSet rs = conexao.createStatement().executeQuery(sql);
            
            while (rs.next()) {
                somavqj = rs.getInt("quant_vendajardim");
            }          
            
            txtTotal_quant_vendaJardim.setText(""+somavqj);
            
        } catch(SQLException err) {
            try {
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO NA tabelaDAO_Venda_LojaJardim() - TelaEstatistica - Quantidade..! \n"+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR IN tabelaDAO_Venda_LojaJardim() - TelaEstatistica - Quantity..! \n"+err, "Attention", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaEstatistica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        String sql1 = "select sum( PRECO_TOTAL_MZN ) as somatotalmzn_vendajardim from tbvenda_jardim where DATA_HORA >= CURDATE()";
        
        try{
            ResultSet rs = conexao.createStatement().executeQuery(sql1);
            
            while (rs.next()) {
                somavaj = rs.getDouble("somatotalmzn_vendajardim");
            }          
            
            txtTotal_preco_artigoJardim.setText(""+somavaj);
            
        } catch(SQLException err) {
            try {
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO NA tabelaDAO_Venda_LojaJardim() - TelaEstatistica - Preco_Total_mzn ..! "+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR IN tabelaDAO_Venda_LojaJardim() - TelaEstatistica - Preco_Total_mzn..! "+err, "Attention", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaEstatistica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        String sql2 = "select sum( PRECO_VENDA ) as somavendamzn_vendajardim from tbvenda_jardim where DATA_HORA >= CURDATE()";
        
        try{
            ResultSet rs = conexao.createStatement().executeQuery(sql2);
            
            while (rs.next()) {
                somavvj = rs.getDouble("somavendamzn_vendajardim");
            }          
            
            txtTotal_preco_vendaJardim.setText(""+somavvj);
            
        } catch(SQLException err) {
            try {
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO NA tabelaDAO_Venda_LojaJardim() - TelaEstatistica - Preco_Venda ..! "+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR IN tabelaDAO_Venda_LojaJardim() - TelaEstatistica - Preco_Venda..! "+err, "Attention", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaEstatistica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        String sql3 = "select sum( LUCRO ) as somalucro_vendajardim from tbvenda_jardim where DATA_HORA >= CURDATE()";
        
        try{
            ResultSet rs = conexao.createStatement().executeQuery(sql3);
            
            while (rs.next()) {
                somavlj = rs.getDouble("somalucro_vendajardim");
            }          
            
            txtTotal_lucroJardim.setText(""+somavlj);
            
        } catch(SQLException err) {
            try {
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO NA tabelaDAO_Venda_LojaJardim() - TelaEstatistica - Lucro ..! "+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR IN tabelaDAO_Venda_LojaJardim() - TelaEstatistica - Balance..! "+err, "Attention", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaEstatistica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void calc_Vendas() {
        somavqt = (somavq1 + somavq2 + somavq3 + somavqj);
        txtTotal_quant_vendaT.setText(""+somavqt);
        
        somavat = (somava1 + somava2 + somava3 + somavaj);
        txtTotal_preco_artigoT.setText(""+somavat);
        
        somavvt = (somavv1 + somavv2 + somavv3 + somavvj);
        txtTotal_preco_vendaT.setText(""+somavvt);
        
        somavlt = (somavl1 + somavl2 + somavl3 + somavlj);
        txtTotal_lucroT.setText(""+somavlt);
    }
    
    private void tabelaDAO_Produto_Loja1() {
        String sql = "select sum( QUANTIDADE ) as quant_produto1 from tbloja_1";
        
        try{
            ResultSet rs = conexao.createStatement().executeQuery(sql);
            
            while (rs.next()) {
                somap1 = rs.getInt("quant_produto1");
            }          
            
            lblQuant1.setText(""+somap1);
            
        } catch(SQLException err) {
            try {
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO NA tabelaDAO_Produto_Loja1() - TelaEstatistica - Quantidade..! \n"+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR IN tabelaDAO_Produto_Loja1() - TelaEstatistica - Quantity..! \n"+err, "Attention", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaEstatistica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void tabelaDAO_Produto_Loja2() {
        String sql = "select sum( QUANTIDADE ) as quant_produto2 from tbloja_2";
        
        try{
            ResultSet rs = conexao.createStatement().executeQuery(sql);
            
            while (rs.next()) {
                somap2 = rs.getInt("quant_produto2");
            }          
            
            lblQuant2.setText(""+somap2);
            
        } catch(SQLException err) {
            try {
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO NA tabelaDAO_Produto_Loja2() - TelaEstatistica - Quantidade..! \n"+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR IN tabelaDAO_Produto_Loja2() - TelaEstatistica - Quantity..! \n"+err, "Attention", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaEstatistica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void tabelaDAO_Produto_Loja3() {
        String sql = "select sum( QUANTIDADE ) as quant_produto3 from tbloja_3";
        
        try{
            ResultSet rs = conexao.createStatement().executeQuery(sql);
            
            while (rs.next()) {
                somap3 = rs.getInt("quant_produto3");
            }          
            
            lblQuant3.setText(""+somap3);
            
        } catch(SQLException err) {
            try {
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO NA tabelaDAO_Produto_Loja3() - TelaEstatistica - Quantidade..! \n"+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR IN tabelaDAO_Produto_Loja3() - TelaEstatistica - Quantity..! \n"+err, "Attention", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaEstatistica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void tabelaDAO_Produto_LojaJardim() {
        String sql = "select sum( QUANTIDADE ) as quant_produtojardim from tbloja_jardim";
        
        try{
            ResultSet rs = conexao.createStatement().executeQuery(sql);
            
            while (rs.next()) {
                somapj = rs.getInt("quant_produtojardim");
            }          
            
            lblQuantJardim.setText(""+somapj);
            
        } catch(SQLException err) {
            try {
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO NA tabelaDAO_Produto_LojaJardim() - TelaEstatistica - Quantidade..! \n"+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR IN tabelaDAO_Produto_LojaJardim() - TelaEstatistica - Quantity..! \n"+err, "Attention", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaEstatistica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void tabelaDAO_ArmazemBeira() {
        String sql = "select sum( QUANTIDADE ) as quant_armazembeira from tbarmazem_beira";
        
        try{
            ResultSet rs = conexao.createStatement().executeQuery(sql);
            
            while (rs.next()) {
                somaab = rs.getInt("quant_armazembeira");
            }          
            
            lblQuantB.setText(""+somaab);
            
        } catch(SQLException err) {
            try {
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO NA tabelaDAO_Produto_LojaJardim() - TelaEstatistica - Quantidade..! \n"+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR IN tabelaDAO_Produto_LojaJardim() - TelaEstatistica - Quantity..! \n"+err, "Attention", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaEstatistica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void tabelaDAO_ArmazemMaputo() {
        String sql = "select sum( QUANTIDADE ) as quant_armazemmaputo from tbarmazem_maputo";
        
        try{
            ResultSet rs = conexao.createStatement().executeQuery(sql);
            
            while (rs.next()) {
                somaam = rs.getInt("quant_armazemmaputo");
            }          
            
            lblQuantM.setText(""+somaam);
            
        } catch(SQLException err) {
            try {
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO NA tabelaDAO_Produto_LojaJardim() - TelaEstatistica - Quantidade..! \n"+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR IN tabelaDAO_Produto_LojaJardim() - TelaEstatistica - Quantity..! \n"+err, "Attention", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaEstatistica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void calc_Produtos() {
        somapt = (somap1 + somap2 + somap3 + somapj + somaab + somaam);
        lblQuantTP.setText(""+somapt);
    }
    
    private void pesquisa_Venda_Loja1() {
        String sql = "select sum( QUANTIDADE ) as quant_venda1 from tbvenda_1 where DATA_HORA like ?";
        
        try{
            PreparedStatement pst = conexao.prepareStatement(sql);

            pst.setString(1, "%" + txtPesquisa.getText() + "%");

            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                somavq1 = rs.getInt("quant_venda1");
            }          
            
            txtTotal_quant_venda1.setText(""+somavq1);
            
        } catch(SQLException err) {
            try {
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO NA Pesquisa_Venda_Loja1() - TelaEstatistica - Quantidade..! \n"+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR IN Pesquisa_Venda_Loja1() - TelaEstatistica - Quantity..! \n"+err, "Attention", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaEstatistica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        String sql1 = "select sum( PRECO_TOTAL_MZN ) as somatotalmzn_venda1 from tbvenda_1 where DATA_HORA like ?";
        
        try{
            PreparedStatement pst = conexao.prepareStatement(sql1);

            pst.setString(1, "%" + txtPesquisa.getText() + "%");

            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                somava1 = rs.getDouble("somatotalmzn_venda1");
            }          
            
            txtTotal_preco_artigo1.setText(""+somava1);
            
        } catch(SQLException err) {
            try {
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO NA Pesquisa_Venda_Loja1() - TelaEstatistica - Preco_Total_mzn ..! "+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR IN Pesquisa_Venda_Loja() - TelaEstatistica - Preco_Total_mzn..! "+err, "Attention", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaEstatistica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        String sql2 = "select sum( PRECO_VENDA ) as somavendamzn_venda1 from tbvenda_1 where DATA_HORA like ?";
        
        try{
            PreparedStatement pst = conexao.prepareStatement(sql2);

            pst.setString(1, "%" + txtPesquisa.getText() + "%");

            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                somavv1 = rs.getDouble("somavendamzn_venda1");
            }          
            
            txtTotal_preco_venda1.setText(""+somavv1);
            
        } catch(SQLException err) {
            try {
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO NA Pesquisa_Venda_Loja1() - TelaEstatistica - Preco_Venda ..! "+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR IN Pesquisa_Venda_Loja1() - TelaEstatistica - Preco_Venda..! "+err, "Attention", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaEstatistica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        String sql3 = "select sum( LUCRO ) as somalucro_venda1 from tbvenda_1 where DATA_HORA like ?";
        
        try{
            PreparedStatement pst = conexao.prepareStatement(sql3);

            pst.setString(1, "%" + txtPesquisa.getText() + "%");

            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                somavl1 = rs.getDouble("somalucro_venda1");
            }          
            
            txtTotal_lucro1.setText(""+somavl1);
            
        } catch(SQLException err) {
            try {
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO NA Pesquisa_Venda_Loja1() - TelaEstatistica - Lucro ..! "+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR IN Pesquisa_Venda_Loja1() - TelaEstatistica - Balance..! "+err, "Attention", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaEstatistica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void pesquisa_Venda_Loja2() {
        String sql = "select sum( QUANTIDADE ) as quant_venda2 from tbvenda_2 where DATA_HORA like ?";
        
        try{
            PreparedStatement pst = conexao.prepareStatement(sql);

            pst.setString(1, "%" + txtPesquisa.getText() + "%");

            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                somavq2 = rs.getInt("quant_venda2");
            }          
            
            txtTotal_quant_venda2.setText(""+somavq2);
            
        } catch(SQLException err) {
            try {
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO NA pesquisa_Venda_Loja2() - TelaEstatistica - Quantidade..! \n"+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR IN pesquisa_Venda_Loja2() - TelaEstatistica - Quantity..! \n"+err, "Attention", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaEstatistica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        String sql1 = "select sum( PRECO_TOTAL_MZN ) as somatotalmzn_venda2 from tbvenda_2 where DATA_HORA like ?";
        
        try{
            PreparedStatement pst = conexao.prepareStatement(sql1);

            pst.setString(1, "%" + txtPesquisa.getText() + "%");

            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                somava2 = rs.getDouble("somatotalmzn_venda2");
            }          
            
            txtTotal_preco_artigo2.setText(""+somava2);
            
        } catch(SQLException err) {
            try {
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO NA pesquisa_Venda_Loja2() - TelaEstatistica - Preco_Total_mzn ..! "+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR IN pesquisa_Venda_Loja2() - TelaEstatistica - Preco_Total_mzn..! "+err, "Attention", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaEstatistica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        String sql2 = "select sum( PRECO_VENDA ) as somavendamzn_venda2 from tbvenda_2 where DATA_HORA like ?";
        
        try{
            PreparedStatement pst = conexao.prepareStatement(sql2);

            pst.setString(1, "%" + txtPesquisa.getText() + "%");

            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                somavv2 = rs.getDouble("somavendamzn_venda2");
            }          
            
            txtTotal_preco_venda2.setText(""+somavv2);
            
        } catch(SQLException err) {
            try {
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO NA pesquisa_Venda_Loja2() - TelaEstatistica - Preco_Venda ..! "+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR IN pesquisa_Venda_Loja2() - TelaEstatistica - Preco_Venda..! "+err, "Attention", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaEstatistica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        String sql3 = "select sum( LUCRO ) as somalucro_venda2 from tbvenda_2 where DATA_HORA like ?";
        
        try{
            PreparedStatement pst = conexao.prepareStatement(sql3);

            pst.setString(1, "%" + txtPesquisa.getText() + "%");

            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                somavl2 = rs.getDouble("somalucro_venda2");
            }          
            
            txtTotal_lucro2.setText(""+somavl2);
            
        } catch(SQLException err) {
            try {
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO NA pesquisa_Venda_Loja2() - TelaEstatistica - Lucro ..! "+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR IN pesquisa_Venda_Loja2() - TelaEstatistica - Balance..! "+err, "Attention", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaEstatistica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void pesquisa_Venda_Loja3() {
        String sql = "select sum( QUANTIDADE ) as quant_venda3 from tbvenda_3 where DATA_HORA like ?";
        
        try{
            PreparedStatement pst = conexao.prepareStatement(sql);

            pst.setString(1, "%" + txtPesquisa.getText() + "%");

            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                somavq3 = rs.getInt("quant_venda3");
            }          
            
            txtTotal_quant_venda3.setText(""+somavq3);
            
        } catch(SQLException err) {
            try {
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO NA pesquisa_Venda_Loja3() - TelaEstatistica - Quantidade..! \n"+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR IN pesquisa_Venda_Loja3() - TelaEstatistica - Quantity..! \n"+err, "Attention", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaEstatistica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        String sql1 = "select sum( PRECO_TOTAL_MZN ) as somatotalmzn_venda3 from tbvenda_3 where DATA_HORA like ?";
        
        try{
            PreparedStatement pst = conexao.prepareStatement(sql1);

            pst.setString(1, "%" + txtPesquisa.getText() + "%");

            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                somava3 = rs.getDouble("somatotalmzn_venda3");
            }          
            
            txtTotal_preco_artigo3.setText(""+somava3);
            
        } catch(SQLException err) {
            try {
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO NA pesquisa_Venda_Loja3() - TelaEstatistica - Preco_Total_mzn ..! "+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR IN pesquisa_Venda_Loja3() - TelaEstatistica - Preco_Total_mzn..! "+err, "Attention", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaEstatistica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        String sql2 = "select sum( PRECO_VENDA ) as somavendamzn_venda3 from tbvenda_3 where DATA_HORA like ?";
        
        try{
            PreparedStatement pst = conexao.prepareStatement(sql2);

            pst.setString(1, "%" + txtPesquisa.getText() + "%");

            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                somavv3 = rs.getDouble("somavendamzn_venda3");
            }          
            
            txtTotal_preco_venda3.setText(""+somavv3);
            
        } catch(SQLException err) {
            try {
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO NA pesquisa_Venda_Loja3() - TelaEstatistica - Preco_Venda ..! "+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR IN pesquisa_Venda_Loja3() - TelaEstatistica - Preco_Venda..! "+err, "Attention", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaEstatistica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        String sql3 = "select sum( LUCRO ) as somalucro_venda3 from tbvenda_3 where DATA_HORA like ?";
        
        try{
            PreparedStatement pst = conexao.prepareStatement(sql3);

            pst.setString(1, "%" + txtPesquisa.getText() + "%");

            ResultSet rs = pst.executeQuery();
            
            while (rs.next()) {
                somavl3 = rs.getDouble("somalucro_venda3");
            }          
            
            txtTotal_lucro3.setText(""+somavl3);
            
        } catch(SQLException err) {
            try {
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO NA pesquisa_Venda_Loja3() - TelaEstatistica - Lucro ..! "+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR IN pesquisa_Venda_Loja3() - TelaEstatistica - Balance..! "+err, "Attention", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaEstatistica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void pesquisa_Venda_LojaJardim() {
        String sql = "select sum( QUANTIDADE ) as quant_vendajardim from tbvenda_jardim where DATA_HORA like ?";
        
        try{
            PreparedStatement pst = conexao.prepareStatement(sql);

            pst.setString(1, "%" + txtPesquisa.getText() + "%");

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                somavqj = rs.getInt("quant_vendajardim");
            }          
            
            txtTotal_quant_vendaJardim.setText(""+somavqj);
            
        } catch(SQLException err) {
            try {
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO NA pesquisa_Venda_LojaJardim() - TelaEstatistica - Quantidade..! \n"+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR IN pesquisa_Venda_LojaJardim() - TelaEstatistica - Quantity..! \n"+err, "Attention", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaEstatistica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        String sql1 = "select sum( PRECO_TOTAL_MZN ) as somatotalmzn_vendajardim from tbvenda_jardim where DATA_HORA like ?";
        
        try{
            PreparedStatement pst = conexao.prepareStatement(sql1);

            pst.setString(1, "%" + txtPesquisa.getText() + "%");

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                somavaj = rs.getDouble("somatotalmzn_vendajardim");
            }          
            
            txtTotal_preco_artigoJardim.setText(""+somavaj);
            
        } catch(SQLException err) {
            try {
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO NA pesquisa_Venda_LojaJardim() - TelaEstatistica - Preco_Total_mzn ..! "+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR IN pesquisa_Venda_LojaJardim() - TelaEstatistica - Preco_Total_mzn..! "+err, "Attention", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaEstatistica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        String sql2 = "select sum( PRECO_VENDA ) as somavendamzn_vendajardim from tbvenda_jardim where DATA_HORA like ?";
        
        try{
            PreparedStatement pst = conexao.prepareStatement(sql2);

            pst.setString(1, "%" + txtPesquisa.getText() + "%");

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                somavvj = rs.getDouble("somavendamzn_vendajardim");
            }          
            
            txtTotal_preco_vendaJardim.setText(""+somavvj);
            
        } catch(SQLException err) {
            try {
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO NA pesquisa_Venda_LojaJardim() - TelaEstatistica - Preco_Venda ..! "+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR IN pesquisa_Venda_LojaJardim() - TelaEstatistica - Preco_Venda..! "+err, "Attention", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaEstatistica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        String sql3 = "select sum( LUCRO ) as somalucro_vendajardim from tbvenda_jardim where DATA_HORA like ?";
        
        try{
            PreparedStatement pst = conexao.prepareStatement(sql3);

            pst.setString(1, "%" + txtPesquisa.getText() + "%");

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                somavlj = rs.getDouble("somalucro_vendajardim");
            }          
            
            txtTotal_lucroJardim.setText(""+somavlj);
            
        } catch(SQLException err) {
            try {
                this.conexao.close();
                if(Idiomas.getPort() == true) {
                    JOptionPane.showMessageDialog(null, "ERRO NA pesquisa_Venda_LojaJardim() - TelaEstatistica - Lucro ..! "+err, "Atenção", 0);
                } else {
                    JOptionPane.showMessageDialog(null, "ERROR IN pesquisa_Venda_LojaJardim() - TelaEstatistica - Balance..! "+err, "Attention", 0);
                }            
            } catch (SQLException ex) {
                Logger.getLogger(TelaEstatistica.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void calc_Pesquisa() {
        somavqt = (somavq1 + somavq2 + somavq3 + somavqj);
        txtTotal_quant_vendaT.setText(""+somavqt);
        
        somavat = (somava1 + somava2 + somava3 + somavaj);
        txtTotal_preco_artigoT.setText(""+somavat);
        
        somavvt = (somavv1 + somavv2 + somavv3 + somavvj);
        txtTotal_preco_vendaT.setText(""+somavvt);
        
        somavlt = (somavl1 + somavl2 + somavl3 + somavlj);
        txtTotal_lucroT.setText(""+somavlt);
    }
    
    @Override
    public void configPort() {
        lblExempl.setText("Pesquisa por Data : [Mês-Dia, (02-21)]");
        tituloVendas.setText("VENDAS");
        lblLoja1.setText("#LOJA_1");
        lblTotal_quant_venda1.setText("Vendas");
        lblTotal_preco_artigo1.setText("Preço total de artigo"); 
         lblTotal_preco_venda1.setText("Preço total de venda ");
        lblTotal_lucro1.setText("Lucro");
        lblLoja2.setText("#LOJA_2");
        lblTotal_quant_venda2.setText("Vendas");
        lblTotal_preco_artigo2.setText("Preço total de artigo");
        lblTotal_preco_venda2.setText("Preço total de venda ");        
        lblTotal_lucro2.setText("Lucro");        
        lblLoja3.setText("#LOJA_3");
        lblTotal_quant_venda3.setText("Vendas");
        lblTotal_preco_artigo3.setText("Preço total de artigo");
        lblTotal_preco_venda3.setText("Preço total de venda ");       
        lblTotal_lucro3.setText("Lucro");
        lblLojaJardim.setText("#LOJA_JARDIM");
        lblTotal_quant_vendaJardim.setText("Vendas");
        lblTotal_preco_artigoJardim.setText("Preço total de artigo");
        lblTotal_preco_vendaJardim.setText("Preço total de venda ");        
        lblTotal_lucroJardim.setText("Lucro");
        tituloTotal.setText("TOTAL");      
        lblTotal_quant_vendaT.setText("Vendas");
        lblTotal_preco_artigoT.setText("Preço total de artigo");        
        lblTotal_preco_vendaT.setText("Preço total de venda ");        
        lblTotal_lucroT.setText("Lucro");
        tituloProdutos.setText("PRODUTOS");
        lblLoja1P.setText("#LOJA_1");
        lblLoja2P.setText("#LOJA_2");        
        lblLoja3P.setText("#LOJA_3");
        lblLojaJardimP.setText("#LOJA_JARDIM");
        lblLojaB.setText("#ARMAZÉM DA BEIRA");        
        lblLojaM.setText("#ARMAZÉM DO MAPUTO");
    }

    @Override
    public void configEng() {
        lblExempl.setText("Search by Date: [Month-Day, (02-21)]");
        tituloVendas.setText("SALES");
        lblLoja1.setText("#SHOP_1");
        lblTotal_quant_venda1.setText("Sellings");
        lblTotal_preco_artigo1.setText("Total price of item");
 lblTotal_preco_venda1.setText("Total selling price");        
        lblTotal_lucro1.setText("Balance");
        lblLoja2.setText("#SHOP_2");
        lblTotal_quant_venda2.setText("Sellings");
        lblTotal_preco_artigo2.setText("Total price of item");
        lblTotal_preco_venda2.setText("Total selling price");        
        lblTotal_lucro2.setText("Balance");        
        lblLoja3.setText("#SHOP_3");
        lblTotal_quant_venda3.setText("Sellings");
        lblTotal_preco_artigo3.setText("Total price of item");
        lblTotal_preco_venda3.setText("Total selling price");       
        lblTotal_lucro3.setText("Balance");
        lblLojaJardim.setText("#JARDIM_SHOP");
        lblTotal_quant_vendaJardim.setText("Sellings");
        lblTotal_preco_artigoJardim.setText("Total price of item");
        lblTotal_preco_vendaJardim.setText("Total selling price");        
        lblTotal_lucroJardim.setText("Balance");
        tituloTotal.setText("TOTAL");     
        lblTotal_quant_vendaT.setText("Sellings");
        lblTotal_preco_artigoT.setText("Total price of item");        
        lblTotal_preco_vendaT.setText("Total selling price");        
        lblTotal_lucroT.setText("Balance");
        tituloProdutos.setText("PRODUCTS");
        lblLoja1P.setText("#SHOP_1");
        lblLoja2P.setText("#SHOP_2");        
        lblLoja3P.setText("#SHOP_3");
        lblLojaJardimP.setText("#JARDIM_SHOP");
        lblLojaB.setText("#BEIRA WAREHOUSE");        
        lblLojaM.setText("#MAPUTO WAREHOUSE");
    }
}
