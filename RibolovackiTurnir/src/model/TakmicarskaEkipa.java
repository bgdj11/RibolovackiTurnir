package model;

public class TakmicarskaEkipa {
    private Long id;
    private String naziv;
    private String drzava;
    private TipTakmicarskeEkipe tipEkipe;
    private Integer uzrast; // samo za deciju

    public TakmicarskaEkipa() {}

    public TakmicarskaEkipa(Long id, String naziv, String drzava,
                            TipTakmicarskeEkipe tipEkipe, Integer uzrast) {
        this.id = id;
        this.naziv = naziv;
        this.drzava = drzava;
        this.tipEkipe = tipEkipe;
        this.uzrast = uzrast;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNaziv() { return naziv; }
    public void setNaziv(String naziv) { this.naziv = naziv; }
    public String getDrzava() { return drzava; }
    public void setDrzava(String drzava) { this.drzava = drzava; }
    public TipTakmicarskeEkipe getTipEkipe() { return tipEkipe; }
    public void setTipEkipe(TipTakmicarskeEkipe tipEkipe) { this.tipEkipe = tipEkipe; }
    public Integer getUzrast() { return uzrast; }
    public void setUzrast(Integer uzrast) { this.uzrast = uzrast; }

    @Override public String toString() {
        return "TakmicarskaEkipa{id=" + id + ", naziv='" + naziv + '\'' +
                ", drzava='" + drzava + '\'' + ", tipEkipe=" + tipEkipe +
                ", uzrast=" + uzrast + '}';
    }
}
