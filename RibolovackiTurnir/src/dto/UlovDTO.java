package dto;

import java.math.BigDecimal;

public class UlovDTO {
    private final Long ulovId;
    private final String ribolovacIme;
    private final String ribolovacPrezime;
    private final String vrstaRibe;
    private final BigDecimal tezina;
    private final Integer brojPoena;

    public UlovDTO(Long ulovId, String ribolovacIme, String ribolovacPrezime,
                   String vrstaRibe, BigDecimal tezina, Integer brojPoena) {
        this.ulovId = ulovId;
        this.ribolovacIme = ribolovacIme;
        this.ribolovacPrezime = ribolovacPrezime;
        this.vrstaRibe = vrstaRibe;
        this.tezina = tezina;
        this.brojPoena = brojPoena;
    }

    public Long getUlovId() { return ulovId; }
    public String getRibolovacIme() { return ribolovacIme; }
    public String getRibolovacPrezime() { return ribolovacPrezime; }
    public String getVrstaRibe() { return vrstaRibe; }
    public BigDecimal getTezina() { return tezina; }
    public Integer getBrojPoena() { return brojPoena; }

    @Override public String toString() {
        String imePrezime = String.format("%s %s", ribolovacIme, ribolovacPrezime);
        String tez = tezina == null ? "0.00" : tezina.setScale(2, java.math.RoundingMode.HALF_UP).toString();
        return String.format("%-4d | %-20s | %-18s | %8s kg | %4d pts",
                ulovId, imePrezime, vrstaRibe, tez, brojPoena == null ? 0 : brojPoena);
    }
}
