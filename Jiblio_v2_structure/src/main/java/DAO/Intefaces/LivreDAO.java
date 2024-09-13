package DAO.Intefaces;

import metier.Model.Livre;

public interface LivreDAO extends DAO<Livre> {
    void insertToDocuments(Livre livre);
}
