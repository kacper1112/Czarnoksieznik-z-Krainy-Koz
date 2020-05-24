package gra;

import java.util.List;
import java.util.Random;

public class Wrog extends NPC{
    private double obecnePunktyZycia;
    private double maksymalnePunktyZycia;
    private final double bazowyAtak;

    public Wrog(String imie) {
        super(imie);
        this.maksymalnePunktyZycia = 100;
        this.obecnePunktyZycia = maksymalnePunktyZycia;
        this.bazowyAtak = 100;
    }

    public Wrog(String imie, int maksymalnePunktyZycia, int bazowyAtak) {
        super(imie);
        this.maksymalnePunktyZycia = maksymalnePunktyZycia;
        this.obecnePunktyZycia = this.maksymalnePunktyZycia;
        this.bazowyAtak = bazowyAtak;
    }

    public double getObecnePunktyZycia() {
        return obecnePunktyZycia;
    }

    public double getMaksymalnePunktyZycia() {
        return maksymalnePunktyZycia;
    }

    public double getBazowyAtak() {
        return bazowyAtak;
    }

    public void otrzymajObrazenia(double wartosc) {
        if(obecnePunktyZycia - wartosc <= 0){
            obecnePunktyZycia = 0;
        } else {
            obecnePunktyZycia -=wartosc;
        }
    }

    public double zadajObrazenia(){
        return this.getEkwipunek().getWyekwipowanaBron().zadajObrazenia();
    }

    @Override
    public Ekwipunek generujEkwipunek() {

        Ekwipunek ekwipunek = new Ekwipunek();
        List<Para<String, String>> bronFizycznaTMP = List.of(
                new Para<>("Ostrze Zniszczonego Krola", "opis"),
                new Para<>("Ostrze Nieskonczonosci", "opis")
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
