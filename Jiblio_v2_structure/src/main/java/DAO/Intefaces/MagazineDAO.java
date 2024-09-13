package DAO.Intefaces;

import metier.Model.Magazine;

public interface MagazineDAO extends DAO<Magazine>{
    void insertToDocuments(Magazine magazine);
}
