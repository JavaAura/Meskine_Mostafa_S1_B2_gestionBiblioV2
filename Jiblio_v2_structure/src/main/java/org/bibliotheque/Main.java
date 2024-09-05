package org.bibliotheque;

import metier.Database.DbConnection;
import metier.Database.Migration;

import java.sql.Connection;


public class Main {
    public static void main(String[] args) {
        Connection conn = DbConnection.getInstance();
        Migration.createDocumentTable(conn);
        DbConnection.closeConnection();
    }
}