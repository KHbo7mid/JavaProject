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
        req="SELECT nom,prenom FROM etudiant "+
                "WHERE idetudiant in (SELECT idetudiant FROM demandeedt "+
                "WHERE idenseignant ="+code+");";
        dao=new EtudiantDAO(URL,USERNAME,PASSWORD);
        rs=dao.selection(req);
        model=new Etudiant.MyTableModel(rs,dao);
        etudiantTable.setModel(model);

        JScrollPane etudianttableScrollPane = new JScrollPane(etudiantTable);
        etudianttableScrollPane.setPreferredSize(new Dimension(600, 400));
        add(etudianttableScrollPane);
        setOpaque(true);
    }
}
