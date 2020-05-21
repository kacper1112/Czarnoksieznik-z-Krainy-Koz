package gra;

import java.util.ArrayList;
import java.util.List;

public class Ekwipunek {
    private List<? super PrzedmiotyFabularne> ekwipunekFabularne;
    private List<? super Pozywienie> ekwipunekPozywienie;
    private List<Bron> ekwipunekBron;
    //private List<Przedmiot> ekwipunekPozostale; CZY TO JEST POTRZEBNE????
    private Bron wyekwipowanaBron;

    public Ekwipunek() {
        ekwipunekFabularne = new ArrayList<>();
        ekwipunekPozywienie = new ArrayList<>();
        ekwipunekBron = new ArrayList<>();
        wyekwipowanaBron = new Bron();
    }

    public void zmienWyekwipowanaBron(int indeksBroni){
        wyekwipowanaBron = ekwipunekBron.get(indeksBroni);
    }

    public List<? super PrzedmiotyFabularne> getEkwipunekFabularne() {
        return ekwipunekFabularne;
    }

    public void setEkwipunekFabularne(List<? super PrzedmiotyFabularne> ekwipunekFabularne) {
        this.ekwipunekFabularne = ekwipunekFabularne;
    }

    public List<? super Pozywienie> getEkwipunekPozywienie() {
        return ekwipunekPozywienie;
    }

    public void setEkwipunekPozywienie(List<? super Pozywienie> ekwipunekPozywienie) {
        this.ekwipunekPozywienie = ekwipunekPozywienie;
    }

    public List<Bron> getEkwipunekBron() {
        return ekwipunekBron;
    }

    public void setEkwipunekBron(List<Bron> ekwipunekBron) {
        this.ekwipunekBron = ekwipunekBron;
    }

    public Bron getWyekwipowanaBron() {
        return wyekwipowanaBron;
    }

    public void setWyekwipowanaBron(Bron wyekwipowanaBron) {
        this.wyekwipowanaBron = wyekwipowanaBron;
    }

    @Override
    public String toString() {
        return "Ekwipunek{" +
                "ekwipunekFabularne=" + ekwipunekFabularne +
                ", ekwipunekPozywienie=" + ekwipunekPozywienie +
                ", ekwipunekBron=" + ekwipunekBron +
                ", wyekwipowanaBron=" + wyekwipowanaBron +
                '}';
    }
}
