package Formation;

import db_config.MyConnection;

import java.sql.*;

public class CoursDAO implements CoursDAOCRUD{
    static Connection connection=null;
    Statement st=null;
    PreparedStatement ps=null;
    public  CoursDAO(String url, String username, String password)
    {
        connection= MyConnection.getConnection(url,username,password);
    }
    @Override
    public int ajouterCours(int id, String nom, String description, int id_enseignant) {
        String req="insert into cours values(?,?,?,?)";
        try {
            if(connection!=null)
            {
                st=connection.createStatement();
                ps=connection.prepareStatement(req);
                ps.setInt(1,id);
                ps.setString(2,nom);
                ps.setString(3,description);
               ps.setInt(4,id_enseignant);
                System.out.println("ajout de Formation effectué avec succes");
                return ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;

    }
    public static ResultSet selection(String req)
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

    @Override
    public int supprimerCours(int id) {
        String req="delete from cours where idCours = ?";
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
    public int modifierCours(int id, String nom, String description, int id_enseignant) {
        String req = "UPDATE cours SET nomCours = ?, description = ?, idenseignant = ?  WHERE idCours = ?";

        try {
            if(connection!=null) {

                ps = connection.prepareStatement(req);

                ps.setString(1, nom);
                ps.setString(2, description);
                ps.setInt(3,id_enseignant);
                ps.setInt(4,id);




                return ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        return 0;
    }
    public ResultSet getCoursByNom(String nomCours) {
        String req="select idCours,nomCours,description,nom,prenom,email,code from cours,enseignant where idenseignant=code and  nomCours=?;";
        try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.setString(1, nomCours);
            return ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
