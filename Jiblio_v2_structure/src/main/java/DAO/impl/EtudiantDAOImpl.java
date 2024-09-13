package DAO.impl;

import DAO.Intefaces.EtudiantDAO;
import metier.Database.DbConnection;
import metier.Model.Etudiant;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EtudiantDAOImpl implements EtudiantDAO {
    @Override
    public List<Etudiant> getAll() {
        List<Etudiant> etudiants = new ArrayList<>();
        try {
            Connection conn = DbConnection.getInstance();
            String query = "SELECT * FROM etudiants";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Etudiant etudiant = new Etudiant();
                etudiant.setId((UUID) rs.getObject("id"));
                etudiant.setUsername(rs.getString("username"));
                etudiant.setEmail(rs.getString("email"));
                etudiant.setPassword(rs.getString("password"));
                etudiant.setFiliere_etudes(rs.getString("filiere_etudes"));

                etudiants.add(etudiant);
            }
            rs.close();
            ps.close();
            DbConnection.closeConnection();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return etudiants;
    }

    @Override
    public Etudiant get(UUID id) {
        Etudiant etudiant = null;
        try {
            Connection conn = DbConnection.getInstance();
            String query = "SELECT * FROM etudiants WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                etudiant = new Etudiant();
                etudiant.setId((UUID) rs.getObject("id"));
                etudiant.setUsername(rs.getString("username"));
                etudiant.setEmail(rs.getString("email"));
                etudiant.setPassword(rs.getString("password"));
                etudiant.setFiliere_etudes(rs.getString("filiere_etudes"));
            }

            rs.close();
            ps.close();
            DbConnection.closeConnection();
        } catch (SQLException e) {
            System.out.println("Error retrieving User: " + e.getMessage());
        }

        return etudiant;
    }

    @Override
    public void save(Etudiant etudiant) {
        try {
            Connection conn = DbConnection.getInstance();
            String query = "INSERT INTO etudiants (id, username, email, password, filiere_etudes) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, etudiant.getId());
            ps.setString(2, etudiant.getUsername());
            ps.setString(3, etudiant.getEmail());
            ps.setString(4, etudiant.getPassword());
            ps.setString(5, etudiant.getFiliere_etudes());

            int result = ps.executeUpdate();
            ps.close();
            DbConnection.closeConnection();

            if (result != 0) {
                System.out.print("etudiant ajouté avec succès !");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void update(Etudiant etudiant) {
        try {
            Connection conn = DbConnection.getInstance();
            String query = "UPDATE etudiants SET username = ?, email = ?, password = ?, filiere_etudes = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, etudiant.getUsername());
            ps.setString(2, etudiant.getEmail());
            ps.setString(3, etudiant.getPassword());
            ps.setString(4, etudiant.getFiliere_etudes());
            ps.setObject(5, etudiant.getId());

            int result = ps.executeUpdate();

            if (result > 0) {
                System.out.println("User updated successfully!");
            }

            ps.close();
            DbConnection.closeConnection();
        } catch (SQLException e) {
            System.out.println("Error updating user: " + e.getMessage());
        }
    }

    @Override
    public boolean delete(UUID id) {
        boolean isDeleted = false;
        try {
            Connection conn = DbConnection.getInstance();
            String query = "DELETE FROM etudiants WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setObject(1, id);

            int result = ps.executeUpdate();

            if (result > 0) {
                isDeleted = true;
                System.out.println("User deleted successfully!");
            } else {
                System.out.println("No user found with the provided ID.");
            }

            ps.close();
            DbConnection.closeConnection();
        } catch (SQLException e) {
            System.out.println("Error deleting user: " + e.getMessage());
        }

        return isDeleted;
    }

    @Override
    public void insertToUser(Etudiant etudiant) {
        try {
            Connection conn = DbConnection.getInstance();
            String query = "INSERT INTO utilisateurs (id, username, email, password) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, etudiant.getId());
            ps.setString(2, etudiant.getUsername());
            ps.setString(3, etudiant.getEmail());
            ps.setString(4, etudiant.getPassword());

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
