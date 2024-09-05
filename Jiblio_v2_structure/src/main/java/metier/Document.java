package metier;

import java.util.UUID;

abstract class Document {
    protected UUID id;
    protected String titre;
    protected String auteur;
    protected String datePublication;
    protected int nombreDePages;
    protected boolean isBorrowed;


    abstract void emprunter();

    abstract void retourner();

    abstract void afficherDetails();

    public String getTitre() {
        return titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public String getDatePublication() {
        return datePublication;
    }

    public int getNombreDePages() {
        return nombreDePages;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public void setNombreDePages(int nombreDePages) {
        this.nombreDePages = nombreDePages;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setDatePublication(String datePublication) {
        this.datePublication = datePublication;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                        ", titre='" + titre + '\'' +
                        ", auteur='" + auteur + '\'' +
                        ", datePublication='" + datePublication + '\'' +
                        ", nombreDePages=" + nombreDePages +
                        ", isBorrowed=" + isBorrowed;
    }
}
