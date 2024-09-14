package telas;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 * @author Ramadan ismaeL
 */

public class Sobre extends JFrame {
    private JLabel info1, ver, infoAth, year, info, info3, info4, info5, info6, imagem, c1, p, e, f;
    
    public Sobre() {
        Janela();
        ConfigView();
    }
    
    private void Janela() {
        setTitle("About");
        setSize(615, 340);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(false);
    }
    
    private void ConfigView() {
        setLayout(null);
        info1 = new JLabel();
        infoAth = new JLabel();
        ver = new JLabel();
        year = new JLabel();
        info = new JLabel();
        info3 = new JLabel();
        info4 = new JLabel();
        info5 = new JLabel();
        info6 = new JLabel();
        imagem = new JLabel();
        c1 = new JLabel();
        p = new JLabel();
        e = new JLabel();
        f = new JLabel();
        
        imagem.setIcon(new ImageIcon(getClass().getResource("/icones/marca.png")));
        imagem.setBounds(0, 0, 200, 190);
        
        info1.setText("BLUE MOON TRADING COMPANY");
        info1.setFont(new Font("Times New Roman", 1, 23));
        info1.setForeground(Color.BLACK);
        info1.setBounds(207, 5, 500, 21);
        
        ver.setText("Version :  1.21");
        ver.setFont(new Font("Times New Roman", 1, 16));
        ver.setForeground(Color.BLACK);
        ver.setBounds(202, 36, 300, 21);
                
        infoAth.setText("Developed by : Ismael, Ramadan Ibraimo");
        infoAth.setFont(new Font("Times New Roman", 1, 16));
        infoAth.setForeground(Color.BLACK);
        infoAth.setBounds(202, 57, 300, 21);
        
        year.setText("Year of Development : 2024");
        year.setFont(new Font("Times New Roman", 0, 16));
        year.setForeground(Color.BLACK);
        year.setBounds(202, 78, 300, 21);
        
        c1.setText("Contact Information :");
        c1.setFont(new Font("Times New Roman", 1, 16));
        c1.setForeground(Color.BLACK);
        c1.setBounds(202, 109, 300, 21);
        
        p.setText("Phone : +258 87 171 7834");
        p.setFont(new Font("Times New Roman", 0, 16));
        p.setForeground(Color.BLACK);
        p.setBounds(202, 130, 300, 21);
        
        e.setText("Email : ramadan.ismael2@gmail.com");
        e.setFont(new Font("Times New Roman", 0, 16));
        e.setForeground(Color.BLACK);
        e.setBounds(202, 151, 300, 21);
        
        f.setText("Facebook group name : IsmaeL Computer Pro");
        f.setFont(new Font("Times New Roman", 0, 16));
        f.setForeground(Color.BLACK);
        f.setBounds(202, 172, 500, 21);
        
        
        
        info.setText("Welcome to Blue Moon Trading Company's software! The software is a stock management and");
        info.setFont(new Font("Times New Roman", 0, 16));
        info.setForeground(Color.BLACK);
        info.setBounds(1, 203, 613, 20);
        
        info3.setText("control solution, an autonomous systems that does not require an internet connection.");
        info3.setFont(new Font("Times New Roman", 0, 16));
        info3.setForeground(Color.BLACK);
        info3.setBounds(1, 223, 613, 20);
        
        info4.setText("With it, you can easily register products, update them, transfer them, and eliminate unnecessary");
        info4.setFont(new Font("Times New Roman", 0, 16));
        info4.setForeground(Color.BLACK);
        info4.setBounds(1, 243, 613, 20);
        
        info5.setText("items. Additionally, it provides robust security to protect your business information.");
        info5.setFont(new Font("Times New Roman", 0, 16));
        info5.setForeground(Color.BLACK);
        info5.setBounds(1, 263, 613, 20);
        
        info6.setText("If you have any questions, please contact me.");
        info6.setFont(new Font("Times New Roman", 0, 16));
        info6.setForeground(Color.BLACK);
        info6.setBounds(1, 283, 613, 20);
             
        getContentPane().add(imagem);
        getContentPane().add(info1);
        getContentPane().add(ver);
        getContentPane().add(infoAth);        
        getContentPane().add(year);
        getContentPane().add(info);
        getContentPane().add(info3);
        getContentPane().add(info4);
        getContentPane().add(info5);
        getContentPane().add(info6);
        getContentPane().add(c1);
        getContentPane().add(p);
        getContentPane().add(e);
        getContentPane().add(f);
    }
}
