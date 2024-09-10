package DAO.impl;

import DAO.Intefaces.LivreDAO;
import metier.Database.DbConnection;
import metier.Model.Livre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LivreDAOImpl implements LivreDAO {

    @Override
    public List<Livre> getAll() {
        List<Livre> livres = new ArrayList<>();
        try {
            Connection conn = DbConnection.getInstance();
            String query = "SELECT * FROM livres";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Livre livre = new Livre();
                livre.setId((UUID) rs.getObject("id"));
                livre.setTitre(rs.getString("titre"));
                livre.setAuteur(rs.getString("auteur"));
                livre.setDatePublication(rs.getString("datePublication"));
                livre.setNombreDePages(rs.getInt("nombredepages"));
                livre.setISBN(rs.getInt("isbn"));

                livres.add(livre);
            }
            rs.close();
            ps.close();
            DbConnection.closeConnection();
        } catch (SQLException e) {
            System.out.println(e);
        }

        return livres;
    }

    @Override
    public Livre get(UUID id) {
        Livre livre = null;
        try {
            Connection conn = DbConnection.getInstance();
            String query = "SELECT * FROM livres WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, id);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                livre = new Livre();
                livre.setId((UUID) rs.getObject("id"));
                livre.setTitre(rs.getString("titre"));
                livre.setAuteur(rs.getString("auteur"));
                livre.setDatePublication(rs.getString("datePublication"));
                livre.setNombreDePages(rs.getInt("nombredepages"));
                livre.setISBN(rs.getInt("isbn"));
            }

            rs.close();
            ps.close();
            DbConnection.closeConnection();
        } catch (SQLException e) {
            System.out.println("Error retrieving book: " + e.getMessage());
        }

        return livre;
    }

    @Override
    public void save(Livre livre) {
        try {
            Connection conn = DbConnection.getInstance();
            String query = "INSERT INTO livres (id, titre, auteur, datePublication, nombredepages, isbn) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, livre.getId());
            ps.setString(2, livre.getTitre());
            ps.setString(3, livre.getAuteur());
            ps.setString(4, livre.getDatePublication());
            ps.setInt(5, livre.getNombreDePages());
            ps.setInt(6, livre.getISBN());

            int result = ps.executeUpdate();
            ps.close();
            DbConnection.closeConnection();

            if (result != 0) {
                System.out.println("livre ajouté avec succès !");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public void update(Livre livre) {
        try {
            Connection conn = DbConnection.getInstance();
            String query = "UPDATE livres SET titre = ?, auteur = ?, datePublication = ?, nombredepages = ?, isbn = ? WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, livre.getTitre());
            ps.setString(2, livre.getAuteur());
            ps.setString(3, livre.getDatePublication());
            ps.setInt(4, livre.getNombreDePages());
            ps.setInt(5, livre.getISBN());
            ps.setObject(6, livre.getId());

            int result = ps.executeUpdate();

            if (result > 0) {
                System.out.println("Book updated successfully!");
            }

            ps.close();
            DbConnection.closeConnection();
        } catch (SQLException e) {
            System.out.println("Error updating book: " + e.getMessage());
        }
    }

    @Override
    public void delete(Livre livre) {

    }
}
