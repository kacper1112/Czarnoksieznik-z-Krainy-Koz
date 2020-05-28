package gra.ElementyPomocnicze;

import gra.RodzajePrzedmiot.BronFizyczna;
import gra.RodzajePrzedmiot.BronMagiczna;
import gra.RodzajePrzedmiot.PrzedmiotFabularny;
import gra.RodzajePrzedmiot.PrzedmiotPozywienie;

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

    public BronFizyczna wyciagnijBronFizyczna(int indeks){
        return ekwipunekBronFizczna.get(indeks);
    }
    public BronMagiczna wyciagnijBronMagiczna(int indeks){
        return  ekwipunekBronMagiczna.get(indeks);
    }

    public PrzedmiotFabularny wyciagnijFabularne(int indeks){
        return ekwipunekFabularne.get(indeks);
    }

    public PrzedmiotPozywienie wyciagnijPozywienie(int indeks){
        return ekwipunekPozywienie.get(indeks);
    }

    public void zmienWyekwipowanaBronNaMagiczna(int indeksBroni){
        if(TYP == TYP_POSIADACZA_EKWIPUNKU.WOJOWNIK){
            System.out.println("Nie mozesz korzystac z broni magicznej - jestes wojownikiem");
        }else if(TYP == TYP_POSIADACZA_EKWIPUNKU.MAG){
            wyekwipowanaBron = ekwipunekBronMagiczna.get(indeksBroni);
        }else if(TYP == TYP_POSIADACZA_EKWIPUNKU.KAPLAN){
            wyekwipowanaBron = ekwipunekBronMagiczna.get(indeksBroni);
        }
    }
    public void zmienWyekwipowanaBronNaFiczyna(int indeksBroni){
        if(TYP == TYP_POSIADACZA_EKWIPUNKU.WOJOWNIK){
            wyekwipowanaBron = ekwipunekBronFizczna.get(indeksBroni);
        }else if(TYP == TYP_POSIADACZA_EKWIPUNKU.MAG){
            System.out.println("Nie mozesz korzystac z broni fizycznej - jestes magiem");
        }else if(TYP == TYP_POSIADACZA_EKWIPUNKU.KAPLAN){
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

