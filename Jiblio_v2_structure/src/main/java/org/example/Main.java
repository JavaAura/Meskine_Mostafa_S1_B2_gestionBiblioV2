package org.example;

import Database.DbConnection;

public class Main {
    public static void main(String[] args) {
        DbConnection.getInstance();
        DbConnection.closeConnection();
    }
}