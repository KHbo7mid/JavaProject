package Enseignant;









import db_config.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

public class GestionEnseignant extends JInternalFrame {
    JTable jt_enseignant;
    JLabel title;
    JPanel ptitle;
    MyTableModel model;
    public GestionEnseignant() {
        this.setTitle("Gestion Enseignant");
        this.setSize(980, 800);
        this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);

        this.setClosable(true);
        this.setResizable(true);
        this.setMaximizable(true);
        this.setIconifiable(true);
        title = new JLabel("Liste des Enseignants");
        title.setFont(new Font("Arial", Font.BOLD, 48));
        ptitle = new JPanel();
        ptitle.add(title);
        add(ptitle, BorderLayout.NORTH);
        jt_enseignant = new JTable();
        add(new JScrollPane(jt_enseignant));
        EnseignantDAO dao = new EnseignantDAO(Config.URL, Config.USERNAME, Config.PASSWORD);
        String req = "select code,nom,prenom,email,matiere from enseignant";
        ResultSet rs = dao.selection(req);
        model = new MyTableModel(rs, dao);
        jt_enseignant.setModel(model);
        jt_enseignant.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    int rowIndex = jt_enseignant.getSelectedRow();
                    JPopupMenu popup = new JPopupMenu();
                    JMenuItem supp = new JMenuItem("supprimer");
                    popup.add(supp);
                    popup.show(jt_enseignant, e.getX(), e.getY());
                    supp.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            int answer = JOptionPane.showConfirmDialog(GestionEnseignant.this, "AVEZ VOUS SUPPRIMER CETTE LIGNE ?", "Error", JOptionPane.YES_NO_OPTION);
                            if (answer == 0) {
                                model.supprimeEnseignant(rowIndex);
                            }
                        }
                    });
                }
            }
        });


        this.setVisible(true);
    }
}
