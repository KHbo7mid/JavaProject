package IHMEtudiant;

import Formation.GestionFormation;
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

public class PanelFormation extends JPanel {
    JLabel title;
    JPanel ptitle;
    static JTable jt_demande;





    static DemandeTable model;

   PanelFormation()
   {
       setLayout(new BorderLayout());

       title = new JLabel("Liste des Formations inscris");
       title.setFont(new Font("Arial", Font.BOLD, 28));
       ptitle = new JPanel();
       ptitle.add(title);
       add(ptitle, BorderLayout.NORTH);
       jt_demande=new JTable();
       add(new JScrollPane(jt_demande),BorderLayout.CENTER);
      new UpdateDemandeTable().start();


       jt_demande.addMouseListener(new MouseAdapter() {
           @Override
           public void mouseClicked(MouseEvent e) {
               if(e.getButton()==MouseEvent.BUTTON3)
               {
                   int rowIndex=jt_demande.getSelectedRow();
                   JPopupMenu popup = new JPopupMenu();
                   JMenuItem supp = new JMenuItem("supprimer");
                   popup.add(supp);
                   popup.show(jt_demande, e.getX(), e.getY());
                   supp.addActionListener(new ActionListener() {
                       @Override
                       public void actionPerformed(ActionEvent e) {
                           int answer = JOptionPane.showConfirmDialog(PanelFormation.this, "AVEZ VOUS SUPPRIMER CETTE LIGNE ?", "Error", JOptionPane.YES_NO_OPTION);
                           if (answer == 0) {
                               model.supprimeDemande(rowIndex);
                           }
                       }
                   });
               }
           }
       });




   }


   static class UpdateDemandeTable extends Thread {

       @Override
       public void run() {
           DemandeDAO demandeDAO=new DemandeDAO(Config.URL, Config.USERNAME, Config.PASSWORD);
           String req="select d.id,c.nomCours,e.nom,e.prenom,e.email from demandeetd d,cours c,enseignant e where d.idCours=c.idCours and c.idenseignant=e.code";
           ResultSet rs=demandeDAO.selection(req);
           model=new DemandeTable(rs,demandeDAO);
           jt_demande.setModel(model);
       }
   }

}
