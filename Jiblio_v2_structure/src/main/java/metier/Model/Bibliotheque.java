package metier.Model;

import DAO.Intefaces.LivreDAO;
import DAO.Intefaces.MagazineDAO;
import DAO.impl.LivreDAOImpl;
import DAO.impl.MagazineDAOImpl;

import java.util.*;
import java.util.stream.Stream;

public class Bibliotheque {
    private static HashMap<UUID, Document> docsMap = new HashMap<UUID, Document>();
    private static MagazineDAO magazineDAO = new MagazineDAOImpl();
    private static LivreDAO livreDAO = new LivreDAOImpl();

    public void ajouter(Livre livre) {
        livreDAO.save(livre);
    }

    public void ajouter(Magazine magazine) {
        magazineDAO.save(magazine);
    }

//    public void ajouter(TheseUniversitaire theseUniversitaire) {
//        magazines.add(theseUniversitaire);
//        docsMap.put(theseUniversitaire.getId(), theseUniversitaire);
//        System.out.println("magazine ajouté avec succès !");
//    }

//    public void ajouter(JournalScientifique journalScientifique) {
//        magazines.add(journalScientifique);
//        docsMap.put(journalScientifique.getId(), journalScientifique);
//        System.out.println("magazine ajouté avec succès !");
//    }


    public void emprunter(String titre, String type) {
//        if (type.equals("livre")) {
//            if (!livres.isEmpty()) {
//                for (int i = 0; i < livres.size(); i++) {
//                    if ((livres.get(i)).getTitre().equals(titre)) {
//                        if ((livres.get(i)).isBorrowed()) {
//                            System.out.println("ce livre est déjà emprunté !");
//                        } else {
//                            (livres.get(i)).setBorrowed(true);
//                            System.out.println("livre emprunté avec succès !");
//                        }
//                        break;
//                    }
//                    if (i == (livres.size() - 1)) {
//                        System.out.println("aucun livre trouvé sous ce titre !");
//                    }
//                }
//            } else {
//                System.out.println("ajoutez d’abord quelques livres !");
//            }
//        } else {
//            if (!magazines.isEmpty()) {
//                for (int i = 0; i < magazines.size(); i++) {
//                    if ((magazines.get(i)).getTitre().equals(titre)) {
//                        if ((magazines.get(i)).isBorrowed()) {
//                            System.out.println("ce magazine est déjà emprunté !");
//                        } else {
//                            (magazines.get(i)).setBorrowed(true);
//                            System.out.println("magazine emprunté avec succès !");
//                        }
//                        break;
//                    }
//                    if (i == (magazines.size() - 1)) {
//                        System.out.println("aucun magazine trouvé sous ce titre !");
//                    }
//                }
//            } else {
//                System.out.println("ajoutez d’abord quelques magazines!");
//            }
//        }
    }

    public void retourner(String titre, String type) {
//        if (type.equals("livre")) {
//            if (!livres.isEmpty()) {
//                for (int i = 0; i < livres.size(); i++) {
//                    if ((livres.get(i)).getTitre().equals(titre)) {
//                        if (!(livres.get(i)).isBorrowed()) {
//                            System.out.println("empruntez d'abord ce livre pour le rendre !");
//                        } else {
//                            (livres.get(i)).setBorrowed(false);
//                            System.out.println("livre retourné avec succès !");
//                        }
//                        break;
//                    }
//                    if (i == (livres.size() - 1)) {
//                        System.out.println("aucun livre trouvé sous ce titre !");
//                    }
//                }
//            } else {
//                System.out.println("ajoutez d’abord quelques livres !");
//            }
//        } else {
//            if (!magazines.isEmpty()) {
//                for (int i = 0; i < magazines.size(); i++) {
//                    if ((magazines.get(i)).getTitre().equals(titre)) {
//                        if (!(magazines.get(i)).isBorrowed()) {
//                            System.out.println("empruntez d'abord ce magazine pour le rendre !");
//                        } else {
//                            (magazines.get(i)).setBorrowed(false);
//                            System.out.println("magazine retourné avec succès !");
//                        }
//                        break;
//                    }
//                    if (i == (magazines.size() - 1)) {
//                        System.out.println("aucun magazine trouvé sous ce titre !");
//                    }
//                }
//            } else {
//                System.out.println("ajoutez d’abord quelques magazines!");
//            }
//        }
    }

    public void showAllBooks() {
        List<Livre> livres = livreDAO.getAll();

        if (livres.isEmpty()) {
            System.out.println("No books found.");
        } else {
            for (Livre livre : livres) {
                System.out.println("ID: " + livre.getId());
                System.out.println("Title: " + livre.getTitre());
                System.out.println("Author: " + livre.getAuteur());
                System.out.println("Publication Date: " + livre.getDatePublication());
                System.out.println("Number of Pages: " + livre.getNombreDePages());
                System.out.println("ISBN: " + livre.getISBN());
                System.out.println("-------------");
            }
        }
    }
    public void showAllMagazines() {
        List<Magazine> magazines = magazineDAO.getAll();

        if (magazines.isEmpty()) {
            System.out.println("No Magazines found.");
        } else {
            for (Magazine magazine : magazines) {
                System.out.println("ID: " + magazine.getId());
                System.out.println("Title: " + magazine.getTitre());
                System.out.println("Author: " + magazine.getAuteur());
                System.out.println("Publication Date: " + magazine.getDatePublication());
                System.out.println("Number of Pages: " + magazine.getNombreDePages());
                System.out.println("Numero: " + magazine.getNumero());
                System.out.println("-------------");
            }
        }
    }

    public void showAllDocuments() {
        List<Livre> livres = livreDAO.getAll();
        List<Magazine> magazines = magazineDAO.getAll();

        Stream<? extends Document> stream1 = livres.stream();
        Stream<? extends Document> stream2 = magazines.stream();

        Stream<Document> combinedStream = Stream.concat(stream1, stream2);
        combinedStream.forEach(System.out::println);
    }

    public Livre getBook(UUID id) {
        return livreDAO.get(id);
    }
    public Magazine getMagazine(UUID id) {
        return magazineDAO.get(id);
    }

    public void updateDocument(Livre livre) {
        livreDAO.update(livre);
    }
    public void updateDocument(Magazine magazine) {
        magazineDAO.update(magazine);
    }

    public void deleteDocument(String type, UUID id) {
        switch (type) {
            case "livre" -> {
                livreDAO.delete(id);
            }
            case "magazine" -> {
                magazineDAO.delete(id);
            }
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
            System.out.println("aucun document trouvé sous le titre '" + titre + "'");
        }
    }
}
