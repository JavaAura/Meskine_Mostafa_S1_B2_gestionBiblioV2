package org.bibliotheque;

import metier.Database.DbConnection;
import metier.Database.Migration;
import presentation.ConsoleUI;

import java.sql.Connection;


public class Main {
    public static void main(String[] args) {
//        new ConsoleUI();
        Connection conn = DbConnection.getInstance();
//        Migration.createJournalTable(conn);
//        Migration.createTheseUniversitaire(conn);
        DbConnection.closeConnection();
    }
}