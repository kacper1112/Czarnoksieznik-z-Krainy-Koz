package gra;

import java.util.ArrayList;
import java.util.List;

public class Ekwipunek {
    private final List<? super PrzedmiotFabularny> ekwipunekFabularne;
    private final List<? super PrzedmiotPozywienie> ekwipunekPozywienie;
    private final List<? super Przedmiot> ekwipunekBron;
    //private List<Przedmiot> ekwipunekPozostale; CZY TO JEST POTRZEBNE????
    private Przedmiot wyekwipowanaBron;

    public Ekwipunek() {
        ekwipunekFabularne = new ArrayList<>();
        ekwipunekPozywienie = new ArrayList<>();
        ekwipunekBron = new ArrayList<>();
    }

    public void wlozFabularne(PrzedmiotFabularny pf){
        ekwipunekFabularne.add(pf);
    }

    public void wlozPozywienie(PrzedmiotPozywienie pozywienie){
        ekwipunekPozywienie.add(pozywienie);
    }

    public void wlozBron(Przedmiot p){
        ekwipunekBron.add(p);
    }

    public Przedmiot wyciagnijBron(int indeks){
        return (Przedmiot) ekwipunekBron.get(indeks);
    }

    public PrzedmiotFabularny wyciagnijFabularne(int indeks){
        return (PrzedmiotFabularny) ekwipunekFabularne.get(indeks);
    }

    public PrzedmiotPozywienie wyciagnijPozywienie(int indeks){
        return (PrzedmiotPozywienie) ekwipunekPozywienie.get(indeks);
    }

    public void zmienWyekwipowanaBron(int indeksBroni){
        wyekwipowanaBron = (Przedmiot) ekwipunekBron.get(indeksBroni);
    }

    //GETERY I SETTERY (CZESC DO WYWALENIA POZNIEJ)

    public List<? super PrzedmiotFabularny> getEkwipunekFabularne() {
        return ekwipunekFabularne;
    }

    public List<? super PrzedmiotPozywienie> getEkwipunekPozywienie() {
        return ekwipunekPozywienie;
    }

    public List<? super Przedmiot> getEkwipunekBron() {
        return ekwipunekBron;
    }

    public Przedmiot getWyekwipowanaBron() {
        return wyekwipowanaBron;
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

