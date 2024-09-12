package metier.Model;

import java.util.UUID;

public class Borrowed {
    private UUID id;
    private UUID utilisateur_id;
    private UUID document_id;
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
        return utilisateur_id;
    }

    public void setUtilisateur_id(UUID utilisateur_id) {
        this.utilisateur_id = utilisateur_id;
    }

    public UUID getDocument_id() {
        return document_id;
    }

    public void setDocument_id(UUID document_id) {
        this.document_id = document_id;
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
