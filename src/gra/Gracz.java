package gra;

public abstract class Gracz {
    //Ekwipunek ekwipunek;
    private double maksymalnePunktyZycia;
    private double obecnePunktyZycia;
    private double sila;
    private double inteligencja;
    private int poziom;
    private double punktyDoswiadczenia;
    private int postepFabularny;
    private double pieniadze;

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

    void zmniejszPunktyZycia(double wartosc) {
        if(obecnePunktyZycia <= wartosc) {
            obecnePunktyZycia = 0;
        } else {
            obecnePunktyZycia -= wartosc;
        }
    }

    void zwiekszPunktyZycia(double wartosc) {
       if(obecnePunktyZycia + wartosc >= maksymalnePunktyZycia) {
           obecnePunktyZycia = maksymalnePunktyZycia;
       } else {
           obecnePunktyZycia += wartosc;
       }
    }

    void otrzymajObrazenia(double wartosc) {
        // jakis skomplikowany wzorek
        double obrazenia = wartosc * 0.5;
    }

    void aktualizujPostepFabularny(int etap) {
        postepFabularny = etap;
    }


}
