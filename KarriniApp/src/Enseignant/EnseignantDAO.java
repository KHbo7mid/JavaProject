package Enseignant;





import db_config.MyConnection;

import java.sql.*;

public class EnseignantDAO implements ENSEIGNANTDAOCRUD {
    static Connection connection=null;
    Statement st=null;
    PreparedStatement ps=null;

    public EnseignantDAO(String url, String username, String password)
    {
        connection= MyConnection.getConnection(url, username, password);
    }
    @Override
    public int ajouterEnseignant(int code ,String nom, String prenom, String email,String matiere) {

        String req="insert into enseignant values (?,?,?,?,?);";
        try {
            if(connection!=null)
            {

                st=connection.createStatement();
                ps=connection.prepareStatement(req);
                ps.setInt(1,code);
                ps.setString(2,nom);
                ps.setString(3,prenom);
                ps.setString(4,email);
                ps.setString(5,matiere);



                System.out.println("ajout avec succes");
                return ps.executeUpdate();




            }
        } catch (SQLException e) {
            System.out.println("erreur d ajout"+e.getMessage());
        }
        return 0;
    }

    @Override
    public int supprimerEnseignant(int id) {
        String req="delete from enseignant where code = ?";
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
    public int modifierEnseignant(int id, String nom, String prenom, String email) {
        String req = "UPDATE enseignant SET nom = ?, prenom = ?, email = ?  WHERE code = ?";

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
    public  Enseignant Recherche(int code) {

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Enseignant enseignant = null;

        try {
            // Establish connection
            if(connection!=null)
            {

            // SQL query
            String query = "SELECT * FROM enseignant WHERE code = ?";

            // Prepare the statement
            statement = connection.prepareStatement(query);
            statement.setInt(1, code);

            // Execute the query
            resultSet = statement.executeQuery();

            // Check if the result set has any rows
            if (resultSet.next()) {
                // Retrieve the data from the result set
                String nom = resultSet.getString("nom");
                String prenom = resultSet.getString("prenom");
                String email = resultSet.getString("email");
                String matiere = resultSet.getString("matiere");

                // Create a new Enseignant object
                enseignant = new Enseignant(code, nom, prenom, email, matiere);
            }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return enseignant;
    }
    public Enseignant RechercheAuthentification(String email, String password) {

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        Enseignant enseignant = null;

        try {
            // Establish connection
            if(connection!=null)
            {
                int code=Integer.parseInt(password);

                // SQL query
                String query = "SELECT * FROM enseignant WHERE code = ? and email=?";

                // Prepare the statement
                statement = connection.prepareStatement(query);
                statement.setInt(1, code);
                statement.setString(2, email);

                // Execute the query
                resultSet = statement.executeQuery();

                // Check if the result set has any rows
                if (resultSet.next()) {
                    // Retrieve the data from the result set
                    String nom = resultSet.getString("nom");
                    String prenom = resultSet.getString("prenom");

                    String matiere = resultSet.getString("matiere");

                    // Create a new Enseignant object
                    enseignant = new Enseignant(code, nom, prenom, email, matiere);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return enseignant;
    }
}


