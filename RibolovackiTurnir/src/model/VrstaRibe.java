package model;

public class VrstaRibe {
    private Long id;
    private String naziv;
    private TipRibeEnum tipRibe;
    private Integer dodatniBodovi; // NOT NULL u bazi

    public VrstaRibe() {}

    public VrstaRibe(Long id, String naziv, TipRibeEnum tipRibe, Integer dodatniBodovi) {
        this.id = id;
        this.naziv = naziv;
        this.tipRibe = tipRibe;
        this.dodatniBodovi = dodatniBodovi;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNaziv() { return naziv; }
    public void setNaziv(String naziv) { this.naziv = naziv; }
    public TipRibeEnum getTipRibe() { return tipRibe; }
    public void setTipRibe(TipRibeEnum tipRibe) { this.tipRibe = tipRibe; }
    public Integer getDodatniBodovi() { return dodatniBodovi; }
    public void setDodatniBodovi(Integer dodatniBodovi) { this.dodatniBodovi = dodatniBodovi; }

    @Override public String toString() {
        return "VrstaRibe{id=" + id + ", naziv='" + naziv + '\'' +
                ", tipRibe=" + tipRibe + ", dodatniBodovi=" + dodatniBodovi + '}';
    }
}
