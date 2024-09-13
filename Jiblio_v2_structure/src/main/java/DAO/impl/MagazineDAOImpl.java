package DAO.impl;

import DAO.Intefaces.MagazineDAO;
import metier.Database.DbConnection;
import metier.Interfaces.Empruntable;
import metier.Model.Magazine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MagazineDAOImpl implements MagazineDAO,Empruntable {

    @Override
    public List<Magazine> getAll() {
        List<Magazine> magazines = new ArrayList<>();
        try {
            Connection conn = DbConnection.getInstance();
            String query = "SELECT * FROM magazines";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Magazine magazine = new Magazine();
                magazine.setId((UUID) rs.getObject("id"));
                magazine.setTitre(rs.getString("titre"));
                magazine.setAuteur(rs.getString("auteur"));
                magazine.setDatePublication(rs.getString("datePublication"));
                magazine.setNombreDePages(rs.getInt("nombredepages"));
                magazine.setNumero(rs.getInt("numero"));

                magazines.add(magazine);
            }
            rs.close();
            ps.close();
            DbConnection.closeConnection();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return magazines;
    }

    @Override
    public Magazine get(UUID id) {
        Magazine magazine = null;
        try {
            Connection conn = DbConnection.getInstance();
            String query = "SELECT * FROM magazines WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                magazine = new Magazine();
                magazine.setId((UUID) rs.getObject("id"));
                magazine.setTitre(rs.getString("titre"));
                magazine.setAuteur(rs.getString("auteur"));
                magazine.setDatePublication(rs.getString("datePublication"));
                magazine.setNombreDePages(rs.getInt("nombredepages"));
                magazine.setBorrowed(rs.getBoolean("isBorrowed"));
                magazine.setNumero(rs.getInt("numero"));
            }

            rs.close();
            ps.close();
            DbConnection.closeConnection();
        } catch (SQLException e) {
            System.out.println("Error retrieving magazine: " + e.getMessage());
        }

        return magazine;
    }

    @Override
    public void save(Magazine magazine) {
        try {
            Connection conn = DbConnection.getInstance();
            String query = "INSERT INTO magazines (id, titre, auteur, datePublication, nombredepages, numero) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, magazine.getId());
            ps.setString(2, magazine.getTitre());
            ps.setString(3, magazine.getAuteur());
            ps.setString(4, magazine.getDatePublication());
            ps.setInt(5, magazine.getNombreDePages());
            ps.setInt(6, magazine.getNumero());

            int result = ps.executeUpdate();

            ps.close();
            DbConnection.closeConnection();

            if (result != 0) {
                System.out.println("magazine ajouté avec succès !");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void update(Magazine magazine) {
        try {
            Connection conn = DbConnection.getInstance();
            String query = "UPDATE magazines SET titre = ?, auteur = ?, datePublication = ?, nombredepages = ?, numero = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, magazine.getTitre());
            ps.setString(2, magazine.getAuteur());
            ps.setString(3, magazine.getDatePublication());
            ps.setInt(4, magazine.getNombreDePages());
            ps.setInt(5, magazine.getNumero());
            ps.setObject(6, magazine.getId());

            int result = ps.executeUpdate();

            if (result > 0) {
                System.out.println("Magazine updated successfully!");
            }

            ps.close();
            DbConnection.closeConnection();
        } catch (SQLException e) {
            System.out.println("Error updating book: " + e.getMessage());
        }
    }

    @Override
    public boolean delete(UUID id) {
        boolean isDeleted = false;
        try {
            Connection conn = DbConnection.getInstance();
            String query = "DELETE FROM magazines WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setObject(1, id);

            int result = ps.executeUpdate();

            if (result > 0) {
                isDeleted = true;
                System.out.println("Magazine deleted successfully!");
            } else {
                System.out.println("No magazine found with the provided ID.");
            }

            ps.close();
            DbConnection.closeConnection();
        } catch (SQLException e) {
            System.out.println("Error deleting magazine: " + e.getMessage());
        }

        return isDeleted;
    }

    @Override
    public void insertToDocuments(Magazine magazine) {
        try {
            Connection conn = DbConnection.getInstance();
            String query = "INSERT INTO documents (id, titre, auteur, datePublication, nombredepages) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, magazine.getId());
            ps.setString(2, magazine.getTitre());
            ps.setString(3, magazine.getAuteur());
            ps.setString(4, magazine.getDatePublication());
            ps.setInt(5, magazine.getNombreDePages());

            int result = ps.executeUpdate();
            ps.close();
            DbConnection.closeConnection();

            if (result != 0) {
                System.out.println("!");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void emprunter(UUID id) {
        try {
            Connection conn = DbConnection.getInstance();
            String query = "UPDATE magazines SET isBorrowed = true WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, id);

            int result = ps.executeUpdate();

            String query2 = "UPDATE documents SET isBorrowed = true WHERE id = ?";
            PreparedStatement ps2 = conn.prepareStatement(query2);
            ps2.setObject(1, id);

            int result2 = ps2.executeUpdate();

            if (result > 0 && result2 > 0) {
                System.out.println("Magazine borrowed!");
            }

            ps.close();
            ps2.close();
            DbConnection.closeConnection();
        } catch (SQLException e) {
            System.out.println("Error borrowing magazine: " + e.getMessage());
        }
    }

    @Override
    public void retourner(UUID id) {
        try {
            Connection conn = DbConnection.getInstance();
            String query = "UPDATE magazines SET isBorrowed = false WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, id);

            int result = ps.executeUpdate();

            String query2 = "UPDATE documents SET isBorrowed = false WHERE id = ?";
            PreparedStatement ps2 = conn.prepareStatement(query2);
            ps2.setObject(1, id);

            int result2 = ps2.executeUpdate();

            if (result > 0 && result2 > 0) {
                System.out.println("Magazine returned!");
            }

            ps.close();
            ps2.close();
            DbConnection.closeConnection();
        } catch (SQLException e) {
            System.out.println("Error returning magazine: " + e.getMessage());
        }
    }
}
