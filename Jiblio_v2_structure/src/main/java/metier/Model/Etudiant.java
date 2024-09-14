package metier.Model;

public class Etudiant extends Utilisateur {
    private String filiere_etudes;

    @Override
    void afficherDetails() {
        System.out.println("ID: " + getId());
        System.out.println("Nom d'utilisateur: " + getUsername());
        System.out.println("Email: " + getEmail());
        System.out.println("-------------");
    }

    public Etudiant() {
    }

    public String getFiliere_etudes() {
        return filiere_etudes;
    }

    public void setFiliere_etudes(String filiere_etudes) {
        this.filiere_etudes = filiere_etudes;
    }

    @Override
    public String toString() {
        return "Etudiant{" +
                "filiere_etudes='" + filiere_etudes  + super.toString() +
                "} ";
    }
}
