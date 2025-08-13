package service;

import db.DB;
import dto.UlovDTO;
import dto.TeamPointsDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReportService {


    public List<UlovDTO> findAllUloviWithNamesSortedByPoints() throws SQLException {
        String sql =
                "SELECT u.id AS ulov_id, r.ime, r.prezime, vr.naziv AS vrsta, " +
                        "       u.tezina, u.broj_poena " +
                        "FROM ulov u " +
                        "JOIN ribolovac r   ON r.id = u.id_ribolovac " +
                        "JOIN vrsta_ribe vr ON vr.id = u.id_vrste_ribe " +
                        "ORDER BY u.broj_poena DESC, u.tezina DESC, u.id ASC";

        try (Connection con = DB.connect();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            List<UlovDTO> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new UlovDTO(
                        rs.getLong("ulov_id"),
                        rs.getString("ime"),
                        rs.getString("prezime"),
                        rs.getString("vrsta"),
                        rs.getBigDecimal("tezina"),
                        rs.getInt("broj_poena")
                ));
            }
            return list;
        }
    }

    public List<TeamPointsDTO> teamPointsLeaderboard() throws SQLException {
        String sql =
                "SELECT te.id AS ekipa_id, te.naziv, te.drzava, " +
                        "       COUNT(DISTINCT r.id) AS broj_ribolovaca, " +
                        "       COUNT(u.id) AS broj_ulova, " +
                        "       COALESCE(SUM(u.broj_poena),0) AS ukupno_poena " +
                        "FROM takmicarska_ekipa te " +
                        "LEFT JOIN ribolovac r ON r.id_ekipe = te.id " +
                        "LEFT JOIN ulov u ON u.id_ribolovac = r.id " +
                        "GROUP BY te.id, te.naziv, te.drzava " +
                        "ORDER BY ukupno_poena DESC, broj_ulova DESC, te.naziv ASC";

        try (Connection con = DB.connect();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            List<TeamPointsDTO> list = new ArrayList<>();
            while (rs.next()) {
                list.add(new TeamPointsDTO(
                        rs.getLong("ekipa_id"),
                        rs.getString("naziv"),
                        rs.getString("drzava"),
                        rs.getLong("broj_ribolovaca"),
                        rs.getLong("broj_ulova"),
                        rs.getLong("ukupno_poena")
                ));
            }
            return list;
        }
    }
}
