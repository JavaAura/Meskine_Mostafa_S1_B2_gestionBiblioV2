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
    private static final List<Livre> livres = livreDAO.getAll();
    private static final List<Magazine> magazines = magazineDAO.getAll();
    private static final List<JournalScientifique> journals = journalDAO.getAll();
    private static final List<TheseUniversitaire> theses = theseDAO.getAll();
    private static final List<Etudiant> etudiants = etudiantDAO.getAll();
    private static final List<Professeur> professeurs = professeurDAO.getAll();

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
        Document documentToBorrow = getDocument(docType, scan, "borrow");

        System.out.print("Enter the user type (etudiant/professeur): ");
        String userType = scan.nextLine();
        Utilisateur borrower = getBorrower(userType, scan);

        if ((documentToBorrow != null) && (borrower != null)) {
            if (documentToBorrow.isBorrowed) {
                System.out.println("this document is already borrowed! try reserving it");
            } else {
                Borrowed borrowingRecord = new Borrowed();

                borrowingRecord.setId(UUID.randomUUID());
                borrowingRecord.setDocument_id(documentToBorrow.getId());
                borrowingRecord.setUtilisateur_id(borrower.getId());
                borrowingRecord.setBorrowed(true);
                borrowingRecord.setReserved(false);
                String date = ConsoleUI.getDateInput(scan, "4.return date: ");
                borrowingRecord.setReturnDate(date);

                switch (docType) {
                    case "livre" -> {
                        livreDAO.emprunter(documentToBorrow.getId());
                    }
                    case "magazine" -> {
                        magazineDAO.emprunter(documentToBorrow.getId());
                    }
                    case "journal" -> {
                        journalDAO.emprunter(documentToBorrow.getId());
                    }
                    case "these" -> {
                        theseDAO.emprunter(documentToBorrow.getId());
                    }
                }

                borrowDAO.save(borrowingRecord);
            }
        }
    }

    public void retourner(String docType, Scanner scan) {
        Borrowed borrowRecord = null;

        Document documentToBorrow = getDocument(docType, scan, "return");

        System.out.print("Enter the user type (etudiant/professeur): ");
        String userType = scan.nextLine();
        Utilisateur borrower = getBorrower(userType, scan);

        if ((documentToBorrow != null) && (borrower != null)) {
            borrowRecord = borrowDAO.exists(borrower.getId(), documentToBorrow.getId());
            if (borrowRecord != null && documentToBorrow.isBorrowed) {
                if (!borrowRecord.isBorrowed()) {
                    System.out.println("this document is not borrowed by this user!");
                } else {
                    switch (docType) {
                        case "livre" -> {
                            livreDAO.retourner(documentToBorrow.getId());
                        }
                        case "magazine" -> {
                            magazineDAO.retourner(documentToBorrow.getId());
                        }
                        case "journal" -> {
                            journalDAO.retourner(documentToBorrow.getId());
                        }
                        case "these" -> {
                            theseDAO.retourner(documentToBorrow.getId());
                        }
                    }

                    borrowDAO.delete(borrowRecord.getId());
                }
            } else {
                System.out.println("this document is not borrowed yet!");
            }
        }
    }

    public void reserver(String docType, Scanner scan) {
        Borrowed borrowRecord = null;

        Document documentToReserve = getDocument(docType, scan, "return");

        System.out.print("Enter the user type (etudiant/professeur): ");
        String userType = scan.nextLine();
        Utilisateur borrower = getBorrower(userType, scan);

        if ((documentToReserve != null) && (borrower != null)) {
            borrowRecord = borrowDAO.exists(borrower.getId(), documentToReserve.getId());
            if (borrowRecord == null && documentToReserve.isBorrowed) {

                borrowRecord = new Borrowed();

                borrowRecord.setId(UUID.randomUUID());
                borrowRecord.setDocument_id(documentToReserve.getId());
                borrowRecord.setUtilisateur_id(borrower.getId());
                borrowRecord.setBorrowed(false);
                borrowRecord.setReserved(true);

                borrowDAO.reserver(borrowRecord);

            } else if (borrowRecord != null && borrowRecord.isReserved()) {
                System.out.println("you already reserved this document!");
            } else if (borrowRecord != null && borrowRecord.isBorrowed()) {
                System.out.println("this document is borrowed by this user already, you can't reserve it!");
            } else {
                System.out.println("this document is not borrowed, try borrowing it directly!");
            }
        }
    }

    public void annulerReservation(String docType, Scanner scan) {
        Borrowed borrowRecord = null;

        Document documentToReserve = getDocument(docType, scan, "return");

        System.out.print("Enter the user type (etudiant/professeur): ");
        String userType = scan.nextLine();
        Utilisateur borrower = getBorrower(userType, scan);

        if ((documentToReserve != null) && (borrower != null)) {
            borrowRecord = borrowDAO.exists(borrower.getId(), documentToReserve.getId());
            if (borrowRecord != null && borrowRecord.isReserved()) {
                borrowDAO.annulerReservation(borrowRecord.getId());
            }else {
                System.out.println("no reservation record found!!");
            }
        }
    }

    public void showAllBooks() {

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
        Stream<Document> combinedStream = getCombinedStream();
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

    public Document getDocument(String DocumentType, Scanner scan, String Action) {
        Livre book = null;
        Magazine magazine = null;
        JournalScientifique journal = null;
        TheseUniversitaire these = null;
        switch (DocumentType) {
            case "livre" -> {
                System.out.print("Enter the book ID to " + Action + ": ");
                try {
                    String idString = scan.nextLine();
                    UUID bookID = UUID.fromString(idString);
                    book = getBook(bookID);
                    if (book == null) {
                        System.out.println("No book found with the provided ID.");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid UUID format. Please enter a valid UUID.");
                }
            }
            case "magazine" -> {
                System.out.print("Enter the magazine ID to " + Action + ": ");
                try {
                    String idString = scan.nextLine();
                    UUID magazineID = UUID.fromString(idString);
                    magazine = getMagazine(magazineID);
                    if (magazine == null) {
                        System.out.println("No magazine found with the provided ID.");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid UUID format. Please enter a valid UUID.");
                }
            }
            case "journal" -> {
                System.out.print("Enter the journal ID to " + Action + ": ");
                try {
                    String idString = scan.nextLine();
                    UUID journalID = UUID.fromString(idString);
                    journal = getJournal(journalID);
                    if (journal == null) {
                        System.out.println("No journal found with the provided ID.");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid UUID format. Please enter a valid UUID.");
                }
            }
            case "these" -> {
                System.out.print("Enter the these ID to " + Action + ": ");
                try {
                    String idString = scan.nextLine();
                    UUID theseID = UUID.fromString(idString);
                    these = getThese(theseID);
                    if (these == null) {
                        System.out.println("No these found with the provided ID.");
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid UUID format. Please enter a valid UUID.");
                }
            }
        }

        if (book != null) {
            return book;
        } else if (magazine != null) {
            return magazine;
        } else if (journal != null) {
            return journal;
        } else {
            return these;
        }
    }

    public Stream<Document> getCombinedStream(){
        Stream<? extends Document> stream1 = livres.stream();
        Stream<? extends Document> stream2 = magazines.stream();
        Stream<? extends Document> stream3 = journals.stream();
        Stream<? extends Document> stream4 = theses.stream();

        return Stream.concat(
                Stream.concat(stream1, stream2),
                Stream.concat(stream3, stream4)
        );
    }
}
