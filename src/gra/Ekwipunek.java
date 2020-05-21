package gra;

import java.util.ArrayList;
import java.util.List;

public class Ekwipunek {
    private List<? super PrzedmiotyFabularne> ekwipunekFabularne;
    private List<? super Pozywienie> ekwipunekPozywienie;
    private List<? super Przedmiot> ekwipunekBron;
    //private List<Przedmiot> ekwipunekPozostale; CZY TO JEST POTRZEBNE????
    private Przedmiot wyekwipowanaBron;

    public Ekwipunek() {
        ekwipunekFabularne = new ArrayList<>();
        ekwipunekPozywienie = new ArrayList<>();
        ekwipunekBron = new ArrayList<>();
    }

    public void wlozFabularne(PrzedmiotyFabularne pf){
        ekwipunekFabularne.add(pf);
    }

    public void wlozPozywienie(Pozywienie pozywienie){
        ekwipunekPozywienie.add(pozywienie);

    }

    public void wlozBron(Przedmiot p){
        ekwipunekBron.add(p);
    }

    public void zmienWyekwipowanaBron(int indeksBroni){
        wyekwipowanaBron = (Przedmiot) ekwipunekBron.get(indeksBroni);
    }

    //GETERY I SETTERY (CZESC DO WYWALENIA POZNIEJ)

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
