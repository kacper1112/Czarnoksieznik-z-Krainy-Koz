package gra;

import java.util.List;
import java.util.Random;

public class Wrog extends NPC {
    private double obecnePunktyZycia;
    private final double maksymalnePunktyZycia;
    private final double bazowyAtak;

    public Wrog(String imie) {
        super(imie);
        this.maksymalnePunktyZycia = 100;
        this.obecnePunktyZycia = maksymalnePunktyZycia;
        this.bazowyAtak = 100;
    }

    public Wrog(String imie, int maksymalnePunktyZycia, int bazowyAtak ){//, boolean czyJestBossem) {
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

    // todo: przeciwnik posluguje sie bronia o ile ma jakas w ekwipunku, jak nie to walczy nwm szponami XD
    public double zadajObrazenia(){
        if(this.getEkwipunek().getWyekwipowanaBron() != null) {
            return this.getEkwipunek().getWyekwipowanaBron().zadajObrazenia();
        }
        return getBazowyAtak();
    }

    @Override
    public Ekwipunek generujEkwipunek() {
        //System.out.println("GENERATOR DLA WROGA");test czy korzysta z dobrego generatora - zaliczony
        Ekwipunek ekwipunekTMP = new Ekwipunek(TYP_POSIADACZA_EKWIPUNKU.WROG);
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
            ekwipunekTMP.wlozBronFizyczna(new BronFizyczna(
                    bronFizycznaTMP.get(indeks).getPierwszy(),
                    bronFizycznaTMP.get(indeks).getDrugi(),
                    rand.nextDouble()*100,
                    rand.nextInt(100),
                    rand.nextDouble()*100,
                    rand.nextDouble()*100
            ));
        }else {
            int indeks = rand.nextInt(bronMagicznaTMP.size());
            ekwipunekTMP.wlozBronMagiczna(new BronMagiczna(
                    bronMagicznaTMP.get(indeks).getPierwszy(),
                    bronMagicznaTMP.get(indeks).getDrugi(),
                    rand.nextDouble()*100,
                    rand.nextInt(100),
                    rand.nextDouble()*100,
                    rand.nextDouble()*100
            ));
        }
        return ekwipunekTMP;
    }
}
