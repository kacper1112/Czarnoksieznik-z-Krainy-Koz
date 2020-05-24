package gra;

import java.util.List;

public class Wydarzenie {
    private final String nazwa;
    private final String opis;
    private Boss boss;
    private List<Wrog> wrogowie;
    private List<Fabularny> postacieFabularne;

    public Wydarzenie(String nazwa, String opis) {
        this.nazwa = nazwa;
        this.opis = opis;
    }

    public void dodajWroga(Wrog wrog) {
        this.wrogowie.add(wrog);
    }
    public void dodajBossa(Boss boss) {
        this.boss = boss;
    }

    public void dodajPostacFabularna(Fabularny fabularny) {
        this.postacieFabularne.add(fabularny);
    }

    public String getNazwa() {
        return nazwa;
    }

    public String getOpis() {
        return opis;
    }

    public List<Wrog> getWrogowie() {
        return wrogowie;
    }

    public List<Fabularny> getPostacieFabularne() {
        return postacieFabularne;
    }

    public Boss getBoss() {
        return boss;
    }
}
