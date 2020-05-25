package gra;

import java.util.ArrayList;
import java.util.List;

public class Ekwipunek {
    private final List<PrzedmiotFabularny> ekwipunekFabularne;
    private final List<PrzedmiotPozywienie> ekwipunekPozywienie;
    private final List<BronMagiczna> ekwipunekBronMagiczna;
    private final List<BronFizyczna> ekwipunekBronFizczna;
    private final TYP_POSIADACZA_EKWIPUNKU TYP;
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
        if(TYP == TYP_POSIADACZA_EKWIPUNKU.WOJOWNIK){
            System.out.println("Nie mozesz korzystac z broni magicznej - jestes wojownikiem");
        }else if(TYP == TYP_POSIADACZA_EKWIPUNKU.MAG){
            ekwipunekBronMagiczna.add(p);
        }else if(TYP == TYP_POSIADACZA_EKWIPUNKU.KAPLAN){
            ekwipunekBronMagiczna.add(p);
        }
        System.out.println("Kutacz Wlozona");
    }
    public void wlozBronFizyczna(BronFizyczna p){
        if(TYP == TYP_POSIADACZA_EKWIPUNKU.WOJOWNIK){
            ekwipunekBronFizczna.add(p);
        }else if(TYP == TYP_POSIADACZA_EKWIPUNKU.MAG){
            System.out.println("Nie mozesz korzystac z broni magicznej - jestes magiem");
        }else if(TYP == TYP_POSIADACZA_EKWIPUNKU.KAPLAN){
            ekwipunekBronFizczna.add(p);
        }
        System.out.println("Kutacz wlozony");
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
        wyekwipowanaBron = ekwipunekBronMagiczna.get(indeksBroni);
    }
    public void zmienWyekwipowanaBronNaFiczyna(int indeksBroni){
        wyekwipowanaBron = ekwipunekBronFizczna.get(indeksBroni);
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

}

