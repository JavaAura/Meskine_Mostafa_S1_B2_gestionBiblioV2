package metier.Model;

import DAO.Intefaces.JournalDAO;
import DAO.Intefaces.LivreDAO;
import DAO.Intefaces.MagazineDAO;
import DAO.Intefaces.TheseDAO;
import DAO.impl.JournalDAOImpl;
import DAO.impl.LivreDAOImpl;
import DAO.impl.MagazineDAOImpl;
import DAO.impl.TheseDAOImpl;

import java.util.*;
import java.util.stream.Stream;

public class Bibliotheque {
    private static HashMap<UUID, Document> docsMap = new HashMap<UUID, Document>();
    private static MagazineDAO magazineDAO = new MagazineDAOImpl();
    private static LivreDAO livreDAO = new LivreDAOImpl();
    private static JournalDAO journalDAO = new JournalDAOImpl();
    private static TheseDAO theseDAO = new TheseDAOImpl();

    public void ajouter(Livre livre) {
        livreDAO.save(livre);
    }

    public void ajouter(Magazine magazine) {
        magazineDAO.save(magazine);
    }

    public void ajouter(JournalScientifique journal) {
        journalDAO.save(journal);
    }

    public void ajouter(TheseUniversitaire these) {
        theseDAO.save(these);
    }

//    public void ajouter(TheseUniversitaire theseUniversitaire) {
//        magazines.add(theseUniversitaire);
//        docsMap.put(theseUniversitaire.getId(), theseUniversitaire);
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

    public void showAllJournals() {
        List<JournalScientifique> journals = journalDAO.getAll();

        if (journals.isEmpty()) {
            System.out.println("No Journals found.");
        } else {
            for (JournalScientifique journal : journals) {
                System.out.println("ID: " + journal.getId());
                System.out.println("Title: " + journal.getTitre());
                System.out.println("Author: " + journal.getAuteur());
                System.out.println("Publication Date: " + journal.getDatePublication());
                System.out.println("Number of Pages: " + journal.getNombreDePages());
                System.out.println("Domaine de recherche: " + journal.getDomaineRecherche());
                System.out.println("-------------");
            }
        }
    }

    public void showAllTheses() {
        List<TheseUniversitaire> theses = theseDAO.getAll();

        if (theses.isEmpty()) {
            System.out.println("No Theses found.");
        } else {
            for (TheseUniversitaire these : theses) {
                System.out.println("ID: " + these.getId());
                System.out.println("Title: " + these.getTitre());
                System.out.println("Author: " + these.getAuteur());
                System.out.println("Publication Date: " + these.getDatePublication());
                System.out.println("Number of Pages: " + these.getNombreDePages());
                System.out.println("Domaine: " + these.getDomaine());
                System.out.println("Universite: " + these.getUniversite());
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

    public JournalScientifique getJournal(UUID id) {
        return journalDAO.get(id);
    }

    public void updateDocument(Livre livre) {
        livreDAO.update(livre);
    }

    public void updateDocument(Magazine magazine) {
        magazineDAO.update(magazine);
    }

    public void updateDocument(JournalScientifique journal) {
        journalDAO.update(journal);
    }

    public void deleteDocument(String type, UUID id) {
        switch (type) {
            case "livre" -> {
                livreDAO.delete(id);
            }
            case "magazine" -> {
                magazineDAO.delete(id);
            }
            case "journal" -> {
                journalDAO.delete(id);
            }
        }
    }

    public void rechercher(String titre) {
//        boolean found = false;
//        for (Document doc : docsMap.values()) {
//            if (doc.getTitre().equalsIgnoreCase(titre)) {
//                System.out.println(doc);
//                found = true;
////                break;
//            }
//        }
//        if (!found) {
//            System.out.println("aucun document trouvé sous le titre '" + titre + "'");
//        }
    }
}
