package metier.Model;


public class Magazine extends Document {
    private int numero;

    public void afficherDetails() {
        System.out.println("ID: " + getId());
        System.out.println("Titre: " + getTitre());
        System.out.println("Auteur: " + getAuteur());
        System.out.println("Date de publication: " + getDatePublication());
        System.out.println("Nombre de pages: " + getNombreDePages());
        System.out.println("Numéro: " + getNumero());
        System.out.println("-------------");
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "numero=" + numero + super.toString() +
                "} ";
    }
}
