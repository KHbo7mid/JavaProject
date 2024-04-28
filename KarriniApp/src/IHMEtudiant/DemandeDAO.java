package IHMEtudiant;

import db_config.MyConnection;

import java.sql.*;

public class DemandeDAO implements DemandeDAOCRUD {
    static Connection connection = null;
    Statement st = null;
    PreparedStatement ps = null;


    public DemandeDAO(String url, String username, String password) {
        connection = MyConnection.getConnection(url, username, password);
    }

    @Override
    public int ajoutDemande( int idCours, int idEtudiant) {
        String req = "insert into demandeetd (idCours, idEtudiant) values (?,?);";
        try {
            if (connection != null) {

                st = connection.createStatement();
                ps = connection.prepareStatement(req,Statement.RETURN_GENERATED_KEYS);

                ps.setInt(1, idCours);
                ps.setInt(2, idEtudiant);

                System.out.println("Demande de Formation effectué avec succes");
                return ps.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;


    }

    public static ResultSet selection(String req) {
        Statement st = null;
        try {
            if (connection != null) {
                st = connection.createStatement();

                ResultSet rs = st.executeQuery(req);
                return rs;
            }
        } catch (SQLException e) {
            System.out.println("erreur " + e.getMessage());

        }
        return null;

    }

    @Override
    public int supprimerDemande(int id) {
        String req = "delete from demandeetd where id = ?";
        try {
            PreparedStatement ps = null;

            ps = connection.prepareStatement(req);
            ps.setInt(1, id);
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }
    }

    public boolean demandeExists(int idCours)  {

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        boolean exists = false;
        try {
        if (connection != null) {
            String req = "select * from demandeetd where  idCours=?;";

            statement = connection.prepareStatement(req);


            statement.setInt(1, idCours);
            resultSet = statement.executeQuery();

            exists = resultSet.next();
        }
        } catch (SQLException e) {
                throw new RuntimeException(e);
            }




        return exists;
    }
}