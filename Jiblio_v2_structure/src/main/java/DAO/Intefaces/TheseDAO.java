package DAO.Intefaces;

import metier.Model.TheseUniversitaire;

public interface TheseDAO extends DAO<TheseUniversitaire>{
    void insertToDocuments(TheseUniversitaire these);
}
