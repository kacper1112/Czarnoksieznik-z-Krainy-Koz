package gra;

import java.util.ArrayList;
import java.util.List;

public class Ekwipunek {
    private final List<PrzedmiotFabularny> ekwipunekFabularne;
    private final List<PrzedmiotPozywienie> ekwipunekPozywienie;
    private final List<BronMagiczna> ekwipunekBronMagiczna;
    private final List<BronFizyczna> ekwipunekBronFizczna;
    //private List<Przedmiot> ekwipunekPozostale; CZY TO JEST POTRZEBNE????
    private bron wyekwipowanaBron;

    public Ekwipunek() {
        ekwipunekFabularne = new ArrayList<>();
        ekwipunekPozywienie = new ArrayList<>();
        ekwipunekBronMagiczna = new ArrayList<>();
        ekwipunekBronFizczna = new ArrayList<>();
    }

    public void wlozFabularne(PrzedmiotFabularny pf){
        ekwipunekFabularne.add(pf);
    }

    public void wlozPozywienie(PrzedmiotPozywienie pozywienie){
        ekwipunekPozywienie.add(pozywienie);
    }

    public void wlozBronMagiczna(BronMagiczna p){
        ekwipunekBronMagiczna.add(p);
    }
    public void wlozBronFizyczna(BronFizyczna p){
        ekwipunekBronFizczna.add(p);
    }

    public BronFizyczna wyciagnijBronFizyczna(int indeks){
        return ekwipunekBronFizczna.get(indeks);
    }
    public BronMagiczna wyciagnijBronMagiczna(int indeks){
        return  ekwipunekBronMagiczna.get(indeks);
    }

    public PrzedmiotFabularny wyciagnijFabularne(int indeks){
        return (PrzedmiotFabularny) ekwipunekFabularne.get(indeks);
    }

    public PrzedmiotPozywienie wyciagnijPozywienie(int indeks){
        return (PrzedmiotPozywienie) ekwipunekPozywienie.get(indeks);
    }

    public void zmienWyekwipowanaBronNaMagiczna(int indeksBroni){
        wyekwipowanaBron = (bron) ekwipunekBronMagiczna.get(indeksBroni);
    }
    public void zmienWyekwipowanaBronNaFiczyna(int indeksBroni){
        wyekwipowanaBron = (bron) ekwipunekBronFizczna.get(indeksBroni);
    }

    //GETERY I SETTERY (CZESC DO WYWALENIA POZNIEJ)

    public List<PrzedmiotFabularny> getEkwipunekFabularne() {
        return ekwipunekFabularne;
    }

    public List<PrzedmiotPozywienie> getEkwipunekPozywienie() {
        return ekwipunekPozywienie;
    }

    public List<BronFizyczna> getEkwipunekBronFizyczna() {
        return ekwipunekBronFizczna;
    }
    public List<BronMagiczna> getEkwipunekBron() {
        return ekwipunekBronMagiczna;
    }

    public bron getWyekwipowanaBron() {
        return wyekwipowanaBron;
    }

}

