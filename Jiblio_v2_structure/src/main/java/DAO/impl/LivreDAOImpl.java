package DAO.impl;

import DAO.Intefaces.LivreDAO;
import metier.Database.DbConnection;
import metier.Model.Livre;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class LivreDAOImpl implements LivreDAO {

    @Override
    public List<Livre> getAll() {
        return List.of();
    }

    @Override
    public void save(Livre livre) {
        try {
            String query = "INSERT INTO livres (id, titre, auteur, datePublication, nombredepages, isbn) VALUES (?, ?, ?, ?, ?, ?)";
            Connection conn = DbConnection.getInstance();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setObject(1, livre.getId());
            ps.setString(2, livre.getTitre());
            ps.setString(3, livre.getAuteur());
            ps.setString(4, livre.getDatePublication());
            ps.setInt(5, livre.getNombreDePages());
            ps.setInt(6, livre.getISBN());

            int result = ps.executeUpdate();
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

    }

    @Override
    public void delete(Livre livre) {

    }
}
