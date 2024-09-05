package metier.Documents;

public class Livre extends Document {
    private int ISBN;

    public void emprunter() {

    }

    public void retourner() {

    }

    public void afficherDetails() {

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
