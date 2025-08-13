package dao;

import db.DB;
import model.Ribolovac;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RibolovacDAO {

    public Ribolovac findById(Long id) throws SQLException {
        String sql = "SELECT id, ime, prezime, datum_rodjenja, broj_telefona, id_ekipe " +
                "FROM ribolovac WHERE id = ?";
        try (Connection con = DB.connect();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? map(rs) : null;
            }
        }
    }

    public List<Ribolovac> findAll() throws SQLException {
        String sql = "SELECT id, ime, prezime, datum_rodjenja, broj_telefona, id_ekipe " +
                "FROM ribolovac ORDER BY id";
        try (Connection con = DB.connect();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            List<Ribolovac> list = new ArrayList<>();
            while (rs.next()) list.add(map(rs));
            return list;
        }
    }

    private Ribolovac map(ResultSet rs) throws SQLException {
        Ribolovac r = new Ribolovac();
        r.setId(rs.getLong("id"));
        r.setIme(rs.getString("ime"));
        r.setPrezime(rs.getString("prezime"));
        r.setDatumRodjenja(rs.getObject("datum_rodjenja", java.time.LocalDate.class));
        r.setBrojTelefona(rs.getString("broj_telefona"));
        long idEkipe = rs.getLong("id_ekipe");
        r.setIdEkipe(rs.wasNull() ? null : idEkipe);
        return r;
    }
}
