package Enseignant;

public interface ENSEIGNANTDAOCRUD {


   public  int ajouterEnseignant(int code,String nom, String prenom, String email,String matiere);

    public int supprimerEnseignant(int id) ;



    public  int modifierEnseignant(int id,String nom,String prenom,String email );
    public  Enseignant Recherche(int code);
}
