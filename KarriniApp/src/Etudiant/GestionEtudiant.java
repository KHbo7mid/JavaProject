package Etudiant;







import db_config.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestionEtudiant extends JInternalFrame {
    JTable jt_etudiant;
    JLabel title;
    JPanel ptitle;
    MyTableModel model;
    public GestionEtudiant() throws SQLException {
        this.setTitle("Gestion Etudiant");
        this.setSize(980, 800);
        this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);

        this.setClosable(true);
        this.setResizable(true);
        this.setMaximizable(true);
        this.setIconifiable(true);
        title=new JLabel("Liste des etudiants");
        title.setFont(new Font("Arial",Font.BOLD,48));
        ptitle=new JPanel();
        ptitle.add(title);
        add(ptitle,BorderLayout.NORTH);
        jt_etudiant=new JTable();
        add(new JScrollPane(jt_etudiant));
        EtudiantDAO dao=new EtudiantDAO(Config.URL, Config.USERNAME, Config.PASSWORD);
        String req="select id,nom,prenom,email from etudiant";
        ResultSet rs=dao.selection(req);
        model=new MyTableModel( rs,dao);
        jt_etudiant.setModel(model);
        jt_etudiant.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton()==MouseEvent.BUTTON3)
                {
                    int rowIndex = jt_etudiant.getSelectedRow();
                    JPopupMenu popup=new JPopupMenu();
                    JMenuItem supp=new JMenuItem("supprimer");
                    popup.add(supp);
                    popup.show(jt_etudiant,e.getX(),e.getY());
                    supp.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            int  answer =JOptionPane.showConfirmDialog(GestionEtudiant.this,"AVEZ VOUS SUPPRIMER CETTE LIGNE ?","Error", JOptionPane.YES_NO_OPTION);
                            if (answer==0)
                            {
                                model.supprimeEtudiant(rowIndex);
                            }
                        }
                    });
                }
            }
        });









        this.setVisible(true);
    }
    public static void main(String[] args)  throws SQLException{
        GestionEtudiant user=new GestionEtudiant();
    }

}
