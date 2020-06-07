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

    public void wlozPrzedmiotFabularny(PrzedmiotFabularny pf) {
        ekwipunekFabularne.add(pf);
    }

    public void wlozPozywienie(PrzedmiotPozywienie pozywienie) {
        ekwipunekPozywienie.add(pozywienie);
    }

    public void wlozBronMagiczna(BronMagiczna p) {
        ekwipunekBronMagiczna.add(p);
    }

    public void wlozBronFizyczna(BronFizyczna p) {
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

    public void wyciagnijBronFizyczna(int indeks) {
        ekwipunekBronFizczna.remove(indeks);
    }

    public void wyciagnijBronMagiczna(int indeks) {
        ekwipunekBronMagiczna.remove(indeks);
    }

    public void wyciagnijPozywienie(int indeks) {
        ekwipunekPozywienie.remove(indeks);
    }

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

    public TYP_POSIADACZA_EKWIPUNKU getTYP() {
        return TYP;
    }

    public void setTYP(TYP_POSIADACZA_EKWIPUNKU TYP) {
        this.TYP = TYP;
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

    public boolean isEmpty() {
        return (ekwipunekBronFizczna.isEmpty() && ekwipunekBronMagiczna.isEmpty() &&
                ekwipunekFabularne.isEmpty() && getEkwipunekPozywienie().isEmpty());
    }

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
        } else if (TYP == TYP_POSIADACZA_EKWIPUNKU.BOSS || TYP == TYP_POSIADACZA_EKWIPUNKU.WROG) {
            System.out.println("Oto ekwipunek twego przeciwnika:");
        }

        if (TYP == TYP_POSIADACZA_EKWIPUNKU.WOJOWNIK || TYP == TYP_POSIADACZA_EKWIPUNKU.MAG || TYP == TYP_POSIADACZA_EKWIPUNKU.KAPLAN) {
            if (this.getWyekwipowanaBron() instanceof BronMagiczna) {
                KolorTekstu.printZielony("Wyekwipowana bron magiczna:\n" + "\t" +
                        this.getWyekwipowanaBron());

            } else if (this.getWyekwipowanaBron() instanceof BronFizyczna) {
                KolorTekstu.printZielony("Wyekwipowana bron fizyczna:\n" + "\t"
                        + this.getWyekwipowanaBron());
            }
        }

        if (!this.getEkwipunekPozywienie().isEmpty()) {
            KolorTekstu.printZielony("Pozywienie:");
            for (PrzedmiotPozywienie p : this.getEkwipunekPozywienie()) {
                KolorTekstu.printZielony("\t" + indeks++ + ". " + p.getNazwa());
            }
        }
        if (!this.getEkwipunekBronFizyczna().isEmpty()) {
            KolorTekstu.printZielony("Bron fizyczna:");
            for (BronFizyczna bron : this.getEkwipunekBronFizyczna()) {
                KolorTekstu.printZielony("\t" + indeks++ + ". " + bron);
            }
        }
        if (!this.getEkwipunekBronMagiczna().isEmpty()) {
            KolorTekstu.printZielony("Bron magiczna:");
            for (BronMagiczna bron : this.getEkwipunekBronMagiczna()) {
                KolorTekstu.printZielony("\t" + indeks++ + ". " + bron);
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
        if (!ekwipunekPrzeciwnika.getEkwipunekPozywienie().isEmpty()) {
            ekwipunekPrzeciwnika.getEkwipunekPozywienie().forEach(this::wlozPozywienie);
        }
        if (!ekwipunekPrzeciwnika.getEkwipunekBronFizyczna().isEmpty()) {
            ekwipunekPrzeciwnika.getEkwipunekBronFizyczna().forEach(this::wlozBronFizyczna);
        }
        if (!ekwipunekPrzeciwnika.getEkwipunekBronMagiczna().isEmpty()) {
            ekwipunekPrzeciwnika.getEkwipunekBronMagiczna().forEach(this::wlozBronMagiczna);
        }
        if (!ekwipunekPrzeciwnika.getEkwipunekFabularne().isEmpty()) {
            ekwipunekPrzeciwnika.getEkwipunekFabularne().forEach(this::wlozPrzedmiotFabularny);
        }
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

