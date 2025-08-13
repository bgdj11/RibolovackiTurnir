package dto;

import java.time.LocalDate;

public class RibolovacDTO {
    private final Long id;
    private final String ime;
    private final String prezime;
    private final LocalDate datumRodjenja;
    private final String brojTelefona;
    private final String nazivEkipe; // umesto idEkipe

    public RibolovacDTO(Long id, String ime, String prezime, LocalDate datumRodjenja,
                        String brojTelefona, String nazivEkipe) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
        this.brojTelefona = brojTelefona;
        this.nazivEkipe = nazivEkipe;
    }

    public Long getId() { return id; }
    public String getIme() { return ime; }
    public String getPrezime() { return prezime; }
    public LocalDate getDatumRodjenja() { return datumRodjenja; }
    public String getBrojTelefona() { return brojTelefona; }
    public String getNazivEkipe() { return nazivEkipe; }

    @Override public String toString() {
        String datum = (datumRodjenja == null) ? "-" : datumRodjenja.toString();
        String tel = (brojTelefona == null || brojTelefona.isBlank()) ? "-" : brojTelefona;
        return String.format("%-4d | %-12s %-14s | %10s | %-14s | %-20s",
                id, ime, prezime, datum, tel, nazivEkipe);
    }
}
