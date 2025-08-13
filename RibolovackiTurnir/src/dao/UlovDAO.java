package dao;

import db.DB;
import model.Ulov;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UlovDAO {

    public Ulov findById(Long id) throws SQLException {
        String sql = "SELECT id, id_ribolovac, id_vrste_ribe, tezina, broj_poena FROM ulov WHERE id = ?";
        try (Connection con = DB.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? map(rs) : null;
            }
        }
    }

    public List<Ulov> findAll() throws SQLException {
        String sql = "SELECT id, id_ribolovac, id_vrste_ribe, tezina, broj_poena FROM ulov ORDER BY id";
        try (Connection con = DB.connect();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            List<Ulov> list = new ArrayList<>();
            while (rs.next()) list.add(map(rs));
            return list;
        }
    }

    private Ulov map(ResultSet rs) throws SQLException {
        Ulov u = new Ulov();
        u.setId(rs.getLong("id"));
        u.setIdRibolovac(rs.getLong("id_ribolovac"));
        u.setIdVrsteRibe(rs.getLong("id_vrste_ribe"));
        u.setTezina(rs.getBigDecimal("tezina"));
        int poeni = rs.getInt("broj_poena");
        u.setBrojPoena(rs.wasNull() ? null : poeni);
        return u;
    }
}
