package DAO.impl;

import DAO.Intefaces.BorrowDAO;
import metier.Database.DbConnection;
import metier.Model.Borrowed;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
        String query = "INSERT INTO borrowing (id, utilisateur_id, document_id, returnDate) "
                + "VALUES (?, ?, ?, ?)";
        try (Connection conn = DbConnection.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setObject(1, borrowed.getId());
            pstmt.setObject(2, borrowed.getUtilisateur_id());
            pstmt.setObject(3, borrowed.getDocument_id());
            pstmt.setString(4, borrowed.getReturnDate());

            pstmt.executeUpdate();
            System.out.println("Borrowed record saved successfully!");

        } catch (Exception e) {
            System.out.println("Error saving borrowed record: " + e.getMessage());
        }
    }

    @Override
    public void update(Borrowed borrowed) {

    }

    @Override
    public boolean delete(UUID id) {
        return false;
    }

    @Override
    public boolean exists() {
        return false;
    }

}
