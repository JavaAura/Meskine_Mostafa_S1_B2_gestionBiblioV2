package DAO.impl;

import DAO.Intefaces.BorrowDAO;
import metier.Database.DbConnection;
import metier.Model.Borrowed;
import metier.Model.Livre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        String query = "INSERT INTO borrowing (id, utilisateur_id, document_id, isBorrowing, isReserving, returnDate) "
                + "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DbConnection.getInstance();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setObject(1, borrowed.getId());
            pstmt.setObject(2, borrowed.getUtilisateur_id());
            pstmt.setObject(3, borrowed.getDocument_id());
            pstmt.setBoolean(4, borrowed.isBorrowed());
            pstmt.setBoolean(5, borrowed.isReserved());
            pstmt.setString(6, borrowed.getReturnDate());

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
        boolean isDeleted = false;
        try {
            Connection conn = DbConnection.getInstance();
            String query = "DELETE FROM borrowing WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setObject(1, id);

            int result = ps.executeUpdate();

            if (result > 0) {
                isDeleted = true;
                System.out.println("document record deleted successfully!");
            } else {
                System.out.println("No document found with the provided ID.");
            }

            ps.close();
            DbConnection.closeConnection();
        } catch (SQLException e) {
            System.out.println("Error deleting record: " + e.getMessage());
        }

        return isDeleted;
    }

    @Override
    public Borrowed exists(UUID utilisateurID, UUID documentID) {
        Borrowed borrow = null;
        try {
            Connection conn = DbConnection.getInstance();
            String query = "SELECT * FROM borrowing WHERE utilisateur_id = ? AND document_id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, utilisateurID);
            ps.setObject(2, documentID);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                borrow = new Borrowed();
                borrow.setId((UUID) rs.getObject("id"));
                borrow.setUtilisateur_id((UUID) rs.getObject("utilisateur_id"));
                borrow.setDocument_id((UUID) rs.getObject("document_id"));
                borrow.setBorrowed(rs.getBoolean("isBorrowing"));
                borrow.setReserved(rs.getBoolean("isReserving"));
                borrow.setReturnDate(rs.getString("returnDate"));
            }

            rs.close();
            ps.close();
            DbConnection.closeConnection();
        } catch (SQLException e) {
            System.out.println("Error retrieving book: " + e.getMessage());
        }

        return borrow;
    }

}
