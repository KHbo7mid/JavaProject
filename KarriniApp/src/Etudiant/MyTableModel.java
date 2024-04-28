package Etudiant;



import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

public class MyTableModel extends AbstractTableModel {
    ResultSetMetaData rsmd;
    ArrayList<Object[]> data =new ArrayList<Object[]>();
    EtudiantDAO dao;
    public MyTableModel(ResultSet rs, EtudiantDAO dao) throws SQLException, IllegalArgumentException {
        this.dao = dao;
        if (rs != null) {
            try {
                rsmd = rs.getMetaData();
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
        } else {
            throw new IllegalArgumentException("ResultSet cannot be null.");
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
        if (getColumnName(columnIndex).equalsIgnoreCase("id"))
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


        int id=Integer.parseInt(getValueAt(rowIndex,columnNameToIndex("id")).toString());

        String nom=getValueAt(rowIndex,columnNameToIndex("nom")).toString();
        String prenom=getValueAt(rowIndex,columnNameToIndex("prenom")).toString();

        String email=getValueAt(rowIndex,columnNameToIndex("email")).toString();




        if (getColumnName(columnIndex).equalsIgnoreCase("nom"))
        {
            nom=aValue.toString();
        }
        if (getColumnName(columnIndex).equalsIgnoreCase("prenom"))
        {
            prenom=aValue.toString();
        }  if (getColumnName(columnIndex).equalsIgnoreCase("email"))
        {
            email=aValue.toString();
        }


        int a=dao.modifierEtudiant(id,nom,prenom,email);
        if (a>0)
        {
            data.get(rowIndex)[columnIndex]=aValue;
            JOptionPane.showMessageDialog(null,"modification effectuée avec succées");
        }
        else{
            JOptionPane.showMessageDialog(null,"erreur dans la modification");
        }
    }
   /* public void insertEtudiant(String nom,String prenom,String email,char[] password)
    {

        int a= dao.ajouterEtudiant(nom,prenom,email,password);
        if (a>0)
        {
            data.add(new Object[]{nom,prenom,email,password});
            //refresh
            fireTableDataChanged();
            JOptionPane.showMessageDialog(null,"Insersion effectuee avec succes");
        }
        else
            JOptionPane.showMessageDialog(null,"error");

    }*/

    public void supprimeEtudiant(int rowIndex)
    {
        int id=Integer.parseInt(getValueAt(rowIndex,columnNameToIndex("id")).toString());
        int a=dao.supprimerEtudiant(id);
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


