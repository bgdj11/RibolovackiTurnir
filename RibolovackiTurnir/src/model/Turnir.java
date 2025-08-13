package model;

import java.time.LocalDate;

public class Turnir {
    private Long id;
    private String naziv;
    private LocalDate datumOdrzavanja;
    private String lokacija;
    private String organizator;

    public Turnir() {}

    public Turnir(Long id, String naziv, LocalDate datumOdrzavanja,
                  String lokacija, String organizator) {
        this.id = id;
        this.naziv = naziv;
        this.datumOdrzavanja = datumOdrzavanja;
        this.lokacija = lokacija;
        this.organizator = organizator;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNaziv() { return naziv; }
    public void setNaziv(String naziv) { this.naziv = naziv; }
    public LocalDate getDatumOdrzavanja() { return datumOdrzavanja; }
    public void setDatumOdrzavanja(LocalDate datumOdrzavanja) { this.datumOdrzavanja = datumOdrzavanja; }
    public String getLokacija() { return lokacija; }
    public void setLokacija(String lokacija) { this.lokacija = lokacija; }
    public String getOrganizator() { return organizator; }
    public void setOrganizator(String organizator) { this.organizator = organizator; }

    @Override public String toString() {
        return "Turnir{id=" + id + ", naziv='" + naziv + '\'' +
                ", datumOdrzavanja=" + datumOdrzavanja +
                ", lokacija='" + lokacija + '\'' +
                ", organizator='" + organizator + '\'' + '}';
    }
}

