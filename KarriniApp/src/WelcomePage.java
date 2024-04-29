



import authentification.Compte;
import authentification.Connecter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;

public class WelcomePage extends JFrame {
    JLabel limage;
    ImageIcon img;
    JPanel pimage,ptitle,pbtn;
    JButton oldC,newC;

    WelcomePage(){
        setTitle("9arrini");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600,700);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(0x2979FF));
        pimage=new JPanel(new FlowLayout(FlowLayout.CENTER,0,50));
        pimage.setOpaque(false);
        ptitle=new JPanel(new BorderLayout());
       ptitle.setOpaque(false);

        limage=new JLabel();
        img=new ImageIcon("KarriniApp/src/images/logo.png");
        Image image=img.getImage().getScaledInstance(600,350,Image.SCALE_SMOOTH);
        ImageIcon newImg =new ImageIcon(image);
        limage.setIcon(newImg);

        pimage.add(limage);

        add(pimage,BorderLayout.NORTH);

        oldC=new JButton("Se Connecter");
        oldC.setBackground(Color.white);
        oldC.setForeground(new Color(0x2979FF));
        oldC.setFont(new Font("Judson",Font.BOLD,20));
        newC=new JButton("Cree un Compte");
        newC.setBackground(Color.white);
        newC.setForeground(new Color(0x2979FF));
        newC.setFont(new Font("Judson",Font.BOLD,20));

        pbtn=new JPanel(new FlowLayout(FlowLayout.CENTER,20,50));
        pbtn.setOpaque(false);
        pbtn.add(oldC);
        pbtn.add(newC);
        ptitle.add(pbtn,BorderLayout.CENTER);
        add(ptitle,BorderLayout.CENTER);


        oldC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                  Socket  socket=new Socket("127.0.0.1",9000);
                  Connecter connecter=new Connecter();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                    JOptionPane.showMessageDialog(null, "Error connecting to the server", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        newC.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Compte compte=new Compte();
            }
        });
        setVisible(true);
    }

}
