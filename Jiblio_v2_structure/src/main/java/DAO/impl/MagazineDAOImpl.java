package DAO.impl;

import DAO.Intefaces.MagazineDAO;
import metier.Database.DbConnection;
import metier.Model.Magazine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class MagazineDAOImpl implements MagazineDAO {
//    public final Connection conn = DbConnection.getInstance();

    @Override
    public List<Magazine> getAll() {
        return List.of();
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
