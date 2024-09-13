package DAO.Intefaces;

import metier.Model.JournalScientifique;
import metier.Model.Livre;

public interface JournalDAO extends DAO<JournalScientifique>{
    void insertToDocuments(JournalScientifique journal);
}
