package metier.Model;


public class Magazine extends Document {
    private int numero;

    public void afficherDetails() {

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
