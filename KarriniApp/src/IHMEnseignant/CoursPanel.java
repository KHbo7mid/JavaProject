package IHMEnseignant;

import Formation.CoursDAO;
import Formation.MyTableModel;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import static db_config.Config.*;

public class CoursPanel extends JPanel {
    String req;
    MyTableModel model= null;
    CoursDAO dao;
    ResultSet rs;

    public CoursPanel(int code) throws SQLException {
        JTable coursTable = new JTable();
        dao=new CoursDAO(URL,USERNAME,PASSWORD);
        req="SELECT nomcours,idcours,description FROM cours WHERE idenseignant='"+code+"';";

        rs=dao.selection(req);

        model=new MyTableModel(rs, dao);
        coursTable.setModel(model);

        JScrollPane courstableScrollPane = new JScrollPane(coursTable);
        courstableScrollPane.setPreferredSize(new Dimension(600, 400));
        setOpaque(false);
        add(courstableScrollPane);
        new Update().start();
    }
}
