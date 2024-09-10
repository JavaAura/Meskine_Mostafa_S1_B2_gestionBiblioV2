package DAO.impl;

import DAO.Intefaces.MagazineDAO;
import metier.Database.DbConnection;
import metier.Model.Livre;
import metier.Model.Magazine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MagazineDAOImpl implements MagazineDAO {

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

}
