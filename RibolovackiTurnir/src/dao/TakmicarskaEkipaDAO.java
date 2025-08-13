package dao;

import db.DB;
import model.TakmicarskaEkipa;
import model.TipTakmicarskeEkipe;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TakmicarskaEkipaDAO {

    public TakmicarskaEkipa findById(Long id) throws SQLException {
        String sql = "SELECT id, naziv, drzava, tip_ekipe, uzrast FROM takmicarska_ekipa WHERE id = ?";
        try (Connection con = DB.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? map(rs) : null;
            }
        }
    }

    public List<TakmicarskaEkipa> findAll() throws SQLException {
        String sql = "SELECT id, naziv, drzava, tip_ekipe, uzrast FROM takmicarska_ekipa ORDER BY id";
        try (Connection con = DB.connect();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            List<TakmicarskaEkipa> list = new ArrayList<>();
            while (rs.next()) list.add(map(rs));
            return list;
        }
    }

    private TakmicarskaEkipa map(ResultSet rs) throws SQLException {
        TakmicarskaEkipa t = new TakmicarskaEkipa();
        t.setId(rs.getLong("id"));
        t.setNaziv(rs.getString("naziv"));
        t.setDrzava(rs.getString("drzava"));
        String tip = rs.getString("tip_ekipe");
        t.setTipEkipe(tip == null ? null : TipTakmicarskeEkipe.valueOf(tip));
        int uz = rs.getInt("uzrast");
        t.setUzrast(rs.wasNull() ? null : uz);
        return t;
    }
}
