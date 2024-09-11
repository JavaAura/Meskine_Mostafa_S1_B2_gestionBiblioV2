package org.bibliotheque;

import metier.Database.DbConnection;
import metier.Database.Migration;
import presentation.ConsoleUI;

import java.sql.Connection;


public class Main {
    public static void main(String[] args) {
        Connection conn = DbConnection.getInstance();
        Migration.createUtilisateursTable(conn);
        Migration.createEtudiantTable(conn);
        Migration.createProfesseurTable(conn);
        DbConnection.closeConnection();
        new ConsoleUI();
    }
}