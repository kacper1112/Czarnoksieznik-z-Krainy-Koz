package gra.NPC;

import gra.ElementyPomocnicze.Ekwipunek;
import gra.ElementyPomocnicze.KolorTekstu;
import gra.ElementyPomocnicze.Para;
import gra.ElementyPomocnicze.TYP_POSIADACZA_EKWIPUNKU;
import gra.RodzajePrzedmiot.BronFizyczna;
import gra.RodzajePrzedmiot.BronMagiczna;
import gra.RodzajePrzedmiot.PrzedmiotFabularny;
import gra.RodzajePrzedmiot.PrzedmiotPozywienie;

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

    public Wrog(String imie, int maksymalnePunktyZycia, int bazowyAtak ){
        super(imie);
        this.maksymalnePunktyZycia = maksymalnePunktyZycia;
        this.obecnePunktyZycia = this.maksymalnePunktyZycia;
        this.bazowyAtak = bazowyAtak;
    }

    public Wrog(String imie, int maksymalnePunktyZycia, int bazowyAtak , boolean czyBron){
        super(imie);
        this.maksymalnePunktyZycia = maksymalnePunktyZycia;
        this.obecnePunktyZycia = this.maksymalnePunktyZycia;
        this.bazowyAtak = bazowyAtak;
        if(this.getEkwipunek().getEkwipunekBronFizyczna().size()>0){
            this.getEkwipunek().zmienWyekwipowanaBronNaFizyczna(0, true);
        } else if(this.getEkwipunek().getEkwipunekBronMagiczna().size()>0) {
            this.getEkwipunek().zmienWyekwipowanaBronNaMagiczna(0, true);
        }
    }

    public Wrog(String imie, int maksymalnePunktyZycia, int bazowyAtak , PrzedmiotFabularny przedmiotFabularny){
        super(imie);
        this.maksymalnePunktyZycia = maksymalnePunktyZycia;
        this.obecnePunktyZycia = maksymalnePunktyZycia;
        this.bazowyAtak = bazowyAtak;
        this.getEkwipunek().wlozDoEkwipunku(przedmiotFabularny);
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
        if(this.getEkwipunek().getWyekwipowanaBron() != null) {
            return this.getEkwipunek().getWyekwipowanaBron().zadajObrazenia();
        }
        KolorTekstu.printCzerwony("Wrog nie posiada broni, ale chyba walczy calkiem niezle i bez niej...");
        return getBazowyAtak();
    }

    /**
     * Wrog na poczatku dostaje:
     * jedna pota albo mieso
     * podstawowa bron fizyczna lub magiczna - szansa 50%
     */
    @Override
    public Ekwipunek generujEkwipunek() {
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
        if(rand.nextDouble() < 0.5){
            ekwipunekTMP.wlozDoEkwipunku(new PrzedmiotPozywienie(
                    "Ryba",
                    "Rybka wylowiona dzisaj rano za pomoca twojej wedki",
                    10,
                    10,
                    10
            ));
        }else {
            ekwipunekTMP.wlozDoEkwipunku(new PrzedmiotPozywienie(
                    "Makaron z groszkiem",
                    "Tradycyjne danie, ktore odkupiles od pewnej staruszki",
                    10,
                    10,
                    10
            ));
        }

        if(rand.nextInt(bronFizycznaTMP.size()) < bronFizycznaTMP.size()/2){
            int indeks = rand.nextInt(bronFizycznaTMP.size());
            ekwipunekTMP.wlozDoEkwipunku(new BronFizyczna(
                    bronFizycznaTMP.get(indeks).getPierwszy(),
                    bronFizycznaTMP.get(indeks).getDrugi(),
                    rand.nextInt(100),
                    rand.nextInt(100),
                    rand.nextDouble()*100,
                    Math.random()
            ));
        }else {
            int indeks = rand.nextInt(bronMagicznaTMP.size());
            ekwipunekTMP.wlozDoEkwipunku(new BronMagiczna(
                    bronMagicznaTMP.get(indeks).getPierwszy(),
                    bronMagicznaTMP.get(indeks).getDrugi(),
                    rand.nextInt(100),
                    rand.nextInt(100),
                    rand.nextDouble()*100,
                    Math.random()
            ));
        }
        /*
        if(ekwipunekTMP.getEkwipunekBronFizyczna().size()>0){
            ekwipunekTMP.zmienWyekwipowanaBronNaFizyczna(0, true);
        } else if(ekwipunekTMP.getEkwipunekBronMagiczna().size()>0) {
            ekwipunekTMP.zmienWyekwipowanaBronNaMagiczna(0, true);
        }
         */

        return ekwipunekTMP;
    }
}
