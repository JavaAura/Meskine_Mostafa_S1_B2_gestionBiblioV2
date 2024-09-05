package metier;

import java.util.ArrayList;

public class Magazine extends Document{
    private int numero;
    private static ArrayList<Magazine> magazines = new ArrayList<Magazine>();

    public void ajouter(){

    }
    public void emprunter(){

    }
    public void retourner(){

    }
    public void afficherDetails(){

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
                "numero=" + numero +
                "} " + super.toString();
    }
}
