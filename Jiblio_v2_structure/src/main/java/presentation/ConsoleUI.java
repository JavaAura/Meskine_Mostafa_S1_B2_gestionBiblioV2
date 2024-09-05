package presentation;

import metier.Bibliotheque;
import metier.Livre;
import metier.Magazine;
import utilitaire.DateUtils;
import utilitaire.Validation;

import java.util.Scanner;
import java.util.UUID;

public class ConsoleUI {
    public ConsoleUI() {
        while (true) {
            this.mainMenu();
            Bibliotheque biblio = new Bibliotheque();
            Scanner input = new Scanner(System.in);
            int choix = input.nextInt();
            switch (choix) {
                case 1 -> {
                    this.typeMenu("Ajouter");
                    int ajouterType = input.nextInt();
                    this.documentType(ajouterType, "ajouter");
                }
                case 2 -> {
                    this.typeMenu("Emprunter");
                    int emprunterType = input.nextInt();
                    this.documentType(emprunterType, "emprunter");
                }
                case 3 -> {
                    this.typeMenu("Retourner");
                    int retournerType = input.nextInt();
                    this.documentType(retournerType, "retourner");
                }
                case 4 -> {
                    biblio.afficherTout();
                }
                case 5 -> {
                    System.out.print("donner le titre du document a rechercher: ");
                    input.nextLine();
                    String titre = input.nextLine();
                    biblio.rechercher(titre);
                }
                case 6 -> {
                    return;
                }
            }
        }
    }

    public void mainMenu() {
        System.out.println("1.Ajouter un document");
        System.out.println("2.Emprunter un document");
        System.out.println("3.Retourner un document");
        System.out.println("4.Afficher tous les documents");
        System.out.println("5.Rechercher un document");
        System.out.println("6.Quitter");
        System.out.print("=> ");
    }

    public void typeMenu(String action) {
        System.out.println("1." + action + " un Livre");
        System.out.println("2." + action + " un Magazine");
        System.out.print("=> ");
    }

    public void addMenu(String type) {
        Scanner input = new Scanner(System.in);
        Bibliotheque biblio = new Bibliotheque();

        if (type.equals("livre")) {
            Livre livre = new Livre();

            String titre = this.getStringInput(input, "1.titre du livre: ");
            livre.setTitre(titre);

            int isbn = this.getIntInput(input, "2.ISBN: ");
            livre.setISBN(isbn);

            String auteur = this.getStringInput(input, "3.nom de l'auteur: ");
            livre.setAuteur(auteur);

            String date = this.getDateInput(input);
            livre.setDatePublication(date);

            int nombreDePages = this.getIntInput(input, "5.nombre de pages: ");
            livre.setNombreDePages(nombreDePages);

            livre.setBorrowed(false);
            livre.setId(UUID.randomUUID());


            biblio.ajouter(livre);
        } else {
            Magazine magazine = new Magazine();

            String titre = this.getStringInput(input, "1.titre du magazine: ");
            magazine.setTitre(titre);

            int numero = this.getIntInput(input, "2.numero du magazine: ");
            magazine.setNumero(numero);

            String auteur = this.getStringInput(input, "3.nom de l'auteur: ");
            magazine.setAuteur(auteur);

            String date = this.getDateInput(input);
            magazine.setDatePublication(date);

            int nombreDePages = this.getIntInput(input, "5.nombre de pages: ");
            magazine.setNombreDePages(nombreDePages);

            magazine.setBorrowed(false);
            magazine.setId(UUID.randomUUID());

            biblio.ajouter(magazine);
        }
    }

    public void documentType(int docType, String operation) {
        Scanner input = new Scanner(System.in);
        Bibliotheque biblio = new Bibliotheque();
        if (operation.equals("ajouter")) {
            switch (docType) {
                case 1:
                    this.addMenu("livre");
                    break;
                case 2:
                    this.addMenu("magazine");
                    break;
            }
        } else if (operation.equals("emprunter")) {
            switch (docType) {
                case 1:
                    System.out.print("donner le titre du livre a emprunter: ");
                    String livreTitre = input.nextLine();
                    biblio.emprunter(livreTitre, "livre");
                    break;
                case 2:
                    System.out.print("donner le titre du magazine a emprunter: ");
                    String magTitre = input.nextLine();
                    biblio.emprunter(magTitre, "magazine");
                    break;
            }
        } else {
            switch (docType) {
                case 1:
                    System.out.print("donner le titre du livre a retourner: ");
                    String livreTitre = input.nextLine();
                    biblio.retourner(livreTitre, "livre");
                    break;
                case 2:
                    System.out.print("donner le titre du magazine a retourner: ");
                    String magTitre = input.nextLine();
                    biblio.retourner(magTitre, "magazine");
                    break;
            }
        }
    }

    public String getDateInput(Scanner scan) {
        String date;
        while (true) {
            System.out.print("4.date de publication: ");
            date = scan.nextLine();
            if (DateUtils.isValidDate(date)) {
                break;
            } else {
                System.out.println("Format de date invalide. Veuillez entrer une date au format dd/MM/yyyy.");
            }
        }
        return date;
    }

    public int getIntInput(Scanner scan, String message) {
        int number;
        while (true) {
            System.out.print(message);
            if (scan.hasNextInt()) {
                number = scan.nextInt();
                scan.nextLine();
                break;
            } else {
                System.out.println("Entrée invalide. Veuillez entrer un nombre entier.");
                scan.next();
            }
        }
        return number;
    }

    public String getStringInput(Scanner scan, String message) {
        String name;
        while (true) {
            System.out.print(message);
            name = scan.nextLine();
            if (Validation.isValidName(name)) {
                break;
            } else {
                System.out.println("Entrée invalide!");
            }
        }
        return name;
    }

}
