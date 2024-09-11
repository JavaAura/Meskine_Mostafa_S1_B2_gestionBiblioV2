package metier.Model;

public class JournalScientifique extends Document {
    private String domaineRecherche;

    @Override
    void afficherDetails() {

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
