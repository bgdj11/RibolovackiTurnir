package dto;

public class TeamPointsDTO {
    private final Long ekipaId;
    private final String naziv;
    private final String drzava;
    private final long brojRibolovaca;
    private final long brojUlova;
    private final long ukupnoPoena;

    public TeamPointsDTO(Long ekipaId, String naziv, String drzava,
                         long brojRibolovaca, long brojUlova, long ukupnoPoena) {
        this.ekipaId = ekipaId;
        this.naziv = naziv;
        this.drzava = drzava;
        this.brojRibolovaca = brojRibolovaca;
        this.brojUlova = brojUlova;
        this.ukupnoPoena = ukupnoPoena;
    }

    public Long getEkipaId() { return ekipaId; }
    public String getNaziv() { return naziv; }
    public String getDrzava() { return drzava; }
    public long getBrojRibolovaca() { return brojRibolovaca; }
    public long getBrojUlova() { return brojUlova; }
    public long getUkupnoPoena() { return ukupnoPoena; }

    @Override public String toString() {
        return String.format("%-4d | %-22s | %-10s | %3d | %4d | %6d",
                ekipaId, naziv, drzava, brojRibolovaca, brojUlova, ukupnoPoena);
    }
}
