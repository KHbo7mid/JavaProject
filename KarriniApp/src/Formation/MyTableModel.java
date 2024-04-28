package Formation;


import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class MyTableModel extends AbstractTableModel {
    ResultSetMetaData rsmd;
    ArrayList<Object[]> data =new ArrayList<Object[]>();
    CoursDAO dao;
     public MyTableModel(ResultSet rs, CoursDAO dao) throws SQLException
    {
        this.dao=dao;
        if(rs!=null) {
            try {
                rsmd = (ResultSetMetaData) rs.getMetaData();
                while (rs.next()) {
                    Object[] ligne = new Object[rsmd.getColumnCount()];
                    for (int i = 0; i < ligne.length; i++) {
                        ligne[i] = rs.getObject(i + 1);

                    }
                    data.add(ligne);
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }else{
            throw new IllegalArgumentException("result set is null");
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
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (getColumnName(columnIndex).equalsIgnoreCase("idCours"))
        {
            return false;
        }
        else return true;
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
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {


        int id=Integer.parseInt(getValueAt(rowIndex,columnNameToIndex("idCours")).toString());

        String nom=getValueAt(rowIndex,columnNameToIndex("nomCours")).toString();
        String description=getValueAt(rowIndex,columnNameToIndex("description")).toString();

        int id_enseignant=Integer.parseInt(getValueAt(rowIndex,columnNameToIndex("idenseignant")).toString());




        if (getColumnName(columnIndex).equalsIgnoreCase("nomCours"))
        {
            nom=aValue.toString();
        }
        if (getColumnName(columnIndex).equalsIgnoreCase("description"))
        {
            description=aValue.toString();
        }  if (getColumnName(columnIndex).equalsIgnoreCase("idenseignant"))
        {
            id_enseignant= Integer.parseInt(aValue.toString());
        }


        int a=dao.modifierCours(id,nom,description,id_enseignant);
        if (a>0)
        {
            data.get(rowIndex)[columnIndex]=aValue;
            JOptionPane.showMessageDialog(null,"modification effectuée avec succées");
        }
        else{
            JOptionPane.showMessageDialog(null,"erreur dans la modification");
        }
    }
    public void insertCours(int id, String nom, String description, int id_enseignant)
    {

        int a= dao.ajouterCours(id,nom,description,id_enseignant);
        if (a>0)
        {  Object[] newRow = {id, nom, description, id_enseignant};

            fireTableDataChanged();
            JOptionPane.showMessageDialog(null,"Insersion effectuee avec succes");
        }
        else
            JOptionPane.showMessageDialog(null,"error");

    }

    public void supprimerCours(int rowIndex)
    {
        int id=Integer.parseInt(getValueAt(rowIndex,columnNameToIndex("idCours")).toString());
        int a=dao.supprimerCours(id);
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
