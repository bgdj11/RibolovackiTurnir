package model;

import java.time.LocalDate;

public class Ribolovac {
    private Long id;
    private String ime;
    private String prezime;
    private LocalDate datumRodjenja;
    private String brojTelefona;
    private Long idEkipe; // FK

    public Ribolovac() {}

    public Ribolovac(Long id, String ime, String prezime, LocalDate datumRodjenja,
                     String brojTelefona, Long idEkipe) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.datumRodjenja = datumRodjenja;
        this.brojTelefona = brojTelefona;
        this.idEkipe = idEkipe;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getIme() { return ime; }
    public void setIme(String ime) { this.ime = ime; }
    public String getPrezime() { return prezime; }
    public void setPrezime(String prezime) { this.prezime = prezime; }
    public LocalDate getDatumRodjenja() { return datumRodjenja; }
    public void setDatumRodjenja(LocalDate datumRodjenja) { this.datumRodjenja = datumRodjenja; }
    public String getBrojTelefona() { return brojTelefona; }
    public void setBrojTelefona(String brojTelefona) { this.brojTelefona = brojTelefona; }
    public Long getIdEkipe() { return idEkipe; }
    public void setIdEkipe(Long idEkipe) { this.idEkipe = idEkipe; }

    @Override public String toString() {
        return "Ribolovac{id=" + id + ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' + ", datumRodjenja=" + datumRodjenja +
                ", brojTelefona='" + brojTelefona + '\'' + ", idEkipe=" + idEkipe + '}';
    }
}
