package org.bibliotheque;

import metier.Database.DbConnection;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection conn = DbConnection.getInstance();
    }
}