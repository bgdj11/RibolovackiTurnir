package service;

import dao.TurnirDAO;
import model.Turnir;

import java.sql.SQLException;
import java.util.List;

public class TurnirService {

    private final TurnirDAO dao;

    public TurnirService() {
        this(new TurnirDAO());
    }

    public TurnirService(TurnirDAO dao) {
        this.dao = dao;
    }

    // READ
    public Turnir getById(Long id) throws SQLException {
        return dao.findById(id);
    }

    public List<Turnir> getAll() throws SQLException {
        return dao.findAll();
    }

    // CREATE
    public Long create(Turnir t) throws SQLException {
        return dao.insert(t);
    }

    // UPDATE
    public boolean update(Turnir t) throws SQLException {
        return dao.update(t);
    }

    // DELETE
    public boolean delete(Long id) throws SQLException {
        return dao.delete(id);
    }
}
