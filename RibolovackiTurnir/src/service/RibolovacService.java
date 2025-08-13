package service;

import dao.RibolovacDAO;
import dto.RibolovacDTO;
import model.Ribolovac;
import model.TakmicarskaEkipa;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RibolovacService {

    private final RibolovacDAO dao;

    public RibolovacService() {
        this(new RibolovacDAO());
    }

    public RibolovacService(RibolovacDAO dao) {
        this.dao = dao;
    }

    public Ribolovac getById(Long id) throws SQLException { return dao.findById(id); }
    public List<Ribolovac> getAll() throws SQLException { return dao.findAll(); }

    // NOVO: DTO prikaz, nazive ekipa čita preko prosleđenog servisa
    public List<RibolovacDTO> getAllWithTeamName(TakmicarskaEkipaService ekipaService) throws SQLException {
        List<Ribolovac> ribolovci = dao.findAll();
        if (ribolovci.isEmpty()) return List.of();

        Map<Long, String> nazivEkipeById = ekipaService.getAll()
                .stream()
                .collect(Collectors.toMap(TakmicarskaEkipa::getId, TakmicarskaEkipa::getNaziv));

        List<RibolovacDTO> dtos = new ArrayList<>(ribolovci.size());
        for (Ribolovac r : ribolovci) {
            String naziv = nazivEkipeById.getOrDefault(r.getIdEkipe(), "#" + r.getIdEkipe());
            dtos.add(new RibolovacDTO(
                    r.getId(),
                    r.getIme(),
                    r.getPrezime(),
                    r.getDatumRodjenja(),
                    r.getBrojTelefona(),
                    naziv
            ));
        }
        return dtos;
    }
}
