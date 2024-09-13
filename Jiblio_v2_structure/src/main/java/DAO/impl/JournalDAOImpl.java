package DAO.impl;

import DAO.Intefaces.JournalDAO;
import metier.Database.DbConnection;
import metier.Interfaces.Empruntable;
import metier.Model.JournalScientifique;
import metier.Model.Livre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class JournalDAOImpl implements JournalDAO, Empruntable {
    @Override
    public List<JournalScientifique> getAll() {
        List<JournalScientifique> journals = new ArrayList<>();
        try {
            Connection conn = DbConnection.getInstance();
            String query = "SELECT * FROM journalScientifiques";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                JournalScientifique journal = new JournalScientifique();
                journal.setId((UUID) rs.getObject("id"));
                journal.setTitre(rs.getString("titre"));
                journal.setAuteur(rs.getString("auteur"));
                journal.setDatePublication(rs.getString("datePublication"));
                journal.setDomaineRecherche(rs.getString("domaineRecherche"));
                journal.setNombreDePages(rs.getInt("nombredepages"));

                journals.add(journal);
            }
            rs.close();
            ps.close();
            DbConnection.closeConnection();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return journals;
    }

    @Override
    public JournalScientifique get(UUID id) {
        JournalScientifique journal = null;
        try {
            Connection conn = DbConnection.getInstance();
            String query = "SELECT * FROM journalScientifiques WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                journal = new JournalScientifique();
                journal.setId((UUID) rs.getObject("id"));
                journal.setTitre(rs.getString("titre"));
                journal.setAuteur(rs.getString("auteur"));
                journal.setDatePublication(rs.getString("datePublication"));
                journal.setDomaineRecherche(rs.getString("domaineRecherche"));
                journal.setNombreDePages(rs.getInt("nombredepages"));
            }

            rs.close();
            ps.close();
            DbConnection.closeConnection();
        } catch (SQLException e) {
            System.out.println("Error retrieving book: " + e.getMessage());
        }

        return journal;
    }

    @Override
    public void save(JournalScientifique journal) {
        try {
            Connection conn = DbConnection.getInstance();
            String query = "INSERT INTO journalScientifiques (id, titre, auteur, datePublication, nombredepages, domaineRecherche) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, journal.getId());
            ps.setString(2, journal.getTitre());
            ps.setString(3, journal.getAuteur());
            ps.setString(4, journal.getDatePublication());
            ps.setInt(5, journal.getNombreDePages());
            ps.setString(6, journal.getDomaineRecherche());

            int result = ps.executeUpdate();
            ps.close();
            DbConnection.closeConnection();

            if (result != 0) {
                System.out.println("journal ajouté avec succès !");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void update(JournalScientifique journal) {
        try {
            Connection conn = DbConnection.getInstance();
            String query = "UPDATE journalScientifiques SET titre = ?, auteur = ?, datePublication = ?, nombredepages = ?, domaineRecherche = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, journal.getTitre());
            ps.setString(2, journal.getAuteur());
            ps.setString(3, journal.getDatePublication());
            ps.setInt(4, journal.getNombreDePages());
            ps.setString(5, journal.getDomaineRecherche());
            ps.setObject(6, journal.getId());

            int result = ps.executeUpdate();

            if (result > 0) {
                System.out.println("Journal updated successfully!");
            }

            ps.close();
            DbConnection.closeConnection();
        } catch (SQLException e) {
            System.out.println("Error updating journal: " + e.getMessage());
        }
    }

    @Override
    public boolean delete(UUID id) {
        boolean isDeleted = false;
        try {
            Connection conn = DbConnection.getInstance();
            String query = "DELETE FROM journalScientifiques WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setObject(1, id);

            int result = ps.executeUpdate();

            if (result > 0) {
                isDeleted = true;
                System.out.println("Journal deleted successfully!");
            } else {
                System.out.println("No journal found with the provided ID.");
            }

            ps.close();
            DbConnection.closeConnection();
        } catch (SQLException e) {
            System.out.println("Error deleting journal: " + e.getMessage());
        }

        return isDeleted;
    }

    @Override
    public void insertToDocuments(JournalScientifique journal) {
        try {
            Connection conn = DbConnection.getInstance();
            String query = "INSERT INTO documents (id, titre, auteur, datePublication, nombredepages) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, journal.getId());
            ps.setString(2, journal.getTitre());
            ps.setString(3, journal.getAuteur());
            ps.setString(4, journal.getDatePublication());
            ps.setInt(5, journal.getNombreDePages());

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
            String query = "UPDATE journalScientifiques SET isBorrowed = true WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, id);

            int result = ps.executeUpdate();

            if (result > 0) {
                System.out.println("Journal borrowed!");
            }

            ps.close();
            DbConnection.closeConnection();
        } catch (SQLException e) {
            System.out.println("Error borrowing journal: " + e.getMessage());
        }
    }

    @Override
    public void retourner(UUID id) {
        try {
            Connection conn = DbConnection.getInstance();
            String query = "UPDATE journalScientifiques SET isBorrowed = false WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, id);

            int result = ps.executeUpdate();

            if (result > 0) {
                System.out.println("Journal returned!");
            }

            ps.close();
            DbConnection.closeConnection();
        } catch (SQLException e) {
            System.out.println("Error returning journal: " + e.getMessage());
        }
    }
}
