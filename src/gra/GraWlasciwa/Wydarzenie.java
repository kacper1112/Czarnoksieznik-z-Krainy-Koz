package gra.GraWlasciwa;

import gra.ElementyPomocnicze.Zagadka;
import gra.NPC.Boss;
import gra.NPC.Fabularny;
import gra.NPC.Wrog;
import gra.RodzajeGracz.Gracz;

import java.util.ArrayList;
import java.util.List;

public class Wydarzenie {
    private final String nazwa;
    private final String opis;
    private final Gracz gracz;
    private final List<Fabularny> postacieFabularne;
    private final List<Wrog> wrogowie;
    private final Boss boss;
    private final Zagadka zagadka;
    private Boolean czyWykonana;

    public Wydarzenie(String nazwa, String opis, Gracz gracz, List<Fabularny> fabularne, int iluWrogow, Boss boss) {
        this.nazwa = nazwa;
        this.opis = opis;
        this.gracz = gracz;
        this.postacieFabularne = fabularne;
        this.wrogowie = generujWrogow(iluWrogow);
        this.boss = boss;
        this.zagadka = null;
        this.czyWykonana = false;
    }

    public Wydarzenie(String nazwa, String opis, Gracz gracz, List<Fabularny> fabularne, List<Wrog> wrogowie, Boss boss) {
        this.nazwa = nazwa;
        this.opis = opis;
        this.gracz = gracz;
        this.postacieFabularne = fabularne;
        this.wrogowie = wrogowie;
        this.boss = boss;
        this.zagadka = null;
        this.czyWykonana = false;
    }

    public Wydarzenie(String nazwa, String opis, Gracz gracz, List<Fabularny> fabularne, List<Wrog> wrogowie, Boss boss, Zagadka zagadka) {
        this.nazwa = nazwa;
        this.opis = opis;
        this.gracz = gracz;
        this.postacieFabularne = fabularne;
        this.wrogowie = wrogowie;
        this.boss = boss;
        this.zagadka = zagadka;
        this.czyWykonana = false;
    }

    //GETTERY
    public Boolean getCzyWykonana() {
        return czyWykonana;
    }

    public Zagadka getZagadka() {
        return zagadka;
    }

    public String getNazwa() {
        return nazwa;
    }

    public String getOpis() {
        return opis;
    }

    public Gracz getGracz() {
        return gracz;
    }

    public List<Fabularny> getPostacieFabularne() {
        return postacieFabularne;
    }

    public List<Wrog> getWrogowie() {
        return wrogowie;
    }

    public Boss getBoss() {
        return boss;
    }

    //SETTERY
    public void setCzyWykonana(Boolean czyWykonana) {
        this.czyWykonana = czyWykonana;
    }

    //METODY WLASCIWE
    private List<Wrog> generujWrogow(int iluWrogow) {
        List<Wrog> tempWrogowie = new ArrayList<>();
        for (int i = 0; i < iluWrogow; i++) {
            Wrog wrog = new Wrog("Podstawowy przeciwnik");
            tempWrogowie.add(wrog);
        }
        return tempWrogowie;
    }

    public void zagadka() {
        this.zagadka.wywolajZagadke(gracz);
    }

}
