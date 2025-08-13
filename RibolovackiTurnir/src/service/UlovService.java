package service;

import dao.UlovDAO;
import model.Ulov;

import java.sql.SQLException;
import java.util.List;

public class UlovService {

    private final UlovDAO dao;

    public UlovService() {
        this(new UlovDAO());
    }

    public UlovService(UlovDAO dao) {
        this.dao = dao;
    }

    public Ulov getById(Long id) throws SQLException {
        return dao.findById(id);
    }

    public List<Ulov> getAll() throws SQLException {
        return dao.findAll();
    }
}
