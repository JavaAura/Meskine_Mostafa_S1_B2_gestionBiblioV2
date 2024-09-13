package metier.Model;

import DAO.impl.*;
import metier.Enum.Role;
import presentation.ConsoleUI;

import java.util.*;
import java.util.stream.Stream;

public class Bibliotheque {
    private static final MagazineDAOImpl magazineDAO = new MagazineDAOImpl();
    private static final LivreDAOImpl livreDAO = new LivreDAOImpl();
    private static final JournalDAOImpl journalDAO = new JournalDAOImpl();
    private static final TheseDAOImpl theseDAO = new TheseDAOImpl();
    private static final EtudiantDAOImpl etudiantDAO = new EtudiantDAOImpl();
    private static final ProfesseurDAOImpl professeurDAO = new ProfesseurDAOImpl();
    private static final BorrowDAOImpl borrowDAO = new BorrowDAOImpl();
    private static Role etudiant = Role.etudiant;
    private static Role professeur = Role.professeur;

    public void ajouter(Livre livre) {
        livreDAO.save(livre);
        livreDAO.insertToDocuments(livre);
    }

    public void ajouter(Magazine magazine) {
        magazineDAO.save(magazine);
        magazineDAO.insertToDocuments(magazine);
    }

    public void ajouter(JournalScientifique journal) {
        journalDAO.save(journal);
        journalDAO.insertToDocuments(journal);
    }

    public void ajouter(TheseUniversitaire these) {
        theseDAO.save(these);
        theseDAO.insertToDocuments(these);
    }

    public void ajouter(Etudiant etudiant) {
        etudiantDAO.save(etudiant);
        etudiantDAO.insertToUser(etudiant);
    }

    public void ajouter(Professeur professeur) {
        professeurDAO.save(professeur);
        professeurDAO.insertToUser(professeur);
    }


    public void borrow(String docType, Scanner scan) {
        Document documentToBorrow = getDocumentToBorrow(docType, scan);

        System.out.print("Enter the user type (etudiant/professeur): ");
        String userType = scan.nextLine();
        Utilisateur borrower = getBorrower(userType, scan);

        if ((documentToBorrow != null) && (borrower != null)) {
            if (documentToBorrow.isBorrowed) {
                System.out.println("you can't borrow this book!");
            } else {
                Borrowed borrowingRecord = new Borrowed();

                borrowingRecord.setId(UUID.randomUUID());
                borrowingRecord.setDocument_id(documentToBorrow.getId());
                borrowingRecord.setUtilisateur_id(borrower.getId());
                borrowingRecord.setBorrowed(true);
                borrowingRecord.setReserved(false);
                String date = ConsoleUI.getDateInput(scan, "4.return date: ");
                borrowingRecord.setReturnDate(date);

                livreDAO.emprunter(documentToBorrow.getId());
                borrowDAO.save(borrowingRecord);
            }
        }
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

    public void showAllEtudiant() {
        List<Etudiant> etudiants = etudiantDAO.getAll();

        if (etudiants.isEmpty()) {
            System.out.println("No users found.");
        } else {
            for (Etudiant etudiant : etudiants) {
                System.out.println("ID: " + etudiant.getId());
                System.out.println("Username: " + etudiant.getUsername());
                System.out.println("Email: " + etudiant.getEmail());
                System.out.println("-------------");
            }
        }
    }

    public void showAllTeachers() {
        List<Professeur> professeurs = professeurDAO.getAll();

        if (professeurs.isEmpty()) {
            System.out.println("No users found.");
        } else {
            for (Professeur professeur : professeurs) {
                System.out.println("ID: " + professeur.getId());
                System.out.println("Username: " + professeur.getUsername());
                System.out.println("Email: " + professeur.getEmail());
                System.out.println("-------------");
            }
        }
    }

    public void showAllDocuments() {
        List<Livre> livres = livreDAO.getAll();
        List<Magazine> magazines = magazineDAO.getAll();
        List<JournalScientifique> journals = journalDAO.getAll();
        List<TheseUniversitaire> theses = theseDAO.getAll();

        Stream<? extends Document> stream1 = livres.stream();
        Stream<? extends Document> stream2 = magazines.stream();
        Stream<? extends Document> stream3 = journals.stream();
        Stream<? extends Document> stream4 = theses.stream();

        Stream<Document> combinedStream = Stream.concat(
                Stream.concat(stream1, stream2),
                Stream.concat(stream3, stream4)
        );

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

    public TheseUniversitaire getThese(UUID id) {
        return theseDAO.get(id);
    }

    public Etudiant getEtudiant(UUID id) {
        return etudiantDAO.get(id);
    }

    public Professeur getProfesseur(UUID id) {
        return professeurDAO.get(id);
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

    public void updateDocument(TheseUniversitaire these) {
        theseDAO.update(these);
    }

    public void updateUser(Etudiant etudiant) {
        etudiantDAO.update(etudiant);
    }

    public void updateUser(Professeur professeur) {
        professeurDAO.update(professeur);
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
            case "these" -> {
                theseDAO.delete(id);
            }
        }
    }

    public void deleteUser(String type, UUID id) {
        switch (type) {
            case "etudiant" -> {
                etudiantDAO.delete(id);
            }
            case "professeur" -> {
                professeurDAO.delete(id);
            }
        }
    }

    public void rechercher(String titre) {

    }

    public Utilisateur getBorrower(String userType, Scanner scan) {
        Etudiant studentBorrower = null;
        Professeur teacherBorrower = null;
        switch (userType) {
            case "etudiant" -> {
                showAllEtudiant();
                System.out.print("Enter the borrower ID (student ID): ");
                try {
                    String idString = scan.nextLine();
                    UUID etudiantID = UUID.fromString(idString);
                    studentBorrower = getEtudiant(etudiantID);
                    if (studentBorrower == null) {
                        System.out.println("No user found with the provided ID.");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid UUID format. Please enter a valid UUID.");
                }
            }
            case "professeur" -> {
                showAllTeachers();
                System.out.print("Enter the borrower ID (teacher ID): ");
                try {
                    String idString = scan.nextLine();
                    UUID teacherID = UUID.fromString(idString);
                    teacherBorrower = getProfesseur(teacherID);
                    if (teacherBorrower == null) {
                        System.out.println("No user found with the provided ID.");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid UUID format. Please enter a valid UUID.");
                }
            }
        }
        if (studentBorrower != null) {
            return studentBorrower;
        } else {
            return teacherBorrower;
        }
    }

    public Document getDocumentToBorrow(String DocumentType, Scanner scan) {
        Livre bookToBorrow = null;
        Magazine magazineToBorrow = null;
        JournalScientifique journalToBorrow = null;
        TheseUniversitaire theseToBorrow = null;
        switch (DocumentType) {
            case "livre" -> {
                System.out.print("Enter the book ID to borrow: ");
                try {
                    String idString = scan.nextLine();
                    UUID bookID = UUID.fromString(idString);
                    bookToBorrow = getBook(bookID);
                    if (bookToBorrow == null) {
                        System.out.println("No book found with the provided ID.");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid UUID format. Please enter a valid UUID.");
                }
            }
            case "magazine" -> {

            }
        }

        if (bookToBorrow != null) {
            return bookToBorrow;
        } else if (magazineToBorrow != null) {
            return magazineToBorrow;
        } else if (journalToBorrow != null) {
            return journalToBorrow;
        } else {
            return theseToBorrow;
        }
    }
}
