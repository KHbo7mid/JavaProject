package IHMEtudiant;

import Formation.CoursDAO;
import chat.PanelChat;
import db_config.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;




public class PanelHome extends JPanel {
    DefaultListModel model;
    JSplitPane jsp;
    JList  jl;
    JTabbedPane jtp;
    CoursDAO formationDao;

    ArrayList<String> formationsOuvertes = new ArrayList<>();

    int idEtudiant;

    DemandeTable modelTable;
    PanelHome(int idEtudiant){
        this.idEtudiant=idEtudiant;
        formationDao=new CoursDAO(Config.URL,Config.USERNAME,Config.PASSWORD);
        setLayout(new FlowLayout(FlowLayout.LEFT));
   jsp=new JSplitPane();
   model=new DefaultListModel();
   jl=new JList();
   jl.setModel(model);
        jl.setPreferredSize(new Dimension(150,550
        ));
        jtp=new JTabbedPane();
        jtp.setPreferredSize(new Dimension(450,100));
        jl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getClickCount()==2)
                {
                    String selectedNomCours = (String) jl.getSelectedValue();
                    if (selectedNomCours != null) {

                        AfficherCoursInfo(selectedNomCours);
                    }

                }
            }
        });
        jsp.setLeftComponent(jl);
        jsp.setRightComponent(jtp);
        add(jsp);
        ListNomFormation();

    }
    private void ListNomFormation() {
        try {
            String req = "select nomCours from cours;";
            ResultSet rs = CoursDAO.selection(req);
            if (rs != null) {
                while (rs.next()) {
                    String nomCours = rs.getString("nomCours");
                    model.addElement(nomCours);
                }
                rs.close(); // Close the ResultSet to release resources
            }
            else{
                System.out.println("empty");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void AfficherCoursInfo(String nomCours) {
        try {
            ResultSet rs = formationDao.getCoursByNom(nomCours);
            if (rs.next()) {
               if(!formationsOuvertes.contains(nomCours))
               {
                   int codeprof=rs.getInt("code");
                   int idCours=rs.getInt("idCours");
                   String nom = rs.getString("nomCours");
                   String description = rs.getString("description");
                   String nomEnseignant = rs.getString("nom").concat(" ").concat(rs.getString("prenom"));
                   String emailEnseignant = rs.getString("email");
                   JLabel lnom=new JLabel(nom);
                   lnom.setFont(new Font("Arial",Font.BOLD,20));
                   JLabel ldescription=new JLabel("<html><body style='width: 300px'>" + description + "</body></html>");



                   JPanel coursePanel = new JPanel();
                   coursePanel.setLayout(new FlowLayout(FlowLayout.CENTER,100,50));
                   JPanel formation=new JPanel(new BorderLayout(0,50));
                   JPanel enseignant=new JPanel(new BorderLayout(0,50));
                   formation.add(lnom,BorderLayout.NORTH);
                   formation.add(ldescription,BorderLayout.CENTER);
                   enseignant.add(new JLabel("Enseignant: " + nomEnseignant ),BorderLayout.NORTH);
                   enseignant.add(new JLabel("Email: "+emailEnseignant),BorderLayout.CENTER);
                   coursePanel.add(formation);
                   coursePanel.add(enseignant);


                   JButton fermer=new JButton("Fermer");
                   JButton inscrireButton = new JButton("Inscrire");

                   JPanel pbtn=new JPanel(new FlowLayout(FlowLayout.CENTER,50,50));
                   inscrireButton.addActionListener(e -> {



                           DemandeDAO demandeDAO=new DemandeDAO(Config.URL,Config.USERNAME,Config.PASSWORD);
                       boolean demandeExists = demandeDAO.demandeExists(idCours);
                          if(demandeExists)
                          {
                              JOptionPane.showMessageDialog(null,"Vous avez deja inscris dans cette formation");
                          }
                          else {
                              int a= demandeDAO.ajoutDemande(idCours,idEtudiant);

                              if (a>0)
                              {

                               new    PanelFormation.UpdateDemandeTable().start();

                                 PanelChat.addContact(codeprof,nomEnseignant);


                                  JOptionPane.showMessageDialog(null,"Ajoute effectué avec succes");



                              }
                              else{
                                  JOptionPane.showMessageDialog(null,"Erreur");
                              }
                          }



                   });
                   fermer.addActionListener(new ActionListener() {
                       @Override
                       public void actionPerformed(ActionEvent e) {
                           jtp.remove(jtp.getSelectedIndex());
                           formationsOuvertes.remove(nomCours);
                       }
                   });
                   pbtn.add(fermer);
                   pbtn.add(inscrireButton);
                   coursePanel.add(pbtn);
                   formationsOuvertes.add(nomCours);


                   jtp.addTab(nomCours, coursePanel);
               }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
