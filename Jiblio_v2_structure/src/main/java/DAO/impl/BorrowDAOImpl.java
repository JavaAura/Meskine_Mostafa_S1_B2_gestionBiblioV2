package DAO.impl;

import DAO.Intefaces.BorrowDAO;
import metier.Model.Borrowed;

import java.util.List;
import java.util.UUID;

public class BorrowDAOImpl implements BorrowDAO {

    @Override
    public List<Borrowed> getAll() {
        return List.of();
    }

    @Override
    public Borrowed get(UUID id) {
        return null;
    }

    @Override
    public void save(Borrowed borrowed) {

    }

    @Override
    public void update(Borrowed borrowed) {

    }

    @Override
    public boolean delete(UUID id) {
        return false;
    }
}
