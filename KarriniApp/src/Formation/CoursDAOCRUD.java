package Formation;

public interface CoursDAOCRUD {
    int ajouterCours(int id,String nom,String description,int id_enseignant);
    public int supprimerCours(int id);
    public int modifierCours(int id,String nom,String description,int id_enseignant);
}
