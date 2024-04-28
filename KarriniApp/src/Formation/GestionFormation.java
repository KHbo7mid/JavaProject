package Formation;








import db_config.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GestionFormation extends JInternalFrame {
    JTable jt_cours;
    JLabel title;
    JPanel ptitle;
    MyTableModel model;
    public GestionFormation() throws SQLException
    {
        this.setTitle("Gestion Formations");
        this.setSize(980, 800);
        this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);

        this.setClosable(true);
        this.setResizable(true);
        this.setMaximizable(true);
        this.setIconifiable(true);
        title = new JLabel("Liste des Formations");
        title.setFont(new Font("Arial", Font.BOLD, 48));
        ptitle = new JPanel();
        ptitle.add(title);
        add(ptitle, BorderLayout.NORTH);
        jt_cours = new JTable();
        add(new JScrollPane(jt_cours));
        CoursDAO dao = new CoursDAO(Config.URL, Config.USERNAME, Config.PASSWORD);
        String req = "select idCours, nomCours,description,idenseignant,nom,email from cours,enseignant where idenseignant=code";
        ResultSet rs = dao.selection(req);
        model = new MyTableModel(rs, dao);
        jt_cours.setModel(model);
        jt_cours.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    int rowIndex = jt_cours.getSelectedRow();
                    JPopupMenu popup = new JPopupMenu();
                    JMenuItem supp = new JMenuItem("supprimer");
                    popup.add(supp);
                    popup.show(jt_cours, e.getX(), e.getY());
                    supp.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            int answer = JOptionPane.showConfirmDialog(GestionFormation.this, "AVEZ VOUS SUPPRIMER CETTE LIGNE ?", "Error", JOptionPane.YES_NO_OPTION);
                            if (answer == 0) {
                                model.supprimerCours(rowIndex);
                            }
                        }
                    });
                }
            }
        });


        this.setVisible(true);

    }
}
