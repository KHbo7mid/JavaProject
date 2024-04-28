package IHMEtudiant;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class DemandeTable extends AbstractTableModel {
    ResultSetMetaData rsmd;
    ArrayList<Object[]> data =new ArrayList<Object[]>();
    DemandeDAO dao;
    DemandeTable(ResultSet rs,DemandeDAO dao)
    {
        this.dao=dao;
        try {
            rsmd= (ResultSetMetaData) rs.getMetaData();
            while (rs.next())
            {
                Object[] ligne=new Object[rsmd.getColumnCount()];
                for(int i=0;i<ligne.length;i++)
                {
                    ligne[i]=rs.getObject(i+1);

                }
                data.add(ligne);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        try {
            return rsmd.getColumnCount();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data.get(rowIndex)[columnIndex];
    }
    public String getColumnName(int column) {
        try {
            return rsmd.getColumnName(column+1);
        } catch (SQLException e) {
            return null;
        }
    }
    public int columnNameToIndex(String columnName)
    {
        for (int i=0;i<getColumnCount();i++)
        {
            if (getColumnName(i).equalsIgnoreCase(columnName))
                return i;
        }
        return -1;
    }

    public void ajoutDemande(int idCours,int idEtudiant)
    {
        int a=dao.ajoutDemande(idCours,idEtudiant);
        if (a>0)
        {
            data.add(new Object[]{idCours,idEtudiant});

            fireTableDataChanged();
            JOptionPane.showMessageDialog(null,"Insersion effectuee avec succes");
        }
        else
            JOptionPane.showMessageDialog(null,"error");
    }
    public void supprimeDemande(int rowIndex)
    {
     int id= Integer.parseInt(getValueAt(rowIndex,columnNameToIndex("id")).toString());
     int a=dao.supprimerDemande(id);
        if (a>0)
        {
            data.remove(rowIndex);
            fireTableDataChanged();

            JOptionPane.showMessageDialog(null,"suppression effectee avec succes");
        }
        else
            JOptionPane.showMessageDialog(null,"error");
    }
}
