package DAO.Intefaces;

import metier.Model.Borrowed;

import java.util.UUID;

public interface BorrowDAO extends DAO<Borrowed> {
    public boolean exists();
}
