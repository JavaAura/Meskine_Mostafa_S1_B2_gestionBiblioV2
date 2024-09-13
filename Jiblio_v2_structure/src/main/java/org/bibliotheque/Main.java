package org.bibliotheque;

import metier.Database.Migration;
import presentation.ConsoleUI;


public class Main {
    public static void main(String[] args) {
//        Migration.createDatabase();
        new ConsoleUI();
    }
}