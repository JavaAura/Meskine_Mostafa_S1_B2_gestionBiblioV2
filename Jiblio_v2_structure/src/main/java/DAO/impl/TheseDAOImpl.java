package DAO.impl;

import DAO.Intefaces.TheseDAO;
import metier.Database.DbConnection;
import metier.Model.Livre;
import metier.Model.TheseUniversitaire;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class TheseDAOImpl implements TheseDAO {
    @Override
    public List<TheseUniversitaire> getAll() {
        List<TheseUniversitaire> theses = new ArrayList<>();
        try {
            Connection conn = DbConnection.getInstance();
            String query = "SELECT * FROM thesesUniversitaire";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                TheseUniversitaire these = new TheseUniversitaire();
                these.setId((UUID) rs.getObject("id"));
                these.setTitre(rs.getString("titre"));
                these.setAuteur(rs.getString("auteur"));
                these.setDatePublication(rs.getString("datePublication"));
                these.setNombreDePages(rs.getInt("nombredepages"));
                these.setUniversite(rs.getString("universite"));
                these.setDomaine(rs.getString("domaine"));

                theses.add(these);
            }
            rs.close();
            ps.close();
            DbConnection.closeConnection();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return theses;
    }

    @Override
    public TheseUniversitaire get(UUID id) {
        TheseUniversitaire these = null;
        try {
            Connection conn = DbConnection.getInstance();
            String query = "SELECT * FROM thesesUniversitaire WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                these = new TheseUniversitaire();
                these.setId((UUID) rs.getObject("id"));
                these.setTitre(rs.getString("titre"));
                these.setAuteur(rs.getString("auteur"));
                these.setDatePublication(rs.getString("datePublication"));
                these.setNombreDePages(rs.getInt("nombredepages"));
                these.setUniversite(rs.getString("universite"));
                these.setDomaine(rs.getString("domaine"));
            }

            rs.close();
            ps.close();
            DbConnection.closeConnection();
        } catch (SQLException e) {
            System.out.println("Error retrieving These: " + e.getMessage());
        }
        return these;
    }

    @Override
    public void save(TheseUniversitaire these) {
        try {
            Connection conn = DbConnection.getInstance();
            String query = "INSERT INTO thesesUniversitaire (id, titre, auteur, datePublication, nombredepages, universite, domaine) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, these.getId());
            ps.setString(2, these.getTitre());
            ps.setString(3, these.getAuteur());
            ps.setString(4, these.getDatePublication());
            ps.setInt(5, these.getNombreDePages());
            ps.setString(6, these.getUniversite());
            ps.setString(7, these.getDomaine());

            int result = ps.executeUpdate();
            ps.close();
            DbConnection.closeConnection();

            if (result != 0) {
                System.out.println("these ajouté avec succès !");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void update(TheseUniversitaire these) {
        try {
            Connection conn = DbConnection.getInstance();
            String query = "UPDATE thesesUniversitaire SET titre = ?, auteur = ?, datePublication = ?, nombredepages = ?, domaine = ?, universite = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, these.getTitre());
            ps.setString(2, these.getAuteur());
            ps.setString(3, these.getDatePublication());
            ps.setInt(4, these.getNombreDePages());
            ps.setString(5, these.getDomaine());
            ps.setString(6, these.getUniversite());
            ps.setObject(7, these.getId());

            int result = ps.executeUpdate();

            if (result > 0) {
                System.out.println("These updated successfully!");
            }

            ps.close();
            DbConnection.closeConnection();
        } catch (SQLException e) {
            System.out.println("Error updating these: " + e.getMessage());
        }
    }

    @Override
    public boolean delete(UUID id) {
        boolean isDeleted = false;
        try {
            Connection conn = DbConnection.getInstance();
            String query = "DELETE FROM thesesUniversitaire WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setObject(1, id);

            int result = ps.executeUpdate();

            if (result > 0) {
                isDeleted = true;
                System.out.println("These deleted successfully!");
            } else {
                System.out.println("No these found with the provided ID.");
            }

            ps.close();
            DbConnection.closeConnection();
        } catch (SQLException e) {
            System.out.println("Error deleting these: " + e.getMessage());
        }

        return isDeleted;
    }

    @Override
    public void insertToDocuments(TheseUniversitaire these) {
        try {
            Connection conn = DbConnection.getInstance();
            String query = "INSERT INTO documents (id, titre, auteur, datePublication, nombredepages) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, these.getId());
            ps.setString(2, these.getTitre());
            ps.setString(3, these.getAuteur());
            ps.setString(4, these.getDatePublication());
            ps.setInt(5, these.getNombreDePages());

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
}
