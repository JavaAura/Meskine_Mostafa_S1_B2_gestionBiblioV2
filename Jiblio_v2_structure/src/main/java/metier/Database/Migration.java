package metier.Database;

import java.sql.Connection;
import java.sql.Statement;

public class Migration {
//    Connection conn = DbConnection.getInstance();

    public static void createDocumentTable(Connection conn){
        Statement statement;
        try {
            String query = "CREATE TABLE Documents ("
                    + "ID SERIAL PRIMARY KEY, "
                    + "titre VARCHAR(50) NOT NULL, "
                    + "auteur VARCHAR(50) NOT NULL, "
                    + "datePublication VARCHAR(100) NOT NULL, "
                    + "nombreDePages INT, "
                    + "isBorrowed BOOLEAN DEFAULT FALSE, "
                    + "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";
            statement = conn.createStatement();
            statement.execute(query);
            System.out.println("table created successfully!");
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
