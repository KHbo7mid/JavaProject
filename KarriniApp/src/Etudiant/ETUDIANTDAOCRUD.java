package Etudiant;



public interface ETUDIANTDAOCRUD {
    int ajouterEtudiant(String nom,String prenom,String email , char[] password);
    public int supprimerEtudiant(int id) ;



    public  int modifierEtudiant(int id,String nom,String prenom,String email ) ;

}
