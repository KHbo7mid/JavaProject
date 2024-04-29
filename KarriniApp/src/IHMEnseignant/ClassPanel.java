package IHMEnseignant;

import Etudiant.EtudiantDAO;
import Etudiant.MyTableModel;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import static db_config.Config.*;

public class ClassPanel extends JPanel  {
    String req;
    ResultSet rs;
    EtudiantDAO dao;
    MyTableModel model;
    public ClassPanel(int code) throws SQLException  {
        JTable etudiantTable = new JTable();
        req= "SELECT nom, prenom FROM etudiant " +
                "WHERE id IN ( " +
                "    SELECT idetudiant FROM demandeetd " +
                "    WHERE idcours IN ( " +
                "        SELECT idcours FROM cours " +
                "        WHERE idenseignant IN ( " +
                "            SELECT code FROM enseignant " +
                "            WHERE code = '" + code + "' " +
                "        ) " +
                "    ) " +
                ")";

        dao=new EtudiantDAO(URL,USERNAME,PASSWORD);
         rs=dao.selection(req);
        if(rs!=null)
        {    model=new Etudiant.MyTableModel(rs,dao);

            etudiantTable.setModel(model);


            JScrollPane etudiantTableScrollPane = new JScrollPane(etudiantTable);
            etudiantTableScrollPane.setPreferredSize(new Dimension(600, 400));
            add(etudiantTableScrollPane);
            setOpaque(false);
        }
        else{
//            JOptionPane.showMessageDialog(null,"l'enseignant n'existe pas");
        }
    }
}
