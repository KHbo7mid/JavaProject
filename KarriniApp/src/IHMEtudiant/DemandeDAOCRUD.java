package IHMEtudiant;

import java.sql.SQLException;

public interface DemandeDAOCRUD {
    int ajoutDemande(int idCours,int idEtudiant) throws SQLException;
    int supprimerDemande(int id);
}
