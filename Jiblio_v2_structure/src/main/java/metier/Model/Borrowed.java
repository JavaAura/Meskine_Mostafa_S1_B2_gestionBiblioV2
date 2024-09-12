package metier.Model;

import java.util.UUID;

public class Borrowed {
    private UUID id;
    private UUID utilisateurID;
    private UUID documentID;
    private boolean isBorrowed;
    private boolean isReserved;
    private String returnDate;

    public Borrowed() {

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUtilisateur_id() {
        return utilisateurID;
    }

    public void setUtilisateur_id(UUID utilisateurID) {
        this.utilisateurID = utilisateurID;
    }

    public UUID getDocument_id() {
        return documentID;
    }

    public void setDocument_id(UUID documentID) {
        this.documentID = documentID;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    public boolean isReserved() {
        return isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
}
