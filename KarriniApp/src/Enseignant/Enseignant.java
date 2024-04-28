package Enseignant;

public class Enseignant {
    int code;
    String nom,prenom,email,matiere;

    public Enseignant(int code, String nom, String prenom, String email,String matiere) {
        this.code = code;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.matiere=matiere;
    }

    public int getCode() {
        return code;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setCode(int id) {
        this.code = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMatiere() {
        return matiere;
    }
}
