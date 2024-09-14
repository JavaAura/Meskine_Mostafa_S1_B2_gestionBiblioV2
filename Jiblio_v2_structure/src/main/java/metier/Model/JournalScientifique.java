package metier.Model;

public class JournalScientifique extends Document {
    private String domaineRecherche;

    @Override
    void afficherDetails() {
        System.out.println("ID: " + getId());
        System.out.println("Titre: " + getTitre());
        System.out.println("Auteur: " + getAuteur());
        System.out.println("Date de publication: " + getDatePublication());
        System.out.println("Nombre de pages: " + getNombreDePages());
        System.out.println("Domaine de recherche: " + getDomaineRecherche());
    }

    public String getDomaineRecherche() {
        return domaineRecherche;
    }

    public void setDomaineRecherche(String domaineRecherche) {
        this.domaineRecherche = domaineRecherche;
    }

    @Override
    public String toString() {
        return "JournalScientifique{" +
                "domaineRecherche='" + domaineRecherche +  super.toString() +
                "} ";
    }


}
