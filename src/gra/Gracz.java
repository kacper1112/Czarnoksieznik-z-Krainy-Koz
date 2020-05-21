package gra;

public abstract class Gracz {
    // pola klasy Gracza
    Ekwipunek ekwipunek;
    double maksymalnePunktyZycia;
    double obecnePunktyZycia;
    double sila;
    double inteligencja;
    int poziom;
    double punktyDoswiadczenia;
    int postepFabularny;
    double pieniadze;

    // gettery i settery Gracza
    public double getMaksymalnePunktyZycia() {
        return maksymalnePunktyZycia;
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
    }

    // gracz otrzymuje obrazenia
    // dodac co sie dzieje, gdy obecnePunktyZycia = 0
    void zmniejszPunktyZycia(double wartosc) {
        if(obecnePunktyZycia <= wartosc) {
            obecnePunktyZycia = 0;
        } else {
            obecnePunktyZycia -= wartosc;
        }
    }

    // gracz zwieksza swoje punkty zycia
    void zwiekszPunktyZycia(double wartosc) {
        if(obecnePunktyZycia + wartosc >= maksymalnePunktyZycia) {
            obecnePunktyZycia = maksymalnePunktyZycia;
        } else {
            obecnePunktyZycia += wartosc;
        }
    }

    public abstract double zadajObrazenia();
    public abstract void otrzymajObrazenia(double wartosc);

    // wkladanie przedmiotow do ekwipunku
    public void wlozBronFizycznaDoEkwipunku(BronFizyczna bronFizyczna) {
        this.ekwipunek.wlozBron(bronFizyczna);
    }

    public void wlozBronMagicznaDoEwkipunku(BronMagiczna bronMagiczna) {
        this.ekwipunek.wlozBron(bronMagiczna);
    }

    public void wlozPozywienieDoEkwipunku(Pozywienie pozywienie) {
        this.ekwipunek.wlozPozywienie(pozywienie);
    }

    public void wlozPrzedmiotFabularnyDoEkwipunku(PrzedmiotyFabularne przedmiotFabularny) {
        this.ekwipunek.wlozFabularne(przedmiotFabularny);
    }

    // dodac uzywanie pozywienia
    public void uzyjPozywienia(int index) {

    }

    public String toString() {
        return "Masz obecnie " +
                obecnePunktyZycia +" punktow zycia, " +
                sila + " sily, "+
                inteligencja +" inteligencji. Posiadasz " +
                poziom + " poziom oraz " +
                punktyDoswiadczenia + " punktow zycia. Masz " +
                pieniadze + " zlotych monet.";
    }

    double obliczWplywAtrybutowPrzedmiotowNaOtrzymanePrzezWojownikaObrazenia() {return 0;}

    void aktualizujPostepFabularny(int etap) {
        postepFabularny = etap;
    }

//    void uzyjPrzedmiotu(Pozywienie pozywienie) {
//
//    }
//
//    void uzyjPrzedmiotu(Fabularny fabularny) {
//
//    }
}