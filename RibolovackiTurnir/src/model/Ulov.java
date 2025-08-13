package model;

import java.math.BigDecimal;

public class Ulov {
    private Long id;
    private Long idRibolovac; // FK
    private Long idVrsteRibe; // FK
    private BigDecimal tezina; // NUMERIC(10,2)
    private Integer brojPoena;

    public Ulov() {}

    public Ulov(Long id, Long idRibolovac, Long idVrsteRibe,
                BigDecimal tezina, Integer brojPoena) {
        this.id = id;
        this.idRibolovac = idRibolovac;
        this.idVrsteRibe = idVrsteRibe;
        this.tezina = tezina;
        this.brojPoena = brojPoena;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getIdRibolovac() { return idRibolovac; }
    public void setIdRibolovac(Long idRibolovac) { this.idRibolovac = idRibolovac; }
    public Long getIdVrsteRibe() { return idVrsteRibe; }
    public void setIdVrsteRibe(Long idVrsteRibe) { this.idVrsteRibe = idVrsteRibe; }
    public BigDecimal getTezina() { return tezina; }
    public void setTezina(BigDecimal tezina) { this.tezina = tezina; }
    public Integer getBrojPoena() { return brojPoena; }
    public void setBrojPoena(Integer brojPoena) { this.brojPoena = brojPoena; }

    @Override public String toString() {
        return "Ulov{id=" + id + ", idRibolovac=" + idRibolovac +
                ", idVrsteRibe=" + idVrsteRibe + ", tezina=" + tezina +
                ", brojPoena=" + brojPoena + '}';
    }
}
