package gra.RodzajeGracz;

import gra.ElementyPomocnicze.Ekwipunek;
import gra.ElementyPomocnicze.generatorEkwipunku;
import gra.GraWlasciwa.Gra;
import gra.RodzajePrzedmiot.*;

public abstract class Gracz implements generatorEkwipunku {
    // pola klasy Gracza
    Ekwipunek ekwipunek;
    private double maksymalnePunktyZycia;
    private double obecnePunktyZycia;
    private double sila;
    private double inteligencja;
    private int poziom;
    private double punktyDoswiadczenia;
    private int postepFabularny;
    private double pieniadze;

    // konstruktor domyslny
    public Gracz() {
        maksymalnePunktyZycia = 100;
        obecnePunktyZycia = maksymalnePunktyZycia;
        sila = 10;
        inteligencja = 10;
        poziom = 0;
        punktyDoswiadczenia = 0;
        postepFabularny = 0;
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
        this.postepFabularny = 0;
        this.pieniadze = 100;
        ekwipunek = generujEkwipunek();
    }

    // gettery i settery Gracza
    public double getMaksymalnePunktyZycia() {
        return maksymalnePunktyZycia;
    }

    public Ekwipunek getEkwipunek() {
        return ekwipunek;
    }

    public void setEkwipunek(Ekwipunek ekwipunek) {
        this.ekwipunek = ekwipunek;
    }


    public double getObecnePunktyZycia() {
        return obecnePunktyZycia;
    }

    public double getSila() {
        return sila;
    }

    public void setSila(double sila) {
        this.sila = sila;
    }

    public double getInteligencja() {
        return inteligencja;
    }

    public void setInteligencja(double inteligencja) {
        this.inteligencja = inteligencja;
    }

    public int getPoziom() {
        return poziom;
    }

    public void setPoziom(int poziom) {
        this.poziom = poziom;
    }

    public double getPunktyDoswiadczenia() {
        return punktyDoswiadczenia;
    }

    public void setPunktyDoswiadczenia(double punktyDoswiadczenia) {
        this.punktyDoswiadczenia = punktyDoswiadczenia;
    }

    public int getPostepFabularny() {
        return postepFabularny;
    }

    public void setPostepFabularny(int postepFabularny) {
        this.postepFabularny = postepFabularny;
    }

    public double getPieniadze() {
        return pieniadze;
    }

    public void setPieniadze(double pieniadze) {
        this.pieniadze = pieniadze;
    }

    // gracz otrzymuje obrazenia
    // dodac co sie dzieje, gdy obecnePunktyZycia = 0
    public void zmniejszPunktyZycia(double wartosc) {
        if(obecnePunktyZycia <= wartosc) {
            obecnePunktyZycia = 0;
        } else {
            obecnePunktyZycia -= wartosc;
        }
    }

    // gracz zwieksza swoje punkty zycia
    public void zwiekszPunktyZycia(double wartosc) {
        if(obecnePunktyZycia + wartosc >= maksymalnePunktyZycia) {
            obecnePunktyZycia = maksymalnePunktyZycia;
        } else {
            obecnePunktyZycia += wartosc;
        }
    }

    public abstract double zadajObrazenia();
    public abstract double zadajMocneObrazenia();
    public abstract void otrzymajObrazenia(double wartosc);
    public abstract void zwiekszLevel();

    // wkladanie przedmiotow do ekwipunku
    public void wlozBronFizycznaDoEkwipunku(BronFizyczna bronFizyczna) {
        this.ekwipunek.wlozBronFizyczna(bronFizyczna);
    }

    public void wlozBronMagicznaDoEwkipunku(BronMagiczna bronMagiczna) {
        this.ekwipunek.wlozBronMagiczna(bronMagiczna);
    }

    public void wlozPozywienieDoEkwipunku(PrzedmiotPozywienie pozywienie) {
        this.ekwipunek.wlozPozywienie(pozywienie);
    }

    public void wlozPrzedmiotFabularnyDoEkwipunku(PrzedmiotFabularny przedmiotFabularny) {
        this.ekwipunek.wlozFabularne(przedmiotFabularny);
    }

    // dodac uzywanie pozywienia
    public void uzyjPozywienia(int index) {
        this.obecnePunktyZycia +=
                this.ekwipunek.getEkwipunekPozywienie().get(index).getPrzywracaneZycie();
        this.ekwipunek.wyciagnijPozywienie(index);
        System.out.println("Masz teraz " + obecnePunktyZycia + "/" + maksymalnePunktyZycia + " punktow zycia");
    }

    // wywolaj wskazowke, jezeli masz wystarczajaca ilosc inteligencji
    public void uzyjPrzedmiotuFabularnego(int index) {
        System.out.println(this.ekwipunek.getEkwipunekFabularne().get(index).getWskazowka(this.inteligencja));
    }

    public String toString() {
        return "Obecne statystyki gracza:\n" +
                "punkty zycia: " + obecnePunktyZycia + "\\" + maksymalnePunktyZycia + "\n" +
                "sila: " + sila + "\n" +
                "inteligencja: " + inteligencja + "\n" +
                "poziom: " + poziom + "\n" +
                "punkty doswiadczenia: " + punktyDoswiadczenia + "\n" +
                "pieniadze: " + pieniadze + "\n" +
                "wyekwipowana bron: " + ekwipunek.getWyekwipowanaBron() + "\n";
    }

    public double getSumaBonusowDoInteligencji() {
        double sumaBonusow = 0;
        for(Przedmiot p: ekwipunek.getEkwipunekFabularne()) {
            sumaBonusow += p.getAtrybut().getBonusDoInteligencji();
        }
        if(ekwipunek.getWyekwipowanaBron() instanceof BronFizyczna) {
            sumaBonusow += ((BronFizyczna)ekwipunek.getWyekwipowanaBron()).getAtrybut().getBonusDoInteligencji();
        } else {
            sumaBonusow += ((BronMagiczna)ekwipunek.getWyekwipowanaBron()).getAtrybut().getBonusDoInteligencji();
        }

        return sumaBonusow;
    }

    public double getSumaBonusowDoSily() {
        double sumaBonusow = 0;
        for(Przedmiot p: ekwipunek.getEkwipunekFabularne()) {
            sumaBonusow += p.getAtrybut().getBonusDoSily();
        }
        if(ekwipunek.getWyekwipowanaBron() instanceof BronFizyczna) {
            sumaBonusow += ((BronFizyczna)ekwipunek.getWyekwipowanaBron()).getAtrybut().getBonusDoSily();
        } else {
            sumaBonusow += ((BronMagiczna)ekwipunek.getWyekwipowanaBron()).getAtrybut().getBonusDoSily();
        }

        return sumaBonusow;
    }

    public double getSzansaNaNatychmiastoweZabicie() {
        double sumaBonusow = 0;
        for(Przedmiot p: ekwipunek.getEkwipunekFabularne()) {
            sumaBonusow += p.getAtrybut().getSzansaNaNatychmiastoweZabicie();
        }
        if(ekwipunek.getWyekwipowanaBron() instanceof BronFizyczna) {
            sumaBonusow += ((BronFizyczna)ekwipunek.getWyekwipowanaBron()).getAtrybut().getSzansaNaNatychmiastoweZabicie();
        } else {
            sumaBonusow += ((BronMagiczna)ekwipunek.getWyekwipowanaBron()).getAtrybut().getSzansaNaNatychmiastoweZabicie();
        }

        return sumaBonusow;
    }

    void aktualizujPostepFabularny(int etap) {
        postepFabularny = etap;
    }
}