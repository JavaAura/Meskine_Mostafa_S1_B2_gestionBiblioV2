package metier.Model;

public class TheseUniversitaire extends Document {
    private String universite;
    private String domaine;

    public TheseUniversitaire() {

    }

    public String getUniversite() {
        return universite;
    }

    public void setUniversite(String universite) {
        this.universite = universite;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    @Override
    void afficherDetails() {

    }

    @Override
    public String toString() {
        return "TheseUniversitaire{" +
                "universite='" + universite + '\'' +
                ", domaine='" + domaine + '\'' +
                '}';
    }
}
