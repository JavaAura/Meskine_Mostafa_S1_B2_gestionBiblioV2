package DAO.impl;

import DAO.Intefaces.MagazineDAO;
import metier.Database.DbConnection;
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
        return null;
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

    }

    @Override
    public boolean delete(UUID id) {
        return false;
    }

}
