package gra.ElementyPomocnicze;

import gra.RodzajePrzedmiot.*;

import java.util.ArrayList;
import java.util.List;

public class Ekwipunek {
    private final List<PrzedmiotFabularny> ekwipunekFabularne;
    private final List<PrzedmiotPozywienie> ekwipunekPozywienie;
    private final List<BronMagiczna> ekwipunekBronMagiczna;
    private final List<BronFizyczna> ekwipunekBronFizczna;
    private TYP_POSIADACZA_EKWIPUNKU TYP;
    private bron wyekwipowanaBron;

    public Ekwipunek(TYP_POSIADACZA_EKWIPUNKU typ) {
        TYP = typ;
        ekwipunekFabularne = new ArrayList<>();
        ekwipunekPozywienie = new ArrayList<>();
        ekwipunekBronMagiczna = new ArrayList<>();
        ekwipunekBronFizczna = new ArrayList<>();
    }

    public void wlozFabularne(PrzedmiotFabularny pf) {
        ekwipunekFabularne.add(pf);
    }
    public void wlozPozywienie(PrzedmiotPozywienie pozywienie) {
        ekwipunekPozywienie.add(pozywienie);
    }
    public void wlozBronMagiczna(BronMagiczna p){
        ekwipunekBronMagiczna.add(p);
    }
    public void wlozBronFizyczna(BronFizyczna p){
        ekwipunekBronFizczna.add(p);
    }

    public int getIloscPozywienie() {
        return ekwipunekPozywienie.size();
    }

    public int getIloscFabularne() {
        return ekwipunekFabularne.size();
    }

    public int getIloscBronMagiczna() {
        return ekwipunekBronMagiczna.size();
    }

    public int getIloscBronFizyczna() {
        return ekwipunekBronFizczna.size();
    }

    public void wyciagnijBronFizyczna(int indeks){
        ekwipunekBronFizczna.remove(indeks);
    }
    public void wyciagnijBronMagiczna(int indeks){
        ekwipunekBronMagiczna.remove(indeks);
    }

    public void wyciagnijFabularne(int indeks){
        ekwipunekFabularne.remove(indeks);
    }

    public void wyciagnijPozywienie(int indeks){
        ekwipunekPozywienie.remove(indeks);
    }

    public void zmienWyekwipowanaBronNaMagiczna(int indeksBroni){
        if(TYP == TYP_POSIADACZA_EKWIPUNKU.WOJOWNIK){
            System.out.println("Nie mozesz korzystac z broni magicznej - jestes wojownikiem");
        }else {
            wyekwipowanaBron = ekwipunekBronMagiczna.get(indeksBroni);
        }
    }

    public void zmienWyekwipowanaBronNaFiczyna(int indeksBroni){
        if(TYP == TYP_POSIADACZA_EKWIPUNKU.MAG) {
            System.out.println("Nie mozesz korzystac z broni fizycznej - jestes magiem");
        } else {
            wyekwipowanaBron = ekwipunekBronFizczna.get(indeksBroni);
        }
    }

    public void setTYP(TYP_POSIADACZA_EKWIPUNKU TYP) {
        this.TYP = TYP;
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
    public List<BronMagiczna> getEkwipunekBronMagiczna() {
        return ekwipunekBronMagiczna;
    }

    public bron getWyekwipowanaBron() {
        return wyekwipowanaBron;
    }

    public boolean isEmpty() {
        return (ekwipunekBronFizczna.isEmpty() && ekwipunekBronMagiczna.isEmpty() &&
                ekwipunekFabularne.isEmpty() && getEkwipunekPozywienie().isEmpty());
    }


    @Override
    public String toString() {
        return "Ekwipunek typu: " + TYP +
                " Przedmioty: Fabularne=" + ekwipunekFabularne +
                ", Przedmioty: Pozywienie=" + ekwipunekPozywienie +
                ", Przedmioty: BronMagiczna=" + ekwipunekBronMagiczna +
                ", Przedmioty: BronFizczna=" + ekwipunekBronFizczna +
                ", wyekwipowanaBron=" + wyekwipowanaBron;
    }
}

