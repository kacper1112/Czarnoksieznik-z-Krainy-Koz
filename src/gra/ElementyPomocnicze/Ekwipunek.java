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

    //GETTERY
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

    public TYP_POSIADACZA_EKWIPUNKU getTYP() {
        return TYP;
    }

    //WYCIAGANIE
    public void wyciagnijBronFizyczna(int indeks) {
        ekwipunekBronFizczna.remove(indeks);
    }

    public void wyciagnijBronMagiczna(int indeks) {
        ekwipunekBronMagiczna.remove(indeks);
    }

    public void wyciagnijPozywienie(int indeks) {
        ekwipunekPozywienie.remove(indeks);
    }

    //WYEKWIPOWANIE
    public void zmienWyekwipowanaBronNaMagiczna(int indeksBroni) {
        if (TYP == TYP_POSIADACZA_EKWIPUNKU.WOJOWNIK) {
            System.out.println("Nie mozesz korzystac z broni magicznej - jestes wojownikiem");
        } else {
            wyekwipowanaBron = ekwipunekBronMagiczna.get(indeksBroni);
            System.out.println("Zmieniles bron na: " + wyekwipowanaBron);
        }
    }

    public void zmienWyekwipowanaBronNaFizyczna(int indeksBroni) {
        if (TYP == TYP_POSIADACZA_EKWIPUNKU.MAG) {
            System.out.println("Nie mozesz korzystac z broni fizycznej - jestes magiem");
        } else {
            wyekwipowanaBron = ekwipunekBronFizczna.get(indeksBroni);
            System.out.println("Zmieniles bron na: " + wyekwipowanaBron);
        }
    }

    //SETTERY
    public void setTYP(TYP_POSIADACZA_EKWIPUNKU TYP) {
        this.TYP = TYP;
    }

    //METODY UŻYTECZNE
    public int pokazEkwipunek() {
        int indeks = 1;
        if (this.isEmpty()) {
            System.out.println("Twoj ekwipunek jest pusty!");
            return 0;
        }

        if (TYP == TYP_POSIADACZA_EKWIPUNKU.WOJOWNIK || TYP == TYP_POSIADACZA_EKWIPUNKU.MAG || TYP == TYP_POSIADACZA_EKWIPUNKU.KAPLAN) {
            KolorTekstu.printZielony("Twoje przedmioty:");
        } else if (TYP == TYP_POSIADACZA_EKWIPUNKU.HANDLARZ) {
            System.out.println("Witaj przybyszu!\n" + "Oto moja oferta, spcjalnie dla ciebie:");
        }


        if (!this.getEkwipunekPozywienie().isEmpty()) {
            KolorTekstu.printZielony("Pozywienie:");
            for (PrzedmiotPozywienie p : this.getEkwipunekPozywienie()) {
                KolorTekstu.printZielony("\t" + indeks++ + ". " + p);
            }
        }
        if (!this.getEkwipunekBronFizyczna().isEmpty()) {
            KolorTekstu.printZielony("Bron fizyczna:");
            for (BronFizyczna bron : this.getEkwipunekBronFizyczna()) {
                KolorTekstu.printZielonyBezNL("\t" + indeks++ + ". " + bron);
                if(bron == wyekwipowanaBron) {
                    KolorTekstu.printZielonyItaliki(" <- aktualnie wyekwipowana");
                } else {
                    System.out.println();
                }
            }
        }
        if (!this.getEkwipunekBronMagiczna().isEmpty()) {
            KolorTekstu.printZielony("Bron magiczna:");
            for (BronMagiczna bron : this.getEkwipunekBronMagiczna()) {
                KolorTekstu.printZielonyBezNL("\t" + indeks++ + ". " + bron);
                if(bron == wyekwipowanaBron) {
                    KolorTekstu.printZielonyItaliki(" <- aktualnie wyekwipowana");
                } else {
                    System.out.println();
                }
            }
        }
        if (!this.getEkwipunekFabularne().isEmpty()) {
            KolorTekstu.printZielony("Przedmioty fabularne:");
            for (PrzedmiotFabularny p : this.getEkwipunekFabularne()) {
                KolorTekstu.printZielony("\t" + indeks++ + ". " + p.getNazwa());
            }
        }
        return indeks - 1;
    }

    public void dodajEkwipunek(Ekwipunek ekwipunekPrzeciwnika) {
        ekwipunekPrzeciwnika.getEkwipunekPozywienie().forEach(this::wlozDoEkwipunku);
        ekwipunekPrzeciwnika.getEkwipunekBronFizyczna().forEach(this::wlozDoEkwipunku);
        ekwipunekPrzeciwnika.getEkwipunekBronMagiczna().forEach(this::wlozDoEkwipunku);
        ekwipunekPrzeciwnika.getEkwipunekFabularne().forEach(this::wlozDoEkwipunku);
    }

    public void wlozDoEkwipunku(Przedmiot przedmiot) {
        if(przedmiot instanceof PrzedmiotPozywienie) {
            ekwipunekPozywienie.add((PrzedmiotPozywienie)przedmiot);
        } else if(przedmiot instanceof PrzedmiotFabularny) {
            ekwipunekFabularne.add((PrzedmiotFabularny)przedmiot);
        } else if(przedmiot instanceof BronFizyczna) {
            ekwipunekBronFizczna.add((BronFizyczna)przedmiot);
        } else if(przedmiot instanceof BronMagiczna) {
            ekwipunekBronMagiczna.add((BronMagiczna)przedmiot);
        } else {
            System.out.println("Nieprawidlowy typ przedmiotu.");
        }
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

