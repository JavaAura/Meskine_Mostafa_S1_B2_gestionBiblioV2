package metier.Interfaces;

import java.util.UUID;

public interface Empruntable {
    void emprunter(UUID id);
    void retourner(UUID id);
}
