package Etudiant;







import db_config.MyConnection;

import java.sql.*;
import java.util.Arrays;

public class EtudiantDAO implements ETUDIANTDAOCRUD {
    Connection connection=null;
    Statement st=null;
    PreparedStatement ps=null;
    static  int code=0;
    public EtudiantDAO(String url, String username, String password)
    {
        connection= MyConnection.getConnection(url, username, password);
    }

    public int ajouterEtudiant(String nom, String prenom, String email,  char[] password) {

        try {
            code++;
            int id=code;
            String req="insert into etudiant values (?,?,?,?,?);";
       if(connection!=null)
       {

               st=connection.createStatement();
               ps=connection.prepareStatement(req);
               ps.setInt(1,id);
               ps.setString(2,nom);
               ps.setString(3,prenom);
           ps.setString(4,email);

               ps.setString(5, Arrays.toString(password));
           System.out.println("ajout avec succes");
           return ps.executeUpdate();




       }
        } catch (SQLException e) {
            System.out.println("erreur d ajout"+e.getMessage());
        }
        return 0;
    }
    public ResultSet selection(String req)
    {
        Statement st=null;
        try {
            if (connection != null) {
                st = connection.createStatement();

                ResultSet rs = st.executeQuery(req);
                return rs;
            }
        }
        catch (SQLException e)
        {
            System.out.println("erreur "+e.getMessage());

        }
        return null;

    }
    public Etudiant Recherche(char[] password,String email) {

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Etudiant etudiant = null;

        try {
            // Establish connection
            if(connection!=null)
            {

                // SQL query
                String query = "SELECT * FROM etudiant WHERE password = ? AND email=?";

                // Prepare the statement
                statement = connection.prepareStatement(query);
                statement.setString(1, Arrays.toString(password));
                statement.setString(2,email);

                // Execute the query
                resultSet = statement.executeQuery();

                // Check if the result set has any rows
                if (resultSet.next()) {
                    // Retrieve the data from the result set
                    int id=resultSet.getInt("id");
                    String nom = resultSet.getString("nom");
                    String prenom = resultSet.getString("prenom");



                    // Create a new Enseignant object
                    etudiant = new Etudiant(id, nom, prenom, email, password);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return etudiant;
    }
    @Override
    public int supprimerEtudiant(int id) {
        String req="delete from etudiant where id = ?";
        try {
            PreparedStatement ps= null;

            ps = connection.prepareStatement(req);
            ps.setInt(1,id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

    }


    @Override
    public int modifierEtudiant(int id,String nom, String prenom, String email) {
        String req = "UPDATE etudiant SET nom = ?, prenom = ?, email = ?  WHERE id = ?";

        try {
            if(connection!=null) {

                ps = connection.prepareStatement(req);

                ps.setString(1, nom);
                ps.setString(2, prenom);
                ps.setString(3, email);
                ps.setInt(4,id);




                return ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return 0;
    }


}
