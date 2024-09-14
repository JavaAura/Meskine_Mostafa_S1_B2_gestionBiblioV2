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
        System.out.println("ID: " + getId());
        System.out.println("Titre: " + getTitre());
        System.out.println("Auteur: " + getAuteur());
        System.out.println("Date de publication: " + getDatePublication());
        System.out.println("Nombre de pages: " + getNombreDePages());
        System.out.println("Domaine: " +getDomaine());
        System.out.println("Université: " + getUniversite());
        System.out.println("-------------");
    }



    @Override
    public String toString() {
        return "TheseUniversitaire{" +
                "universite='" + universite + '\'' +
                ", domaine='" + domaine + '\'' +  super.toString() +
                "} ";
    }
}
