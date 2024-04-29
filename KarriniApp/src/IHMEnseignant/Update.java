package IHMEnseignant;

import Formation.CoursDAO;
import Formation.MyTableModel;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import static db_config.Config.*;


public class Update extends Thread{

    public void run(){

        CoursDAO dao=new CoursDAO(URL,USERNAME,PASSWORD);
        String req="SELECT nomcours,idcours,description FROM cours ;";

        ResultSet rs=dao.selection(req);

        MyTableModel model= null;
        try {
            model = new MyTableModel(rs, dao);
            model.fireTableDataChanged();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        JTable coursTable=new JTable();
        coursTable.setModel(model);
            }

    }







