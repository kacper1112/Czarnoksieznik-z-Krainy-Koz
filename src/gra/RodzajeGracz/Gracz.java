package gra.RodzajeGracz;

import gra.ElementyPomocnicze.Ekwipunek;
import gra.ElementyPomocnicze.generatorEkwipunku;
import gra.RodzajePrzedmiot.BronFizyczna;
import gra.RodzajePrzedmiot.BronMagiczna;
import gra.RodzajePrzedmiot.Przedmiot;

public abstract class Gracz implements generatorEkwipunku {
    private final double maksymalnePunktyZycia;
    Ekwipunek ekwipunek;
    private double obecnePunktyZycia;
    private double sila;
    private double inteligencja;
    private int poziom;
    private double punktyDoswiadczenia;
    private double pieniadze;

    // konstruktor domyslny
    public Gracz() {
        maksymalnePunktyZycia = 100;
        obecnePunktyZycia = maksymalnePunktyZycia;
        sila = 10;
        inteligencja = 10;
        poziom = 0;
        punktyDoswiadczenia = 0;
        pieniadze = 100;
        ekwipunek = generujEkwipunek();
    }

    // konstruktor klasy gracza
    public Gracz(double maksymalnePunktyZycia, double sila, double inteligencja) {
        this.maksymalnePunktyZycia = 10000;
        this.obecnePunktyZycia = this.maksymalnePunktyZycia;
        this.sila = sila;
        this.inteligencja = inteligencja;
        this.poziom = 1;
        this.punktyDoswiadczenia = 0;
        this.pieniadze = 100;
        ekwipunek = generujEkwipunek();
    }

    //GETTERY
    public double getMaksymalnePunktyZycia() {
        return maksymalnePunktyZycia;
    }

    public Ekwipunek getEkwipunek() {
        return ekwipunek;
    }

    public double getObecnePunktyZycia() {
        return obecnePunktyZycia;
    }

    public double getSila() {
        return sila;
    }

    public double getInteligencja() {
        return inteligencja;
    }

    public int getPoziom() {
        return poziom;
    }

    public double getPunktyDoswiadczenia() {
        return punktyDoswiadczenia;
    }

    public double getPieniadze() {
        return pieniadze;
    }

    public double getSumaBonusowDoInteligencji() {
        double sumaBonusow = 0;
        for (Przedmiot p : ekwipunek.getEkwipunekFabularne()) {
            sumaBonusow += p.getAtrybut().getBonusDoInteligencji();
        }
        if (ekwipunek.getWyekwipowanaBron() instanceof BronFizyczna) {
            sumaBonusow += ((BronFizyczna) ekwipunek.getWyekwipowanaBron()).getAtrybut().getBonusDoInteligencji();
        } else {
            sumaBonusow += ((BronMagiczna) ekwipunek.getWyekwipowanaBron()).getAtrybut().getBonusDoInteligencji();
        }

        return sumaBonusow;
    }

    public double getSumaBonusowDoSily() {
        double sumaBonusow = 0;
        for (Przedmiot p : ekwipunek.getEkwipunekFabularne()) {
            sumaBonusow += p.getAtrybut().getBonusDoSily();
        }
        if (ekwipunek.getWyekwipowanaBron() instanceof BronFizyczna) {
            sumaBonusow += ((BronFizyczna) ekwipunek.getWyekwipowanaBron()).getAtrybut().getBonusDoSily();
        } else {
            sumaBonusow += ((BronMagiczna) ekwipunek.getWyekwipowanaBron()).getAtrybut().getBonusDoSily();
        }

        return sumaBonusow;
    }

    public double getSzansaNaNatychmiastoweZabicie() {
        double sumaBonusow = 0;
        for (Przedmiot p : ekwipunek.getEkwipunekFabularne()) {
            sumaBonusow += p.getAtrybut().getSzansaNaNatychmiastoweZabicie();
        }
        if (ekwipunek.getWyekwipowanaBron() instanceof BronFizyczna) {
            sumaBonusow += ((BronFizyczna) ekwipunek.getWyekwipowanaBron()).getAtrybut().getSzansaNaNatychmiastoweZabicie();
        } else {
            sumaBonusow += ((BronMagiczna) ekwipunek.getWyekwipowanaBron()).getAtrybut().getSzansaNaNatychmiastoweZabicie();
        }

        return sumaBonusow;
    }

    //SETTERY
    public void setSila(double sila) {
        this.sila = sila;
    }

    public void setInteligencja(double inteligencja) {
        this.inteligencja = inteligencja;
    }

    public void setPoziom(int poziom) {
        this.poziom = poziom;
    }

    public void setPunktyDoswiadczenia(double punktyDoswiadczenia) {
        this.punktyDoswiadczenia = punktyDoswiadczenia;
    }

    public void setPieniadze(double pieniadze) {
        this.pieniadze = pieniadze;
    }

    public void setEkwipunek(Ekwipunek ekwipunek) {
        this.ekwipunek = ekwipunek;
    }

    //METODY WLASCIWE
    public void zmniejszPunktyZycia(double wartosc) {
        if (obecnePunktyZycia <= wartosc) {
            obecnePunktyZycia = 0;
        } else {
            obecnePunktyZycia -= wartosc;
        }
    }

    public void zwiekszPunktyZycia(double wartosc) {
        if (obecnePunktyZycia + wartosc >= maksymalnePunktyZycia) {
            obecnePunktyZycia = maksymalnePunktyZycia;
        } else {
            obecnePunktyZycia += wartosc;
        }
    }

    public abstract double zadajObrazenia();

    public abstract double zadajMocneObrazenia();

    public abstract void otrzymajObrazenia(double wartosc);

    public abstract void zwiekszLevel();

    public void uzyjPozywienia(int index) {
        double leczenie = ekwipunek.getEkwipunekPozywienie().get(index).getPrzywracaneZycie();
        ekwipunek.wyciagnijPozywienie(index);

        if(obecnePunktyZycia + leczenie >= maksymalnePunktyZycia) {
            obecnePunktyZycia = maksymalnePunktyZycia;
        } else {
            obecnePunktyZycia += leczenie;
        }
        System.out.println("Masz teraz " + obecnePunktyZycia + "/" + maksymalnePunktyZycia + " punktow zycia");
    }

    // wywolaj wskazowke, jezeli masz wystarczajaca ilosc inteligencji
    public void uzyjPrzedmiotuFabularnego(int index) {
        System.out.println(this.ekwipunek.getEkwipunekFabularne().get(index).getWskazowka(this.inteligencja));
    }

    @Override
    public String toString() {
        return "Obecne statystyki gracza:\n" +
                "\t punkty zycia: " + obecnePunktyZycia + "/" + maksymalnePunktyZycia + "\n" +
                "\t sila: " + sila + "\n" +
                "\t inteligencja: " + inteligencja + "\n" +
                "\t poziom: " + poziom + "\n" +
                "\t punkty doswiadczenia: " + punktyDoswiadczenia + "\n" +
                "\t pieniadze: " + pieniadze + "\n" +
                "\t wyekwipowana bron: " + ekwipunek.getWyekwipowanaBron() + "\n";
    }

}