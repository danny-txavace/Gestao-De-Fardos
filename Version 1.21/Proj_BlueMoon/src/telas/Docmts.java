package telas;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.border.MatteBorder;

/**
 * @author Ramadan ismaeL
 */

public class Docmts extends JFrame {
    private JSeparator separator;
    private JLabel a1, a2, a3, a4;
    private JLabel b1, b2;
    private JLabel c1, c2, c3, c4, c5;
    private JLabel d1, d2, d3, d4, d5;
    private JLabel e1, e2, e3, e4, e5, e6;
    private JLabel f1, f2, f3, f4, f5;
    private JLabel g1, g2, g3, g4, g5;
    private JLabel t1, t2, t3, t4, t5, t6, t7, t8;
    private JLabel r1, r2, r3;
    
    public Docmts() {
        Janela();
        ConfigView();
    }
    
    private void Janela() {
        setTitle("Docs");
        setSize(1200, 633);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(false);
    }
    
    private void ConfigView() {
        setLayout(null);
        
        separator = new JSeparator();
        
        t1 = new JLabel();
        t2 = new JLabel();
        t3 = new JLabel();
        t4 = new JLabel();
        t5 = new JLabel();
        t6 = new JLabel();
        t7 = new JLabel();
        t8 = new JLabel();
        
        a1 = new JLabel();
        a2 = new JLabel();
        a3 = new JLabel();
        a4 = new JLabel();
        
        b1 = new JLabel();
        b2 = new JLabel();
        
        c1 = new JLabel();
        c2 = new JLabel();
        c3 = new JLabel();
        c4 = new JLabel();
        c5 = new JLabel();
        
        d1 = new JLabel();
        d2 = new JLabel();
        d3 = new JLabel();
        d4 = new JLabel();
        d5 = new JLabel();
        
        e1 = new JLabel();
        e2 = new JLabel();
        e3 = new JLabel();
        e4 = new JLabel();
        e5 = new JLabel();
        e6 = new JLabel();
        
        f1 = new JLabel();
        f2 = new JLabel();
        f3 = new JLabel();
        f4 = new JLabel();
        f5 = new JLabel();
        
        g1 = new JLabel();
        g2 = new JLabel();
        g3 = new JLabel();
        g4 = new JLabel();
        g5 = new JLabel();
        
        r1 = new JLabel();
        r2 = new JLabel();
        r3 = new JLabel();
        
        t1.setText("O SOFTWARE DA BLUE MOON TRADING COMPANY");
        t1.setFont(new Font("Times New Roman", 1, 23));
        t1.setForeground(Color.BLACK);
        t1.setBounds(1, 5, 600, 21);
        
        t2.setText(">> Introdução");
        t2.setFont(new Font("Times New Roman", 1, 18));
        t2.setForeground(Color.BLACK);
        t2.setBounds(1, 36, 613, 20);
        
        a1.setText("No agitado mundo do comércio, o gerenciamento eficiente de estoque é fundamental para");
        a1.setFont(new Font("Times New Roman", 0, 16));
        a1.setForeground(Color.BLACK);
        a1.setBounds(1, 56, 590, 20);
        
        a2.setText("o sucesso. Bem-vindo ao documentário que revela a inovadora solução de software");
        a2.setFont(new Font("Times New Roman", 0, 16));
        a2.setForeground(Color.BLACK);
        a2.setBounds(1, 76, 590, 20);
        
        a3.setText("desenvolvido por Ramadan Ibraimo Ismael. Mantenha-se conectado e actualizado");
        a3.setFont(new Font("Times New Roman", 0, 16));
        a3.setForeground(Color.BLACK);
        a3.setBounds(1, 96, 590, 20);
        
        a4.setText("seguindo a página do facebook como titular, Ismael Computer Pro, respectivamente.");
        a4.setFont(new Font("Times New Roman", 0, 16));
        a4.setForeground(Color.BLACK);
        a4.setBounds(1, 116, 590, 20);
        
        t3.setText(">> Capítulo 1: Gênese");
        t3.setFont(new Font("Times New Roman", 1, 18));
        t3.setForeground(Color.BLACK);
        t3.setBounds(1, 141, 613, 20);
        
        b1.setText("A gênese do software da Blue Moon Trading Company surgiu da necessidade premente de");
        b1.setFont(new Font("Times New Roman", 0, 16));
        b1.setForeground(Color.BLACK);
        b1.setBounds(1, 161, 590, 20);
        
        b2.setText("um gerenciamento e controle de estoque.");
        b2.setFont(new Font("Times New Roman", 0, 16));
        b2.setForeground(Color.BLACK);
        b2.setBounds(1, 181, 590, 20);
        
        t4.setText(">> Capítulo 2: Funcionalidade");
        t4.setFont(new Font("Times New Roman", 1, 18));
        t4.setForeground(Color.BLACK);
        t4.setBounds(1, 206, 613, 20);
        
        c1.setText("No cerne, o software da Blue Moon Trading Company é um farol de gerenciamento e");
        c1.setFont(new Font("Times New Roman", 0, 16));
        c1.setForeground(Color.BLACK);
        c1.setBounds(1, 226, 590, 20);
        
        c2.setText("controle de estoque. Oferecendo uma variedade de recursos, desde o registro de produtos até");
        c2.setFont(new Font("Times New Roman", 0, 16));
        c2.setForeground(Color.BLACK);
        c2.setBounds(1, 246, 590, 20);
        
        c3.setText("atualizações e transferências sem interrupções, o software capacita a navegação, i.e,");
        c3.setFont(new Font("Times New Roman", 0, 16));
        c3.setForeground(Color.BLACK);
        c3.setBounds(1, 266, 590, 20);
        
        c4.setText("a pesquisa por seus estoques com facilidade. E as pesquisas podem ser feitas por :");
        c4.setFont(new Font("Times New Roman", 0, 16));
        c4.setForeground(Color.BLACK);
        c4.setBounds(1, 286, 590, 20);
        
        c5.setText("Nome primitivo, alcunha, números de celulares, lojas, perfis, data, hora, marca e categoria.");
        c5.setFont(new Font("Times New Roman", 0, 16));
        c5.setForeground(Color.BLACK);
        c5.setBounds(1, 306, 590, 20);
        
        t5.setText(">> Capítulo 3: Segurança");
        t5.setFont(new Font("Times New Roman", 1, 18));
        t5.setForeground(Color.BLACK);
        t5.setBounds(1, 331, 613, 20);
        
        d1.setText("Em uma era marcada por violações de dados e ameaças cibernéticas, proteger informações");
        d1.setFont(new Font("Times New Roman", 0, 16));
        d1.setForeground(Color.BLACK);
        d1.setBounds(1, 351, 590, 20);
        
        d2.setText("comerciais sensíveis é fundamental. O software da Blue Moon Trading Company é uma");
        d2.setFont(new Font("Times New Roman", 0, 16));
        d2.setForeground(Color.BLACK);
        d2.setBounds(1, 371, 590, 20);
        
        d3.setText("fortaleza de segurança, implementando medidas robustas para proteger dados valiosos.");
        d3.setFont(new Font("Times New Roman", 0, 16));
        d3.setForeground(Color.BLACK);
        d3.setBounds(1, 391, 590, 20);
       
        d4.setText("Por meio de protocolos de criptografia e autenticação, garante a confidencialidade e");
        d4.setFont(new Font("Times New Roman", 0, 16));
        d4.setForeground(Color.BLACK);
        d4.setBounds(1, 411, 590, 20);
        
        d5.setText("integridade das informações comerciais vitais.");
        d5.setFont(new Font("Times New Roman", 0, 16));
        d5.setForeground(Color.BLACK);
        d5.setBounds(1, 431, 590, 20);
        
        t6.setText(">> Capítulo 4: Experiência do Usuário");
        t6.setFont(new Font("Times New Roman", 1, 18));
        t6.setForeground(Color.BLACK);
        t6.setBounds(1, 456, 613, 20);
        
        e1.setText("Navegar pelas complexidades do gerenciamento e controle de estoque muitas vezes pode");
        e1.setFont(new Font("Times New Roman", 0, 16));
        e1.setForeground(Color.BLACK);
        e1.setBounds(1, 476, 590, 20);
        
        e2.setText("ser intimidante. No entanto, o software da Blue Moon Trading Company desenvolvido por");
        e2.setFont(new Font("Times New Roman", 0, 16));
        e2.setForeground(Color.BLACK);
        e2.setBounds(1, 496, 590, 20);
        
        e3.setText("Ramadan Ibraimo Ismael, prioriza a experiência do usuário, oferecendo uma interface");
        e3.setFont(new Font("Times New Roman", 0, 16));
        e3.setForeground(Color.BLACK);
        e3.setBounds(1, 516, 590, 20);
       
        e4.setText("intuitiva projetada para interação perfeita. Seja registrando produtos, atualizando níveis");
        e4.setFont(new Font("Times New Roman", 0, 16));
        e4.setForeground(Color.BLACK);
        e4.setBounds(1, 536, 590, 20);
        
        e5.setText("de estoques ou solucionando problemas, os usuários se sentem capacitados pelo design");
        e5.setFont(new Font("Times New Roman", 0, 16));
        e5.setForeground(Color.BLACK);
        e5.setBounds(1, 556, 590, 20);
        
        e6.setText("amigável do software.");
        e6.setFont(new Font("Times New Roman", 0, 16));
        e6.setForeground(Color.BLACK);
        e6.setBounds(1, 576, 590, 20);
        
        separator.setBounds(592, 31, 1, 600);
        separator.setBorder(new MatteBorder(0, 0, 0, 1, Color.LIGHT_GRAY));
        
        t7.setText(">> Capítulo 5: Depoimentos");
        t7.setFont(new Font("Times New Roman", 1, 18));
        t7.setForeground(Color.BLACK);
        t7.setBounds(594, 36, 613, 20);
        
        f1.setText("O verdadeiro testemunho da eficácia do software da Blue Moon Trading Company reside nos");
        f1.setFont(new Font("Times New Roman", 0, 16));
        f1.setForeground(Color.BLACK);
        f1.setBounds(594, 56, 590, 20);
        
        f2.setText("depoimentos de seus usuários. De pequenas empresas a corporações multinacionais,");
        f2.setFont(new Font("Times New Roman", 0, 16));
        f2.setForeground(Color.BLACK);
        f2.setBounds(594, 76, 590, 20);
        
        f3.setText("empresas como Loja 1, Loja 2, Loja 3 & Loja Jardim que abraçaram o software como um");
        f3.setFont(new Font("Times New Roman", 0, 16));
        f3.setForeground(Color.BLACK);
        f3.setBounds(594, 96, 590, 20);
      
        f4.setText("catalisador para crescimento e eficiência. Os depoimentos ecoam o sentimento de");
        f4.setFont(new Font("Times New Roman", 0, 16));
        f4.setForeground(Color.BLACK);
        f4.setBounds(594, 116, 590, 20);
       
        f5.setText("produtividade aprimorada, custos operacionais reduzidos e conveniência incomparável.");
        f5.setFont(new Font("Times New Roman", 0, 16));
        f5.setForeground(Color.BLACK);
        f5.setBounds(594, 136, 590, 20);
        
        t8.setText(">> Conclusão");
        t8.setFont(new Font("Times New Roman", 1, 18));
        t8.setForeground(Color.BLACK);
        t8.setBounds(594, 161, 613, 20);
        
        g1.setText("No cenário em constante evolução do comércio, o software da Blue Moon Trading Company");
        g1.setFont(new Font("Times New Roman", 0, 16));
        g1.setForeground(Color.BLACK);
        g1.setBounds(594, 181, 590, 20);
        
        g2.setText("se destaca como um símbolo de inovação e confiabilidade. Desde o seu início até sua ampla");
        g2.setFont(new Font("Times New Roman", 0, 16));
        g2.setForeground(Color.BLACK);
        g2.setBounds(594, 201, 590, 20);
        
        g3.setText("adoção, o software, desenvolvido somente por Ramadan Ibraimo Ismael, representa uma");
        g3.setFont(new Font("Times New Roman", 0, 16));
        g3.setForeground(Color.BLACK);
        g3.setBounds(594, 221, 590, 20);
      
        g4.setText("mudança de paradigma na gestão e controle de estoques.");
        g4.setFont(new Font("Times New Roman", 0, 16));
        g4.setForeground(Color.BLACK);
        g4.setBounds(594, 241, 590, 20);
       
        g5.setText("produtividade aprimorada, custos operacionais reduzidos e conveniência incomparável.");
        g5.setFont(new Font("Times New Roman", 0, 16));
        g5.setForeground(Color.BLACK);
        g5.setBounds(594, 136, 590, 20);
        
        /**
 * @author Ramadan ismaeL
 */
        
        r1.setText("/**");
        r1.setFont(new Font("Ink free", 0, 20));
        r1.setForeground(Color.BLACK);
        r1.setBounds(621, 331, 590, 25);
        
        r2.setText("*   #author :   Ramadan ismaeL");
        r2.setFont(new Font("Ink free", 0, 20));
        r2.setForeground(Color.BLACK);
        r2.setBounds(621, 356, 590, 25);
        
        r3.setText("*/");
        r3.setFont(new Font("Ink free", 0, 20));
        r3.setForeground(Color.BLACK);
        r3.setBounds(621, 381, 590, 25);
        
        getContentPane().add(t1);
        
        getContentPane().add(t2);
        getContentPane().add(a1);
        getContentPane().add(a2);
        getContentPane().add(a3);
        getContentPane().add(a4);
        
        getContentPane().add(t3);
        getContentPane().add(b1);
        getContentPane().add(b2);
        
        getContentPane().add(t4);
        getContentPane().add(c1);
        getContentPane().add(c2);
        getContentPane().add(c3);
        getContentPane().add(c4);
        getContentPane().add(c5);
        
        getContentPane().add(t5);
        getContentPane().add(d1);
        getContentPane().add(d2);
        getContentPane().add(d3);
        getContentPane().add(d4);
        getContentPane().add(d5);
        
        getContentPane().add(t6);
        getContentPane().add(e1);
        getContentPane().add(e2);
        getContentPane().add(e3);
        getContentPane().add(e4);
        getContentPane().add(e5);
        getContentPane().add(e6);
                
        getContentPane().add(separator);
        
        getContentPane().add(t7);
        getContentPane().add(f1);
        getContentPane().add(f2);
        getContentPane().add(f3);
        getContentPane().add(f4);
        getContentPane().add(f5);
        
        getContentPane().add(t8);
        getContentPane().add(g1);
        getContentPane().add(g2);
        getContentPane().add(g3);
        getContentPane().add(g4);
        
        getContentPane().add(r1);
        getContentPane().add(r2);
        getContentPane().add(r3);
    }
}
