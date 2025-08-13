package dao;

import db.DB;
import model.Turnir;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TurnirDAO {

    // READ
    public Turnir findById(Long id) throws SQLException {
        String sql = "SELECT id, naziv, datum_odrzavanja, lokacija, organizator FROM turnir WHERE id = ?";
        try (Connection con = DB.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? map(rs) : null;
            }
        }
    }

    public List<Turnir> findAll() throws SQLException {
        String sql = "SELECT id, naziv, datum_odrzavanja, lokacija, organizator FROM turnir ORDER BY id";
        try (Connection con = DB.connect();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            List<Turnir> list = new ArrayList<>();
            while (rs.next()) list.add(map(rs));
            return list;
        }
    }

    // CREATE
    public Long insert(Turnir t) throws SQLException {
        String sql = "INSERT INTO turnir(naziv, datum_odrzavanja, lokacija, organizator) " +
                "VALUES (?, ?, ?, ?) RETURNING id";
        try (Connection con = DB.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, t.getNaziv());
            ps.setObject(2, t.getDatumOdrzavanja()); // LocalDate
            ps.setString(3, t.getLokacija());
            ps.setString(4, t.getOrganizator());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    long id = rs.getLong(1);
                    t.setId(id);
                    return id;
                }
            }
        }
        throw new SQLException("Insert turnir failed, no ID obtained.");
    }

    // UPDATE
    public boolean update(Turnir t) throws SQLException {
        String sql = "UPDATE turnir SET naziv=?, datum_odrzavanja=?, lokacija=?, organizator=? WHERE id=?";
        try (Connection con = DB.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, t.getNaziv());
            ps.setObject(2, t.getDatumOdrzavanja());
            ps.setString(3, t.getLokacija());
            ps.setString(4, t.getOrganizator());
            ps.setLong(5, t.getId());
            return ps.executeUpdate() == 1;
        }
    }

    // DELETE
    public boolean delete(Long id) throws SQLException {
        String sql = "DELETE FROM turnir WHERE id = ?";
        try (Connection con = DB.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate() == 1;
        }
    }

    private Turnir map(ResultSet rs) throws SQLException {
        Turnir t = new Turnir();
        t.setId(rs.getLong("id"));
        t.setNaziv(rs.getString("naziv"));
        t.setDatumOdrzavanja(rs.getObject("datum_odrzavanja", java.time.LocalDate.class));
        t.setLokacija(rs.getString("lokacija"));
        t.setOrganizator(rs.getString("organizator"));
        return t;
    }
}
