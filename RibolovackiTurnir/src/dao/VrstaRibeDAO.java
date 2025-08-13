package dao;

import db.DB;
import model.TipRibeEnum;
import model.VrstaRibe;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VrstaRibeDAO {

    // READ
    public VrstaRibe findById(Long id) throws SQLException {
        String sql = "SELECT id, naziv, tip_ribe, dodatni_bodovi FROM vrsta_ribe WHERE id = ?";
        try (Connection con = DB.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? map(rs) : null;
            }
        }
    }

    public List<VrstaRibe> findAll() throws SQLException {
        String sql = "SELECT id, naziv, tip_ribe, dodatni_bodovi FROM vrsta_ribe ORDER BY id";
        try (Connection con = DB.connect();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            List<VrstaRibe> list = new ArrayList<>();
            while (rs.next()) list.add(map(rs));
            return list;
        }
    }

    // CREATE
    public Long insert(VrstaRibe v) throws SQLException {
        String sql = "INSERT INTO vrsta_ribe(naziv, tip_ribe, dodatni_bodovi) VALUES (?, ?::tip_ribe_enum, ?) RETURNING id";
        try (Connection con = DB.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, v.getNaziv());
            ps.setString(2, v.getTipRibe() == null ? null : v.getTipRibe().name());
            ps.setInt(3, v.getDodatniBodovi());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    long id = rs.getLong(1);
                    v.setId(id);
                    return id;
                }
            }
        }
        throw new SQLException("Insert vrsta_ribe failed, no ID obtained.");
    }

    // UPDATE
    public boolean update(VrstaRibe v) throws SQLException {
        String sql = "UPDATE vrsta_ribe SET naziv=?, tip_ribe=?::tip_ribe_enum, dodatni_bodovi=? WHERE id=?";
        try (Connection con = DB.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, v.getNaziv());
            ps.setString(2, v.getTipRibe() == null ? null : v.getTipRibe().name());
            ps.setInt(3, v.getDodatniBodovi());
            ps.setLong(4, v.getId());
            return ps.executeUpdate() == 1;
        }
    }

    // DELETE
    public boolean delete(Long id) throws SQLException {
        String sql = "DELETE FROM vrsta_ribe WHERE id = ?";
        try (Connection con = DB.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            return ps.executeUpdate() == 1;
        }
    }

    private VrstaRibe map(ResultSet rs) throws SQLException {
        VrstaRibe v = new VrstaRibe();
        v.setId(rs.getLong("id"));
        v.setNaziv(rs.getString("naziv"));
        String tip = rs.getString("tip_ribe");
        v.setTipRibe(tip == null ? null : TipRibeEnum.valueOf(tip));
        v.setDodatniBodovi(rs.getInt("dodatni_bodovi"));
        return v;
    }
}
