package metier.Database;

import java.sql.Connection;
import java.sql.Statement;

public class Migration {

    private Migration(){

    }

    public static void createDatabase(){
        Connection conn = DbConnection.getInstance();
        createDocumentsTable(conn);
        createLivresTable(conn);
        createMagazinesTable(conn);
        createJournalTable(conn);
        createTheseUniversitaireTable(conn);
        createUtilisateursTable(conn);
        createEtudiantTable(conn);
        createProfesseurTable(conn);
        DbConnection.closeConnection();
    }

    public static void createDocumentsTable(Connection conn){
        Statement statement;
        try {
            String query = "CREATE TABLE documents ("
                    + "id UUID PRIMARY KEY, "
                    + "titre VARCHAR(50) NOT NULL, "
                    + "auteur VARCHAR(50) NOT NULL, "
                    + "datePublication date NOT NULL, "
                    + "nombreDePages INT, "
                    + "isBorrowed BOOLEAN DEFAULT FALSE, "
                    + "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";
            statement = conn.createStatement();
            statement.execute(query);
            System.out.println("documents table created successfully!");
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public static void createLivresTable(Connection conn){
        Statement statement;
        try {
            String query = "CREATE TABLE livres ("
                    + "ISBN INT) INHERITS (documents)";
            statement = conn.createStatement();
            statement.execute(query);
            System.out.println("livres table created successfully!");
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public static void createMagazinesTable(Connection conn){
        Statement statement;
        try {
            String query = "CREATE TABLE magazines ("
                    + "numero INT) INHERITS (documents)";
            statement = conn.createStatement();
            statement.execute(query);
            System.out.println("magazines table created successfully!");
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public static void createJournalTable(Connection conn){
        Statement statement;
        try {
            String query = "CREATE TABLE journalScientifiques ("
                    + "domaineRecherche VARCHAR(200) NOT NULL) INHERITS (documents)";
            statement = conn.createStatement();
            statement.execute(query);
            System.out.println("JournalScientifiques table created successfully!");
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public static void createTheseUniversitaireTable(Connection conn){
        Statement statement;
        try {
            String query = "CREATE TABLE thesesUniversitaire ("
                    + "universite VARCHAR(100) NOT NULL, "
                    + "domaine VARCHAR(100) NOT NULL ) INHERITS (documents)";
            statement = conn.createStatement();
            statement.execute(query);
            System.out.println("ThesesUniversitaire table created successfully!");
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public static void createUtilisateursTable(Connection conn){
        Statement statement;
        try {
            String query1 = "CREATE TABLE utilisateurs ("
                    + "id UUID PRIMARY KEY, "
                    + "username VARCHAR(250) NOT NULL, "
                    + "email VARCHAR(250) UNIQUE NOT NULL, "
                    + "password VARCHAR(250) NOT NULL, "
                    + "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";
            statement = conn.createStatement();
            statement.executeUpdate(query1);
            System.out.println("users table created successfully!");
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public static void createEtudiantTable(Connection conn){
        Statement statement;
        try {
            String query = "CREATE TABLE etudiants ("
                    + "filiere_etudes VARCHAR(200)) INHERITS (utilisateurs)";
            statement = conn.createStatement();
            statement.execute(query);
            System.out.println("etudiant table created successfully!");
        }catch (Exception e){
            System.out.println(e);
        }
    }
    public static void createProfesseurTable(Connection conn){
        Statement statement;
        try {
            String query = "CREATE TABLE professeurs ("
                    + "modele_enseigne VARCHAR(200)) INHERITS (utilisateurs)";
            statement = conn.createStatement();
            statement.execute(query);
            System.out.println("professeur table created successfully!");
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
