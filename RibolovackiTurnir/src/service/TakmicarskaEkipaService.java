package service;

import dao.TakmicarskaEkipaDAO;
import model.TakmicarskaEkipa;

import java.sql.SQLException;
import java.util.List;

public class TakmicarskaEkipaService {

    private final TakmicarskaEkipaDAO dao;

    public TakmicarskaEkipaService() {
        this(new TakmicarskaEkipaDAO());
    }

    public TakmicarskaEkipaService(TakmicarskaEkipaDAO dao) {
        this.dao = dao;
    }

    public TakmicarskaEkipa getById(Long id) throws SQLException {
        return dao.findById(id);
    }

    public List<TakmicarskaEkipa> getAll() throws SQLException {
        return dao.findAll();
    }
}
