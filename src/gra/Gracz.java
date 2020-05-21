package gra;

public abstract class Gracz {
    //Ekwipunek ekwipunek;
    double maksymalnePunktyZycia;
    double obecnePunktyZycia;
    double sila;
    double inteligencja;
    int poziom;
    double punktyDoswiadczenia;
    int postepFabularny;
    double pieniadze;

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

class Wojownik extends Gracz {
    void otrzymajObrazenia(double wartosc) {
        // obrazenia zostaja pomniejszone o tyle % ile sily ma wojownik



        double obrazenia = (1 - sila / 100) * wartosc;
        zmniejszPunktyZycia(obrazenia);
    }
}

class Mag extends Gracz {
    void otrzymajObrazenia(double wartosc) {

    }
}

class Kaplan extends Gracz {
    void otrzymajObrazenia(double wartosc) {

    }
}
