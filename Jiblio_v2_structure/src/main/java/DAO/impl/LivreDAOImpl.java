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
        Connection conn = DbConnection.getInstance();
        try {
            String query = "INSERT INTO livres (titre, auteur, datePublication, nombredepages, isbn) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, livre.getTitre());
            ps.setString(2, livre.getAuteur());
            ps.setString(3, livre.getDatePublication());
            ps.setInt(4, livre.getNombreDePages());
            ps.setInt(5, livre.getISBN());

            int result = ps.executeUpdate();
            DbConnection.closeConnection();

            if (result != 0) {
                System.out.println("livre insere!");
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
