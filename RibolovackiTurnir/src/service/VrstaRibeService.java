package service;

import dao.VrstaRibeDAO;
import model.VrstaRibe;

import java.sql.SQLException;
import java.util.List;

public class VrstaRibeService {

    private final VrstaRibeDAO dao;

    public VrstaRibeService() {
        this(new VrstaRibeDAO());
    }

    public VrstaRibeService(VrstaRibeDAO dao) {
        this.dao = dao;
    }

    // READ
    public VrstaRibe getById(Long id) throws SQLException {
        return dao.findById(id);
    }

    public List<VrstaRibe> getAll() throws SQLException {
        return dao.findAll();
    }

    // CREATE
    public Long create(VrstaRibe v) throws SQLException {
        return dao.insert(v);
    }

    // UPDATE
    public boolean update(VrstaRibe v) throws SQLException {
        return dao.update(v);
    }

    // DELETE
    public boolean delete(Long id) throws SQLException {
        return dao.delete(id);
    }
}
