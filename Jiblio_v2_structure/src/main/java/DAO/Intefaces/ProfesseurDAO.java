package DAO.Intefaces;

import metier.Model.Etudiant;
import metier.Model.Professeur;

public interface ProfesseurDAO extends DAO<Professeur>{
    void insertToUser(Professeur professeur);
}
