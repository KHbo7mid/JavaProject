package Admin;





import Enseignant.AjoutEnseignant;
import Enseignant.EnseignantDAO;
import Enseignant.GestionEnseignant;
import Etudiant.EtudiantDAO;
import Etudiant.GestionEtudiant;
import Formation.AjoutCours;
import Formation.CoursDAO;
import Formation.GestionFormation;
import chat.PanelChat;
import db_config.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import static chat.PanelChat.ContactEnseignant;
import static chat.PanelChat.ContactEtudiant;

public class Dashboard extends JFrame {
    JMenuBar menuBar;
    JDesktopPane desktopPane;
    JMenu enseignant,etudiant,formation;
   JMenuItem listeEtudiant,ajoutEnseignant,listeEnseignant,ListFormation,ajoutFormation,chatEnseignant,chatEtudiant;
    JLabel limage;
    JPanel pimage,pchatBTN;
    PanelChat chatProf,chatStudent;



    ImageIcon img;
    JLabel bvn;
    JButton chatBTN;

    Dashboard()
    {
        setTitle("Dashboard");
        this.setSize(1000, 1000);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(true);
        menuBar=new JMenuBar();


        enseignant=new JMenu("Enseignants");
        etudiant=new JMenu("Etudiants");
        formation=new JMenu("Formation");
        listeEtudiant=new JMenuItem("liste des Etudiants");
        chatEnseignant=new JMenuItem("Chat avec les enseignants");
        chatEtudiant=new JMenuItem("Chat avec les etudiants");




        menuBar.add(enseignant);
        menuBar.add(etudiant);
        menuBar.add(formation);
        etudiant.add(listeEtudiant);
        etudiant.add(chatEtudiant);
        desktopPane=new JDesktopPane();
        add(desktopPane,BorderLayout.CENTER);
        setJMenuBar(menuBar);


           listeEtudiant.addActionListener(new ActionListener() {
               @Override
               public void actionPerformed(ActionEvent e) {
                   GestionEtudiant gestionEtudiant= null;
                   try {
                       gestionEtudiant = new GestionEtudiant();
                       chatProf.setVisible(false);
                       chatStudent.setVisible(false);
                       desktopPane.add(gestionEtudiant);
                   } catch (SQLException ex) {
                       throw new RuntimeException(ex);
                   }

               }
           });

        ajoutEnseignant=new JMenuItem("Ajouter Enseignant");
        listeEnseignant=new JMenuItem("Liste des Enseigants");

        enseignant.add(ajoutEnseignant);
        enseignant.add(listeEnseignant);
        enseignant.add(chatEnseignant);

        ajoutEnseignant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AjoutEnseignant ajout=new AjoutEnseignant();
                chatProf.setVisible(false);
                chatStudent.setVisible(false);
                desktopPane.add(ajout);
            }
        });
listeEnseignant.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        GestionEnseignant gestionEnseignant=new GestionEnseignant();
        chatProf.setVisible(false);
        chatStudent.setVisible(false);
        desktopPane.add(gestionEnseignant);
    }
});
        ListFormation=new JMenuItem("Liste des Formations");

    formation.add(ListFormation);
    ListFormation.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            GestionFormation formation= null;
            try {
                formation = new GestionFormation();
                chatProf.setVisible(false);
                chatStudent.setVisible(false);
                desktopPane.add(formation);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }

        }
    });

        ajoutFormation=new JMenuItem("Ajout de Formation");
        formation.add(ajoutFormation);
        ajoutFormation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AjoutCours addCours=new AjoutCours();
                chatProf.setVisible(false);
                chatStudent.setVisible(false);
                desktopPane.add(addCours);
            }
        });

        limage=new JLabel();
        img=new ImageIcon("KarriniApp/src/images/admin.jpeg");
        Image image=img.getImage().getScaledInstance(80,65,Image.SCALE_SMOOTH);
        ImageIcon newImg =new ImageIcon(image);
        limage.setIcon(newImg);


        bvn=new JLabel("Bienvenue ");
        bvn.setFont(new Font("Arial",Font.BOLD,25));
        JPanel pbvn = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 10));
        pbvn.add(bvn);

       pbvn.add(limage);
    add(pbvn,BorderLayout.NORTH);
        chatProf=new PanelChat(0,"Admin");
        EnseignantDAO  enseignantDAO=new EnseignantDAO(Config.URL,Config.USERNAME,Config.PASSWORD);
        ContactEnseignant(enseignantDAO);

        chatProf.setVisible(false);
        chatStudent=new PanelChat(0,"Admin");
        EtudiantDAO etudiantDAO=new EtudiantDAO(Config.URL,Config.USERNAME,Config.PASSWORD);
        ContactEtudiant(etudiantDAO);


               chatEnseignant.addActionListener(new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent e) {
                       chatProf.setVisible(true);
                       chatStudent.setVisible(false);
                       add(chatProf,BorderLayout.WEST);

                   }
               });


        chatEtudiant.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chatStudent.setVisible(true);
                chatProf.setVisible(false);
                add(chatStudent,BorderLayout.WEST);
            }
        });
        this.setVisible(true);

    }


    public static void main(String[] args) {
        Dashboard d=new Dashboard();
    }
}
