package DAO.impl;

import DAO.Intefaces.ProfesseurDAO;
import metier.Database.DbConnection;
import metier.Model.Professeur;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProfesseurDAOImpl implements ProfesseurDAO {
    @Override
    public List<Professeur> getAll() {
        List<Professeur> professeurs = new ArrayList<>();
        try {
            Connection conn = DbConnection.getInstance();
            String query = "SELECT * FROM professeurs";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Professeur etudiant = new Professeur();
                etudiant.setId((UUID) rs.getObject("id"));
                etudiant.setUsername(rs.getString("username"));
                etudiant.setEmail(rs.getString("email"));
                etudiant.setPassword(rs.getString("password"));
                etudiant.setModele_enseigne(rs.getString("modele_enseigne"));

                professeurs.add(etudiant);
            }
            rs.close();
            ps.close();
            DbConnection.closeConnection();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return professeurs;
    }

    @Override
    public Professeur get(UUID id) {
        Professeur professeur = null;
        try {
            Connection conn = DbConnection.getInstance();
            String query = "SELECT * FROM professeurs WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                professeur = new Professeur();
                professeur.setId((UUID) rs.getObject("id"));
                professeur.setUsername(rs.getString("username"));
                professeur.setEmail(rs.getString("email"));
                professeur.setPassword(rs.getString("password"));
                professeur.setModele_enseigne(rs.getString("modele_enseigne"));
            }

            rs.close();
            ps.close();
            DbConnection.closeConnection();
        } catch (SQLException e) {
            System.out.println("Error retrieving User: " + e.getMessage());
        }

        return professeur;
    }

    @Override
    public void save(Professeur professeur) {
        try {
            Connection conn = DbConnection.getInstance();
            String query = "INSERT INTO professeurs (id, username, email, password, modele_enseigne) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, professeur.getId());
            ps.setString(2, professeur.getUsername());
            ps.setString(3, professeur.getEmail());
            ps.setString(4, professeur.getPassword());
            ps.setString(5, professeur.getModele_enseigne());

            int result = ps.executeUpdate();
            ps.close();
            DbConnection.closeConnection();

            if (result != 0) {
                System.out.println("professeur ajouté avec succès !");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void update(Professeur professeur) {
        try {
            Connection conn = DbConnection.getInstance();
            String query = "UPDATE professeurs SET username = ?, email = ?, password = ?, modele_enseigne = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, professeur.getUsername());
            ps.setString(2, professeur.getEmail());
            ps.setString(3, professeur.getPassword());
            ps.setString(4, professeur.getModele_enseigne());
            ps.setObject(5, professeur.getId());

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
            String query = "DELETE FROM professeurs WHERE id = ?";
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
}
