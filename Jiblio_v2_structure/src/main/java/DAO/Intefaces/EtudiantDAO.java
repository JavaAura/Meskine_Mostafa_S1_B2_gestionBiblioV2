package DAO.Intefaces;

import metier.Model.Etudiant;

public interface EtudiantDAO extends DAO<Etudiant>{
    void insertToUser(Etudiant etudiant);
}
