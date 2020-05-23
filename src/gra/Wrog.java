package gra;

import java.util.List;
import java.util.Random;

public class Wrog extends NPC{
    private double punktyZycia;
    private double bazowyAtak;

    public Wrog(String imie) {
        super(imie);
        punktyZycia = 100;
        bazowyAtak = 100;
    }

    public Wrog(String imie, int punktyZycia, int bazowyAtak) {
        super(imie);
        this.punktyZycia = punktyZycia;
        this.bazowyAtak = bazowyAtak;
    }

    public double getPunktyZycia() {
        return punktyZycia;
    }

    public double getBazowyAtak() {
        return bazowyAtak;
    }

    public void otryzmajObrazenia(double wartosc) {
        if(punktyZycia - wartosc <= 0){
            punktyZycia = 0;
        }else {
            punktyZycia -=wartosc;
        }
    }

    public double zadajObrazenia(){
        return this.generujEkwipunek().getWyekwipowanaBron().getWartosc();
    }

    @Override
    public Ekwipunek generujEkwipunek() {
        Ekwipunek ekwipunek = new Ekwipunek();
        List<Para<String, String>> bronFizycznaTMP = List.of(
                new Para<>("Ostrze Zniszczonego Krola", "opis"),
                new Para<>("Ostrze Nieskonoczonosci", "opis")
        );
        List<Para<String, String>> bronMagicznaTMP = List.of(
                new Para<>("Rozdzka Zniszczenia", "opis"),
                new Para<>("Kosa Statika", "opis")
        );

        Random rand = new Random();
        if(rand.nextInt(bronFizycznaTMP.size()) < bronFizycznaTMP.size()/2){
            int indeks = rand.nextInt(bronFizycznaTMP.size());
            ekwipunek.wlozBron(new BronFizyczna(
                    bronFizycznaTMP.get(indeks).getPierwszy(),
                    bronFizycznaTMP.get(indeks).getDrugi(),
                    rand.nextDouble()*100,
                    rand.nextInt(100),
                    rand.nextDouble()*100,
                    rand.nextDouble()*100
            ));
        }else {
            int indeks = rand.nextInt(bronMagicznaTMP.size());
            ekwipunek.wlozBron(new BronMagiczna(
                    bronMagicznaTMP.get(indeks).getPierwszy(),
                    bronMagicznaTMP.get(indeks).getDrugi(),
                    rand.nextDouble()*100,
                    rand.nextInt(100),
                    rand.nextDouble()*100,
                    rand.nextDouble()*100
            ));
        }
        return ekwipunek;
    }
}
