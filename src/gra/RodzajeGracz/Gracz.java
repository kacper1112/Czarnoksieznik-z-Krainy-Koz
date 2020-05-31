package gra.RodzajeGracz;

import gra.ElementyPomocnicze.Ekwipunek;
import gra.ElementyPomocnicze.generatorEkwipunku;
import gra.GraWlasciwa.Gra;
import gra.RodzajePrzedmiot.BronFizyczna;
import gra.RodzajePrzedmiot.BronMagiczna;
import gra.RodzajePrzedmiot.PrzedmiotFabularny;
import gra.RodzajePrzedmiot.PrzedmiotPozywienie;

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
        this.maksymalnePunktyZycia = maksymalnePunktyZycia;
        this.obecnePunktyZycia = maksymalnePunktyZycia;
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

    public void setMaksymalnePunktyZycia(double maksymalnePunktyZycia) {
        this.maksymalnePunktyZycia = maksymalnePunktyZycia;
    }

    public double getObecnePunktyZycia() {
        return obecnePunktyZycia;
    }

    public void setObecnePunktyZycia(double obecnePunktyZycia) {
        this.obecnePunktyZycia = obecnePunktyZycia;
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
        System.out.println("Obecnie masz " + obecnePunktyZycia + " punktow zycia");
    }

    // gracz zwieksza swoje punkty zycia
    public void zwiekszPunktyZycia(double wartosc) {
        if(obecnePunktyZycia + wartosc >= maksymalnePunktyZycia) {
            obecnePunktyZycia = maksymalnePunktyZycia;
        } else {
            obecnePunktyZycia += wartosc;
        }
        System.out.println("Obecnie masz " + obecnePunktyZycia + " punktow zycia");
    }

    public abstract double zadajObrazenia();
    public abstract double zadajObrazeniaSpecjalne();
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
    }

    // wywolaj wskazowke, jezeli masz wystarczajaca ilosc inteligencji
    public void uzyjPrzedmiotuFabularnego(int index) {
        this.ekwipunek.getEkwipunekFabularne().get(index).getWskazowka(this.inteligencja);
    }

    // zmien wyekwipowana bron na Fizyczna
    public void zmienBronNaFizyczna(int index) {
        if(index < this.ekwipunek.getEkwipunekBronFizyczna().size()) {
            this.ekwipunek.zmienWyekwipowanaBronNaFiczyna(index);
            System.out.println("Zmieniles bron na: " + ekwipunek.getEkwipunekBronFizyczna().get(index));
        } else {
            System.out.println("Bron o podanym indeksie nie istnieje");
        }
    }

    // zmien wyekwipowana bron na Fizyczna
    public void zmienBronNaMagiczna(int index) {
        if(index < this.ekwipunek.getEkwipunekBronMagiczna().size()) {
            this.ekwipunek.zmienWyekwipowanaBronNaMagiczna(index);
            System.out.println("Zmieniles bron na: " + ekwipunek.getEkwipunekBronMagiczna().get(index));
        } else {
            System.out.println("Bron o podanym indeksie nie istnieje");
        }
    }

    public int pokazEkwipunek() {
        int indeks = 1;
        if(ekwipunek.isEmpty()) {
            System.out.println("Twoj ekwipunek jest pusty!");
            return 0;
        }

        System.out.println("Twoje przedmioty:");
        if(!ekwipunek.getEkwipunekPozywienie().isEmpty()) {
            System.out.println("Pozywienie:");
            for(PrzedmiotPozywienie p: ekwipunek.getEkwipunekPozywienie()) {
                System.out.println(indeks++ + ". " + p.getNazwa());
            }
        }
        if(!ekwipunek.getEkwipunekBronFizyczna().isEmpty()) {
            System.out.println("Bron fizyczna:");
            for(BronFizyczna bron: ekwipunek.getEkwipunekBronFizyczna()) {
                System.out.println(indeks++ + ". " + bron.getNazwa());
            }
        }
        if(!ekwipunek.getEkwipunekBronMagiczna().isEmpty()) {
            System.out.println("Bron magiczna:");
            for(BronMagiczna bron: ekwipunek.getEkwipunekBronMagiczna()) {
                System.out.println(indeks++ + ". " + bron.getNazwa());
            }
        }
        if(!ekwipunek.getEkwipunekFabularne().isEmpty()) {
            System.out.println("Przedmioty fabularne:");
            for(PrzedmiotFabularny p: ekwipunek.getEkwipunekFabularne()) {
                System.out.println(indeks++ + ". " + p.getNazwa());
            }
        }
        return indeks - 1;
    }

    /*
    public void menuEkwipunku() {
        int indeks;

        if(ekwipunek.isEmpty()) {
            System.out.println("Twoj ekwipunek jest pusty!");
            return;
        }
        indeks = pokazEkwipunek();
        System.out.println("Wybierz przedmiot do uzycia lub bron do wyekwipowania");
        indeks = Gra.wczytajWyborGracza(indeks);

        // obliczamy z ktorej kategorii chcemy wyciagnac przedmiot
        int rozmiarEq = 0;
        boolean uzytoPrzedmiot = false;

        rozmiarEq += ekwipunek.getEkwipunekPozywienie().size();
        if(rozmiarEq >= indeks) {
            uzyjPozywienia(indeks);
            uzytoPrzedmiot = true;
        }
        rozmiarEq += ekwipunek.getEkwipunekBronFizyczna().size();
        if(!uzytoPrzedmiot && rozmiarEq >= indeks) {
            zmienBronNaFizyczna(indeks);
            uzytoPrzedmiot = true;
        }
        rozmiarEq += ekwipunek.getEkwipunekBronMagiczna().size();
        if(!uzytoPrzedmiot && rozmiarEq >= indeks) {
            zmienBronNaMagiczna(indeks);
            uzytoPrzedmiot = true;
        }
        if(!uzytoPrzedmiot) {
            uzyjPrzedmiotuFabularnego(indeks);
        }
    }
    */
    public String toString() {
        return "Masz obecnie " +
                obecnePunktyZycia +" punktow zycia, " +
                sila + " sily, "+
                inteligencja +" inteligencji. Posiadasz " +
                poziom + " poziom oraz " +
                punktyDoswiadczenia + " punktow zycia. Masz " +
                pieniadze + " zlotych monet" +
                this.ekwipunek.getWyekwipowanaBron().toString() + " to Twoja bron";
    }

    double obliczWplywAtrybutowPrzedmiotowNaOtrzymanePrzezWojownikaObrazenia() {return 0;}

    void aktualizujPostepFabularny(int etap) {
        postepFabularny = etap;
    }
}