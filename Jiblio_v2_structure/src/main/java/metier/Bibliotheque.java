package metier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class Bibliotheque {
    private static ArrayList<Livre> livres = new ArrayList<Livre>();
    private static ArrayList<Magazine> magazines = new ArrayList<Magazine>();
    private static HashMap<UUID, Document> docsMap = new HashMap<UUID, Document>();

    public void ajouter(Livre livre) {
        livres.add(livre);
        docsMap.put(livre.getId(), livre);
        System.out.println("livre ajouté avec succès !");
    }

    public void ajouter(Magazine magazine) {
        magazines.add(magazine);
        docsMap.put(magazine.getId(), magazine);
        System.out.println("magazine ajouté avec succès !");
    }

    public void emprunter(String titre, String type) {
        if (type.equals("livre")) {
            if (!livres.isEmpty()) {
                for (int i = 0; i < livres.size(); i++) {
                    if ((livres.get(i)).getTitre().equals(titre)) {
                        if ((livres.get(i)).isBorrowed()) {
                            System.out.println("ce livre est déjà emprunté !");
                        } else {
                            (livres.get(i)).setBorrowed(true);
                            System.out.println("livre emprunté avec succès !");
                        }
                        break;
                    }
                    if (i == (livres.size() - 1)) {
                        System.out.println("aucun livre trouvé sous ce titre !");
                    }
                }
            } else {
                System.out.println("ajoutez d’abord quelques livres !");
            }
        } else {
            if (!magazines.isEmpty()) {
                for (int i = 0; i < magazines.size(); i++) {
                    if ((magazines.get(i)).getTitre().equals(titre)) {
                        if ((magazines.get(i)).isBorrowed()) {
                            System.out.println("ce magazine est déjà emprunté !");
                        } else {
                            (magazines.get(i)).setBorrowed(true);
                            System.out.println("magazine emprunté avec succès !");
                        }
                        break;
                    }
                    if (i == (magazines.size() - 1)) {
                        System.out.println("aucun magazine trouvé sous ce titre !");
                    }
                }
            } else {
                System.out.println("ajoutez d’abord quelques magazines!");
            }
        }
    }

    public void retourner(String titre, String type) {
        if (type.equals("livre")) {
            if (!livres.isEmpty()) {
                for (int i = 0; i < livres.size(); i++) {
                    if ((livres.get(i)).getTitre().equals(titre)) {
                        if (!(livres.get(i)).isBorrowed()) {
                            System.out.println("empruntez d'abord ce livre pour le rendre !");
                        } else {
                            (livres.get(i)).setBorrowed(false);
                            System.out.println("livre retourné avec succès !");
                        }
                        break;
                    }
                    if (i == (livres.size() - 1)) {
                        System.out.println("aucun livre trouvé sous ce titre !");
                    }
                }
            } else {
                System.out.println("ajoutez d’abord quelques livres !");
            }
        } else {
            if (!magazines.isEmpty()) {
                for (int i = 0; i < magazines.size(); i++) {
                    if ((magazines.get(i)).getTitre().equals(titre)) {
                        if (!(magazines.get(i)).isBorrowed()) {
                            System.out.println("empruntez d'abord ce magazine pour le rendre !");
                        } else {
                            (magazines.get(i)).setBorrowed(false);
                            System.out.println("magazine retourné avec succès !");
                        }
                        break;
                    }
                    if (i == (magazines.size() - 1)) {
                        System.out.println("aucun magazine trouvé sous ce titre !");
                    }
                }
            } else {
                System.out.println("ajoutez d’abord quelques magazines!");
            }
        }
    }

    public void afficherTout() {
        for (int i = 0; i < livres.size(); i++) {
            System.out.println(livres.get(i));
        }
        for (int i = 0; i < magazines.size(); i++) {
            System.out.println(magazines.get(i));
        }
    }

    public void rechercher(String titre) {
        boolean found = false;
        for (Document doc : docsMap.values()) {
            if (doc.getTitre().equalsIgnoreCase(titre)) {
                System.out.println(doc);
                found = true;
//                break;
            }
        }
        if (!found) {
            System.out.println("aucun document trouvé sous le titre '" + titre +"'");
        }
    }
}
