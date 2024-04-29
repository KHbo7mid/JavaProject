package IHMEtudiant;

import chat.PanelChat;

import javax.swing.*;
import java.awt.*;


public class HomePage extends JFrame {

    JTabbedPane jtp;
    JPanel welcome,pchat,phome,pformation;


    JLabel lwelcome,lnom,lprenom,limage;
    ImageIcon img;

    int idEtudiant;
    String nom,prenom;


   public  HomePage(int id,String nom,String prenom)

    {
        idEtudiant=id;
        this.nom=nom;
        this.prenom=prenom;
        setTitle("Home page");
        this.setSize(1000, 800);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(true);
        welcome=new JPanel(new FlowLayout(FlowLayout.CENTER,70,30));
        welcome.setBackground(new Color(0x2979FF));
        lwelcome=new JLabel("Bienvenu");
        lwelcome.setFont(new Font("Arial",Font.BOLD,25));
        lwelcome.setForeground(Color.white);

        lnom=new JLabel(nom);
        lprenom=new JLabel(prenom);
        lnom.setFont(new Font("Arial",Font.BOLD,25));
        lnom.setForeground(Color.white);
        lprenom.setFont(new Font("Arial",Font.BOLD,25));
        lprenom.setForeground(Color.white);
        welcome.add(lwelcome);
        welcome.add(lnom);
        welcome.add(lprenom);
        limage=new JLabel();
        img=new ImageIcon("KarriniApp/src/images/logo.png");
        Image image=img.getImage().getScaledInstance(200,100,Image.SCALE_SMOOTH);
        ImageIcon newImg =new ImageIcon(image);
        limage.setIcon(newImg);
        welcome.add(limage);
        jtp=new JTabbedPane();
        phome=new PanelHome(idEtudiant);
        pformation=new PanelFormation();
        String User=nom+" "+prenom;
        pchat=new PanelChat(idEtudiant,User);

       jtp.addTab("Home",phome);
       jtp.add("Formations",pformation);
       jtp.add("Chat",pchat);






        add(welcome,BorderLayout.NORTH);
        add(jtp,BorderLayout.CENTER);


        setVisible(true);

    }
    public static void main(String [] args)
    {
        int id=1;
        String nom="khiari";
        String prenom="ahmed";
        new HomePage(id,nom,prenom);


    }
}
