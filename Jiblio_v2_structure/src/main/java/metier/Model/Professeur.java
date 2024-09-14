package metier.Model;

public class Professeur extends Utilisateur {
    private String modele_enseigne;

    @Override
    void afficherDetails() {
        System.out.println("ID: " + getId());
        System.out.println("Nom d'utilisateur: " + getUsername());
        System.out.println("Email: " + getEmail());
        System.out.println("-------------");
    }

    public Professeur() {
    }

    public String getModele_enseigne() {
        return modele_enseigne;
    }

    public void setModele_enseigne(String modele_enseigne) {
        this.modele_enseigne = modele_enseigne;
    }

    @Override
    public String toString() {
        return "Professeur{" +
                "modele_enseigne='" + modele_enseigne + super.toString() +
                "} ";
    }
}
