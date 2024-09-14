package metier.Interfaces;

import metier.Model.Borrowed;

import java.util.UUID;

public interface Reservable {
    public void reserver(Borrowed borrowed);
    public void annulerReservation(UUID id);
}
