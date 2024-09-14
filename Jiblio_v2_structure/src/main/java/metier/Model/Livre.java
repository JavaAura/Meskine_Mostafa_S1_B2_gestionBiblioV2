package metier.Model;

public class Livre extends Document {
    private int ISBN;

    public void afficherDetails() {
        System.out.println("ID: " + getId());
        System.out.println("Titre: " + getTitre());
        System.out.println("Auteur: " + getAuteur());
        System.out.println("Date de publication: " + getDatePublication());
        System.out.println("Nombre de pages: " + getNombreDePages());
        System.out.println("ISBN: " + getISBN());
        System.out.println("-------------");
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }

    public int getISBN() {
        return ISBN;
    }


    @Override
    public String toString() {
        return "Livre{" +
                "ISBN=" + ISBN + super.toString() +
                "} ";
    }
}
